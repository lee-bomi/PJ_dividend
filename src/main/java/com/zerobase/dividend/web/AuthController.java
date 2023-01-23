package com.zerobase.dividend.web;

import com.zerobase.dividend.model.Auth;
import com.zerobase.dividend.model.MemberEntity;
import com.zerobase.dividend.security.TokenProvider;
import com.zerobase.dividend.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequestMapping("/auth")
@RequiredArgsConstructor
@RestController
public class AuthController {

    private final MemberService memberService;
    private final TokenProvider tokenProvider;

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody Auth.SingUp request) {
        // 회원가입을 위한 API
        MemberEntity result = memberService.register(request);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/signin")
    public ResponseEntity<?> signin(@RequestBody Auth.SingIn request) {
        //id, pw인증
        MemberEntity member = memberService.authenticate(request);
        //토큰생성
        String token = this.tokenProvider.generateToken(member.getUsername(), member.getRoles());

        return ResponseEntity.ok(token);
    }


}
