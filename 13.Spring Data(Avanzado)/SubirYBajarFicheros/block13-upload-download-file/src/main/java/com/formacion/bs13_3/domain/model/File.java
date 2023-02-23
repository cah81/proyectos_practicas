package com.formacion.bs13_3.domain.model;


import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;


import javax.persistence.*;
import java.util.Date;

@Data
@Getter
@Setter
@NoArgsConstructor
@Entity
public class File {
    @Id
    @GeneratedValue(generator = "file-seq")
    @GenericGenerator(name = "file-seq", strategy = "com.formacion.bs13_3.generatorId.Generator_ID")
    @Column(name = "id")
    public String id;

    private String name;
    private String url;
    private String type;

    private Long size;


    private Date uploadDate;





    public File(String name, String type,String url,long size) {
        this.name = name;
        this.type = type;

        this.url = url;
        this.size = size;
        this.uploadDate = new Date();


    }



}
