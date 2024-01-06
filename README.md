OpenFeign：Spring Cloud声明式服务调用组件 https://c.biancheng.net/springcloud/open-feign.html

代码分支:Branche_OpenFeign

内容：
OpenFeign 实现远程服务调用
OpenFeign 超时控制
OpenFeign 日志增强

依次启动 
micro-service-cloud-eureka-7001
micro-service-cloud-provider-dept-8001/8002/8003（服务提供者集群）
micro-service-cloud-consumer-dept-feign（对比服务消费者micro-service-cloud-consumer-dept-80实现服务调用更加的简单）


注册中心:http://localhost:7001/

接口访问测试:
http://localhost:8000/consumer/dept/get/1
http://localhost:8000/consumer/dept/list
http://localhost:8000/consumer/dept/feign/timeout


