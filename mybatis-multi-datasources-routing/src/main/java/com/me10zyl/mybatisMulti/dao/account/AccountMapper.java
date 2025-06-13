package com.me10zyl.mybatisMulti.dao.account;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;


@Mapper
public interface AccountMapper {

    @Update("update account set balance = balance - #{money} where uid = #{uid} and balance >= #{money}")
    int dedectBalance(@Param("uid") Integer uid, @Param("money") Integer money);
}
