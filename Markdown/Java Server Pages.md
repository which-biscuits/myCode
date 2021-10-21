# 环境配置

## Servlet & JSP

Servlet 是一个Java类 , 它可以动态地处理请求并作出响应

JSP页面是一个基于文本的文档 , 它以servlet 的方式执行 , 但是它可以更方便建立动态内容

## Tomcat

### 	目录


> bin : 工具及其启动程序目录</br>
conf : Tomcat的各种配置文件, 最重要的是 server.xml </br>
lib : API 目录</br>
logs : 运行日志目录</br>
webapps : 默认网站部署目录</br>
work : 网站运行目录</br>

### 	配置

> conf/server.xml : 服务器的主配置文件</br>
conf/web.xml : Web应用程序的文件(缺省的Servlet,JSP,MIME类型定义) </br>
conf/tomcat-user.xml : tomcat用户的信息(用于权限与安全) </br>


### 	主要配置参数

> port : 定义TCP/IP端口号 默认为8080 </br>
maxThreads : 设置处理客户请求的线程的最大数目,这个值也决定了服务器可以同时响应请求的最大数目,默认值是200 </br>
enableLookups : 如果为true表示支持域名解析, 可以把IP地址解析为主机名</br>
redirectPort : 指定转发端口.如果当前端口只支持non-SSL请求,在需要安全通信的场合,将把客户请求转发到基于	  SSL的rediectPort端口</br>
acceptCount : 设定在监听端队列中的最大客户请求数,如果队列已满,客户请求将被拒绝</br>
connectionTimeout : 定义建立客户连接超时的时间,以毫秒为单位,如果设置为-1,表示不限制建立客户连接的时间

```jsp
修改HTTP服务端口
<Connector port="8080" protocol="HTTP/1.1" connectionTimeout="20000" redirectPort="8443"/>
```


### 		设置Web服务目录

- 部署到根目录

```jsp
%TOMCAT_HOME%\webapps\Root
```

- Web apps下的Web服务目

```jsp
Tomcat服务器安装目录webapps下的任何一个子目录都可以作为一个web服务目录.
可新建子目录
```

- 新建Web服务目录

```xml
用记事本打开conf文件夹中的主配制文件 server.xml 在</Host>的前面加入
<Contextpath="/apple" docBase="D:\MyBook\zhang" debug="0" reloadable="true"/>
保存并重启Tomcat服务器
```

***

# 注释

```html
<!-- 注释内容 -->	// HTML文档注释
<%-- 注释内容 -->	// JSP文档注释
// ...			  // Java单行文档注释
/* ... */		  // Java多行文档注释
```



# Java Server Pages

## JSP页面基本结构

### 	元素组成

```jsp
1.普通的HTML标签
2.JSP标记,如指令标记 / 动作标记
3.变量和方法的声明
4.Java程序片
5.Java表达式
```

### 	运行原理

```jsp
JSP页面中普通的HTML标记符号,交给客户的浏览器执行显示
JSP标记,变量和方法声明,Java程序片由Tomcat服务器负责执行,将需要显示的结果发送给客户的浏览器
Java表达式由Tomcat服务器负责计算,将结果转化为字符串,交给客户的浏览器负责显示
```

## 变量 & 方法 & 类

```jsp
<%! ... %>
```

### 	变量

变量的类型可以是Java语言允许的任何数据类型, 这些变量称为 JSP 页面的 **成员变量**
		声明的变量在整个JSP页面内都有效

**用户共享 JSP 页面的成员变量**	多线程,由Tomcat 服务器来管理

```jsp
<%!
    int a,b=10,c;	// 生成Servlet类的成员变量
    String tom = null,jerry = "love JSP";
    Date date;
%>

<%
    int a,b,c;	// 生成Servlet类的_jspService方法的局部变量
%>
```

### 	方法和类

在<%! ... %>标记符号内**定义的方法**在整个JSP页面有效, 可以在Java程序片中被调用

在<%! ... %>标记符号内**定义的类**在整个JSP页面有效, 可以在Java程序片中使用该类创建对象

```jsp
<%!
    String getDate() {	// 定义方法
        return java.util.Date().toLocalString();
    }
    class Cat extends Animal {
        private int age;
    }
%>
```

### 	synchronized 关键字

用于修饰JSP页面中定义的方法

当一个线程在调用由 其 修饰的方法时,其他调用该方法的线程必须进入等待 , 同一时间只允许一个线程进行操作		可用于实现 防止多线程同时修改成员变量 ，产生负面影响

## Java 程序片

在`<% ... %>`之间插入Java程序片	**合法的java代码**

程序片中声明的变量称为JSP页面的 **局部变量** , 在 JSP 页面后续的所有程序片和表达式中有效

**不同线程中的局部变量互不影响**

## Java 表达式

在`<%= ... %>`之间插入一个表达式 , 必须能求值.表达式由服务器计算出结果后以**字符串**的形式发送给用户端显示

```jsp
<%= getDate()%>
```

## JSP 指令标记

### 	page 指令标记

page指令用来定义整个JSP页面的一些属性和这些属性的值, 属性值用单引号或双引号括起来

page指令与其书写位置无关, 习惯上把page指令写在JSP页面的最前面

```jsp
<%@ page porperty1="value" porperty2="value"%>
```

#### 	contentType 属性

确定JSP页面响应的MIME类型和JSP页面字符的编码

如果不使用page指令为contentType指定一个值,那么contentType默认是"text/html ; charset=ISO-8859-1"

不允许在同一文档中两次使用page指令给contentType属性指定不同的属性值

```jsp
<%@ page contentType="application/msword" %>
<%@ page contentType="application/msword; charset=utf-8" %>
```

#### 	language 属性

定义JSP页面使用的脚本语言, 该属性的值目前只能取 "java"

```java
<%@ page language="java"%>
```

#### 	import 属性

为JSP页面引入Java运行环境提供的包中的类

```jsp
<%@page import="java.io.*,java.util.Date" %>
默认引入 :
"java.lang.*, javax.servlet.*, javax.servlet.jsp.*, javax.servlet.http.*
```

#### 	session 属性

用于设置是否需要内置的 session 对象

属性值可以为 true / false	默认值为 true

#### 	buffer 属性

用来指定out设置的缓冲区的大小或不使用缓冲区

```jsp
<%@page buffer="24kb" %>	<%-- 默认值为8kb 	none 代表不设置缓冲区 --%>
```

#### 	autoFlush 属性

用于指定out的缓冲区被填满时,缓冲区是否自动刷新	默认为 true

当设置为false 时, 如果out的缓冲区填满, 就会出现缓存溢出异常. 当 buffer 的值是 none 时, autoFlush 的值就不能设置为 false

#### 	isThreadSafe 属性

用来设置JSP页面是否可多线程访问	true / false	默认为 true

#### 	info 属性

设置一个字符串, 为JSP页面准备一个常用且可能要经常修改的字符串

```jsp
<%@ page info="we are students" %>
<% 
	String s = getServletInfo();	// 获取 info 属性的属性值
%>
```

### include 指令标记

在JSP页面出现该指令的位置处,静态插入一个文件,实现代码的复用

一个JSP页面中的include指令的数量不受限制

**静态插入** 当前JSP页面和插入的文件合并为一个新的JSP页面,然后JSP引擎再将这个新的页面转译成Java文件

```jsp
<%@ include file="文件的URL" %>
```

## JSP 动作标记

### include 动作标记

告诉页面动态包含一个文件, 即JSP页面运行时才将文件加入

```jsp
<jsp:include page="文件的URL" />		// 无子标记
<jsp:include page="文件的URL" >
    param子标记
</jsp:include>
```

如果是普通的文本文件,就将文件内容发送到客户端,由浏览器负责显示

如果是JSP文件,JSP引擎就执行这个文件,然后将执行的结果发送到用户端浏览器显示

### param 动作标记

以 `name-value` 对的形式为其他标记提供附加信息

与`jsp:include`动作标记一起使用时,可以将param标记中的值传递到include动作标记要加载的文件中去,被加载的JSP文件可以使用Tomcat服务器提供的request内置对象获取include动作标记的param子标记中name属性所提供的值

```jsp
<jsp:param name="name" value="value" />
```

### forward 动作标记

从该指令处停止当前页面的执行,而转向执行page属性指定的JSP页面

可使用param动作标记作为子标记传送信息,要转向的JSP页面用request内置对象获取param子标记中的name属性所提供的值

**跳转后,浏览器地址栏URL保持不变**

```jsp
<jsp:forward page="要转向的页面"/>
<jsp:forward page="要转向的页面">
    param子标记
</jsp:forwawrd>
```

### useBean 动作标记

用来创建,并使用一个Javabean

id	表示实例	scope	表示此对象的使用范围

```jsp
<jsp:useBean id="id" scope="page|request|session|application" typeSpec/>
```

### setProperty 动作标记

与useBean协同使用,用来设置Bean的简单属性和索引属性

`<jsp:setProoerty>`标签使用Bean给定的setXXX()方法,在Bean中设置一个或多个属性值

利用`<jsp:setProoerty>`设置属性值有多种方法

```jsp
<jsp:setProperty name="beanName" propertyDetails/>
```

### getProperty 动作标记

对 jsp:setProperty 操作的补充,用来访问一个Bean的属性

```jsp
<jsp:getProperty name="userSession" property="name"/>
```

## Java Bean

Java Bean 的核心技术是内省(introspection), 内省就是通过分析Bean确定Bean的功能, 这是Java Bean API的本质特征

### 属性

#### 简单属性

简单属性具有单一性

```java
读属性方法:	public T getN() {...}
写属性方法:	public void setN(T obj) {...}
N : 属性名称		T : 属性类型
```

#### 索引属性

索引属性包含多个值

```java
获取某个值:	public T getN(int index) {...}
设置某个值:	public void setN(int index,T obj) {...}
获取所有值:	public T[] getN() {...}
设置所有值:	public void setN(T[] objs) {...}
N : 属性名称		T : 属性类型
```

### JSP:useBean 基本属性

```jsp
<jsp:useBean id="Bean_name" class="beans" scope=""/>

id :	Bean 对象标识 变量名称
class :	Bean 类名称(全名,包含包名)
scope :	Bean 作用范围, 默认page
		page : 能在当前JSP文件和所有静态包含文件的总使用Bean, 直到页面执行完毕向客户端发回响应或转到另一个文件
		request : 有效范围仅限于使用JavaBean的请求
		session : 有限范围在用户整个连接过程中 (整个会话阶段均有效)
		application : 有效范围涵盖整个应该程序,也就是对整个网站均有效
type :	Bean 类型, 可以是本类, 或其父类或实现的接口, 默认为本类
```

### JavaBean语法特点

> 对于boolean类型的成员变量,即布尔逻辑类型的属性,允许使用"is"代替上面的"get"和"set"
>
> 类中声明的方法的访问属性都必须是public的
>
> 类中声明的构造方法必须是public&无参数的

## JSP 内置对象

JSP 内置对象可以直接使用, **不需要显示声明, 也不需要实例化**

内部对象只对**Java程序片** 和 **Java表达式**有用, 在声明中不能使用

### 	request 对象

封装了用户提交的信息, 调用对象相应的方法可以获取封装的信息

#### 		1.获取用户提交信息

用户通常使用HTML表单向服务器的某个JSP页面提交信息

```JSP
HTML提交:
<form action="JSP页面" method="get/post">
    <input name="input1" value="" />	// 必须拥有 input 属性
    提交手段
</form>
JSP获取:
String s = request.getParameter("input1")	// 通过name属性获取
```

#### 		2.处理汉字信息

避免 request 对象获取的信息出现乱码

```java
对信息从新编码:
String str = request.getParameter("message");
byte b[] = str.getBytes("IOS-8859-1");
str = new String(b);

request 设置编码:
request.setCharacterEncoding("gb2312");
```

#### 		3.常用方法

```jsp
getProtocol() :		获取客服向服务器传送数据所使用的通信协议和它的版本号	HTTP/1.14
getServerName() :	获得接受请求的服务器主机名
getServerPort() :	获得服务器主机的端口号
getServerHost() :	获得客户机的全名, 如果名字获取不得, 则获得客户机的IP地址
getRemoteAddr() :	获得发送请求的客户机的IP地址
getMethod() :		获得客户提交信息方式, 如 get post put等
getServletPath() :	获得客户请求的JSP页面的文件目录
getContentLength() :	获得客户提交信息长度, 以字节为单位
getHeader(String name) :	获得HTTP头文件中由参数 name 指定的头名字的值
getHeaderNames() :	获得客户请求中所有头部域的名字
getPathInfo() :		获得客户请求时关联到URL的附加路径信息, 没有此信息则返回空值
getCookies() :		返回客户端的Cookies对象, 结果是一个Cookies数组. 无则返回空值
getRequestURL() :	获得发出请求字符串的客户端地址
getParameter(String name) :	获得客户提交给服务器的name参数
getParameterNames() :	获得客户提交给服务器的所有参数名
getParamrterValues(String name) :	获得指定参数所有值 
```

#### 		4.处理HTML标记

```html
<form> 标记
```

### 	response 对象

response 对象对客户的请求做出响应, 向客户端发送数据

#### 		1.动态响应contentType属性

```java
response.setContentType(String s);
当用setContentType()方法动态改变了contentType的属性值, JSP引擎就会按照新的MIME类型将JSP页面的输出结果返回给客户
```

#### 		2.response 的HTTP头

```java
动态添加新的响应头和头的值,存在则覆盖值
addHeader(String head,String value);
setHeader(String head,String value);
```

#### 		3.response 重定向

```java
response.sendRedirect(URL url);
```

#### 4.response 的状态行

当服务器对用户请求进行响应时,,它发送的首行称为状态行

```jsp
设置状态行内容:
response.setStatus(int n);

状态码 Status:
1xx : 实验性质
2xx : 表示请求成功 eg. 200
3xx : 表示请求满足前应采取进一步行动
4xx : 表示无法满足请求	eg. 404/请求页面不存在
5xx : 表示服务器出现问题  eg. 505/表示服务器内部发生问题
```

### session 对象

**HTTP**为无状态协议, 服务器端不会保留连接的有关信息, 因此当下一次连接时, 服务器无法判断这一次的连接和以前的连接是否属于同一用户

#### 1.session对象ID

用户在访问一个Web服务目录期间, 服务器为该用户分配一个session对象 (称作用户的会话), 服务器可以在各个页面使用这个session记录当前用户的有关信息

该对象对应一个String类型的ID号, 服务器在响应客户请求的同时,把ID号发送到客户端, 并写入客户端的cookies中

当用户关闭浏览器后,一个会话结束,服务器端该用户的session对象被取消. 当用户重新打开浏览器建立新连接时,JSP引擎为客户再创建一个新的session对象

#### 2.session对象与URL重写

如果用户不支持Cookie,JSP页面可以通过URL重写来实现session对象的唯一性

URL重写即向用户请求的URL中添加参数,把session对象的id传带过去

```java
String str = response.encodeRedirectURL("second.jsp");
String str = response.encodeURL("second.jsp");
然后将连接目标写成 <%= str%>
```

#### 3.session对象存储数据

session对象驻留在服务器端,通过调用某些方法保存用户在访问某个web服务目录期间的有关数据

```java
数据处方法:
public void setAttribute(String key,Object obj);	// 添加指定对象参数 obj,并指定索引关键字 key
public Object getAttribute(string key);		// 获取参数对象 索引关键字为 key
public Enumeration getAttributeNames();		// 产生枚举对象 遍历 索引关键字
public void removeAttribute(String name);		// 移除 索引关键字对应的对象
```

#### 4,session对象的生命周期

session对象的生存期限依赖于session对象是否调用`invalidate()`方法使得**session无效**或session对象达到了设置的最长"发呆"状态时间以及是否关闭服务器

当关闭服务器时,用户的session消失

"发呆"状态时间是指用户对某个Web服务目录发出的两次请求之间的间隔时间(默认为发呆时间是30min)

```xml
Tomcat /conf/web.xml
<session-config>
    <session-timeout>30</session-timeout>
</session-config>

JSP :
session.setMaxInactiveInterval(int interval);		// 设置最长发呆时间 / 秒
session.getMaxInactiveInterval();	// 获取最长发呆时间 / 秒
```

####  5.session对象的特点

内置对象session由Tomcat服务器负责创建, session是实现了HttpSession接口类的一个实例

session对象被分配了一个String 类型的ID, Tomcat 服务器将ID发送到客户端,存放在客户的Cookie中

同一个用户在同一个Web服务目录中的各个页面的session是相同的

不同用户的session对象互不相同,具有不同的ID

Session生命周期: 关闭浏览器; 调用invalidate()方法; 超过最长的"发呆时间"

### application 对象

由多个客户端用户共享

服务器启动后,新建一个对应Web服务目录的application对象,该对象一旦建立,就一直保持到服务器关闭

每个Web服务目录下的application对象被访问该服务目录的所有用户共享, 但不同Web服务目录下的application互不影响

#### 1.application 对象的常用方法

```java
public void setAttribute(String key, Object obj);	// 将指定的对象 obj 添加到 application 对象中 索引关键字 key
public Object getAttribute(String key);		// 获取 application 中关键字为 key 的对象
public Enumerration getAttributeNames();	// 产生枚举对象 获取所有索引关键字
public void removeAttribute(String key);	// 删除 application 中关键字为 key 的对象
```

### out对象

out对象是一个输出流, 用来向用户端输出数据。out对象可调用如下方法用于各种数据的输出

```java
out.print/println(Boolean);	// 用于输出一个 布尔值
out.print/println(char);	// 输出一个字符
out.print/println(double);	// 输出双精度的浮点数
out.print/println(long);		// 输出一个长整型数
out.print/println(string);	// 输出一个字符串
out.newline();	// 输出一个换行符
out.flush();	// 输出缓冲区的内容
out.close();	// 关闭流
```

### pageContext 对象

javax.servlet.jsp.PageContext类的实例对象

封装了对其它八大隐式对象的引用

```java
以键值对的方式, 将一个对象的值存放在 pageContext中:
void setAttribute(String name,Object value);
void setAttribute(String name,Object value,int scope);

根据名称获取pageContext中存放对象的值:
void getAttribute(String name);
void getAttribute(String name, int scope);
```

### page对象

当前页面跳转后的Servlet类的实例

代表当前的JSP页面	类似于Java 中的 this 指针

```jsp
<%= ((javax.servlet.jsp.HttpJspPage) page).getServletInfo()%>
```



### config对象

javax.servlet.ServletConfig 接口的实例

代表当前JSP配置信息

提供了检索Servlet初始化参数的方法

```java
String propertyFile = (String) config.getInitParameter("PropertyFile");
```

### exception对象

exception对象用于处理JSP页面中的错误,异常

exception对象是java.lang.Throwable类的实例

## Java Servlet

### Servlet的部署 创建 运行

```java
package  moon.sun;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
public class Example6_1 extends HttpServlet {
    public void init(ServletConfig config) throws ServletException {
        super.init(config);

    }
    public void service(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // 设置相应的MIME类型
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();	// 获得向用户发送数据的输出流
        out.println("<html><body bgcolor=#ffccff>");
        out.println("<h1>hello servlet!</h1>");
        out.println("</body></html>");
    }
}
```

### 编写部署文件web.xml

**web.xml文件需要存放在WEB-INFO目录中**

```xml
<?xml version="1.0" encoding="utf-8"?>
<!-- 第一行为XML文件的声明,在其前面不能有空白 其他的处理指令或注释 -->
<!-- ?和xml之间不能有空格 -->
<web-app>
<!-- xml文件的根标签 -->
	<servlet>
    <!-- 一个web-app标签可以有多个servlet标签 -->
    	<servlet-name> hello </servlet-name>
        <!-- 在Tomcat服务器中创建的servlet的名字 -->
        <servlet-class> moon.sun.Example6_1 </servlet-class>
		<!-- 用来创建servlet的Servlet类的名字,包含完整的包名 -->
		<!-- 一个servlet标记只可部署一个servlet对象 -->
    </servlet>
    <servlet-mapping>
    <!-- 一个servlet标记可对应一个或多个servlet-mapping对象 -->
        <servlet-name> hello </servlet-name>
        <!-- Tomcat服务器创建的servlet的名字-->
    	<url-pattern> /lookHello </url-pattern>
        <!-- 标记指定用户用怎样的URL来申请servlet -->
    </servlet-mapping>
</web-app>
```

### servlet的工作原理

servlet是javax.servlet包中HttpServlet类的子类的一个实例,由Tomcat服务器负责创建并完成初始化

当多个用户同时请求一个servlet时,服务器为每个用户分别启动一个线程而不是公用一个进程,这些线程由Tomcat服务器来管理,与传统的CGI为每个用户启动一个进程相比较,效率要高的多

**一个servlet的生命周期主要有以下三个过程组成**

1. 初始化servlet 在第一次请求加载时,服务器会调用init方法完成必要的初始化工作
2. 新诞生的servlet再调用service方法相应用户的请求
3. 当服务器关闭时,调用的distroy方法销毁servlet

### init方法

该方法是HttpServlet类中的方法, 可以在子类中重写这个方法

```java
public void init(ServletConfig config) throws ServletException;
```

ServletConfig对象负责传递服务设置信息,如果传递失败就会发生ServiceException,servlet就不能正常工作

### service方法

用于处理用户请求并且返回响应

```java
public void service(HttpServletRequest request, HttpServletResponse) throws ServletException,IOException;
```

### destroy方法

当Tomcat服务器终止服务时,例如关闭Tomcat服务器等,destroy()方法会被执行,销毁servlet

```java
public destroy();
```

### doGet方法

```java
public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletExceeption,IOException;
```

### dePost方法

```java
public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletExceeption,IOException;
```

### 通过JSP页面访问servlet

根据XML文件中设定的url-pattern进行访问

```html
<form action="url-pattern?name1=value&name2=value">
</form>
$.ajax({
	url:"url-pattern?name1=value&name2=value",
	data:"",
})
```

### 共享变量

请求servlet类的用户共享该servlet类的成员变量

但不共享方法中的局部变量

### 重定向和转发

#### senRedirect方法

重定向方法void sendRedirect(String location)是HttpServletResponse类中的方法

重定向方法仅仅是将用户从当前页面或servlet定向到另一个JSP页面或servlet, 但不能将用户请求(request)转发给另一个页面

#### forward方法

RequestDispatcher 对象可以把用户对当前JSP页面或servlet的请求转发给另一个JSP页面或servlet

**获取RequsetDispatcher对象**

```java
public RequestDispatcher getRequsetDispatcher(String path);
```

**转发**

```java
void forward(ServletRequest request, ServletResponse response) throws ServletException,ava.io.IOException;
```

**eg:**

```java
RequestDispatcher dispatcher = resquest.getRequestDispatcher("url-pattern");
dispatcher.forward(resquest,response);
```

#### 二者区别

**转发：**用户可以看到转发到的JSP页面或servlet的运行效果, 但是,浏览器的地址栏不会发生变化,当用户刷新页面是,请求的仍然是当前JSP页面或servlet的地址。当servlet中执行到forward语句时,Tomcat服务器会立即结束当前servlet的执行

**重定向：**Tomcat服务器还是要把当前的servlet代码执行完毕后才实施重定向操作，但Tomcat不再给用户看当前servlet的执行效果

**转发的优点：**可以让JSP页面和处理数据的servlet**解耦**，JSP页面只需要和处理转发的servlet打交道。并在此servlet根据用户需求，将用户的请求转发给对应的servlet。便于维护

### session的使用

通过HttpServletRequest对象request调用getSession方法获取用户的session对象

```java
HttpSession session = request.getSession(true);
```

