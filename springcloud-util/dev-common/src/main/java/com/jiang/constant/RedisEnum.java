package com.jiang.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

/**
 * @author jiangsj
 * @create 2023/5/31
 * @desc
 */
@ToString
@AllArgsConstructor
@Getter
public enum RedisEnum {

    /**
     * 常用地址
     */
    COMMON_ADDRESSES("commonAddresses"),

    /**
     * 轮播图片
     */
    CAROUSEL_PICTURE("carouselPicture"),

    /**
     * 搜索列表
     */
    SEARCH_NAVIGATION("searchNavigation"),

    /**
     * 导航地址
     */
    NAVIGATION_URL("navigationUrl");

    public final String value;
}
