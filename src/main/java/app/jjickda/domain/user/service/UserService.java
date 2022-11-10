package app.jjickda.domain.user.service;

import app.jjickda.domain.user.dto.request.EmailDuplicationDto;
import app.jjickda.domain.user.dto.response.EmailDuplicationResultDto;
import app.jjickda.domain.user.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // 이메일 중복 체크
    public EmailDuplicationResultDto emailDuplicationCheck(EmailDuplicationDto emailDuplicationDto) {

        int emailCheckCount = userRepository.emailDuplicationCheck(emailDuplicationDto);

        if (emailCheckCount == 0) {
            return EmailDuplicationResultDto.builder()
                    .success(true)
                    .message("")
                    .build();
        } else {
            return EmailDuplicationResultDto.builder()
                    .success(false)
                    .message("이미 가입된 이메일 주소입니다. 다른 이메일을 입력하여 주세요.")
                    .build();
        }
    }


}
