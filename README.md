## JSON
现在和外部第三方的对接接口一般情况都是json对象传输。所以为了避免在代码里面出现到处的map。
1、构建对应的json
2、使用https://github.com/robohorse/RoboPOJOGenerator 去创建jackson pojo对象。

## @Import
可以在类上使用@Import(Class)把其他的@Configuration配置类进行合并

## 常用工具类
commons-io  IOUtils



### 代码提交流程
1. 使用安装好的git flow， start feature开始一个新功能的开发
2. 提交代码
3. 切换到develop分支，git pull代码
4. 切换到你的feature分支，merge develop分支，然后创建merge request

To start your application in the dev profile, simply run:

    ./mvnw


For further instructions on how to develop with JHipster, have a look at [Using JHipster in development][].

### 数据库
开发人员使用本地h2文件数据库，dev中的数据库连接配置如下：

```yaml
spring:
    datasource:
        type: com.zaxxer.hikari.HikariDataSource
        url: jdbc:h2:file:./build/h2db/db/bumblebee;DB_CLOSE_DELAY=-1
        username: sa
        password:
        hikari:
            auto-commit: false
    h2:
        console:
            enabled: true
        jpa:
            database-platform: io.github.jhipster.domain.util.FixedH2Dialect
            database: H2
            show-sql: true
            properties:
                hibernate.id.new_generator_mappings: true
                hibernate.connection.provider_disables_autocommit: true
                hibernate.cache.use_second_level_cache: true
                hibernate.cache.use_query_cache: false
                hibernate.generate_statistics: true
                hibernate.cache.region.factory_class: com.hazelcast.hibernate.HazelcastCacheRegionFactory
                hibernate.cache.hazelcast.instance_name: bumblebee
                hibernate.cache.use_minimal_puts: true
                hibernate.cache.hazelcast.use_lite_member: true
```

本地数据查看连接方式：
1. 通过浏览器查看
打开url：http://localhost:8080/h2-console
2. 按照上面的配置修改连接信息，可以连接查看数据库数据

## 导入jdl文件并生成实体及相关代码
1. 打开网站[jdl-studio] 编写实体描述文件。
2. 下载到本地，将jdl文件统一放到jdl目录下
3. 进入项目根目录，运行如下命令导入你的jdl文件 `jhipster import-jdl ./jdl/xxx.jh`
    > 需要注意: 在jh文件中需要添加如下内容
    > ```
    > dto * with mapstruct
    > service all with serviceImpl
    > skipClient entityName
    > ```
    > 一定要加注释
4. 执行过程中会有一些文件是否否覆盖的问询，一般输入`y`覆盖即可
5. 文件生成好后，还需判断生成实体中有哪些需要公共的审计字段，如果需要则需要手动修改实体类，继承类`AbstractAuditingEntity`；
   在生成好的实体对应的liquibase文件中添加公共字段，对应的内容为：
   xml
    <column name="created_by" type="varchar(50)">
        <constraints nullable="false"/>
    </column>
    <column name="created_date" type="timestamp" defaultValueDate="${now}">
        <constraints nullable="false"/>
    </column>
    <column name="reset_date" type="timestamp">
        <constraints nullable="true"/>
    </column>
    <column name="last_modified_by" type="varchar(50)"/>
    <column name="last_modified_date" type="timestamp"/>
 
    ```
 


## 如何在intellj IDEA上使用gitlab插件
1. 安装gitlab插件
2. 配置插件


## 创建merge request流程
1. 合并develop分支代码到你当前的开发分支
2. 如果有冲突，解决冲突
3. commit，push你的分支代码
4. 创建merge request


## intellij IDEA 需要开发人员安装的插件
1. gitlab
2. jira
3. sonarlint
4. 阿里巴巴java code guide

## 环境配置ssh互信
1. 本地环境运行ssh-keygen命令生成秘钥对，一般按默认选项连续按`enter`键即可
2. copy本地公钥文件内容 `~/.ssh/id_rsa.pub`
3. 将公钥粘贴至目标服务器的`~/.ssh/authorized_keys`中（可追加多个）
4. 在本地执行命令`ssh username@target_ip`，第一次执行会有提示，直接按`enter`键便可登录目标服务器

执行完以上操作后，便可以在本地免密`ssh`、`sftp`登录目标服务器，并且可以远程执行`scp`命令将文件或目录上传至目标服务器。
在项目中，我们将Jenkins打好的包上传至测试环境tomcat服务器就是用ssh互信和scp命令方式实现的。


## Rocket MQ环境
1. http://192.168.1.18:8080/#/topic
192.168.1.18:9876


## 如何使用Quartz
1. 在build.gradle 里面添加依赖 `compile("com.magfin:quartz:1.0")`
2. 需要在SpringBoot application类上添加如下的配置
    @Import(SchedulerConfig.class)
    @ComponentScan({"com.magfin.quartz"})
3. liquibase的changelog文件里添加
    `<include file="classpath:liquibase/quartz-master.xml"/>`
4. 添加一个java 类用　`@Configuration` 注解, 请参考　TriggerConfig.java
5. 在application.properties文件里添加你的cron 表达式, 请参考：[在线生成](http://www.jeasyuicn.com/cron/)
    public class Test2Job implements Job {
        private static final Logger LOG = LoggerFactory.getLogger(Test2Job.class);
        @Override
        public void execute(JobExecutionContext context) throws JobExecutionException {
            LOG.info("LK-PC005a0: execute test 2 job.");
        }
    }


## 报错内容 Command line is too long. Shorten command line for ServiceStarter or also for Application default configuration.

解决方法:

修改项目下 .idea\workspace.xml，找到标签 <component name="PropertiesComponent"> ， 在标签里加一行  <property name="dynamic.classpath" value="true" />


## Apollo 本地配置
1. 把config-cache目录放到C:\opt\data\bumblebee目录下
2. 设置环境变量ENV=Local， 或者在运行是加上-Denv=Local


## 延时任务 https://juejin.cn/post/6844904150703013901#heading-0

## Swagger3 使用 需要加入依赖
<dependency>
    <groupId>io.springfox</groupId>
    <artifactId>springfox-boot-starter</artifactId>
    <version>3.0.0</version>
</dependency>

访问链接：http://localhost:8080/springboot/swagger-ui/#/
找到 http://localhost:8080/springboot/v3/api-docs
导入Apifox：项目设置-> 数据管理->导入数据，URL导入 http://localhost:8080/springboot/v3/api-docs
在线分享：将接口文档以URL形式分享出去，方便外部团队在线查看
 
