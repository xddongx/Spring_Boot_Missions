package site.xddongx.basicboard.Controller;

import org.apache.coyote.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import site.xddongx.basicboard.Service.MediaService;
import site.xddongx.basicboard.model.MediadescriptorDto;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Collection;

@RestController
@RequestMapping("/media")
public class MediaController {
    private static final Logger logger = LoggerFactory.getLogger(MediaController.class);
    private final MediaService mediaService;

    public MediaController(MediaService mediaService) {
        this.mediaService = mediaService;
    }

    @PostMapping("/upload")
    public ResponseEntity<MediadescriptorDto> uploadMedia(@RequestParam("file") MultipartFile file) {
        MediadescriptorDto descriptorDto = this.mediaService.saveFile(file);
        return ResponseEntity.status(descriptorDto.getStatus()).body(descriptorDto);
    }

    @PostMapping("/upload-bulk")
    public ResponseEntity<Collection<MediadescriptorDto>> uploadMediaBulk(@RequestParam("file") MultipartFile[] files) {
        return ResponseEntity.status(HttpStatus.MULTI_STATUS).body(this.mediaService.saveFileBulk(files));
    }

    @GetMapping("**")
    public ResponseEntity<byte[]> staticLikeEndpoint(HttpServletRequest request) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_PNG);
        return new ResponseEntity<>(this.mediaService.getFileAsBytes(request.getRequestURI().split("media")[1]), headers, HttpStatus.OK);
    }
}
