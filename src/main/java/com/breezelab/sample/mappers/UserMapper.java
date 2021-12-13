package com.breezelab.sample.mappers;

import com.breezelab.sample.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.time.LocalDateTime;
import java.util.List;

@Mapper
public interface UserMapper {

    @Select("SELECT * FROM User")
    List<User> getUserList();

    @Insert("INSERT INTO user(account, password, email, role, created_at) VALUES(#{account},#{password},#{email},#{role},#{created_at})")
    int insertUser(@Param("account") String account, @Param("password") String password, @Param("email") String email, @Param("role") String role, @Param("created_at") LocalDateTime created_at);

}
