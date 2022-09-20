package com.yoo.kakaopay.controller;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@NoArgsConstructor
@Slf4j
public class IssueControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private WebApplicationContext ctx;

    @Before
    public void setUp(){
        this.mvc = MockMvcBuilders.webAppContextSetup(ctx)
                .addFilters(new CharacterEncodingFilter("UTF-8", true))  // 필터 추가
                .alwaysDo(print())
                .build();
    }

    @Test
    public void testIssueBarcode() throws Exception {
        //String userId = "123456789"; //존재하는 id
        //String userId = "1234"; //존재하지 않는 id
        String userId = "111111111"; //신규 id

        mvc.perform(MockMvcRequestBuilders.get("/issue/barcode?userId="+ userId)
            .contentType(MediaType.APPLICATION_JSON))
            .andDo(print());
    }

}