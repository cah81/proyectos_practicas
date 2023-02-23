package com.temperature_batch.infraestructure.job.step4.item;

import com.temperature_batch.domain.WeatherRisk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;

import java.util.List;

public class WeatherRiskItemWriterStep4 extends FlatFileItemWriter<WeatherRisk> {

    public WeatherRiskItemWriterStep4(){
        Resource outPut = new FileSystemResource("output/out.csv");
        setResource(outPut);
        setAppendAllowed(false);
        setLineAggregator(new DelimitedLineAggregator<WeatherRisk>(){
            {
                setDelimiter(",");
                setFieldExtractor(new BeanWrapperFieldExtractor<WeatherRisk>(){
                    {
                        setNames(new String []{"location","month","year","numberMeasurements","averageTemperature","risk"});
                    }
                });
            }
        });


    }


}
