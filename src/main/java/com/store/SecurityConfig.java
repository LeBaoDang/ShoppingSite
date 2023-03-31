package com.store;

import com.store.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    UserService userService;

    // cung cap nguon du lieu dang nhap
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService);
    }

    // phan quyen su dung
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.authorizeRequests()
                .antMatchers("/order/**").authenticated()
                .antMatchers("/admin/**").hasAnyRole("STAF", "DIRE")
                .antMatchers("/rest/authorities").hasRole("DIRE")
                .anyRequest().permitAll(); // tất cả trang còn lại bắt đăng nhập

        http.formLogin()
                .loginPage("/security/login/form") /* địa chỉ form đăng nhập */
                .loginProcessingUrl("/security/login") /* địa chỉ khi nhấn đăng nhập */
                .defaultSuccessUrl("/security/login/success", false) /*
                 * địa chỉ xử lý đăng nhập khi thành công -
                 * False: Đăng nhập thành công không nhất thiết
                 * quay về trang success
                 */
                .failureUrl("/security/login/error"); /* địa chỉ xử lý đăng nhập khi lỗi */

        // cấu hình remember me
        http.rememberMe().tokenValiditySeconds(
                86400); /* xác định thời gian tồn tại cookies (thời gian tự động login có hiệu lực) */

        // truy cập không đúng vai trò
        http.exceptionHandling()
                .accessDeniedPage("/security/unauthoried"); /*
         * địa chỉ khi đăng nhập đường dẫn chưa cấp quyền - VD:
         * CUSTOMER đăng nhập đường dẫn của DIRE
         */

        // đăng xuất
        http.logout().logoutUrl("/security/logoff") /* địa chỉ đường dẫn khi đăng xuất */
                .logoutSuccessUrl("/security/logoff/success"); /* địa chỉ xử lý dăng xuất khi thành công */

        // Đăng nhập từ mạng xã hội
        http.oauth2Login()
                .loginPage("/security/login/form")
                .defaultSuccessUrl("/security/login/success", true)
                .failureUrl("/security/login/error")
                .authorizationEndpoint()
                .baseUri("/oauth2/authorization");
    }


    // cơ chế mã hóa mật khẩu
    @Bean
    public BCryptPasswordEncoder getpaPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }


    /* Cho phép truy xuất REST API từ bên ngoài (domain khác ) */
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers(HttpMethod.OPTIONS, "/**");
    }
}
