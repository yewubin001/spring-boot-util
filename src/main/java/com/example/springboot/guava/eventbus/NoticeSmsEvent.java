package com.example.springboot.guava.eventbus;

/**
 * @Auther: yewub
 * @Date: 2019/2/15 11:56
 * @Description:
 */
public class NoticeSmsEvent extends SmsEvent {

    public NoticeSmsEvent(Long smsId, String telphone, String content) {
        super(smsId, telphone, content);
    }
}
