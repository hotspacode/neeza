# Simple-mock: 让MOCK变得更简单

## Introduction

基于ASM实现Java method mock，实现任意Java方法在线Mock

# Quick Start

- mvn clean package 
- -javaagent:simple-mock-agent/target/simple-mock-agent-1.0.1-SNAPSHOT.jar


# Plan

### 第一阶段
    - 确定项目目标，Organization名称（项目放到组织下），项目名称,gitpage
    - 申请Central - Maven Repository账号，发布第一个1.0.1-SNAPSHOT版本
    - mock agent
        - mock参数基于静态config对象配置
    - mock server
        - mock页面原形设计
        - 数据存储采用内存存储（抽象存储实现，未来加入文件存储、数据库存储、NoSql存储等等）
        - mock server区分app
        - 页面手动输入mock json
        - RMI通信
    
### 第二阶段
    - mock agent
        - mock参数支持jvm参数配置
        - mock参数支持动态修改
    - mock server
        - 数据存储支持文件存储
        - 支持集成，server可以集成到项目中，随着项目启动而启动，可以简化单独部署的问题
        
    - 文档
        - gitpage
        - wiki
    - 设计
        - logo
        
    - 方向
        - 做大做牛

### 第三阶段
    - mock agent
        - mock参数接入配置中心
    - mock server
        - 数据存储支持MySQL存储
        - 页面支持动态加载jar

### 第四阶段
    - mock agent
        - 启动Netty和服务通信
        - mock client自动汇报方法签名
        - mock client支持内存存储数据减少和server的通信
    - mock server
        - mock server支持推送服务mock
        - mock server和client实现心跳
        - mock server mock数据直接放到client内存


### 第五阶段
    - mock server
        - 支持restclient写法页面直接mock restful接口
 

# 当前问题
- spring bean
- 对象被重新加载后，对象属性值没了