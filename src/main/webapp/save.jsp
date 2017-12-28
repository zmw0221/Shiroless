<%@ page contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
  <head>
    <title>全能之书</title>
	
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="this is my page">
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" type="text/css" href="themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="themes/icon.css">
	<script type="text/javascript" src="jquery.min.js"></script>
	<script type="text/javascript" src="jquery.easyui.min.js"></script>
   <script type="text/javascript">
   		//添加tabs
   		function urlClick(mytitle,myUrl){
   			//判断是否存在tabs
   			var isExist= $("#tb").tabs("exists",mytitle);
   			if(!isExist){
   				$("#tb").tabs("add",{
   					title:mytitle,
   					closable:true,
   					content:'<iframe frameborder=0 width="100%" height="100%" scrolling="no" src="'+myUrl+'"></iframe>'
   				})
   			}
   			$("#tb").tabs("select",mytitle);
   		}
   		
   </script>

  </head>
  
  <body style="padding:1px;margin:1px">
  	
  	<div class="easyui-layout" style="width:100%;height:100%;">
  		<!-- 上北只能设置高度 一般不会设置宽度 -->
		<div data-options="region:'north'" style="height:15%"></div>
		<div data-options="region:'west',split:true" title="导航菜单" style="width:17%;">
			<!-- 添加菜单栏 -->
			<div class="easyui-accordion" style="width:95%;height:100%;">
				<div title="权限管理"  style="overflow:auto;padding:10%;">
				<c:forEach var="menu" items="${requestScope.menuList}">
					<a href="javascript:urlClick('${menu.menuname}','${pageContext.request.contextPath}${menu.menuurl}')" style="text-decoration: none "><img src="themes/icons/man.png"/>${menu.menuname}</a>
				</c:forEach>
				</div>
				<div title="系统管理" style="padding:10%;">
					
				</div>
			</div>	
		</div>
		<div data-options="region:'center',iconCls:'icon-ok'">
			<!-- 添加内容 -->
			<div id="tb" class="easyui-tabs" style="width:100%;height:100%">
				<div title="欢迎使用" style="padding:10px ">
				<iframe frameborder=0 width="100%" height="100%" scrolling="no" src="Lg.html"></iframe>
				</div>
				
			</div>	
		</div>
	</div>
  
  </body>
</html>
