package com.example.mybatis.control;

import com.example.mybatis.dao.CommentDAO;
import com.example.mybatis.dataobject.CommentDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class CommentController {
    @Autowired
    private CommentDAO commentDAO;

    @GetMapping("/comments")
    @ResponseBody
    public List<CommentDO> getAll() {
        return commentDAO.findAll();
    }
    @PostMapping("/comment")
    @ResponseBody
    public CommentDO save(@RequestBody  CommentDO commentDO){
        commentDAO.insert(commentDO);
        return commentDO;
    }
    @PostMapping("comment/update")
    @ResponseBody
    public CommentDO update(@RequestBody CommentDO commentDO) {
        commentDAO.update(commentDO);
        return commentDO;
    }

    @GetMapping("comment/del")
    @ResponseBody
    public boolean delete(@RequestParam("id") Long id){
        return commentDAO.delete(id) > 0;
    }
    @GetMapping("/comment/findByRefId")
    @ResponseBody
    public List<CommentDO> findByRefId(@RequestParam("refId") String refId){
        return commentDAO.findByRefId(refId);
    }
}
