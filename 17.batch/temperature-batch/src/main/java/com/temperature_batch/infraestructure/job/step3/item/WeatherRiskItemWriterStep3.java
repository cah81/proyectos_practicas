package com.temperature_batch.infraestructure.job.step3.item;

import com.temperature_batch.domain.WeatherRisk;
import com.temperature_batch.infraestructure.repository.WeatherRiskRepository;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class WeatherRiskItemWriterStep3 implements ItemWriter<WeatherRisk> {
    @Autowired
    WeatherRiskRepository riskRepository;
    @Override
    public void write(List<? extends WeatherRisk> list) throws Exception {

        for(WeatherRisk item:list){
            System.out.println(item);
        }
        riskRepository.saveAll(list);
    }

    }

