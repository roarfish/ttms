<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>  
<!-- Left side column. contains the logo and sidebar -->
  <aside class="main-sidebar">
    <!-- sidebar: style can be found in sidebar.less -->
    <section class="sidebar">
      <!-- /.search form -->
      <!-- sidebar menu: : style can be found in sidebar.less -->
      <ul class="sidebar-menu">
	      <shiro:hasPermission name="product:list">
	      	<li class="active treeview">
	          <a href="#">
	            <i class="fa fa-dashboard"></i> <span>产品管理</span>
	            <span class="pull-right-container">
	              <i class="fa fa-angle-left pull-right"></i>
	            </span>
	          </a>
	          <ul class="treeview-menu">
	          	<shiro:hasPermission name="product:project:view">
	          		<li><a id="load-project-id"><i class="fa fa-circle-o"></i>项目管理</a></li>
	          	</shiro:hasPermission>
	          	<shiro:hasPermission name="team:team:view">
	          		<li><a id="load-team-id"><i class="fa fa-circle-o"></i>团目管理</a></li>
	          	</shiro:hasPermission>
	          	<shiro:hasPermission name="product:product:view">
	          		<li><a id="load-type-id"><i class="fa fa-circle-o"></i>产品分类</a></li>
	          	</shiro:hasPermission>
	          	<shiro:hasPermission name="product:type:view">
	          		<li><a href="#"><i class="fa fa-circle-o"></i>产品管理</a></li>
	          	</shiro:hasPermission>
	            <shiro:hasPermission name="attachment:view">
	            	<li><a id="load-attachment-id"><i class="fa fa-circle-o"></i>附件管理</a></li>
	            </shiro:hasPermission>
	          </ul>
	        </li>
	      </shiro:hasPermission>
        <shiro:hasPermission name="sys:list">
        	<li class="treeview">
	          <a href="#">
	            <i class="fa fa-files-o"></i>
	            <span>系统管理</span>
	            <span class="pull-right-container">
	              <i class="fa fa-angle-left pull-right"></i>
	            </span>
	          </a>
	          <ul class="treeview-menu">
	          	<shiro:hasPermission name="sys:role:view">
	          		<li><a id="load-role-id"><i class="fa fa-circle-o"></i>角色管理</a></li>
	          	</shiro:hasPermission>
	          	<shiro:hasPermission name="sys:menu:view">
	          		<li><a id="load-menu-id"><i class="fa fa-circle-o"></i>菜单管理</a></li>
	          	</shiro:hasPermission>
	          	<shiro:hasPermission name="sys:user:view">
	          		<li><a id="load-user-id"><i class="fa fa-circle-o"></i>用户管理</a></li>
	          	</shiro:hasPermission>
	          </ul>
        	</li>
        </shiro:hasPermission>
        
        <li class="treeview">
	          <a href="#">
	            <i class="fa fa-files-o"></i>
	            <span>会话管理</span>
	            <span class="pull-right-container">
	              <i class="fa fa-angle-left pull-right"></i>
	            </span>
	          </a>
	          <ul class="treeview-menu">
	          	<li><a id="load-session-online"><i class="fa fa-circle-o"></i>在线用户</a></li>
	          </ul>
        	</li>
      </ul>
    </section>
    <!-- /.sidebar -->
  </aside>
<script type="text/javascript">
$("#load-session-online").click(function(){
	var url="session/listUI.do?t="+Math.random(1000);
	$(".content").load(url);
})
$('#load-project-id').click(function(){
	var url="project/listUI.do?t="+Math.random(1000);
	$(".content").load(url);
})
$('#load-team-id').click(function(){
	var url="team/listUI.do?t="+Math.random(1000);
	$(".content").load(url);
})
$('#load-type-id').click(function(){
	var url="type/listUI.do?t="+Math.random(1000);
	$(".content").load(url);
})
$('#load-product-id').click(function(){
	var url="product/listUI.do?t="+Math.random(1000);
	$(".content").load(url);
})
$('#load-attachment-id').click(function(){
	var url="attachment/attachmentUI.do?t="+Math.random(1000);
	$(".content").load(url);
})
$('#load-role-id').click(function(){
	var url="role/listUI.do?t="+Math.random(1000);
	$(".content").load(url);
})
$('#load-menu-id').click(function(){
	var url="menu/listUI.do?t="+Math.random(1000);
	$(".content").load(url);
})
$('#load-user-id').click(function(){
	var url="user/listUI.do?t="+Math.random(1000);
	$(".content").load(url);
})
</script>