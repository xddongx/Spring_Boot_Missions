package site.xddongx.basicboard.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import site.xddongx.basicboard.model.MediadescriptorDto;

import javax.print.attribute.standard.Media;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;

@Service
public class LocalMediaService implements MediaService {
    private static final Logger logger = LoggerFactory.getLogger(LocalMediaService.class);
    private final String basePath = "./media";

    @Override
    public MediadescriptorDto saveFile(MultipartFile file) {

        return this.saveToDir(file);
    }

    @Override
    public Collection<MediadescriptorDto> saveFileBulk(MultipartFile[] files) {
        Collection<MediadescriptorDto> resultList = new ArrayList<>();
        for (MultipartFile file: files) {
            resultList.add(this.saveToDir(file));
        }
        return resultList;
    }

    @Override
    public byte[] getFileAsBytes(String resourcePath) {
        return new byte[0];
    }

    private MediadescriptorDto saveToDir(MultipartFile file) {
        MediadescriptorDto dto = new MediadescriptorDto();
        dto.setStatus(200);
        dto.setOriginalName(file.getOriginalFilename());

        try {
            LocalDateTime now = LocalDateTime.now();
            String targetDir = Path.of(basePath, now.format(DateTimeFormatter.BASIC_ISO_DATE)).toString();
            String newFileName = now.format(DateTimeFormatter.ofPattern("HHmmss")) + "_" + file.getOriginalFilename();
            File dirNow = new File(targetDir);

            if (!dirNow.exists()) dirNow.mkdir();
            file.transferTo(Path.of(targetDir, newFileName));


        } catch (IOException e) {
            logger.error(e.getMessage());
        }
        return null;
    }
}
