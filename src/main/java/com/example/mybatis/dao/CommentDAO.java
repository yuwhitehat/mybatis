package com.example.mybatis.dao;

import com.example.mybatis.dataobject.CommentDO;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CommentDAO {

    /**
     * xml中配置
     * @param commentDO
     * @return
     */
    int insert(CommentDO commentDO);

    /**
     * 修改评论
     * @param commentDO
     * @return
     */
    int update(CommentDO commentDO);

    /**
     * 删除评论
     * @param id
     * @return
     */
    int delete(@Param("id") long id);

    /**
     * 根据ref_id 查询
     * @param refId
     * @return
     */
    List<CommentDO> findByRefId(@Param("refId") String refId);

    /**
     * 查询所有的评论
     * @return
     */
    List<CommentDO> findAll();

    /**
     * 批量插入
     * @param commentDOS
     * @return
     */
    int batchAdd(@Param("list") List<CommentDO> commentDOS);

    /**
     * 通过user_id查询多条comment
     * @param ids
     * @return
     */
    List<CommentDO> findByUserIds(@Param("userIds") List<Long> ids);



}
