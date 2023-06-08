package kr.ac.daelim.ch16.config.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import kr.ac.daelim.ch16.dto.EntryPointErrorResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        System.out.println("[commence] 인증 실패로 에러 발생");
        ObjectMapper objectMapper = new ObjectMapper();
        EntryPointErrorResponse entryPointErrorResponse = new EntryPointErrorResponse();
        entryPointErrorResponse.setMsg("인증이 실패하였습니다.");

        response.setStatus(401);
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");
        //entryPointErrorResponse를 ObjectMapper를 이용하여 String 형태로 변경하여 보냄
        response.getWriter().write(objectMapper.writeValueAsString(entryPointErrorResponse));
    }
}
