package site.xddongx.basicboard.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import site.xddongx.basicboard.model.MediadescriptorDto;

import javax.print.attribute.standard.Media;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
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
        try {
            return Files.readAllBytes(Path.of(basePath, resourcePath));
        } catch (IOException e) {
            logger.error(e.getMessage());
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    private MediadescriptorDto saveToDir(MultipartFile file) {
        MediadescriptorDto descriptorDto = new MediadescriptorDto();
        descriptorDto.setStatus(200);
        descriptorDto.setOriginalName(file.getOriginalFilename());

        try {
            LocalDateTime now = LocalDateTime.now();                // 중복을 방지하기 위해서
            String targetDir = Path.of(basePath, now.format(DateTimeFormatter.BASIC_ISO_DATE)).toString();
            String newFileName = now.format(DateTimeFormatter.ofPattern("HHmmss")) + "_" + file.getOriginalFilename();
            File dirNow = new File(targetDir);

            if (!dirNow.exists()) dirNow.mkdir();
            file.transferTo(Path.of(targetDir, newFileName));

            descriptorDto.setResourcePath(Path.of(targetDir, newFileName).toString().substring(1));

            return descriptorDto;

        } catch (IOException e) {
            logger.error(e.getMessage());
            descriptorDto.setMessage("Failed");
            descriptorDto.setStatus(500);
            return descriptorDto;
        }
    }
}
