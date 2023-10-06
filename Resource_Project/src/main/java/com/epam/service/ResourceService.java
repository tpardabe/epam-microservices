package com.epam.service;

import com.epam.repo.Music;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ResourceService {
    int upload(MultipartFile audioFile) throws IOException;

    Music get(Integer id);

    List<Integer> delete(List<Integer> ids);
}
