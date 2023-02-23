package com.temperature_batch.infraestructure.job.configuration;

import com.temperature_batch.domain.Weather;
import com.temperature_batch.domain.WeatherRisk;
import com.temperature_batch.infraestructure.dto.WeatherDto;
import com.temperature_batch.infraestructure.dto.WeatherRiskDto;
import com.temperature_batch.infraestructure.job.joblistener.JobExecutionListener;
import com.temperature_batch.infraestructure.job.step1.item.WeatherItemReaderStep1;
import com.temperature_batch.infraestructure.job.step1.item.WheatherItemWriterStep1;
import com.temperature_batch.infraestructure.job.step1.item.WeatherItemProcessorStep1;
import com.temperature_batch.infraestructure.job.step1.listener.WeatherItemProcessorListener;
import com.temperature_batch.infraestructure.job.step1.listener.WeatherItemReaderListener;

import com.temperature_batch.infraestructure.job.step1.listener.WeatherItemWriterListener;

import com.temperature_batch.infraestructure.job.step2.item.WeatherFlatFileItemWriterStep2;
import com.temperature_batch.infraestructure.job.step2.item.WeatherItemProcessorStep2;
import com.temperature_batch.infraestructure.job.step2.item.WeatherItemReaderStep2;
import com.temperature_batch.infraestructure.job.step2.listener.WeatherItemReaderErrorListener;
import com.temperature_batch.infraestructure.job.step3.item.WeatherRiskItemProcessorStep3;
import com.temperature_batch.infraestructure.job.step3.item.WeatherRiskItemReaderStep3;
import com.temperature_batch.infraestructure.job.step3.item.WeatherRiskItemWriterStep3;
import com.temperature_batch.infraestructure.job.step3.mapper.WeatherRiskDtoRowMapper;
import com.temperature_batch.infraestructure.job.step4.item.WeatherRiskItemReaderStep4;
import com.temperature_batch.infraestructure.job.step4.item.WeatherRiskItemWriterStep4;
import com.temperature_batch.infraestructure.job.step4.mapper.WeatherRiskRowMapperStep4;
import com.temperature_batch.infraestructure.repository.WeatherRiskRepository;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;

import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.MultiResourceItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

import javax.sql.DataSource;


@Configuration
@EnableBatchProcessing
public class BatchConfig {
    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Value("classpath*:/data/inputData*.csv")
    private Resource[] inputResources;

    @Autowired
    DataSource dataSource;

    @Autowired
    WeatherRiskRepository riskRepository;

    public void test(){
        riskRepository.findListWeatherRisk();
    }

    @Bean
    public WeatherItemReaderListener readerListener(){
        return new WeatherItemReaderListener();
    }
    @Bean
    public WeatherItemWriterListener writerListener(){
        return new WeatherItemWriterListener();
    }
    @Bean
    public WeatherItemProcessorListener weatherItemProcessorListener(){
        return new WeatherItemProcessorListener();
    }
    @Bean
    public WeatherItemProcessorStep1 weatherItemProcessor(){
        return new WeatherItemProcessorStep1();
    }

    @Bean
    public WheatherItemWriterStep1 writer(){
        return new WheatherItemWriterStep1();
    }
    @Bean
    public WeatherItemReaderStep1 weatherItemReaderStep1(){
        return new WeatherItemReaderStep1();
    }

    @Bean
    public WeatherItemReaderStep2 weatherItemReaderStep2(){
        return new WeatherItemReaderStep2();
    }

    @Bean
    public WeatherRiskItemProcessorStep3 weatherRiskItemProcesor(){
        return  new WeatherRiskItemProcessorStep3();
    }

    @Bean
    public WeatherRiskItemWriterStep3 weatherRiskItemWriter(){
        return new WeatherRiskItemWriterStep3();
    }
    @Bean
    public WeatherItemReaderErrorListener weatherItemReaderErrorListener(){
        return new WeatherItemReaderErrorListener();
    }
    @Bean
    public WeatherRiskItemReaderStep3 weatherRiskItemReaderStep3(){
        return  new WeatherRiskItemReaderStep3();
    }
    @Bean
    public WeatherRiskItemReaderStep4 weatherRiskItemReaderStep4(){
        return new WeatherRiskItemReaderStep4();
    }



    @Bean
    public Job temperatureRegisterJob(Step step1, Step step2 ,JobExecutionListener jobExecutionListener){
        return jobBuilderFactory
                .get("temperatureRegisterJob")
                .incrementer(new RunIdIncrementer())
                .listener(jobExecutionListener)
                .start(step1)
                .next(step2)
                .next(step3())
                .next(step4())
                .build();

    }
    //only allowed to save values in range
    @Bean
    public Step step1(WeatherItemReaderListener readerListener,
                      WeatherItemWriterListener writerListener,
                      WeatherItemProcessorListener processorListener,
                      WeatherItemProcessorStep1 weatherItemProcessorStep1,
                      WeatherItemReaderStep1 weatherItemReaderStep1)
                      {
        return stepBuilderFactory.get("step1")
                .<Weather, Weather>chunk(5)
                .listener(readerListener)
                .listener(writerListener)
                .listener(processorListener)
                .faultTolerant()
                .skipLimit(1000)
                .skip(IllegalArgumentException.class)
                .reader(weatherItemReaderStep1.multiResourceItemReader())
                //.reader(multiResourceItemReader())
                .processor(weatherItemProcessorStep1)
                .writer(writer())
                .build();
    }

    @Bean
    public Step step2(WeatherItemReaderStep2 weatherItemReaderStep2){
        return stepBuilderFactory.get("step2")
                .<Weather,WeatherDto>chunk(100)
                .reader(weatherItemReaderStep2.multiResourceItemReader())
                .processor(new WeatherItemProcessorStep2())
                .writer(new WeatherFlatFileItemWriterStep2())
                .build();}


    @Bean
    public Step step3(){
        return stepBuilderFactory.get("step3")
                .<WeatherRiskDto,WeatherRisk>chunk(100)
                .reader(weatherRiskItemReaderStep3().readerJDBC())
                .processor(weatherRiskItemProcesor())
                .writer(weatherRiskItemWriter())
                .build();

    }

    @Bean
    public Step step4(){
        return stepBuilderFactory.get("step4")
                .<WeatherRisk,WeatherRisk>chunk(100)
                .reader(weatherRiskItemReaderStep4().readerStep4())
                .writer(new WeatherRiskItemWriterStep4())

                .build();

    }





}
