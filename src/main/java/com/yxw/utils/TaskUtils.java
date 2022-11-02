package com.yxw.utils;

import com.yxw.service.WeatherService;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;

import javax.annotation.Resource;

/**
 * @author : yuxinwen
 * @mingcheng : weather
 * @模块 : com.yxw.utils
 * @date :2022/11/2 9:32
 */
@Configuration
public class TaskUtils {
    @Resource
    private WeatherService weatherService;

    @Scheduled(cron = "0 0 07 * * ? ")
    public void doTask() {
        weatherService.sendSimpleMessage();
    }
}
