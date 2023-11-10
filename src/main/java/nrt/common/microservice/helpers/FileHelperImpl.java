package nrt.common.microservice.helpers;

import lombok.extern.slf4j.Slf4j;
import nrt.common.microservice.exceptions.CommonBusinessException;
import nrt.common.microservice.exceptions.ErrorCode;
import org.apache.commons.io.FileUtils;
import org.aspectj.util.FileUtil;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.io.*;
import java.util.UUID;

/**
 * Implementation of the FileHelper interface to managed files
 *
 * @author nahueltabasso
 */
@Service
@Slf4j
public class FileHelperImpl implements FileHelper {

    @Override
    public String saveImageInDirectory(MultipartFile file, String directory) {
        log.info("Enter to saveImageInDirectory()");
        log.info("Filename -> " + file.getOriginalFilename());
        String filePath = "";
        try {
            String uniqueFilename = UUID.randomUUID().toString();
            // Create a unique temporary file in a temporary directory of operative system
            File tempFile = File.createTempFile(uniqueFilename, ".tmp");
            log.info("Create a tempFile");
            log.info("TempFile -> " + tempFile.getAbsolutePath());
            // Copy the content of the received file as an argument to the method in tempFile
            file.transferTo(tempFile);

            // Verified if the received file is an image
            try (InputStream input = new FileInputStream(tempFile)) {
                if (ImageIO.read(input) == null) {
                    throw new CommonBusinessException(ErrorCode.FILE_NOT_VALID);
                }
            }

            // Save the image on the specified directory in application.yml
            uniqueFilename = uniqueFilename.concat(".jpg");
            File imageFile = new File(directory, uniqueFilename);
            filePath = directory.concat("/").concat(uniqueFilename);
            log.info("Save file in -> " + filePath);
            FileUtil.copyFile(tempFile, imageFile);
            log.info("File saved!");

            // Delete the tempFile
            tempFile.delete();
            log.info("tempFile deleted");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return filePath;
    }

    @Override
    public Boolean deleteFileByPath(String path) {
        log.info("Enter to deleteFileByPath");

        File file = new File(path);
        if (file.delete())
            return true;
        return false;
    }

    @Override
    public File convert(MultipartFile multipartFile) {
        log.info("Enter to convert()");

        try {
            byte[] bytes = multipartFile.getBytes();
            File file = new File(multipartFile.getOriginalFilename());
            FileUtils.writeByteArrayToFile(file, bytes);
            return file;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}
