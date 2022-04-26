//package com.student.project.amazone.config;
//
//import com.google.common.collect.Sets;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.provisioning.InMemoryUserDetailsManager;
//
//public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {
////    private final PasswordEncode passwordEncode;
////
////
////    @Autowired
////    public ApplicationSecurityConfig(PasswordEncode passwordEncode, PasswordEncode passwordEncode1) {
////        this.passwordEncode = passwordEncode1;
////    }
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http
//                .cors()
//                .and()
//                .authorizeRequests()
//                .antMatchers("/**")
//                .permitAll()
//                .antMatchers("/api/**").hasRole("user:read")
//                .anyRequest()
//                .authenticated()
//                .and()
//                .httpBasic();
//    }
//
//    @Override
//    public UserDetailsService userDetailsServiceBean() throws Exception {
//        return super.userDetailsServiceBean();
//    }
//
//    @Override
//    protected UserDetailsService userDetailsService() {
//        UserDetails trung = User.builder()
//                .username("trung")
//                .password("1234")
//                .roles("user:read")
//                .build();
//
//        return new InMemoryUserDetailsManager(
//                trung
//        );
//    }
//}
