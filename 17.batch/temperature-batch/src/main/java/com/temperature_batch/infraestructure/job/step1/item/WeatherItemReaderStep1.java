package com.temperature_batch.infraestructure.job.step1.item;

import com.temperature_batch.domain.Weather;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.MultiResourceItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.Resource;

public class WeatherItemReaderStep1 {

    @Value("classpath*:/data/inputData*.csv")
    private Resource[] inputResources;

    @Bean
    public MultiResourceItemReader<Weather> multiResourceItemReader(){
        MultiResourceItemReader<Weather> resourceItemReader = new MultiResourceItemReader<>();
        resourceItemReader.setResources(inputResources);
        resourceItemReader.setDelegate(reader());
        return resourceItemReader;
    }


    @SuppressWarnings({ "rawtypes", "unchecked" })
    @Bean
    public FlatFileItemReader<Weather> reader(){
        FlatFileItemReader<Weather> reader = new FlatFileItemReader<>();
        reader.setLinesToSkip(1);
        reader.setLineMapper(new DefaultLineMapper<>(){
            {
                //3 columns in each row
                setLineTokenizer(new DelimitedLineTokenizer(){
                    {
                        setNames(new String[]{"location","date","temperature"});
                    }
                });
                //set values  in Employee Class
                setFieldSetMapper(new BeanWrapperFieldSetMapper<Weather>(){
                    {
                        setTargetType(Weather.class);
                    }
                });
            }
        });
        return reader;
    }
}
