package com.formacion.bs13_3.domain.payload;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class UploadFileResponse {

    private String id;
    private String name;
    private String fileDownloadUri;
    private String type;
    private long size;

    private Date uploadDate;


   public UploadFileResponse(String id, String fileName, String fileDownloadUri, String fileType, long size) {
        this.id = id;
        this.name = fileName;
        this.fileDownloadUri = fileDownloadUri;
        this.type = fileType;
        this.size = size;
        this.uploadDate = new Date();
    }

}