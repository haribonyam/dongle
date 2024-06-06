package com.dongle.dongle.service;

import com.dongle.dongle.entitiy.FileEntity;
import com.dongle.dongle.repository.FileRepository;
import jdk.jfr.Registered;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FileServiceImpl implements FileService{
    private final FileRepository fileRepository;

    @Value("${upload.path}")
    private String uploadPath;

    @Override
    public String fileSave(MultipartFile file) {


        try {

            String fileName = UUID.randomUUID()+file.getOriginalFilename();
            String savePath = System.getProperty("user.dir")+uploadPath +"/";
            File dest = new File(savePath,fileName);
            file.transferTo(dest);

            return "/img/"+fileName;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }



    @Override
    public void save(FileEntity fileEntity) {
        fileRepository.save(fileEntity);
    }
}
