package site.xddongx.basicboard.Controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

@RestController
@RequestMapping("/media")
public class MediaController {
    private static final Logger logger = LoggerFactory.getLogger(MediaController.class);

    @PostMapping("/test")
    public void uploadMedia(@RequestParam("file")MultipartFile file) {
        String basePath = "./media";
        File directory = new File(basePath);
        if (!directory.exists()) directory.mkdir();

        Path newFilePath = Path.of(basePath, file.getOriginalFilename());
        try {
            file.transferTo(newFilePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
