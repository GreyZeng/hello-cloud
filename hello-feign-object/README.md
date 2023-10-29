依次启动 hello-register, hello-provider, hello-consumer

访问：http://localhost:8011/swagger-ui.html

# Eureka

## InstanceStatus

> 用于标识服务实例的状态
> 服务实例主要有UP、DOWN、STARTING、OUT_OF_SERVICE、UNKNOWN这几个状态。
> 其中OUT_OF_SERVICE标识停止服务， 即停止接收请求，处于这个状态的服务实例将不会被路由到，经常用于升级部署的场景。

## LookupService

> 这个接口主要是给Client端用的，其定义了获取所有应用信息、根据应用id获取所有服务实例，以及根据visualHostname使用round-robin方式获取下一个服务实例的方法。

## 服务实例如何注册到注册中心

> 本质上就是在服务启动的时候，需要调用Eureka Server的REST API的register方法，去注册该应用实例的信息。对于使用Java的应用服务，
> 可以使用Netflix的Eureka Client封装的API去调用；
> 对于Spring Cloud的应用，可以使用spring-cloud-starter-netflix-eureka-client，基于Spring Boot的自动配置，自动帮你实现服务信息的注册。

## 服务实例如何从服务中心剔除

> 正常情况下服务实例在关闭应用的时候，应该通过钩子方法或其他生命周期回调方法去调用Eureka Server的REST API的de-register方法，来删除自身服务实例的信息。
> 另外为了解决服务实例挂掉或其他异常情况没有及时删除自身信息的问题，Eureka Server要求Client端定时进行续约，也就是发送心跳，来证明该服务实例还是存活的，是健康的，是可以调用的。
> 如果租约超过一定时间没有进行续约操作，Eureka Server端会主动剔除。这一点Eureka Server采用的就是分布式应用里头经典的心跳模式。

## 主从复制

> 主从复制也就是广为人知的Master-Slave模式，即有一个主副本，其他副本为从副本。
> 所有对数据的写操作都提交到主副本，最后再由主副本更新到其他从副本。具体更新的方式，还可以细分为同步更新、异步更新、同步及异步混合。
> 对于主从复制模式来讲，写操作的压力都在主副本上，它是整个系统的瓶颈，但是从副本可以帮主副本分担读请求。

## 对等复制

> 即Peer to Peer的模式，副本之间不分主从，任何副本都可以接收写操作，然后每个副本之间相互进行数据更新。
> 对于对等复制模式来讲，由于任何副本都可以接收写操作请求，不存在写操作压力瓶颈。但是由于每个副本都可以进行写操作处理，各个副本之间的数据同步及冲突处理是一个比较棘手的问题。
> 另外为了防止每个Client端都按配置文件指定的顺序进行请求造成Eureka Server节点请求分布不均衡的情况
> Client端有个定时任务（默认5分钟执行一次）来刷新并随机化Eureka Server的列表。
> Eureka Server本身依赖了Eureka Client，也就是每个Eureka Server是作为其他Eureka Server的Client。
> 在单个Eureka Server启动的时候，会有一个syncUp的操作，通过Eureka Client请求其他Eureka Server节点中的一个节点获取注册的应用实例信息，然后复制到其他peer节点。
> Eureka Server在执行复制操作的时候，使用HEADER_REPLICATION的httpheader来将这个请求操作与普通应用实例的正常请求操作区分开来。
> 通过HEADER_REPLICATION来标识是复制请求，这样其他peer节点接收到请求的时候，就不会再对它的peer节点进行复制操作，从而避免死循环。

## 数据复制冲突

Eureka Server由于采用了Peer to peer的复制模式，其重点要解决的另外一个问题就是数据复制的冲突问题。针对这个问题，Eureka采用如下两个方式来解决

- lastDirtyTimestamp标识
- heartbeat

针对数据的不一致，一般是通过版本号机制来解决，最后在不同副本之间只需要判断请求复制数据的版本号与本地数据的版本号高低就可以了。Eureka没有直接使用版本号的属性，而是采用一个叫作lastDirtyTimestamp的字段来对比。
如果开启SyncWhenTimestampDiffers配置（默认开启），当lastDirtyTimestamp不为空的时候，就会进行相应的处理：
如果请求参数的lastDirtyTimestamp值大于Server本地该实例的lastDirtyTimestamp值，则表示Eureka Server之间的数据出现冲突，这个时候就返回404，要求应用实例重新进行register操作。
如果请求参数的lastDirtyTimestamp值小于Server本地该实例的lastDirtyTimestamp值，如果是peer节点的复制请求， 则表示数据出现冲突，返回409给peer节点，要求其同步自己最新的数据信息。

peer节点之间的相互复制并不能保证所有操作都能够成功，因此Eureka还通过应用实例与Server之间的heartbeat也就是renewLease操作来进行数据的最终修复，即如果发现应用实例数据与某个Server的数据出现不一致，则Server返回404，应用实例重新进行register操作。

Eureka Client端与Server端之间有个租约，Client要定时发送心跳来维持这个租约，表示自己还存活着。Eureka通过当前注册的实例数，去计算每分钟应该从应用实例接收到的心跳数，
如果最近一分钟接收到的续约的次数小于等于指定阈值的话，则关闭租约失效剔除，禁止定时任务剔除失效的实例，从而保护注册信息。

# Feign

> Feign在默认情况下使用的是JDK原生的URLConnection发送HTTP请求，没有连接池，
> 但是对每个地址会保持一个长连接，即利用HTTP的persistence connection。
> 我们可以用httpclient或者okhttp替换Feign原始的HTTP Client，通过设置连接池、超时时间等对服务之间的调用调优。
> Spring Cloud从Brixtion.SR5版本开始支持这种替换。