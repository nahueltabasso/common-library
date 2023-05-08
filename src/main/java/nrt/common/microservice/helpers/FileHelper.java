package nrt.common.microservice.helpers;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;

/**
 * File Helper Layer
 *
 * This interface provides the commons signs to managed different files
 *
 * @author nahueltabasso
 */
public interface FileHelper {

    public String saveImageInDirectory(MultipartFile file, String directory);
    public Boolean deleteFileByPath(String path);

    public File convert(MultipartFile multipartFile);
}
