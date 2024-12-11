<h1 align="center">Friend-Matchmaking</h1>
<p align="center"><strong>伙伴匹配系统</strong></p>



## 需求分析

1. 用户去给自己添加交友标签

2. 主动搜索：允许用户根据标签去搜索其他用户
   1. Redis缓存

3. 组队功能：
   1. 队伍增删改查
   2. 根据标签查询队伍
   3. 邀请其他人

4. 允许用户修改标签

5. 推荐
   1. 相似度计算 + 本地分布式计算




## 技术栈

### 前端

1. Vue3 提高开发效率
2. Vant UI 
3. Vite 提高打包效果
4. Nginx单机部署



### 后端

1. Java语言 + SpringBoot框架
2. SpringMVC + MyBatis + MyBatisPlus
3. MySql
4. Redis
5. Swagger + Knife4j 接口文档