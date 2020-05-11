package haui.stores.utils;

import lombok.extern.log4j.Log4j2;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Instant;

@Log4j2
public class FileUtils {
    public static boolean checkNullOrEmptyImage(MultipartFile file) {
        return file == null || file.isEmpty() || file.getSize() == 0;
    }

    /**
     * Save image file in to the directory path
     *
     * @param theDir the directory root for store image upload
     * @param file   the multi part file image
     * @return the string path of file image saved in storage
     */
    public static String saveImage(String theDir, MultipartFile file) {
        Path root = Path.of(theDir);
        Long timeNow = Instant.now().getEpochSecond();
        try {
            String newFileName = timeNow + "-" + file.getOriginalFilename();
            Files.copy(file.getInputStream(),
                    root.resolve(newFileName));
            return "/" + theDir + "/" + newFileName;
        } catch (Exception e) {
            log.error("Fail to save image!!");
            throw new RuntimeException("FAIL!");
        }
    }

    /**
     * The method create directory if directory is not exists
     *
     * @param directory the path of directory
     */
    public static void createDirectoryIfNotExist(String directory) {
        try {
            File theDir = new File(directory);
            if (theDir.exists()) {
                log.info("The {} directory had existed", theDir);
                return;
            }
            theDir.mkdirs();
            log.info("Created {} directory", theDir);
        } catch (Exception e) {
            log.info("Exception: {}", e.getMessage());
        }

    }

}
