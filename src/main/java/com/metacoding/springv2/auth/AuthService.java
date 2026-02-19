package com.metacoding.springv2.auth;


import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.metacoding.springv2._core.handler.ex.Exception401;
import com.metacoding.springv2.auth.AuthRequest.LoginDTO;
import com.metacoding.springv2.user.User;
import com.metacoding.springv2.user.UserRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class AuthService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    

    public void 로그인(LoginDTO reqDTO) {
        //1.UserRepository에서 username 확인  
       User findUser = userRepository.findByUsername(reqDTO.getUsername())
        .orElseThrow(() -> new Exception401("유저네임을 찾을 수 없어요")); //못 찾으면 튕겨버리기
        //2.password를 hash해서 비교검증 해야함 DB에는 그냥 1234가 아닌 해시값이 들어가 있으니까
        boolean isSamePassword = bCryptPasswordEncoder.matches(reqDTO.getPassword(), findUser.getPassword()); //리턴타입은 boolean
        if (!isSamePassword) {throw new Exception401("비밀번호가 틀렸어요");

        }
        //3.Authentication 객체 만들기
        Authentication authentication = 
        new UsernamePasswordAuthenticationToken(findUser, null, findUser.getAuthorities());
        //4. SecurityContextHolder에 저장
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

}
