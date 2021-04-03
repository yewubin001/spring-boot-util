package com.example.springboot.tools.debug;

/**
 * @Auther: 59315
 * @Date: 2021/4/2 22:00
 * @Description:
 */
public class IServiceImpl implements IService {


    @Override
    public void execute() {
        System.out.println("method executed");
    }
}
