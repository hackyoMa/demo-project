package com.github.demoproject.util;

import org.jspecify.annotations.Nullable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * CurrentUser
 *
 * @author hackyo
 * @since 2022/4/1
 */
public final class CurrentUser {

    public static @Nullable String getId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            return authentication.getName();
        }
        return null;
    }

}
