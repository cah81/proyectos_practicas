package com.formacion.bs13_3.application.storageService;

import com.formacion.bs13_3.domain.model.File;

import com.formacion.bs13_3.exception.FileStorageException;
import com.formacion.bs13_3.exception.MyFileNotFoundException;
import com.formacion.bs13_3.infraestructure.configuration.FileStorageProperties;
import com.formacion.bs13_3.infraestructure.repository.FileRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;

import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.nio.file.StandardCopyOption;
import java.util.Date;


@Service
@RequiredArgsConstructor
public class FileStorageServiceImpl implements FileStorageService {

    @Autowired
    FileRepository fileRepository;

    private  Path fileStorageLocation;

    @Autowired
    public FileStorageServiceImpl(FileStorageProperties fileStorageProperties) {

        this.fileStorageLocation = Paths.get(fileStorageProperties.getUploadDir()).toAbsolutePath().normalize();
        //definicion de ubicacion inicial

    }
    public void init(){
        try {
            Files.createDirectories(this.fileStorageLocation);
        } catch (Exception ex) {
            throw new FileStorageException("Could not create the directory where the uploaded files will be stored.", ex);
        }
    }

    //almacener en ubicacion
    public File storeFile(MultipartFile file,String type) {
        // Normalize file name
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        //revision de extension del tipo de archivo
        if(!fileName.substring(fileName.lastIndexOf('.')+1).equals(type))
            throw new RuntimeException("el fichero no corresponde con la extension");

        try {
            // Check if the file's name contains invalid characters
            if (fileName.contains("..")) {
                throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
            }


            // Copy file to the target location (Replacing existing file with the same name)
            Path targetLocation = this.fileStorageLocation.resolve(fileName);


            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);


            String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                    .path("/downloadFile/")
                    .path(fileName)
                    .toUriString();

          //  File dbFile = new File(fileName, file.getContentType(), file.getBytes(), fileDownloadUri, file.getSize());
            File dbFile = new File();
            dbFile.setName(fileName);
            dbFile.setType(file.getContentType());
            dbFile.setUrl(fileDownloadUri);
            dbFile.setSize(file.getSize());
            dbFile.setUploadDate(new Date());

            return fileRepository.save(dbFile);
        } catch (IOException ex) {
            throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
        }
    }

    //para descargar el archivo
    public Resource loadFileAsResource(String fileName) {
        try {
            Path filePath = this.fileStorageLocation.resolve(fileName).normalize();
            Resource resource = new UrlResource(filePath.toUri());
            if (resource.exists()) {
                return resource;
            } else {
                throw new MyFileNotFoundException("File not found " + fileName);
            }
        } catch (MalformedURLException ex) {
            throw new MyFileNotFoundException("File not found " + fileName, ex);
        }
    }

    //para consultar por id

    public Resource loadFileAsResourceById(String id) {

        try {
            String fileName = getFile(id).getName();
            Path filePath = this.fileStorageLocation.resolve(fileName).normalize();
            Resource resource = new UrlResource(filePath.toUri());
            if (resource.exists()) {
                return resource;
            } else {
                throw new MyFileNotFoundException("File not found " + fileName);
            }
        } catch (MalformedURLException ex) {
            throw new MyFileNotFoundException("File not found "  , ex);
        }
    }



    public File getFile(String id) {

       return fileRepository.findById(id).orElseThrow(() -> new MyFileNotFoundException("File not found with id" + id));
    }
    //para eliminar
    public void deleteAll() throws IOException {
        FileSystemUtils.deleteRecursively(fileStorageLocation.toFile());
    }
    //para eliminar archivos en la bd
    public void deleteAllFileBd(){
        fileRepository.deleteAll();
    }
    //para cambiar la ubicacion de descarga de archivo
    public void setLocation(String fileStorageLocation) {

        this.fileStorageLocation= Paths.get(fileStorageLocation);
    }
}

















    /*

    private final Path root= Paths.get("uploads");

    @Override
    public void init() {
        try {
            Files.createDirectory(root);
        } catch (IOException e) {
            throw new RuntimeException("Could not initialize folder for upload!");
        }
    }
    @Override
    public void deleteAll() {
        FileSystemUtils.deleteRecursively(root.toFile());
    }



    @Override
    public File store(MultipartFile file, String type) throws Exception {
        String fileName2= StringUtils.cleanPath(file.getOriginalFilename().replace(" ",""));

        Path rutaArchivo= Paths.get("uploads").resolve(fileName2).toAbsolutePath();

        String fileType= StringUtils.cleanPath(file.getContentType());
        String[] splitfile = fileType.split("/");
        String arraysFile = splitfile[1];

        String fileDownloadUri = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("files/").path(fileName2).toUriString();

        Files.copy(file.getInputStream(),rutaArchivo);
        File filedb= new File(fileName2,fileType,file.getBytes(),fileDownloadUri,LocalDateTime.now());

        return fileRespository.save(filedb);

    }

    @Override
    public Resource load(String filename) {
        try {
            Path file = root.resolve(filename);
            Resource resource = new UrlResource(file.toUri());

            if (resource.exists() && resource.isReadable()) {
                return resource;
            } else {
                throw new RuntimeException("Could not read the file! " + filename);
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException("Error: " + e.getMessage());
        }
    }

    public File getFile(String id) {
        return fileRespository.findById(id).get();
    }

    public Stream<File> getAllFiles() {
        return fileRespository.findAll().stream();
    }*/

