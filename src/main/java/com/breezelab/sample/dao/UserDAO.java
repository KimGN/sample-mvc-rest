package com.breezelab.sample.dao;

import com.breezelab.sample.dto.UserDTO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDAO {
    List<UserDTO> getTestData();
}
