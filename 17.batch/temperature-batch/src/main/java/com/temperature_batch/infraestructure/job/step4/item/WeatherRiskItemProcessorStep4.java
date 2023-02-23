package com.temperature_batch.infraestructure.job.step4.item;

import com.temperature_batch.domain.WeatherRisk;
import org.springframework.batch.item.ItemProcessor;

public class WeatherRiskItemProcessorStep4 implements ItemProcessor<WeatherRisk,WeatherRisk> {
    @Override
    public WeatherRisk process(WeatherRisk weatherRisk) throws Exception {
        return weatherRisk;
    }
}
