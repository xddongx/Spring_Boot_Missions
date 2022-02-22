package site.xddongx.board;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Controller
public class FileController {

    @PostMapping("/upload")
    // 업로드하는 파일들을 MultipartFile 형태의 파라미터로 전달된다.
    public String upload(@RequestParam MultipartFile[] uploadfile, Model model) throws IllegalStateException, IOException {
        String projectPath = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\image";
        List<FileDto> list = new ArrayList<>();

        for (MultipartFile file: uploadfile) {
            if (!file.isEmpty()){
                // UUID를 이용해 unique한 파일 이름을 만드러준다.
                FileDto dto = new FileDto(UUID.randomUUID().toString(), file.getOriginalFilename(), file.getContentType());
                list.add(dto);

                File newFilewName = new File(projectPath, dto.getUuid() + "_" + dto.getFileName());
                // 전달된 내용을 실제 물리적인 파일로 저장해준다.
                file.transferTo(newFilewName);
            }
        }
        model.addAttribute("files", list);
        return "result";
    }
//    https://goodteacher.tistory.com/351

}
