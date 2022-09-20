package com.yoo.kakaopay.controller;

import com.yoo.kakaopay.exception.ApiException;
import com.yoo.kakaopay.exception.ExceptionEnum;
import com.yoo.kakaopay.service.CollectService;
import com.yoo.kakaopay.service.IssueService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@RequestMapping("/collect")
public class CollectController {

    @SuppressWarnings("unused")
    private Logger logger = LoggerFactory.getLogger(CollectController.class);

    private CollectService collectService;
    private IssueService issueService;

    public CollectController(CollectService collectService, IssueService issueService) {
        this.collectService = collectService;
        this.issueService = issueService;
    }

    @PostMapping("/point")
    public int collectPoint(@RequestBody HashMap<String, Object> param){
        if(!param.containsKey("barcode")){
            throw new ApiException(ExceptionEnum.BARCODE_EXCEPTION_01);
        }
        if(!param.containsKey("partnerId")){
            throw new ApiException(ExceptionEnum.PARTNER_EXCEPTION_01);
        }
        if(!param.containsKey("point")){
            throw new ApiException(ExceptionEnum.POINT_EXCEPTION_01);
        }

        String barcode = param.get("barcode").toString();
        String partnerId = param.get("partnerId").toString();
        String point = param.get("point").toString();

        if(barcode.length() != 10){
            throw new ApiException(ExceptionEnum.BARCODE_EXCEPTION_02);
        }
        if(partnerId.equals("")){
            throw new ApiException(ExceptionEnum.PARTNER_EXCEPTION_01);
        }
        if(Integer.parseInt(point) == 0){
            throw new ApiException(ExceptionEnum.POINT_EXCEPTION_01);
        }

        //1) 존재하는 상점인지 확인
        HashMap<String, Object> partnerMap = new HashMap<>();
        partnerMap = collectService.checkPartner(param);
        if(Integer.parseInt(partnerMap.get("cnt").toString()) == 0){
            throw new ApiException(ExceptionEnum.PARTNER_EXCEPTION_02);
        }

        //2) 존재하는 바코드인지 확인
        HashMap<String, Object> barcodeMap = new HashMap<>();
        barcodeMap = issueService.checkBarcode(param);
        if(Integer.parseInt(barcodeMap.get("cnt").toString()) == 0){
            throw new ApiException(ExceptionEnum.BARCODE_EXCEPTION_03);
        }

        //3) 포인트 적립
        param.put("type","earn");
        collectService.insertPointInfo(param);

        return HttpStatus.OK.value();
    }
}
