package com.lfxwkj.sur.config.security;

import com.lfxwkj.sur.auth.aop.PermissionAop;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 资源过滤的aop
 *
 * @author fengshuonan
 * @Date 2019/7/20 17:55
 */
@Configuration
public class PermissionAopConfig {

    /**
     * 资源过滤的aop
     *
     * @author fengshuonan
     * @Date 2019/7/21 10:12
     */
    @Bean
    public PermissionAop permissionAop() {
        return new PermissionAop();
    }

}
