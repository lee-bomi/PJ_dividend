package com.zerobase.dividend.service;

import com.zerobase.dividend.exception.impl.AlreadyExistUserException;
import com.zerobase.dividend.model.Auth;
import com.zerobase.dividend.model.MemberEntity;
import com.zerobase.dividend.persist.MemberRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class MemberService implements UserDetailsService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return this.memberRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("couldn't find user -> " + username)); //memberEntity가 userDetail을 상속받았으므로, entity형태로 반환
    }

    public MemberEntity register(Auth.SingUp member) {

        boolean exists = memberRepository.existsByUsername(member.getUsername());
        if (exists) {
            throw new AlreadyExistUserException();
        }

        member.setPassword(this.passwordEncoder.encode(member.getPassword()));
        var result = memberRepository.save(member.toEntity());
        return result;
    }

    public MemberEntity authenticate(Auth.SingIn member) {
        MemberEntity memberEntity = memberRepository.findByUsername(member.getUsername())
                .orElseThrow(() -> new RuntimeException("존재하지않는 ID입니다"));

        if (!this.passwordEncoder.matches(member.getPassword(), memberEntity.getPassword())) {
            throw new RuntimeException("비밀번호가 일치하지않습니다");
        }
        return memberEntity;
    }
}
