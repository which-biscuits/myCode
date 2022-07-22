# 图形用户界面

## 框架

### 创建框架

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

### 添加组件

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

## 事件处理

### 事件和事件源

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

### 事件监听器

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



## 监听接口适配器

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

## 布局管理器

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

### BorderLayout

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



### FlowLayout

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

### GridLayout

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

### 容器

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

## 文本组件

Swing组件中, 具有用户输入和编辑文本功能的常用组件只有 **文本域 : JTextField / 文本区 : JTextArea / 密码域 : JPassword**

```java
Swing文本组件都继承于抽象类 javax.swing.text.JTextComponent:

public String getText();	// 返回当前文本组件中包含的文本
public void setText(String t);	// 将当前文本组件中的文本设定为指定文本
public boolean isEditable();	// 返回当前文本组件是否可编辑
public void setEditable(boolean b);	// 将当前文本组件设置为 可编辑 / 不可编辑
```

### 文本域

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

### 文本区

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

### 密码域

密码域是一种特殊的文本域,它也允许用户编辑单行文本

用户输入的每个字符都会用回显字符(默认情况下,回显字符一般是"*")表示

创建密码域需要使用类 **Javax.swing.JPasswardField**

```java
// 获取用户输入
public char[] getPassword();
// 设置回显字符
public void setEchoChar(char c);
```

## 面板

用来容纳组件的容器, 面板不能独立存在, 只能添加到其他容器中, 面板也可以添加到其他面板中

**面板是JPanel 的对象** 面板的默认布局是 **FlowLayout** 可直接容纳组件

```java
JPanel northPanel = new JPanel();	// 创建JPanel 对象
northPanel.setLayout(new FlowLayout());	// 设置布局管理器
northPanel.add(textField);	// 添加组件
northPanel.add(passwordField);
add(northPanel, BorderLayout.NORTH);	// 将其加入到页面中
```

## 选择组件

Swing组件中, 常用的选择组件有 按钮 JButton / 复选框 JCheckBox / 单选按钮 JRadioButton / 组合框 JComboBox

 / 列表 JLIst / 滑块 JSlider 等

### 按钮

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

### 标签

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

### 复选框

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

### 单选按钮

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

### 边框

为组件设置边框, 可用于区分不同的单选框组

```java
public void setBorder(Border border);

eg :
Border etched = BorderFactory.createEtchedBorder();	// 创建一个蚀刻边框
Border titled = BorderFactory.createTitledBorder(etched, "标题");
```

### 组合框

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

### 列表

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