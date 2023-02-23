package com.temperature_batch.infraestructure.job.step1.item;

import com.temperature_batch.domain.Weather;
import com.temperature_batch.infraestructure.repository.WeatherRepository;
import com.temperature_batch.infraestructure.repository.WeatherRiskRepository;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class WheatherItemWriterStep1 implements ItemWriter<Weather> {
    @Autowired
    WeatherRepository repository;

    @Autowired
    WeatherRiskRepository riskRepository;


   @Override
    public void write(List<? extends Weather> list) throws Exception {
        for(Weather item:list){
            System.out.println(item);
        }
        repository.saveAll(list);
    }
}
