package app.jjickda.domain.user.service;

import app.jjickda.domain.common.dto.response.DefaultResultDto;
import app.jjickda.domain.user.dto.request.EmailDuplicationDto;
import app.jjickda.domain.user.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.internal.matchers.Any;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith({MockitoExtension.class})
public class UserServiceTest {


    @Mock
    UserRepository userRepository;
    @InjectMocks
    UserService userService;


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

}
