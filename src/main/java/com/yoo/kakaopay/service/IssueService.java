package com.yoo.kakaopay.service;

import com.yoo.kakaopay.mapper.ApiMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;

@Service
public class IssueService {

    private ApiMapper apiMapper;

    public IssueService(ApiMapper apiMapper) {
        this.apiMapper = apiMapper;
    }

    @Transactional
    public HashMap<String, Object> selectUserInfo(HashMap<String, Object> param) {
        return apiMapper.selectUserInfo(param);
    }

    @Transactional
    public HashMap<String, Object> checkBarcode(HashMap<String, Object> param) {
        return apiMapper.checkBarcode(param);
    }

    @Transactional
    public void updateBarcode(HashMap<String, Object> param) {
        apiMapper.updateBarcode(param);
    }
}
