# weather-email

#### 介绍

这是一个发送邮箱的小项目，每天七点会发送当天天气情况，支持多人不同地方的天气发送。

#### 使用技术
后端：SpringBoot+MySQL+MyBatis-Plus

前端：Vue2+Element-UI+Axios
#### 效果图

<img alt="img.png" src="img.png"/>


####  操作步骤:

**1. 进入自己QQ邮箱网页版点击账户找到如下图，把申请的密钥记下来：**

<img alt="img_1.png" src="img_1.png"/>

**2. 进入 https://www.tianqiapi.com/user/login 注册登录申请ID密钥如下图：**

<img alt="img_2.png" src="img_2.png"/>

**3. 导入数据库添加用户信息，数据库添加的信息对应邮件如下图：**

<img alt="img_3.png" src="img_3.png"/>

**4. 在application.properties文件中填写你申请的密钥，天气ID**

<img alt="img_4.png" src="img_4.png"/>

**5. 启动SpringBoot，访问 http://localhost:8085/weather.html 进行信息添加**

<img alt="img_5.png" src="img_5.png"/>
