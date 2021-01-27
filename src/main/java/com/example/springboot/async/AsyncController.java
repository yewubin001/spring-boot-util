package com.example.springboot.async;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * @Auther: 59315
 * @Date: 2019/6/13 22:08
 * @Description: @Async 一般不会有返回值，如果需要返回值就返回Future，就变成同步的了。
 *
 */
@RestController
@RequestMapping("/api")
public class AsyncController {
    private static final Logger logger = LoggerFactory.getLogger(AsyncController.class);
    @Autowired
    private AsyncService asyncService;
    
    @GetMapping("/async")
    public String async() {
        logger.info("start submit");
        //调用service层的任务
        asyncService.executeAsync();
        logger.info("end submit");
        return "success";
    }

    @GetMapping("/async-future")
    public String asyncFuture() throws ExecutionException, InterruptedException {
        logger.info("start submit");
        //调用service层的任务
        Future<String> fu = asyncService.executeAsyncReturn();
        logger.info("end submit");
        return fu.get();
    }
}
