package app.jjickda.domain.user.controller;

import app.jjickda.domain.common.dto.response.DefaultResultDto;
import app.jjickda.domain.user.service.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("USER 컨트롤러 테스트")
@WebMvcTest(UserRestController.class)
class UserControllerTest {

    @MockBean
    private UserService userService;

    @Autowired
    private MockMvc mockMvc;

    @DisplayName("로그아웃 테스트")
    @Test
    void logoutTest() throws Exception {

        // given
        DefaultResultDto defaultResultDto = DefaultResultDto.builder()
                .success(true)
                .message("로그아웃 성공!")
                .build();

        when(userService.logout()).thenReturn(defaultResultDto);

        this.mockMvc.perform(get("/api/user/logout")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
        ;

        verify(userService).logout();
    }

}
