# JavaWeb项目之新生管理系统（Servlet+JSP+JavaBean）



## 项目介绍


### 功能介绍



-登录，登出和登录验证

-新生报到：


     新生录取信息管理：条件搜索新生，搜索结果以列表的形式给出，可以对列表中的客户信息进行编辑和删除，导入新生信息
 
     班级分配：修改新生班级
 
     专业调整：修改新生系别和专业
 
     财务缴费：条件搜索新生缴费情况，搜过结果以列表的形式给出
 
-宿舍管理：条件搜索宿舍，搜索结果以列表的形式给出，管理宿舍人员（查看人员，增加人员，删除人员)

-班级管理：条件搜索班级，搜索结果以列表的形式给出，管理班级人员（查看人员，增加人员，删除人员）

-成绩管理：条件搜索成绩，搜索结果以列表的形式给出

-用户管理：管理员修改密码

### 运用知识

-数据库基本查询

-数据库连接池c3p0

-运用dbutil.jar包一键封装表单数据到bean对象中，简化对数据库增、删、改、查的代码

-运用fileupload.jar包上传文件

-运用poi.jar包解析EXCEL文件

-AJAX异步更新

-运用json-lib.jar包将数据转化为JSON格式

-标签库JSTL

-JavaWeb三层框架的分离

## 准备


1.导入第三方包，c3p0配置文件和db.proerties数据库配置文件

2、建包

-dao,dao.impl

-service,service.impl

-servlet.controller,servle.filter,servle.UI

-domain

## 搭建环境


IntelliJ IDEA +MySQL
