package com.example.springboot.tools.controller;


import com.example.springboot.tools.json.CustomVO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * 用户信息表(EduEdmoPmsUser)表控制层
 *
 * @author makejava
 * @since 2018-08-08 16:14:25
 */
@RestController
public class EduEdmoPmsUserController {

    /**
     * @return
     */
    @GetMapping(value = "/customer")
    public CustomVO getCustom() {
        CustomVO customVO = new CustomVO();
        customVO.setMoney(new Double(200));
        customVO.setDate(new Date());
        System.out.println(customVO);
        return customVO;
    }

}