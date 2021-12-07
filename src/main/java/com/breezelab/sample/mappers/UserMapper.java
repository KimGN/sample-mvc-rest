package com.breezelab.sample.mappers;

import com.breezelab.sample.model.UserModel;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserMapper {

    @Select("SELECT * FROM User WHERE id=#{id}")
    UserModel getUserModel(@Param("id") String id);

    @Select("SELECT * FROM User")
    List<UserModel> getUserModelList() throws Exception;

    @Insert("INSERT INTO User VALUE(#{account},#{password})")
    int insertUserModel(@Param("account") String account , @Param("password") String password);

}
