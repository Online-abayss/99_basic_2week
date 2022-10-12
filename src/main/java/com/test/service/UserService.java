package com.test.service;

import com.test.entity.RefreshToken;
import com.test.Dto.LoginRequestDto;
import com.test.Dto.SignupRequestDto;
import com.test.Dto.TokenDto;
import com.test.entity.User;
import com.test.entity.UserRoleEnum;
import com.test.repository.RefreshTokenRepository;
import com.test.repository.UserRepository;
import com.test.security.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final RefreshTokenRepository refreshTokenRepositroy;
    private final JwtProvider jwtProvider;



//    // 항해 강의 복붙
//    @Autowired
//    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
//        this.userRepository = userRepository;
//        this.passwordEncoder = passwordEncoder;
//    }
//
//
//    public void registerUser(SignupRequestDto requestDto) {
//        // 회원 ID 중복 확인
//        String username = requestDto.getUsername();
//        Optional<User> found = userRepository.findByUsername(username);
//        if (found.isPresent()) {
//            throw new IllegalArgumentException("중복된 사용자 ID 가 존재합니다.");
//        }
//        // 패스워드 암호화
//        String password = passwordEncoder.encode(requestDto.getPassword());
//        String email = requestDto.getEmail();
//
//        // 사용자 ROLE 확인
//        UserRoleEnum role = UserRoleEnum.USER;
//
//            role = UserRoleEnum.ADMIN;
//
//
//        User user = new User(username, password, email, role);
//        userRepository.save(user);
//    }
    public String registerUser(SignupRequestDto signupRequestDto) throws IllegalAccessException {
        String username = signupRequestDto.getUsername();
        String password = signupRequestDto.getPassword();
//        String samepassword = signupRequestDto.getSamepassword();

        Optional<User> found = userRepository.findByUsername(username);
        if(found.isPresent()) {
            throw new IllegalAccessException("중복 닉네임 확인!");
        }
//        if(!password.equals(samepassword)) {
//            throw new IllegalAccessException("비밀번호가 서로 다릅니다!");
//        }
        password = passwordEncoder.encode(signupRequestDto.getPassword());

        String email = signupRequestDto.getEmail();

        // 사용자 ROLE 확인
        UserRoleEnum role = UserRoleEnum.USER;

            role = UserRoleEnum.ADMIN;
//        LoginRequestDto dto = LoginRequestDto.builder()
//                .username(username)
//                .password(password)
//                .build();
        User user = new User(username, password, email, role);
        userRepository.save(user);
        return user.getUsername()+" 가입완료!";
    }



    //로그인
    public String login(LoginRequestDto loginRequestDto, HttpServletResponse response){
        // 1. Login ID/PW 를 기반으로 AuthenticationToken 생성
        UsernamePasswordAuthenticationToken authenticationToken
                = new UsernamePasswordAuthenticationToken(loginRequestDto.getUsername(), loginRequestDto.getPassword());

        // 2. 실제로 검증 (사용자 비밀번호 체크) 이 이루어지는 부분
        //    authenticate 메서드가 실행이 될 때 CustomUserDetailsService 에서 만들었던 loadUserByUsername 메서드가 실행됨
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

        // 3. 인증 정보를 기반으로 JWT 토큰 생성
        TokenDto tokenDto = jwtProvider.generateTokenDto(authentication);

        // 4. RefreshToken 저장
        RefreshToken refreshToken = RefreshToken.builder()
                .key(authentication.getName())
                .value(tokenDto.getRefreshToken())
                .build();

        refreshTokenRepositroy.save(refreshToken);

        response.addHeader("Access-Token", tokenDto.getGrantType()+" "+tokenDto.getAccessToken());
        response.addHeader("Refresh-Token", tokenDto.getRefreshToken());

        return authentication.getName();
    }
}
