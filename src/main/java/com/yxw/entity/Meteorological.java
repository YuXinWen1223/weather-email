package com.yxw.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author yuxw
 * @data 2024/05/11
 * @description 天气
 */
@NoArgsConstructor
@Data
public class Meteorological {

    @JsonProperty("cityid")
    private String cityid;
    @JsonProperty("date")
    private String date;
    @JsonProperty("week")
    private String week;
    @JsonProperty("update_time")
    private String updateTime;
    @JsonProperty("city")
    private String city;
    @JsonProperty("cityEn")
    private String cityEn;
    @JsonProperty("country")
    private String country;
    @JsonProperty("countryEn")
    private String countryEn;
    @JsonProperty("wea")
    private String wea;
    @JsonProperty("wea_img")
    private String weaImg;
    @JsonProperty("tem")
    private String tem;
    @JsonProperty("tem1")
    private String tem1;
    @JsonProperty("tem2")
    private String tem2;
    @JsonProperty("win")
    private String win;
    @JsonProperty("win_speed")
    private String winSpeed;
    @JsonProperty("win_meter")
    private String winMeter;
    @JsonProperty("humidity")
    private String humidity;
    @JsonProperty("visibility")
    private String visibility;
    @JsonProperty("pressure")
    private String pressure;
    @JsonProperty("air")
    private String air;
    @JsonProperty("air_pm25")
    private String airPm25;
    @JsonProperty("air_level")
    private String airLevel;
    @JsonProperty("air_tips")
    private String airTips;
    @JsonProperty("alarm")
    private AlarmDTO alarm;
    @JsonProperty("rain_pcpn")
    private String rainPcpn;
    @JsonProperty("uvIndex")
    private String uvIndex;
    @JsonProperty("uvDescription")
    private String uvDescription;
    @JsonProperty("wea_day")
    private String weaDay;
    @JsonProperty("wea_day_img")
    private String weaDayImg;
    @JsonProperty("wea_night")
    private String weaNight;
    @JsonProperty("wea_night_img")
    private String weaNightImg;
    @JsonProperty("sunrise")
    private String sunrise;
    @JsonProperty("sunset")
    private String sunset;
    @JsonProperty("hours")
    private List<HoursDTO> hours;
    @JsonProperty("aqi")
    private AqiDTO aqi;
    @JsonProperty("zhishu")
    private ZhishuDTO zhishu;

    @NoArgsConstructor
    @Data
    public static class AlarmDTO {
        @JsonProperty("alarm_type")
        private String alarmType;
        @JsonProperty("alarm_level")
        private String alarmLevel;
        @JsonProperty("alarm_title")
        private String alarmTitle;
        @JsonProperty("alarm_content")
        private String alarmContent;
    }

    @NoArgsConstructor
    @Data
    public static class AqiDTO {
        @JsonProperty("update_time")
        private String updateTime;
        @JsonProperty("cityid")
        private String cityid;
        @JsonProperty("city")
        private String city;
        @JsonProperty("cityEn")
        private String cityEn;
        @JsonProperty("country")
        private String country;
        @JsonProperty("countryEn")
        private String countryEn;
        @JsonProperty("air")
        private String air;
        @JsonProperty("air_level")
        private String airLevel;
        @JsonProperty("air_tips")
        private String airTips;
        @JsonProperty("pm25")
        private String pm25;
        @JsonProperty("pm25_desc")
        private String pm25Desc;
        @JsonProperty("pm10")
        private String pm10;
        @JsonProperty("pm10_desc")
        private String pm10Desc;
        @JsonProperty("o3")
        private String o3;
        @JsonProperty("o3_desc")
        private String o3Desc;
        @JsonProperty("no2")
        private String no2;
        @JsonProperty("no2_desc")
        private String no2Desc;
        @JsonProperty("so2")
        private String so2;
        @JsonProperty("so2_desc")
        private String so2Desc;
        @JsonProperty("co")
        private String co;
        @JsonProperty("co_desc")
        private String coDesc;
        @JsonProperty("kouzhao")
        private String kouzhao;
        @JsonProperty("yundong")
        private String yundong;
        @JsonProperty("waichu")
        private String waichu;
        @JsonProperty("kaichuang")
        private String kaichuang;
        @JsonProperty("jinghuaqi")
        private String jinghuaqi;
    }

    @NoArgsConstructor
    @Data
    public static class ZhishuDTO {
        @JsonProperty("chuanyi")
        private ChuanyiDTO chuanyi;
        @JsonProperty("daisan")
        private DaisanDTO daisan;
        @JsonProperty("ganmao")
        private GanmaoDTO ganmao;
        @JsonProperty("chenlian")
        private ChenlianDTO chenlian;
        @JsonProperty("ziwaixian")
        private ZiwaixianDTO ziwaixian;
        @JsonProperty("liangshai")
        private LiangshaiDTO liangshai;
        @JsonProperty("kaiche")
        private KaicheDTO kaiche;
        @JsonProperty("xiche")
        private XicheDTO xiche;
        @JsonProperty("lvyou")
        private LvyouDTO lvyou;
        @JsonProperty("diaoyu")
        private DiaoyuDTO diaoyu;

        @NoArgsConstructor
        @Data
        public static class ChuanyiDTO {
            @JsonProperty("level")
            private String level;
            @JsonProperty("tips")
            private String tips;
        }

        @NoArgsConstructor
        @Data
        public static class DaisanDTO {
            @JsonProperty("level")
            private String level;
            @JsonProperty("tips")
            private String tips;
        }

        @NoArgsConstructor
        @Data
        public static class GanmaoDTO {
            @JsonProperty("level")
            private String level;
            @JsonProperty("tips")
            private String tips;
        }

        @NoArgsConstructor
        @Data
        public static class ChenlianDTO {
            @JsonProperty("level")
            private String level;
            @JsonProperty("tips")
            private String tips;
        }

        @NoArgsConstructor
        @Data
        public static class ZiwaixianDTO {
            @JsonProperty("level")
            private String level;
            @JsonProperty("tips")
            private String tips;
        }

        @NoArgsConstructor
        @Data
        public static class LiangshaiDTO {
            @JsonProperty("level")
            private String level;
            @JsonProperty("tips")
            private String tips;
        }

        @NoArgsConstructor
        @Data
        public static class KaicheDTO {
            @JsonProperty("level")
            private String level;
            @JsonProperty("tips")
            private String tips;
        }

        @NoArgsConstructor
        @Data
        public static class XicheDTO {
            @JsonProperty("level")
            private String level;
            @JsonProperty("tips")
            private String tips;
        }

        @NoArgsConstructor
        @Data
        public static class LvyouDTO {
            @JsonProperty("level")
            private String level;
            @JsonProperty("tips")
            private String tips;
        }

        @NoArgsConstructor
        @Data
        public static class DiaoyuDTO {
            @JsonProperty("level")
            private String level;
            @JsonProperty("tips")
            private String tips;
        }
    }

    @NoArgsConstructor
    @Data
    public static class HoursDTO {
        @JsonProperty("hours")
        private String hours;
        @JsonProperty("wea")
        private String wea;
        @JsonProperty("wea_img")
        private String weaImg;
        @JsonProperty("tem")
        private String tem;
        @JsonProperty("win")
        private String win;
        @JsonProperty("win_speed")
        private String winSpeed;
        @JsonProperty("vis")
        private String vis;
        @JsonProperty("aqinum")
        private String aqinum;
        @JsonProperty("aqi")
        private String aqi;
    }
}
