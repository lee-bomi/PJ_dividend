package com.zerobase.dividend.security;


import com.zerobase.dividend.service.MemberService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.jsoup.helper.StringUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;

@Component
@RequiredArgsConstructor
public class TokenProvider {

    private static final long TOKEN_EXPIRE_TIME = 1000 * 60 * 60;
    private static final String KEY_ROLES = "roles";
    private final MemberService memberService;

    @Value("${spring.jwt.secret}")
    private String secretKey;

    /**
     * 토큰생성
     * @return
     */
    public String generateToken(String username, List<String> roles) {
        Claims claims = Jwts.claims().setSubject(username);
        claims.put(KEY_ROLES, roles);

        var now = new Date();
        System.out.println("now : " + now);
        var expiredDate = new Date(now.getTime() + TOKEN_EXPIRE_TIME);

        return Jwts.builder()
                .setClaims(claims)  //토큰내용
                .setIssuedAt(now)   //토큰발행
                .setExpiration(expiredDate) //토큰만료시간
                .signWith(SignatureAlgorithm.ES512, this.secretKey) //암호화알고리즘, 비밀키
                .compact();
    }

    public Authentication getAuthentication(String jwt) {
        UserDetails userDetails = this.memberService.loadUserByUsername(this.getUsername(jwt));
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    //토큰이 유효한지 확인하는 메서드
    public String getUsername(String token) {
        return this.parseClaims(token).getSubject();
    }

    public boolean validateToken(String token) {
        if (!StringUtils.hasText(token)) return false;

        var claims = this.parseClaims(token);
        return !claims.getExpiration().before(new Date());  //현재시간과 유효시간을 비교하여 토큰이 유효한지 확인
    }

    private Claims parseClaims(String token) {
        try {
            //토큰에 담긴 claim반환하는 메서드
            return Jwts.parser().setSigningKey(this.secretKey).parseClaimsJwt(token).getBody();
        } catch (ExpiredJwtException e) {
            // TODO
            return e.getClaims();
        }
    }
}
