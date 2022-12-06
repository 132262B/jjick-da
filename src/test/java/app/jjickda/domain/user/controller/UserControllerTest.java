package app.jjickda.domain.user.controller;

import app.jjickda.domain.user.dto.response.User;
import app.jjickda.domain.user.service.UserService;
import app.jjickda.global.config.exception.CustomException;
import org.junit.Rule;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.rules.ExpectedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
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
    void logout() throws Exception {

        this.mockMvc.perform(get("/api/user/logout"))
                .andDo(print())
                .andExpect(status().isOk());

        verify(userService).logout();
    }

}
