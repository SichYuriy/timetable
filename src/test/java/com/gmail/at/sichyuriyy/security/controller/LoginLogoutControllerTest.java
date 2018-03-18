package com.gmail.at.sichyuriyy.security.controller;

import com.gmail.at.sichyuriyy.security.service.SecurityService;
import com.gmail.at.sichyuriyy.user.domain.User;
import com.gmail.at.sichyuriyy.user.dto.UserTransformer;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class LoginLogoutControllerTest {

    private SecurityService securityService;
    private MockMvc mvc;

    @Before
    public void setUp() {
        securityService = mock(SecurityService.class);
        LoginLogoutController controller =
                new LoginLogoutController(securityService, new UserTransformer());
        this.mvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    public void currentUser_whenUserIsLoggedIn_shouldReturnUser() throws Exception {
        User loggedInUser = User.builder().id(1L).username("John_Snow").password("123").build();
        when(securityService.findLoggedInUser()).thenReturn(Optional.of(loggedInUser));

        mvc.perform(MockMvcRequestBuilders.get("/session/currentUser"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.username").value("John_Snow"))
                .andExpect(jsonPath("$.password").value("123"));

        verify(securityService).findLoggedInUser();
    }

    @Test
    public void currentUser_whenUserIsNotLoggedIn_shouldBeOk() throws Exception {
        when(securityService.findLoggedInUser()).thenReturn(Optional.empty());

        mvc.perform(MockMvcRequestBuilders.get("/session/currentUser"))
                .andExpect(status().isOk())
                .andExpect(content().string(""));

        verify(securityService).findLoggedInUser();
    }


    @Test
    public void login_whenCredentialsAreWrong_shouldResponse400() throws Exception {
        doThrow(UsernameNotFoundException.class).when(securityService).login(anyString(), anyString());
        mvc.perform(
                MockMvcRequestBuilders.post("/session/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"username\":\"John_Snow\",\"password\":\"123\"}"))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(""));

        verify(securityService).login("John_Snow", "123");
    }

    @Test
    public void login_whenCredentialsAreOk_shouldResponse200() throws Exception {
        mvc.perform(
                MockMvcRequestBuilders.post("/session/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"username\":\"John_Snow\",\"password\":\"123\"}"))
                .andExpect(status().isOk())
                .andExpect(content().string(""));

        verify(securityService).login("John_Snow", "123");
    }

    @Test
    public void logout_shouldResponse200() throws Exception {
        mvc.perform(
                MockMvcRequestBuilders.post("/session/logout"))
                .andExpect(status().isOk())
                .andExpect(content().string(""));

        verify(securityService).logout(any(HttpServletRequest.class), any(HttpServletResponse.class));
    }
}