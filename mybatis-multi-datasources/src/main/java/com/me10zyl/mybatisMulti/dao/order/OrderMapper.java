package com.me10zyl.mybatisMulti.dao.order;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
@Mapper
public interface OrderMapper {
    @Insert("INSERT INTO `order`(uid) VALUES (#{uid})")
    void insert(@Param("uid") Integer uid);
}
