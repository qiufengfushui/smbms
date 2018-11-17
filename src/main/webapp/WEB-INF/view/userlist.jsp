<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="common/head.jsp"%>
<%
	if (session.getAttribute("userList")==null){
	    response.sendRedirect("/smbms/user/management.do");
	}
%>

        <div class="right">
            <div class="location">
                <strong>你现在所在的位置是:</strong>
                <span>用户管理页面</span>
            </div>
            <div class="search">
           		<form method="get" action="${pageContext.request.contextPath }/user/management.do">
					 <span>用户名：</span>
					 <input name="queryname" class="input-text"	type="text" value="${queryUserName}">

					 <span>用户角色：</span>
					 <select name="queryUserRole">
						    <option value="0"
								<c:if test="${queryUserRole == '0'}">
									selected
								</c:if>
							>--请选择--</option>
							<option value="1"
									<c:if test="${queryUserRole == '1'}">
										selected
									</c:if>
							>系统管理员</option>
							<option value="2"
									<c:if test="${queryUserRole == '2'}">
										selected
									</c:if>
							>经理</option>
							<option value="3"
									<c:if test="${queryUserRole == '3'}">
										selected
									</c:if>
							>普通员工</option>
	        		</select>
					 <input type="hidden" name="pageIndex" value="1"/>
					 <input	value="查 询" type="submit" id="searchbutton">
					 <a href="${pageContext.request.contextPath}/user/useradd.do" >添加用户</a>
				</form>
            </div>
            <!--用户-->
            <table class="providerTable" cellpadding="0" cellspacing="0">
                <tr class="firstTr">
                    <th width="10%">用户编码</th>
                    <th width="20%">用户名称</th>
                    <th width="10%">性别</th>
                    <th width="10%">年龄</th>
                    <th width="10%">电话</th>
                    <th width="10%">用户角色</th>
                    <th width="30%">操作</th>
                </tr>
                   <c:forEach var="user" items="${userList }" varStatus="status">
					<tr>
						<td>
						<span>${user.userCode }</span>
						</td>
						<td>
						<span>${user.userName }</span>
						</td>
						<td>
							<span>
								<c:out value="${user.userGender}"></c:out>
							</span>
						</td>
						<td>
						<span>${user.age}</span>
						</td>
						<td>
						<span>${user.phone}</span>
						</td>
						<td>
							<span>${user.role.roleName}</span>
						</td>
						<td>
						<span><a class="viewUser" href="javascript:;" userid=${user.id } username=${user.userName }><img src="${pageContext.request.contextPath }/static/images/read.png" alt="查看" title="查看"/></a></span>
						<span><a class="modifyUser" href="javascript:;" userid=${user.id } username=${user.userName }><img src="${pageContext.request.contextPath }/static/images/xiugai.png" alt="修改" title="修改"/></a></span>
						<span><a class="deleteUser" href="javascript:;" userid=${user.id } username=${user.userName }><img src="${pageContext.request.contextPath }/static/images/schu.png" alt="删除" title="删除"/></a></span>
						</td>
					</tr>
				</c:forEach>
			</table>
			<input type="hidden" id="totalPageCount" value="${pageBean.totalPage}"/>
		  	<c:import url="rollpage.jsp">
	          	<c:param name="totalCount" value="${pageBean.totalRecord}"/>
	          	<c:param name="currentPageNo" value="${pageBean.pageNum}"/>
	          	<c:param name="totalPageCount" value="${pageBean.totalPage}"/>
          	</c:import>
        </div>
    </section>

<!--点击删除按钮后弹出的页面-->
<div class="zhezhao"></div>
<div class="remove" id="removeUse">
    <div class="removerChid">
        <h2>提示</h2>
        <div class="removeMain">
            <p>你确定要删除该用户吗？</p>
            <a href="#" id="yes">确定</a>
            <a href="#" id="no">取消</a>
        </div>
    </div>
</div>
<%
	session.removeAttribute("userList");
	session.removeAttribute("pageBean");
	session.removeAttribute("queryUserName");
	session.removeAttribute("queryUserRole");
%>
<%@include file="common/foot.jsp" %>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/userlist.js"></script>
