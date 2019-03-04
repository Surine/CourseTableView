# CourseTableViewApp - 简洁优雅的加载课程表

> CourseTableView是一个开源的，简洁的，支持多种自定义属性的Android课程表控件，由于开发周期较短，所以目前仅限于实现，后期将会逐步优化性能

> 详细文档请参考[CourseTableView](https://www.yuque.com/hysteria/coursetableview)


###  一 . 实现功能
- 加载课程：最核心的功能
- UI自定义：定制自己想要的课表，色彩，间距，高度，最大节次，显示隐藏周末等等。
- 数据内部处理：只需要传入所有数据，和要加载的条件（如当前周等），CourseTableView将会自己处理
- 可显示当前周几，日期等等(截图是以前的，当时还没做日期)


### 二. 测试图

![测试图](https://cdn.nlark.com/yuque/0/2019/png/276442/1551413899053-d3fbae3e-10d0-43bb-bc4f-ae3da0c9b359.png?x-oss-process=image/resize,w_714)



### 三 . 日志
- V1.0.0 基础实现
- V1.0.1 修复一个bug，增加使用Demo




### 四 . 致谢
感谢以下开源项目：
(可在Github搜索)
- TimetableView  : 通过TimetableView让我了解了语雀这个工具，并且通过此项目让我有了分离配置的想法。
- CourseTable  :  CourseTableView基于本项目核心实现，在加载课程和配置UI方面做了相应优化
其他对应的参考在源码中可以查看




### 五. 关于
- 许可：Apache2.0
- 开发者：Surine
- 博客：https://blog.surine.cn
- 其他类似项目（https://github.com/surine）
  1. 小天盒子 -  课程表实例（课程表控件非本控件，而是使用RecycleView实现的，目前抽离起来比较困难，而且较难自由定制，于是才萌生了要写这个控件的想法）
  2. Sutils - Android开源工具类包，第一个开源的库，水平有限，实现很低级，大神勿喷，




### 六. 展望
- 性能上的优化
- 实现一个课表数据分析库（配合CourseTableView使用，快速搭建一个对应学校的课程表，或者直接集成在CourseTableView中）
- 更多的UI自定义
