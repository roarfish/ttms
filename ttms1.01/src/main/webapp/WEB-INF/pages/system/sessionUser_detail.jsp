<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri= "http://java.sun.com/jsp/jstl/core" prefix= "c" %> 
<%@ page language="java" import="java.util.*,cn.tedu.ttms.*" %>
<%@page import="cn.tedu.ttms.system.entity.*"%>
<c:set var="basePath" value="${pageContext.request.contextPath}"></c:set>
<%-- <script type="text/javascript" src="${basePath}/ttms/system/sessionUser_detail.js"></script> --%>
<%
	SysUserOnline user=(SysUserOnline)request.getAttribute("onlineUser");
%>
<div class="container">
	<!-- 页面导航 -->
    <div class="page-header">
		<div class="page-title" style="padding-bottom: 5px">
			<ol class="breadcrumb">
			  <li class="active" id="titleId">Session详情</li>
			</ol>
		</div>
	</div>
	<form method="post" id="sessionFormHead">
		<!-- 列表显示内容 -->
		<div class="row col-md-12">
			<table class="table table-bordered" id="userTable">
				<tbody id="tbody">
					<tr>
						<th style="width:300px;">Session Id</th>
						<td><%=user.getSessionId() %></td>
					</tr>
					<tr>
						<th style="width:300px;">Session 创建时间</th>
						<td><%=user.getStartTime() %></td>
					</tr>
					<tr>
						<th style="width:300px;">Session 最后交互时间</th>
						<td><%=user.getLastAccess() %></td>
					</tr>
					<tr>
						<th style="width:300px;">Session 状态</th>
						<td><%=user.getValid() == 1 ? "有效" : "无效" %></td>
					</tr>
					<tr>
						<th style="width:300px;">Session Host</th>
						<td><%=user.getHost() %></td>
					</tr><tr>
						<th style="width:300px;">Session Timeout</th>
						<td><%=user.getTimeout() %></td>
					</tr>
					<tr>
						<th style="width:300px;">昵称</th>
						<td><%=user.getUsername() %></td>
					</tr>
					<tr>
						<th style="width:300px;">Email/帐号</th>
						<td><%=user.getEmail() %></td>
					</tr>
					<tr>
						<th style="width:300px;">创建时间</th>
						<td><%=user.getCreatedTime() %></td>
					</tr>
					<tr>
						<th style="width:300px;">最后登录时间</th>
						<td><%=user.getLastLoginTime() %></td>
					</tr>
				</tbody>
			</table>
		</div>
	</form>
</div>