package com.yoo.kakaopay.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;

import java.util.HashMap;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@NoArgsConstructor
@Slf4j
public class ListControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private WebApplicationContext ctx;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setUp() {
        this.mvc = MockMvcBuilders.webAppContextSetup(ctx)
                .addFilters(new CharacterEncodingFilter("UTF-8", true))  // 필터 추가
                .alwaysDo(print())
                .build();

        objectMapper = Jackson2ObjectMapperBuilder.json().featuresToDisable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS).modules(new JavaTimeModule()).build();
    }

    @Test
    public void testPointUse() throws Exception {
        HashMap<String, Object> param = new HashMap<>();
        param.put("barcode","7281013626"); //존재하는 바코드
        //param.put("barcode","728101"); //존재하지 않는 바코드
        //param.put("startDt","2022-09-20"); //시작기간 날짜형식
        param.put("startDt","20220920"); //시작기간 날짜형식x
        param.put("endDt","2022-09-21"); //종료기간 날짜형식
        //param.put("endDt","100"); //종료기간 날짜형식x

        String content = objectMapper.writeValueAsString(param);

        mvc.perform(MockMvcRequestBuilders.post("/list/point")
                .content(content)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print());
    }
}