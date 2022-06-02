package com.example.springboot.tools.controller;


import com.example.springboot.tools.json.CustomVO;
import com.example.springboot.utils.ExcelUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 用户信息表(EduEdmoPmsUser)表控制层
 *
 * @author makejava
 * @since 2018-08-08 16:14:25
 */
@RestController
@Api(tags = "用户信息")
public class EduEdmoPmsUserController {

    /**
     * @return
     */
    @ApiOperation("查询客户信息")
    @ApiImplicitParam(name = "name", value = "名字", dataType = "String")
    @GetMapping(value = "/customer")
    public CustomVO getCustom(@RequestParam("name") String name) {
        CustomVO customVO = new CustomVO();
        customVO.setMoney(new BigDecimal(200));
        customVO.setDate(new Date());
        System.out.println(customVO);
        return customVO;
    }

    /**
     * excel导出
     *
     * @return
     */
    @GetMapping("/excel-export")
    public ResponseEntity<FileSystemResource> exportExcel() {
        List<ExcelDataDTO> excelData = new ArrayList<>();
        return ExcelUtil.excelExport(excelData, ExcelDataDTO.class, "文件下载", "第一页");
    }

    /**
     * @return
     */
    @ApiOperation("保存客户信息")
    @PostMapping(value = "/save/customer")
    public void saveCustom(@RequestBody CustomVO customVO) {
        BigDecimal money = customVO.getMoney();
        money.add(BigDecimal.ZERO);
        System.out.println(customVO);
    }
}
