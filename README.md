# teachManager
早期个人项目（很烂的项目），开启重构及更新为springboot项目

使用的技术（请自行品尝）：
* spring security
* layui + amazeUI + ajax
* (伪)jenkins


[写在最前,请鼠标悬停查看](#teachManager  "...我发现我20,21,22号的提交，在contributions竟然没有进度，就去百度寻求原因，发现我电脑上提交的邮箱和github不一致（用的是公司gitlab的邮箱）。
如果有喜欢给人生刷点儿绿的小伙伴有幸看到这里，请谨记，在commits里看一下，提交人的名称和头像与你github账号是否一致！！！")

> 决定继续使用amazeUI作为前端前台框架（非常喜欢这个妹子开源框架，作为一个大老爷们，感觉自己也是个小公举啦）
>
> 采用layui作为前端后台框架（这个前端框架真心好用）
>> 决定使用amazeUI的最新版本（2.7.2），翻新重做页面，并补齐手机端的页面效果
>
> 使用thymeleaf渲染html页面（抛弃jsp，可以H5做好页面拉过来直接用，说到底就是懒）
>
> 决定自己画点儿图切图玩啦，再丑也喜欢。。。

------------------- 2019/03/14启封 --------------------

页面基调算是固定了，准备切割成上（导航栏），左（侧边功能栏），右（main content）
但是。。。amazeUI先放下了，换一个单纯的web前端框架吧，响应式框架对于我这个不专业的人来说有点儿难玩（大不了以后再转回来，如果我不懒的话。。。）

对于切图这个，暂时先用FREE Font Awesome代替。

功能目前如下（以后再补充）：
* 首页
  > 用来显示部分个人的各种简要信息，以及信息推送等
* 综合信息
  * 班级信息
  * 考试信息
  * ...
* 教务管理
  * 教务排课
  * 考试安排
  * 教学评价
* 信息管理
  * 用户管理
  * 学生管理
  * 教师管理
  * 课程管理
  * 班级管理
* 日志管理
* 设置
  * 主题切换
  * 修改个人信息
  * 修改密码
  * ...

------------------- 2019/03/15炸了 --------------------

经过两天的尝试，决定采用jquaryUI

------------------- 2019/03/17 -------------------- 

* 使用了jquary的load（）方法，通过div标签的分割，将一个页面分割为三个页面
* 统一了UI风格
* 遗留的问题：
  1. ul标签（FUNCTION）和h1标签（LOGO）没放到一行
  2. 侧边栏显示有问题

------------------- 2019/03/18 一切向好的一面发展 -------------------- 

* 把amazeUI带回来了（真香！），前端框架使用amazeUI + jquaryUI
* 制作左侧功能栏滑动条，使其适合页面风格
* 补充css注释（防止自己看不懂，也给新手能用这个项目来学习的机会）

------------------- 2019/03/19 一切向好的一面发展 -------------------- 

暂时放下前端，学习spring security框架，并运用到项目中（替换掉shiro），搭建ing...

------------------- 2019/03/19 开启后端旅程 -------------------- 

spring security基本搭建成功，但是要注意个坑
当不受权限控制的页面，页面样式及js是在外部文件中时，要注意配置不拦截的静态资源。
具体见commitID:5b3ee4a906a3a5f5bb3cb184ee772f4ddfe042ff

------------------- 2019/04/02 完善spring security中 -------------------- 

看起来蛮长时间没去做这个项目了。。。很长一段时间，都非常忙，经常加班
之后去学习服务器的相关内容，以及研究了一段时间的jenkins
近期比较清闲，于是赶紧回来搞这个项目

说说最近jenkins遇到的坑
在研究jenkins的时候，我就想着，我的项目，在服务器上更新时，每次都需要手动打包，并部署到服务器上
这操作过于繁琐，于是就想着在我的服务器上安装配置jenkins

然后我发现。。。服务器内存过小，jenkins编译项目时，jvm需要的内存太大。。。。我给不起
就算是调整虚拟内存也远远不够
我的服务器 双核1G内存，30G存储空间，平时的剩余内存仅130 - 250mb之间

------------------- 2019/08/01 不用出差真美好 -------------------- 

权限模块基本完成了

之后对权限模块的完善：
  1. 删除的验证
  2. 用户的批量新增
  3. 融入redis
之后要做的内容：
  1. 内容管理（教学咨询等）
  2. 前台展示
  3. 前台，后台与登录系统的分离

------------------- 2019/08/20 即将又要出差了，台湾最近局势紧张，真不想去 -------------------- 
