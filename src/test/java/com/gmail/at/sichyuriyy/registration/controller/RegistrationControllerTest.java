package com.gmail.at.sichyuriyy.registration.controller;

import com.gmail.at.sichyuriyy.registration.service.RegistrationService;
import com.gmail.at.sichyuriyy.registration.service.UserAlreadyExistException;
import com.gmail.at.sichyuriyy.user.domain.User;
import com.gmail.at.sichyuriyy.user.dto.UserTransformer;
import com.gmail.at.sichyuriyy.user.validation.UserValidator;
import com.gmail.at.sichyuriyy.validation.ValidationErrorHandler;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class RegistrationControllerTest {

    @Mock
    private RegistrationService registrationService;
    private MockMvc mvc;

    private ArgumentCaptor<User> userCaptor = ArgumentCaptor.forClass(User.class);

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        RegistrationController controller =
                new RegistrationController(registrationService, new UserTransformer());
        this.mvc = MockMvcBuilders.standaloneSetup(controller)
                .setControllerAdvice(new ValidationErrorHandler())
                .setValidator(new UserValidator())
                .build();
    }

    @Test
    public void register_whenUserIsValidAndUsernameIsFree_shouldResponse200() throws Exception {
        mvc.perform(
                post("/users/registration")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"username\":\"John_Snow\",\"password\":\"123\"}"))
                .andExpect(status().isOk());

        verify(registrationService).register(userCaptor.capture());

        assertThat(userCaptor.getValue().getUsername()).isEqualTo("John_Snow");
        assertThat(userCaptor.getValue().getPassword()).isEqualTo("123");
    }

    @Test
    public void register_whenUserIsValidAndUsernameIsBusy_shouldResponse400() throws Exception {
        doThrow(new UserAlreadyExistException("test message")).when(registrationService).register(any(User.class));

        mvc.perform(
                post("/users/registration")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"username\":\"John_Snow\",\"password\":\"123\"}"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errors.[0].fieldName").value("username"))
                .andExpect(jsonPath("$.errors.[0].errorCode").value("user.username.alreadyExist"))
                .andExpect(jsonPath("$.errors.[0].defaultMessage").value("test message"));

        verify(registrationService).register(userCaptor.capture());

        assertThat(userCaptor.getValue().getUsername()).isEqualTo("John_Snow");
        assertThat(userCaptor.getValue().getPassword()).isEqualTo("123");
    }

    @Test
    public void register_whenUsernameIsEmpty_shouldResponse400() throws Exception {
        mvc.perform(
                post("/users/registration")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"username\":\"\",\"password\":\"123\"}"))
                .andExpect(status().isBadRequest())
                .andDo(print())
                .andExpect(jsonPath("$.errors.[0].fieldName").value("username"))
                .andExpect(jsonPath("$.errors.[0].errorCode").value("user.username.required"))
                .andExpect(jsonPath("$.errors.[0].defaultMessage").value("username can't be empty"));

        verifyZeroInteractions(registrationService);
    }

    @Test
    public void register_whenPasswordIsEmpty_shouldResponse400() throws Exception {
        mvc.perform(
                post("/users/registration")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"username\":\"John_Snow\",\"password\":\"\"}"))
                .andExpect(status().isBadRequest())
                .andDo(print())
                .andExpect(jsonPath("$.errors.[0].fieldName").value("password"))
                .andExpect(jsonPath("$.errors.[0].errorCode").value("user.password.required"))
                .andExpect(jsonPath("$.errors.[0].defaultMessage").value("password can't be empty"));

        verifyZeroInteractions(registrationService);
    }
}