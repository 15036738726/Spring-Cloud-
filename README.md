Config：Spring Cloud分布式配置组件 https://c.biancheng.net/springcloud/config.html
代码分支:Branche_Config

搭建 Config 服务端
搭建 Config 客户端
手动刷新配置
Config+Bus 实现配置的动态刷新

Spring Cloud Config
Spring Cloud Config 是由 Spring Cloud 团队开发的项目，它可以为微服务架构中各个微服务提供集中化的外部配置支持。

简单点说就是，Spring Cloud Config 可以将各个微服务的配置文件集中存储在一个外部的存储仓库或系统（例如 Git 、SVN 等）中，对配置的统一管理，以支持各个微服务的运行。
 
Spring Cloud Config 包含以下两个部分：
Config Server：也被称为分布式配置中心，它是一个独立运行的微服务应用，用来连接配置仓库并为客户端提供获取配置信息、加密信息和解密信息的访问接口。
Config Client：指的是微服务架构中的各个微服务，它们通过 Config Server 对配置进行管理，并从 Config Sever 中获取和加载配置信息。

Spring Cloud Config 默认使用 Git 存储配置信息，因此使用 Spirng Cloud Config 构建的配置服务器天然就支持对微服务配置的版本管理。我们可以使用 Git 客户端工具方便地对配置内容进行管理和访问。
除了 Git 外，Spring Cloud Config 还提供了对其他存储方式的支持，例如 SVN、本地化文件系统等

Spring Cloud Config 工作流程如下：
开发或运维人员提交配置文件到远程的 Git 仓库。
Config 服务端（分布式配置中心）负责连接配置仓库 Git，并对 Config 客户端暴露获取配置的接口。
Config 客户端通过 Config 服务端暴露出来的接口，拉取配置仓库中的配置。
Config 客户端获取到配置信息，以支持服务的运行。

启动：
注册中心，micro-service-cloud-config-center-3344
访问github上的文件信息：
http://localhost:3344/main/config-dev.yml


配置更新后，Spring Cloud Config 服务端（Server）可以直接从 Git 仓库中获取最新的配置。（无重启）
除非重启 Spring Cloud Config 客户端（Client），否则无法通过 Spring Cloud Config 服务端获取最新的配置信息

手动刷新配置，需要借助curl命令行工具

Config+Bus 实现配置的动态刷新
Spring Cloud Bus 又被称为消息总线，它能够通过轻量级的消息代理（例如 RabbitMQ、Kafka 等）将微服务架构中的各个服务连接起来，实现广播状态更改、事件推送等功能，还可以实现微服务之间的通信功能。
目前 Spring Cloud Bus 支持两种消息代理：RabbitMQ 和 Kafka。


利用 Spring Cloud Bus 实现配置的动态刷新需要以下步骤:
当 Git 仓库中的配置发生改变后，运维人员向 Config 服务端发送一个 POST 请求，请求路径为“/actuator/refresh”。
Config 服务端接收到请求后，会将该请求转发给服务总线 Spring Cloud Bus。
Spring Cloud Bus 接到消息后，会通知给所有 Config 客户端。
Config 客户端接收到通知，请求 Config 服务端拉取最新配置。
所有 Config 客户端都获取到最新的配置。