package com.yoo.kakaopay.service;

import com.yoo.kakaopay.mapper.ApiMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;

@Service
public class UseService {

    private ApiMapper apiMapper;

    public UseService(ApiMapper apiMapper) {
        this.apiMapper = apiMapper;
    }

    @Transactional
    public HashMap<String, Object> selectPoint(HashMap<String, Object> param) {
        return apiMapper.selectPoint(param);
    }
}
