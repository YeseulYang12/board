<%@page import="java.sql.Connection"%>
<%@page import="javax.sql.DataSource"%>
<%@page import="javax.naming.Context"%>
<%@page import="javax.naming.InitialContext"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	/*
	커넥션 pool을 이용한 커넥션 객체 사용]
	-톰캣 서버가 커넥션 pool에 미리 생성해 놓은  Connection객체를 갖다 사용하는것		
	*/
	//1]InitialContext객체 생성(Context > InitialContext)
	Context ctx= new InitialContext();
	/*
	 2]InitialContext 객체로 JNDI서비스 구조의  
	   초기 ROOT디렉토리 얻기(예:c드라이브로 접근)
	   lookup해서 각 WAS서버의 서비스 루트 디렉토리를 얻는다 
	   단, 톰캣은 루트 디렉토리명이 java:comp/env이다. 
	 */
	//ctx=(Context)ctx.lookup("java:comp/env");
	 /* 
	  3]context.xml에 등록한 네이밍을 lookup
	    -톰캣의 context.xml에 등록한 네이밍으로 DataSource를 얻는다 
	*/
	//DataSource source=(DataSource)ctx.lookup("jsp");
	//풀 경로로 접근하기  
	DataSource source=(DataSource)ctx.lookup("java:comp/env/kosmo");
	  
  /* 
   	4]커넥션pool에서 톰캣 서버가 생성해 놓은 Connection객체를 갖다 쓰자
    	DataSource의 getConnection()메소드로 
    	톰캣이 pool에 미리 생성해 놓은  Connection객체를 가져다 쓴다.
    */  
	Connection conn= source.getConnection();
   	String connString="";
   	if(conn !=null) connString="<h3>커넥션 객체 가져오기 성공</h3>";
   	else connString="<h3>커넥션 객체 가져오기 실패</h3>";
  	//5]커넥션 풀에 사용한 커넥션 객체 다시 반납(메모리 해제가 아님)
   	if(conn !=null) conn.close();

%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>ConnectionPool.jsp</title>
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
  <script src="https://cdn.jsdelivr.net/npm/jquery@3.6.0/dist/jquery.slim.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js"></script>
  <style>
  	/*점보트론 폭 줄이기*/
    .jumbotron{
      padding-top:1rem;
      padding-bottom:1rem;
      margin-bottom: .5rem;
    }
    
  </style>
</head>
<body>
  <div class="container">
    <div class="jumbotron bg-info">
      <h1>커넥션 풀</h1>     
    </div>   
   	<%=connString %>
    
  </div>
</body>
</html>