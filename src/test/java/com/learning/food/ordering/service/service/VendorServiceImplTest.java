package com.learning.food.ordering.service.service;

import com.learning.food.ordering.service.Application;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@ActiveProfiles(profiles = "test")
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class VendorServiceImplTest {

    @BeforeEach
    void setUp() {
    }

    @Test
    void addVendor() {
    }

    @Test
    void getVendors() {
    }

    @Test
    void getVendor() {
    }

    @Test
    void updateVendor() {
    }

    @Test
    void deleteVendor() {
    }
}