package com.plate.hy.auth

import com.plate.hy.domain.Member
import com.plate.hy.repository.MemberRepository
import jakarta.servlet.http.HttpSession
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService
import org.springframework.security.oauth2.core.user.DefaultOAuth2User
import org.springframework.security.oauth2.core.user.OAuth2User
import java.util.*

class CustomOauth2MemberService(
    private val memberRepository: MemberRepository,
    private val httpSession: HttpSession
): OAuth2UserService<OAuth2UserRequest, OAuth2User> {
    override fun loadUser(userRequest: OAuth2UserRequest): OAuth2User {
        val delegate: OAuth2UserService<OAuth2UserRequest, OAuth2User> = DefaultOAuth2UserService()
        val oAuth2User: OAuth2User = delegate.loadUser(userRequest);

        // 로그인 진행 중인 서비스를 구분
        // 네이버로 로그인 진행 중인지, 구글로 로그인 진행 중인지, ... 등을 구분
        val registrationId: String = userRequest.clientRegistration.registrationId;

        // OAuth2 로그인 진행 시 키가 되는 필드 값(Primary Key와 같은 의미)
        // 구글의 경우 기본적으로 코드를 지원
        // 하지만 네이버, 카카오 등은 기본적으로 지원 X
        val userNameAttributeName: String = userRequest.clientRegistration
            .providerDetails
            .userInfoEndpoint
            .userNameAttributeName;

        // OAuth2UserService를 통해 가져온 OAuth2User의 attribute 등을 담을 클래스
        val attributes: OAuthAttributes = OAuthAttributes.of(registrationId, userNameAttributeName, oAuth2User.getAttributes());

        // 사용자 저장 또는 업데이트
        val member: Member = saveOrUpdate(attributes);

        // 세션에 사용자 정보 저장
//        httpSession.setAttribute("user", SessionUser(member));

        return DefaultOAuth2User(
            Collections.singleton(SimpleGrantedAuthority(member.role.value)),
        attributes.attributes,
        attributes.nameAttributeKey);
    }

    private fun saveOrUpdate(attributes: OAuthAttributes): Member {
        val member: Member = memberRepository.findByEmail(attributes.email)?.update(attributes.name) ?: attributes.toEntity()

        return memberRepository.save(member);
    }
}
