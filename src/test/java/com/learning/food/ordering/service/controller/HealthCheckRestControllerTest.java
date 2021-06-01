package com.learning.food.ordering.service.controller;

import com.google.gson.Gson;
import com.learning.food.ordering.service.Application;
import com.learning.food.ordering.service.constant.ApiConstant;
import com.learning.food.ordering.service.constant.CommonConstants;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@ActiveProfiles(profiles = "test")
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class HealthCheckRestControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    private Gson gson = new Gson();

    @BeforeEach
    void setUp() {
        this.mockMvc = webAppContextSetup(webApplicationContext).build();

    }

    /**
     * To check health of application
     *
     * @throws Exception
     */
    @Test
    void checkHealth() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(ApiConstant.HEALTH_CHECK_API)
                .header(CommonConstants.AUTHORIZATION, CommonConstants.TOKEN)
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding(CommonConstants.UTF);
        MvcResult mvcResult = mockMvc.perform(requestBuilder)
                .andExpect(status().isOk()).andReturn();
    }
}