# 线上购物商城-C1

---



### 一、demo结构说明：

1. application.yml文件用于配置数据库、端口等，需要修改以连接本地数据库

2. entity-实体类对应数据库表字段

   mapper-数据层

   controller-控制类调用接口

   sevice-服务接口

   serviceImpl-业务逻辑服务实现

   config-配置如拦截器等

   dto-简化实体，便于信息传输需要

   utils-工具类
  
### 二、注意事项：

1. service层中最好不要返回Result
2. 接口设计使用RESTful风格
3. 接口文档地址：/doc.html
