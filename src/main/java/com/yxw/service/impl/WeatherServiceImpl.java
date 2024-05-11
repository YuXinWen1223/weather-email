package com.yxw.service.impl;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yxw.entity.Meteorological;
import com.yxw.entity.Weather;
import com.yxw.mapper.WeatherMapper;
import com.yxw.service.WeatherService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import javax.mail.internet.MimeMessage;
import java.util.List;

/**
 * <p>
 * 天气表 服务实现类
 * </p>
 *
 * @author 余欣文
 * @since 2022-10-12
 */
@Log4j2
@Service
public class WeatherServiceImpl extends ServiceImpl<WeatherMapper, Weather> implements WeatherService {
    @Value("${request.weather.qq}")
    private String fromMail;
    @Value("${request.weather.addpid}")
    private String appId;
    @Value("${request.weather.appsecret}")
    private String appSecret;
    private static final String WEA = "雨";

    @Resource
    private WeatherService service;
    @Resource
    private JavaMailSender emailSender;

    String city;

    /**
     * 发送简单信息
     *
     * @return boolean
     */
    @Override
    public boolean sendSimpleMessage() {
        QueryWrapper<Weather> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("is_delete", 1);
        List<Weather> weatherList = service.list(queryWrapper);
        for (Weather weather : weatherList) {
            Meteorological meteorological = fetchWeather(weather);
            if (meteorological != null) {
                sendEmail(weather, meteorological);
                log.info("{}邮件已发送", weather.getName());
            }
        }
        return true;
    }

    private void sendEmail(Weather weather, Meteorological meteorological) {
        try {
            MimeMessage message = emailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo(weather.getMail());
            helper.setFrom(fromMail);
            helper.setSubject(weather.getTitle());
            helper.setText(buildHtml(meteorological, weather), true);
            emailSender.send(message);
        } catch (Exception e) {
            log.error("邮件发送失败：");
            log.error(e.getMessage(), e);
        }
    }

    private Meteorological fetchWeather(Weather weather) {
        try {
            HttpRequest httpRequest = HttpUtil.createGet("https://v1.yiketianqi.com/api?unescape=1&version=v62&appid=" + appId + "&appsecret=" + appSecret + "&cityid=" + weather.getCityId());
            String response = httpRequest.execute().body();
            Meteorological meteorological = JSON.parseObject(response, Meteorological.class);
            return meteorological;
        } catch (Exception e) {
            log.error("获取天气信息失败：");
            log.error(e.getMessage(), e);
            return null;
        }
    }

    /**
     * @param meteorological 气象
     * @param list           列表
     * @return {@link String }
     * @author yuxw
     * @data 2024/05/11
     * @description 构建 天气HTML
     */
    private String buildHtml(Meteorological meteorological, Weather list) {
        StringBuilder html = new StringBuilder();
        html.append("<!DOCTYPE html>\n" + "<html>\n" + "<head>\n" + "<meta charset=\"utf-8\">\n" + "<title>文档标题</title>\n" + "</head><body>");
        html.append("<div class=\"table-wrapper\">");
        if (meteorological != null && meteorological.getWea().contains(WEA)) {
            html.append("<h1 style=\"color: red;\">").append(list.getPrompt()).append("</h1>");
        }
        if (meteorological != null && !StringUtils.isEmpty(meteorological.getAirTips())) {
            html.append("<h2>").append(meteorological.getCity()).append("今日").append("<span style=\"color: #8F4586;\">").append(meteorological.getWea()).append("，").append(meteorological.getAirLevel()).append("</span>").append("，").append(meteorological.getAirTips()).append("</h2>");
            html.append("<h2>").append(meteorological.getCity()).append("今日").append("<span style=\"color: #8F4586;\">").append(meteorological.getAqi().getKouzhao()).append("</span>").append("，").append(meteorological.getAqi().getYundong()).append("<span style=\"color: #8F4586;\">").append("，").append(meteorological.getAqi().getWaichu()).append("</span>").append("，").append(meteorological.getAqi().getKaichuang()).append("</h2>");
        } else {
            html.append("<h2>").append(meteorological != null ? meteorological.getCity() : "").append("今日").append("</span>").append("空气质量").append("<span style=\"color: #8F4586;\">").append("<span style=\"color: #8F4586;\">").append("，").append(meteorological != null ? meteorological.getWea() : "").append("</span>").append("</h2>");
        }
        if (meteorological != null) {
            Meteorological.AlarmDTO alarm = meteorological.getAlarm();
            if (alarm != null) {
                html.append("预警类型：<span style=\"color: red;\">").append(alarm.getAlarmType()).append("</span>&nbsp&nbsp");
                html.append("预警等级：<span style=\"color: red;\">").append(alarm.getAlarmLevel()).append("</span>&nbsp&nbsp<br>");
                html.append("预警标题：<span style=\"color: red;\">").append(alarm.getAlarmTitle()).append("</span>&nbsp&nbsp<br>");
                html.append("预警详情描述：<span style=\"color: red;\">").append(alarm.getAlarmContent()).append("</span>&nbsp&nbsp");
            }
            html.append("<h3 style=\"color: #00EC00;\">----------气温状况----------</h3>");
            html.append("最高温度：<span style=\"color: violet;\">").append(meteorological.getTem1()).append("</span>&nbsp&nbsp");
            html.append("最低温度：<span style=\"color:red;\">").append(meteorological.getTem2()).append("</span>&nbsp&nbsp");
            html.append("当前温度：<span style=\"color:green;\">").append(meteorological.getTem()).append("</span><br>");
            html.append("白天天气：<span style=\"color: #0000FF;\">").append(meteorological.getWeaDay()).append("</span>&nbsp&nbsp");
            html.append("夜间天气：<span style=\"color: #9900FF;\">").append(meteorological.getWeaNight()).append("</span>&nbsp&nbsp");
            html.append("空气湿度：<span style=\"color: color:#5C3317;\">").append(meteorological.getHumidity()).append("</span><br>");
            html.append("浓见度：<span style=\"color: #6A5ACD;\">").append(meteorological.getVisibility()).append("</span>&nbsp&nbsp");
            html.append("日出时间：<span style=\"color:red;\">").append(meteorological.getSunrise()).append("</span>&nbsp&nbsp");
            html.append("日落时间：<span style=\"color: #9ACD32;\">").append(meteorological.getSunset()).append("</span>&nbsp&nbsp");
            html.append("<h3 style=\"color: #00EC00;\">----------今日建议----------</h3>");
            Meteorological.ZhishuDTO zhishu = meteorological.getZhishu();
            if (zhishu != null) {
                html.append(buildAdviceTable(zhishu));
            }
        }
        html.append("</table></body>" + "</html>");
        return html.toString();
    }

    /**
     * @param zhishu 天气指数
     * @return {@link String }
     * @author yuxw
     * @data 2024/05/11
     * @description 天气指数建议表格
     */
    private String buildAdviceTable(Meteorological.ZhishuDTO zhishu) {
        StringBuilder html = new StringBuilder();
        html.append("<table class=\"fl-table\"><tr><th><b style=\"color: #804040;\">名称标题</b></th><th><b style=\"color: #804040;\">建议</b></th><th><b style=\"color: #804040;\">原因</b></th></tr>");
        Meteorological.ZhishuDTO.ChenlianDTO chenlian = zhishu.getChenlian();
        if (!StringUtils.isEmpty((chenlian.getLevel()))) {
            html.append("<tr><td>").append("晨练指数").append("</td><td>").append("<span style=\"color: #81C0C0;\">").append(chenlian.getLevel()).append("</span>").append("</td><td>").append(chenlian.getTips()).append("</td><td>").append("</td><td>").append("</td></tr>");
        }
        Meteorological.ZhishuDTO.DaisanDTO daisan = zhishu.getDaisan();
        if (!StringUtils.isEmpty((daisan.getLevel()))) {
            html.append("<tr><td>").append("带伞指数").append("</td><td>").append("<span style=\"color: #81C0C0;\">").append(daisan.getLevel()).append("</span>").append("</td><td>").append(daisan.getTips()).append("</td><td>").append("</td><td>").append("</td></tr>");
        }
        Meteorological.ZhishuDTO.ChuanyiDTO chuanyi = zhishu.getChuanyi();
        if (!StringUtils.isEmpty((chuanyi.getLevel()))) {
            html.append("<tr><td>").append("穿衣指数").append("</td><td>").append("<span style=\"color: #81C0C0;\">").append(chuanyi.getLevel()).append("</span>").append("</td><td>").append(chuanyi.getTips()).append("</td><td>").append("</td><td>").append("</td></tr>");
        }
        Meteorological.ZhishuDTO.KaicheDTO kaiche = zhishu.getKaiche();
        if (!StringUtils.isEmpty(kaiche.getLevel())) {
            html.append("<tr><td>").append("开车指数").append("</td><td>").append("<span style=\"color: #81C0C0;\">").append(kaiche.getLevel()).append("</span>").append("</td><td>").append(kaiche.getTips()).append("</td><td>").append("</td><td>").append("</td></tr>");
        }
        Meteorological.ZhishuDTO.GanmaoDTO ganmao = zhishu.getGanmao();
        if (!StringUtils.isEmpty((ganmao.getLevel()))) {
            html.append("<tr><td>").append("感冒指数").append("</td><td>").append("<span style=\"color: #81C0C0;\">").append(ganmao.getLevel()).append("</span>").append("</td><td>").append(ganmao.getTips()).append("</td><td>").append("</td><td>").append("</td></tr>");
        }
        Meteorological.ZhishuDTO.DiaoyuDTO diaoyu = zhishu.getDiaoyu();
        if (!StringUtils.isEmpty(diaoyu.getLevel())) {
            html.append("<tr><td>").append("钓鱼指数").append("</td><td>").append("<span style=\"color: #81C0C0;\">").append(diaoyu.getLevel()).append("</span>").append("</td><td>").append(diaoyu.getTips()).append("</td><td>").append("</td><td>").append("</td></tr>");
        }
        Meteorological.ZhishuDTO.LiangshaiDTO liangshai = zhishu.getLiangshai();
        if (!StringUtils.isEmpty(liangshai.getLevel())) {
            html.append("<tr><td>").append("晾晒指数").append("</td><td>").append("<span style=\"color: #81C0C0;\">").append(liangshai.getLevel()).append("</span>").append("</td><td>").append(liangshai.getTips()).append("</td><td>").append("</td><td>").append("</td></tr>");
        }
        Meteorological.ZhishuDTO.XicheDTO xiche = zhishu.getXiche();
        if (!StringUtils.isEmpty(xiche.getLevel())) {
            html.append("<tr><td>").append("洗车指数").append("</td><td>").append("<span style=\"color: #81C0C0;\">").append(xiche.getLevel()).append("</span>").append("</td><td>").append(xiche.getTips()).append("</td><td>").append("</td><td>").append("</td></tr>");
        }
        Meteorological.ZhishuDTO.LvyouDTO lvyou = zhishu.getLvyou();
        if (!StringUtils.isEmpty(lvyou.getTips())) {
            html.append("<tr><td>").append("旅游指数").append("</td><td>").append("<span style=\"color: #81C0C0;\">").append(lvyou.getLevel()).append("</span>").append("</td><td>").append(lvyou.getTips()).append("</td><td>").append("</td><td>").append("</td></tr>");
        }
        Meteorological.ZhishuDTO.ZiwaixianDTO ziwaixian = zhishu.getZiwaixian();
        if (!StringUtils.isEmpty(ziwaixian.getLevel())) {
            html.append("<tr><td>").append("紫外线指数").append("</td><td>").append("<span style=\"color: #81C0C0;\">").append(ziwaixian.getLevel()).append("</span>").append("</td><td>").append(ziwaixian.getTips()).append("</td><td>").append("</td><td>").append("</td></tr>");
        }
        return html.toString();
    }
}
