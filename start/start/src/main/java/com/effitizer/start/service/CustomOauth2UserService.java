package com.effitizer.start.service;

import com.effitizer.start.config.auth.JwtTokenUtil;
import com.effitizer.start.config.auth.JwtUserDetails;
import com.effitizer.start.config.auth.user.OAuth2UserInfo;
import com.effitizer.start.config.auth.user.OAuth2UserInfoFactory;
import com.effitizer.start.exception.OAuthProviderMissMatchException;
import com.effitizer.start.domain.ProviderType;
import com.effitizer.start.domain.Role;
import com.effitizer.start.domain.User;
import com.effitizer.start.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
public class CustomOauth2UserService extends DefaultOAuth2UserService {
    @Autowired UserRepository userRepository;
    @Autowired JwtTokenUtil jwtTokenUtil;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        System.out.println("attributes" + super.loadUser(userRequest).getAttributes());
        OAuth2User user = super.loadUser(userRequest);
        //여기서 attriutes를 찍어보면 모두 각기 다른 이름으로 데이터가 들어오는 것을 확인 가능
        try{
            return process(userRequest, user);
        } catch (AuthenticationException ex){
            throw new OAuth2AuthenticationException(ex.getMessage());
        } catch (Exception ex){
            throw new InternalAuthenticationServiceException(ex.getMessage(), ex.getCause());
        }
    }

    //인증을 요청하는 사용자에 따라서 없는 회원이면 회원가입, 이미 존재하는 회원이면 업데이트를 실행
    private OAuth2User process(OAuth2UserRequest userRequest, OAuth2User user) {
        ProviderType providerType = ProviderType.valueOf(userRequest.getClientRegistration().getRegistrationId().toUpperCase());
        //provider타입에 따라서 각각 다르게 userInfo 가져옴
        OAuth2UserInfo userInfo = OAuth2UserInfoFactory.getOAuth2UserInfo(providerType, user.getAttributes());

        User savedUser = userRepository.findByEmail(userInfo.getEmail()).get();

        if (savedUser != null) {
            if (providerType != savedUser.getProviderType()) {
                throw new OAuthProviderMissMatchException(
                        "Looks like you're signed up with " + providerType +
                                " account. Please use your " + savedUser.getProviderType() + " account to login."
                );
            }
        } else {
            savedUser = createUser(userInfo, providerType);
        }

        System.out.println(jwtTokenUtil.generateToken(userInfo.getEmail()));

        return new JwtUserDetails(savedUser, user.getAttributes());
    }

    //넘어온 사용자 정보를 통해서 회원가입을 실행한다.
    private User createUser(OAuth2UserInfo userInfo, ProviderType providerType) {
        User user = new User();
        user.setName(userInfo.getName());
        user.setRole(Role.USER);
        user.setPassword("");
        user.setEmail(userInfo.getEmail());
        user.setProviderType(providerType);
        user.setIs_subscribed(Boolean.FALSE);

        return userRepository.save(user);
    }
}
