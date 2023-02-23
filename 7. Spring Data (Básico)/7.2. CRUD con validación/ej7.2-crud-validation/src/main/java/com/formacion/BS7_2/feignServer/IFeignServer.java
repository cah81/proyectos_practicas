package com.formacion.BS7_2.feignServer;
import com.formacion.BS7_2.feignServer.configuration.FeignClientConfig;
import com.formacion.BS7_2.teacher.infraestructure.dto.output.TeacherOutputDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "feignClient", url = "http://localhost:8080",configuration = FeignClientConfig.class)
public interface IFeignServer {

    @GetMapping(value = "/teacher/{id}",consumes = MediaType.APPLICATION_JSON_VALUE)
    public TeacherOutputDto getTeacher(@PathVariable("id")  String idTeacher)throws Exception;
}
