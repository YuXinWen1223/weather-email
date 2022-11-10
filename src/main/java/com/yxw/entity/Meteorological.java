package com.yxw.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

/**
 * 气象
 *
 * @author yuxw
 * @date 2022/10/26
 */
@Data
@NoArgsConstructor
public class Meteorological {
    /**
     * 日期
     */
    private String date;
    /**
     * 星期
     */
    private String week;
    /**
     * 实时天气情况
     */
    private String wea;
    /**
     * 白天天气情况
     */
    private String weaDay;
    /**
     * 晚上天气情况
     */
    private String weaNight;
    /**
     * 当前温度
     */
    private String tem;
    /**
     * 高温
     */
    private String tem1;
    /**
     * 低温
     */
    private String tem2;
    /**
     * 湿度
     */
    private String humidity;
    /**
     * 能见度
     */
    private String visibility;
    /**
     * 气压
     */
    private String pressure;
    /**
     * 空气质量
     */
    private String airLevel;
    /**
     * 空气质量描述
     */
    private String airTips;
    /**
     * 日出
     */
    private String sunrise;
    /**
     * 日落
     */
    private String sunset;
    /**
     * 建议
     */
    private List<Index> index;
    /**
     * 天气预警
     */
    private Map<?, ?> alarm;
}
