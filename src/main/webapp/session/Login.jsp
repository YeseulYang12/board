<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<jsp:include page="/template/Top.jsp"/>  
 	<div class="container" style="margin-top:65px;">

    <div class="jumbotron bg-warning">
      <h1>로그인</h1>      
    </div>
    <span class="font-weight-bold text-danger"><%=request.getAttribute("NOT-LOGIN")==null?"":request.getAttribute("NOT-LOGIN") %></span>
	<form class="form-inline" action="<%=request.getContextPath() %>/session/LoginProcess.jsp" method="POST">
		<label>아이디</label> 
		<input type="text" name="id" class="form-control mx-2" value="<%=request.getParameter("id")==null?"":request.getParameter("id") %>" /> 
		<label>비밀번호</label> 
		<input type="password" name="pwd" class="form-control mx-2" value="<%=request.getParameter("pwd")==null?"":request.getParameter("pwd") %>" /> 
		<input type="submit" class="btn btn-danger mx-2" value="로그인" />
	</form>
</div>
<jsp:include page="/template/Footer.jsp"/>	
  