package com.temperature_batch.infraestructure.job.step2.listener;

import com.temperature_batch.domain.Weather;
import com.temperature_batch.infraestructure.job.step1.listener.WeatherItemReaderListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.ItemReadListener;

public class WeatherItemReaderErrorListener implements ItemReadListener<Weather> {
    private static final Logger LOGGER= LoggerFactory.getLogger(WeatherItemReaderErrorListener.class);
    @Override
    public void beforeRead() {
        LOGGER.info("beforeRead");
    }

    @Override
    public void afterRead(Weather weather) {
        LOGGER.info("after read :" + weather.toString());
    }

    @Override
    public void onReadError(Exception e) {
        LOGGER.info("onReadError");
    }
}
