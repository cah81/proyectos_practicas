package com.temperature_batch.infraestructure.job.step4.mapper;

import com.temperature_batch.domain.WeatherRisk;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class WeatherRiskRowMapperStep4 implements RowMapper<WeatherRisk> {
    @Override
    public WeatherRisk mapRow(ResultSet rs, int rowNum) throws SQLException {
        WeatherRisk weatherRisk = new WeatherRisk();
        weatherRisk.setLocation(rs.getString("location"));
        weatherRisk.setYear(rs.getInt("year"));
        weatherRisk.setMonth(rs.getInt("month"));
        weatherRisk.setNumberMeasurements(rs.getInt("number_measurements"));
        weatherRisk.setAverageTemperature(rs.getFloat("average_temperature"));
        weatherRisk.setRisk(rs.getString("risk"));
        return weatherRisk;
    }
    }


