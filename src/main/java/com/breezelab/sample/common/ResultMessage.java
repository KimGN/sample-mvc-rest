package com.breezelab.sample.common;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResultMessage {
    private String successYn; // 성공 여부
    private String bizcode; // 업무성 코드
    private Object data; // 데이터 객체
    private String message; // 메세지
    private String devMessage; // 개발자용 메세지

    public ResultMessage() {
    }

    public ResultMessage(String successYn) {
        this.successYn = successYn;
    }

    public ResultMessage(String successYn, String message) {
        this.successYn = successYn;
        this.message = message;
    }

    public ResultMessage(String successYn, String bizcode, String message) {
        this.successYn = successYn;
        this.bizcode = bizcode;
        this.message = message;
    }

    public ResultMessage(String successYn, String bizcode, Object data, String message) {
        this.successYn = successYn;
        this.bizcode = bizcode;
        this.data = data;
        this.message = message;
    }

    public ResultMessage(String successYn, String bizcode, String message, String devMessage) {
        this.successYn = successYn;
        this.bizcode = bizcode;
        this.message = message;
        this.devMessage = devMessage;
    }

    @SuppressWarnings("unchecked")
    public <T> T getData() {
        return (T)data;
    }

}
