package org.example.Demo.VO.Menu;


import lombok.Data;
import org.example.Demo.entity.Menu;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Illya
 * @Date: 2024/5/1 上午10:10
 */
@Data
public class MenuVO {
    private String name;
    private String url;

    public MenuVO toMenuVo(Menu menu) {
        MenuVO menuVO = new MenuVO();
        menuVO.setName(menu.getName());
        menuVO.setUrl(menu.getUrl());
        return menuVO;
    }

    public List<MenuVO> toMenuVoList(List<Menu> menuList) {
        List<MenuVO> menuVOList = new ArrayList<>();
        for (Menu menu : menuList) {
            menuVOList.add(toMenuVo(menu));
        }
        return menuVOList;
    }
}
