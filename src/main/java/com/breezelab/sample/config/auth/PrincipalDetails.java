package com.breezelab.sample.config.auth;

// 시큐리티가 /login 주소 요청이 오면 낚아 채서 로그인을 진행시킨다
// 로그인 진행이 완료 되면 Security Session 만들어 준다 (Security ContextHolder) key 값에 Session 정보를 저장시킨다
// Object type => Authentication type Object
// Authentication 안에 User 정보가 있아야 한다
// User Object type => UserDetails type Object

// Security Session => Authentication => UserDetails(PrincipalDetails)

//UserDetails(PrincipalDetails) 객채 생성
//Authentication 객체를 만들어서 등록 => PrincipalDetailsService
import com.breezelab.sample.model.User;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

@Data
public class PrincipalDetails implements UserDetails, OAuth2User {

    private User user; // Composition - 구성

    private Map<String, Object> attributes;

    // 일반 로그인
    public PrincipalDetails(User user){
        this.user = user;
    }
    // OAuth 로그인
    public PrincipalDetails(User user, Map<String, Object> attributes){
        this.user = user;
        this.attributes = attributes;
    }

    // 해당 user 권한을 리턴한다 - type 정해져있음
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities(){
        Collection<GrantedAuthority> collect = new ArrayList<>();
        collect.add(new GrantedAuthority() {
            @Override
            public String getAuthority() {
                return user.getRole();
            }
        });
        return collect;
    }



    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    // 계정 만료
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    // 계정 잠금
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    // 비밀번호 사용기간
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    // 계정 활성화
    @Override
    public boolean isEnabled() {
        // 사이트에서 1년동안 접속이 없으면 휴면계정
        // 현재시간 - 로긴시간 => 초과하면 휴먼 계정
        return true;
    }


    // ------ OAuth2 -----
    @Override
    public Map<String, Object> getAttributes() {
        return attributes;
    }

    @Override
    public String getName() {
        return null;
    }


}
