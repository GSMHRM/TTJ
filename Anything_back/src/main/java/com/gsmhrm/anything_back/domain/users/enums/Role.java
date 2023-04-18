package com.gsmhrm.anything_back.domain.users.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
        ADMIN,
        USER;

        @JsonCreator
        public static Role from(String s) {
                return Role.valueOf(s.toUpperCase());
        }

        @Override
        public String getAuthority() {
                return name();
        }
}
