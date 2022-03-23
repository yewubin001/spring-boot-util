package com.example.springboot.tools.menu;

import com.example.springboot.utils.JSONUtil;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: yewub
 * @Date: 2018/12/3 14:17
 * @Description: 菜单递归转成树形结构 json
 */
public class MenuService {

    /**
     * 根据角色查询菜单
     *
     * @param authority
     * @return
     */
    public static String getSysResourceByAuthority(String authority) {
        // 查出所有菜单记录
        List<SysResourceDTO> sysResourceDTOList = new ArrayList<>();
        init(sysResourceDTOList);
        // 一级菜单
        List<MenuResource> menuResources = new ArrayList<>();
        for (SysResourceDTO sysResourceDTO : sysResourceDTOList) {
            if (StringUtils.isEmpty(sysResourceDTO.getParentCode())) {
                MenuResource menuResource = new MenuResource();
                menuResource.setId(sysResourceDTO.getCode());
                menuResource.setIcon(sysResourceDTO.getIcon());
                menuResource.setSymbol(sysResourceDTO.getUrl());
                menuResource.setName(sysResourceDTO.getName());
                menuResources.add(menuResource);
            }
        }
        // 为一级菜单设置子菜单
        for (MenuResource menuResource : menuResources) {
            menuResource.setChild(getChildItem(menuResource.getId(), sysResourceDTOList));
            // 如果没有子菜单，则设置router
            if (menuResource.getChild() == null || menuResource.getChild().isEmpty()) {
                menuResource.setRouter(menuResource.getSymbol());
            }
        }
        return JSONUtil.toJson(menuResources);
    }

    /**
     * 子菜单递归
     *
     * @param code
     * @param sysResourceDTOs
     * @return
     */
    private static List<ChildItem> getChildItem(String code, List<SysResourceDTO> sysResourceDTOs) {
        // 子菜单
        List<ChildItem> childList = new ArrayList<>();
        for (SysResourceDTO sysResourceDTO : sysResourceDTOs) {
            ChildItem childItem = new ChildItem();
            // 遍历所有节点，将父菜单code与传过来的code比较
            if (code.equals(sysResourceDTO.getParentCode())) {
                childItem.setId(sysResourceDTO.getCode());
                childItem.setSymbol(sysResourceDTO.getUrl());
                childItem.setName(sysResourceDTO.getName());
                childList.add(childItem);
            }
        }

        // 递归查找子菜单
        for (ChildItem menu : childList) {
            menu.setChild(getChildItem(menu.getId(), sysResourceDTOs));
            // 如果没有子菜单，则设置router
            if (menu.getChild() == null || menu.getChild().isEmpty()) {
                menu.setRouter(menu.getSymbol());
            }
        }
        return childList;
    }


    public static void init(List<SysResourceDTO> sysResourceDTOList) {
        SysResourceDTO sysResourceDTO = new SysResourceDTO();

        sysResourceDTO.setId(1L);
        sysResourceDTO.setCode("1000000");
        sysResourceDTO.setName("渠道平台");
        sysResourceDTO.setParentCode(null);
        sysResourceDTO.setStatus(1);
        sysResourceDTO.setUrl("/channelPlatform");
        sysResourceDTOList.add(sysResourceDTO);

        SysResourceDTO sysResourceDTO2 = new SysResourceDTO();
        sysResourceDTO2.setId(2L);
        sysResourceDTO2.setCode("1001000");
        sysResourceDTO2.setName("渠道信息管理");
        sysResourceDTO2.setParentCode("1000000");
        sysResourceDTO2.setStatus(1);
        sysResourceDTO2.setUrl("/channelPlatform/channelManagement");
        sysResourceDTOList.add(sysResourceDTO2);

        SysResourceDTO sysResourceDTO3 = new SysResourceDTO();
        sysResourceDTO3.setId(3L);
        sysResourceDTO3.setCode("1001001");
        sysResourceDTO3.setName("资产方管理");
        sysResourceDTO3.setParentCode("1001000");
        sysResourceDTO3.setStatus(1);
        sysResourceDTO3.setUrl("/channelPlatform/channelManagement/assetManagement");
        sysResourceDTOList.add(sysResourceDTO3);
    }


    public static void main(String[] args) {
        String menuInfo = getSysResourceByAuthority("ROLE_USER");
        System.out.println(menuInfo);
    }
}
