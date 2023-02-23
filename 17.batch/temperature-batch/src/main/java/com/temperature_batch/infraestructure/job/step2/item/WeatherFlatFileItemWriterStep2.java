package com.temperature_batch.infraestructure.job.step2.item;

import com.temperature_batch.infraestructure.dto.WeatherDto;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;

public class WeatherFlatFileItemWriterStep2 extends FlatFileItemWriter<WeatherDto> {

    private final Resource outPutResource = new FileSystemResource("output/REGISTROS_ERRONEO.csv");

    public WeatherFlatFileItemWriterStep2(){
        Resource outPutResource= new FileSystemResource("output/REGISTROS_ERRONEO.csv");
        setResource(outPutResource);
        setAppendAllowed(true);
        setLineAggregator(new DelimitedLineAggregator<WeatherDto>(){
            {
                setDelimiter(",");
                setFieldExtractor(new BeanWrapperFieldExtractor<WeatherDto>(){
                    {
                        setNames(new String [] {"location","date","temperature"});
                    }
                });
            }
        });
    }













}
