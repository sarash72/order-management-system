package org.example.product.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class File {

    private String fileName;
    private String filePath;
    private MultipartFile file;   // فیلد فایل

}
