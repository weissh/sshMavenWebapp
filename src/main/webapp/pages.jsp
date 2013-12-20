<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="org.apache.commons.lang3.StringUtils"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%String basePath = request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort()+ request.getContextPath() + "/";%>
<%
	String parameter = request.getParameter("parameter");if(StringUtils.isBlank(parameter)){parameter="webconsole=0";}
	String method = request.getParameter("method");
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<body>
${cname}
${cteacher}
<h2>欢迎！</h2>
 <s:debug></s:debug>
 ${pageBean.list.size()}
 right<table>
  <s:property value="pageBean.pageSize"/>
<div id="page">
	<s:iterator value="pageBean.list" var="list">
<tr>
<td style="border-bottom: #CCC dashed 2px;" height="30">
${list.unicode}
</td>
<td style="border-bottom: #CCC dashed 2px;">
${list.cno}
</td>
<td style="border-bottom: #CCC dashed 2px;">
${list.cname}
</td>
<td colspan="2" style="border-bottom: #CCC dashed 2px;">
${list.cteacher}
</td>

</tr>
</s:iterator>
	</table>
	<s:if test="#request.pageBean!=null">
		<% //首页%>
		<s:if test="#request.pageBean.currentPage!=1">
			<a style="width: 50px;" href="javascript:turnToUrl(1);">首   页</a>
		</s:if>
		<% //如果当前页数不等于第一页,则显示上一页%>
		<s:if test="#request.pageBean.currentPage!=1">
			<a style="width: 50px;" href="javascript:turnToUrl(<s:property value="#request.pageBean.currentPage-1"/>);">上一页</a>
		</s:if>
		<% //如果总页数小于10也%>
		<s:if test="#request.pageBean.totalPage<10">
			<s:iterator value="new int[#request.pageBean.totalPage]" status="a">
				<s:if test="#request.pageBean.currentPage==#a.index+1">
					<label class="on"><s:property value="#a.index+1"/></label>
				</s:if>
				<s:else>
					<a href="javascript:turnToUrl(<s:property value="#a.index+1"/>);"><s:property value="#a.index+1"/></a>
				</s:else>
			</s:iterator>
		</s:if>
		<s:else>
			<% //如果当前页数等于末页%>
			<s:if test="#request.pageBean.totalPage==#request.pageBean.currentPage">
				<s:iterator value="new int[#request.pageBean.totalPage]" status="a">
					<s:if test="#a.index+1==#request.pageBean.currentPage">
						<label class="on"><s:property value="#a.index+1"/></label>
					</s:if>
					<s:else>
						<s:if test="#a.index+(#request.pageBean.currentPage-9)<#request.pageBean.totalPage">
							<a href="javascript:turnToUrl(<s:property value="#a.index+(#request.pageBean.totalPage-9)"/>);"><s:property value="#a.index+(#request.pageBean.totalPage-9)"/></a>
						</s:if>
					</s:else>
				</s:iterator>
			</s:if>
			<% //如果当前页数小于等于9%>
			<s:elseif test="#request.pageBean.currentPage<=9">
				<s:iterator value="new int[10]" status="a">
					<s:if test="#request.pageBean.currentPage==#a.index+1">
						<label class="on"><s:property value="#a.index+1"/></label>
					</s:if>
					<s:else>
						<a href="javascript:turnToUrl(<s:property value="#a.index+1"/>);"><s:property value="#a.index+1"/></a>
					</s:else>
				</s:iterator>
			</s:elseif>
			<% //如果当前页数减去9大于1并且加1小于末页%>
			<s:elseif test="#request.pageBean.currentPage-9>1 && #request.pageBean.currentPage+1<=#request.pageBean.totalPage">
				<s:iterator value="new int[#request.pageBean.currentPage]" status="a">
					<s:if test="#a.index+(#request.pageBean.currentPage-9)<=#request.pageBean.currentPage+1">
						<s:if test="#a.index+(#request.pageBean.currentPage-9)==#request.pageBean.currentPage">
							<label class="on"><s:property value="#request.pageBean.currentPage"/></label>
						</s:if>
						<s:else>
							<a href="javascript:turnToUrl(<s:property value="#a.index+(#request.pageBean.currentPage-9)"/>);"><s:property value="#a.index+(#request.pageBean.currentPage-9)"/></a>
						</s:else>
					</s:if>
				</s:iterator>
			</s:elseif>
			<% //如果当前页数等于第10%>
			<s:elseif test="#request.pageBean.currentPage==10">
				<s:iterator value="new int[11]" status="a">
					<s:if test="#a.index+1==10">
						<label><s:property value="#a.index+1"/></label>
					</s:if>
					<s:else>
						<a href="javascript:turnToUrl(<s:property value="#a.index+1"/>);"><s:property value="#a.index+1"/></a>
					</s:else>
				</s:iterator>
			</s:elseif>
			<% //如果当前页数不等于末页,则显示下一页%>
			<s:if test="#request.pageBean.currentPage!=#request.pageBean.totalPage">
				<a style="width: 50px;" href="javascript:turnToUrl(<s:property value="#request.pageBean.currentPage+1"/>);">下一页</a>
			</s:if>
			<% //尾页%>
			<s:if test="#request.pageBean.currentPage!=#request.pageBean.totalPage">
				<a style="width: 50px;" href="javascript:turnToUrl(<s:property value='#request.pageBean.totalPage'/>);">尾    页</a>
			</s:if>
		</s:else>
	</s:if>
	<script charset="utf-8" type="text/javascript" language="javascript">
		function turnToUrl(pagenum){
			if(pagenum!=0 && pagenum!=undefined){
				window.location.href="<%=basePath%><%=method%>?<%=parameter%>&pagenum="+pagenum;
			}
		}
	</script>
</div>
</body>
</html>


