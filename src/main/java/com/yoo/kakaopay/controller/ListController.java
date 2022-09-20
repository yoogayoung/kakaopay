package com.yoo.kakaopay.controller;

import com.yoo.kakaopay.exception.ApiException;
import com.yoo.kakaopay.exception.ExceptionEnum;
import com.yoo.kakaopay.service.IssueService;
import com.yoo.kakaopay.service.ListService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;

@RestController
@RequestMapping("/list")
public class ListController {

    @SuppressWarnings("unused")
    private Logger logger = LoggerFactory.getLogger(ListController.class);

    private ListService listService;
    private IssueService issueService;

    public ListController(ListService listService, IssueService issueService) {
        this.listService = listService;
        this.issueService = issueService;
    }

    @PostMapping("/point")
    public HashMap<String, Object> pointList(@RequestBody HashMap<String, Object> param){
        if(!param.containsKey("barcode")){
            throw new ApiException(ExceptionEnum.BARCODE_EXCEPTION_01);
        }
        if(!param.containsKey("startDt")){
            throw new ApiException(ExceptionEnum.LIST_EXCEPTION_01);
        }
        if(!param.containsKey("endDt")){
            throw new ApiException(ExceptionEnum.LIST_EXCEPTION_02);
        }

        String barcode = param.get("barcode").toString();
        String startDt = param.get("startDt").toString();
        String endDt = param.get("endDt").toString();

        if(barcode.length() != 10){
            throw new ApiException(ExceptionEnum.BARCODE_EXCEPTION_02);
        }
        //1) 날짜 형식 검증
        checkDate(startDt);
        checkDate(endDt);

        //2) 존재하는 바코드인지 확인
        HashMap<String, Object> barcodeMap = new HashMap<>();
        barcodeMap = issueService.checkBarcode(param);
        if(Integer.parseInt(barcodeMap.get("cnt").toString()) == 0){
            throw new ApiException(ExceptionEnum.BARCODE_EXCEPTION_03);
        }

        //3) 포인트 내역 조회
        ArrayList<HashMap<String, Object>> pointList = listService.selectPointList(param);

        HashMap<String, Object> resultMap = new HashMap<>();
        resultMap.put("history",pointList);
        return resultMap;
    }

    public static void checkDate(String checkDate) {
        try {
            SimpleDateFormat dateFormatParser = new SimpleDateFormat("yyyy-MM-dd");
            dateFormatParser.setLenient(false);
            dateFormatParser.parse(checkDate);
        } catch (Exception e) {
            throw new ApiException(ExceptionEnum.LIST_EXCEPTION_03);
        }
    }
}
