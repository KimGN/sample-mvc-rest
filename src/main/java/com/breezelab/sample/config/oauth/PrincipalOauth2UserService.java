package com.breezelab.sample.config.oauth;

import com.breezelab.sample.config.auth.PrincipalDetails;
import com.breezelab.sample.mappers.UserMapper;
import com.breezelab.sample.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class PrincipalOauth2UserService extends DefaultOAuth2UserService {
    // 로그인 후처리
    // 구글로 부터 받은 userRequest 데이터 대한 후처리 함수

    @Autowired
    UserMapper userMapper;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException{

        OAuth2User oAuth2User = super.loadUser(userRequest);
        System.out.println("getAttributes : " + oAuth2User.getAttributes());
        // 강제로 회원가입을 진행
        String provider = userRequest.getClientRegistration().getRegistrationId(); // google
        String providerId = (String) oAuth2User.getAttributes().get("sub");
        String username = provider+"_"+providerId; // google_113093857194453228426;
        String email = (String) oAuth2User.getAttributes().get("email");
        String role = "ROLE_USER";
        LocalDateTime createAt = LocalDateTime.now();

        User userEntity = userMapper.getUsername(username);






        if(userEntity == null){

            // naver
            if("naver".equals(provider)){

            }else{
                // google
                userMapper.insertOAuthUser(username, "null", email, role, createAt, provider, providerId);
            }
        }


        System.out.println("------------------------------------");
        System.out.println("username : " +  username);
        System.out.println( "OAuth : " + userMapper.getUsername(username));
        System.out.println("User : " + userMapper.getUsername("admin"));

        return new PrincipalDetails(userEntity, oAuth2User.getAttributes());
    }
}
