package service.impl;

import mapper.ImageMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import po.Image;
import service.UploadService;

@Service
public class UploadServiceImpl implements UploadService {
    @Autowired
    private ImageMapper imageMapper;

    public void insertFile(String filename, String src, String description) {
        Image image = new Image(filename, src, description);
        imageMapper.insert(image);
    }
}
