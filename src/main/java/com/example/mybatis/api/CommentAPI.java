package com.example.mybatis.api;

import com.example.mybatis.model.Comment;
import com.example.mybatis.model.Result;
import com.example.mybatis.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class CommentAPI {
    @Autowired
    private CommentService commentService;

    @PostMapping("/api/comment/post")
    @ResponseBody
    public Result<Comment> commentPost(@RequestParam("refId") String refId,
                                       @RequestParam("parentId") long parentId,
                                       @RequestParam("content") String content,
                                       HttpServletRequest request) {

        long userId = (long)request.getSession().getAttribute("userId");

        return commentService.post(refId, userId, parentId, content);
    }

    @GetMapping("/api/comment/query")
    @ResponseBody
    public Result<List<Comment>> commentQuery(@RequestParam("refId") String refId) {
        return commentService.query(refId);
    }



}
