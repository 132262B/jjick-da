package app.jjickda.domain.user.service;

import app.jjickda.domain.common.dto.response.DefaultResultDto;
import app.jjickda.domain.user.dto.request.EmailDuplicationDto;
import app.jjickda.domain.user.dto.request.LoginDto;
import app.jjickda.domain.user.dto.request.SignUpDto;
import app.jjickda.domain.user.dto.response.User;
import app.jjickda.domain.user.repository.UserRepository;
import app.jjickda.global.config.exception.CustomException;
import app.jjickda.global.config.exception.ErrorCode;
import app.jjickda.global.utils.SHA256Util;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DuplicateKeyException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@DisplayName("USER 관련 테스트")
@ExtendWith({MockitoExtension.class})
class UserServiceTest {

    @Mock
    UserRepository userRepository;
    @InjectMocks
    UserService userService;

    private User testUser;

    private final String PASSWORD = "Test1234#";

    @BeforeEach
    public void generateUser() {
        testUser = User.builder()
                .idx(1)
                .email("user@jjickda.com")
                .roleIdx(1)
                .roleName("ROLE_USER")
                .name("테스터")
                .build();
    }

    @DisplayName("이메일 체크 테스트 (존재하는 이메일이 없는경우)")
    @Test
    public void emailDuplicationCheckSuccessTest() {
        String testEmail = "user@jjickda.com";
        EmailDuplicationDto emailDuplicationDto = new EmailDuplicationDto(testEmail);

        when(userRepository.emailDuplicationCheck(emailDuplicationDto))
                .thenReturn(0);

        DefaultResultDto defaultResultDto = userService.emailDuplicationCheck(emailDuplicationDto);

        Assertions.assertTrue(defaultResultDto.isSuccess());

        verify(userRepository, times(1)).emailDuplicationCheck(any(EmailDuplicationDto.class));
    }

    @DisplayName("이메일 체크 테스트 (존재하는 이메일이 있는경우)")
    @Test
    public void emailDuplicationCheckFailuredTest() {
        String testEmail = "user@jjickda.com";
        EmailDuplicationDto emailDuplicationDto = new EmailDuplicationDto(testEmail);

        when(userRepository.emailDuplicationCheck(emailDuplicationDto))
                .thenReturn(1);

        DefaultResultDto defaultResultDto = userService.emailDuplicationCheck(emailDuplicationDto);

        Assertions.assertFalse(defaultResultDto.isSuccess());

        verify(userRepository, times(1)).emailDuplicationCheck(any(EmailDuplicationDto.class));
    }

    @DisplayName("회원가입 테스트 (성공시)")
    @Test
    public void signUpUserTestWithSuccess() {
        SignUpDto signUpDto = SignUpDto.builder()
                .email(testUser.getEmail())
                .password(SHA256Util.encrypt(PASSWORD))
                .name("테스터")
                .build();

        when(userRepository.signUp(signUpDto)).thenReturn(1);

        DefaultResultDto defaultResultDto = userService.signUp(signUpDto);

        Assertions.assertTrue(defaultResultDto.isSuccess());

        verify(userRepository, times(1))
                .signUp(any(SignUpDto.class));
    }


    @DisplayName("회원가입 테스트 (가입 도중 이메일 중복이 발생한 경우)")
    @Test
    public void signUpUserTestWithFailed() {
        SignUpDto signUpDto = SignUpDto.builder()
                .email(testUser.getEmail())
                .password(SHA256Util.encrypt(PASSWORD))
                .name("테스터")
                .build();

        when(userRepository.signUp(signUpDto)).thenThrow(DuplicateKeyException.class);

        try {
            userService.signUp(signUpDto);
        } catch (CustomException e) {
            Assertions.assertEquals(e.getErrorCode(), ErrorCode.SIGN_UP_EMAIL_DUPLICATE);
        }
    }

    @DisplayName("로그인 테스트 (성공)")
    @Test
    public void loginTestWithSuccess() {
        LoginDto loginDto = LoginDto.builder()
                .email(testUser.getEmail())
                .password(SHA256Util.encrypt(PASSWORD))
                .build();

        when(userRepository.getUser(loginDto)).thenReturn(testUser);

        User user = userService.getUser(loginDto);
        Assertions.assertEquals(user, testUser);

        verify(userRepository, times(1))
                .getUser(any(LoginDto.class));
    }

    @DisplayName("로그인 테스트 (실패)")
    @Test
    public void loginTestWithFailed() {
        LoginDto loginDto = LoginDto.builder()
                .email(testUser.getEmail())
                .password(SHA256Util.encrypt("NoTest1234!"))
                .build();

        when(userRepository.getUser(loginDto)).thenReturn(null);

        User user = userService.getUser(loginDto);
        Assertions.assertNull(user);

        verify(userRepository, times(1))
                .getUser(any(LoginDto.class));
    }
}
