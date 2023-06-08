package kr.ac.daelim.ch16.service;

import kr.ac.daelim.ch16.dto.SignInResultDto;
import kr.ac.daelim.ch16.dto.SignUpResultDto;

public interface SignService {
    SignUpResultDto signUp(String id, String password, String name, String email, String role);
    SignInResultDto signIn(String id, String password) throws RuntimeException;
}
