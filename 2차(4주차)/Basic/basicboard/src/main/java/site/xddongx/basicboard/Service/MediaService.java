package site.xddongx.basicboard.Service;

import org.springframework.web.multipart.MultipartFile;
import site.xddongx.basicboard.model.MediadescriptorDto;

import java.util.Collection;

public interface MediaService {
    MediadescriptorDto saveFile(MultipartFile file);
    Collection<MediadescriptorDto> saveFileBulk(MultipartFile[] files);
    byte[] getFileAsBytes(String resourcePath);
}
