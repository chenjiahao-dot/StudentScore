package com.example.Context;



import com.example.Util.MenuVO;

import org.example.Demo.Enummerate.DirectionEnum;
import org.example.Demo.Enummerate.PrimaryRoleEnum;
import org.example.Demo.entity.User.UserData;

import java.util.List;
import java.util.Map;

/**
 * @author Illya
 */
public class BaseContext {


    //当前用户ID
    public static ThreadLocal<Long> currentId = new ThreadLocal<>();

    //当前用户主角色
    public static ThreadLocal<PrimaryRoleEnum> currentPrimaryRoleEnum = new ThreadLocal<>();

    //当前用户所有方向
    public static ThreadLocal<List<DirectionEnum>> currentDirectionList = new ThreadLocal<>();
    //当前用户的菜单列表
    public static ThreadLocal<Map<String, List<MenuVO>>> currentMenuMap = new ThreadLocal<>();

    public static ThreadLocal<String> currentName = new ThreadLocal<>();



    public static Long getCurrentId() {
        return currentId.get();
    }

    public static void setCurrentId(Long id) {
        currentId.set(id);
    }
    public static void remove() {
        currentId.remove();
    }
    public static void removeCurrentId() {
        currentId.remove();
    }

    public static PrimaryRoleEnum getCurrentPrimaryRoleEnum() {
        return currentPrimaryRoleEnum.get();
    }

    public static void setUCurrentPrimaryRoleEnum(PrimaryRoleEnum primaryRoleId) {
        currentPrimaryRoleEnum.set(primaryRoleId);
    }

    public static void removeCurrentPrimaryRoleEnum() {
        currentPrimaryRoleEnum.remove();
    }

    public static List<DirectionEnum> getCurrentDirectionList() {
        return currentDirectionList.get();
    }

    public static void setCurrentDirectionList(List<DirectionEnum> directionEnumList) {
        currentDirectionList.set(directionEnumList);
    }

    public static void removeCurrentDirectionList() {
        currentDirectionList.remove();
    }





    public static String getCurrentName() {
        return currentName.get();
    }

    public static void setCurrentName(String userName) {
        currentName.set(userName);
    }

    public static void removeCurrentName() {
        currentName.remove();
    }


}
