###集成验证框架
1.在pom.xml中引入相关jar包，不过web包里面包含了hibernatevalidation和api的支持，所以不用再引入
2.在待验证的实体里面添加相应的注解
3.在Controller添加相应的注解
4.做参数校验的工具类检验service层

###实现统一异常处理功能
1.实现异常处理类，并且用@ControllerAdvice修饰就可以了
2.@ControllerAdvice用来拦截@Controller或者@RestController抛出的异常
3.@ExceptionHanler用来指明捕捉到哪种异常
4.@ResponseBody将异常用json返回

###集成CaffeineCache缓存功能
1.
    @Cacheable:缓存数据，一般用在查询方法上。将查询道德数据进行缓存
    @CachePut:更新方法上，将数据从缓存中进行更新
    @CacheEvict：删除缓存
2.pom.xml Cache的相关jar包支持
3.CacheManager Bean配置类
4.使用注解，表示我们的方法哪些需要缓存

###集成Guava令牌桶实现全局限流
1.pom.xml引入Guava工具包的支持
2.定义一个拦截器，实现令牌的发放和获取
3.将拦截器配置到web系统中