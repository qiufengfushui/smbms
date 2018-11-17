<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="common/head.jsp"%>
<%
	if (session.getAttribute("billList")==null){
	    response.sendRedirect("/smbms/bill/management.do");
	}
%>
<div class="right">
       <div class="location">
           <strong>你现在所在的位置是:</strong>
           <span>订单管理页面</span>
       </div>
       <div class="search">
       <form method="get" action="${pageContext.request.contextPath }/bill/management.do">
			<span>商品名称：</span>
			<input name="queryProductName" type="text" value="${queryProductName == null?'':queryProductName}">
			<span>供应商：</span>
		   <input type="hidden" id="proId" value="${proId}">
			<select name="queryProviderId" id="provider">

       		</select>
			 
			<span>是否付款：</span>
			<select name="queryIsPayment">
				<option value="">--请选择--</option>
				<option value="未付款" ${queryIsPayment == '未付款' ? "selected=\"selected\"":"" }>未付款</option>
				<option value="已付款" ${queryIsPayment == '已付款' ? "selected=\"selected\"":"" }>已付款</option>
       		</select>
		   <input type="hidden" name="pageIndex" value="1"/>
			 <input	value="查 询" type="submit" id="searchbutton">
			 <a href="${pageContext.request.contextPath}/bill/billadd.do">添加订单</a>
		</form>
       </div>
       <!--账单表格 样式和供应商公用-->
      <table class="providerTable" cellpadding="0" cellspacing="0">
          <tr class="firstTr">
              <th width="10%">订单编码</th>
              <th width="20%">商品名称</th>
              <th width="10%">供应商</th>
              <th width="10%">订单金额</th>
              <th width="10%">是否付款</th>
              <th width="10%">创建时间</th>
              <th width="30%">操作</th>
          </tr>
          <c:forEach var="bill" items="${billList }" varStatus="status">
				<tr>
					<td>
					<span>${bill.billCode }</span>
					</td>
					<td>
					<span>${bill.productName }</span>
					</td>
					<td>
					<span>${bill.provider.proName}</span>
					</td>
					<td>
					<span>${bill.totalPrice}</span>
					</td>
					<td>
					<span>
						<c:out value="${bill.isPayment}"></c:out>
					</span>
					</td>
					<td>
					<span>
						<c:out value="${bill.creationDate}"></c:out>
					</span>
					</td>
					<td>
					<span><a class="viewBill" href="javascript:;" billid=${bill.id } billcc=${bill.billCode }><img src="${pageContext.request.contextPath }/images/read.png" alt="查看" title="查看"/></a></span>
					<span><a class="modifyBill" href="javascript:;" billid=${bill.id } billcc=${bill.billCode }><img src="${pageContext.request.contextPath }/images/xiugai.png" alt="修改" title="修改"/></a></span>
					<span><a class="deleteBill" href="javascript:;" billid=${bill.id } billcc=${bill.billCode }><img src="${pageContext.request.contextPath }/images/schu.png" alt="删除" title="删除" onclick="deleteBill(this)"/></a></span>
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
<div class="remove" id="removeBi">
    <div class="removerChid">
        <h2>提示</h2>
        <div class="removeMain">
            <p>你确定要删除该订单吗？</p>
            <a href="#" id="yes">确定</a>
            <a href="#" id="no">取消</a>
        </div>
    </div>
</div>
<%
	session.removeAttribute("billList");
	session.removeAttribute("pageBean");
	session.removeAttribute("queryIsPayment");
	session.removeAttribute("queryProductName");
	session.removeAttribute("proId");
%>
<%@include file="common/foot.jsp" %>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/billlist.js"></script>