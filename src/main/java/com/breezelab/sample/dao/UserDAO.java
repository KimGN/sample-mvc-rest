package com.breezelab.sample.dao;

import com.breezelab.sample.dto.UserDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserDAO {
    List<UserDTO> getTestData();
}
