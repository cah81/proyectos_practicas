package com.formacion.bs13_3.infraestructure.controller;

import com.formacion.bs13_3.application.storageService.FileStorageService;
import com.formacion.bs13_3.domain.model.File;
import com.formacion.bs13_3.domain.payload.UploadFileResponse;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class UploadController {
    @Autowired
    FileStorageService service;

    @PostMapping("/uploadFile/{type}")
    public UploadFileResponse uploadFile(@RequestParam("file") MultipartFile file,@PathVariable String type) {
        File fileNew = service.storeFile(file,type);
        UploadFileResponse response = new UploadFileResponse();
        return new UploadFileResponse(fileNew.getId(), fileNew.getName(), fileNew.getUrl(), fileNew.getType(), fileNew.getSize());

    }

    @PostMapping("/uploadMultipleFiles/{type}")
    public List<UploadFileResponse> uploadMultipleFiles(@RequestParam("files") MultipartFile[] files, String type) {
        return Arrays.asList(files)
                .stream()
                .map(file -> uploadFile(file,type))
                .collect(Collectors.toList());
    }

    @GetMapping("/downloadFile/{fileId}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileId) {
        // Load file from database
        Resource resourceFile = service.loadFileAsResourceById(fileId);
        File fileDb = service.getFile(fileId);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,"attachment; filename=\""+
                resourceFile.getFilename() + "\"").body(resourceFile);



    }

    @GetMapping("/files/name/{filename:.+}")
    public ResponseEntity<Resource> getFilebyName(@PathVariable String filename) {
        Resource file = service.loadFileAsResource(filename);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }

    @DeleteMapping("/deletefile")
    public String deleteAllFile() throws IOException {

        service.deleteAll();
        return "Files deleted";
    }

    @DeleteMapping("/deletefileBD")
    public String deleteAllFileBD(){
        service.deleteAllFileBd();
        return "Registers deletes in bd";
    }
    @GetMapping("/setpath")
    public String setPath(@RequestParam String path){
        service.setLocation(path);
        service.init();
        return path;
    }
}
