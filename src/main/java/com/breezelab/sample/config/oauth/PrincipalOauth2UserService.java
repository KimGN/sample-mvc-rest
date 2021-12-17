package com.breezelab.sample.config.oauth;

import com.breezelab.sample.config.auth.PrincipalDetails;
import com.breezelab.sample.config.oauth.provider.GoogleUserInfo;
import com.breezelab.sample.config.oauth.provider.NaverUserInfo;
import com.breezelab.sample.config.oauth.provider.OAuth2UserInfo;
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



        OAuth2UserInfo oAuth2UserInfo = null;

        if("naver".equals(provider)){
            oAuth2UserInfo = new NaverUserInfo(oAuth2User.getAttributes());
            System.out.println("getAttributes-naver : " + oAuth2User.getAttributes().get("response"));
        }else{
            // google
            oAuth2UserInfo = new GoogleUserInfo(oAuth2User.getAttributes());

        }

        String providerId = oAuth2UserInfo.getProviderId();
        String username = provider+"_"+providerId; // google_113093857194453228426;
        String email = oAuth2UserInfo.getEmail();
        String role = "ROLE_USER";
        LocalDateTime createAt = LocalDateTime.now();

        System.out.println("------------------------------------");
        System.out.println("username : " +  username);
        System.out.println("email : " +  email);
        System.out.println( "OAuth : " + userMapper.getUsername(username));


        User userEntity = userMapper.getUsername(username);

        if(userEntity == null){
            userMapper.insertOAuthUser(username, "null", email, role, createAt, provider, providerId);
        }

        return new PrincipalDetails(userEntity, oAuth2User.getAttributes());
    }
}
