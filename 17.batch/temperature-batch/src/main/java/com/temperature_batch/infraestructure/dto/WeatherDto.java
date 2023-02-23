package com.temperature_batch.infraestructure.dto;

import com.temperature_batch.domain.Weather;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.text.SimpleDateFormat;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class WeatherDto {
    private String location;
    private String date;
    private Integer temperature;

    public WeatherDto(Weather weather){
        this.location = weather.getLocation();
        this.date = new SimpleDateFormat("dd/MM/yyyy").format(weather.getDate());
        this.temperature = weather.getTemperature();
    }
}
