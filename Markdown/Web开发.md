# HTML

## 标题标签

```HTMl
h1-h6 六级标题    一级标题只能有一个
全部加粗
<h1> </h1>  <h2> </h2>    默认为四级标题

```

## 段落标签

```html
<p> </p>
```

## 列表标签

**有序列表**

```html
<ol type="1,a,A,i,I"(排序方式) start="3"(首序号)>
	<li> 选项1<li>
    <li> 选项2<li>
</ol>
```

**无序列表**

```html
<ul type="disc,cirle,square,none"> 
    <li> 选项1 </li>
    <li> 选项2 </li>
</ul>
```

**定义列表**

```html
<dl>
	<dt> 选项 </dt>
    <dd> 解释项(注释,可多个) </dd>
</dl>
```

## div标签

```html
<div style="width:300px;height:300px;border=20px solid red">
	用于划分区域
</div>
```

## 内联标签

```html
<span>
	宽度只与内容有关(内容撑开高度)
    不会自动换行 从左至右
</span>
```

## 图片标签

```html
<img src="图片的地址(路径)" alt="命名"/>
	相对路径 src="./img/十月.jpg"
	绝对路径 src="C://img/十月.jpg"
```

## 粗体标签

```html
<b> </b>
```

## 斜体标签

```html
<i> </i>
```

## 超链接标签

```html
<a herf="www.baidu.com" title="提示文字" target="_self(本身)"> 我是一个超链接 </a>
```

## 特殊符号

```html
空格    &nbsp;
一个中文字符宽    &emsp;
大于    &gt;
小于    &lt;
等于    &equals;
```

## 表格

```html
<table border="1">    # 属性 边框
    <caption> 标题 </caption>
    <tr>    # 行
    	<th colspan="2"(合并列) rowspan="2"(合并行)>表头(默认加粗)</th>
    </tr>
    <tr>
    	<td>列1</td>
    	<td>列2</td>
    </tr>
</table>
```

## 表单

```html
<form action="页面跳转地址" method="get/post">
    
</form>
```

## **文本框**

```html
<input type="text" placeholder="提示信息"/>
<input type="password"/>
```

## **单选框**

```html
<input type="redio" name="sex"/>
<input type="redio" name="sex" value="famale"/>
```

## **多选框**

```html
<input type="checkbox" name="hobby" value="run" id="man"/>
<label for="male" checked="checked"(默认选中)> 使文字可点</label>
```

## **下拉框**

```html
<select name="address" id="address">
    <optgroup label="中国">
    	<option value=""> 中国 </option>
    </optgroup>
</select>
```

## **中国省市区地址三级联动jQuery插件**

```html
<div data-toggle="distpicker">
  <select data-province="浙江省"></select>
  <select data-city="杭州市"></select>
  <select data-district="西湖区"></select>
</div>
<script src="distpicker.js"></script>
```

## **按钮**

```html
<input type="submit"/>    提交
<input type="reset"/>     重置
<input type="button" value="按钮"/>    普通按钮
<button type="submit"> 登录 </button>
<input type="image" src="www.baidu.com" alt="提交" />    图像提交
<input type="hidden" readonly="readonly" disabled="disabled"/>
<fieldset id="users">    表单分组
    <legend> 标题 </legend>
</fieldset>
```

## **视频**

```html
<video src="路径" controls="controls"(视频控制) width="200px" height="200px"> </video>
```

## **音频**

```html
<audio src="路径" controls="controls"(音频控制)> </audio>
```

## **移动文字**

```html
<marquee behavior="slide,scoll,alternate,right,up,down" scrollamount="10(速度,默认为3)" direction="left"> </marquee>
```

# CSS

# JS
## 表单验证技术
### 正则表达式表单验证
# JQ