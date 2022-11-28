package app.jjickda.domain.user.service;

import app.jjickda.domain.common.dto.response.DefaultResultDto;
import app.jjickda.domain.user.dto.request.EmailDuplicationDto;
import app.jjickda.domain.user.dto.request.LoginDto;
import app.jjickda.domain.user.dto.request.SignUpDto;
import app.jjickda.domain.user.dto.response.User;
import app.jjickda.domain.user.repository.UserRepository;
import app.jjickda.global.config.exception.CustomException;
import app.jjickda.global.config.exception.ErrorCode;
import app.jjickda.global.utils.SessionUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // 이메일 중복 체크
    public DefaultResultDto emailDuplicationCheck(EmailDuplicationDto emailDuplicationDto) {

        int emailCheckCount = userRepository.emailDuplicationCheck(emailDuplicationDto);

        if (emailCheckCount == 0) {
            return DefaultResultDto.builder()
                    .success(true)
                    .message("")
                    .build();
        } else {
            return DefaultResultDto.builder()
                    .success(false)
                    .message("이미 가입된 이메일 주소입니다. 다른 이메일을 입력하여 주세요.")
                    .build();
        }
    }

    // 회원가입, 계정생성
    public DefaultResultDto signUp(SignUpDto signUpDto) {

        try {
            userRepository.signUp(signUpDto);

            // 현재 찍다 프로젝트에서 idx가 pk, email은 uk 로 등록되어 있는데, insert 도중 uk 값이 충돌되는 경우 DuplicateKeyException 이 발생함.
        } catch (DuplicateKeyException e) {
            throw new CustomException("회원가입 도중 이메일 중복문제 발생", ErrorCode.SIGN_UP_EMAIL_DUPLICATE);
        }

        return DefaultResultDto.builder()
                .success(true)
                .message("회원가입 성공!")
                .build();
    }

    // 사용자 정보 조회
    public User getUser(LoginDto loginDto) {
        return userRepository.getUser(loginDto);
    }

    // 로그인
    public DefaultResultDto login(User user) {

        if (user == null) {
            return DefaultResultDto.builder()
                    .success(false)
                    .message("아이디가 존재하지 않거나, 틀린 비밀번호 입니다.")
                    .build();
        } else {
            SessionUtil.setUserAttribute(user);

            return DefaultResultDto.builder()
                    .success(true)
                    .message("로그인 성공!")
                    .build();
        }
    }

    // 로그아웃
    public DefaultResultDto logout() {
        SessionUtil.invalidate();

        return DefaultResultDto.builder()
                .success(true)
                .message("로그아웃 성공!")
                .build();
    }
}
