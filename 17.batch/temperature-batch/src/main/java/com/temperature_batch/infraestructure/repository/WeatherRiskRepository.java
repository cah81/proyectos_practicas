package com.temperature_batch.infraestructure.repository;

import com.temperature_batch.domain.WeatherRisk;
import com.temperature_batch.infraestructure.dto.WeatherRiskDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WeatherRiskRepository extends JpaRepository<WeatherRisk,Long> {
 @Query(value = "SELECT t.location,YEAR(t.date) as y,MONTH(t.date) as m,COUNT(t.temperature) as c,AVG(t.temperature) as average FROM weather AS t GROUP BY t.location,YEAR(t.date),MONTH(t.date) ORDER BY t.location",nativeQuery = true)
 List<WeatherRiskDto> findListWeatherRisk();
}
