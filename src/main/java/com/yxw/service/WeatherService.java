package com.yxw.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yxw.entity.Weather;

/**
 * <p>
 * 天气表 服务类
 * </p>
 *
 * @author 余欣文
 * @since 2022-10-12
 */
public interface WeatherService extends IService<Weather> {
    /**
     * 发送简单信息
     *
     * @return boolean
     */
    boolean sendSimpleMessage();
}
