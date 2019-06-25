## JSON
现在和外部第三方的对接接口一般情况都是json对象传输。所以为了避免在代码里面出现到处的map。
1、构建对应的json
2、使用https://github.com/robohorse/RoboPOJOGenerator 去创建jackson pojo对象。

## @Import
可以在类上使用@Import(Class)把其他的@Configuration配置类进行合并

## 常用工具类
commons-io  IOUtils
