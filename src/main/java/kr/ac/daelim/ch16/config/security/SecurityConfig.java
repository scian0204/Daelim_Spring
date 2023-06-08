package kr.ac.daelim.ch16.config.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableGlobalMethodSecurity(
        prePostEnabled = true
)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final JwtTokenProvider jwtTokenProvider;

    public SecurityConfig(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .httpBasic().disable() // 제공하는 로그인 폼 UI 사용 안함
                .csrf().disable() // rest api는 csrf 보안 필요없으므로 비활성화
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) // 세션 사용 안하므로 비활성화
                .and()
                // 해당 패턴의 경로로 들어오면 누구나 허용
                .authorizeRequests().antMatchers("/sign-api/sign-in", "/sign-api/sign-up", "/sign-api/exception").permitAll()
                .antMatchers(HttpMethod.GET, "/user/**").permitAll() // 해당 패턴의 경로로 들어오는 Get 요청 누구나 허용
                .anyRequest().hasAnyRole("USER", "ADMIN") // 나머지 요청 해당 권한만 허용
                .and()
                // 오류 핸들링
                .exceptionHandling().accessDeniedHandler(new CustomAccessDeniedHandler()) // 해당 오류 발생 시 해당 핸들러로 처리
                .and()
                .exceptionHandling().authenticationEntryPoint(new CustomAuthenticationEntryPoint()) // 해당 오류 발생 시 해당 핸들러로 처리
                .and()
                // 1파라미터 필터를 2파라미터 필터 전에 불러옴
                .addFilterBefore(new JwtAuthenicationFilter(jwtTokenProvider), UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    public void configure(WebSecurity webSecurity) throws Exception {
        webSecurity.ignoring()
                .antMatchers(
                        "/v3/api-docs/**", 
                        "/swagger-ui/**", 
                        "/swagger-ui/index.html", 
                        "/swagger/**"
                ); // swagger 허용
    }
}
