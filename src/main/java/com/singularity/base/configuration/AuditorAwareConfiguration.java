package com.singularity.base.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.Optional;

@Configuration
@EnableJpaAuditing
public class AuditorAwareConfiguration implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {
        /*
         * SecurityContext 에서 인증정보를 가져와 주입시킨다.
         * 현재 코드는 현재 Context 유저가 USER 인가 권한이 있으면, 해당 Principal name 을 대입하고, 아니면 Null 을 set 한다.
         */
//        return Optional.ofNullable(SecurityContextHolder.getContext())
//                .map(SecurityContext::getAuthentication)
//                .map(authentication -> {
//                    Collection<? extends GrantedAuthority> auth = authentication.getAuthorities();
//                    boolean isUser = auth.contains(new SimpleGrantedAuthority("USER"));
//                    if (isUser) return authentication.getName();
//                    return null;
//                });
        return Optional.of("1");
    }
}