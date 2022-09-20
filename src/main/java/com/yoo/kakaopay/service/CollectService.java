package com.yoo.kakaopay.service;

import com.yoo.kakaopay.mapper.ApiMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;

@Service
public class CollectService {

    private ApiMapper apiMapper;

    public CollectService(ApiMapper apiMapper) {
        this.apiMapper = apiMapper;
    }

    @Transactional
    public void insertPointInfo(HashMap<String, Object> param) {
        apiMapper.insertPointInfo(param);
    }

    @Transactional
    public HashMap<String, Object> checkPartner(HashMap<String, Object> param) {
        return apiMapper.checkPartner(param);
    }
}
