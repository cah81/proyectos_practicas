package com.temperature_batch.infraestructure.job.step2.item;

import com.temperature_batch.domain.Weather;
import com.temperature_batch.infraestructure.dto.WeatherDto;
import org.springframework.batch.item.ItemProcessor;

public class WeatherItemProcessorStep2 implements ItemProcessor<Weather, WeatherDto> {
    @Override
    public WeatherDto process(Weather weather) throws Exception {
       if(weather.getTemperature()>50 || weather.getTemperature()<-20){
           return new WeatherDto(weather);
       }
       return null;
    }
}
