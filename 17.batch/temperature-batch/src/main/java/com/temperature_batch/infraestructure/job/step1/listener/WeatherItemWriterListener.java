package com.temperature_batch.infraestructure.job.step1.listener;


import com.temperature_batch.domain.WeatherRisk;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.ItemWriteListener;

import java.util.List;

public class WeatherItemWriterListener implements ItemWriteListener<WeatherRisk> {
    private static final Logger LOGGER= LoggerFactory.getLogger(WeatherItemWriterListener.class);

    @Override
    public void beforeWrite(List<? extends WeatherRisk> list) {
        LOGGER.info("beforeWrite");
    }

    @Override
    public void afterWrite(List<? extends WeatherRisk> list) {
        for (WeatherRisk weatherRisk : list) {
            LOGGER.info("afterWrite :" + weatherRisk.toString());
        }
    }

    @Override
    public void onWriteError(Exception e, List<? extends WeatherRisk> list) {
        LOGGER.info("onWriteError");
    }
}
