# SimpleWebServer

模仿Tomcat结构做的简易Web服务器，以下是Tomcat的架构图：

[![QEXZS1.md.png](https://s2.ax1x.com/2019/11/30/QEXZS1.md.png)](https://imgchr.com/i/QEXZS1)

**Server:**

* Tomcat服务器的最顶层组件

* 负责运行Tomcat服务器

*  负责加载服务器资源和环境变量

**Service：**

* 包含了Connector和Engine的抽象组件
* 一个Server可以包含多个Service
* 一个Service可以包含多个Connector和一个Engine

**Connector和Processor组件：**

* Connecotr提供基于不同特定协议的实现
* Connector负责接受和解析请求，并返回响应结果
* Connector负责接受和解析请求后由Proccesor派遣请求至Engine进行处理

**Engin：**

* 容器是Tomcat用来处理请求的组件
* 容器内部的组件按照层级排列
* Engine是容器的顶层组件

**Host：**

* Host代表一个虚拟主机
* 一个Engine可以支持对多个虚拟主机的请求
* Engine通过解析请求来决定将请求发送给哪一个Host

**Context：**

* Tomcat中最复杂的组件之一
* 简单理解为Context代表一个Web Application
* 其职责包括应用资源管理(应用war包)，应用类加载，Servlet管理，安全管理等。

**Wrapper：**
* Wrapper是容器的最底层组件
* 负责管理Servelet实例的生命周期

项目实例主要精简了Engine以下的实例，把重点聚焦在了IO上，处理流程为：

​						**Connector -> Processor -> 交给相应的Servlet处理**





