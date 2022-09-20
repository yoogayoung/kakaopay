package com.yoo.kakaopay.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;

@Mapper
@Repository
public interface ApiMapper {
    HashMap<String, Object> selectUserInfo(HashMap<String, Object> param);
    HashMap<String, Object> checkBarcode(HashMap<String, Object> param);
    void updateBarcode(HashMap<String, Object> param);

    void insertPointInfo(HashMap<String, Object> param);
    HashMap<String, Object> checkPartner(HashMap<String, Object> param);

    HashMap<String, Object> selectPoint(HashMap<String, Object> param);
    ArrayList<HashMap<String, Object>> selectPointList(HashMap<String, Object> param);
}
