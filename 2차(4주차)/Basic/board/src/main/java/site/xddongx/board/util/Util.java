package site.xddongx.board.util;

import site.xddongx.board.board.BoardDto;
import site.xddongx.board.post.PostDto;

import java.util.ArrayList;
import java.util.List;

public class Util {

    public static BoardDto selectBoard(int id, List<BoardDto> boardList) {
        BoardDto targetBoard = null;

        for (BoardDto board:boardList){
            if (board.getId() == id){
                targetBoard = board;
                return targetBoard;
            }
        }

        return new BoardDto();
    }

    public static PostDto selectPost(int id, List<PostDto> postList) {
        PostDto targetPost = null;

        for (PostDto post:postList){
            if (post.getId() == id) {
                targetPost = post;
                return targetPost;
            }
        }

        return new PostDto();
    }

}
