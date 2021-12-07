package com.breezelab.sample.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserModel{
    private Long id;
    private String account;
    private String password;
    // 계정 만료
    private boolean isAccountNonExpired;
    // 계정 정지
    private boolean isAccountNonLocked;
    // 증명
    private boolean isCredentialsNonExpired;
    // 활성화
    private boolean isEnabled;

}
