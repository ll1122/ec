/**
 * Copyright (c) 2012-2024 https://www.eryansky.com
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.eryansky.modules.sys.utils;

import com.eryansky.common.spring.SpringContextHolder;
import com.eryansky.common.utils.StringUtils;
import com.eryansky.modules.sys.mapper.Area;
import com.eryansky.modules.sys.service.AreaService;

/**
 * @author Eryan
 * @date 2016-05-12
 */
public class AreaUtils {

    private AreaUtils(){}

    /**
     * 静态内部类，延迟加载，懒汉式，线程安全的单例模式
     */
    public static final class Static {
        private static final AreaService areaService = SpringContextHolder.getBean(AreaService.class);
        private Static(){}
    }

    /**
     * @param areaId 区域ID
     * @return
     */
    public static Area get(String areaId) {
        if (StringUtils.isNotBlank(areaId)) {
            return Static.areaService.get(areaId);
        }
        return null;
    }


    /**
     * @param code 区域编码
     * @return
     */
    public static Area getByCode(String code) {
        if (StringUtils.isNotBlank(code)) {
            return Static.areaService.getByCode(code);
        }
        return null;
    }

    /**
     * @param bizCode 信息分类编码
     * @return
     */
    public static Area getByBizCode(String bizCode) {
        if (StringUtils.isNotBlank(bizCode)) {
            return Static.areaService.getByBizCode(bizCode);
        }
        return null;
    }


    /**
     * 查找名称
     *
     * @param areaId 区域ID
     * @return
     */
    public static String getAreaName(String areaId) {
        Area area = get(areaId);
        if (area != null) {
            return area.getName();
        }
        return null;
    }

    /**
     * 查找名称
     *
     * @param areaCode 区域编码
     * @return
     */
    public static String getAreaNameByAreaCode(String areaCode) {
        Area area = getByCode(areaCode);
        if (area != null) {
            return area.getName();
        }
        return null;
    }


    /**
     * 查找名称
     *
     * @param areaBizCode 区域信息分类编码
     * @return
     */
    public static String getAreaNameByAreaBizCode(String areaBizCode) {
        Area area = getByBizCode(areaBizCode);
        if (area != null) {
            return area.getName();
        }
        return null;
    }
}
