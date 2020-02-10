# 备忘
- [done]去core  ，瘦身agent, 走其他方式
- [done]MockSpy 加break
- [done]校验和其他字节码框架有没有冲突，理论上是有的，走visitLocalVariable这种方式操作局部变量表最安全(有顾虑验证)
- 未来agent生效的情况才执行所有init操作
- 预留invoke设计思路
- 善用设计模式解决问题
- 方法调用缓存内容使用SoftReference存储
- 徽章生成[https://shields.io/](https://shields.io/)
- [仓库申请](https://issues.sonatype.org/browse/OSSRH-53417)

# 2019年11月11日计划
- web IDE页面开发
    - 可参考
        - [nocode](https://liugq5713.github.io/vue-element-nocode-admin/#/form)
- 通信neeza-transport-netty-http通用实现 
- 存储neeza-store-mysql（JPA）通用实现实现
- neeza-server-standalone restful接口定义 
    - 获取project列表
    - 按照树形的方式返回project对应的所有的资源
    - 按照className获取所有资源详情
    - 根据descriptor获取资源的操作历史
    - 提交mock
- 数据对象设计
    - 每个资源定义为一个Entity，每个资源可以关联多个Rule
- 抽象接口定义设计
    - 存储spi api定义
    - spy和server通信spi api定义
# todo
- MockSpyService类里面的FastJSONSerialization和TransportClient使用单例模式或者可配置加载
- [done]日志加入