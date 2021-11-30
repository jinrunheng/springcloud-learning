# Spring Cloud Learning

## Spring Cloud 及 Cloud Native 概述

### 1. 简单理解微服务

> 微服务就是一些协同工作的小而自治的服务 -- Sam Newman

#### 1.1 微服务的优点

- 异构性
  - 语言、存储 ... ...
- 弹性
  - 一个组件不可用，不会导致级联故障
- 扩展
  - 单体服务不易扩展，多个较小的服务可以按需扩展

- 易于部署
- 与组织结构对齐
- 可组合性
- 可替代性

#### 1.2 实施微服务的代价

- 分布式系统的复杂性
- 开发，测试等诸多研发过程中的复杂性
- 部署、监控等诸多运维复杂性
- ... ...

### 2. 如何理解云原生（Cloud Native）

> 云原生技术有利于各组织在公有云、私有云和混合云等新型动态环境中，构建和运行可弹性扩展的应用 -- CNCF Cloud Native Definition v1.0

#### 2.1 云原生应用要求

- DevOps

  开发与运维一同致力于交付高品质的软件服务于客户

- 持续交付

  软件的构建、测试和发布，要更快，更频繁，更稳定

- 微服务

  以一组小微服务的形式来部署应用

- 容器

  提供比传统虚拟机更高的效率

### 3. 12-Factor APP

#### 3. 1 THE TWELVE-FACTOR APP

目的：

- 为构建 SaaS 应用提供行之有效的方法论
- 适用于任意语言和后端服务的开发的应用程序
- https://12factor.net/zh_cn/

#### 3.2 了解 12-Factors

**基准代码（Codebase）**

- 一份基准代码，多份部署

**依赖（Dependencies）**

- 显示声明依赖关系

**配置（Config）**

- 在环境中存储配置

**后端服务（Backing services）**

- 把后端服务当作附加资源

**构建，发布，运行（Build，release，run）**

- 严格分离构建和运行

**进程（Processes）**

- 以一个或多个无状态进程运行应用

**端口绑定（Port Binding）**

- 通过端口绑定提供服务

**并发（Concurrency）**

- 通过进程模型进行扩展

**易处理（Disposability）**

- 快速启动和优雅种植可最大化健壮性

**开发环境与线上环境等价（Dev / Prod parity）**

- 尽可能地保持开发，预发布，线上环境相同

**日志（Logs）**

- 将日志当作事件流

**管理进程（Admin processes）**

- 后台管理任务当作一次性进程运行

#### 3.3 一份基准代码，多份部署

- 使用版本控制系统加以管理
- 基准代码与应用保持一一对应的关系
- 尽管每个应用只对应一份基准代码，但可以同时存在多分部署

#### 3.4 显示声明依赖关系

- 12-Factor 的应用程序不会隐式依赖系统级的类库
- 它一定通过依赖清单，确切地声明所有依赖项
- 在运行过程中，通过依赖隔离工具来确保程序不会调用系统中存在但清单中未声明的依赖项

#### 3.5 严格分离构建和运行

- 12-Factor 应用严格区分构建、发布、运行三个步骤
- 部署工具通常都提供了发布管理工具
- 每一个发布版本必须对应一个唯一的发布 ID

#### 3.6 以一个或多个无状态进程运行应用

- 12-Factor 应用的进程必须无状态且无共享
- 任何需要持久化的数据都要存储在后端服务内

#### 3.7 快速启动和优雅终止可最大化健壮性

- 进程应当追求最小启动时间
- 进程一旦接收终止信号就会优雅的终止
- 进程应当面对突然死亡时保持健壮性

#### 3.8 尽可能保持开发，预发布，线上环境相同

- 想要做到持续部署就必须缩小本地与线上差异
- 后端服务是保持开发与线上等价的重要部分
- 应该反对在不同环境间使用不同的后端服务

### 4. 认识 Spring Cloud 的组成部分

> Spring Cloud offers a simple and accessible programming model to the most common distributed system patterns, helping developers build resilient, reliable, and coordinated applications. -- Spring 官网

![Spring Microservices Online Sale, UP TO 51% OFF](https://tva1.sinaimg.cn/large/008i3skNgy1gwx93qn070j30tc0e2gmi.jpg)



#### 4.1 Spring Cloud 的主要功能

- 服务发现
- 服务网关
- 服务熔断
- 分布式消息
- 配置服务
- 分布式跟踪
- 服务安全（Spring Security）
- 各种云平台支持

#### 4.2 Spring Cloud 的版本号规则

- Spring Cloud 是一个大工程，其中包含多个独立项目
- BOM -Release Train
  - London Tube Stations
  - 字母排序
    - Greenwich，Finchley，Edgware
  - SR - Service Release

