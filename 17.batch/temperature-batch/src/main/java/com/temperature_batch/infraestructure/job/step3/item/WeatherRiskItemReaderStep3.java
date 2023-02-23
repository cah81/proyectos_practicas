package com.temperature_batch.infraestructure.job.step3.item;

import com.temperature_batch.infraestructure.dto.WeatherRiskDto;
import com.temperature_batch.infraestructure.job.step3.mapper.WeatherRiskDtoRowMapper;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

public class WeatherRiskItemReaderStep3 {
    @Autowired
    DataSource dataSource;

    @Bean
    public JdbcCursorItemReader<WeatherRiskDto> readerJDBC(){

        JdbcCursorItemReader<WeatherRiskDto> reader = new JdbcCursorItemReader<>();
        reader.setSql("SELECT t.location,YEAR(t.date) as y,MONTH(t.date) " +
                "as m,COUNT(t.temperature) as c,AVG(t.temperature)" + " as average " +
                "FROM weather AS t " +
                "GROUP BY t.location,YEAR(t.date),MONTH(t.date) ORDER BY t.location");
        reader.setDataSource(dataSource);
        reader.setFetchSize(100);
        reader.setRowMapper(new WeatherRiskDtoRowMapper());
        return reader;
    }


}
