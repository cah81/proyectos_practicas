package com.temperature_batch.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "WeatherRisk")
@NoArgsConstructor
@AllArgsConstructor
public class WeatherRisk {
    @Id
    @GeneratedValue

    private Long id;
    private String location;
    private Integer month;
    private Integer numberMeasurements;
    private Integer year;
    private Float AverageTemperature;
    private String risk;


   @OneToOne
   @JoinColumn(name = "id")
   private Weather weather;

    public WeatherRisk (Weather weather,String risk){
        this.weather = weather;
        this.risk = risk;
    }


}
