package com.yxw.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author : yuxinwen
 * @mingcheng : demo1
 * @模块 : com.weather.entity
 * @date :2022/10/12 13:59
 */
@Data
@NoArgsConstructor
public class Index {
    /**
     * 标题
     */
    private String title;
    /**
     * 等级
     */
    private String level;
    /**
     * 建议
     */
    private String desc;

}
