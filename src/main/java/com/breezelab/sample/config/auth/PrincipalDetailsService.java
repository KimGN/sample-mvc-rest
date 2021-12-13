package com.breezelab.sample.config.auth;

import com.breezelab.sample.mappers.UserMapper;
import com.breezelab.sample.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

//Authentication 등록
// 시큐리티 설정에서 loginProcessingUrl("/login");
// 요청이 오면 자동으로 UserDetailsService 타입으로 IoC 되어있는 loadUserByUsername 함숙가 실행됨 - 규칙
@Service
public class PrincipalDetailsService implements UserDetailsService {

    @Autowired
    public UserMapper mapper;


    @Override
    // input 의 name 과 메칭 바꾸고 싶으면 config => .usernameParameter()
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        // user 가 있는지 검색 해서 존재 하면
        // Session = Authentication = UserDetails(PrincipalDetails) 순차적으로 등록이 된다
        System.out.println("---------------------------");
        System.out.println("username : " + username);
        System.out.println("---------------------------");
        User userEntity = mapper.getUsername(username);
        if(userEntity != null){
            return new PrincipalDetails(userEntity);
        }
        return null;
    }
}
