package com.bit.book.utils;

import com.bit.book.exception.BasicException;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * @author fanhongtao
 * 2022/10/13 15:36
 */
public class UploadUtils {

    private static final String FILE_TYPE_IMG = "img";

    private static final String CLASS_PATH_PREFIX;

    static {
        String CLASS_PATH_PREFIX1;
        try {
            Resource resource = new ClassPathResource("");
            CLASS_PATH_PREFIX1 = resource.getFile().getAbsolutePath() + "/";
        } catch (IOException e) {
            CLASS_PATH_PREFIX1 = "./";
        }
        CLASS_PATH_PREFIX = CLASS_PATH_PREFIX1;
    }


    /**
     * 图片上传，返回 classpath下的路径 如： /static/img/db1/organ/20211003_xxxx.jpg
     */
    public static String uploadPicture(MultipartFile file, String path) {
        return upload(file, FILE_TYPE_IMG, path);
    }

    /**
     * 文件上传
     */
    private static String upload(MultipartFile file, String fileType, String path) {
        String fileName = file.getOriginalFilename();
        String pathPrefix, filePath;
        if (fileType.equals(FILE_TYPE_IMG)) {
            pathPrefix = "static/img";
        } else {
            pathPrefix = "static/other";
        }
        pathPrefix += path;
        try {
            File directory = new File(CLASS_PATH_PREFIX + pathPrefix);
            if (!directory.exists()) {
                directory.mkdirs();
            }
            pathPrefix += CommonUtils.dateToStr() + "_";
            filePath = pathPrefix + fileName;
            File dest = new File(CLASS_PATH_PREFIX + filePath);
            if (dest.exists()) {
                filePath = pathPrefix + CommonUtils.timeToStr() + "_" + fileName;
                dest = new File(CLASS_PATH_PREFIX + filePath);
            }
            file.transferTo(dest.getAbsoluteFile());
        } catch (IOException e) {
            e.printStackTrace();
            throw new BasicException(-1,e.getMessage());
            //throw new BasicException(HttpStatus.INTERNAL_SERVER_ERROR.value(), "文件上传失败！");
        }
        return "/" + filePath;
    }

    /**
     * 文件删除，
     */
    public static void removeFile(String path) {
        try {
            String absPath = CLASS_PATH_PREFIX + path;
            File dest = new File(absPath);
            dest.delete();
        } catch (Exception e) {
            throw new BasicException(HttpStatus.INTERNAL_SERVER_ERROR.value(), "文件删除失败！");
        }
    }
}
