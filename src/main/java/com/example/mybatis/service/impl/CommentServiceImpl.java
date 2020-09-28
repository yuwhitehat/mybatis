package com.example.mybatis.service.impl;

import com.example.mybatis.dao.CommentDAO;
import com.example.mybatis.dataobject.CommentDO;
import com.example.mybatis.model.Comment;
import com.example.mybatis.model.Result;
import com.example.mybatis.service.CommentService;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.text.StringEscapeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentDAO commentDAO;

    @Override
    public Result<Comment> post(String refId, long userId, long parentId, String content) {
        Result<Comment> commentResult = new Result<>();
        if (StringUtils.isEmpty(refId) || userId == 0 || StringUtils.isEmpty(content)) {
            commentResult.setCode("500");
            commentResult.setMessage("refId、userId、content不能为空");
            return commentResult;
        }
        //对content进行安全转义
        String body = StringEscapeUtils.escapeHtml4(content);
        CommentDO commentDO = new CommentDO();
        commentDO.setRefId(refId);
        commentDO.setUserId(userId);
        commentDO.setParentId(parentId);
        commentDO.setContent(body);
        commentDAO.insert(commentDO);
        Comment comment = commentDO.toModel();
        commentResult.setSuccess(true);
        commentResult.setData(comment);
        return commentResult;
    }

    @Override
    public Result<List<Comment>> query(String refId) {
        Result<List<Comment>> result = new Result<>();
        //查询所有的评论记录包含回复的
        List<Comment> comments = commentDAO.findByRefId(refId);
        //构建map结构
        Map<Long, Comment> commentMap = new HashMap<>();
        //初始化一个虚拟根节点，0对应所有一级评论的父亲
        commentMap.put(0L, new Comment());
        //把所有的评论都转换为map数据
        comments.forEach(comment -> commentMap.put(comment.getId(), comment));
        //再次遍历评论数据
        comments.forEach(comment -> {
            //得到父评论
            Comment parent = commentMap.get(comment.getParentId());
            if (parent != null) {
                //初始化children变量
                if (parent.getChildren() == null) {
                    parent.setChildren(new ArrayList<>());
                }
                parent.getChildren().add(comment);
            }
        });
        //得到所有的一级评论
        List<Comment> data = commentMap.get(0L).getChildren();
        result.setData(data);
        result.setSuccess(true);
        return result;
    }
}
