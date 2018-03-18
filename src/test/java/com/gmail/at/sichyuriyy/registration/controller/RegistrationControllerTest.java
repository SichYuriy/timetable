package com.gmail.at.sichyuriyy.registration.controller;

import com.gmail.at.sichyuriyy.registration.service.RegistrationService;
import com.gmail.at.sichyuriyy.user.domain.User;
import com.gmail.at.sichyuriyy.user.dto.UserTransformer;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
        this.mvc = MockMvcBuilders.standaloneSetup(controller).build();
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
}