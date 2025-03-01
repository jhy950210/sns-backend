package com.plate.hy.auth

import com.plate.hy.domain.Member
import lombok.Getter

@Getter
class OAuthAttributes(
    val attributes: Map<String, Any>,
    val nameAttributeKey: String,
    val name: String,
    val email: String) {

    companion object {
        fun of(registrationId: String,
               userNameAttributeName: String,
               attributes: Map<String, Any>): OAuthAttributes {
            return ofGoogle(userNameAttributeName, attributes)
        }

        private fun ofGoogle(usernameAttributeName: String,
                             attributes: Map<String, Any>): OAuthAttributes {
            return OAuthAttributes(
                name = attributes["name"] as String,
                email = attributes["email"] as String,
                attributes = attributes,
                nameAttributeKey = usernameAttributeName
            )
        }
    }

    fun toEntity(): Member {
        return Member.createGoogleUser(name,  email)
    }
}
