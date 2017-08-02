package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import service.UploadService;

import java.io.File;
import java.util.UUID;

@Controller
@RequestMapping
public class UploadController {
    @Autowired
    private UploadService uploadService;

    @RequestMapping("/upload")
    public String upload(@RequestParam("file")MultipartFile pictureFile, @RequestParam("description")String description) throws Exception {
        String FILEPATH = "C:\\Users\\ShawyerPeng\\Desktop\\pictures\\";
        String originalFilename = "";
        String newFileName = "";

        //进行图片的上传
        if (pictureFile!=null && pictureFile.getOriginalFilename()!=null && pictureFile.getOriginalFilename().length()>0) {
            //图片上传成功后，将图片的地址写到数据库
            originalFilename = pictureFile.getOriginalFilename();

            newFileName = UUID.randomUUID() + originalFilename.substring(originalFilename.lastIndexOf("."));

            //新文件
            File file = new File(FILEPATH + newFileName);

            //将内存中的文件写入磁盘
            pictureFile.transferTo(file);
        }

        uploadService.insertFile(originalFilename, FILEPATH+newFileName, description);

        return "index";
    }

    @RequestMapping("/uploadMultipart")
    public String uploadMultipart(@RequestParam("file")MultipartFile[] pictureFile, @RequestParam("description")String description) throws Exception {
        String FILEPATH = "C:\\Users\\ShawyerPeng\\Desktop\\pictures\\";
        String originalFilename = "";
        String newFileName = "";

        //进行图片的上传
        for (int i = 0; i<pictureFile.length; i++) {
            if (pictureFile[i]!=null && pictureFile[i].getOriginalFilename()!=null && pictureFile[i].getOriginalFilename().length()>0) {
                originalFilename = pictureFile[i].getOriginalFilename();
                newFileName = UUID.randomUUID() + originalFilename.substring(originalFilename.lastIndexOf("."));

                File file = new File(FILEPATH + newFileName);

                pictureFile[i].transferTo(file);
            }
            uploadService.insertFile(originalFilename, FILEPATH+newFileName, description);
        }

        return "index";
    }
}
