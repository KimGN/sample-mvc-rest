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

    @Select("SELECT * FROM user WHERE username=#{username}")
    User getUsername(@Param("username") String username);

    @Select("SELECT * FROM user")
    List<User> getUserList();

    @Insert("INSERT INTO user(username, password, email, role, created_at) VALUES(#{username},#{password},#{email},#{role},#{created_at})")
    int insertUser(@Param("username") String username, @Param("password") String password, @Param("email") String email, @Param("role") String role, @Param("created_at") LocalDateTime created_at);

    @Insert("INSERT INTO user(username, password, email, role, created_at, provider, provider_id) VALUES(#{username},#{password},#{email},#{role},#{created_at}, #{provider}, #{provider_id})")
    int insertOAuthUser(@Param("username") String username, @Param("password") String password, @Param("email") String email, @Param("role") String role, @Param("created_at") LocalDateTime created_at, @Param("provider") String provider, @Param("provider_id") String provider_id);

}
