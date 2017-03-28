学习了一阵子的SSM框架，一直在各种博客，简书，慕课网学习，最后终于自己撸出来一个简单的个人相册。
***
项目的演示效果：
![个人相册.gif](http://upload-images.jianshu.io/upload_images/3435345-a4747ccc476e7c51.gif?imageMogr2/auto-orient/strip)
***
开发的工具及环境：
IntelliJ IDEA: 2016
Maven :3.0x
Hbuilder(前端部分，可以用记事本代替2333)
Java 8
***
项目流程（dao->service->web）：
1.添加所有依赖：
***
``` javascript
 <dependency>
            <groupId>junit</groupId>
            <artifactId>junit</artifactId>
            <version>4.11</version>
            <scope>test</scope>
        </dependency>
        <!--补全项目依赖-->
        <!--1.日志 java日志有:slf4j,log4j,logback,common-logging
            slf4j:是规范/接口
            日志实现:log4j,logback,common-logging
            使用:slf4j+logback
        -->
        <dependency>
            <groupId>org.slf4j</groupId>
            <artifactId>slf4j-api</artifactId>
            <version>1.7.12</version>
        </dependency>
        <dependency>
            <groupId>ch.qos.logback</groupId>
            <artifactId>logback-core</artifactId>
            <version>1.1.1</version>
        </dependency>
        <!--实现slf4j接口并整合-->
        <dependency>
            <groupId>ch.qos.logback</groupId>
            <artifactId>logback-classic</artifactId>
            <version>1.1.1</version>
        </dependency>
        <!--1.数据库相关依赖-->
        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
            <version>5.1.35</version>
            <scope>runtime</scope>
        </dependency>
        <dependency>
            <groupId>c3p0</groupId>
            <artifactId>c3p0</artifactId>
            <version>0.9.1.1</version>
        </dependency>
        <!--2.dao框架:MyBatis依赖-->
        <dependency>
            <groupId>org.mybatis</groupId>
            <artifactId>mybatis</artifactId>
            <version>3.3.0</version>
        </dependency>
        <!--mybatis自身实现的spring整合依赖-->
        <dependency>
            <groupId>org.mybatis</groupId>
            <artifactId>mybatis-spring</artifactId>
            <version>1.2.3</version>
        </dependency>
        <!--3.Servlet web相关依赖-->
        <dependency>
            <groupId>taglibs</groupId>
            <artifactId>standard</artifactId>
            <version>1.1.2</version>
        </dependency>
        <dependency>
            <groupId>jstl</groupId>
            <artifactId>jstl</artifactId>
            <version>1.2</version>
        </dependency>
        <dependency>
            <groupId>com.fasterxml.jackson.core</groupId>
            <artifactId>jackson-databind</artifactId>
            <version>2.5.4</version>
        </dependency>
        <dependency>
            <groupId>javax.servlet</groupId>
            <artifactId>javax.servlet-api</artifactId>
            <version>3.1.0</version>
        </dependency>
        <!--4:spring依赖-->
        <!--1)spring核心依赖-->
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-core</artifactId>
            <version>4.1.7.RELEASE</version>
        </dependency>
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-beans</artifactId>
            <version>4.1.7.RELEASE</version>
        </dependency>
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-context</artifactId>
            <version>4.1.7.RELEASE</version>
        </dependency>
        <!--2)spring dao层依赖-->
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-jdbc</artifactId>
            <version>4.1.7.RELEASE</version>
        </dependency>
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-tx</artifactId>
            <version>4.1.7.RELEASE</version>
        </dependency>
        <!--3)springweb相关依赖-->
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-web</artifactId>
            <version>4.1.7.RELEASE</version>
        </dependency>
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-webmvc</artifactId>
            <version>4.1.7.RELEASE</version>
        </dependency>
        <!--4)spring test相关依赖-->
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-test</artifactId>
            <version>4.1.7.RELEASE</version>
        </dependency>
        <!--添加redis依赖-->
        <dependency>
            <groupId>redis.clients</groupId>
            <artifactId>jedis</artifactId>
            <version>2.7.3</version>
        </dependency>
        <!--prostuff序列化依赖-->
        <dependency>
            <groupId>com.dyuproject.protostuff</groupId>
            <artifactId>protostuff-core</artifactId>
            <version>1.0.8</version>
        </dependency>
        <dependency>
            <groupId>com.dyuproject.protostuff</groupId>
            <artifactId>protostuff-runtime</artifactId>
            <version>1.0.8</version>
        </dependency>
        <dependency>
            <groupId>commons-fileupload</groupId>
            <artifactId>commons-fileupload</artifactId>
            <version>1.3.1</version>
        </dependency>
```
***
2.添加Mybatis的配置文件：
``` javascript
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE configuration
        PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-config.dtd">

<configuration>
    <!--配置全局属性-->
    <settings>
        <!--使用jdbc的getGeneratekeys获取自增主键值-->
        <setting name="useGeneratedKeys" value="true"/>
        <!--使用列别名替换列名　　默认值为true
        select name as title(实体中的属性名是title) form table;
        开启后mybatis会自动帮我们把表中name的值赋到对应实体的title属性中
        -->
        <setting name="useColumnLabel" value="true"/>

        <!--开启驼峰命名转换Table:create_time到 Entity(createTime)-->
        <setting name="mapUnderscoreToCamelCase" value="true"/>
    </settings>
</configuration>
```
这里最好去官网看最新配置文件的头配置<strong>http://www.mybatis.org/mybatis-3/zh/index.html</strong>
***
然后编写dao层的代码：
相册实体类
``` javascript
public interface PictureDao {
    /**
     * @return 返回所有图片
     */
    List<Picture> getAllPictures();

    /**上传图片，并且将图片名，图片描述信息插入数据库
     * @param picName
     * @param content
     * @return插入成功返回1，失败0
     */
    int InsertPicture(@Param("picName") String picName, @Param("content") String content);
}
``` 
用户实体类
``` javascript
public interface UserDao {
    /**如果查询到该用户就会返回1
     * @param username,pwd
     * @return数据库被修改的行数
     */
    User getUserByName(@Param("username") String username, @Param("pwd") String pwd);
}

```
实体类创建好，我们就在resource文件夹下创建一个mapper文件夹，放我们dao层的映射文件。
UserDao.xml
``` javascript
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper
        PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="com.koali.dao.UserDao">
<select id="getUserByName" resultType="com.koali.entity.User" >
    SELECT * FROM USER WHERE username=#{username} AND pwd=#{pwd}
</select>
</mapper>
``` 
PictureDao.xml
``` javascript
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper
        PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="com.koali.dao.PictureDao">
    <select id="getAllPictures" resultType="com.koali.entity.Picture">
        SELECT * FROM PICTURE
    </select>

    <insert id="InsertPicture">
        INSERT INTO `picture` (`picname`,`content`) VALUES (#{picName},#{content})
    </insert>
</mapper>
</mapper>
``` 
最后整合到Spring里面。所以我再次在resource文件夹下创建一个spring文件夹，并且创建一个文件名为：
spring-dao.xml
``` javascript
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
        http://www.springframework.org/schema/beans/spring-beans.xsd
        http://www.springframework.org/schema/context
        http://www.springframework.org/schema/context/spring-context.xsd">
    <!--配置整合mybatis过程
   1.配置数据库相关参数-->
    <context:property-placeholder location="classpath:jdbc.properties"/>

    <!--2.数据库连接池-->
    <bean id="dataSource" class="com.mchange.v2.c3p0.ComboPooledDataSource">
        <!--配置连接池属性-->
        <property name="driverClass" value="${jdbc.driver}"/>

        <!-- 基本属性 url、user、password -->
        <property name="jdbcUrl" value="${jdbc.url}"/>
        <property name="user" value="${jdbc.username}"/>
        <property name="password" value="${jdbc.password}"/>

        <!--c3p0私有属性-->
        <property name="maxPoolSize" value="30"/>
        <property name="minPoolSize" value="10"/>
        <!--关闭连接后不自动commit-->
        <property name="autoCommitOnClose" value="false"/>

        <!--获取连接超时时间-->
        <property name="checkoutTimeout" value="1000"/>
        <!--当获取连接失败重试次数-->
        <property name="acquireRetryAttempts" value="2"/>
    </bean>

    <!--约定大于配置-->
    <!--３.配置SqlSessionFactory对象-->
    <bean id="sqlSessionFactory" class="org.mybatis.spring.SqlSessionFactoryBean">
        <!--往下才是mybatis和spring真正整合的配置-->
        <!--注入数据库连接池-->
        <property name="dataSource" ref="dataSource"/>
        <!--配置mybatis全局配置文件:mybatis-config.xml-->
        <property name="configLocation" value="classpath:mybatis-config.xml"/>
        <!--扫描entity包,使用别名,多个用;隔开-->
        <property name="typeAliasesPackage" value="com.elric.entity"/>
        <!--扫描sql配置文件:mapper需要的xml文件-->
        <property name="mapperLocations" value="classpath:mapper/*.xml"/>
    </bean>

    <!--４:配置扫描Dao接口包,动态实现DAO接口,注入到spring容器-->
    <bean class="org.mybatis.spring.mapper.MapperScannerConfigurer">
        <!--注入SqlSessionFactory-->
        <property name="sqlSessionFactoryBeanName" value="sqlSessionFactory"/>
        <!-- 给出需要扫描的Dao接口-->
        <property name="basePackage" value="com.koali.dao"/>
    </bean>
</beans>
``` 
因为spring-dao.xml里面有些属性要连接到我们的数据库，所以我们把我们的数据库的连接驱动，用户名什么鬼都写在一个叫
jdbc.properties
``` javascript
jdbc.driver=com.mysql.jdbc.Driver
jdbc.url=jdbc:mysql://localhost:3306/picture?useUnicode=true&characterEncoding=utf-8
jdbc.username=Elric
jdbc.password=881010
``` 
dao层编写结束（表示写blog比敲代码还累23333）！
***
3.编写Service层
因为这是个小Demo（博主刚学不久，还是一只小菜鸡）。所以Service的实现大抵跟dao差不多。
先写两个Service接口：
UserService
``` javascript
public interface UserService {
    /**本次中我们只需要对用户身份做出判断然后给予url
     * @return 数据库查询到为1
     */
    User CheckUser(String username, String pwd);
}
``` 
PictureService
``` javascript
public interface PictureService {
    /**查询所有照片
     * @return 所有照片
     */
    List<Picture> getAllPicture();

    /**
     * 这个服务就是PictureDao中的InsertP
     * @param picName
     * @param content
     * @return 数据库成功返回1，失败返回0
     */
    int InsertPicture(String picName, String content);
}
```
然后再写两个实现Service接口的实现类：
PictureServiceImpl
 ``` javascript
@Service
public class PictureServiceImpl implements PictureService {
    @Autowired
    private PictureDao pictureDao;

    public List<Picture> getAllPicture() {
        return pictureDao.getAllPictures();
    }
    public int InsertPicture(String picName, String content) {
        return pictureDao.InsertPicture(picName,content);
    }
}
```
UserServiceImpl 
PictureServiceImpl
 ``` javascript
@Service
public class UserServiceImpl implements com.koali.service.UserService {
    @Autowired
    private UserDao userDao;
    public User CheckUser(String username, String pwd) {
        return userDao.getUserByName(username,pwd);
    }
}
```
然后写配置文件：
在resource中的spring文件夹下创建spring-service.xml
spring-service.xml
 ``` javascript
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context" xmlns:tx="http://www.springframework.org/schema/tx"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
        http://www.springframework.org/schema/beans/spring-beans.xsd
        http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context.xsd http://www.springframework.org/schema/tx http://www.springframework.org/schema/tx/spring-tx.xsd">
    <!--扫描service包下所有使用注解的类型-->
    <context:component-scan base-package="com.koali.service"/>
    <!--配置事务管理器-->
    <bean id="transactionManager" class="org.springframework.jdbc.datasource.DataSourceTransactionManager">
        <!--注入数据库连接池-->
        <property name="dataSource" ref="dataSource"/>
    </bean>
    <!--配置基于注解的声明式事务
    默认使用注解来管理事务行为-->
    <tx:annotation-driven transaction-manager="transactionManager"/>
 ``` 
到此Service层就写好了，这个比较简单。
****
3.web层的编写：
现在web.xml添加spring-mvc的前端控制器：
 ``` javascript
    <servlet>
    <servlet-name>seckill-dispatcher</servlet-name>
    <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
    <init-param>
        <param-name>contextConfigLocation</param-name>
        <param-value>classpath:spring/spring-*.xml</param-value>
    </init-param>
    </servlet>
    <servlet-mapping>
        <servlet-name>seckill-dispatcher</servlet-name>
        <!--默认匹配所有请求-->
        <url-pattern>/</url-pattern>
    </servlet-mapping>
<!--去除乱码的过滤器-->
    <filter>
        <filter-name>characterEncodingFilter</filter-name>
        <filter-class>org.springframework.web.filter.CharacterEncodingFilter</filter-class>
        <init-param>
            <param-name>encoding</param-name>
            <param-value>UTF-8</param-value>
        </init-param>
        <init-param>
            <param-name>forceEncoding</param-name>
            <param-value>true</param-value>
        </init-param>
    </filter>
    <filter-mapping>
        <filter-name>characterEncodingFilter</filter-name>
        <url-pattern>/*</url-pattern>
    </filter-mapping>
 ``` 
然后在resourced的spring文件夹创建spring-web.xml
spring-web.xml
 ``` javascript
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xmlns:mvc="http://www.springframework.org/schema/mvc"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context.xsd http://www.springframework.org/schema/mvc http://www.springframework.org/schema/mvc/spring-mvc.xsd">
    <!--指明controller所在包，并扫描其中的注解-->
    <context:component-scan base-package="com.koali.web"/>
    <!--静态资源（js,image等）的访问-->
    <mvc:default-servlet-handler/>
    <!--开启注解-->
    <mvc:annotation-driven/>
    <!--ViewResolver视图解析器-->
    <!--用于支持Servlet，JSP视图解析-->
    <bean id="jspViewResolver" class="org.springframework.web.servlet.view.InternalResourceViewResolver">
        <property name="viewClass" value="org.springframework.web.servlet.view.JstlView"/>
        <property name="prefix" value="/WEB-INF/jsp/"/>
        <property name="suffix" value=".jsp"/>
    </bean>
    <!--200*1024*1024即200M resolveLazily属性启用是为了推迟文件解析，以便捕获文件的异常-->
    <bean id="multipartResolver" class="org.springframework.web.multipart.commons.CommonsMultipartResolver">
        <property name="maxUploadSize" value="209715200" />
        <property name="resolveLazily" value="true" />
        <property name="defaultEncoding" value="UTF-8"></property>
    </bean>
    <mvc:resources location="/WEB-INF/jsp/css/" mapping="/css/**" />
    <mvc:resources location="/WEB-INF/jsp/fonts/" mapping="/fonts/**" />
    <mvc:resources location="/WEB-INF/jsp/images/" mapping="/images/**" />
    <mvc:resources location="/WEB-INF/jsp/js/" mapping="/js/**"/>
</beans>
 ``` 
最后编写我们的前端控制器：
MainController
 ``` javascript
@Controller
public class MainController {
    @Autowired
    private PictureService pictureService;
    @Autowired
    private UserService userService;
    @RequestMapping(value = "/")
    public String index(Model model){
        List<Picture> pictures =pictureService.getAllPicture();
        System.out.println(pictures.size());
        model.addAttribute("pictures",pictures);
        return "index";
    }
    @RequestMapping(value = "login")
    public String login(){
        return "login";
    }
    @RequestMapping(value = "checkandRedict")
    public String checkAndRedict(@Param("username") String username,@Param("pwd") String pwd){
        User user = userService.CheckUser(username,pwd);
        System.out.println(user);
        if (user!=null){
            return "upload";
        }else {
            return "index";
        }
    }
    @RequestMapping(value = "upload",method = RequestMethod.POST)
    public String upload(@RequestParam("file") MultipartFile file,@Param("content") String content, HttpServletRequest request,Model model) throws IOException{
        //获取项目的根路径，将上传图片的路径与我们的资源路径在一起，才能显示
        HttpSession session= request.getSession();
        String path = session.getServletContext().getRealPath("/");
        System.out.println("getRealPath('/'):"+path);
        int end = path.indexOf("t",19);
        String prePath = path.substring(0,end);
        String realPath = prePath+"target\\demo\\WEB-INF\\jsp\\images";
        System.out.println("DEBUG:"+realPath);
        String picName = new Date().getTime()+".jpg";
        if (!file.isEmpty()){
            FileUtils.copyInputStreamToFile(file.getInputStream(),new File(realPath,new Date().getTime()+".jpg"));
        }else if(content==null){
            content = "";//如果输入为null数据库不允许插入
        }
        //图片类的名字保存为路径+名字方便后期前端提取
        //将图片名字用时间戳保存，反正上传图片为中文乱码等问题
       int code =  pictureService.InsertPicture("images/"+picName,content);
        if (code==1) {
            List<Picture> pictures = pictureService.getAllPicture();
            model.addAttribute("pictures", pictures);
            return "index";
        }else
            return "index";
    }
}
 ``` 
至此项目就到此为止！
***
期间遇到好多好多坑，比如mybatis无端端就连不上，或者莫名其妙扫描不到我的Spring配置文件，可是我在我IDEA明明ctrl点击找的到，反正很多很多，有些解决了，有些还是不懂，不过以后慢慢接触应该就会越来越熟悉！
后面的计划应该会给项目加Shiro用户提权，虽然我的也是要登陆才能上传图片，所以我不太清楚Shiro对我好像用处不大，不过还是学一学，加进去。然后再学点高并发并且加进去，虽然如果我搭建了我这个项目到我的服务器，也不可能有很多人同时访问这个网站，但是高并发这种东西还是要学学的。
最后献上我的项目的地址:<strong>https://github.com/Elricyo/SSM</strong>
