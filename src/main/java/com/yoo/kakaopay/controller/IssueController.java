package com.yoo.kakaopay.controller;

import com.yoo.kakaopay.exception.ApiException;
import com.yoo.kakaopay.exception.ExceptionEnum;
import com.yoo.kakaopay.service.IssueService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Random;

@RestController
@RequestMapping("/issue")
public class IssueController {

    @SuppressWarnings("unused")
    private Logger logger = LoggerFactory.getLogger(IssueController.class);

    private IssueService issueService;

    public IssueController(IssueService issueService) {
        this.issueService = issueService;
    }

    @GetMapping("/barcode")
    public String issueBarcode(@RequestParam(required = false) String userId){
        if(userId.length() != 9){
            throw new ApiException(ExceptionEnum.ID_EXCEPTION_02);
        }

        HashMap<String, Object> param = new HashMap<>();
        param.put("userId",userId);

        //1) USER TABLE을 조회하여 사용자의 멤버십 바코드 존재 여부 확인
        HashMap<String, Object> userMap = new HashMap<>();
        userMap = issueService.selectUserInfo(param);
        if(userMap == null){
            throw new ApiException(ExceptionEnum.ID_EXCEPTION_02);
        }
        String barcode = userMap.get("barcode").toString();

        //2) 바코드가 존재하지 않는다면 신규 바코드 생성
        if(barcode.equals("")){
            HashMap<String, Object> barcodeMap = new HashMap<>();
            HashMap<String, Object> check = new HashMap<>();
            while(true){
                Random random = new Random();
                for(int i=0; i<10; i++){
                    barcode += random.nextInt(9) + "";
                }
                check.put("barcode",barcode);
                barcodeMap = issueService.checkBarcode(check);
                if(Integer.parseInt(barcodeMap.get("cnt").toString()) == 0){
                    break;
                }
            }
            //3) 생성된 바코드 정보 업데이트
            check.put("userId",userId);
            issueService.updateBarcode(check);
        }

        return barcode;
    }
}
