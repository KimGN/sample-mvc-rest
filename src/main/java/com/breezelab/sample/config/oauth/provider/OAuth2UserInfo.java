package com.breezelab.sample.config.oauth.provider;

public interface OAuth2UserInfo {

    String getProvider();

    String getProviderId();

    String getName();

    String getEmail();

}
