Eureka：Spring Cloud服务注册与发现组件（集群配置）
https://c.biancheng.net/springcloud/eureka.html

代码分支:Branche_Eureka_Colony

host文件修改：
#Spring Cloud eureka 集群
127.0.0.1 eureka7001.com
127.0.0.1 eureka7002.com
127.0.0.1 eureka7003.com

默认情况下，Eureka 的自我保护机制是开启的，如果想要关闭，则需要在配置文件中添加以下配置。
eureka:
  server:
    enable-self-preservation: false # false 关闭 Eureka 的自我保护机制，默认是开启,一般不建议大家修改

测试步骤：	
7001 设置成关闭
7002、7003 按默认，开启

依次启动 
micro-service-cloud-eureka-7001/7002/7003
micro-service-cloud-provider-dept-8001

关闭 micro-service-cloud-provider-dept-8001	 等待几分钟	

访问“http://eureka7001.com:7001/	
在 DS Replicas 选项上面出现了红色警告信息“RENEWALS ARE LESSER THAN THE THRESHOLD. THE SELF PRESERVATION MODE IS TURNED OFF. THIS MAY NOT PROTECT INSTANCE EXPIRY IN CASE OF NETWORK/OTHER PROBLEMS.”，
出现该信息则表示 Eureka 的自我保护模式已关闭，且已经有服务被移除。
micro-service-cloud-provider-dept-8001 提供的服务已经从服务列表中移除

访问“http://eureka7002.com:7002/  或7003
在 DS Replicas 选项上面出现了红色警告信息“EMERGENCY! EUREKA MAY BE INCORRECTLY CLAIMING INSTANCES ARE UP WHEN THEY'RE NOT. RENEWALS ARE LESSER THAN THRESHOLD AND HENCE THE INSTANCES ARE NOT BEING EXPIRED JUST TO BE SAFE.”，
出现该信息表明 Eureka 的自我保护机制处于开启状态，且已经被触发。
micro-service-cloud-provider-dept-8001 的服务信息依然保存 Eureka Server 服务注册表中，并未被移除。


注册中心：
http://localhost:7001/ 或 http://eureka7001.com:7001/
http://localhost:7002/ 或 http://eureka7002.com:7002/
http://localhost:7003/ 或 http://eureka7003.com:7003/

接口访问测试:http://localhost:8001/dept/list


