<%@page import="java.net.URLDecoder"%>
<%@page import="java.net.URLEncoder"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@page import="model.BBSDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- Delete.jsp -->

<%
	//파라미터 받기
	String no = request.getParameter("no");	
	//현재 페이지번호 받기
	int nowPage = Integer.parseInt(request.getParameter("nowPage"));
	/*
	searchWord를 한글로 검색후 수정시/삭제시 주의 사항
	※sendRedirect()메소드로 쿼리스트링으로 한글 전달시 한글 깨짐
	해결책:
	1.검색어를  URLEncoder.encode()
	  단,검색후 총 레코드 수 구할때는 인코딩하지 않는 검색어 전달
	2.수정완료시 뷰 혹은 삭제 완료시 목록으로 이동시 뷰(View.jsp)나 목록(List.jsp)에서는 URLDecoder.decode()해야 한다
	  예]
	  Delete.jsp :URLEncoder.encode(searchWord, "UTF-8")
	  List.jsp : URLDecoder.decode(searchWord, "UTF-8")
	단,form태그로 이동하거나 loation으로 이동시에는  URL 인코딩 및 디코딩 불필요
	*/
	
	//검색과 관련된 파라미터 받기
	String searchColumn =request.getParameter("searchColumn");
	String searchWord = request.getParameter("searchWord");	
	String searchString="";//검색용 쿼리 스트링	
	Map map = new HashMap(); 
	if(searchWord !=null && searchWord.length()!=0){
		map.put("searchWord",searchWord);
		map.put("searchColumn",searchColumn);
		
		//검색시 생성된 페이징 번호 클릭시 처리하기 위한 추가 쿼리스트링
		searchString=String.format("searchColumn=%s&searchWord=%s&", searchColumn,searchWord);
	}
	
	
	//CRUD작업용 DAO계열 객체 생성
	BBSDao dao = new BBSDao(application);
	int affected=dao.delete(no);
	//마지막 레코드 삭제시 페이지가 하나 줄어드는 경우 코딩 시작
	int totalRecordCount = dao.getTotalRecordCount(map);
	int pageSize=Integer.parseInt(application.getInitParameter("PAGE-SIZE"));
	int totalPage = (int)Math.ceil((double)totalRecordCount/pageSize);
	if(totalPage < nowPage) nowPage = totalPage;
	//마지막 레코드 삭제시 페이지가 하나 줄어드는 경우 코딩 끝
	
	
	dao.close();
	if(affected ==1){
		//아래는 한글이 깨짐
		//response.sendRedirect("List.jsp?nowPage="+nowPage+"&"+searchString);
		//한글이 깨지지 않는다
		out.println("<script>");
		out.println(String.format("location.replace('List.jsp?nowPage=%s&%s')",nowPage,searchString));		
		out.println("</script>");
	}
	else{
		out.println("<script>");
		out.println("alert('삭제 실패했어요');");
		out.println("history.back();");
		out.println("</script>");
	}
%>
