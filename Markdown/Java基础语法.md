# 基础语法

## 标识符

- Java 的标识符可以为任意长度

- 由 字母 / 数字 / $ / _   组成

- 首字母不可为数字

- 严格区分大小写
***

## 关键字

- `const	goto `	无实际用途,目前仅为**占位符**

|            |            |              |             |                |
|:----------:|:----------:|:------------:|:-----------:|:--------------:|
| `abstract` | `continue` |    `for`     |    `new`    |    `switch`    |
|  `assert`  | `default`  |    `goto`    |  `package`  | `synchronized` |
| `boolean`  |    `do`    |     `if`     |  `private`  |     `this`     |
|  `break`   |  `double`  | `implements` | `protected` |    `throw`     |
|   `byte`   |   `else`   |   `import`   |  `public`   |    `throws`    |
|   `case`   |   `enum`   | `instanceof` |  `return`   |  `transient`   |
|  `catch`   | `extends`  |    `int`     |   `short`   |     `try`      |
|   `char`   |  `final`   | `interface`  |  `static`   |     `void`     |
|  `class`   | `finally`  |    `long`    | `strictfp`  |   `volatile`   |
|  `const`   |  `float`   |   `native`   |   `super`   |    `while`     |

### transient

- **瞬态**：关闭对象成员的序列化

```java
class Login implements java.io.Serializable {
    private String name;
    // 对象序列化时, 忽略数据成员
    private transient String passward;	
}
```

### volatile

- 并发编程下，多线程修改变量，会出现线程间变量的不可见性
- 按照**JMM模型**，所有的成员变量和静态变量都存在于主内存中，主内存中的变量可以被多个线程共享。每个线程都存在一个专属于自己的工作内存，工作内存一开始存储的是成员变量的副本。所以线程很多时候都是直接访问自己工作内存中的该变量，其他线程对主内存变量值的修改将不可见！！

- 通过`volatile`修饰变量，实现并发编程中变量的可见性
- `volatile`**只能保证可见性，不能保证原子性**（不保证线程安全）

### instanceof

```java
B instanceof A
if ((B是指定类A || B派生于A || B实现了指定接口A) && B != null) { return true;}
else { return false; }
```

### final

1. 任何一个变量被 final 修饰后 一旦初始化,其值便不可改变

2. 与**abstract**互斥

**final 类**

```java
//  不可被继承 (java.long.String 不可被继承)
final class test{}
```

**final 方法** 

```java
// 不可被重写，可以重载
final void test(){}
```

**final 局部变量** 

```java
// 变量有且仅能被赋值一次 **常量**
// 只能在创建时赋值一次
final int test = 0;
```

**final 静态变量**

```java
// 产生常量 **只能在创建 或 静态代码块 中赋值一次**
public static final int TEST = 1;
```

**final 实例变量**

```java
// 意义不大，只能赋值一次，直接用 static 代替
// 复制地点: 定义，实例代码块，构造器
```

### public

- 权限修饰符, 在任意地方可以访问

### protected

- 权限修饰符, 可以在本类、本包、其他包继承本类的子类中访问

### **权限修饰符缺省**

- 在本类、本包中访问

### private

- 权限修饰符, 只能在本类中访问

***

##  基本数据类型

**在不同平台中各种基本数据类型占用储存空间长度固定, 保证了 `Java` 语言的跨平台性**

**整数类型**

```Java
byte	a;	// 1 字节
short	a;	// 2 字节
int 	a;	// 4 字节
long a = 100l;	// 8 字节
```

**浮点数类型**

**默认为 double 类型**

```java
float a = 3.14F;	// 单精度浮点数 4 字节
double b = 3.14D;	// 双精度浮点数 8 字节
Double.isNaN(b);	// whether is not a number 
```

**字符类型**

```java
char a = "字符类型"	// 支持Unicode ==> \u____(四位)
```

**布尔类型**

```java
boolean a = "布尔类型" // false or true
```

***

## 变量

**变量**

数据类型	变量名1,变量名2....;

**不提倡多变量在一行内声明**

`int a,b,c;`

**常量**

标识符字母均 大写

`final double PI = 3.1415926535 `

## 	基本运算方法

```java
加	add		减	subtract
乘	multipiy	除	divide

BigInteger num3 = num1.divide(num2);
```

***

## 数组

**数组引用的声明**

```java
数据类型[] 数据名;
数据类型 数据名[];		// 源于 C语言 , 不推荐
数组名.length ==> 返回数组长度
```

**数组的创建**

元素个数的表达式的值必须 >= 0 且为整数	~~long~~

```java
数组名 = new 数据类型[元素个数表达式];
```

**元素的访问**

```java
数组名[下表表达式]		下表表达式  0 ==> MAX-1
```

**对象数组**

数组中存放的是 **对象引用** ,访问前需要通过 new 创建实例

```java
class1[] class = new class[3];
```

**初始化数组**

```java
int[] arr = new int[]{1,2,3,};		// 第二个int后的方框不可指定元素个数
int[] arr = {1,2,3,};
```

**多维数组**

```java
1:
int[][] arr = new int[3][4];	// 不推荐使用

2:
int[][] arr = new int[3][];
arr[0] = new int[4];    // 多维数组可不等长
arr[1] = new int[3];
arr[2] = new int[7];
```

**多维数组初始化**

```java
int[][] arr1 = {{1,2,},{3,4,},}
```

**数据的传递**

```java
public void fun(int[] a) {...}; 
```

**可变参数列表**

- 以数组为参数 , 将**同一类型不定数目**的变量一次性传递
- 数据类型...参数名    生成 **可变参数列表**

```java
public void fun(int...array) {...}
```

**返回值-数组**

```java
int[] a = new int[10];
return a;	// 返回的只是一个引用数组的引用
```

***

## 运算符

**算数运算符**

Java 中可连续赋值

```java
+(加)	-(减)	*(乘)	/(除)	%(求余)
```

int型 / int型		**取整**

(float / double) / int型		**除法,返回 float / double**

`++(自增) / --(自减)`  	(前置 后置 与C语言相同)

**关系运算符**

```java
<	<=	>	>=	==	!=
```

**逻辑运算符**

```java
!	&&	||	^        // false / true
```

**复合赋值运算符**

```java
+=	-=	*=	/=	%=	<<=	>>=
```

**条件运算符**

```java
(a)?(b):(c) // 三元运算符
等价于 if( a ) { b } else { c }
```

## 类型转换

**自动类型转换**

```java
byte ==> short ==> int ==> long ==> float ==> double
    	  char ==> int...
```

**强制类型转换**

```java
float x = (float) a;
```
***

## 控制台的输入和输出

### 	System 类

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
### Scanner 类

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

## 条件语句

### 	if 语句

```java
if (boolean) {
    语句1;
} else if (boolean) {
    语句2;
} else {
    语句3;
}
```

### 	switch 语句

```java
switch (表达式) {
    case a : {}
	case b : {}
	case c : {}
    defafult: {}
}
```
***

## 循环语句

### 	while 循环

```java
while (boolean) {
    语句;
}
```

### 	do - while 循环

```java
do {
    语句;
} whlie (boolean);
```

### 	for 循环

```java
for (a ; boolean ; c) {
    语句;
}
```
### foreach 语句

- 迭代器遍历的简化写法 可以**遍历集合以及数组**
- 只能顺序遍历所有内容

```java
// 数据类型 必须与数组元素类型相同
for (数据类型 变量名 : 集合 or 数组) {	
    语句
}
```

***

##  跳转语句

- break;	跳出当前一层循环 / switch	(无标号)

- break 标号;		跳出标号指定的循环体

- continue;		结束一层循环,并开始下一次迭代	(无标号)

- continue 标号;		结束本次循环,并开始目标层的下一次循环;

**标号**

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

## 方法

### 		递归调用

```java
public long  factorial(int num) {
    if (num <= 1) {
        return 1;
    } else {
        return num * factorial(num-1);
    }
}
```

### 	方法重载

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

## 类和对象

### 	类的定义

- 类名的首字母建议大写。满足**驼峰模式**。 
- 定义类：**一个Java文件可以定义多个类**（一个文件尽量只放一个类）。但是只有一个类是用public修饰，public修饰的类名必须称为Java文件名。
- 类中有且仅有**5大成分**
  - **成员变量Field**：描述类或者对象的属性信息的。
  - **成员方法Method**：描述类或者对象的行为的。
  - **构造器**（构造方法,Constructor）: 初始化类的一个对象返回。
  - **代码块**
  - **内部类**

面向对象的三大特征：==**封装，王子xiao

### 构造器

- **作用**：通过调用构造器可以返回一个类的对象，构造器同时负责帮我们把对象的数据（属性和行为等信息）初始化好。

- 构造器的**分类**：无参数构造器，有参数构造器。

- 构造器的注意点：一个类默认自带一个无参数构造器，但是**如果写了有参数构造器那么默认的无参数构造器就消失了**，此时如果还需要用无参数构造器就需要自己从新写一个。

```java
  修饰符 类名(形参列表) {
      // 构造体代码，执行代码
  }
```
### this

this代表了当前对象的引用。
this关键字可以用在实例方法和构造器中。
this用在方法中，谁调用这个方法，this就代表谁。
this用在构造器，代表了构造器正在初始化的那个对象的引用。

```java
Cat () { this(2,4); }	
cat (int age; int weight) {...}	
```

==调用语句必须放在方法体的第一句==

其他方法中不能调用构造方法,且参数不能为this的参数或实例成员

### 静态

**静态变量**

- 一个静态变量永远只有一份存储空间 通过==static==修饰的变量

- 可以通过类名访问，**也可以通过对象名访问,但不推荐。**

```java
public class A { static int i= 0;}
A.i++;	// i = 1
A a1 = new A();		// i = 2
```

**静态方法**

类的方法,通过类名直接访问，**也可以通过对象名访问,但不推荐.**

```java
public class Cat {
    int age;
    static int getAge() {
        return age;
    }
}
```

**静态变量的初始化**

- 不能在**构造方法**中初始化 final 静态变量

- 声明指定初值初始化静态变量,**不可使用类的实例对象**

- 仅在**第一次**用到该变量的时候进行初始化

### 代码块

**静态代码块**：

- 在类被加载时执行一次

```java
static {...}
```

**实例代码块**：

- 在创建类的对象时,**构造方法执行前执行**
- 实例代码块的代码在底层实际上是提取到每个构造器中去执行的

```java
{...}
```
***

## **封装**

-  形成了规范，即使毫无意义还是会这样写代码！
-  合理隐藏，合理暴露。
-  封装的规范：成员变量私有，方法一般公开，提供成套的**getter和setter方法**暴露成员变量的取值和赋值。
-  封装的作用：提高安全性，提高代码的组件化思想。
-  封装已经成为Java代码的规范，即使毫无意义，我们也要这样写代码（成员变量私有，方法公开）

1.使用 `private` 关键字来修饰成员变量。

2.使用`public`修饰getter和setter方法。

## 继承

- **继承**：就是子类继承父类的**属性**和**行为**，使得子类对象可以直接具有与父类相同的属性、相同的行为。子类可以直接访问父类中的**非私有**的属性和行为。
- 若无显示指定,就隐含继承于类 **java.lang.Object**
- Java 中仅支持**单继承** 即只可有一个指定的父类
- 优点：提高**代码的复用性**（减少代码冗余，相同代码重复利用）。使类与类之间产生了关系。

```java
public 子类名 extends 父类名 {
    定义子类新成员
}
```

**子类的构造方法**

- **子类不能继承父类的构造器，因为子类有自己的构造器。**

- 在创建子类对象时, 系统会自动调用父类的构造方法

  ​	1.调用父类默认的构造方法

  ​	2.通过 super(); 显式调用父类含参数的构造方法, 参数不能为 this 或 当前对象的实例对象

- **值得注意的是子类可以继承父类的私有成员（成员变量，方法），只是子类无法直接访问而已，可以通过getter/setter方法访问父类的private成员变量。**

**方法覆盖**

- 子类中的方法应与父类中被覆盖的方法有相同的方法名,返回值类型 和 形参类型列表(形参名可不同)

- 可通过 **super.方法名** 调用父类的数据成员和方法

- **方法覆盖时 , 不可修改其访问权限**

**方法重载**

- 当对应形参类型或者参数个数不同,则为方法重载
- `@Override`

**数据成员和静态方法隐藏**

- 子类中可改写父类的数据成员和静态方法	不支持多态
- 使用 **super.成员** 访问父类被隐藏的数据成员 或 方法名

## 多态

父类类型的范围 > 子类类型的范围

**概念**：

- 同一个类型的对象，执行同一个行为，在不同的状态下会表现出不同的行为特征
- **方法**：编译看左边，运行看右边**(调用父类的抽象方法，执行子类中重写的实现方法)**
- **变量**：没有多态

**使用前提**：

- 必须存在继承或实现关系
- 必须存在父类类型的变量引用子类类型的对象
- 需要存在方法重写

**特点**：

- ==优点：== 子类类型可以实现组件化切换，(**业务功能不变的情况下切换功能内容**)，便于扩展和维护，实现类与类之间的解耦
- ==优点：==将父类类型作为方法参数，传递子类给方法，体现多态的扩展性和便利
- ==缺点：==通过父类**不能访问子类的特有方法和成员变量**，只能访问重写的父类方法和父类的成员变量
- **调用子类特有方法和成员变量**：强制类型转换为子类
- 开发逻辑中，**应先判断子类的真实类型再强制转换**，否则运行异常但编译不异常 **instanceof判断**

```java
父类类型 name = new 子类构造器();
接口 name = new 实现类构造器();
```

***
## 抽象

### 	抽象类
```java
abstract class A {...}
```
**特点**：
 1. 抽象类不一定含有抽象方法,含有抽象方法的一定是抽象类
 2. 为继承而定义 / 只能做父类 
 3. 抽象类必须有构造器供子类调用，但**不能创建对象** => ==抽象意味着不能具体化==
 4. 抽象父类中的所有抽象方法**都要在子类中覆盖**,否则子类仍为抽象类

**警告**：

- 静态方法不能声明为抽象的
- final 方法不能声明为抽象的 这与 final 方法不支持 子类覆盖有关
- final 类不能声明为抽象的 这与final 类无法被继承有关 

### 抽象方法

```java
abstract 返回值类型 方法名(形式参数表);
```

- **只有方法签名 无方法体**

### 抽象类设计模板设计模式

设计模式：
- 是前人（技术大牛，或者一些技术协会，或者一些大型知名的IT公司）
- 已经研发好或者设计好或者在实战开发中发现的的优秀软件设计思想，开源出去
- 后来者可以直接使用就能够得到很好的软件模式。

设计模式的目的：
- 得到优秀的软件架构，从而提升代码的可重用性，扩展性，维护性，可读性。

模板模式的作用：
- 部分实现，部分抽象，可以极大的简化功能代码，提高开发效率
***
## 接口

**JDK 1.8 之前，接口只能拥有抽象方法和常量**

```java
(public) interface name {
    // 公用静态常量 变量名全大写，用_连接
    (public static final) String TEST_ONE = "test";	
    // 公用抽象方法
    (public abstract) void fun();		
}
```

**特点**：

- 实现接口的类必须重写完接口中全部的抽象方法，否则定义为抽象类
- 不可定义构造方法
- 在一个类实现接口时,方法必须由 public 修饰
- 若未实现某接口的的全部方法,则产生抽象类 , 类必须由 abstract 修饰
- 接口称为被**实现**，实现接口的类称为**实现类**

**接口实现**：

- 多实现 单继承

```java
// 接口可以多实现
class D extends C implements A,B {...}	
```

**接口继承**:

- 多继承

```java
interface A extends B,C {...}
```

**JDK 1.8 之后，新增的接口方法**

```java
(public) interface name {
    // 默认方法: 示例方法，使用 default 修饰 只能用接口的实现类的对象调用
    default void run(){ return 0 }
    // 静态方法: static修饰 只能用接口名本身来调用
    static void run(){ return 0 }
    // 私有方法: private修饰 私有实例方法 -- JDK 1.9
    private void run(){ return 0 }
}
```

***

## 枚举类型

**枚举类的作用**：

- 信息标志和信息分类
- 在Java语言中, 枚举类型是一种引用数据类型, 本质上说它是一种**特殊的类**

```java
枚举类型的定义:
enum 枚举类型名称 {枚举值1, 枚举值2, 枚举值3, ... ,枚举值n}
```

**枚举类的特点**:

1. 枚举类是用**final修饰**的，枚举类不能被继承
2. 枚举类默认继承了枚举类 java.lang.Enum
3. 枚举类的第一行罗列的是枚举类的对象名称，而且是用常量存储的
4. 枚举类的构造器默认是私有的
5. 枚举类相当于**多例对象，多例设计模式**

**反编译**：

```java
public final class Sex extends java.lang.Enum<Sex> {
  public static final Sex BOY;
  public static final Sex GIRL;
  public static Sex[] values();
  public static Sex valueOf(java.lang.String);
  static {};
}
```

**枚举类型的声明与赋值**

```java
// 不能显式使用运算符 new 创建枚举类型的实例对象
Weekday wd;
Weekday wd = Weekday.枚举值;
```

**常用方法**

```java
// 返回当前枚举常量的名称
public String toString();
// 返回会当前常量的序数 (0 ~ length-1)
public final int ordinal();		
// 获取枚举类型的全部枚举值
Weekday[] wds = Weekday.values();	
```

**枚举对象的比较**

1. 同一枚举类型的不同实例对象的值一定不相同,因此,在比较两个枚举变量引用对象的值是否相同时,不需要调用方法 `equal()`
2. 直接使用运算符 == 
3. 在 case 语句中 应使用相应枚举类型中定义的枚举变量, 且枚举常量前不能加限定名

***

## 内部类

- 在一个类的内部定义另一个类
- 对象引用的创建 ”**外部类名.内部类名** `OuterClass.InnerClass`

**实例内部类**：

- 无`static`修饰，属于外部类的**对象**，与外部类的对象一起加载
- 不能在实例内部类中定义静态成员
- 可以定义常量`public static final`
- 实例内部类可以直接访问外部类的静态成员
- 实例内部类可以直接访问外部类的实例成员
```java
OuterClass t = new OuterClass();
OuterClass.InnerClass tin2 = t.new InnerClass();
```

**静态内部类**：

- 有`static`修饰，**属于外部类本身**， 只加载一次
- 静态内部类可以直接访问外部类的静态成员
- 静态内部类不可以访问外部类的实例成员
- **特点与外部类相同**，只是位于外部类内部

```java
// 外部类名.内部类名
OuterClass.InnerClass innerClass = new OuterClass.InnerClass();
```

**匿名内部类**：

- 没有名字的局部内部类
- 即时返回一个匿名内部类的对象
- 匿名内部类的对象的类型，相当于当前new的子类类型的对象
- **在编译时会产生class文件**：`OuterClass.InnerClass.class`

```java
abstract class Animal {
    public static void run();
}

Animal animal = new Animal(){
    @Override
    public void run() {}
}
```
***

## 泛型

- **泛型 : **将程序中的数据类型参数化, 通过它可以定义类型安全的泛型类 / 接口 / 方法
- 泛型和集合都**只支持引用数据类型**不支持基本数据类型
- JDK 1.7 开始之后，泛型后面的数据类型可以省略

```java
Comparable<Integer> c = new Integer("1");
// 进一步指明了可以与当前对象C进行比较的那些对象的类型是 Integer
// 主要用于定义类型安全的泛型集合类型
System.out.println(c.compareTo("1"));	// 编译错误
```

### 泛型特点

**泛型类 / 接口 可以带有多个类型参数**

```java
public class HashMap<K, V> extends AbstractMap<K, V> implements Map<K, V>, Cloneable, Serialize {
    ...
}
```

**受限类型参数**

- 无论限制条件类型为类或接口 必须使用关键字 extends
- 类和接口类型之间以符号 & 连接

```java
单受限:
class MyList<E extends java.lang.Number> {...}	// 类型参数E必须使用Number及其子类

多受限:
class MyList<E extends NUmber & Comparable<E> & java,io.Serializable> {...}
```

**java语言泛型的实现 擦拭 Erasure :**

- 编译时消除程序中的所有泛型信息, 并将其转化成等价的非泛型代码
  - 擦除尖括号中的所有类型参数信息, java.util.List<Integer> ==> java.util.List
  - 将所有类型参数都替换为Object, 对带限制条件的类型参数, 替换为限制条件中的第一个限定类型
  - 擦除类型参数信息后, 在所有类型不正确的地方, 插入适当的强制类型转换

### 泛型类

- 类型参数是一个标识符, 按照规范, 通常用单个大写字母表示(E、T、K、V)
- 类型参数不能用于创建对象, 也不能用于instanceof运算等
- 创建泛型类或接口时, 指定的类型实参本身可以不是一个实际的类型
- **泛型没有继承关系**

```java
// 定义泛型类Point
public class Point<T> {
    public final T x;
    
    public Point(T x) {	
        this.x = x;
    }
}
使用:
// 类型实参必须为引用类型
Point<Integer> point2 = new Point<Integer>(new Integer(2), 4);	// 自动装箱
```

### **泛型方法**

- Java语言中的所有方法均可以声明特定于其执行范围的类型参数
- 方法的类型参数应使用尖括号括起来放在方法修饰符的后面, 返回值类型的前面
- 泛型方法可以带有多个类型参数以及限制条件
- 不能创建泛型数组

```java
public static <T> void fromArrayToList(T[] arr, GenericList<T> list) {...}
```

- 调用泛型方法时,可以在方法名前面用尖括号指定方法的类型实参

```java
GenericList<Integer> list = new GenericList<Integer>();
MyClass.<Integer>fromArrayToList(arr, list);
MyClass.fromArrayToList(arr, list);	// 类型实参可省略
// 调用泛型方法时, 如果没有显式指定方法的类型参数, 编译器一般能根据方法调用表达式或其所在语句推断出正确的类型参数
```

### 泛型接口

- 在实现接口时传入确定的数据类型，重写的方法即对该数据类型的操作

```java
public interface Map<K, V> {
    V put(K key, V value);
    ...
}
```

### 通配符

- 在**使用泛型**时，代表一切类型

```java
在 Java 中, 假设 A 是 B 的子类
G<T> 是一个泛型类(或接口), G<T> 并不是G<B>的子类型

GenericList<?> list = new GenericList<Ingeter>();	// 自动类型转换
list.add("1");	//编译错误
list.add(new Object());		// 编译错误
在通配符中, 链表list的元素类型是未知的, 不能将任何类型的对象添加到其中, 可以从一个元素类型位置的链表中取出元素.
```

**通配符的受限形式**

- 受限条件中只能有一个类或接口类型

```java
// 上受限类型 / 上限通配符 :
// 类型 ? 必须为 T 或其子类
public static <T> void copy(List<? extends T> dest) {...}	

// 下受限类型 / 下限通配符 :
// 类型 ? 必须为 T 或其父类
public static <T> void copy(List<? super T> dest) {...}	
```

***

## 迭代器

**Iterator 接口**

```java
// 迭代器接口的定义
public interface Iterator<E> {
    // 返回当前迭代器指向的集合中的下一个元素
    E next();
    // 检查当前迭代器指向的集合中是否还有其他元素可迭代, 如有, 返回true
    Boolean hasNext();
    // 从当前迭代器所指向的集合中移除迭代器最近返回的元素
    void remove();
}
```

**迭代器的获取**

```java
Iterator<String> it = lists.iterator();
```

***

## 可变参数列表

- 参数用在形参中可以接收多个数据。
- 可变参数的格式：数据类型... 参数名称

**可变参数的作用**：

- 传输参数非常灵活，方便。
- 可以不传输参数。
- 可以传输一个参数。
- 可以传输多个参数。
- 可以传输一个数组。

**可变参数在方法内部本质上就是一个数组**
**注意事项**：

1. 一个形参列表中**可变参数只能有一个**
2. 可变参数**必须放在形参列表的最后面**

```java
// 定义
public static void sum(int...nums);

// 使用
sum(); // 可以不传输参数。
sum(10); // 可以传输一个参数。
sum(10,20,30); // 可以传输多个参数。
sum(new int[]{10,30,50,70,90}); // 可以传输一个数组。
```

***

# 集合

- 集合是一个大小可变的容器。
- 容器中的每个数据称为一个元素。数据==元素。
- 集合的特点是：类型可以不确定，大小不固定。集合有很多种，不同的集合特点和使用场景不同。
- 数组：类型和长度一旦定义出来就都固定了。

**Collection 接口**：

- Java中所有集合直接或间接实现Collection接口
- **集合架构常见集合的继承(实现)关系**
  1. **Set集合接口**：集合中的元素 **无序、不重复、无索引**
     - **HashSet 实现类**：无序、不重复、无索引
       - **LinkedHashSet 实现类**：有序、不重复、无索引
     - **TreeSet 实现类**：按照元素大小默认升序排序、不重复、无索引
  2. **List集合接口**：集合**以特定顺序容纳元素、有索引**
     - **ArrayList 实现类**：添加的元素是有序、可重复、有索引，查询快，增删慢
     - **LinkedList 实现类**：添加的元素是有序、可重复、有索引，增删快，查询慢

## Collection 接口

```java
Collection 的定义:
public interface Collection<E> extends Iterable<E> {
    // 移除当前集合中的所有元素
    void clear();
    // 将集合与指定对象作相等性比较
    boolean equals(Object o);	
    // 返回当前集合的hash值
    int hashCode();	
    // 当前集合中是否不含任何元素
    boolean isEmpty();	
    // 返回当前集合中元素的个数
    int size();
    
    // 确保当前集合中包含指定元素, 没有则返回 false
    boolean add(E o);
    // 将指定集合中的所有元素添加到当前集合中, 添加了其中任一元素则返回True
    boolean addAll(Collection<? extends E> c);
    
    // 是否包含指定元素
    boolean contains(Object o);	
    // 当前集合是否包含指定集合中的所有元素
    boolean contains(Collection<?> c);
    
    // 移除当前集合中的第一个指定元素
    boolean remove(Object o);	
    // 从当前集合中移除所有包含在指定集合中的元素
    boolean removeAll(Collection<?> c);	
    // 在当前集合中仅保留指定集合中也含有的元素
    boolean retainAll(Collection<?> c);	
    
    // 返回一个包含当前集合中所有元素的数组
    Object[] toArray();	
    // 返回一个包含当前集合中所有元素的数组, 返回数组的元素类型和参数指定的元素类型
    <T> T[] toArray(T[] a);	
}
```

***

## HashSet

**Set 集合去重原理**：

- 对于**有值特性**的，Set集合可以直接判断进行去重复
- 对于**引用数据类型**的类对象：
  1. Set集合会让两两对象，先调用自己的**hashCode()**方法得到彼此的哈希值（所谓的内存地址）
  2. 然后比较两个对象的哈希值是否相同，如果不相同则直接认为两个对象不重复。
  3. 如果哈希值相同，会继续让两个对象进行**equals**比较内容是否相同，如果相同认为真的重复了，如果不相同认为不重复。

**存储原理**：

- 基于**哈希表**存储数据，增删改查的性能都很好，无序、不重复
- **JDK 1.8之前**：哈希表 = 数组 + 链表  + （哈希算法）
- **JDK 1.8之后**：哈希表 = 数组 + 链表 + 红黑树  + （哈希算法）
- 当链表长度超过阈值 **8** 时，将链表转换为红黑树，这样大大减少了查找时间。

```java
Set<String> sets = new HashSet<>();
```

***

## LinkedHashSet

- **有序**、不重复、无索引
- 底层依然是使用哈希表存储元素的，但是每个元素都额外带一个链来维护添加顺序
- 存储顺序的链会占内存空间、

***

## TreeSet

- 按照元素大小**默认升序排序**、不重复、无索引
- TreeSet集合的**底层是基于TreeMap**

**排序方法**：

- **有值特性的元素**直接可以升序排序。（浮点型，整型）
- **字符串类型**的元素会依字符顺序，按照字符的编号排序。
- 对于**自定义的引用数据类型**，TreeSet默认无法排序，执行的时候直接报错，不知道排序规则。

**自定义的引用数据类型的排序实现**：

1. 实现比较接口 `Comparable`
2. TreeSet 的比较器 **Comparator方法**

```java
Set<Employee> employees1 = new TreeSet<>(new Comparator<Employee>() {
    @Override
    public int compare(Employee o1, Employee o2) {
        // o1比较者   o2被比较者
        // 如果程序员认为比较者大于被比较者 返回正数！
        // 如果程序员认为比较者小于被比较者 返回负数！
        // 如果程序员认为比较者等于被比较者 返回0！
        return o1.getAge() - o2.getAge();
    }
});
```

**java优先使用 Comparator 方法**

***

## List 接口

```java
public interface List<E> extends Collection<E> {
    // 在当前集合的指定位置插入指定元素
    void add(int index, E element);	
    // 在当前集合的指定位置插入指定集合中的所有元素
    boolean addAll(int index, Collection<? extends E> c);	
    
    // 返回当前集合中指定位置的元素
	E get(int index);	
    // 用指定元素替换当前集合指定位置的元素,并返回被替换的元素
    E set(int index, E element);
     // 移除当前集合中指定位置的元素, 并返回该元素
    E remove(int index);
    
    // 返回指定元素的当前集合中第一次出现的位置, 或 -1
    int indexOf(Object o);	
    // 返回指定元素在当前集合中最后一次出现的位置, 或 -1
    int lastIndexOf(Object o);
    
    // 返回当前集合元素的ListIterator对象, 迭代器指向当前集合的起始位置
    ListIterator<E> listIterator();	
    // 返回当前集合元素的ListIterator对象, 迭代器指向index位置
    ListIterator<E> listIterator(int index);	
    
    // 返回一个包含当前集合位置fromIndex 至 toIndex-1 中元素的子集
    list<E> subList(int fromIndex, int toIndex);	
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

***

## ArrayList

- 添加的元素是有序、可重复、有索引
- 基于数组存储数据，**查询快，增删慢**
- 实现List 接口的所有方法

```java
List<String> lists = new ArrayList<>();
```

***

## LinkedList

- 添加的元素是有序、可重复、有索引
- 基于链表存储数据，**增删快，查询慢**
- **双向链表**，增删改查前后的元素更快
- 实现了 **Deque** 接口

```java
LinkedList<String> lists = new LinkedList<>();
```

***

## Map 接口

**特点**：

- Map集合的特点都是**由键决定**的。
- Map集合的键是**无序,不重复的，无索引**的。
- Map集合后面重复的键对应的元素会覆盖前面的整个元素！
- Map集合的值无要求。
- Map集合的键值对都可以为null。

**Map集合的遍历**：

1. 键找值

```java
for (String key : map.keySet()) {
// 通过键取对应的值
Integer value = maps.get(key);
System.out.println(key + "=" + value);
}
```

2. 键值对

```java
// 1.把Map集合转换成一个Set集合:
Set<Map.Entry<String,Integer>> entries = maps.entrySet();
// 2.此时键值对元素的类型就确定了，类型是键值对实体类型：Map.Entry<K, V>
// 3.接下来就可以用foreach遍历这个Set集合，类型用Map.Entry<K, V>
for (Map.Entry<String, Integer> entry : entries) {
String key = entry.getKey();
Integer value = entry.getValue();
System.out.println(key + "=>" + value);
}
```

3. Lambda表达式

```java
maps.forEach((k, v)-> System.out.println(k+"==>"+v));
```

***

## HashMap

- 元素按照键是**无序**，不重复，无索引，值不做要求。

```java
// 把指定的键与指定的值添加到 Map 集合中
public V put(K key, V value);
// 添加一个Map中的所有键值对
public void putAll(Map<? extends K, ? extends V> m);
// 把指定的键 所对应的键值对元素 在 Map 集合中删除，返回被删除元素的值
public V remove(Object key)
// 根据指定的键，在Map集合中获取对应的值。
public V get(Object key);

// 清空 Map
public void clear();
// 是否为空
public boolean isEmpty();
// Map 大小
public int size()

// 获取 Map 集合中所有的 键，存储到 Set 集合中
public Set<K> keySet();
// 获取到 Map 集合中所有的键值对对象的集合( Set 集合)
public Set<Map.Entry<K,V>> entrySet();
// 获取 Map 所有 值 的集合
public Collection<V> values();

// 判断该集合中是否有此键
public boolean containKey(Object key);
// 判断该集合中是否有此值
public boolean containsValue(Object value);
```

***

## LinkedHashMap

- 元素按照键是**有序**，不重复，无索引，值不做要求
- 基于哈希表按照键存储数据的

***

## TreeMap

- TreeMap集合按照键是可排序不重复的键值对集合。(**默认升序**)

- 所以TreeMap集合指定大小规则有2种方式：

  1. 直接为对象的类实现比较器规则接口Comparable，重写比较方法

  2. 直接为集合设置比较器Comparator对象,重写比较方法

***

# 原子性

- 指在一次操作或者多次操作中，要么**所有的操作全部都得到了执行**并且不会受到任何因素的干扰而中断，**要么所有的操作都不执行**。 

## 保证原子性：加锁

- 某一个线程进入`synchronized`代码块前后，执行过程入如下：
  - 线程获得锁
  - 清空工作内存
  - 从主内存拷贝共享变量最新的值到工作内存成为副本
- **保证工作内存内的值一定为最新值**
- 加锁机制**性能差**：同步块内每次仅允许一个线程运行

```java
new Thread(new Runnable() {
    @Override
    public void run() {
        synchronized (this) {
            System.out.println("test");
        }
    }
}).start();
```

## 保证原子性：原子类

- `java.util.concurrent.atomic`包(简称**Atomic包** start JDK1.5)，

  - 这个包中的原子操作类提供了一种用法简单，性能高效，线程安全地更新一个变量的方式。

**Atomic包可用基本数据类型**：

- **AtomicBoolean**：以原子更新的方式更新boolean；
- **AtomicInteger**：以原子更新的方式更新Integer;
- **AtomicLong**：以原子更新的方式更新Long；

**Atomic包可用数组类型**：

- **AtomicIntegerArray** : 原子更新整型数组里的元素
- **AtomicLongArray** : 原子更新长整型数组里的元素
- **AtomicReferenceArray** : 原子更新引用类型数组里的元素

**Atomic包引用类型**：

- **AtomicReference**：原子更新引用类型
- **AtomicReferenceFieldUpdater**：原子更新引用类型里的字段
- **AtomicMarkableReference**：原子更新带有标记位的引用类型。可以原子更新一个布尔类型的标记位和引用类型

```java
// 初始化一个默认值为0的原子型 Integer
public AtomicInteger();
// 初始化一个指定值的原子型 Integer
public AtomicInteger(int initialValue);
// 获取值
int get();
// 以原子方式将当前值加 1 ，注意，这里返回的是自增前的值。
int getAndIncrement();
// 以原子方式将当前值加1，注意，这里返回的是自增后的值。
int incrementAndGet();
// 以原子方式将输入的数值与实例中的值（ AtomicInteger 里的 value ）相加，并返回结果。
int addAndGet(int data);
// 以原子方式设置为 newValue 的值，并返回旧值。
int getAndSet(int value);
```

## CAS机制

- CAS的全成是： Compare And Swap(比较再交换); 是现代CPU广泛支持的一种对内存中的共享数据进行操作的一种特殊指令。CAS可以将read-modify-check-write
- 原子操作**直接由处理器保证**。

**修改操作机制：**

- 3个基本操作数：内存地址V，旧的预
- 期值A，要修改的新值B
- 当旧的预期值A与内存中的值相同：**直接修改**
- 当旧的预期值A与内存中的值不同：获取内存的旧值，**重新计算要修改的新值**，重复上述操作

**CAS与Synchronized**

- CAS和Synchronized都可以保证多线程环境下共享数据的安全性

**Synchronized悲观锁：**

- 总是假设最坏的情况，每次去拿数据的时候都认为别人会修改，所以每次在拿数据的时候都会上锁，这样别人想拿这个数据就会阻塞直到它拿到锁

- （**共享资源每次只给一个线程使用，其它线程阻塞，用完后再把资源转让给其它线程**）。因此Synchronized我们也将其称之为**悲观锁**。jdk中的**ReentrantLock**也是一种悲观锁。性能较差！！

**CAS乐观锁：**

- 总是假设最好的情况，每次去拿数据的时候都认为别人不会修改，所以不会上锁，但是在更新的时候会判断一下在此期间别人有没有去更新这个数据。

- CAS这种机制我们也可以将其称之为乐观锁。综合性能较好！

***

# 异常

- Java语言中, 所有的异常类型都派生自类`java.lang.Throwable`
- 其包含两个子类:
  - **java.lang.Error**：表示系统错误, 程序一般不需要也不应该对这种类型的异常进行处理
  - **java.lang.Exception**：表示可能从任何Java方法或运行期偶发事件中抛出的异常

**特点**：

- 运行时异常被抛出可以不处理。可以自动抛出,编译时异常必须处理.按照规范都应该处理!
- 重写方法申明抛出的异常，应该与父类被重写方法申明抛出的异常一样或者范围更小
- 方法默认都**可以自动抛出运行时异常**！  throws RuntimeException可以省略不写!!
- 当多异常处理时，捕获处理，前边的异常类不能是后边异常类的父类。
- 在try/catch后可以追加finally代码块，其中的代码一定会被执行，通常用于资源回收操作。

## Exception

- **编译时异常**：继承自Exception的异常或者其子类，**编译阶段就会报错**，必须程序员处理的。否则代码编译就不能通过！！

**Exception异常类的常用子类**

| 异常类型                         | 说明                                                         |
| -------------------------------- | :----------------------------------------------------------- |
| **`ClassNotFoundException`**     | **没有找到欲加载的类**                                       |
| **`CloneNotSupportedException`** | **调用方法clone复制对象,<br/>但定义该对象的类没有实现接口Cloneable** |
| **`IllegalAccessException`**     | **非法访问类和类的成员**                                     |
| **`InterruptedException`**       | **当线程在活动之前或活动期间<br/>处于等待/休眠/占用且该线程被中断** |
| **`NoSuchFieldException`**       | **类中不包含指定名称的数据成员**                             |
| **`NoSuchMethodException`**      | **无法找到指定方法**                                         |
| **`RuntimeException`**           | **可能在Java虚拟机正常运行期间抛出的异常的父类**             |
| **`IOException`**                | **发生某种I/O错误**                                          |
| **`FileNotFoundException`**      | **试图打开指定路径名的文件失败**                             |
| **`MalformedURLException`**      | **出现错误的URL**                                            |
| **`UnknownHostException`**       | **无法确认主机的IP地址**                                     |

## RuntimeException

- **运行时异常**: 继承自RuntimeException的异常或者其子类，编译阶段是不会出错的，它是在运行时阶段可能出现，运行时异常可以处理也可以不处理，编译阶段是不会出错的，但是运行阶段可能出现，还是建议提前处理！！

**RuntimeException异常类的子类**

| **异常类型**                          | **说明**                                                     |
| :------------------------------------ | :----------------------------------------------------------- |
| **`ArithmeticException`**             | **出现异常的运算条件**                                       |
| **`ArrayStoreException`**             | **将错误类型的对象存储到一个对象数组中**                     |
| **`ClassCastException`**              | **试图将对象强制转换为不是实例的子类**                       |
| **`IllegalArgumentException`**        | **方法调用时传递的参数不合法**                               |
| **`IndexOutOfBoundsException`**       | **索引值超出范围**                                           |
| **`NegativeArraySizeException`**      | **试图创建元素个数为负数的数组**                             |
| **`NullPointerException`**            | **试图访问空对象时**                                         |
| **`SecurityException`**               | **存在安全侵犯时**                                           |
| **`ArrayIndexOutOfBoundsException`**  | **用非法索引值访问数组**                                     |
| **`StringIndexOutOfBoundsException`** | **字符串索引值超出范围**                                     |
| **`IllegalThreadStateException`**     | **线程没有处于请求操作所要求的适当状态时**                   |
| **`NumberFormatException`**           | **字符串无法转换成适当格式数值类型**                         |
| **`InputMismatchException`**          | **当获取的标记与期望类型的模式不匹配,<br />或者该标记超出期望类型的范围内时** |

**Exception类的构造方法**

```java
public Exception();
public Exception(String message);
```

**Throwable类获取异常信息**

```java
// 返回当前异常的详细信息 
String getMessage();
// 返回当前异常的简要描述    
String toString();
//在标准错误输出上打印当前异常及其栈追踪信息
void printStackTrace();
```

## 自定义异常类

- 自定义异常类时,应该使它派生于类**`Exception`**或它的子类

```java
class MyException extends Exception {
    public MyException() {}
    public MyExceptino(String Message) {
        super(message);
    }
}
```

## 抛出和声明异常

- 当程序运行出现错误时, **Java语言预定义的异常**会由系统创建一个相应的异常类对象并抛出
- **其他异常(如用户自定义异常)**必须在程序中中使用**throw**显式抛出

- Java语言预定义的异常也可以使用关键字throw在程序中显式抛出

- 对于**必检异常**，必须使用**throws**在该方法的方法头中声明异常的类型

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
    // 可能出现异常的代码;
} catch(Type1 id1) {
    // 对 Type1 类异常进行处理的代码;
} catch(Type2 id2) {
    // 对 Type2 类异常进行处理的代码;
} catch(Exception e1) {
    // 确保捕获所有异常,但放置靠前则之后的 catch块都会失效;
} finally {
    // 无论是否出现异常,都会执行;
}
```

***



# 多线程

**什么是进程**：
- 程序是静止的，运行中的程序就是进程。
- 进程的三个特征：
    1. 动态性 ： 进程是运行中的程序，要动态的占用内存，CPU和网络等资源。
    2. 独立性 ： 进程与进程之间是相互独立的，彼此有自己的独立内存区域。
    3. 并发性 ： 假如CPU是单核，同一个时刻其实内存中只有一个进程在被执行。CPU会分时轮询切换依次为每个进程服务，因为切换的速度非常快，给我们的感觉这些进程在同时执行，这就是并发性。

**并行**：同一个时刻同时有多个在执行。

**什么是线程**：

- 线程是属于进程的。一个进程可以包含多个线程，这就是多线程。
- 线程是进程中的一个独立执行单元。
- 线程创建开销相对于进程来说比较小。
- 线程也支持“并发性”。

**线程的作用**：

- 可以提高程序的效率，线程也支持并发性，可以有更多机会得到CPU。
- 多线程可以解决很多业务模型。
- 大型高并发技术的核心技术。
- 设计到多线程的开发可能都比较难理解。

## `Thread`类

**继承`Thread`类的方式**

```java
public class ThreadDemo {
    public static void main(String[] args) {
        // 3.创建一个线程对象
        Thread t = new MyThread();
        // 4.调用线程对象的start()方法启动线程,最终还是执行run()方法！
        t.start();
    }
}
// 1.定义一个线程类继承Thread类。
class MyThread extends Thread{
    // 2.重写run()方法
    @Override
    public void run() {
        // 线程的执行方法。
        System.out.println("子线程输出");
    }
}
```

**继承`Thread`类的优缺点**：

- **优点**：编码简单。
- **缺点**：线程类已经继承了Thread类无法继承其他类了，功能不能通过继承拓展（单继承的局限性）

```java
// -- 给当前线程取名字。
public void setName(String name);
// -- 线程存在默认名称，子线程的默认名称是：Thread-索引。
// -- 主线程的默认名称就是：main
// -- 获取当前线程的名字。
public void getName();

// -- 获取当前线程对象，这个代码在哪个线程中，就得到哪个线程对象。
public static Thread currentThread();
// -- 让当前线程休眠多少毫秒再继续执行。
public static void sleep(long time);
```

## `Runnable`接口

通过实现接口`java.lang.Runnable`, 在方法run中定义相应线程的子任务代码

- 线程任务类只是实现了Runnable接口，可以继续继承其他类，而且可以继续实现其他接口（避免了单继承的局限性）
- 同一个线程任务对象可以被包装成多个线程对象
- 适合多个多个线程去共享同一个资源
- 实现解耦操作，线程任务代码可以被多个线程共享，线程任务代码和线程独立。
- 线程池可以放入实现Runable或Callable线程任务对象。
- `Thread`类本身也是实现了`Runnable`接口的。
- **不能直接得到线程执行的结果，也不能抛出异常**（重写的`run()`方法是无参、无返回值、无异常函数）

```java
// 1.创建一个线程任务类实现Runnable接口。
public class testRunnable implements Runnable {
    public static void main(String[] args) {
        // 3.创建一个线程任务对象(注意：线程任务对象不是线程对象，只是执行线程的任务的)
        Runnable target = new testRunnable();
        // 4.把线程任务对象包装成线程对象.且可以指定线程名称
        Thread t = new Thread(target,"1号线程");
        // 5.调用线程对象的start()方法启动线程
        t.start();
    }
    // 2.重写run()方法
    @Override
    public void run() {
        // 线程的执行方法。
        System.out.println("子线程输出");
    }
}
```

**匿名内部类简化写法**：

```java
Runnable runnable = new Runnable() {
    @Override
    public void run() {
        System.out.println("test");
    }
};
Runnable runnable = () -> System.out.println("test");
```

## `Callable`接口

- 线程任务类只是实现了Callable接口，可以继续继承其他类，而且可以继续实现其他接口（避免了单继承的局限性）
- 同一个线程任务对象可以被包装成多个线程对象
- 适合多个多个线程去共享同一个资源（后面内容）
- 实现解耦操作，线程任务代码可以被多个线程共享，线程任务代码和线程独立。
- 线程池可以放入实现Runable或Callable线程任务对象。(后面了解)
- **能直接得到线程执行的结果**！

```java
// 1.创建一个线程任务类实现 Callable 接口，申明线程返回的结果类型
class MyCallable implements Callable<String>{
    // 2.重写线程任务类的call方法！
    @Override
    public String call() throws Exception {
        return "执行结果"
    }
    public static void main(String[] args) {
        // 3.创建一个Callable的线程任务对象
        Callable call = new MyCallable();
        // 4.把Callable任务对象包装成一个未来任务对象
        FutureTask<String> task = new FutureTask<>(call);
        // 5.把未来任务对象包装成线程对象
        Thread thread = new Thread(task);
        // 6.启动线程对象
        thread.start();
        // 7. 在最后去获取线程执行的结果,如果线程没有结果，让出CPU等线程执行完再来取结果
        try {
            // 获取call方法返回的结果（正常/异常结果）
            String rs = task.get(); 
            System.out.println(rs);
        }  catch (Exception e) {
            e.printStackTrace();
        }
    }
}
```

**`FutureTask`的作用**：

- 实现了`Runnable`接口:这样就可以被包装成线程对象！
- 未来任务对象可以在线程执行完毕之后去得到线程执行的结果`object.get()`。

**`FutureTask`的缺点**：

- 如果线程长时间没有返回值或抛出异常，会导致线程阻塞

```java
public V get() throws InterruptedException, ExecutionException;
```

- 等待固定时长，如果在这个时长内程序还是没有运行完成，就会出现 juc.TimeOutException 异常

```java
public V get(long timeout, TimeUnit unit)
        throws InterruptedException, ExecutionException, TimeoutException;
// timeout:等待时长 unit:时间单位
futureTask.get(2, TimeUnit.SECONDS);
```

## 线程优先级

- Java语言中,每个线程都有一个优先级, 而且, 程序中每个显式创建的线程都可以标记为守护线程

```java
// 更改当前线程的优先级
new Thread(threadA).setPriority(num);

// 返回当前线程的优先级
new Thread(threadB).getPriority();

Java语言中,线程有10个优先级
最低优先级常量    Thread.MIN_PRIORITY = 10;
最高优先级常量    Thread.MAX_PRIORITY = 1;
默认优先级常量    THread.NORM_PRIORITY = 5;
```

- Java线程的优先级通常会被映射到其运行平台操作系统的优先级上。**Windows操作系统只有七个优先级别**, 这种映射关系是不确定的

## 守护线程

- Java语言中, 线程分为用户线程和守护线程两类。 

- 主线程为用户线程，在用户线程中创建的线程默认为用户线程。

- 守护线程也成为后台线程,这种线程与用户线程的区别在于当一个程序中的所有用户线程都结束运行时,程序会立即结束执行,不管当前是否还有守护线程正在运行

守护线程通常用来在后台为其他线程提供服务, 它不属于程序中的必要部分

```java
设置为守护线程    new Thread(threadA).setDaemon(true);    // 必须在当前线程启动之前调用，主线程无法转换为守护线程
判断是否为守护线程    new Thread(threadB).isDaemon();

返回对当前正在运行的线程对象的引用    Thread thread = Thread.currentThread();
返回线程的名称    new Thread(threadB).getName();    // 每个线程都有默认的名称 thread-0
设置线程的名称    new Thread(threadB).setName();
设置休眠时间    Thread.sleep(1000);    // 毫秒
```

## 线程池

**线程池的作用**：

- 降低资源消耗：

  减少了创建和销毁线程的次数，**每个工作线程都可以被重复利用**，可执行多个任务。

- 提高响应速度：

  不需要频繁的创建线程，如果有线程可以直接用，不会出现系统僵死！

- 提高线程的可管理性：

  线程池可以约束系统最多只能有多少个线程，**不会因为线程过多而死机**

**线程池的核心思想**：

- 线程复用，同一个线程可以被重复使用，来处理多个任务。

**线程池的创建**：

```java
// 当某一个线程在60s内没有被使用时,系统会自动终止并从缓存中移除它
ExecutorService pools = Executors.newCathedThreadPool();

// 创建一个可重用的包含指定数量线程的线程池
ExecutorService pools = Executors.newFixedThreadPool(3);
```

**提交`Runnable`接口任务**：

```java
pools.submit(new Runnable() {
    @Override
    public void run() {
        System.out.println("test");
    }
})
```

**提交`Callable`接口任务**：

```java
Future<String> task = pools.submit(new Callable<String>() {
    @Override
    public String call() throws Exception {
        return "test";
    }
});
// 获取返回值 主线程程会等待 task 线程结束
String result = task.get()
```

**线程池的显式结束**：

```java
// 等所有线程任务完成之后关闭线程池
exec.shutdown();
// 立即关闭线程池
exec.shutdownNow();
```

## `Lock`锁

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

## `synchronized`同步方法

- 当一个线程需要执行某个被关键字synchronized保护的代码时,他将首先检查内置的锁是否可用
  - 如果可用,就获得该锁,执行其中的代码,然后释放该锁.

**锁对象**：

- **实例方法**：同步方法默认用this作为的锁对象。

- **静态方法**：同步方法默认用类名.class作为的锁对象。

```java
public synchronized double getBalance() {
    return balance;
}
```

## `synchronized`同步块

- 当前线程必须获得指定对象的内置锁
- 把出现线程安全问题的核心代码给上锁，每次只能一个线程进入，执行完毕以后自动解锁，其他线程才可以进来执行。

**锁对象**：

- 锁对象建议使用共享资源。
- 在实例方法中建议用**this**作为锁对象。此时this正好是共享资源！
- 在**静态方法**中建议用类名.class字节码作为锁对象。

```java
public double getBalance() {
    // 对象名是指用于保护随后临界区中代码的内置锁所属对象的引用名
    synchronized(指定对象名) {    
        return balance;
    }
}
```

## 线程间协作

- 对于`synchronized`保护的临界区, 可通过调用下述方法实现线程间协作

- 下述方法必须出现在同步方法或者同步块中,且只能通过用于保护当前临界区的内置锁所属对象调用

```java
// 使用wait方法将导致当前线程释放掉它拥有的当前对象的内置锁
// 使当前线程的等待,直到其他线程同一对象调用方法 notify or notifyAll
public final void wait() throws InterruptedException;
// 或者等待时间超出参数指定的时间
public final void wait(int timeout) throws InterruptedException;

// 唤醒正在当前对象内置锁上等待的所有线程
public final void notifyAll();
// 唤醒任意一个正在当前对象内置锁上等待的线程
public final void notify();
```

## 线程的状态

- Java语言使用内部枚举类型==java.lang.Thread.State==描述六种状态

- 调用类Thread.getState();可以获取当前线程的状态

**新生状态**

- **创建一个新的线程对象**,在尚未启动前,该线程就处于新生状态, 

`Thread.State.NEW`

**可运行状态**

- 一个处于可运行状态的线程可能正在运行也可能没有运行,这取决于**线程调度器是否给他分配了CPU**

- 线程调度器只会给处于可运行状态的线程分配CPU, 并且倾向于先给优先级高的线程分配CPU时间片

`Thread.State.RUNNABLE`

**阻塞状态**

- 当线程**试图获取对象的内置锁被其他线程拥有时**,线程进入阻塞状态

`Thread.State.BLOCKED`

**等待状态**

- 获得锁对象后，调用`wait()`方法

`Thread.State.WAITING`

**计时等待状态**

- 调用`wait(time) / sleep(time)`方法

`Thread.State.TIMED_WAITING`

**终止状态**

- 执行完毕或出现了异常

`Thread.State.TERMINATED`

## 死锁
**死锁产生的四个必要条件**：

- **互斥使用**，即当资源被一个线程使用(占有)时，别的线程不能使用。
- **不可抢占**，资源请求者不能强制从资源占有者手中夺取资源，资源只能由资源占有者主动释放。
- **请求和保持**，即当资源请求者在请求其他的资源的同时保持对原有资源的占有。

- **循环等待**，即存在一个等待循环队列：p1要p2的资源，p2要p1的资源。这样就形成了一个等待环路

当上述四个条件**都成立的时候，便形成死锁**。当然，死锁的情况下如果打破上述任何一个条件，便可让死锁消失

## `ConcurrentHashMap`

**`HashMap`**：

- 线程不安全的，性能好
- 如果在要求线程安全的业务情况下就不能用这个集合做Map集合，否则业务会崩溃

**`HashTable`**：

- Hashtable是线程安全的Map集合，但是性能较差！(已经被淘汰了，虽然安全，但是性能差)
- 安全基于synchronized，综合性能差,被淘汰了。
- 类内所有方法全部用`synchronized`修饰，对整个对象加锁。

**`ConcurrentHashMap`**：

- 线程安全，效率高，性能好，最新最好用的线程安全的Map集合
- **分段式锁**，对Map内的每一个键值对分别设置锁，在保证线程安全的前提下提高效率

## `CountDownLatch`

- 允许一个或多个线程等待其他线程完成操作，再执行自己。
- 指定约束线程继续执行的条件数`count`，**所有条件均满足后才可继续执行**
- 要保证多线程中处理的为同一个 `CountDownLatch`对象

```java
// 构造器,初始化唤醒需要的down几步
public CountDownLatch(int count);

// 让当前线程等待，必须down完初始化的数字才可以被唤醒，否则进入无限等待
public void await() throws InterruptedException;
// 计数器进行减1 （down 1）
public void countDown()    
```

## `CyclicBarrier`

- 允许一组线程全部等待彼此达到共同屏障点的同步辅助。  
- 循环阻塞在涉及固定大小的线程方的程序中很有用，这些线程必须偶尔等待彼此。 屏障被称为循环，因为它可以**在等待的线程被释放之后重新使用**。 
- 循环屏障可以实现**每次**达到一组屏障就触发一个任务执行

**构造器：**

```java
// 当给定数量的线程正在等待时，它将跳闸，当屏障跳闸时执行给定的屏障动作，由最后一个进入屏障的线程执行。 
public CyclicBarrier(int parties,Runnable barrierAction);
// 当给定数量的线程（线程）正在等待时，它将跳闸，并且当屏障跳闸时不执行预定义的动作。
public CyclicBarrier(int parties);
```

**常用方法：**

```java
// 返回旅行这个障碍所需的聚会数量。
public int getParties();
// 进入屏障等待状态，等待指定数量的parties在这个障碍上调用了await 
// 返回值:当前线程的到达索引，其中索引 getParties() - 1表示第一个到达，零表示最后到达 
public int await();
// 等待所有parties已经在此屏障上调用await ，或指定的等待时间过去。
public int await(long timeout,TimeUnit unit);
// 查询这个障碍是否处于破碎状态。
public boolean isBroken();
// 将屏障重置为初始状态。如果任何一方正在等待屏障，他们将返回BrokenBarrierException 。 
public void reset();
// 返回目前正在等待障碍的各方的数量。
public int getNumberWaiting();
```

## `Semaphore`

- **流量控制**，控制线程并发占锁的数量

**构造器：**

```java
// permits 表示许可线程的数量
public Semaphore(int permits);
// fair 表示公平性，如果这个设为 true 的话，下次执行的线程会是等待最久的线程
public Semaphore(int permits, boolean fair);
```
**常用方法：**

```java
// 表示获取许可
public void acquire() throws InterruptedException;
// 表示释放许可
public void release() release(); 
```

## `Exchanger`

- 用于线程间协作的工具类，进行**线程间的数据交换**。
- 如果第一个线程先执行exchange()方法，它会一直等待第二个线程也执行exchange方法，当两个线程都到达同步点时，这两个线程就可以交换数据，将本线程生产出来的数据传递给对方。

**构造器：**

```java
public Exchanger();
```

**常用方法：**

```java
// 双方交换的数据类型要求一致
public V exchange(V x);
// 双方交换数据，等待指定事件后抛出异常
public V exchange(V x,long timeout,TimeUnit unit);
```

***

# Java内存模型

概述：JMM(Java Memory Model)Java内存模型,是java虚拟机规范中所定义的一种内存模型。

Java内存模型(Java Memory Model)描述了Java程序中各种变量(线程共享变量)的访问规则，以及在JVM中将变量存储到内存和从内存中读取变量这样的底层细节。

所有的共享变量都存储于主内存。这里所说的变量指的是实例变量和类变量。不包含局部变量，因为局部变量是线程私有的，因此不存在竞争问题。每一个线程还存在自己的工作内存，线程

的工作内存，保留了被线程使用的变量的工作副本。线程对变量的所有的操作(读，取)都必须在工作内存中完成，而不能直接读写主内存中的变量，不同线程之间也不能直接访问

对方工作内存中的变量，线程间变量的值的传递需要通过主内存完成。

![](https://raw.githubusercontent.com/which-biscuits/pigGo/main/JVM.png)

***

# Lambda表达式

**作用：**

- 简化匿名内部类的代码写法

**格式：**

```java
(匿名内部类被重写方法的形参列表) -> {
    代码块
}
Runnable runnable = () -> {
    System.out.println("test")
}
Collections.sort(lists ,( t1,  t2) -> t1.getAge() - t2.getAge());
```

**特点：**

- 不能简化所有匿名内部类的写法
- 只能简化**接口**中**只有一个抽象方法**的匿名内部类形式（**函数式接口**）

**@FunctionalInterface注解**

- 函数式接口的标志，可以使用Lambda表达式

**省略写法：**

- 如果Lambda表达式的方法体代码只有一行代码。可以省略大括号不写,同时要省略分号！

- 如果Lambda表达式的方法体代码只有一行代码。可以省略大括号不写。

  此时，如果这行代码是return语句，必须省略return不写，同时也必须省略";"不写

- 参数类型可以省略不写。
- 如果只有一个参数，参数类型可以省略，同时()也可以省略。

***

# 方法引用

**作用：**

- 进一步简化Lambda表达式的写法
- 类型或者对象::引用的方法

**静态方法的引用：**

- 被引用的方法的参数列表要和函数式接口中的抽象方法的参数列表一致

```java
Collections.sort(lists, Comparator.comparingInt(Student::getAge));
```

**实例方法的引用：**

- 被引用的方法的参数列表要和函数式接口中的抽象方法的参数列表一致

```java
// 对象是 System.out = new PrintStream();
// 实例方法：println()
lists.forEach(s -> System.out.println(s));
```

**特定类型方法的引用：**

- 内置类型方法

```java
Arrays.sort(strs, ( s1,  s2 ) ->  s1.compareToIgnoreCase(s2));
// 特定类型的方法引用：
Arrays.sort(strs,  String::compareToIgnoreCase);
```

**构造器的引用：**

```java
String[] strs1 = lists.toArray(s -> new String[s] );
```

***

# `Stream`流

- Stream流是用来简化集合类库或者数组API的弊端。
- Stream流其实就一根传送带，元素在上面可以被Stream流操作。
- 支持**链式编程**

**流的获取：**

```java
// -- Collection 集合获取流
Collection<String> c = new ArrayList<>();
Stream<String> ss = c.stream();

// -- Map 集合获取流
Map<String, Integer> map = new HashMap<>();
// -- 键的流
Stream<String> keyss = map.keySet().stream();      

// -- 值的流
Stream<Integer> valuess = map.values().stream();

// -- 键值对的流
Stream<Map.Entry<String,Integer>> keyAndValues = map.entrySet().stream();

// -- 数组获取流
String[] arrs = new String[]{"Java", "JavaEE" ,"Spring Boot"};
Stream<String> arrsSS1 = Arrays.stream(arrs);
Stream<String> arrsSS2 = Stream.of(arrs);
```

**操作方法：**

```java
// -- 对流的每一个元素执行操作。 
void forEach(Consumer<? super T> action);
// -- 返回此流中的元素数。
long count();
// -- 返回由与此给定谓词匹配的此流的元素组成的流 过滤器
Stream<T> filter(Predicate<? super T> predicate);
// -- 返回由该流的元素组成的流，截断长度不能超过maxSize
Stream<T> limit(long maxSize);
// -- 在丢弃流的第一个n元素后，返回由该流的n元素组成的流。 如果此流包含少于n元素，那么将返回一个空流。 
Stream<T> skip(long n);
// -- 返回由给定函数应用于此流的元素的结果组成的流
<R> Stream<R> map(Function<? super T,? extends R> mapper);

int count = new ArrayList<String>().stream().filter(s -> s.length() == 3).skip(3).limit(3).map(Student::new).count();
Stream<String> stream = Stream.concat(new ArrayList<String>().stream(), new ArrayList<String>().stream());
```

**终结方法：**

- 一旦Stream调用了终结方法，流的操作就全部终结了，不能继续使用，只能创建新的Stream操作。
- 终结方法： `foreach`，`count`

**非终结方法：**

- 每次调用完成以后返回一个新的流对象,可以继续使用，支持链式编程！

**Stream流的收集：**

```java
// -- 创建一个懒惰连接的流，其元素是第一个流的所有元素，后跟第二个流的所有元素。 
static <T> Stream<T> concat(Stream<? extends T> a,Stream<? extends T> b);
// -- 将流封装为指定格式的集合
<R,A> R collect(Collector<? super T,A,R> collector);
// -- 返回一个包含此流的元素的数组。 
Object[] toArray();
// -- 将流封装为指定类型的数组
<A> A[] toArray(IntFunction<A[]> generator);

// -- 把stream流转换成Set集合。
Set<String> sets = new Stream<String>.collect(Collectors.toSet());

// -- 把stream流转换成List集合。
List<String> lists= Stream<String>.collect(Collectors.toList());

// -- 把stream流转换成数组。
Object[] arrs = Stream<String>.toArray();
// -- 可以借用构造器引用申明转换成的数组类型！！！
String[] arrs1 = Stream<String>.toArray(String[]::new);
```

***

# IO流

**字符集：**

- ASCII码， 2^8（256）个字符

- GBK编码，兼容ASCII，2^16（65536）个字符
- Unicode编码，兼容ASCII，边长编码，1-3个字节

**分类：**

- 按照流的方向分为：输入流，输出流。

  1. **输出流**：以内存为基准，把内存中的数据写出到磁盘文件或者网络介质中去的流称为输出流。

     **作用**：写数据到文件，或者写数据发送给别人。

  2. **输入流**：以内存为基准，把磁盘文件中的数据或者网络中的数据读入到内存中去的流称为输入流。

     **作用**：读取数据到内存。

- 按照流的内容分为: 字节流，字符流。

  1. **字节流**：流中的数据的最小单位是一个一个的字节，这个流就是字节流。
2. **字符流**：流中的数据的最小单位是一个一个的字符，这个流就是字符流。

## `InputStream`

- 所有的字节输入流派生于**抽象类**java.io.InputStream

- 文件字节输入流 : java.io.FileInputStream

- 字节数组输入流 : java.io.ByteArrayInputStream

- 过滤字节输入流 : java.io.FilterInputStream

**方法：**

```java
// 读取一个字节并返回对应的int型, 在输入流的末尾则返回-1
public abstract int read();	
// 读取最多b.length个字节并存储其中, 返回 <= b.length 或 -1
public int read(byte[] b);	

// 跳过并丢弃 n 个字节, 返回实际跳过的字节数
public long skip(long n);	
// 返回数据流中可读取的字节数
public int available();	
// 关闭当前输入流, 并释放所有xiang
public void close();		
```
**解决中文读取乱码 : 一次读取所有字符**

- **当文件过大时，占用大量内存**，不适合大文件使用

```java
File file = new File("test.txt");
InputStream inputStream = new FileInputStream(file);
byte[] buffer = new byte[(int) file.length()];
int len = inputStream.read(buffer);
String rs = new String(buffer);
```

## `FileInputStream`

- **文件字节输入流**，继承`InputStream`类
- 以内存为基准，把磁盘文件中的数据按照字节的形式读入到内存中的流。简单来说，就是按照字节读取文件数据到内存。

**构造器：**

```java
// 创建一个字节输入流管道与源文件对象接通。
public FileInputStream(File path);
// 创建一个字节输入流管道与文件路径对接。
public FileInputStream(String pathName);
```

## `OutputStream`

所有的字节输出流派生于**抽象类**java.io.OutputStream

文件字节输出流 : java.io.FileOutputStream

字节数组输出流 : java.io.ByteArrayOutputStream

过滤字节输出流 : java.io.FilterOutputStream

**当使用文件输出流创建对象时,会覆盖原有文件内容或将内容追加到原有文件中**

```java
// 将指定字节 b 写入当前输出流
public abstract void write(int b);	
// 将数组b中的所有字节写入当前输出流
public void write(byte[] b);	
// 将数组 buffer 的指定字节写入到当前输出流
public void write(byte[] buffer , int pos , int len);

// 先写入内存缓冲区, 直到填满缓冲区\关闭输出流或调用方法flush,才将数据写出
// 刷新当前输出流, 写出缓冲的输出字节
public void flush();		
// 关闭当前输出流, 并释放所有相关资源
public void close();		
```

## `FileOutputStream`

- 文件字节输出流，继承`OutputStream`类
- 以内存为基准，把内存中的数据，按照字节的形式写出到磁盘文件中去，把内存数据按照字节写出到磁盘文件中去。
- 管道使用完毕后，要使用`close()`关闭管道

**构造器：**

```java
// 创建一个字节输出流管道通向目标文件对象。
public FileOutputStream(File file);
// 创建一个字节输出流管道通向目标文件路径。
public FileOutputStream(String file);
// 创建一个追加数据的字节输出流管道通向目标文件对象。
public FileOutputStream(File file , boolean append);
// 创建一个追加数据的字节输出流管道通向目标文件路径。
public FileOutputStream(String file , boolean append);
```

## `FileReader`

- 以内存为基准，把磁盘文件的数据以**字符**的形式读入到内存，读取文本文件内容到内存中去。
- 字符流一个一个字符的读取文本内容输出，**可以解决中文读取输出乱码**的问题

**构造器：**

```java
// 创建一个字符输入流与源文件对象接通。
public FileReader(File file);
// 创建一个字符输入流与源文件路径接通。
public FileReader(String filePath);
```

**方法：**

```java
// 读取一个字符的编号返回！ 读取完毕返回-1
public int read();
// 读取一个字符数组，读取多少个字符就返回多少个数量，读取完毕返回-1
public int read(char[] buffer);
```

## `FileWriter`

- 以内存为基准，把内存中的数据按照字符的形式写出到磁盘文件中去，就是把内存的数据以字符写出到文件中去。

**构造器：**

```java
// 创建一个字符输出流管道通向目标文件对象。
public FileWriter(File file);
// 创建一个字符输出流管道通向目标文件路径。
public FileWriter(String filePath);
// 创建一个追加数据的字符输出流管道通向目标文件对象。
public FileWriter(File file,boolean append);
// 创建一个追加数据的字符输出流管道通向目标文件路径。
public FileWriter(String filePath,boolean append);
```

**方法：**

```java
// 写一个字符出去
public void write(int c);
// 写一个字符串出去：
public void write(String c);
// 写一个字符数组出去
public void write(char[] buffer);
// 写字符串的一部分出去
public void write(String c ,int pos ,int len);
// 写字符数组的一部分出去
public void write(char[] buffer ,int pos ,int len);
```

## 缓存IO流

## `bufferedInputStream`

- 可以把低级的字节输入流**包装**成一个高级的缓冲字节输入流管道
- 提高字节输入流读数据的性能
- 缓冲字节输入流管道自带了一个**8KB的缓冲池**，每次可以直接借用操作系统的功能最多提取8KB的数据到缓冲池中去，直接从缓冲池读取数据，所以性能较好！

**构造器：**

```java
public BufferedInputStream(InputStream in);
```

## `bufferedOutputStream`
- 可以把低级的字节输出流**包装**成一个高级的缓冲字节输出流，从而提高写数据的性能。
- 缓冲字节输出流自带了**8KB缓冲池**,数据就直接写入到缓冲池中去，性能极高

**构造器：**

```java
public BufferedOutputStream(OutputStream os);
```

## `bufferedReader`

- 字符缓冲输入流可以把字符输入流包装成一个高级的缓冲字符输入流，可以提高字符输入流读数据的性能。
- 缓冲字符输入流默认会有一个**8K的字符缓冲池**,可以提高读字符的性能。

**构造器：**

```java
public BufferedReader(Reader reader)
```

**方法：**

```java
// 读取一行数据返回，读取完毕返回null
public String readLine();

while ((line = bufferReader.readLine()) != null) {
    System.out.println(line);0
}
```

## `bufferedWriter`

- 把字符输出流包装成一个高级的缓冲字符输出流，提高写字符数据的性能。
- 高级的字符缓冲输出流多了一个8k的字符缓冲池，写数据性能极大提高了!

**构造器：**

```java
public BufferedWriter(Writer writer);
```

**方法：**

```java
// 新建一行 
public void newLine();
```

## `InputStreamReader`

- 可以解决字符流读取不同编码乱码的问题。
- 可以把原始的字节流按照当前默认的代码编码转换成字符输入流。
- 也可以把原始的字节流按照指定编码转换成字符输入流

**构造器：**

```java
// 可以使用当前代码默认编码转换成字符流，几乎不用！
public InputStreamReader(InputStream is);
// 可以指定编码把字节流转换成字符流
public InputStreamReader(InputStream is,String charset);
```

## `OutputStreamWriter`

- 可以指定编码把字节输出流转换成字符输出流。
- 可以指定写出去的字符的编码。

**构造器：**

```java
// 用当前默认编码UTF-8把字节输出流转换成字符输出流
public OutputStreamWriter(OutputStream os);
// 指定编码把字节输出流转换成字符输出流
public OutputStreamWriter(OutputStream os , String charset);
```

## 对象序列化

## `DataInputStream` && `DataOutputStream`

- **以指定类型读取或写入数据**

- 继承`FilterInputStream` && `FilterOutputStream`

**构造器：**

```java
public DataInputStream(InputStream in);
public DataOutputStream(OutputStream out);
```

**方法：**

```java
读取方法：
readBoolean / readChar / readShort / readByte / readInt / readLong / readFloat / readDouble / readUTF(读取字符串)
写入方法
writeBoolean / writeChar / writeShort/ writeByte / writeInt / writeLong / writeFloat / writeDouble / writeUTF(写入字符串)
```

DataOutputStream 输出与平台无关的二进制, PrintStream 用于格式化输出, 可以以文本方式阅读

## `PrintStream` && `PrintWriter`

- 可视化文本格式输出, `System.out` 就是`PrintStream`的一个**对象**
- `PrintStream` 同时支持字节流输出
- `PrintWeiter`同时支持字符输出流
- **自动将流包装**为 `buffered` 缓冲流

**构造器：**

```java
public PrintStream(OutputStream os):
public PrintStream(String filepath):
```

**方法：**

- 同`System.out`

```java
// 打印流重定向
PrintStream printStream = new PrintStream("log.txt");
System.setOut(printStream);
```



## `ObjectOutputStream` && `ObjectInputStream`

- InputStream 的子类, 并实现了DataOutputStream的全部功能
- OutputStream 的子类, 并实现了DataOutputStream的全部功能

**构造器：**

```java
public ObjectInputStream(InputStream in);
public ObjectOutputStream(OutputStream out);
```

**方法：**

```java
WriteObject(Object obj);
ReadObject(Object obj); 
```

**注意 : **

- 对象的读取顺序应与对象的写入顺序一致

- 读取对象的类文件应被加载完毕

- 可序列化对象: 实现了接口`Java.io.Serializable`
- **对未实现序列化接口的数据成员 无法进行序列化**
- **序列化时会自动忽略静态数据成员**

**transient (瞬态) : **关闭对象成员的序列化

```java
class Login implements java.io.Serializable {
    private String name;
    private transient String passward;	// 对象序列化时, 忽略数据成员
}
```

**序列化版本号：**

```java
// 加入序列版本号
private static final long serialVersionUID = 1L;
```

- **序列化对象和反序列化使用的版本号一致**才能使用，否则报错

## 资源释放

- 资源使用完毕后，需要显式的关闭资源

**since 1.7：**

- 资源类一定是实现了**`Closeable`**接口，实现这个接口的类就是资源，有`close()`方法，`try-with-resources`会自动调用它的`close()`关闭资源。

```java
try(
    InputStream is = new FileInputStream("test.txt");
    /* 关闭资源！是自动进行的 */
){}catch (Exception e){
    e.printStackTrace();
}
```

## 随机文件访问

**java.io.RandomAccessFile : 文件的随机访问**

**构造方法：**

```java
RandomAccessFile(File file, String mode);
RandomAccessFile(String name, String mode);
// file / name : 要访问的文件对象或文件名
// ① r: 以只读方式打开
// ② rw：打开以便读取和写入
// ③ rwd:打开以便读取和写入；同步文件内容的更新
// ④ rws:打开以便读取和写入；同步文件内容和元数据的更新
```

常用方法：

```java
// 设置文件光标位置向前偏移pos个字节, 打开文件时光标位于开始位置
public void seek(long pos) throws IOException;	
// 返回文件的字节长度
public long length() throws IOException;	
// 返回当前偏移的字节数(输入输出流光标位置)
public long getFilePointer() throws IOException;	
```

# 单例设计模式

**什么是单例 SingleIntance**：

- 一个类永远**只存在一个对象**，不能创建多个对象

**使用场景**：

- 部分实例对象只需要一个，类永远只有一个对象（例：部分应用不允许多开），节省内存

**单例的实现**：

1. **饿汉**单例设计模式：

   通过类获取单例对象的时候，**对象已经被创建**

   无论是否使用都已经创建好了，所以会浪费一定的内存。如果一个程序有大量的饿汉式单例模式，那么在类加载时，会同时创建大量单例，会浪费硬件资源。

   ```java
   class SingleIntance {
       // 1.把类的构造器私有化，不允许创建类的对象
       private SingleInstance() {}
       // 2.定义一个静态成员变量用于存储一个对象！
       private static SingleInstance singleInstance = new SingleInstance();
       // 3.提供方法向外暴露单例对象
       public static SingleInstance getInstance() { return singleInstance; }
   }
   ```

2. **懒汉**单例设计模式：

   通过类获取单例对象的时候发现没有才会去创建一个对象

    ```java
   class SingleIntance {
       // 1.把类的构造器私有化，不允许创建类的对象
       private SingleInstance() {}
       // 2.定义一个静态成员变量用于存储一个对象！需要时创建实例
       private static SingleInstance singleInstance;
       // 3.提供方法向外暴露单例对象 当实例对象为空时，创建对象
       public static SingleInstance getInstance() {
           if (singleInstance == null) 
               singleInstance = new SingleInstance();
           return singleInstance; 
       }
   }
    ```

***

# 内置类

## System

**终止当前虚拟机**：

```java
public static void exit(int status);	// 0 -> 正常终止
System.exit(0);
```

**获取系统当前的毫秒值**：

```java
public static native long currentTimeMillis();
System.currentTimeMillis();
```

**数组的拷贝**：

```java
/**
 	@param      src      the source array.
    @param      srcPos   starting position in the source array.
    @param      dest     the destination array.
    @param      destPos  starting position in the destination data.
    @param      length   the number of array elements to be copied.
 */
public static native void arraycopy(Object src,  int  srcPos,Object dest, int destPos, int length);
System.arraycopy(src, srcPos, dest, destPos, length);
```

***

## Math

- **静态类**，不能创建对象，**不可被继承**

**数学常量**

```java
public static final double E;	// 自然对数的底e
public static final PI;			// 圆周率Π
```

**三角函数**

```java
public static double sin( double a );	// 正弦值        
public static double cos( double a );	// 余弦值        
public static double tan( double a );	// 正切值        
// 单位为 弧度
public static double asin( double a );	// 正弦值为 a 的角度    
public static double acos( double a );	// 余弦值为 a 的角度    
public static double atan( double a );	// 正切值为 a 的角度    
public static double toRadians( double angdeg );	// 角度 转为 弧度    
public static double toDegrees( double angrad );	// 弧度 转为 角度    
```

**指数函数**

```java
public static double exp( double a );	// e 的 a 次方    
public static double pow( double a, double b );	// a 的 b 次方    

public static double sqrt( double a );	// a 的平方根    
public static double cbrt( double a );	// a 的立方根    
```

**对数函数**

```java
public static double log( double a );	// ln a       
public static double log10( double a );	// log10 a    
```

**取整方法**

```java
public static double ceil( double a );	// >= a 的整数    
public static double floor( double a);	// <= a 的整数    
public static double rint( double a );	// 最接近 a 的整数 左右距离相同时以偶数优先
public static int round( float a );		// 返回floor(a+0.5f)    
public static long round( double a);	// 返回floor(a+0.5d)    
```

**其他常用方法**

```java
public static int abs( Object o );	// 方法重载 返回 int long float double 型参数的绝对值
public static Object max( Object o );	// 方法重载 返回 int long float double 型参数的较大值
public static Object min( Object o );	// 方法重载 返回 int long float double 型参数的较小值
public static double random();	// 0.0 - 1.0 的伪随机 double 类型
```

***

## BigInteger / BigDecimal

- 用于解决基础浮点运算出现的**数据失真问题**
- Java中小数运算有可能会有精度问题，如果要解决这种精度问题，可以使用BigDecimal

**概述**：

| 相关内容 | 具体描述                                                     |
| -------- | :----------------------------------------------------------- |
| 包       | java.math                                                                  使用时需要导包 |
| 类声明   | public class BigDecimal extends Number implements Comparable<BigDecimal> |
| 描述     | BigDecimal类提供了算术，缩放操作，舍入，比较，散列和格式转换的操作。提供了更加精准的数据计算方式 |

**构造方法**：

| 构造方法名             | 描述                                            |
| ---------------------- | ----------------------------------------------- |
| BigDecimal(double val) | 将double类型的数据封装为BigDecimal对象          |
| BigDecimal(String val) | 将 BigDecimal 的字符串表示形式转换为 BigDecimal |

注意：推荐使用第二种方式，第一种存在精度问题；

**常用方法**：

| 方法声明                                       | 描述     |
| ---------------------------------------------- | -------- |
| `public BigDecimal add(BigDecimal value)`      | 加法运算 |
| `public BigDecimal subtract(BigDecimal value)` | 减法运算 |
| `public BigDecimal multiply(BigDecimal value)` | 乘法运算 |
| `public BigDecimal divide(BigDecimal value)`   | 触发运算 |

- **注意**：对于divide方法来说，如果除不尽的话，就会出现java.lang.ArithmeticException异常。此时可以使用divide方法的另一个重载方法；
- BigDecimal divide(BigDecimal divisor, int scale, int roundingMode): divisor：除数对应的BigDecimal对象；scale:精确的位数；roundingMode取舍模式

`BigInteger`  对象 可以表示任意精度和大小的整数

```java
BigInteger num1 = new BigInteger("9999999999999");
BigDecimal num2 = new BigDecimal("3.1415678");
```


***

## Object

- **所有的java类 直接或间接继承 Object类**

**equals()**

- **默认**：比较两个对象的地址是否相同 完全可以用 `==`代替
- **一般通过子类重写**根据具体业务进行对对象的比较

```java
object1.equals(object2);
class LeWen {
    @Override
    public boolean equals(Object obj) {
        // 1. 先判断 obj 是否为指定类型，否则直接返回false
        // 2. 将 obj 强制类型转换为指定类型
        // 3. 根据具体业务 判断 obj 和 this 是否相同
        return this == obj ? true : false;
    }
}
```

**toString()**

- 返回一个代表当前对象信息的字符串
- **默认**： 包名.类名@当前对象在堆内存中的地址信息
- 直接输出对象名称，默认**自动调用** `toString()` 方法
- **一般通过子类重写**用于输出对象的内容信息

```java
odject1.toString();
class LeWen {
    @Override
    public String toString() {
		return "重写方法，以便返回对象的内容信息";
    }
}
```

**clone**

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

## Objects

- start from **JDK 1.7**

**equals()**

- **静态方法**
- 如果参数相等，返回`true`
- 如果两个参数都是`null` ， 返回`true`
- 如果只有一个参数是`null` ，则返回`false` 。 
- 否则，使用第一个参数的[`equals`](../../java/lang/Object.html#equals-java.lang.Object-)方法确定相等性。

```java
public static boolean equals(Object a,Object b);
```

==**Objects 类的方法大多是将object 类中的方法更改为静态方法 并 在判断前加入了空指针的检查**==

***

## 包装类

- 包装类的对象不可变,创建后值无法修改
- 将基本数据类型包装成对象
- 引用数据类型的默认值可以为 `null`

```java
Object <== Boolean / Number / Character
Number <== Byte / Short / Integer / Long / Float / Double
```
**自动装箱与拆箱**：

- **自动装箱**：可以直接把基本数据类型的值或者变量赋值给包装类。
- **自动拆箱**：可以把包装类的变量直接赋值给基本数据类型。
```java
Integer intObject = 1;
int num = intObject;
```

**valueOf()**

- 创建对象并初始化,返回该对象

```java
// 以相应的基本数据类型为参数
Integer integerObject = Integer.valueOf(1);
// 以相应的字符串为参数
Double doubleObject = Double.valueOf("1.2");
```

**String ==> 基本数据类型**

```java
int i = Integer.parseInt("2");
double d = Double.parseDouble("4.5");
```

**equals()**

- 在包装类中被覆盖为  比较两个对象内容是否相同

**hashCode()**

- 在包装类中被覆盖为 返回数据值

**类的最大最小值**

- 除 Boolean 类外 , 每个包装类中都定义有静态常量	`MAX_VALUE / MIN_VALUE`

```java
Integer.MAX_VALUE;
Double.MIN_VALUE;
```

***

## Character 类

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



## Arrays 类

**fill()**

```java
Arrays.fill( arrayObject, value );		// 将 value 的值 赋值给arrayObject中的所有元素
Arrays.fill( arrayObject, start, end, value );    // 同上 ,指定赋值区域
// 注意 value 的数据类型
```

**数组 ==> 字符串**

```java
Arrays.toString(数组对象);	// 一维数组
Arrays.deepToString(数组对象);		// 多维数组
```

**数组复制**

```java
Arrays.copyOf(数组对象,个数);		// 从头开始选择, 个数 > length的部分 赋默认值 0
Arrays.copyOfRange(数组对象, start, end);	// 超出部分 赋默认值 0
```

**数组的相等**

```java
Arrays.equals(a1, a2);	// 元素个数相同,对应位置元素相同 返回 True
Arrays.deepEquals(a1, a2);	// 多维数组的比较
```

**数组的排序**

除 Boolean 的基本数据类型 都可排序	**从小到大**

```java
Arrays.sort(数组对象);	// 全数组排序
Arrays.sort(对象, start, end);	// 将 (start,end) 部分进行排序
```

**对象数组的排序**

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

**查找**

二分法,数据已自然顺序排序	除 Boolean 数据类型

```java
Arrays.binarySearch(对象, start, end, key);	// 返回下标 或 负值(数据不存在)
```

## StringBuilder/StringBuffer

**创建可变字符串对象**

类StringBuffer可以安全的用于多线程编程, 类StringBuilder 运行速度更快

**1.类StringBuilder**

```java
创建无字符的对象    public StringBuilder();// 初始容量为16个字符
创建指定容量对象    public StringBuilder(int capacity);// 初始容量为 capacity
创建指定字符串str的对象    public StringBUilder(String str);// 默认长度为 str + 16

eg:
StringBuilder strBuilder1 = new StringBuilder();
StringBuilder strBuilder2 = new StringBuilder(32);
StringBuilder strBuilder3 = new StringBuilder("hello Java!");
```

**2.类StringBuffer**

```java
创建一个空对象    public StringBuffer();	// 初始容量为16个字符
创建指定容量对象    public StringBuffer(int capacity);// 初始容量为 capacity
创建指定字符串str的对象    public StringBuffer(String str);// 默认长度为 str + 16

eg:
StringBuffer strBuffer1 = new StringBuffer();
StringBuffer strBuffer2 = new StringBuffer(32);
StringBuffer strBuffer3 = new StringBuffer("hello Java!");
```

**在可变字符串中追加和插入新内容**

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

**常用操作**

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

## 字符串 String

==类String中的方法, 实际上都是创建了一个新的String对象, 原有对象没有任何改变==

==当调用String类的某个方法并未改变String对象值,则返回引用源String对象的引用,并不会创建全新的String对象==

### **创建 String 对象**

String 对象不可变

```java
char[] data = {'a','b','c',};
String str1 = new String("...");
String str2 = "...";
```

### **操作String对象**

**字符串长度**

```java
str1.length()
```

**比较**

```java
str1.equals(str2);    // 区分大小写
str1.equalsIgnoreCase(str2);    // 忽略大小写

str1.compareTo(str2);    // 返回第一个不相同字符 的 编码差值 str1 - str2 (ASCII)
str1.compareToIgnoreCase(str2);    // 不区分大小写
```

**规范化字符串**

相同内容的**字符串文字常量** 共享同一个String对象

```java
String str1 = "Java";	// 规范化字符串
String str1 = new String("...");	// 非规范化字符串
String str2 = str1.intern();	// 返回对应的规范化字符串
```

**字符串拼接**

```java
String str1 = "Hello!";
String str2 = str1.concat(" world!");	// 返回新对象
```

**查找单个字符**

```java
public int indexOf(int ch);    // 返回 ch 第一次出现的位置 或者 -1 未找到
public int indexOf(int ch, int fromIndex);    // 从指定位置开始 , 同上

public int lastIndexOf(int ch);    // 返回 ch 最后一次出现的位置 或者 -1 未找到
public int lastIndexOf(int ch, int fromIndex);    // 从指定位置开始 , 同上
```

**查找字符串**

```java
public int indexOf(String str);    // 返回 str 第一次出现的位置 或者 -1 未找到
public int indexOf(String str, int fromIndex);    // 从指定位置开始 , 同上

public int lastIndexOf(String str);		// 返回 str 最后一次出现的位置 或者 -1 未找到
public int lastIndexOf(String str, int fromIndex);    // 从指定位置开始 , 同上

public boolean startsWith(String prefix);	// 判断是否以字符串开头
public boolean endsWith(String suffix);		// 判断是否以字符串结尾
```

**转换字符串**

```java
转换为纯小写    public String toLowerCase(String str);    // 将str中的字符串转换为纯小写,并产生新字符串
转换为纯大写    public String toUpperCase(String str);    // 将str中的字符串转换为纯大写,并产生新字符串

替换字符串字符    public String replace(char oldChar, char newChar);    // 替换字符串对应字符,返回新字符串

移除开头结尾空白字符    public String trim();    // 移除当前字符串开头结尾的空白字符,生成新字符串返回
```

**提取子字符串**

```java
public String substring(int beginIndex);	// 返回指定位置到结尾的子串
public String substring(int beginIndex, int endIndex);	// 返回指定区间的字串 左闭右开
```

**将字符串转换成数组**

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

**格式化字符串**

```java
public static String format(String format, Object...args);

eg:
int num = 2; double d = 3.5;
String str = String.format("num = %d, d = %f", num, d);
==> str = "num = 2, d = 3.500000"
```

**format 参数语法格式**

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

***

## Date

**构造器**：

```java
// 无参构造器 根据当前时间戳调用 有参构造器
public Date() { this(System.currentTimeMillis()); }
// 有参构造器 date : 从1970-01-01 00:00:00开始走到此刻的总的毫秒值。 1s = 1000ms
public Date(long date) { fastTime = date; }
```

**getTime()**：

- 用于性能分析 **计算代码运行时间**

```java
// 从1970-01-01 00:00:00开始走到Date对象时间的总的毫秒值。
long time = new Date().getTime();
```

***

## DateFormat

**作用**：

- 可以把“日期对象”或者“时间毫秒值”格式化成我们喜欢的时间形式。（格式化时间）
- 可以把字符串的时间形式解析成日期对象。（解析字符串时间）
- **抽象类**，不能直接使用

***

## SimpleDateFormat

- DateFormat 的子类
- `java.text.SimpleDateFormat`

```java
// 指定时间的格式创建简单日期格式化对象
public SimpleDateFormat(String pattern);
SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss EEE a")
// 把日期对象格式化为指定的日期格式，返回的是格式化的字符串
public final String format(Date date);
// 把时间毫秒值格式化为指定的日期格式，返回的是格式化的字符串
public final String format(Object time);
// 把字符串的时间解析成日期对象
public Date parse(String source) throws ParseException;
```

|  格式字符 |    含义    | 类型  |    Examples    |
| :------: | :-------: | :--: | :------------: |
| G | 时代指示器 | Text | AD；BC |
| y | 年 | Year  | `1996`;`96` |
| Y | 周年 | Year  | `2009`;`09` |
| M | 月份 | Month | `July`;`Jul`;`07` |
| L | 月份 | Month | `July`;`Jul`;`07` |
| w | Week in year | Number | 27 |
| W | Week in year | Number | 2 |
| D | Day in year | Number | 189 |
| d | Day in month | Number | 10 |
| F | Day of week in month | Number | 2 |
| E | Day name in week | Text | `Tuesday`; `Tue` |
| u | Day number of week | Number | 1 |
| a | Am/pm marker | Text | AM；PM |
| H | Hour in day (0-23) | Number | 0 |
| k | Hour in day (1-24) | Number | 24 |
| K | Hour in am/pm (0-11) | Number | 0 |
| h | Hour in am/pm (1-12) | Number | 12 |
| m | Minute in hour | Number | 30 |
| s | Second in minute | Number | 55 |
| S | Millisecond | Number | 978 |
| z | 时区 | General time zone |`PST`;`GMT-08:00` |
|  |  |  | Pacific Standard Time |
| Z | 时区 | RFC 822 time zone | `-0800` |
| X | 时区 | ISO 8601 time zone | `-08`;`-0800`;`-08:00` |

- **Text:**对于格式化，如果模式字母的数量是4以上，则使用完整的形式;  否则，如果有的话，使用简短或缩写形式。 对于解析，两种形式都是接受的，与模式字母的数量无关。  
- **Number:**对于格式化，模式字母的数量是最小位数，而较短的数字将零填充到此数量。  对于解析，模式字母的数量将被忽略，除非需要分隔两个相邻的字段。 
- **Year:**如果格式化程序的[`Calendar`](../../java/text/DateFormat.html#getCalendar--)是公历，则应用以下规则。
  - 对于格式化，如果模式字母数为2，那么年份将被截断为2位数; 否则被解释为[number](#number) 。 
  - 对于解析，如果模式字母的数量大于2，则年份将按字面解释，而不管数字的数量。 所以使用“MM / dd /  yyyy”模式，“01/11/12”解析到公元12年1月11日 
  - 为了使用缩写年份模式（“y”或“yy”）进行解析，  `SimpleDateFormat`必须解释相对于某个世纪的缩写年份。  它是通过将日期调整为在创建`SimpleDateFormat`实例之后的80年之前和20年之后进行的。  例如，使用1997年1月1日创建的“MM / dd /  yy”模式和`SimpleDateFormat`实例，字符串“01/11/12”将被解释为2012年1月11日，而字符串“05/04 /  64“将被解释为1964年5月4日。在解析期间，只有由[`Character.isDigit(char)`](../../java/lang/Character.html#isDigit-char-)定义的两个数字组成的字符串将被解析为默认世纪。  任何其他数字字符串，例如一位数字字符串，三位或三位以上数字字符串，或两位数字字符串（不全部为数字）（例如“-1”），均按字面解释。  所以“01/02/3”或“01/02/003”的解析方式与公元3年1月2日相同。 同样，“01/02 /  -3”在公元前4年1月2日被解析。 

***

## Calendar

- **抽象类**，不能直接创建对象

```java
// 通过静态方法，返回日历类的对象
public static Calendar getInstance();
Calendar calendar = Calender.getInstance();

// 取日期中的某个字段信息
public int get(int field);
int year = calendar.get(Calendar.YEAR);

// 拿到此刻日期对象
public final Date getTime();

// 拿到此刻时间毫秒值
public long getTimeInMillis();

// 修改日历的某个字段信息
public void set(int field,int value);
calendar.set(Calendar.DAY_OF_YEAR , 701);

// 为某个字段增加/减少指定的值
public void add(int field,int amount);
calendar.add(Calendar.DAY_OF_YEAR , 701);
```
***

## Collections

```java
// 将所有指定元素 elements添加到指定集合 c 中
public static <T> boolean addAll(Collection<? super T> c, T...elements);
Collections.addAll(names,"曹操","贾乃亮","王宝强","陈羽凡");

// 根据自然顺序对指定List集合中的元素从小到大排序. 
public static <T extends Comparable<? super T> > void sort(List<T> list);

// 在一个已经按元素的自然顺序从小到大排序的指定List集合中查找指定元素key, 或 负值
public static <T> int binarySearch(List<? extends Comparable<? super T> > list, T key);	

// 将集合src中的所有元素复制到dest中的相同位置.
public static <T> void copy(List<? super T> dest, List<? extends T> src);	

// 根据元素的自然顺序, 返回指定Collection集合中的最大元素.
public static <T extends Object & Comparable<? super T> > T max(Collection<? extends T> coll);	

// 根据元素的自然顺序, 返回指定Collection集合中的最小元素.
public static <T extends Object & Comparable<? super T> > T min(Collection<? extends T> coll);	

// 将指定List集合中的所有元素反转到相应的位置
public static void reverse(list<? > list);	

// 打乱List集合的顺序
public static void shuffle(List<?> list);
```

## `File` 类

- 代表操作系统的文件对象。
- 是用来操作操作系统的文件对象的，删除文件，获取文件信息，创建文件（文件夹）...
- 广义来说操作系统认为文件包含（**文件和文件夹**）
- **尽量使用相对路径(确保程序的跨平台性) 运行指定相对或绝对路径名**

**构造器：**

```java
// 根据路径获取文件对象
public File(String pathname);
// 根据父路径和文件名称获取文件对象！
public File(String parent , String child);
// 根据父文件夹对象和文件名获取文件对象
public File(File parent , String child);

File file = new File("./Data.txt");
```

**常用方法：**

```java
public boolean canRead();		// 判断当前文件是否可读	
public boolean canWrite();		// 判断当前文件是否可读	
public boolean exists();		// 判断当前文件或对象是否存在	
public boolean isDirectory();	// 判断是否为已存在的目录		
public boolean isFile();		// 判断是否为已存在的文件		
public boolean isHidden();		// 判断是否为一个隐藏文件		

public boolean delete();		// 删除当前文件或目录 只能删除空文件夹
public String getName();		// 返回当前文件或目录的名称	
public String getParent();		// 返回当前对象或目录的父目录的路径名	
public String getPath();		// 返回定义当前File对象或目录的路径名	
public File getAbsoluteFile();	// 返回此抽象路径名的绝对形式。
public long lastModified();		// 返回最后一次被修改的时间	
public long length();			// 返回文件的长度(字节)		
public String[] list();			// 返回目录中的一级文件名称
public File[] listFiles();		// 返回目录中的一级文件对象

public boolean mkdir();			// 创建当前File对象表示的目录		
public boolean mkdirs();		// 创建当前File对象表示的目录即所有不存在的父目录	

public boolean renameTo(File dest);		// 重命名		
```

## `Properties`属性集对象

- **Properties**代表的是一个属性文件，可以把键值对的数据存入到一个属性文件中去。
- **属性文件**：后缀是.properties结尾的文件,里面的内容都是 key=value。
- 实现了`Map`接口，在map集合的基础上实现了**将属性集存取到文件**中

**构造器：**

```java
public Properties();
```

**方法：**

```java
// 保存一对属性
public Object setProperty(String key, String value);
// 使用此属性列表中指定的键搜索属性值
public String getProperty(String key);

// 所有键的名称的集合
public Set<String> stringPropertyNames();

// 保存数据到属性文件中去
public void store(OutputStream out, String comments);
// 保存数据到属性文件中去 comments: 保存注释
public void store(Writer fw, String comments);

// 加载属性文件的数据到属性集对象中去
public synchronized void load(InputStream inStream);
// 加载属性文件的数据到属性集对象中去
public synchronized void load(Reader fr);
```

***

## `InetAddress`

- 一个该类的对象就代表一个IP地址对象。

**方法：**

```java
// 获得本地主机IP地址对象。
static InetAddress getLocalHost();
// 根据IP地址字符串或主机名获得对应的IP地址对象。
static InetAddress getByName(String host);

// 获得主机名。
String getHostName();
// 获得IP地址字符串。
String getHostAddress();
// 目标IP地址在指定时间内是否可达
public boolean isReachable(int timeout);

inetAddress ip = InetAddress.getByName("www.baidu.com");
String hostName = ip.getHostName();
```

***

# UDP通信

**特点：**

* 面向无连接的协议
* 发送端只管发送，不确认对方是否能收到。
* 基于数据包进行数据传输。
* 发送数据的包的大小限制64KB以内
* 因为面向无连接，速度快，但是不可靠。会丢失数据！

## `DatagramPacket`

- 数据包对象，用来封装要发送或要接收的数据

**构造器：**

```java
/** -- 发送端用：
  * 创建发送端数据包对象
  * buf：要发送的内容，字节数组
  * length：要发送内容的长度，单位是字节
  * address：接收端的IP地址对象
  * port：接收端的端口号
 */
new DatagramPacket(byte[] buf, int length, InetAddress address, int port);

/** -- 接收端用：
  * 创建接收端的数据包对象
  * buf：用来存储接收到内容
  * length：能够接收内容的长度
 */
new DatagramPacket(byte[] buf, int length);
```

**方法：**

```java
// 获得实际接收到的字节个数
int getLength();
```

## `DatagramSocket`

- 发送对象，用来发送或接收数据包

**构造器：**

```java
// 创建发送端的Socket对象，系统会随机分配一个端口号。
DatagramSocket();
// 创建接收端的Socket对象并指定端口号
DatagramSocket(int port);
```

**方法：**

```java
// 发送数据包
void send(DatagramPacket dp);
// 接收数据包
void receive(DatagramPacket p);
```

***

# Socket网络通信

- 面向连接的协议，Socket网络编程
- **Socket**：一个该类的对象就代表一个**客户端程序**
- **ServerSocket**：一个该类的对象就代表一个**服务器端程序**

## BIO通信模式

- **同步阻塞式通信**

- （Socket网络编程也就是上面的通信架构）
- 同步：当前线程要自己进行数据的读写操作。
- 异步：当前线程可以去做其他事情。
- 阻塞： 在数据没有的情况下，还是要继续等待着读。
- 非阻塞：在数据没有的情况下，会去做其他事情，一旦有了数据再来获取。（柜台取款，取个号，然后坐在椅子上做其它事，等号广播会通知你办理）
- **BIO表示同步阻塞式IO**服务器实现模式为一个连接一个线程，即客户端有连接请求时服务器端就需要启动一个线程进行处理，如果这个连接不做任何事情会造成不必要的线程开销，当然可以通过线程池机制改善。同步阻塞式性能极差：大量线程，大量阻塞。

## 伪异步通信

- 引入了**线程池**
- 不需要一个客户端一个线程，可以实现1个线程复用来处理很多个客户端
- 这种架构，可以避免系统的死机，因为不会出现很多线程，线程可控。
- **高并发下性能较差差**：线程数量少，数据依然是阻塞的。数据没有来线程还是要等待

## NIO同步非阻塞IO

- 服务器实现模式为请求对应一个线程，
- 即客户端发送的连接请求都会注册到**多路复用器**上，
- 多路复用器**轮询**到连接**有I/O请求时才启动一个线程进行处理**。
- **同步**：线程还是要不断的接收客户端连接，以及处理数据。
- **非阻塞**：如果一个管道没有数据，不需要等待，可以轮询下一个管道是否有数据

## AIO示异步非阻塞IO

- 服务器实现模式为一个有效请求一个线程，
- 客户端的I/O请求都是由**操作系统先完成IO操作后再通知服务器应用**来启动线程进行处理。
- 异步：服务端线程接收到了客户端管道以后就交给底层处理它的io通信。
- 非阻塞：底层也是客户端有数据才会处理，有了数据以后处理好通知服务器应用来启动线程进行处理。

## `Channel`

- Channel是一个对象，可以通过它读取和写入数据。可以把它看做是IO中的流，不同的是：
  - Channel是**双向的**，既可以读又可以写，而流是单向的
  - Channel可以进行**异步**的读写
  - 对Channel的读写**必须通过buffer对象**

**实现类：**

```java
FileChannel inChannel = new FileInputStream(new File("test.txt")).getChannel();
FileChannel outChannel = new FileOutputStream(new File("test.txt")).getChannel();
```

* FileChannel：从文件读取数据的
* DatagramChannel：读写UDP网络协议数据
* SocketChannel：读写TCP网络协议数据
* ServerSocketChannel：可以监听TCP连接

## `Buffer`

- 包含一些要写入或者读到Stream对象的。**应用程序不能直接对 Channel 进行读写操作**，而**必须通过 Buffer 来进行**，即 Channel 是通过 Buffer 来读写数据的。

**实现类：**

```java
// 获得容器buffer
public static ByteBuffer allocate(int capacity);
ByteBuffer buffer = ByteBuffer.allocate(1024);
```

- ByteBuffer
- CharBuffer
- DoubleBuffer
- FloatBuffer
- IntBuffer
- LongBuffer
- ShortBuffer

**方法：**

```java
// 将 buffer 读 / 写模式切换
public final Buffer flip();
buffer.flip();

// 从通道中读取 buffer 对象
public abstract int write(ByteBuffer src);
outChannel.write(buffer);

// 清空 buffer 缓冲区，清空整个缓冲区
public final Buffer clear();
// 清空 buffer 缓冲区，只清空已读部分
// 任何未读的数据都被移到缓冲区的起始处，新写入的数据将放到缓冲区未读数据的后面。
public abstract ByteBuffer compact();
```



## `Selector`

- 可以注册到很多个Channel上，监听各个Channel上发生的事件，并且能够根据事件情况决定Channel读写。

**构造器：**

```java
Selector selector = Selector.open();
```

**方法：**

```java
// 设置通道为非阻塞 
public final SelectableChannel configureBlocking(boolean block);
channel.configureBlocking(false);
// 多路复用器开始负责使用管道接收客户端的连接请求 -> 然后管理通道
public final SelectionKey register(Selector sel, int ops);
channel.register(selector,  SelectionKey.OP_ACCEPT);

// 注册的Channel 必须设置成 异步模式 才可以,否则异步IO就无法工作，这就意味着我们不能把一个 FileChannel 注册到 Selector ，因为 FileChannel 没有异步模式，但是网络编程中的 SocketChannel 是可以的。
```

**SelectionKey：** 

- 代表这个通道在此 Selector 上注册。当某个 Selector 通知您某个传入事件时，它是通过提供对应于该事件的 SelectionKey 来进行的。
- SelectionKey 还可以用于取消通道的注册。

**方法：**

```java
// - The interest set 从通道中获取到的事件
int interestSet = selectionKey.interestOps();
boolean isInterestedInAccept  = interestSet & SelectionKey.OP_ACCEPT;
boolean isInterestedInConnect = interestSet & SelectionKey.OP_CONNECT;
boolean isInterestedInRead    = interestSet & SelectionKey.OP_READ;
boolean isInterestedInWrite   = interestSet & SelectionKey.OP_WRITE; 
    
// - The ready set 操作就绪集合
int readySet = selectionKey.readyOps();
selectionKey.isAcceptable();
selectionKey.isConnectable();
selectionKey.isReadable();
selectionKey.isWritable();

// - The Channel
Channel  channel  = selectionKey.channel();
// - The Selector
Selector selector = selectionKey.selector(); 

// - An attached object (optional) 通道上的附加信息（可用于标识等）
selectionKey.attach(theObject);
Object attachedObj = selectionKey.attachment();
SelectionKey key = channel.register(selector, SelectionKey.OP_READ, theObject);

// 获取 selector 中的 channel 集合
Set<SelectionKey> selectedKeys = selector.selectedKeys();
```

## 异步通道

* `AsynchronousSocketChannel`
* `AsynchronousServerSocketChannel`
* `AsynchronousFileChannel`
* `AsynchronousDatagramChannel`

**附加方法：**

```java
// 异步操作回调函数
void completed(V result, A attachment);
void failed(Throwable exc, A attachment);
```



## `Socket`

- **客户端程序**
- 只要执行该方法，就会立即连接指定的服务器程序
  - 如果**连接不成功**，则会抛出异常。
  - 如果**连接成功**，则表示三次握手通过。

**构造器：**

```java
// 根据ip地址字符串和端口号创建客户端Socket对象
Socket(String host, int port);
```

**方法：**

```java
// 获得字节输出流对象
OutputStream getOutputStream();
// 获得字节输入流对象
InputStream getInputStream();
// 获取通信的远程主机IP信息
public SocketAddress getRemoteSocketAddress();
// 通知对方输出结束
public void shutdownOutput();
```

## `ServerSocket`

**构造器：**

```java
public ServerSocket(int port);
```

**方法：**

```java
// 等待接收一个客户端的Socket管道连接请求，连接成功返回一个Socket对象
public Socket accept();
```

**多请求处理：创建独立线程**

- 线程数量受限于服务端性能

```java
// 1.注册端口: public ServerSocket(int port)
ServerSocket serverSocket = new ServerSocket(9999);
// 2.定义一个循环不断的接收客户端的连接请求
while(true){
    // 3.开始等待接收客户端的Socket管道连接。
    Socket socket = serverSocket.accept();
    // 4.每接收到一个客户端必须为这个客户端管道分配一个独立的线程来处理与之通信。
    new Thread(() -> {
        try{
            BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String line ;
            while((line = br.readLine())!=null){
                System.out.println(socket.getRemoteSocketAddress()+"说："+line);
            }
        }catch (Exception e){
            System.out.println(socket.getRemoteSocketAddress()+"下线了~~~~~~");
        }
    }).start();
}
```

**多请求处理：线程池**

- **优势**：不会引起系统的死机，可以控制并发线程的数量。
- **劣势**：同时可以并发的线程将受到限制。
- **适合短连接**

```java
ServerSocket ss = new ServerSocket(9999);

// 一个服务端只需要对应一个线程池
HandlerSocketThreadPool handlerSocketThreadPool = new HandlerSocketThreadPool(3, 100);

// 客户端可能有很多个
while(true){
    Socket socket = ss.accept() ;
    System.out.println("有人上线了！！");
    // 每次收到一个客户端的socket请求，都需要为这个客户端分配一个
    // 独立的线程 专门负责对这个客户端的通信！！
    handlerSocketThreadPool.execute(new ReaderClientRunnable(socket));
}
```



***

# 数据结构

## 红黑树

- **自平衡的二叉查找树**

**特点**：

1. 每一个节点或是红色的，或者是黑色的。
2. 根节点必须是黑色
3. 每个叶节点(Nil)是黑色的；（如果一个节点没有子节点或者父节点，则该节点相应的指针属性值为Nil，这些Nil视为叶节点）
4. 如果某一个节点是红色，那么它的子节点必须是黑色(不能出现两个红色节点相连的情况)
5. 对每一个节点，从该节点到其所有后代叶节点的简单路径上，均包含相同数目的黑色节点；

![](https://raw.githubusercontent.com/which-biscuits/pigGo/main/java_red_black_tree.png)

- **元素插入**：每一次插入完毕以后，使用黑色规则进行校验，如果不满足红黑规则，就需要通过变色，左旋和右旋来调整树，使其满足红黑规则；

## Queue 接口

典型的队列是一种**"先进先出(FIFO)"**集合, 即元素从集合的某一端放入, 另一端取出, 并且元素放入集合的顺序与取出的顺序相同

```java
public interface Queue<E> extends Collection<E> {
    boolean add(E e);	// 将指定元素添加到当前队列的尾部
    boolean offer(E e);	// 将指定元素添加到当前队列的尾部
    E remove();			// 获取并移除当前队列头部的元素
    E poll();			// 获取并移除当前队列头部的元素
    E element();		// 获取但不删除当前队列头部的元素
    E peek();			// 获取但不移除当前队列头部的元素
   
}
```

***

## Deque 接口

- 双端队列, 双端队列允许在它的双端同时添加和移除元素

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

***

# 正则表达式

- **Regex**
- 链接 [[RoadToCoding](https://www.r2coding.com/#/README?id=正则表达式)]:

**正则表达式匹配**:

```java
public boolean matches(String regex);
String string = "forTest123456";
string.matches("\\w{4, }");
```

**正则表达式分割**：

```java
public String[] split(String regex);
string.split("\\w");
```

**正则表达式替换**：

```java
public String replaceAll(String regex, String replacement)
string.replaceAll("\\w", "/");
```

**正则匹配模式**：

```java
// 1.定义爬取规则
String regex = "([\u4E00-\u9FA5·]{2,16})|([a-zA-Z][a-zA-Z\s]{0,20}[a-zA-Z])";
// 2.编译正则表达式成为一个匹配规则对象
Pattern pattern = Pattern.compile(regex);
// 3.通过匹配规则对象得到一个匹配数据内容的匹配器对象
Matcher matcher = pattern.matcher("wzx王子潇wzx");
// 4.通过匹配器去内容中爬取出信息
while(matcher.find()){
    System.out.println(matcher.group());
}
```

## 字符

| **表达式** | **描述**                                               |
| ---------- | ------------------------------------------------------ |
| `[abc]`    | 字符集。匹配集合中所含的任一字符。                     |
| `[^abc]`   | 否定字符集。匹配任何不在集合中的字符。                 |
| `[a-z]`    | 字符范围。匹配指定范围内的任意字符。                   |
| `.`        | 匹配除换行符以外的任何单个字符。                       |
| `\`        | 转义字符。                                             |
| `\w`       | 匹配任何字母数字，包括下划线（等价于`[A-Za-z0-9_]`）。 |
| `\W`       | 匹配任何非字母数字（等价于`[^A-Za-z0-9_]`）。          |
| `\d`       | 数字。匹配任何数字。                                   |
| `\D`       | 非数字。匹配任何非数字字符。                           |
| `\s`       | 空白。匹配任何空白字符，包括空格、制表符等。           |
| `\S`       | 非空白。匹配任何非空白字符。                           |

## 分组和引用

| **表达式**       | **描述**                                                     |
| ---------------- | ------------------------------------------------------------ |
| `(expression)`   | 分组。匹配括号里的整个表达式。                               |
| `(?:expression)` | 非捕获分组。匹配括号里的整个字符串但不获取匹配结果，拿不到分组引用。 |
| `\num`           | 对前面所匹配分组的引用。比如`(\d)\1`可以匹配两个相同的数字，`(Code)(Sheep)\1\2`则可以匹配`CodeSheepCodeSheep`。 |

## 锚点和边界

| **表达式** | **描述**                                                     |
| ---------- | ------------------------------------------------------------ |
| `^`        | 匹配字符串或行开头。                                         |
| `$`        | 匹配字符串或行结尾。                                         |
| `\b`       | 匹配单词边界。比如`Sheep\b`可以匹配`CodeSheep`末尾的`Sheep`，不能匹配`CodeSheepCode`中的`Sheep` |
| `\B`       | 匹配非单词边界。比如`Code\B`可以匹配`HelloCodeSheep`中的`Code`，不能匹配`HelloCode`中的`Code`。 |

## 数量表示

| **表达式** | **描述**                                   |
| ---------- | ------------------------------------------ |
| `?`        | 匹配前面的表达式0个或1个。即表示可选项。   |
| `+`        | 匹配前面的表达式至少1个。                  |
| `*`        | 匹配前面的表达式0个或多个。                |
| `|`        | 或运算符。并集，可以匹配符号前后的表达式。 |
| `{m}`      | 匹配前面的表达式m个。                      |
| `{m,}`     | 匹配前面的表达式最少m个。                  |
| `{m,n}`    | 匹配前面的表达式最少m个，最多n个。         |

## 预查断言

| **表达式** | **描述**                                                     |
| ---------- | ------------------------------------------------------------ |
| `(?=)`     | 正向预查。比如`Code(?=Sheep)`能匹配`CodeSheep`中的`Code`，但不能匹配`CodePig`中的`Code`。 |
| `(?!)`     | 正向否定预查。比如`Code(?!Sheep)`不能匹配`CodeSheep`中的`Code`，但能匹配`CodePig`中的`Code`。 |
| `(?<=)`    | 反向预查。比如`(?<=Code)Sheep`能匹配`CodeSheep`中的`Sheep`，但不能匹配`ReadSheep`中的`Sheep`。 |
| `(?<!)`    | 反向否定预查。比如`(?<!Code)Sheep`不能匹配`CodeSheep`中的`Sheep`，但能匹配`ReadSheep`中的`Sheep`。 |

## 特殊标识

| **表达式** | **描述**                   |
| ---------- | -------------------------- |
| `/.../i`   | 忽略大小写。               |
| `/.../g`   | 全局匹配。                 |
| `/.../m`   | 多行修饰符。用于多行匹配。 |

***

# `junit`单元测试

- 单元测试是指程序员写的测试代码给自己的类中的方法进行预期正确性的验证。
- **单元**：在Java中，一个类就是一个单元
- **单元测试**：用Junit编写的一小段代码，用来对某个类中的某个方法进行功能测试或业务逻辑测试。
- **测试类的命名规范**：以Test开头，以业务类类名结尾，使用驼峰命名法

**测试方法要求：**

*  必须`public`修饰
*  **没有返回值没有参数**
*  必须使注解`@Test`修饰

**测试结果断言：**

```java
/**
 * 参数一：测试失败的提示信息。
 * 参数二：期望值。
 * 参数三：实际值
 */
Assert.assertEquals("登录业务功能方法有错误，请检查！","success",rs);
```

**常用注解：**

```java
// @Before：用来修饰实例方法，该方法会在每一个测试方法执行之前执行一次。
@Before
public void before();

// @After：用来修饰实例方法，该方法会在每一个测试方法执行之后执行一次。
@After
public void after();

// @BeforeClass：用来静态修饰方法，该方法会在所有测试方法之前只执行一次。
@BeforeClass
public static void beforeClass();

// @AfterClass：用来静态修饰方法，该方法会在所有测试方法之后只执行一次。
@AfterClass
public static void afterClass();
```

# 反射

- 对于任何一个类，在"**运行的时候**"都可以**直接得到这个类全部成分**。
  - 类的构造器**对象**。（Constructor）
  - 类的成员变量**对象**。（Field）
  - 类的成员方法**对象**。（Method）
- 可以**破坏面向对象的封装性**（暴力反射）
- 可以**破坏泛型的约束性**（反射不受泛型的约束）

- **核心思想和关键**：得到编译以后的**class文件对象**。

```java
HelloWorld.java -> javac -> HelloWorld.class;
Class c = HelloWorld.class;

Class : 字节码文件的类型
Constructor : 构造器的类型
Field : 成员变量的类型
Method : 方法的类型
```

## `Class`对象

**对象获取：**

```java
// - 通过类名获取
Class c1 = Student.class;

// - 通过对象获取
Student student = new Student();
Class c2 = student.getClass();

// - 通过**全限**名(全路径限定名称)
Clas c3 = Class.forName("org.junit.Before");
```

**`Class`的方法：**

```java
// 获得类名字符串：类名
String getSimpleName();
// 获得类全名：包名+类名
String getName();  
```

## `Constructor`对象

**对象获取：**

```java
// 只能拿**public**修饰的无参构造器
public Constructor getConstructor();
// 指定构造器的参数列表，调用有参构造器
public Constructor getConstructor(Class... parameterTypes);
// 获取所有的构造器，只能拿**public**修饰的构造器
public Constructor[] getConstructors();

// 获取任意权限的无参构造器
public Constructor getDeclaredConstructor();
// 指定构造器的参数列表，调用有参构造器    
public Constructor getDeclaredConstructor(Class... parameterTypes);
// 获取所有申明的构造器，无所谓权限
public Constructor[] getDeclaredConstructors();
```

**方法：**

```java
// 获取构造器参数个数
public int getParameterCount();
// 获取构造器参数类型列表
public Class<?>[] getParameterTypes();

// 创建对象，注入构造器需要的数据。
T newInstance(Object... initargs);
// 修改访问权限，true代表暴力攻破权限，false表示保留不可访问权限(暴力反射)
void setAccessible(true);
```

## `field对象`

**对象获取：**

```java
// 根据成员变量名获得对应Field对象，只能获得public修饰
Field getField(String name);
// 获得所有的成员变量对应的Field对象，只能获得public的
Field[] getFields();

// 根据成员变量名获得对应Field对象，只要申明了就可以得到
Field getDeclaredField(String name);
// 获得所有的成员变量对应的Field对象，只要申明了就可以得到
Field[] getDeclaredFields();
```

**方法：**

```java
// 获取变量名称
public String getName();
// 获取变量类型
public Class<?> getType();
// obj: 指定对象, value: 指定值
public void set(Object obj, Object value);
// obj: 指定对象
public Object get(Object obj);
// 修改访问权限，true代表暴力攻破权限，false表示保留不可访问权限(暴力反射)
void setAccessible(true);
```

## `Method`对象

**对象获取：**

```java
// 根据方法名和参数类型获得对应的方法对象，只能获得public的
Method getMethod(String name,Class...args);
// 获得类中的所有成员方法对象，返回数组，只能获得public修饰的且包含父类的
Method[] getMethods();
    
// 根据方法名和参数类型获得对应的方法对象，包括private的
Method getDeclaredMethod(String name,Class...args);
// 获得类中的所有成员方法对象，返回数组,只获得本类申明的方法。
Method[] getDeclaredMethods();
```

**方法：**

```java
// obj: 执行方法的对象, args: 方法的参数列表 并获取返回值
Object invoke(Object obj, Object... args);
// 返回值参数
public Class<?> getReturnType();
// 获取参数个数
public int getParameterCount();
// 获取参数类型列表
public Class<?>[] getParameterTypes();
```

# 注解

- 在类上，方法上，成员变量，构造器，...上对成分进行编译约束，标记等操作**since JDK1.5**
- 注解相当一种标记，是类的组成部分，可以给类携带一些额外的信息。
- 编译器或JVM看的，编译器或JVM可以根据注解来完成对应的功能。

**注解的定义：**

```java
public @interface Test {
    /* 内部内容 */
}
```

**注解的属性：**

- 格式1：数据类型 属性名();

       - 格式2：数据类型 属性名() default 默认值
       - 注解的属性需要赋值，默认值则非必须赋值

```java
public @interface Test {
    
    String name();
    String[] authors() default [];   
}
@Test(name="注解", authors="")
```

**特殊属性：**

- 如果只有一个value属性的情况下，使用value属性的时候**可以省略value名称不写**
- 多属性时不允许省略

```java
String value();

@Book("/deleteBook.action")
@Book(value = "/deleteBook.action" , age = 12)
```

**元注解：**

- 用于注解 注解的特殊注解
- `@Target`：约束自定义注解只能在哪些地方使用
- `@Retention`：申明注解的生命周期

```java
QTarget
/* 可使用的值定义在ElementType枚举类中，常用值如下
 * - TYPE，类，接口
 * - FIELD, 成员变量
 * - METHOD, 成员方法
 * - PARAMETER, 方法参数
 * - CONSTRUCTOR, 构造器
 * - LOCAL_VARIABLE, 局部变量
 */
@Target({ElementType.FIELD})
    
@Retention
/* 作用：用来标识注解的生命周期(有效存活范围)
 * 可使用的值定义在RetentionPolicy枚举类中，常用值如下
 * - SOURCE：注解只作用在源码阶段，生成的字节码文件中不存在
 * - CLASS：注解作用在源码阶段，字节码文件阶段，运行阶段不存在，默认值.
 * - RUNTIME：注解作用在源码阶段，字节码文件阶段，运行阶段（开发常用）
 */
@Retention({RetentionPolicy.RUNTIME})
```

**注解解析：**

**`Annotation`**: 注解类型，该类是所有注解的父类。注解都是一个Annotation的对象

**`AnnotatedElement`**:该接口定义了与注解解析相关的方法

```java
// 所有的类成分Class, Method , Field , Constructor:都实现了AnnotatedElement接口
// 他们都拥有解析注解的能力

// 获得当前对象上使用的所有注解，返回注解数组。
Annotation[]  getDeclaredAnnotations();
// 根据注解类型获得对应注解对象
T getDeclaredAnnotation(Class<T> annotationClass);
// 判断当前对象是否使用了指定的注解，如果使用了则返回true，否则false
boolean isAnnotationPresent(Class<Annotation> annotationClass);
```

**解析原理：**

- 注解在哪个成分上，我们就先拿哪个成分对象。
- **注解作用成员方法**，则要获得该成员方法对应的Method对象，再来拿上面的注解
- **注解作用在类上**，则要该类的Class对象，再来拿上面的注解
- **注解作用在成员变量上**，则要获得该成员变量对应的Field对象，再来拿上面的注解

# 动态代理

- **拦截对真实对象方法的直接访问，增强真实对象方法的功能**
- **动态代理**：在程序运行时创建的代理对象
- 动态代理可以对被代理对象的方法进行增强，可以在不修改方法源码的情况下，增强被代理对象方法的功能，在方法执行前后做任何你想做的事情

**动态代理的获取：**

```java
public static Object newProxyInstance(ClassLoader loader, Class[] interfaces, InvocationHandler h);

/**
1、obj.getClass().getClassLoader()目标对象通过getClass方法获取类的所有信息后，调用getClassLoader() 方法来获取类加载器。获取类加载器后，可以通过这个类型的加载器，在程序运行时，将生成的代理类加载到JVM即Java虚拟机中，以便运行时需要！ 

2、obj.getClass().getInterfaces()获取被代理类的所有接口信息，以便于生成的代理类可以具有代理类接口中的所有方法。 

3、InvocationHandler 这是调用处理器接口，它自定义了一个 invoke 方法，用于集中处理在动态代理类对象上的方法调用，通常在该方法中实现对委托类方法的处理以及访问.
*/
```

**invoke方法：**

```java
public Object invoke(Object proxy, Method method, Object[] args 
/**
1、Object proxy生成的代理对象，在这里不是特别的理解这个对象，但是个人认为是已经在内存中生成的proxy对象。 
2、Method method：被代理的对象中被代理的方法的一个抽象。
3、Object[] args：被代理方法中的参数。这里因为参数个数不定，所以用一个对象数组来表示。
*/
```

**动态代理的使用：**

```java
public class LogProxy {
	// 提供一个方法，用于生产需要被代理对象的代理对象。
	public static Object getProxy(Object obj) {
		return Proxy.newProxyInstance(obj.getClass().getClassLoader(), obj
				.getClass().getInterfaces(), new InvocationHandler() {
			// 重写函数的运行方法
			@Override
			public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
				// 先记录开始时间点
				long startTimer = System.currentTimeMillis();
				try {
                    // 真正去触发被代理对象中该方法的执行
					return method.invoke(obj, args);
				} catch (Exception e) {
					throw new RuntimeException(e);
				} finally {
					long endTimer = System.currentTimeMillis();
					// 在什么时刻执行完，花费了多长时间完成
					SimpleDateFormat sdf = new SimpleDateFormat(
							"yyyy-MM-dd HH:mm:ss");
					System.out.println(method.getName() + "方法执行->" + sdf.format(endTimer) + "，耗时：" + (endTimer - startTimer));
				}
			}
		});
	}
}
```

# XML

**简介：**

- 英文：Extensible Markup Language 可扩展的标记语言，由各种标记(标签==元素)组成。
- 可扩展：所有的标签都是自定义的，可以随意扩展的。如：\<abc/>，<hobby>,<sex>
- 标记语言：整个文档由各种标签组成。清晰，数据结构化！
- XML是通用格式标准，全球所有的技术人员都知道这个东西，都可能会按照XML的规范存储数据，交互数据！！

**作用：**

- 数据交换：不同的计算机语言之间，不同的操作系统之间，不同的数据库之间，进行数据交换。
- 配置文件：在后期我们主要用于各种框架的配置文件。

**特点：**

* 用于数据交互，用于数据的存储，**用于做系统的配置文件（大家后期天天会用）**
* **区分大小写**
* 非常严谨，只要有错误，解析器就不能解析
* 可以扩展的，所有的标签都是程序员自己创建出来。
* **XML文件的后缀为.xml**

## 组成

### 声明（抬头）

```xml
<?xml version="1.0" encoding="UTF-8" ?>
```

**声明的三种属性**

| **文档声明的三个属性** | **说明**                                                     |
| ---------------------- | ------------------------------------------------------------ |
| **version**            | 指定XML文件使用的版本，取值是1.0                             |
| **encoding**           | 当前XML文件使用的编码(字符集)                                |
| **standalone**         | 指定当前这个XML文件是否是一个独立的文件，<br>省略的，默认是独立文件。 |

**版本说明**

```
**W3C **在1988年2月发布1.0版本，2004年2月又发布1.1版本，因为1.1版本不能向下兼容1.0版本，所以1.1没有人用。在2004年2月W3C又发布了1.0版本的第三版。我们学习的还是1.0版本。
```

### 元素(标签)

- 区分大小写
- 命名：不能有空格冒号
- 根元素：**有且只能有一个根元素**

```xml
<person>
</person>
```

### 属性

```xml
<persion id="110">
```

![1552225389171](https://raw.githubusercontent.com/which-biscuits/pigGo/main/XML_01.png)

### 注释

![1552225416050](https://raw.githubusercontent.com/which-biscuits/pigGo/main/XML_02.png)

### 实体字符

- XML中的实体字符与HTML一样。因为很多符号已经被文档结构所使用，所以在元素体或属性值中想使用这些符号就必须使用实体字符

![1552225605462](https://raw.githubusercontent.com/which-biscuits/pigGo/main/202207221523603.png)

### CDATA 字符数据区

- 解决大量使用实体字符导致的数据可读性降低

![1552225954936](https://raw.githubusercontent.com/which-biscuits/pigGo/main/XML_03.png)

```xml
<sql>
        <![CDATA[
            select * from user where age < 20
        ]]>
    </sql>
```

### 处理指令

![1552226032459](https://raw.githubusercontent.com/which-biscuits/pigGo/main/XML_04.png)

## DTD约束

- DTD: Document Type Definiation 文档类型定义
- 纯文本文件，指定了XML约束规则。
- **不能验证数据类型**
- 因为DTD是一个文本文件，本身不能验证是否正确。

**DTD文件的导入：**

| **导入**DTD文件的两种格式                             | **说明**                               |
| ----------------------------------------------------- | -------------------------------------- |
| **<!DOCTYPE** **根元素 SYSTEM "DTD文件">**            | 系统DTD文件，通常个人或公司内部使用    |
| **<!DOCTYPE** **根元素 PUBLIC "文件描述" "DTD文件">** | 公有的DTD文件，在互联网上广泛使用的DTD |

**元素声明：**

```dtd
<!ELEMENT 元素名称 类别>
<!ELEMENT 元素名称 (元素内容)>

<!-- 只有 PCDATA 的元素通过圆括号中的 #PCDATA 进行声明： -->
<!ELEMENT test1 (#PCDATA)>
<!-- 空元素声明 -->
<!ELEMENT test2 EMPTY>

<!ELEMENT 书架 (书+)>
<!ELEMENT 书 (书名,作者,售价)>
<!ELEMENT 书名 (#PCDATA)>
<!ELEMENT 作者 (#PCDATA)>
<!ELEMENT 售价 (#PCDATA)>
```

**属性声明：**

```dtd
<!ATTLIST 元素名称 属性名称 属性类型 默认值>

<!ATTLIST payment type CDATA "check">
```

**属性类型：**

| CDATA              | 值为字符数据 (character data) |
| ------------------ | ----------------------------- |
| (*en1*\|*en2*\|..) | 此值是枚举列表中的一个值      |
| ID                 | 值为唯一的 id                 |
| IDREF              | 值为另外一个元素的 id         |
| IDREFS             | 值为其他 id 的列表            |
| NMTOKEN            | 值为合法的 XML 名称           |
| NMTOKENS           | 值为合法的 XML 名称的列表     |
| ENTITY             | 值是一个实体                  |
| ENTITIES           | 值是一个实体列表              |
| NOTATION           | 此值是符号的名称              |
| xml:               | 值是一个预定义的 XML 值       |

**默认值参数可使用下列值：**

| 值           | 解释           |
| :----------- | :------------- |
| 值           | 属性的默认值   |
| #REQUIRED    | 属性值是必需的 |
| #IMPLIED     | 属性不是必需的 |
| #FIXED value | 属性值是固定的 |

**实体：**

```dtd
<!ENTITY 实体名称 "实体的值">

<!ENTITY writer "Bill Gates">
<!ENTITY copyright "Copyright W3School.com.cn">

<author>&writer;&copyright;</author>
```

## Schema约束

- 约束文件本身也是一个XML文件，**本身也会受到其他xsd文件的约束**
- 内置多种数据类型，可以检查数据类型是否正确
- 支持命名空间，一个XML文件可以同时引入多个xsd的约束文件，让约束规则重用
- xsd：**XML Schema Defination**

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<xs:schema xmlns:xs="http://www.w3.org/2001/XMLSchema"
           targetNamespace="http://www.itcast.cn"
          >
    <xs:element name='书架'>
        <xs:complexType>
            <xs:sequence maxOccurs='unbounded'>
                <xs:element name='书'>
                    <xs:complexType>
                        <xs:sequence>
                            <xs:element name='书名' type='xs:string'/>
                            <xs:element name='作者' type='xs:string'/>
                            <xs:element name='售价' type='xs:double'/>
                        </xs:sequence>
                    </xs:complexType>
                </xs:element>
            </xs:sequence>
        </xs:complexType>
    </xs:element>
</xs:schema>
```

## DOM解析

- **优点**：将整个XML文件加载到内存中，生成一棵DOM树。随意访问树上任何一个节点，可以修改和删除节点，程序开发比较方便。纯面向对象开发。
- **缺点**：占内存，如果XML文件很大，可能出现内存溢出。

![1552305195234](https://raw.githubusercontent.com/which-biscuits/pigGo/main/DOM_01.png)

### DOM树

### 生成的DOM树

![1552305350183](https://raw.githubusercontent.com/which-biscuits/pigGo/main/DOM_02.png)

​	由于DOM方式解析XML文档所有都是节点Node，所有节点又都被封装到Document对象中，所以解析的重点就是获取Document对象。

### DOM4j中DOM树的API:

| **组成**      | **说明**                                |
| ------------- | --------------------------------------- |
| **Document**  | 当前解析的XML文档对象                   |
| **Node**      | XML中节点，它是其它所有节点对象的父接口 |
| **Element**   | 代表一个元素(标签)                      |
| **Attribute** | 代表一个属性                            |
| **Text**      | 代表标签中文本                          |

## SAX解析

- **优点**：事件驱动型解析方式，读取一行就解析一行，释放内存。理论上可以解析任意大小的XML文件。
- **缺点**：使用过的元素不能再访问了，不能修改元素，只能查找。

## dom4j

**获取Document对象：**

```java
// 1.创建类: 读取XML文件
SAXReader reader = new SAXReader();
// 2.获取XML输入流
InputStream in = Demo3Document.class.getResourceAsStream("/Contacts.xml");
// 3.通过reader来读取xml, 生成了一个document对象
Document document = reader.read(in);

```

**获取根元素：**

```java
// 得到文档以后，通过文档得到根元素
Element rootElement = document.getRootElement();
```

**Element元素方法：**

```java
// 取元素的名称。
String getName();
// 获取当前元素下的全部子元素（一级）
List<Element> elements();
// 获取当前元素下的指定名称的全部子元素（一级）
List<Element> elements(String name);
// 获取当前元素下的指定名称的某个子元素，默认取第一个（一级）
Element element(String name);

// 可以直接获取当前元素的子元素的文本内容
String elementText(String name);
// 去前后空格,直接获取当前元素的子元素的文本内容
String elementTextTrim(String name);
// 直接获取当前元素的文本内容。
String getText();
// 去前后空格,直接获取当前元素的文本内容。
String getTextTrim();
```

**Attribute对象：**

```java
// 获取元素的全部属性对象。
List<Attribute> attributes();
// 根据名称获取某个元素的属性对象。
Attribute attribute(String name);
// 直接获取某个元素的某个属性名称的值。
String attributeValue(String var1);

// 获取属性名称。
String getName();
// 获取属性值。
String getValue();
```

## Xpath

- 用于快速查找XML元素的路径表达式,是用于方便的检索XML文件中的信息。

**方法：**

```java
// 检索出一批节点集合。
List<Node> selectNodes(String xpath);
// 检索出一个节点返回。
Node selectSingleNode(String xpath);
```

**绝对路径检索：**

```java
String xpath = "/contactList/contact/name";
// 得到所有name元素
List<Node> nodeList = document.selectNodes(xpath);
```

**相对路径检索：**

```java
List<Node> nodeList = node.selectNodes("./contact/name");
```

**全文搜索：**

```java
// 找contact元素，无论元素在哪里
List<Node> nodeList = node.selectNodes("//name");
// 找contact，无论在哪一级，但name一定是contact的子节点
List<Node> nodeList = node.selectNodes("//contact/name");
// contact无论在哪一种，name只要是contact的子孙元素都可以找到
List<Node> nodeList = node.selectNodes("//contact//name");
```

**属性查找：**

```java
// 查找属性对象。无论哪个元素，只有有个属性即可
List<Node> nodeList = node.selectNodes("//@id");
// 查找元素对象，全文搜索指定元素名和属性名
List<Node> nodeList = node.selectNodes("//name[@id]");
// 查找元素对象，全文搜索指定元素名·属性名，并且属性值指定
List<Node> nodeList = node.selectNodes("//name[@id='1']");
```

