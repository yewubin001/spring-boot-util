package com.example.springboot.design.行为型模式.listener.guava;

/**
 * @Auther: yewub
 * @Date: 2019/2/15 11:55
 * @Description:
 */
public class SmsEvent {
    private Long smsId;
    private String telphone;
    private String content;

    public SmsEvent(Long smsId, String telphone, String content) {
        this.smsId = smsId;
        this.telphone = telphone;
        this.content = content;
    }

    public String getTelphone() {
        return telphone;
    }

    public void setTelphone(String telphone) {
        this.telphone = telphone;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getSmsId() {
        return smsId;
    }

    public void setSmsId(Long smsId) {
        this.smsId = smsId;
    }
}
