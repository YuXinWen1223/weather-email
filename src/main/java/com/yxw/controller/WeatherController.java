package com.yxw.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yxw.entity.Weather;
import com.yxw.service.WeatherService;
import com.yxw.utils.Result;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 天气表 前端控制器
 * </p>
 *
 * @author 余欣文
 * @since 2022-10-12
 */
@RestController
@RequestMapping("/weather")
@Log4j2
public class WeatherController {
    @Resource
    private WeatherService weatherservice;
    @Value("${request.weather.addpid}")
    private String appId;
    @Value("${request.weather.appsecret}")
    private String appSecret;

    /**
     * 得到天气发送邮箱
     *
     * @return {@link Boolean}
     */
    @GetMapping("/list")
    public Boolean getWeather() {
        return weatherservice.sendSimpleMessage();
    }

    @GetMapping("/test")
    public void test() {
        log.warn("这是测试打印{}", appId);
        log.warn("这是测试打印{}", appSecret);
    }

    @PostMapping("/getListWeather/{current}/{limit}")
    public Result<?> getListWeather(@PathVariable Long current, @PathVariable Long limit) {
        //page分页
        Page<Weather> pageBanner = new Page<>(current, limit);
        //条件对象
        //根据什么进行查询
        QueryWrapper<Weather> wrapper = new QueryWrapper<>();
        //排序
        wrapper.orderByAsc("id");
        //调用方法实现多条件分页查询
        weatherservice.page(pageBanner, wrapper);
        //总记录数
        long total = pageBanner.getTotal();
        List<Weather> records = pageBanner.getRecords();
        return Result.ok().data("records", records).data("total", total);
    }

    @PostMapping("/addWeather")
    public Result<?> addWeather(@RequestBody Weather weather) {

        boolean result = getResult(weather);
        if (!result) {
            return Result.error().message("邮箱重复");
        }
        weatherservice.save(weather);
        return Result.ok().message("添加成功");

    }

    @PostMapping("/updateWeather")
    public Result<?> updateWeather(@RequestBody Weather weather) {

        QueryWrapper<Weather> wrapper = new QueryWrapper<>();
        wrapper.eq("id", weather.getId());
        Weather one = weatherservice.getOne(wrapper);
        if (one.getMail().equals(weather.getMail()) && one.getId().equals(weather.getId())) {
            weatherservice.updateById(weather);
            return Result.ok().message("修改成功");
        }

        boolean result = getResult(weather);
        if (!result) {
            return Result.error().message("邮箱重复");

        }
        weatherservice.updateById(weather);
        return Result.ok().message("修改成功");

    }

    @PostMapping("/updateState")
    public Result<?> updateState(@RequestBody Weather weather) {
        Weather w = new Weather();
        w.setId(weather.getId());
        if (weather.getIsDelete().equals(1)) {
            w.setIsDelete(0);
        }else {
            w.setIsDelete(1);
        }
        weatherservice.updateById(w);
        return Result.ok().message("修改成功");

    }

    private boolean getResult(Weather weather) {
        String mail = weather.getMail();
        QueryWrapper<Weather> wrapper = new QueryWrapper<>();
        wrapper.eq("mail", mail);
        int count = weatherservice.count(wrapper);
        return count < 1;
    }
}

