package com.dongle.dongle.service;

import com.dongle.dongle.entitiy.FileEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

public interface FileService {
    String fileSave(MultipartFile file);

    void save(FileEntity fileEntity);
}
