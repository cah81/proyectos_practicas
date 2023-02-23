package com.temperature_batch.infraestructure.job.step3.mapper;

import com.temperature_batch.infraestructure.dto.WeatherRiskDto;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class WeatherRiskDtoRowMapper implements RowMapper<WeatherRiskDto> {
    @Override
    public WeatherRiskDto mapRow(ResultSet rs, int rowNum) throws SQLException {
        WeatherRiskDto weatherRiskDto = new WeatherRiskDto();
        weatherRiskDto.setLocation(rs.getString("location"));
        weatherRiskDto.setYear(rs.getInt("y"));
        weatherRiskDto.setMonth(rs.getInt("m"));
        weatherRiskDto.setNumberMeasurements(rs.getInt("c"));
        weatherRiskDto.setAverageTemperature(rs.getFloat("average"));
        return weatherRiskDto;
    }
}
