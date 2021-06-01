package com.learning.food.ordering.service.controller;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.learning.food.ordering.service.Application;
import com.learning.food.ordering.service.constant.ApiConstant;
import com.learning.food.ordering.service.constant.CommonConstants;
import com.learning.food.ordering.service.dto.PageableResponseDto;
import com.learning.food.ordering.service.dto.ResponseDto;
import com.learning.food.ordering.service.dto.VendorDto;
import com.learning.food.ordering.service.repository.VendorRepository;
import org.junit.jupiter.api.Assertions;
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

import java.lang.reflect.Type;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@ActiveProfiles(profiles = "test")
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class VendorRestControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private VendorRepository vendorRepository;

    private Gson gson = new Gson();

    @BeforeEach
    void setUp() {
        this.mockMvc = webAppContextSetup(webApplicationContext).build();
        vendorRepository.deleteAll();
    }

    @Test
    void addVendorDetails() throws Exception {
        VendorDto vendorDto = VendorDto.builder()
                .vendorName("food")
                .description("testing")
                .build();
        String body = gson.toJson(vendorDto);
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post(ApiConstant.VENDOR_API_ROOT + ApiConstant.VENDOR_API)
                .contentType(MediaType.APPLICATION_JSON)
                .header(CommonConstants.AUTHORIZATION, CommonConstants.TOKEN)
                .content(body)
                .characterEncoding(CommonConstants.UTF);

        MvcResult mvcResult = mockMvc.perform(requestBuilder)
                .andExpect(status().isOk()).andReturn();
        ResponseDto<VendorDto> responseDto = gson.fromJson(mvcResult.getResponse().getContentAsString(), ResponseDto.class);

        System.out.println(mvcResult.getResponse().getContentAsString());
        Assertions.assertEquals(200, responseDto.getStatus());
    }

    @Test
    void addVendorDetailsWithoutName() throws Exception {
        VendorDto vendorDto = VendorDto.builder()
                .description("testing")
                .build();
        String body = gson.toJson(vendorDto);
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post(ApiConstant.VENDOR_API_ROOT + ApiConstant.VENDOR_API)
                .contentType(MediaType.APPLICATION_JSON)
                .header(CommonConstants.AUTHORIZATION, CommonConstants.TOKEN)
                .content(body)
                .characterEncoding(CommonConstants.UTF);

        MvcResult mvcResult = mockMvc.perform(requestBuilder)
                .andExpect(status().is4xxClientError()).andReturn();

        System.out.println(mvcResult.getResponse().getContentAsString());
    }


    @Test
    void getVendors() throws Exception {
        VendorDto vendorDto = VendorDto.builder()
                .vendorName("food")
                .description("testing")
                .build();
        vendorDto = addVendor(vendorDto);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(ApiConstant.VENDOR_API_ROOT + ApiConstant.VENDOR_API)
                .contentType(MediaType.APPLICATION_JSON)
                .header(CommonConstants.AUTHORIZATION, CommonConstants.TOKEN)
                .characterEncoding(CommonConstants.UTF);
        MvcResult mvcResult = mockMvc.perform(requestBuilder)
                .andExpect(status().isOk()).andReturn();
        System.out.println(mvcResult.getResponse().getContentAsString());
        Type type = new TypeToken<ResponseDto<PageableResponseDto<VendorDto>>>() {
        }.getType();
        ResponseDto<PageableResponseDto<VendorDto>> responseDto = gson.fromJson(mvcResult.getResponse().getContentAsString(), type);
        Assertions.assertEquals(200, responseDto.getStatus());
        Assertions.assertEquals(1, responseDto.getResult().getDetails().size());


    }

    @Test
    void getVendorsEmptyList() throws Exception {

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(ApiConstant.VENDOR_API_ROOT + ApiConstant.VENDOR_API)
                .contentType(MediaType.APPLICATION_JSON)
                .header(CommonConstants.AUTHORIZATION, CommonConstants.TOKEN)
                .characterEncoding(CommonConstants.UTF);
        MvcResult mvcResult = mockMvc.perform(requestBuilder)
                .andExpect(status().isOk()).andReturn();
        System.out.println(mvcResult.getResponse().getContentAsString());
        Type type = new TypeToken<ResponseDto<PageableResponseDto<VendorDto>>>() {
        }.getType();
        ResponseDto<PageableResponseDto<VendorDto>> responseDto = gson.fromJson(mvcResult.getResponse().getContentAsString(), type);
        Assertions.assertEquals(200, responseDto.getStatus());
        Assertions.assertEquals(0, responseDto.getResult().getDetails().size());


    }


    @Test
    void getVendor() throws Exception {
        VendorDto vendorDto = VendorDto.builder()
                .vendorName("food")
                .description("testing")
                .build();
        vendorDto = addVendor(vendorDto);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(ApiConstant.VENDOR_API_ROOT + ApiConstant.VENDOR_API + "/" + vendorDto.vendorId)
                .contentType(MediaType.APPLICATION_JSON)
                .header(CommonConstants.AUTHORIZATION, CommonConstants.TOKEN)
                .characterEncoding(CommonConstants.UTF);
        MvcResult mvcResult = mockMvc.perform(requestBuilder)
                .andExpect(status().isOk()).andReturn();
        System.out.println(mvcResult.getResponse().getContentAsString());
        Type type = new TypeToken<ResponseDto<VendorDto>>() {
        }.getType();

        ResponseDto<VendorDto> responseDto = gson.fromJson(mvcResult.getResponse().getContentAsString(), type);
        Assertions.assertEquals(200, responseDto.getStatus());
        Assertions.assertEquals(vendorDto.vendorId, responseDto.getResult().vendorId);

    }

    @Test
    void updateVendor() throws Exception {
        VendorDto vendorDto = VendorDto.builder()
                .vendorName("food")
                .description("testing")
                .build();
        vendorDto = addVendor(vendorDto);
        vendorDto.setVendorName("Food");

        String body = gson.toJson(vendorDto);
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .put(ApiConstant.VENDOR_API_ROOT + ApiConstant.VENDOR_API + "/" + vendorDto.vendorId)
                .contentType(MediaType.APPLICATION_JSON)
                .header(CommonConstants.AUTHORIZATION, CommonConstants.TOKEN)
                .content(body)
                .characterEncoding(CommonConstants.UTF);

        MvcResult mvcResult = mockMvc.perform(requestBuilder)
                .andExpect(status().isOk()).andReturn();
        Type type = new TypeToken<ResponseDto<VendorDto>>() {
        }.getType();
        ResponseDto<VendorDto> responseDto = gson.fromJson(mvcResult.getResponse().getContentAsString(), type);

        System.out.println(mvcResult.getResponse().getContentAsString());
        Assertions.assertEquals(200, responseDto.getStatus());
        Assertions.assertEquals("Food", responseDto.getResult().vendorName);

    }

    @Test
    void removeVendor() throws Exception {
        VendorDto vendorDto = VendorDto.builder()
                .vendorName("food")
                .description("testing")
                .build();
        vendorDto = addVendor(vendorDto);
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .delete(ApiConstant.VENDOR_API_ROOT + ApiConstant.VENDOR_API + "/" + vendorDto.vendorId)
                .contentType(MediaType.APPLICATION_JSON)
                .header(CommonConstants.AUTHORIZATION, CommonConstants.TOKEN)
                .characterEncoding(CommonConstants.UTF);

        MvcResult mvcResult = mockMvc.perform(requestBuilder)
                .andExpect(status().isOk()).andReturn();
        Type type = new TypeToken<ResponseDto<VendorDto>>() {}.getType();

        ResponseDto<VendorDto> responseDto = gson.fromJson(mvcResult.getResponse().getContentAsString(), type);

        System.out.println(mvcResult.getResponse().getContentAsString());
        Assertions.assertEquals(200, responseDto.getStatus());
        Assertions.assertEquals(vendorDto.getVendorId(), responseDto.getResult().vendorId);

    }

    private VendorDto addVendor(VendorDto vendorDto) throws Exception {

        String body = gson.toJson(vendorDto);
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post(ApiConstant.VENDOR_API_ROOT + ApiConstant.VENDOR_API)
                .contentType(MediaType.APPLICATION_JSON)
                .header(CommonConstants.AUTHORIZATION, CommonConstants.TOKEN)
                .content(body)
                .characterEncoding(CommonConstants.UTF);

        MvcResult mvcResult = mockMvc.perform(requestBuilder)
                .andExpect(status().isOk()).andReturn();
        Type type = new TypeToken<ResponseDto<VendorDto>>() {
        }.getType();
        ResponseDto<VendorDto> responseDto = gson.fromJson(mvcResult.getResponse().getContentAsString(), type);
        responseDto.getResult();

        return responseDto.getResult();
    }
}