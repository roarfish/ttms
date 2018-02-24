<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri= "http://java.sun.com/jsp/jstl/core" prefix= "c" %> 
<c:set var="basePath" value="${pageContext.request.contextPath}"></c:set>
<script type="text/javascript" src="${basePath}/ttms/system/sessionUser_list.js"></script>
<div class="container">
	<!-- 页面导航 -->
    <div class="page-header">
		<div class="page-title" style="padding-bottom: 5px">
			<ol class="breadcrumb">
			  <li class="active" id="titleId">当前在线用户</li>
			</ol>
		</div>
	</div>
	<form method="post" id="sessionFormHead">
		<!-- 列表显示内容 -->
		<div class="row col-md-12">
			<table class="table table-bordered" id="userTable">
				<thead>
					<tr>
					    <th>SessionID</th>
						<th>昵称</th>
						<th>邮箱</th>
						<th>创建会话时间</th>
						<th>上次会话结束时间</th>
						<th>状态</th>
						<th>操作</th>
					</tr>
				</thead>
				<tbody id="tbody"></tbody>
			</table>
		</div>
	</form>
</div>