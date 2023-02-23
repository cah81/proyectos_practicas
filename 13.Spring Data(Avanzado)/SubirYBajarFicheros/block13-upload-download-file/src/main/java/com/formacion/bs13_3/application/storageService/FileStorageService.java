package com.formacion.bs13_3.application.storageService;

import com.formacion.bs13_3.domain.model.File;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.stream.Stream;

public interface FileStorageService {


    public void deleteAll() throws IOException;
    public File storeFile(MultipartFile file,String type);
    public Resource loadFileAsResource(String fileName);

    public File getFile(String id);

    public void deleteAllFileBd();

    public void setLocation(String fileStorageLocation);

    public void init();
    public Resource loadFileAsResourceById(String id);

}
