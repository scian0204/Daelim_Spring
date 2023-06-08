package kr.ac.daelim.ch16.controller;

import kr.ac.daelim.ch16.dto.SignInResultDto;
import kr.ac.daelim.ch16.dto.SignUpResultDto;
import kr.ac.daelim.ch16.service.SignService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sign-api")
@RequiredArgsConstructor
public class SignController {
    private final SignService signServce;

    @PostMapping("/sign-in")
    public SignInResultDto signIn(@RequestParam String id, @RequestParam String password) {
        SignInResultDto signInResultDto = signServce.signIn(id, password);
        if (signInResultDto.getCode() == 0) {
            System.out.println("[SignIn] 정상적으로 로그인되었습니다.");
            System.out.println("token: " + signInResultDto.getToken());
        }

        return signInResultDto;
    }

    @PostMapping("/sign-up")
    public SignUpResultDto signUp(
            @RequestParam String id,
            @RequestParam String password,
            @RequestParam String name,
            @RequestParam String email,
            @RequestParam String role
    ) {
        return signServce.signUp(id, password, name, email, role);
    }

    @GetMapping("/exception")
    public void exception() throws RuntimeException {
        throw new RuntimeException("접근이 금지되었습니다.");
    }
}
