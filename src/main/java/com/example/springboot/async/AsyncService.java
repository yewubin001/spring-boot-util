package com.example.springboot.async;

import java.util.concurrent.Future;

public interface AsyncService {
    /**
     * 执行异步任务
     */
    void executeAsync();

    /**
     * 执行异步任务
     */
    Future<String> executeAsyncReturn();

}
