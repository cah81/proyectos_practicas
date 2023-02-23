package com.temperature_batch.infraestructure.job.step4.item;

import com.temperature_batch.domain.WeatherRisk;
import com.temperature_batch.infraestructure.job.step4.mapper.WeatherRiskRowMapperStep4;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;

public class WeatherRiskItemReaderStep4 {
    @Autowired
    DataSource dataSource;
    @Bean
    public JdbcCursorItemReader<WeatherRisk> readerStep4(){

        JdbcCursorItemReader<WeatherRisk> reader = new JdbcCursorItemReader<>();
        reader.setSql("SELECT t.location,t.year ,t.month ,t.number_measurements,t.average_temperature,t.risk " +
                "FROM weather_risk AS t ");
        reader.setDataSource(dataSource);
        reader.setFetchSize(100);
        reader.setRowMapper(new WeatherRiskRowMapperStep4());
        return reader;
    }
}
