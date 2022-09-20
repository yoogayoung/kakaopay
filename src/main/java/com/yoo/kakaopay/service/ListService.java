package com.yoo.kakaopay.service;

import com.yoo.kakaopay.mapper.ApiMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;

@Service
public class ListService {

    private ApiMapper apiMapper;

    public ListService(ApiMapper apiMapper) {
        this.apiMapper = apiMapper;
    }

    @Transactional
    public ArrayList<HashMap<String, Object>> selectPointList(HashMap<String, Object> param) {
        return apiMapper.selectPointList(param);
    }
}
