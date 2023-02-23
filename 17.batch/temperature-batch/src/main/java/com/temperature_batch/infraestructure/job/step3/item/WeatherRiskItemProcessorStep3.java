package com.temperature_batch.infraestructure.job.step3.item;

import com.temperature_batch.domain.WeatherRisk;
import com.temperature_batch.infraestructure.dto.WeatherRiskDto;
import org.springframework.batch.item.ItemProcessor;

public class WeatherRiskItemProcessorStep3 implements ItemProcessor<WeatherRiskDto, WeatherRisk> {
    @Override
    public WeatherRisk process(WeatherRiskDto weatherRiskDto) throws Exception {
        WeatherRisk weatherRisk = new WeatherRisk();
        weatherRisk.setYear(weatherRiskDto.getYear());
        weatherRisk.setMonth(weatherRiskDto.getMonth());
        weatherRisk.setLocation(weatherRiskDto.getLocation());
        weatherRisk.setNumberMeasurements(weatherRiskDto.getNumberMeasurements());
        weatherRisk.setAverageTemperature(weatherRiskDto.getAverageTemperature());
        if(weatherRiskDto.getAverageTemperature()>=36)
            weatherRisk.setRisk("HIGH");
        else if (weatherRiskDto.getAverageTemperature()<36 && weatherRiskDto.getAverageTemperature()>32)
            weatherRisk.setRisk("MEDIUM");
        else
            weatherRisk.setRisk("LOW");
        return weatherRisk;
    }
}
