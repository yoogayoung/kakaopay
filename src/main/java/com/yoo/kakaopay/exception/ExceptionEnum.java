package com.yoo.kakaopay.exception;

import lombok.Getter;
import lombok.ToString;
import org.springframework.http.HttpStatus;

@Getter
@ToString
public enum ExceptionEnum {
    RUNTIME_EXCEPTION(HttpStatus.BAD_REQUEST, "E0001"),
    ACCESS_DENIED_EXCEPTION(HttpStatus.UNAUTHORIZED, "E0002"),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "E0003"),
    ID_EXCEPTION_01(HttpStatus.BAD_REQUEST, "E1001", "사용자 ID가 없습니다."),
    ID_EXCEPTION_02(HttpStatus.BAD_REQUEST, "E1002", "사용자 ID에 해당하는 사용자가 존재하지 않습니다."),
    BARCODE_EXCEPTION_01(HttpStatus.BAD_REQUEST, "E2001", "바코드 정보가 없습니다."),
    BARCODE_EXCEPTION_02(HttpStatus.BAD_REQUEST, "E2002", "유효한 바코드가 아닙니다."),
    BARCODE_EXCEPTION_03(HttpStatus.BAD_REQUEST, "E2003", "등록되지 않은 멤버십 바코드입니다."),
    PARTNER_EXCEPTION_01(HttpStatus.BAD_REQUEST, "E3001", "상점 ID 정보가 없습니다."),
    PARTNER_EXCEPTION_02(HttpStatus.BAD_REQUEST, "E3002", "등록되지 않은 상점입니다."),
    POINT_EXCEPTION_01(HttpStatus.BAD_REQUEST, "E4001", "적립금 정보가 없습니다."),
    POINT_EXCEPTION_02(HttpStatus.BAD_REQUEST, "E4002", "포인트 부족으로 사용할 수 없습니다."),
    LIST_EXCEPTION_01(HttpStatus.BAD_REQUEST, "E5001", "시작기간이 없습니다."),
    LIST_EXCEPTION_02(HttpStatus.BAD_REQUEST, "E5002", "종료기간이 없습니다."),
    LIST_EXCEPTION_03(HttpStatus.BAD_REQUEST, "E5002", "날짜 형식이 맞지 않습니다.");

    private final HttpStatus status;
    private final String code;
    private String message;

    ExceptionEnum(HttpStatus status, String code) {
        this.status = status;
        this.code = code;
    }

    ExceptionEnum(HttpStatus status, String code, String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }
}
