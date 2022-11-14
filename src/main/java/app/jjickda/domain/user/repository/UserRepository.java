package app.jjickda.domain.user.repository;

import app.jjickda.domain.user.dto.request.EmailDuplicationDto;
import app.jjickda.domain.user.dto.request.LoginDto;
import app.jjickda.domain.user.dto.request.SignUpDto;
import app.jjickda.domain.user.dto.response.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserRepository {

    // 이메일 중복 체크
    int emailDuplicationCheck(EmailDuplicationDto emailDuplicationDto);

    // 회원가입, 계정생성
    int signUp(SignUpDto signUpDto);

    // 로그인
    User getUser(LoginDto lgoinDto);
}
