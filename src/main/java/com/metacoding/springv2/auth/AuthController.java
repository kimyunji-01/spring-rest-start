package com.metacoding.springv2.auth;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.metacoding.springv2._core.util.Resp;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class AuthController {

    private final AuthService authService;
     @PostMapping("/join")
     public ResponseEntity<?>join(@RequestBody AuthRequest.JoinDTO reqDTO){ //@RequestBody가 붙어있으면 제이슨
      var respDTO = authService.회원가입(reqDTO);  
        return Resp.ok(respDTO); 
     }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest.LoginDTO reqDTO) { //ResponseEntity 헤더와 바디를 같이 응답해주
        String accessToken = authService.로그인(reqDTO);
        return Resp.ok(accessToken);
    }

    @GetMapping("/health")
    public String healthCheck() {
        return "health ok";
    } 
}
