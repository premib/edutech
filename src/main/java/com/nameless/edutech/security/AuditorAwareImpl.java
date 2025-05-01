package com.nameless.edutech.security;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AuditorAwareImpl implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {
        String currentUser = SecurityContextHolder.getContext().getAuthentication().getName();
        return Optional.ofNullable(currentUser);
    }
}
