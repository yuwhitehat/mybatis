package com.example.mybatis.dao;

import com.example.mybatis.dataobject.CommentDO;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CommentDAO {

    @Insert("insert into comment (ref_id, user_id, content, parent_id, gmt_created, gmt_modified) values (#{refId}, #{userId}, #{content}, #{parentId}, now(),now())")
    @Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "id")
    int insert(CommentDO commentDO);


    @Select("select id,ref_id as refId, user_id as userId, content, parent_id as parentId,gmt_created as gmtCreated, gmt_modified as gmtModified from comment")
    List<CommentDO> findAll();

    @Update("update comment set content=#{content}, gmt_modified=now() where id=#{id}")
    int update(CommentDO commentDO);

    @Delete("delete from comment where id=#{id}")
    int delete(@Param("id") long id);

    @Select("select * from comment where ref_id=#{refId}")
    List<CommentDO> findByRefId(@Param("refId") String refId);

}
