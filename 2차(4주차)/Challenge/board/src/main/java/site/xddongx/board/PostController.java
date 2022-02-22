package site.xddongx.board;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import site.xddongx.board.post.PostDto;
import site.xddongx.board.post.PostService;

@Controller
public class PostController {
    private final PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }
    @GetMapping("/")
    public String getPost(Model model ) {
        PostDto postDto = postService.readPostOne(0);

        model.addAttribute("post", postDto);
        return "index";
    }
}
