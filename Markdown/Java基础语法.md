# 1 标识符

- Java 的标识符可以为任意长度

- 由 字母 / 数字 / $ / _   组成

- 首字母不可为数字

- 严格区分大小写
***

# 2 关键字

```Java
abstract    continue    for          new          switch
assert      default     goto         package      synchronized
boolean     do          if           private      this
break       double      implements   protected    throw
byte        else        import       public       throws
case        enum        instanceof   return       transient
catch       extends     int          short        try
char        final       interface    static       void
class       finally     long         strictfp     volatile
const       float       native       super        while
```

> `const	goto `	无实际用途,目前仅为**占位符**

***

# 3 基本数据类型

> 在不同平台中各种基本数据类型占用储存空间长度固定, 保证了 `Java` 语言的**跨平台性**

## 	整数类型

```Java
byte      字符型
short     短整型
int       整型
long      长整型
```

## 	浮点数类型

```java
float      单精度浮点数
double     双精度浮点数
```

## 	字符类型

```java
char      字符类型 // 支持Unicode ==> \u____(四位)
```

## 	布尔类型

```java
boolean     布尔类型 // false / true
```

## 	整型文字常量

默认为 **int** 类型

- 十进制		不可以 0 开头,除 0 的情况	0 / 1 / 2021

- 八进制		以 0 开头	010 / -0276		(0 - 7位)

- 十六进制	以 0x / 0X 开头	0x4Ab	(0 - 9 位)		// 不严格区分大小写

- long型常量后加后缀  `L`  		`int a = 123456789L`

## 	浮点型文字常量

- 默认 **double** 类型

- **不可以省略小数点**  5.6 / 5. / .6

- 加后缀	D / d	F / f	可省略小数点

## 	字符文字常量

' A '		' $ '

## 	布尔文字常量

false  /  true
***

# 4 变量及基本运算

## 	变量

数据类型	变量名1,变量名2....;

`int a,b,c;`

## 	常量

标识符字母均 **大写**

`final double PI = 3.1415926535 `

## 运算符

### 	算数运算符

Java 中可连续赋值

```java
+(加)	-(减)	*(乘)	/(除)	%(求余)
```

int型 / int型		**取整**

(float / double) / int型		**除法,返回 float / double**

`++(自增) / --(自减)`  	(前置 后置 与C语言相同)

### 	关系运算符

```java
<	<=	>	>=	==	!=
```

### 	逻辑运算符

```java
!	&&	||	^        // false / true
```

### 	复合赋值运算符

```java
+=	-=	*=	/=	%=	<<=	>>=
```

### 	条件运算符

```java
(a)?(b):(c)
等价于 if( a ) { b } else { c }
```

### 	自动类型转换

```java
byte ==> short ==> int ==> long ==> float ==> double
    	  char ==> int...
```

### 	强制类型转换

```java
float x = (float) a;
```
***

# 5 控制台的输入和输出

## 	System 类

不能实例化 , 无法创建对象

```java
标准输入流
public static final InputStream in;

标准输出流
public static final PrintStream out;

标准错误输出流
public static final PrintStream err;

例:
System.out.println("Hello,Java!")	// 换行输出
System.out.print("Hello,Java!")		// 不换行输出

byte read[] = new byte[1024];
System.in.read(read);		// 从控制台读取字节型数据
System.in.read(read,start,len);		// 从控制台读取 len 个字节的数据 并从read[start] 开始存放
```
## Scanner 类

```java
import java.util.Scanner;

Scanner scanner = new Scanner(System.in);	// 从键盘输入
Scanner scanner = new Scanner(new File("Data.txt"));	// 从文件输入
Scanner scanner = new Scanner("123456789");	// 从字符串输入
// 当为不存在的文件创建Scanner对象会抛出错误 FileNotFoundException 异常
```

### 常用方法

```java
public void close();	// 关闭当前的Scanner对象
public boolean hasNext();	// 如果当前scanner对象中有另一个标记, 返回true
public String next();	// 查找并返回来自当前scanner对象的下一个完整标记
public boolean nextBoolean();	// 扫描下一个布尔值的输入标记并返回该值
public byte nextByte();		// 将输入信息的下一个标记扫描为一个byte值并返回
public short nextShort();	// 将输入信息的下一个标记扫描为一个short值并返回
public short nextInt();	// 将输入信息的下一个标记扫描为一个short值并返回
public short nextLong();	// 将输入信息的下一个标记扫描为一个short值并返回
public short nextFloat();	// 将输入信息的下一个标记扫描为一个short值并返回
public short nextDouble();	// 将输入信息的下一个标记扫描为一个short值并返回
public short nextLine();	// 将输入信息的下一个标记扫描为一个short值并返回
```

**Scanner 对象的默认分隔符是空白字符**

```java
public Scanner useDelimiter(String pattern);	// 通过正则表达式为当前对象设置新的分隔模式
eg:
Scanner scanner = new Scanner(System.in).useDelimiter("[0-9]");	// 以数字 0-9 作为分隔符
```

****

***

# 6 条件语句

## 	if 语句

```java
if (boolean) {
    语句1;
} else if (boolean) {
    语句2;
} else {
    语句3;
}
```

## 	switch 语句

```java
switch (表达式) {
    case a : {}
	case b : {}
	case c : {}
    defafult: {}
}
```
***

# 7 循环语句

## 	while 循环

```java
while (boolean) {
    语句;
}
```

## 	do - while 循环

```java
do {
    语句;
} whlie (boolean);
```

## 	for 循环

```java
for (a ; boolean ; c) {
    语句;
} // IDEA fori 快捷创建
```
***

# 8 跳转语句

- break;	跳出当前一层循环 / switch	(无标号)

- break 标号;		跳出标号指定的循环体

- continue;		结束一层循环,并开始下一次迭代	(无标号)

- continue 标号;		结束本次循环,并开始目标层的下一次循环;

## 	标号

```java
tag_a:		// 实现 break; continue;	对循环体操作
for (int i = 0;i <= 5;i++) {
    tag_b:
    for (int j = 0;j <= 10;j++) {
        if (j < i) {
            break tag_b;	// 结束tag_b指定的for循环
        } else {
            continue tag_a;	// 开始新一轮 tag_a 指定的for循环
        }
    }
}
```
***

# 9 方法

## 		定义

```java
修饰符 返回值类型 方法名(形式参数表) {
    方法体
}	// 方法名的首字母小写,单词连写,其余单词首字母大写
eg:
public double area (double length; double width) {
    return length * width;
}
```

## 		调用

```java
int length = 10;
int width = 5;
int area = area(length, width);
```

## 		递归调用

```java
public long  factorial(int num) {
    if (num <= 1) {
        return 1;
    } else {
        return num * factorial(num-1);
    }
}
```

## 	方法重载

- 在一个类中,可定义多个同名的方法,但方法的形参表互不相同

- 在调用类中的方法时,有对应方法及形参表时直接调用,没有则进行自动类型转换

```java
static void method (short x) {}
static void method (int x) {}
static void method (int x, double y) {}

method((int) 1);	// 直接调用函数 2
method(1,1);	// 无直接匹配,调用函数三,将 1 转换为 double 型
```
***

# 10 类和对象

## 	类的定义

```java
修饰符 class class_name (extends / implements) {
    类体 (数据成员 + 方法)
}
```

## 	对象引用

```java
类名 对象名 = new 类名();
```

## 	匿名对象引用

```java
new 类名().方法名()/;
```

## 	静态变量

- 一个静态变量永远只有一份存储空间

- 可以通过类名访问

```java
public class A { static int i= 0;}
A.i++;	// i = 1
A a1 = new A();		// i = 2
```

### 	静态方法

类的方法,通过类名直接访问,**也可以通过对象名访问,但不推荐.**

```java
public class Cat {
    int age;
    static int getAge() {
        return age;
    }
}
```

### 构造方法

一种特殊方法,方法名与类名相同	默认会启用自动生成构造方法

```java
public class Cat {
    int age;
    int weight;
    // 方法名 与 类名 相同
    Cat (int insage, int itsweight) {	// 方法体,参数列表可为空
        age = itsage;
        weight = itsweight;
    }
}
Cat cat2 = new Cat(12,15);	// 调用 自定义 构造方法
```

### 静态变量的初始化

- 不能在**构造方法**中初始化 final 静态变量

- 声明指定初值初始化静态变量,**不可使用类的实例对象**

- 仅在**第一次**用到该变量的时候进行初始化

### 初始化块

#### 		静态初始化块

​	只初始化一次

```java
static {...}
```

#### 		实例初始化块

​	在创建类的对象时,**构造方法执行前执行**

```java
{...}
```
***

# 11 包

## 类的添加

```java
package packagename;	// 包名全部小写	放在文件开头位置
```

## pubic

- 使该类可被其他类使用 (公用的)

- 一个java文件中只允许有一个 公用类

```java
package cn.com.wzx.my_package.mysubpackage;
// 对应存放目录为 cn/com/wzx/my_package/mysubpackage
```

## 类的使用

```java
import mypackage.dog;	// import 语句之前不允许存在 class
import mypackage.*;		// 同时导入包中的所有 publick 类
pack1.MyClass m = new pack1.MyClass();		// 实现在同一文件中使用不同包中重名的类
pack2.MyClass m = new pack2.MyClass();
```

## 类访问权限

- public	公用的 可在包外使用

- 默认访问权限		只可在所属包中访问

## 成员访问权限

- public	任何可访问该类的代码都可以访问该成员`

- private	只能在所属类中被访问

- protected	在所属类 及所属类的子类 及其所属包中可访问

- 默认访问权限	只可在所属包中访问

### 		私有数据成员的外部访问

​		私有数据成员一般有对应的读取方法以及修改写入方法

```java
private	int Age;
int getAge() {
    return Age;
}	// 获取方法
int setAge(int age) {
    this.age = ((a>0)&&(a<20)?a:0);
}	// 修改方法
```

### 		构造方法默认为公用的

### 不可变对象和类

不存在修改方法 只能的构造时赋值
***

# 12 对象的存在时间与垃圾回收器

```JAVA
Book b1 = new Book();
// b1 为创建的对象引用 保存在堆栈中,退出作用域时,其所占内存就会被释放
// new Book() 创建一个对象 并保存在堆中
```

当引用某个对象的所有对象引用都退出作用域,则无法访问,但继续占用内存,**自动回收内存**
***

# 13 this

当前对象本身

- 在类的定义中直接访问同一类的成员

```java
this.radius = age;
```

- 返回当前对象

```java
return this;
```

- 访问被隐藏的数据成员

```java
private int age;
Cat (int age) { this.age = age;}
```

- 调用所属类的另一个构造方法 (通过方法重载定义多个构造方法)

```java
Cat () { this(2,4); }	
cat (int age; int weight) {...}	
```

- ​	**调用语句必须放在方法体的第一句**

- ​	其他方法中不能调用构造方法,且参数不能为this的参数或实例成员
***

# 14 Math 类

## 	数学常量

```java
自然对数的底e    public static final double E;
圆周率Π         public static final PI;
```

## 	三角函数

```java
正弦值        public static double sin( double a );
余弦值        public static double cos( double a );
正切值        public static double tan( double a );
// 单位为 弧度
正弦值为 a 的角度    public static double asin( double a );
余弦值为 a 的角度    public static double acos( double a );
正切值为 a 的角度    public static double atan( double a );
角度 转为 弧度    public static double toRadians( double angdeg );
弧度 转为 角度    public static double toDegrees( double angrad );
```

## 	指数函数

```java
e 的 a 次方    public static double exp( double a );
a 的 b 次方    public static double pow( double a, double b );

a 的平方根    public static double sqrt( double a );
a 的立方根    public static double cbrt( double a );
```

## 	对数函数

```java
ln a       public static double log( double a );
log10 a    public static double log10( double a );
```

## 	取整方法

```java
>= a 的整数    public static double ceil( double a );
<= a 的整数    public static double floor( double a);
最接近 a 的整数    public static double rint( double a );// 左右距离相同时以偶数优先

返回floor(a+0.5f)    public static int round( float a );
返回floor(a+0.5d)    public static long round( double a);
```

## 	其他常用方法

```java
绝对值    abs( object o );	// 方法重载 返回 int long float double 型参数的绝对值
最大值    max( object o );	// 方法重载 返回 int long float double 型参数的较大值
最小值    min( object o );	// 方法重载 返回 int long float double 型参数的较小值
0.0 - 1.1 的伪随机    random();	// double 类型
```

# 15 BigInteger / BigDecimal

> `BigInteger`  对象 可以表示任意精度和大小的整数

> BigInteger num1 = new BigInteger("9999999999999");
> BigDecimal nu`m2 = new BigDecimal("3.1415678");

## 	基本运算方法

```java
加	add		减	subtract
乘	multipiy	除	divide

BigInteger num3 = num1.divide(num2);
```
***

# 16 继承与子类

- Java 中定义的每个类都继承于一个已存在的类

- 若无显示指定,就隐含继承于类 **java.lang.Object**

- Java 中仅支持**单继承** 即只可有一个指定的父类

```java
public 子类名 extends 父类名 {
    定义子类新成员
}
```

## 	子类的构造方法 super()

- 子类不能继承父类的构造方法

- 在创建子类对象时, 系统会自动调用父类的构造方法

  ​	1.调用父类默认的构造方法

  ​	2.通过 super(); 显式调用父类含参数的构造方法, 参数不能为 this 或 当前对象的实例对象

## 	方法覆盖

- 子类中的方法应与父类中被覆盖的方法有相同的方法名,返回值类型 和 形参类型列表(形参名可不同)

- 可通过 **super.方法名** 调用父类的数据成员和方法

- **方法覆盖时 , 不可修改其访问权限**

## 	方法重载

- 当对应形参类型或者参数个数不同,则为方法重载

## 	数据成员和静态方法隐藏

- 子类中可改写父类的数据成员和静态方法	不支持多态
- 使用 **super.成员** 访问父类被隐藏的数据成员 或 方法名

***

# 17 final 关键字

任何一个变量被 final 修饰后 一旦初始化,其值便不可改变

### 	final 静态变量

必须在声明的同时初始化	或在静态初始化块中初始化

### 	final 实例变量

必须在声明的同时初始化	或在实例初始化块中初始化

### 	final 方法参数

可在方法中读取它的值,但不可改变

### 	final 对象引用

不能引用另一个对象,但对象的成员参数可以改变

### 	final 方法

在继承过程中**不能被子类覆盖**	(允许重载)

构造方法 不可使用 final 修饰

效果等价于	private + 方法

***

# 18 对象类型转换

- 子类对象 可向上转换为 父类对象	// **无法访问在子类中新定义的成员**
- 当父类对象 引用 子类对象时    可以**强制类型转换**为 子类对象

# 19 instanceof

- 检查某个对象的实际类型是否是某个对象或派生于指定类 / 实现了指定接口
- false  /  true

```java
B instanceof A
if ((B是指定类A || B派生于A || B实现了指定接口A) && B != null) { return true;}
else { return false; }
```

***

# 20 抽象类和抽象方法

### 	抽象方法

- 抽象方法是一个不完全方法,只有方法头 无方法体

```java
abstract 返回值类型 方法名(形式参数表);
```

### 	抽象类

- 抽象类不一定含有抽象方法,含有抽象方法的一定是抽象类
- 为继承而定义 / 只能做父类 / 不能使用new 创建对象
- 可定义构造方法 / 由子类构造方法调用
- **抽象父类中的所有抽象方法都要在子类中覆盖,否则子类仍为抽象类**

```java
abstract class A {...}
```

- 静态方法不能声明为抽象的
- final 方法不能声明为抽象的 这与 final 方法不支持 子类覆盖有关
- final 类不能声明为抽象的 这与final 类无法被继承有关 

***

# 21 接口

### 	定义

```java
(public) interface name {
    int NUM = 1;	// 公用静态常量
    void fun();		// 公用抽象方法
}
```

### 	information

- 不可定义构造方法
- 在一个类实现接口时,方法必须由 public 修饰
- 若未实现某接口的的全部方法,则产生抽象类 , 类必须由 abstract 修饰

###     接口继承

允许同时继承多个接口,但只允许继承一个类

```java
class D extends C implements A,B {...}

interface A extends B,C {...}
```

***

# 22 Object 类

### 	equals

```java
Object1.equals(object2);	// 比较两个对象引用是否引用同一个对象	一般在子类中覆盖
```

###  	toString

```java
Odject1.toString();		// 返回一个代表当前对象信息的字符串		默认为 类名 + @ + 该对象的哈希码/无符号十六进制
```

### 	clone

```java
// 浅层复制
newObject = oldObject;		// 对象引用值的复制
// 深层复制
newObject = oldObject.clone();		// 将对象 oldObject 的值复制到一个新的地址
```

- 该方法在Object 类中被声明为 protected 覆盖时可改写为 public;
- 默认情况下,定义的类都不具备克隆能力,**需要通过接口 Cloneable 实现**
- 当对象的成员为对象时,需要对每个对象成员分别使用clone()方法    否则为对成员引用的复制
- Java 标准库中有部分类实现了接口 Cloneable

***

# 23 包装类

- 包装类的对象不可变,创建后值无法修改
- 将基本数据类型包装成对象

```java
Object <== Boolean / Number / Character
Number <== Byte / Short / Integer / Long / Float / Double
```

### 	构造方法

均无默认构造方法

```java
// 字符对象的一种构造方法
public Character( char value );
// 基本数据类型对象的两种构造方法
public Integer( int value );
public Integer( String s );
// 当 s = "True" 时为true 不区分大小写	else ==> false
public Boolean( String s );		
```

### 	静态方法

### 		valueOf()

​	创建对象并初始化,返回该对象

```java
Integer integerObject = Integer.valueOf(1);		// 以相应的基本数据类型为参数
Double doubleObject = Double.valueOf("1.2");	// 以相应的字符串为参数
```

### 		toString()

​	返回特征字符串

### 		String ==> 基本数据类型

```java
int i = Integer.parseInt("2");
double d = Double.parseDouble("4.5");
```

### 		equals()

​	在包装类中被覆盖为  比较两个对象内容是否相同

### 		hashCode()

​	在包装类中被覆盖为 返回数据值

### 	获取对象的值

```java
boolean b = booleanObject.booleanValue();
char c = characterObject.charValue();
```

### 	类的最大最小值

除 Boolean 类外 , 每个包装类中都定义有静态常量	MAX_VALUE / MIN_VALUE

```java
Integer.MAX_VALUE;
Double.MIN_VALUE;
```

### 	自动装箱与拆箱

```java
Integer intObject = 1;		// 自动装箱
int num = intObject;		// 自动拆箱
(4 < 5)? 4:true;	// 
```



***

# 24 Character 类

```java
是否为数字    public static boolean isDigit( char ch );
是否为字母    public static boolean isLetter( char ch );
是否大写字母    public static boolean isUpperCase( char ch );
是否小写字母    public static boolean isLowerCase( char ch );
是否字母或数字    public static boolean isLetterOrDigit( char ch );
是否Unicode空白字符    public static boolean isSpaceChar( char ch );
		
是否可为标识符首字母    public static boolean isJavaIdentifierStart( char ch );
是否可为标识符非首字母    public static boolean isJavaIdentifierPart( char ch );

转成小写字母    public static char toLowerCase( char ch );
转成大写字母    public static char toUpperCase( char ch );
```

# 25 内部类

> 在一个类的内部定义另一个类
> 
> 对象引用的创建 ”**外部类名.内部类名**“	`OuterClass.InnerClass`

### 	非静态内部类的对象

```java
OuterClass t = new OuterClass();
OuterClass.InnerClass tin2 = t.new InnerClass();
```

### 	静态内部类

```java
"外部类名.内部类名"
OuterClass.M mm = new OuterClass.M();
```

### 	匿名内部类

```java
new AI(){...};
```

### 	私有内部类

- 无法在外部类之外访问

- private    protected

***

# 26 数组

### 		数组引用的声明

```java
数据类型[] 数据名;
数据类型 数据名[];		// 源于 C语言 , 不推荐
数组名.length ==> 返回数组长度
```

### 	数组的创建

元素个数的表达式的值必须 >= 0 且为整数	~~long~~

```java
数组名 = new 数据类型[元素个数表达式];
```

### 	元素的访问

```java
数组名[下表表达式]		下表表达式  0 ==> MAX-1
```

### 	对象数组

数组中存放的是 **对象引用** ,访问前需要通过 new 创建实例

```java
class1[] class = new class[3];
```

### 初始化数组

```java
int[] arr = new int[]{1,2,3,};		// 第二个int后的方框不可指定元素个数
int[] arr = {1,2,3,};
```

### 多维数组

```java
1:
int[][] arr = new int[3][4];	// 不推荐使用

2:
int[][] arr = new int[3][];
arr[0] = new int[4];    // 多维数组可不等长
arr[1] = new int[3];
arr[2] = new int[7];
```

### 多维数组初始化

```java
int[][] arr1 = {{1,2,},{3,4,},}
```

### foreach 语句

```java
for (数据类型 变量名 : 数组) {	// 数据类型 必须与数组元素类型相同
    语句
}
```

### 数据的传递

```java
public void fun(int[] a) {...}; 
```

### 可变参数列表

- 以数组为参数 , 将**同一类型不定数目**的变量一次性传递
- 数据类型...参数名    生成 **可变参数列表**

```java
public void fun(int...array) {...}
```

### 返回值-数组

```java
int[] a = new int[10];
return a;	// 返回的只是一个引用数组的引用
```

# 27Arrays 类

### fill()

```java
Arrays.fill( arrayObject, value );		// 将 value 的值 赋值给arrayObject中的所有元素
Arrays.fill( arrayObject, start, end, value );    // 同上 ,指定赋值区域
// 注意 value 的数据类型
```

### 数组 ==> 字符串

```java
Arrays.toString(数组对象);	// 一维数组
Arrays.deepToString(数组对象);		// 多维数组
```

### 数组复制

```java
Arrays.copyOf(数组对象,个数);		// 从头开始选择, 个数 > length的部分 赋默认值 0
Arrays.copyOfRange(数组对象, start, end);	// 超出部分 赋默认值 0
```

### 数组的相等

```java
Arrays.equals(a1, a2);	// 元素个数相同,对应位置元素相同 返回 True
Arrays.deepEquals(a1, a2);	// 多维数组的比较
```

### 数组的排序

除 Boolean 的基本数据类型 都可排序	**从小到大**

```java
Arrays.sort(数组对象);	// 全数组排序
Arrays.sort(对象, start, end);	// 将 (start,end) 部分进行排序
```

### 对象数组的排序

```java
class A implements Compareable {	// 实现接口 自然比较法
    public int i,j;
    // 自定义 类的实例对象的比较函数
    public int compareTo(Object obj) {
        int k = ((A) obj).i;
        if (k>i) {
            return -1;		// 当目标对象参数大于该对象参数时,返回 负整数
        } else if (k<i) {
            return 1;		// 当目标对象参数小于该对象参数时,返回 正整数
        } else {
            return 0;		// 当目标对象参数等于该对象参数时,返回 0
        }
    }
}
```

### 查找

二分法,数据已自然顺序排序	除 Boolean 数据类型

```java
Arrays.binarySearch(对象, start, end, key);	// 返回下标 或 负值(数据不存在)
```

# 28 字符串 String

==类String中的方法, 实际上都是创建了一个新的String对象, 原有对象没有任何改变==

==当调用String类的某个方法并未改变String对象值,则返回引用源String对象的引用,并不会创建全新的String对象==

### 创建 String 对象

String 对象不可变

```java
char[] data = {'a','b','c',};
String str1 = new String("...");
String str2 = "...";
```

### 操作String对象

#### 字符串长度

```java
str1.length()
```

#### 比较

```java
相等性比较    str1.equals(str2);    // 区分大小写
相等性比较    str1.equalsIgnoreCase(str2);    // 忽略大小写

ASCII码差值    str1.compareTo(str2);    // 返回第一个不相同字符 的 编码差值 str1 - str2 (ASCII)
ASCII码差值    str1.compareToIgnoreCase(str2);    // 不区分大小写
```

#### 规范化字符串

相同内容的**字符串文字常量** 共享同一个String对象

```java
String str1 = "Java";	// 规范化字符串
String str1 = new String("...");	// 非规范化字符串
String str2 = str1.intern();	// 返回对应的规范化字符串
```

#### 字符串拼接

```java
String str1 = "Hello!";
String str2 = str1.concat(" world!");	// 返回新对象
```

#### 查找单个字符

```java
查找首次出现位置    public int indexOf(int ch);    // 返回 ch 第一次出现的位置 或者 -1 未找到
查找首次出现位置    public int indexOf(int ch, int fromIndex);    // 从指定位置开始 , 同上

查找最后出现位置    public int lastIndexOf(int ch);    // 返回 ch 最后一次出现的位置 或者 -1 未找到
查找最后出现位置    public int lastIndexOf(int ch, int fromIndex);    // 从指定位置开始 , 同上
```

#### 查找字符串

```java
查找首次出现位置    public int indexOf(String str);    // 返回 str 第一次出现的位置 或者 -1 未找到
查找首次出现位置    public int indexOf(String str, int fromIndex);    // 从指定位置开始 , 同上

查找最后出现位置    public int lastIndexOf(String str);    // 返回 str 最后一次出现的位置 或者 -1 未找到
查找最后出现位置    public int lastIndexOf(String str, int fromIndex);    // 从指定位置开始 , 同上

以指定字符串开头    public boolean startsWith(String prefix);    // 判断是否以字符串开头
以指定字符串结尾    public boolean endsWith(String suffix);    // 判断是否以字符串结尾
```

#### 转换字符串

```java
转换为纯小写    public String toLowerCase(String str);    // 将str中的字符串转换为纯小写,并产生新字符串
转换为纯大写    public String toUpperCase(String str);    // 将str中的字符串转换为纯大写,并产生新字符串

替换字符串字符    public String replace(char oldChar, char newChar);    // 替换字符串对应字符,返回新字符串

移除开头结尾空白字符    public String trim();    // 移除当前字符串开头结尾的空白字符,生成新字符串返回
```

#### 提取子字符串

```java
提取指定位置到结尾    public String substring(int beginIndex);// 返回指定位置到结尾的子串
提取指定区间字符串    public String substring(int beginIndex, int endIndex);// 返回指定区间的字串		
```

#### 将字符串转换成数组

```java
全转换    public char[] toCharArray();	// 将当前字符串转换为字符数组

/*转换指定字符串并复制到指定数组的指定位置*/
/*将 (srcBegin, srcEnd) 位置的字符,放置在 dst字符数组从 dstBegin 位置中*/
public void getChars(int srcBegin, int srcEnd, char[] dst, int dstBegin);

eg:
char[] chars = {'v','e','r','y',' ','g','o','o','d'};
"too much".getchars(4,8,chars,5);
==> chars = {'v','e','r','y',' ','m','u','c','h'}
```

#### 格式化字符串

```java
public static String format(String format, Object...args);

eg:
int num = 2; double d = 3.5;
String str = String.format("num = %d, d = %f", num, d);
==> str = "num = 2, d = 3.500000"
```

##### format 参数语法格式

```java
%[argument_index $ ][flags][width][.precision]conversion
```

**conversion**

| 转换符 |      类型      | 转换符 |       类型       |
| :----: | :------------: | :----: | :--------------: |
|   b    |   boolean值    |   e    | 浮点数(科学计数) |
|   c    |  Unicode字符   |   o    |   整数(八进制)   |
|   d    |  整数(十进制)  |   s    |      String      |
|   f    | 浮点数(十进制) |   x    |  整数(十六进制)  |

**width**

```java
可选非负十进制数  用于指定格式参数转换后的最少字符数, 超过则无限制,不足则补空格(默认在左侧补空格,右对齐)
```

**precision**

```java
可选非负十进制数  用于指定浮点数类型参数的精度, 设置显示小数的位数
eg:		String("%.2f",2.129)	==>		2.12
```

**flags**

| 标志 |                       说明                       |
| :--: | :----------------------------------------------: |
|  -   |             相应参数转换结果将左对齐             |
|  +   |    相应参数转换结果总是包括整数和负数的正负号    |
|  0   | 相应参数转换结果将在数字前用零填充以满足指定宽度 |
| 空格 |      相应参数转换结果将在正数前面加一个空格      |
|  ,   | 相应参数转换结果将在整数或浮点数中添加分组分隔符 |
|  (   |         相应参数转换结果将负数扩在括号内         |

**argument_index**

```java
用于表明参数在参数列表中的位置
String.format("%2$d,%1$d", 1, 2);
```

# 29 类StringBuilder/StringBuffer

### 创建可变字符串对象

类StringBuffer可以安全的用于多线程编程, 类StringBuilder 运行速度更快

#### 1.类StringBuilder

```java
创建无字符的对象    public StringBuilder();// 初始容量为16个字符
创建指定容量对象    public StringBuilder(int capacity);// 初始容量为 capacity
创建指定字符串str的对象    public StringBUilder(String str);// 默认长度为 str + 16

eg:
StringBuilder strBuilder1 = new StringBuilder();
StringBuilder strBuilder2 = new StringBuilder(32);
StringBuilder strBuilder3 = new StringBuilder("hello Java!");
```

#### 2.类StringBuffer

```java
创建一个空对象    public StringBuffer();	// 初始容量为16个字符
创建指定容量对象    public StringBuffer(int capacity);// 初始容量为 capacity
创建指定字符串str的对象    public StringBuffer(String str);// 默认长度为 str + 16

eg:
StringBuffer strBuffer1 = new StringBuffer();
StringBuffer strBuffer2 = new StringBuffer(32);
StringBuffer strBuffer3 = new StringBuffer("hello Java!");
```

### 在可变字符串中追加和插入新内容

```java
追加	append
StringBuilder strBuilder = new StringBuilder("Hello");
strBuilder.append(" java!");
==> strBuilder = "Hello Java!"
    
插入	insert
StringBuilder strBuilder = new StringBuilder("Java!");
strBUilder.insert(0,"Hello ");
==> strBuilder = "Hello Java!"
```

### 常用操作

```java
索引字符内容    public char charAt(int index);// 返回 index 处字符

字符串的长度    public int length();	// 返回存储 字符 个数

删除指定区域    public StringBuilder delete(int start, int end);// 包头不包尾
删除指定字符    public StringBUilder deleteCharAt(int index);

替换指定区域    public StringBuilder replace(int Start, int end, String str);

索引子串内容    public StringBuilder substring(int start);
索引指定区域子串内容    public StringBuilder substring(int start, int end);

逆转字串内容    public StringBulider reverse();// 返回当前对象

设置字符内容    public void setCharAt(int index, char ch);

设置字符串长度    public void setLength(int newLength);// 长 删, 短 补零

生成String对象    public void String toString();

获取对象的最大容量    public int capacity();		// 返回对象目前容量
```

# 30 命令行参数

类的**main**方法 有一个String 数组类型的参数, 可通过**命令行**给其传参

```java
c:\test>java class1 2.6 + 4 "Hello Java!"	// 参数列表,有空格时需要双引号引出, 参数以空格划分字符串
```

# 31 异常处理

## 异常和异常类

> Java语言中, 所有的异常类型都派生自类==java.lang.Throwable==    其包含两个子类:
>
> ==java.lang.Error==    表示系统错误, 程序一般不需要也不应该对这种类型的异常进行处理
>
> ==java.lang.Exception==    表示可能从任何Java方法或运行期偶发事件中抛出的异常

**EXception异常类的常用子类**

|               异常类型               |                            说明                             |
| :----------------------------------: | :---------------------------------------------------------: |
|   java.lang.ClassNotFoundException   |                     没有找到欲加载的类                      |
| java.lang.CloneNotSupportedException | 调用方法clone复制对象,但定义该对象的类没有实现接口Cloneable |
|   java.lang.IllegalAccessException   |                    非法访问类和类的成员                     |
|    java.lang.InterruptedException    | 当线程在活动之前或活动期间处于等待/休眠/占用且该线程被中断  |
|    java.lang.NoSuchFieldException    |                类中不包含指定名称的数据成员                 |
|   java.lang.NoSuchMethodException    |                      无法找到指定方法                       |
|      java.lang.RuntimeException      |        可能在Java虚拟机正常运行期间抛出的异常的父类         |
|         java.io.IOException          |                       发生某种I/O错误                       |
|    java.io.FileNotFoundException     |                试图打开指定路径名的文件失败                 |
|    java.net.MalformedURLException    |                        出现错误的URL                        |
|    java.net.UnknownHostException     |                    无法确认主机的IP地址                     |

**RuntimeException**为Java虚拟机正常运行期间抛出的异常,是Java解释器执行正常指令时产生的异常,表示编译器无法发现的编译错误

**RuntimeException异常类的子类**

|                 异常类型                  |                             说明                             |
| :---------------------------------------: | :----------------------------------------------------------: |
|       java.lang.ArithmeticException       |                      出现异常的运算条件                      |
|       java.lang.ArrayStoreException       |             将错误类型的对象存储到一个对象数组中             |
|       java.lang.ClassCastException        |              试图将对象强制转换为不是实例的子类              |
|    Java.lang.IllegalArgumentException     |                  方法调用时传递的参数不合法                  |
|    java.lang.IndexOutOfBoundsException    |                        索引值超出范围                        |
|   java.lang.NegativeArraySizeException    |                 试图创建元素个数为负数的数组                 |
|      java.lang.NullPointerException       |                       试图访问空对象时                       |
|        java.lang.SecurityException        |                        存在安全侵犯时                        |
| java.lang.ArrayIndexOutOfBoundsException  |                     用非法索引值访问数组                     |
| java.lang.StringIndexOutOfBoundsException |                     字符串索引值超出范围                     |
|   java.lang.IllegalThreadStateException   |            线程没有处于请求操作所要求的适当状态时            |
|      java.lang.NumberFormatException      |               字符串无法转换成适当格式数值类型               |
|     java.util.InputMismatchException      | 当获取的标记与期望类型的模式不匹配,<br />或者该标记超出期望类型的范围内时 |

**Exception类的构造方法**

```java
public Exception();
public Exception(String message);
```

**Throwable类获取异常信息**

```java
返回当前异常的详细信息    String getMessage();
返回当前异常的简要描述    String toString();

在标准错误输出上打印当前异常及其栈追踪信息    void printStackTrace();
```

## 自定义异常类

> 自定义异常类时,应该使它派生于类==Exception==或它的子类

```java
class MyException extends Exception {
    public MyException() {}
    public MyExceptino(String Message) {
        super(message);
    }
}
```

## 抛出和声明异常

> 当程序运行出现错误时, ==Java语言预定义的异常==会由系统创建一个相应的异常类对象并抛出
>
> ==其他异常(如用户自定义异常)==必须在程序中中使用==throw==显式抛出
>
> Java语言预定义的异常也可以使用关键字throw在程序中显式抛出
>
> 对于==必检异常==,必须使用==throws==在该方法的方法头中声明异常的类型

```java
static int method(int a, int b) throws MyException, ArithmeticException {
    if(a < 0) throw new MyException("被除数不能小于零");
    if(b == 0) throw new ArithmeticException("除数不能等于零");
    return a / b;
}
```

方法覆盖时,当父类中被覆盖的方法没有声明某必检异常,子类中的覆盖方法也不能声明

## 捕获异常

`try`    `catch`    `finally`

```java
try {
    可能出现异常的代码;
} catch(Type1 id1) {
    对 Type1 类异常进行处理的代码;
} catch(Type2 id2) {
    对 Type2 类异常进行处理的代码;
} catch(Exception e1) {
    确保捕获所有异常,但放置靠前则之后的 catch块都会失效;
} finally {
    无论是否出现异常,都会执行;
}
```

# 32 多线程

## 32.1 创建任务和线程

通过实现接口==java.lang.Runnable==, 在方法run中定义相应线程的子任务代码

```java
public class testRunnable implements Runnable {
    String str;
    int num;
    public testRunnable(String str, int num) {
        this.str = str;
        this.num = num;
    }
    public static void main(String[] args) {
        testRunnable threadA = new testRunnable("threadA",30);
        testRunnable threadB = new testRunnable("threadB",20);
        new Thread(threadA).start();
        new Thread(threadB).start();
    }
    public void run() {
        for(int index=0;index<num;index++)
        	System.out.println(str);
    }
}
```

## 32.2 线程属性

Java语言中,每个线程都有一个优先级, 而且, 程序中每个显式创建的线程都可以标记为守护线程

### 32.2.1 线程优先级

```java
更改当前线程的优先级
new Thread(threadA).setPriority(num);

返回当前线程的优先级
new Thread(threadB).getPriority();

Java语言中,线程有10个优先级
最低优先级常量    Thread. MIN_PRIORITY = 10;
最高优先级常量    Thread. MAX_PRIORITY = 1;
默认优先级常量    THread. NORM_PRIORITY = 5;
```

Java线程的优先级通常会被映射到其运行平台操作系统的优先级上。Windows操作系统只有七个优先级别, 这种映射关系是不确定的

### 32.2.2 守护线程

Java语言中, 线程分为用户线程和守护线程两类。 

主线程为用户线程，在用户线程中创建的线程默认为用户线程。

==守护线程==也成为后台线程,这种线程与用户线程的区别在于当一个程序中的所有用户线程都结束运行时,程序会立即结束执行,不管当前是否还有守护线程正在运行

守护线程通常用来在后台为其他线程提供服务, 它不属于程序中的必要部分

```java
设置为守护线程    new Thread(threadA).setDaemon(true);    // 必须在当前线程启动之前调用，主线程无法转换为守护线程
判断是否为守护线程    new Thread(threadB).isDaemon();

返回对当前正在运行的线程对象的引用    Thread thread = Thread.currentThread();
返回线程的名称    new Thread(threadB).getName();    // 每个线程都有默认的名称 thread-0
设置线程的名称    new Thread(threadB).setName();
设置休眠时间    Thread.sleep(1000);    // 毫秒
```

## 32.3 线程池

### 32.3.1 线程池的创建

```java
创建一个可根据需要创建新线程的线程池
import java.util.concurrent.Executors;
ExecutorService exec = Executors.newCathedThreadPool();
当某一个线程在60s内没有被使用时,系统会自动终止并从缓存中移除它

创建一个可重用的包含指定数量线程的线程池
ExecutorService exec = Executors.newFixedThreadPool(3);
```

### 32.3.2 线程池的操作

```java
线程池的关闭    exec.shutdown();    // 线程池必须显式关闭
添加子任务    exec.execute(new TestExecutor("Thread",3));
```

### 32.3.3 使用Lock锁实现同步

多线程程序中, 一个资源可以被多个并发线程共享, 如果不加以防范, 就有可能引起资源冲突

```java
import java.util.concurrent.locks.*;
private final banklock = new ReentrantLock();

banklock.lock();
try {
    临界区
} finally {
    banklock.unlock();
}

当某一线程执行到此处时,如果当前锁可用,则获得这个锁,并给下方代码加锁,线程执行完成后进行解锁.
当前一线程解锁前,其他线程执行其中的加锁语句时进入等待,直至当前锁被释放.
```

### 32.3.4 使用synchronized实现同步

当一个线程需要执行某个被关键字synchronized保护的代码时,他将首先检查==当前对象==内置的锁是否可用, 如果可用,就获得该锁,执行其中的代码,然后释放该锁.

**同步方法**

当前线程必须获得当前对象的内置锁

```java
public synchronized double getBalance() {
    return balance;
}
```

**同步块**

当前线程必须获得指定对象的内置锁

```java
public double getBalance() {
    synchronized(指定对象名) {    // 对象名是指用于保护随后临界区中代码的内置锁所属对象的引用名
        return balance;
    }
}
```

### 32.3.5 线程间协作

对于synchronized保护的临界区, 可通过调用下述方法实现线程间协作

==下述方法必须出现在同步方法或者同步块中,且只能通过用于保护当前临界区的内置锁所属对象调用

```java
## 使用wait方法将导致当前线程释放掉它拥有的当前对象的内置锁
使当前线程的等待,直到其他线程同一对象调用方法 notify or notifyAll
public final void wait() throws InterruptedException;
或者等待时间超出参数指定的时间
public final void wait(int timeout) throws InterruptedException;

唤醒正在当前对象内置锁上等待的所有线程
public final void notifyAll();
唤醒任意一个正在当前对象内置锁上等待的线程
public final void notify();

```

### 32.3.6 线程的状态

> Java语言使用内部枚举类型==java.lang.Thread.State==描述六种状态
>
> 调用类Thread.getState();可以获取当前线程的状态

**新生状态**

创建一个新的线程对象,在尚未启动前,该线程就处于新生状态, 

Thread.State.NEW

**可运行状态**

一个处于可运行状态的线程可能正在运行也可能没有运行,这取决于线程调度器是否给他分配了CPU

线程调度器只会给处于可运行状态的线程分配CPU, 并且倾向于先给优先级高的线程分配CPU时间片

Thread.State.RUNNABLE

**阻塞状态**

当线程试图获取对象的内置锁被其他线程拥有时,线程进入阻塞状态

Thread.State.BLOCKED

**等待状态**

Thread.State.WAITING

**计时等待状态**

Thread.State.TIMED_WAITING

**终止状态**

Thread.State.TERMINATED

# 33 图形用户界面

## 33.1 框架

### 33.1.1 创建框架

**类javax.swing.JFrame**

```java
设置关闭框架时的行为    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  // 关闭当前框架时结束运行
显示框架    setVisible(Boolean);    // 可以在框架第一次显示前,设置框架属性,并向其中添加组件
设置框架大小    setSize(int weight; int height);    // 默认为0, 只显示标题栏


public JFrame();    // 创建一个没有标题的框架
public JFrame(String title);    // 创建一个带有指定标题的框架

eg:
public class FrameDemo {
    public static void main(String[] args) {
        JFrame frame = new JFrame("测试JFrame");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);    // 框架关闭时结束运行
        frame.setSize(300, 100);    // 设置框架大小
        frame.setVisible(true);    // 显式框架
    }
}
```

### 33.1.2 添加组件

```java
添加组件    add();
删除组件    remove();

import javax.swing.*;
import java.awt.*;

public class FrameDemo {
    public static void main(String[] args) {
        JFrame frame = new JFrame("测试JFrame");
        JButton button = new JButton("按钮");    // 创建一个按钮
        Container container = frame.getContentPane();    // 取得框架的内容框格
        container.add(button);    // 将按钮添加到框架的内容框格中
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 100);
        frame.setVisible(true);
    }
}
```

**内容窗格委托**

```java
class FrameWithButton1 extends JFrame {    // 创建JFrame类的子类
    public FrameWithButton1() {
        JButton button = new JButton("按钮");
        button.setSize(100,100);
        add(button);
    }
    public static void main(String[] args) {
        FrameWithButton1 frame = new FrameWithButton1();
        frame.setTitle("将一个按钮添加到框架中");
        frame.setSize(3000,500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
```

## 33.2 事件处理

### 33.2.1 事件和事件源

事件源: 触发某一事件的组件    (并非所有的事件源都是GUI组件)

**常见事件及相应的事件源类型**

当一个组件可以触发某种事件时, 该组件的所有子类也可以触发同样的事件

|    事件源    |        用户操作        |          事件           |
| :----------: | :--------------------: | :---------------------: |
|   JButton    |        点击按钮        |       ActionEvent       |
|  JTextField  |    在文本域按回车键    |       ActionEvent       |
|  JCheckBox   |       点击复选框       | ActionEvent / ItemEvent |
| JRadioButton |      点击单选按钮      | ActionEvent / ItemEvent |
|  JComboBox   |        选定选项        | ActionEvent / ItemEvent |
|    JList     |        选定选项        |   ListSelectionEvent    |
|  JMenuItem   |       选定菜单项       | ActionEvent / ItemEvent |
|   JSlider    |        滑动滑块        |       ChangeEvent       |
|    Window    |    窗口打开 / 关闭     |       WindowEvent       |
|  Component   |     点击或移动鼠标     |       MouseEvent        |
|  Component   |  按下或释放键盘上的键  |        KeyEvent         |
|  Container   | 在容器中添加或删除组件 |     ContainerEvent      |
|  Component   |   组件获得或失去焦点   |       FocusEvent        |
|  Component   |  组件移动 / 改变大小   |     ComponentEvent      |
|  JScrollBar  |       移动滚动条       |     AdjustmentEvent     |

### 33.2.2 事件监听器

组件触发某一特定事件后,相关事件监听器将接收并对事件做出相应的处理

想要监听器接收某个组件触发的某种事件,就必须在该事件源中注册它

**注册** 调用事件源提供的注册方法来声明某个对象是该事件的监听器

每个监听器都是一个对象, 其所属类必须实现Java语言定义的相应监听器接口

|        事件        |                  监听器接口及注册方法                  |                          监听器方法                          |
| :----------------: | :----------------------------------------------------: | :----------------------------------------------------------: |
|    ActionEvent     |        ActionListener<br />addActionLIstener();        |                actionPerformed(ActionEvent e)                |
|  AdjustmentEvent   |    AdjustmentListener<br />addAdjustmentListener();    |          adjustmentValueChanged(AdjustmentEvent e)           |
|   ComponentEvent   |     ComponentListener<br />addComponentListener();     | componentResized(ComponentEvent e)<br />componentMoved(ComponentEvent e)<br />componentShown(ComponentEvent e)<br />componentHidden(ComponentEvent e) |
|   ContainerEvent   |     ContainerListener<br />addContainerLIstener();     | componentAdded(ContainerEvent e)<br />componentRemoved(ContainerEvent e) |
|     FocusEvent     |         FocusListener<br />addFocusListener();         |    focusGained(FocusEvent e)<br />focusLost(FocusEvent e)    |
|     ItemEvent      |          ItemListener<br />addItemListener();          |                itemStateChanged(ItemEvent e)                 |
|      KeyEvent      |          KeyListener<br />addItemListener();           | KeyTyped(KeyEvent e)<br />keyPressed(KeyEvent e)<br />keyReleased(KeyEvent e) |
|     MouseEvent     |         MouseListener<br />addMouseListener();         | mouseChicked(MouseEvent e)<br />mousePressed(MouseEvent e)<br />mouseReleased(MouseEvent e)<br />mouseEnter(MouseEvent e)<br />mouseExited(MouseEvent e) |
|     MouseEvent     |   MouseMotionListener<br />addMouseMotionListener();   |   mouseDragged(MouseEvent e)<br />mouseMoved(MouseEvent e)   |
|     TextEvent      |          TextListener<br />addTextListener();          |                textValueChanged(TextEvent e)                 |
|    WindowEvent     |        WindowLIstener<br />addWindowLIstener();        | windowOpened(WindowEvent e)<br />windowClosing(WindowEvent e)<br />windowClosed(WindowEvent e)<br />windowIconified(WindowEvent e)<br />windowDeiconified(WindowEvent e)<br />windowActived(WindowEvent e)<br />windowDeactivated(WindowEvent e) |
|    ChangeEvent     |        ChangeListener<br />addChangeListener();        |                 stateChanged(ChangeEvent e)                  |
| ListSelectionEvent | LIstSelectionListener<br />addListSelectionListener(); |              valueChange(ListSelectionEvent e)               |

```java
import javax.swing.*;
import java.awt.event.*;

public class JButtonEvent1 extends JFrame{
    public JButtonEvent1() {
        JButton button = new JButton("按钮");
        button.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        System.out.println(((JButton) e.getSource()).getText());
                    }
                }
        );
        add(button);
    }

    public static void main(String[] args) {
        JButtonEvent1 frame = new JButtonEvent1();
        frame.setTitle("演示动作时间处理");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(100,100);
        frame.setVisible(true);
    }
}
```



## 33.3 监听接口适配器

在某些监听器接口定义了多个方法, 但是在实际编程中, 往往只会使用其中的部分方法, 即程序中只需要实现接口中的部分方法

为了解决这个问题,  java为所有具有多个方法的监听器接口提供了相应的适配器类

|    监听器接口     |    监听适配器    |     监听器接口      |     监听适配器     |
| :---------------: | :--------------: | :-----------------: | :----------------: |
| ComponentListener | ComponentAdapter |    MouseListener    |    MouseAdapter    |
| ContainerListener | ContainerAdapter | MouseMotionListener | MouseMotionAdapter |
|   FocusListener   |   FocusAdapter   |   WindowListener    |   WindowAdapter    |
|    KeyListener    |    KeyAdapter    |                     |                    |

```java
import java.awt.event.*;
import javax.swing.*;

public class TestWindowAdapter {
    public static void main(String[] args) {
        JFrame frame = new JFrame("测试 WindowAdapter ");
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.out.println("wzxyyds666");
                System.exit(0);
            }
        });
        frame.setSize(300,300);
        frame.setVisible(true);
    }
}
```

## 33.4 布局管理器

**使用方法 : **创建相应的布局管理器类对象, 然后调用类**Container** 中的方法**setLayout**将此对象设置为该容器的布局管理器

**默认布局管理器 : **BorderLayout 

对于被添加多次的组件, **以最后一次为有效添加**

```java
设置布局管理器:
public class BorderJButton extends JFrame{
    public BorderJButton() {
        setLayout(new BorderLayout());
    }
}
```

### 33.4.1 BorderLayout

将容器的内部空间划分为 **东 / 西 / 南 / 北 / 中 ** 五个区域, 它们分别以常量来表示

在添加组件时若无显式指定则默认添加到 **中央区域**

```java
BorderLayout.EAST(东);  BorderLayout.WEST(西);  BorderLayout.SOUTH(南);
BorderLayout.NORTH(北);  BorderLayout.CENTER(中);
```

**构造方法**

```java
public BorderLayout();	// 创建一个新的 BorderLayout 布局管理器对象, 组件之间没有水平和垂直间距
public BorderLayout(int hgap, int vgap);	// 以指定的水平和垂直间距创建一个新的 BorderLayout 布局管理器对象
```

**eg : **

```java
public BorderJButton() {
    setLayout(new BorderLayout());
    add(new Button("North"), BorderLayout.NORTH);
    add(new Button("South"), BorderLayout.SOUTH);
    add(new Button("East"), BorderLayout.EAST);
    add(new Button("West"), BorderLayout.WEST);
    add(new Button("Center"), BorderLayout.CENTER);
}
```



### 33.4.2 FlowLayout

根据容器的组件排列方式属性, 简单的将容器中的组件按添加的先后顺序依次摆放, 默认情况下, 容器中每一行的组件都是居中对齐的

**构造方法**

```java
public FlowLayout();	// 创建一个新的 FlowLayout 对象, 对齐方式是默认的居中对齐, 组件之间的水平和竖直间距都是默认的5个像素
public FlowLayout(int align);	// 以参数指定的对齐方式创建一个新的FLowLayout布局管理器对象, 组件之间的水平和垂直间距都是默认的5个像素
// 对齐方式 FlowLayout.LEFT(左对齐) FlowLayout.CENTER(居中) FlowLayout.RIGHT(右对齐)
public FLowLayout(int align, int hgap, int vgap);	// 以指定的对齐方式, 水平垂直间距创建一个新的FlowLayout布局管理器
```

**eg : **

```java
public FlowJButton() {
    applyComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);	// 修改排序方式为右至左
    setLayout(new FlowLayout(FlowLayout.LEFT,0,0));	// 设置左对齐, 水平和垂直间距为0px
    for (int index = 0; index < 9; index++) {
        add(new Button("JButton" + index));
    }
}
```

### 33.4.3 GridLayout

把容器的空间平均划分成若干行乘若干列的矩形网格, 每个网格中只能添加一个组件. 

如果程序指定了划分的具体非0行数, 编译器将忽略指定的具体列数, 实际列数将由指定的行数和实际的组件数决定

只有在行数指定为0时, 指定的列数才有实际意义

**构造方法**

```java
public GridLayout();	// 创建一个GridLayout管理器对象, 每行中只有一个列
public GridLayout(int rows, int cols);	// 以指定的行列数创建一个GridLayout布局管理器, 组件之间的水平垂直距离都是0
public GridLayout(int rows, int cols, int hgap, int vgap);	// 以指定参数创建布局管理器
```

**eg : **

```java
public GridJButton() {
    setLayout(new GridLayout(3,4,10,10));
    for (int index = 0; index < 9; index++) {
        add(new Button("JButton" + index));
    }
    add
}
```

### 33.4.4 CardLayout

将容器当作一个卡片盒 而把添加到容器中的每一个组件当作一张卡片, 每次只有一张卡片是可见的

**构造方法**

```java
public CardLayout();	// 创建一个间距为0的新的CardLayout
public CardLayout(int hgap, int vgap);	// 以指定的水平和垂直间距创建一个新的CardLayout布局管理器
```

**常用方法**

```java
public void first(Container parent);	// 显示容器中的第一张卡片
public void next(Container parent);		// 显示容器中的下一张卡片
public void previous(Container parent);	// 显示容器中的前一张卡片
public void last(Container parent);	// 显示容器中的最后一张卡片
public void show(Container parent, String name);	// 显示容器中指定名称的卡片

public void add(Component comp, String name);	// 添加组件并指定名称标识
```

**eg : **

```java
private CardLayout cl = new CardLayout();
private Container container = getContentPane();
public CardJButton() {
    JButton button;
    ActionListener listener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            cl.next(container); // container必须为内容框格
        }
    };
    setLayout(cl);
    for (int index = 0; index < 12; index++) {
        button = new JButton("JButton " + index);
        add(button, Integer.toString(index));
        button.addActionListener(listener);
    }
}
```

## 33.5 文本组件

Swing组件中, 具有用户输入和编辑文本功能的常用组件只有 **文本域 : JTextField / 文本区 : JTextArea / 密码域 : JPassword**

```java
Swing文本组件都继承于抽象类 javax.swing.text.JTextComponent:

public String getText();	// 返回当前文本组件中包含的文本
public void setText(String t);	// 将当前文本组件中的文本设定为指定文本
public boolean isEditable();	// 返回当前文本组件是否可编辑
public void setEditable(boolean b);	// 将当前文本组件设置为 可编辑 / 不可编辑
```

### 33.5.1 文本域

创建文本域需要使用类 **javax.swing.JTextField**

**构造方法**

```java
public JTextField();	// 创建一个文本域, 初始化字符串为空, 列数为0
public JTextField(String text);	// 创建一个文本域, 初始化字符串为 text, 列数为0
public JTextField(int columns);	// 创建一个文本域, 初始化字符串为空, 列数为columns
public JTextField(String text, int columns);	// 创建一个文本域, 初始化字符串为text, 列数为columns
```

**常用方法**

```java
public void addActionListener(ActionListener l);	// 向当前文本域注册动作事件监听器
public int getColumns();	// 返回当前文本域的列数
public void setColumns(int columns);	// 设置当前文本域的列数
public int getHorizontalAlignment();	// 返回当前文本的水平对齐方式
public int setHorizontalALignment(int alignment);	// 设置当前文本的水平对齐方式
public void setFont(Font f);	// 设置当前文本域中文本的字体
```

### 33.5.2 文本区

创建文本区需要使用类 **Javax.swing.JTextArea**

**构造方法**

```java
public JTextArea();	// 创建一个文本区, 初始化字符串为空, 行 / 列数 = 0
public JTextArea(String text);	// 创建一个文本区, 初始化字符串为text, 行 / 列数 = 0
public JTextArea(int rows, int columns);	// 创建一个文本区, 初始化字符串为空, 行 / 列 = rows / columns
public JTextArea(String text, int rows, int columns);	// 创建一个文本区, 初始化字符串为text, rows / columns
```

**常用方法**

```java
public void append(String str);	// 将字符串str添加到文本区中文本的末尾
public void replaceRange(String str, int start, int end);	// 用字符串str替换当前文本区中文本从start-end的字符
public void insert(String str, int pos);	// 将字符串str插入到当前文本区中文本的指定位置pos
public int getRows();	// 返回当前文本区的行数
public void setRows(int rows);	//设置当前文本区的行数
public int getLineCount();	// 返回当前文本区中所包含文本的行数
```

### 33.5.3 密码域

密码域是一种特殊的文本域,它也允许用户编辑单行文本

用户输入的每个字符都会用回显字符(默认情况下,回显字符一般是"*")表示

创建密码域需要使用类 **Javax.swing.JPasswardField**

```java
// 获取用户输入
public char[] getPassword();
// 设置回显字符
public void setEchoChar(char c);
```

## 33.5.4 面板

用来容纳组件的容器, 面板不能独立存在, 只能添加到其他容器中, 面板也可以添加到其他面板中

**面板是JPanel 的对象** 面板的默认布局是 **FlowLayout** 可直接容纳组件

```java
JPanel northPanel = new JPanel();	// 创建JPanel 对象
northPanel.setLayout(new FlowLayout());	// 设置布局管理器
northPanel.add(textField);	// 添加组件
northPanel.add(passwordField);
add(northPanel, BorderLayout.NORTH);	// 将其加入到页面中
```

## 33.6 选择组件

Swing组件中, 常用的选择组件有 按钮 JButton / 复选框 JCheckBox / 单选按钮 JRadioButton / 组合框 JComboBox

 / 列表 JLIst / 滑块 JSlider 等

### 33.6.1 按钮

**构造方法**

```java
public JButton();	// 创建一个不带标记的按钮
public JButton(Icon icon);	// 创建一个带指定图标的标签
public JButton(String text);	// 创建一个带指定文本的标签
public JButton(String text, Icon icon);	// 创建一个带指定文本和图标的标签
```

**常用方法**

```java
// JButton 是抽象类javax.swing.AbstractButton 的子类, 有以下常用方法:
public void addActionListener(ActionListener l);	// 注册动作事件监听器
public Icon getIcon();	// 返回按钮上的图标
public void setIcon(Icon defaultIcon);	// 设置按钮上的图标
public String getText();	// 返回按钮上的文本
public void setText(String text);	// 设置按钮上的文本
public void setEnabled(boolean b);	// 设置按钮的状态 true / false

public void setHorizontalAlignment(int alignment);	// 设置按钮上图标和文本的水平对齐方式
// SwingConstants.RIGHT \ SwingConstants.LEFT \ SwingContants.CENTER(默认值) \ SwingContants.LEADING \ SwingContants.TRAILING
public void setVerticalAlignment(int alignment);	// 设置按钮上图标和文本的垂直对齐方式
// SwingContants.CENTER(默认值) \ SwingConstants.TOP \ SwingContants.BOTTOM

public void setHorizontalPosition(int textPosition);	// 设置按钮上文本相对于图标的水平位置
// SwingConstants.RIGHT \ SwingConstants.LEFT \ SwingContants.CENTER \ SwingContants.LEADING \ SwingContants.TRAILING(默认值)
public void setVerticalTextPosition(int textPosition);	// 设置按钮上文本相对于图标的垂直位置
// SwingContants.CENTER(默认值) \ SwingConstants.TOP \ SwingContants.BOTTOM
```

### 33.6.2 标签

与按钮相同, 标签也可以显示文本和图像, 但无点击事件,

**构造方法**

```java
public JLabel();	// 创建空标签
public JLabel(Icon image);	// 创建具有指定图标的标签
public JLabel(Icon image, int horizontalAlignment);		// 创建具有指定图标和水平对齐方式的标签
public JLabel(String text);	// 创建具有指定文本的标签
public JLabel(String text, int horizontalAlignment);	// 创建具有指定文本和水平对齐方式的标签
public JLabel(String text, Icon image, int horizontalAlignment);	// 创建具有文本/图标/水平对齐方式的标签
```

**常用方法**

与按钮相似, 可调用方法设置或返回标签上的文本或图标, 对齐方式 , 相对位置

### 33.6.3 复选框

复选框是一个很小的方框, 方框旁边可以表有文本或图标, 复选框有两种状态 **选中 / 未选中**
**构造方法**

```java
public JCheckBox();	// 创建一个不带标记的复选框, 复选框未选中
public JCheckBox(Icon icon);	// 创建一个标有指定图标的复选框, 复选框未被选中
public JCheckBox(Icon icon, boolean selected);	// 创建一个标有指定图标的复选框, 复选框选中状态指定
public JCheckBox(String text);	// 创建一个标有指定文本的复选框, 复选框未被选
public JCheckBox(String text, boolean selected);	// 创建一个带有指定文本的复选框, 复选框选中状态指定
public JCheckBox(String text, Icon icon);	// 创建一个标有指定文本和图标的复选框, 复选框未被选中
public JCheckBox(String text, Icon icon, boolean selected);	// 创建标有文本和图标的复选框, 指定选中状态
```

### 33.6.4 单选按钮

创建单选按钮后, 需要将其归属到某个组中, 通过类 Javax.swing.ButtonGroup 进行分组 通过 add方法将按钮添加其中

点击单选按钮会触发事件 ActiveEvent 和 ItemEvent

**构造方法**

```java
public JRadioButton();	// 创建一个不带标记的单选按钮, 单选按钮未被选中
public JRadioButton(Icon icon);	// 创建一个标有指定图标的单选按钮, 单选按钮未选中
public JRadioButton(Icon icon, boolean selected);	// 创建一个标有指定标记的单选按钮, 指定选中状态
public JRadioButton(String text);	// 创建一个标有指定文本的单选按钮, 未被选中
public JRadioButton(String text, boolean selected);	// 创建一个标有指定文本的单选按钮, 指定选中状态
public JRadioButton(String text, Icon icon);	// 创建一个指定文本和图标的单选按钮, 未被选中
public JRadioButton(String text, Icon icon, boolean selected);	// 创建标有文本和图标的单选框, 指定选中状态
```

### 33.6.5 边框

为组件设置边框, 可用于区分不同的单选框组

```java
public void setBorder(Border border);

eg :
Border etched = BorderFactory.createEtchedBorder();	// 创建一个蚀刻边框
Border titled = BorderFactory.createTitledBorder(etched, "标题");
```

### 33.6.6 组合框

> 组合框 也称下拉式列表, 是一些项目的简单列表, 与单选按钮类似, 用户可以从中选择一个

**构造方法**

```java
public JComboBox();	//创建一个组合框, 组合框中的选项为空
public JComboBox(Object[] items);	// 创建一个组合框, 组合框中的选项由参数items指定
```

**常用方法**

```java
public void addItem(Object anObject);	// 在组合框中添加一个选项anObject
public void removeItem(Object anObject);	// 在组合框中删除参数指定的 anObject选项
public void removeItemAt(int anIndex);	// 在组合框中删除参数anIndex 索引的选项
public void removeAllItems();	// 删除组合框中的所有选项

public Object getItemAt(int index);	// 返回组合框中索引为参数index的选线
public int getSelectedIndex();	// 返回组合框中被选择的选项的索引值
public Object getSelectedItem();	// 返回组合框中被选中的选项
```

### 33.6.7 列表

在屏幕上持续占用指定行数的空间, 允许用户选中多个 : ctrl   /   shift

**构造方法**

```java
public JList();	// 创建一个列表, 列表中的选项为空
public JList(Object[] listData);	// 创建一个列表, 列表中的选项由参数listData指定
```

**常用方法**

```java
public int[] getSelectedIndices();	// 返回列表中所有被选中的选项的索引
public Object[] getSelectedValues();	// 返回列表中所有被选中的选项
public void setVisibleRowCount(int visibleRowCount);	// 设置列表中同时可见的首选行数, 默认为8
```



# 34 文件和流

## 34.1 文件和目录管理

### File 对象

**尽量使用相对路径(确保程序的跨平台性) 运行指定相对或绝对路径名**

```java
import java.io.File;
File file = new File("Data.txt");

public boolean canRead();	// 判断当前文件是否可读	
public boolean canWrite();	// 判断当前文件是否可读	
public boolean exists();	// 判断当前文件或对象是否存在	
public boolean isDirectory();	// 判断是否为已存在的目录		
public boolean isFile();	// 判断是否为已存在的文件		
public boolean isHidden();	// 判断是否为一个隐藏文件		

public boolean delete();	// 删除当前文件或目录	
public String getName();	// 返回当前文件或目录的名称	
public String getParent();	// 返回当前对象或目录的父目录的路径名	
public String getPath();	// 返回当前对象或目录的路径名	
public long lastModified();	// 返回最后一次被修改的时间	
public long length();		// 返回文件的长度(字节)		
public String[] list();		// 返回目录中的子对象	

public boolean mkdir();		// 创建当前File对象表示的目录		
public boolean mkdirs();	// 创建当前File对象表示的目录即所有不存在的父目录	

public boolean renameTo(File dest);		// 重命名		
```

## 34.2 字节流

### InputStream

所有的字节输入流派生于**抽象类**java.io.InputStream

文件字节输入流 : java.io.FileInputStream

字节数组输入流 : java.io.ByteArrayInputStream

过滤字节输入流 : java.io.FilterInputStream

```java
public abstract int read() throws IOException;	// 读取一个字节并返回对应的int型, 在输入流的末尾则返回-1
public int read(byte[] b) throws IOException;	// 读取最多b.length个字节并存储其中, 返回 <= b.length 或 -1

public long skip(long n) throws IOException;	// 跳过并丢弃 n 个字节, 返回实际跳过的字节数
public int available() throws IOException;	// 返回数据流中可读取的字节数
public void close() throws IOException;		// 关闭当前输入流, 并释放所有xiang
```

### OutputStream

所有的字节输出流派生于**抽象类**java.io.OutputStream

文件字节输出流 : java.io.FileOutputStream

字节数组输出流 : java.io.ByteArrayOutputStream

过滤字节输出流 : java.io.FilterOutputStream

**当使用文件输出流创建对象时,会覆盖原有文件内容或将内容追加到原有文件中**

```java
public abstract void write(int b) throws IOException;	// 将指定字节 b 写入当前输出流
public void write(byte[] b) throws IOException;		// 将数组b中的所有字节写入当前输出流
// 先写入内存缓冲区, 直到填满缓冲区\关闭输出流或调用方法flush,才将数据写出
public void flush() throws IOException;		// 刷新当前输出流, 写出缓冲的输出字节
public void close() throws IOException;		// 关闭当前输出流, 并释放所有相关资源
```

### FilterInputStream && FilterOutputStream

**以指定类型读取或写入数据**

```java
常用子类:
DataInputStream && DataOutputStream
	常用数据方法:
	readBoolean / readChar / readShort / readByte / readInt / readLong / readFloat / readDouble / readUTF(读取字符串)
	writeBoolean / writeChar / writeShort/ writeByte / writeInt / writeLong / writeFloat / writeDouble / writeUTF(写入字符串)

BufferedInputStream && BufferOutputStream
	缓冲输出/输入流. 进行I/O操作时,建立缓冲区,可以避免每次读写都访问基础设备,提高效率,建议都建立缓冲区

PrintStream
	可视化文本格式输出, System.out 就是PrintStream的一个子类
```

DataOutputStream 输出与平台无关的二进制, PrintStream 用于格式化输出, 可以以文本方式阅读

## 34.3 字符流

### Reader && Writer

**Reader && Writer 及其部分字符流子类和对应的字节流类**

|      字符流类      |    对应的字节流类     |
| :----------------: | :-------------------: |
|       Reader       |      InputStream      |
|       Writer       |     OutputStream      |
|   BufferedReader   |  BufferedInputStream  |
|   BufferedWriter   | BufferedOutputStream  |
|  CharArrayReader   | ByteArrayInputStream  |
|  CharArrayWriter   | ByteArrayOutputStream |
|    FilterReader    |   FilterInputStream   |
|    FilterWriter    |  FilterOutputStream   |
|    PrintWriter     |      PrintStream      |
| InputStreamReader  |          无           |
| OutputStreamWriter |          无           |

**InputStreamReader : **将一个字节输入流转换成字符输入流

**OutputStreamWriter : **将一个字节输出流转换成字符输出流

## 34.4 随机文件访问

**java.io.RandomAccessFile : 文件的随机访问**

```java
构造方法:
RandomAccessFile(File file, String mode);
RandomAccessFile(String name, String mode);
// file / name : 要访问的文件对象或文件名
// ① r: 以只读方式打开
// ② rw：打开以便读取和写入
// ③ rwd:打开以便读取和写入；同步文件内容的更新
// ④ rws:打开以便读取和写入；同步文件内容和元数据的更新
```

```java
常用方法:
public void seek(long pos) throws IOException;	// 设置文件光标位置向前偏移pos个字节, 打开文件时光标位于开始位置
public long length() throws IOException;	// 返回文件的字节长度
public long getFilePointer() throws IOException;	// 返回当前偏移的字节数(输入输出流光标位置)
```

## 34.5 对象I/O

```java
ObjectOutputStream : OutputStream 的子类, 并实现了DataOutputStream的全部功能, 定义了方法WriteObject(Object obj);
ObjectInputStream : InputStream 的子类, 并实现了DataOutputStream的全部功能, 定义了方法ReadObject(Object obj); 
```

**注意 : **

>1. 对象的读取顺序应与对象的写入顺序一致
>2. 读取对象的类文件应被加载完毕

## 34.6 对象序列化

可序列化对象: 实现了接口*Java.io.Serializable*

**transient (瞬态) : **关闭对象成员的序列化

```java
class Login implements java.io.Serializable {
    private String name;
    private transient String passward;	// 对象序列化时, 忽略数据成员
}
```

**对未实现序列化接口的数据成员 无法进行序列化**

**序列化时会自动忽略静态数据成员**

# 35 枚举和泛型

## 35.1 枚举类型

在Java语言中, 枚举类型是一种引用数据类型, 本质上说它是一种**特殊的类**

```java
枚举类型的定义:
enum 枚举类型名称 {枚举值1, 枚举值2, 枚举值3, ... ,枚举值n}
```

**枚举类型的声明与赋值**

```java
Weekday wd;
Weekday wd = Weekday.枚举值;
// 不能显式使用运算符 new 创建枚举类型的实例对象
```

**常用方法**

```java
public String toString();	// 返回当前枚举常量的名称
public final int ordinal();		// 返回会当前常量的序数 (0 ~ length-1)

Weekday[] wds = Weekday.values();	/// 获取枚举类型的全部枚举值
```

**枚举对象的比较**

```java
同一枚举类型的不同实例对象的值一定不相同,因此,在比较两个枚举变量引用对象的值是否相同时,不需要调用方法 equal()
直接使用运算符 == 

在 case 语句中 应使用相应枚举类型中定义的枚举变量, 且枚举常量前不能加限定名
```

## 35.2 泛型类 / 接口 / 方法

**泛型 : **将程序中的数据类型参数化, 通过它可以定义类型安全的泛型类 / 接口 / 方法

```java
Comparable<Integer> c = new Integer("1");
// 进一步指明了可以与当前对象C进行比较的那些对象的类型是 Integer
// 主要用于定义类型安全的泛型集合类型
System.out.println(c.compareTo("1"));	// 编译错误
```

**泛型类**

类型参数是一个标识符, 按照规范, 通常用单个大写字母表示

类型参数不能用于创建对象, 也不能用于instanceof运算等

创建泛型类或接口时, 指定的类型实参本身可以不是一个实际的类型

```java
public class Point<T> {	// 定义泛型类Point
    public final T x;
    public Point(T x) {	// 定义泛型类的构造方法时不能带尖括号部分
        this.x = x;
    }
}
使用:
// 类型实参必须为引用类型
Point<Integer> point2 = new Point<Integer>(new Integer(2), 4);	// 自动装箱
```

**泛型类 / 接口 可以带有多个类型参数**

```java
public interface Map<K, V> {
    V put(K key, V value);
    ...
}
public class HashMap<K, V> extends AbstractMap<K, V> implements Map<K, V>, Cloneable, Serialize {
    ...
}
```

**受限类型参数**

```java
// 无论限制条件类型为类或接口 必须使用关键字 extends
单受限:
class MyList<E extends java.lang.Number> {...}	// 类型参数E必须使用Number及其子类

多受限:
// 类和接口类型之间以符号 & 连接
class MyList<E extends NUmber & Comparable<E> & java,io.Serializable> {...}
```

**泛型方法**

Java语言中的所有方法均可以声明特定于其执行范围的类型参数

方法的类型参数应使用尖括号括起来放在方法修饰符的后面, 返回值类型的前面

泛型方法可以带有多个类型参数以及限制条件

```java
public static <T> void fromArrayToList(T[] arr, GenericList<T> list) {...}
```

调用泛型方法时,可以在方法名前面用尖括号指定方法的类型实参

```java
GenericList<Integer> list = new GenericList<Integer>();
MyClass.<Integer>fromArrayToList(arr, list);
MyClass.fromArrayToList(arr, list);	// 类型实参可省略
// 调用泛型方法时, 如果没有显式指定方法的类型参数, 编译器一般能根据方法调用表达式或其所在语句推断出正确的类型参数
```

## 35.3 通配符

```java
在 Java 中, 假设 A 是 B 的子类
G<T> 是一个泛型类(或接口), G<T> 并不是G<B>的子类型

GenericList<?> list = new GenericList<Ingeter>();	// 自动类型转换
list.add("1");	//编译错误
list.add(new Object());		// 编译错误
在通配符中, 链表list的元素类型是未知的, 不能将任何类型的对象添加到其中, 可以从一个元素类型位置的链表中取出元素.
```

**通配符的受限形式的受限条件中只能有一个类或接口类型**

```java
上受限类型 / 上限通配符 :
public static <T> void copy(List<? extends T> dest) {...}	// 类型?必须为T或其子类

下受限类型 / 下限通配符 :
public static <T> void copy(List<? super T> dest) {...}	// 类型必须为T或其父类
```

**使用案例**

```java
public static <T> void add(GenericList<T> list1, GenericList<T> list2) {...}

add((GenericList<Integer>) list1, (GenericList<Object>) list2);	// 编译错误
无法为T找到一个合法的实际类型

修改方法:
public static <T> void add(GenericList<T> list1, GenericList<? super T> list2) {...}
// 可以将元素是子类型的数组赋值给父类型的数组引用
```

## 35.4 java语言泛型的实现和局限性

**擦拭 Erasure :**

> 编译时消除程序中的所有泛型信息, 并将其转化成等价的非泛型代码
>
> 1. 擦除尖括号中的所有类型参数信息, java.util.List<Integer> ==> java.util.List
> 2. 将所有类型参数都替换为Object, 对带限制条件的类型参数, 替换为限制条件中的第一个限定类型
> 3. 擦除类型参数信息后, 在所有类型不正确的地方, 插入适当的强制类型转换

**局限性 : **

> 基本数据类型不能用作类型实参
>
> 类型参数不能用于创建对象
>
> 类型参数和泛型类型不能用于instanceof运算
>
> 不能创建泛型数组

```java
static <T > void speak(T speaks) {
    speaker.talk();	// 编译错误
}
因 擦拭 的编译方法, 无法在 Object 类中调用自定义的 talk() 方法

修改方法 定义 Speakers 接口:
static <T extends Speakers> void speak(T speaks) {...}
```

# 36 集合

```java
Collection:
集合中的每个元素都是一个独立的对象, 其中 List 集合以特定顺序容纳元素; set集合中不能有重复的元素; Queue集合只允许在一端插入一段输出; Deque 集合允许在它的两端同时插入和移除元素

Map:
集合中的每个元素都是一对key / value 对象, 且同一集合中, 每个元素的键都不能相同, 通过Map集合, 可以将一个对象映射到另一个对象
```

**集合架构常见集合的继承(实现)关系**

## **36.1 集合操作**

```java
Collection:
boolean add(E);
boolean add(int index, E);

Map:
V put (K key, V value);
```

**泛型集合**

```java
List<Integer> ints = new ArrayList<Integer>();
```

## **36.2 迭代器与foreach语句**

```java
// 迭代器对象的创建
public interface java.lang.Iterable<T> {
    Iterator<T> interator;
}
Iterator<T> it = ints.interator;	// 迭代器对象
```

## Iterator 接口

```java
// 迭代器接口的定义
public interface Iterator<E> {
    E next();	// 返回当前迭代器指向的集合中的下一个元素
    Boolean hasNext();	// 检查当前迭代器指向的集合中是否还有其他元素可迭代, 如有, 返回true
    void remove();	// 从当前迭代器所指向的集合中移除迭代器最近返回的元素
}
```



## **36.3 Collection 接口**

```java
Collection 的定义:
public interface Collection<E> extends Iterable<E> {
    boolean add(E o);	// 确保当前集合中包含指定元素, 没有则返回 false
    boolean addAll(Collection<? extends E> c);	// 将指定集合中的所有元素添加到当前集合中, 添加了其中任一元素则返回True
    void clear();	// 移除当前集合中的所有元素
    boolean contains(Object o);	// 是否包含指定元素
    boolean contains(Collection<?> c);	// 当前集合是否包含指定集合中的所有元素
    boolean equals(Object o);	// 将集合与指定对象作相等性比较
    int hashCode();	// 返回当前集合的hash值
    boolean isEmpty();	// 当前集合中是否不含任何元素
    boolean remove(Object o);	// 移除当前集合中的一个指定元素
    boolean removeAll(Collection<?> c);	// 从当前集合中移除所有包含在指定集合中的元素
    boolean retainAll(Collection<?> c);	// 在当前集合中仅保留指定集合中也含有的元素
    int size();	// 返回当前集合中元素的个数
    Object[] toArray();	// 返回一个包含当前集合中所有元素的数组
    <T> T[] toArray(T[] a);	// 返回一个包含当前集合中所有元素的数组, 返回数组的元素类型和参数指定的元素类型
}
```

## 36.4 List 接口

```java
public interface List<E> extends Collection<E> {
    void add(int index, E element);	// 在当前集合的指定位置插入指定元素
    boolean addAll(int index, Collection<? extends E> c);	// 在当前集合的指定位置插入指定集合中的所有元素
	E get(int index);	// 返回当前集合中指定位置的元素
    int indexOf(Object o);	// 返回指定元素的当前集合中第一次出现的位置, 或 -1
    int lastIndexOf(Object o);	// 返回指定元素在当前集合中最后一次出现的位置, 或 -1
    ListIterator<E> listIterator();	// 返回当前集合元素的ListIterator对象, 迭代器指向当前集合的起始位置
    ListIterator<E> listIterator(int index);	// 返回当前集合元素的ListIterator对象, 迭代器指向index位置
    E remove(int index);	// 移除当前集合中指定位置的元素, 并返回该元素
    E set(int index, E element);	// 用指定元素替换当前集合指定位置的元素,并返回被替换的元素
    list<E> subList(int fromIndex, int toIndex);	// 返回一个包含当前集合位置fromIndex 至 toIndex-1 中元素的子集
}

public interface ListIterator<E> extends Iterator<E> {
    void add(E o);	// 在当前迭代器指向的集合的指定位置前插入指定元素
    boolean hasPrevious();	// 检查当前迭代器指向的集合的当前位置前是否还有其他元素
    int nextIndex();	// 返回当前迭代器指向的集合中的下一个元素的位置
    E previous();	// 返回当前迭代器指向集合中的前一个元素
    int perviousIndex();	// 返回当前迭代器指向集合中的前一个元素的位置
    void set(E o);	// 用指定元素替换当前迭代器最近返回的元素
}
```

## 36.5 Queue 接口

典型的队列是一种**"先进先出(FIFO)"**集合, 即元素从集合的某一端放入, 另一端取出, 并且元素放入集合的顺序与取出的顺序相同

```java
public interface Queue<E> extends Collection<E> {
    boolean add(E e);	// 将指定元素添加到当前队列的尾部
    boolean offer(E e);	// 将指定元素添加到当前队列的尾部
    E remove();	// 获取并移除当前队列头部的元素
    E poll();	// 获取并移除当前队列头部的元素
    E element();	// 获取但不删除当前队列头部的元素
    E peek();	// 获取但不移除当前队列头部的元素
   
}
```

## 36.6 Deque 接口

双端队列, 双端队列允许在它的双端同时添加和移除元素

```java
继承于Queue,定义了支持双端队列操作的方法:
// 以下方法在操作失败时抛出异常
void addFirst(E e);	// 将指定元素插入当前双端队列的头部
void addLast(E e);	// 将指定元素插入当前双端队列的尾部
E removeFirst();	// 获取并移除当前双端队列头部的元素
E removeLast();		// 获取并移除当前双端队列尾部的元素
E getFirst();	// 获取但不移除当前双端队列头部的元素
E getLast();	// 获取但不移除当前双端队列尾部的元素

// 以下方法在操作失败时返回false
boolean offerFirst(E e);	// 将指定元素插入到当前双端队列的头部
boolean offerLast(E e);		// 将指定元素插入到当前双端队列的尾部

// 以下方法在操作失败时返回null
E pollFirst();	// 获取并移除当前双端队列头部的元素
E pollLast();	// 获取并移除当前双端队列尾部的元素
E peekFirst();	// 获取但不移除当前双端队列头部的元素
E peekLast();	// 获取但不移除当前双端队列尾部的元素
```

## 36.7 Collections 类

```java
public static <T> boolean addAll(Collection<? super T> c, T...elements);	// 将所有指定元素 elements添加到指定集合 c 中
public static <T extends Comparable<? super T> > void sort(List<T> list);	// 根据自然顺序对指定List集合中的元素从小到大排序. 
public static <T> int binarySearch(List<? extends Comparable<? super T> > list, T key);	// 在一个已经按元素的自然顺序从小到大排序的指定List集合中查找指定元素key, 或 负值
public static <T> void copy(List<? super T> dest, List<? extends T> src);	// 将集合src中的所有元素复制到dest中的相同位置.
public static <T extends Object & Comparable<? super T> > T max(Collection<? extends T> coll);	// 根据元素的自然顺序, 返回指定Collection集合中的最大元素.
public static <T extends Object & Comparable<? super T> > T min(Collection<? extends T> coll);	// 根据元素的自然顺序, 返回指定Collection集合中的最小元素.
public static void reverse(list<? > list);	// 将指定List集合中的所有元素反转到相应的位置
```

#### 

