package model;

import java.util.Map;

import jakarta.servlet.http.HttpServletRequest;
import service.DaoService;

public class PagingUtil {

	public static String pagingText(int totalRecordCount,int pageSize,int blockPage,int nowPage,String page){
		String pagingStr="";
		
		//1.전체 페이지 구하기
		int totalPage= (int)(Math.ceil(((double)totalRecordCount/pageSize)));
		
		int intTemp = ((nowPage - 1) / blockPage) * blockPage + 1;

		//처음 및 이전을 위한 로직
		if(intTemp != 1){
                
			pagingStr+="<a class=\"page-link\" href='"+page+"nowPage=1'>◀◀</a>&nbsp;<a class=\"page-link\" href='"+page+"nowPage="+(intTemp -blockPage)+"'>◀</a>";
		}
		
		//페이지 표시 제어를 위한 변수
		int blockCount = 1;
		
		//페이지를 뿌려주는 로직
		//블락 페이지 수만큼 혹은 마지막 페이지가 될때까지 페이지를 표시한다1 
		while(blockCount <= blockPage && intTemp <= totalPage){  // 페이지 오버 를 체크
				//현재 페이지를 의미함
			if(intTemp == nowPage){  
				pagingStr+="<span class=\"page-link text-danger\" >"+intTemp+"</span>&nbsp;";
			}
		     else
		    	 pagingStr+="<a class=\"page-link\" href='"+page+"nowPage="+intTemp+"'>"+intTemp+"</a>&nbsp;";
		       
			intTemp = intTemp + 1;
			blockCount = blockCount + 1;
		
		}

		//다음 및 마지막을 위한 로직
			
		if(intTemp <= totalPage){

			pagingStr+="<a class=\"page-link\" href='"+page+"nowPage="+intTemp+"'>▶</a>&nbsp;<a class=\"page-link\" href='"+page+"nowPage="+totalPage+"'>▶▶</a>"; 

				   
		}
		
		return pagingStr;
	}
	public static String pagingBootStrapStyle(int totalRecordCount,int pageSize,int blockPage,int nowPage,String page){
		
		String pagingStr="<ul class=\"pagination justify-content-center\">";
		
		//1.전체 페이지 구하기
		int totalPage= (int)(Math.ceil(((double)totalRecordCount/pageSize)));
		
		int intTemp = ((nowPage - 1) / blockPage) * blockPage + 1;

		//처음 및 이전을 위한 로직
		if(intTemp != 1){
			pagingStr+="<li>\r\n" + 
					"<a  class=\"page-link\" href='"+page+"nowPage=1'>\r\n" + 
					"<span >&laquo;</span>\r\n" + 
					"</a>\r\n" + 
					"</li>\r\n" + 
					"<li >\r\n" + 
					"<a class=\"page-link\" href='"+page+"nowPage="+(intTemp -blockPage)+"' >\r\n" + 
					"<span>&lsaquo;</span>\r\n" + 
					"</a>\r\n" + 
					"</li>";   
			
		}
		
		//페이지 표시 제어를 위한 변수
		int blockCount = 1;
		
		//페이지를 뿌려주는 로직
		//블락 페이지 수만큼 혹은 마지막 페이지가 될때까지 페이지를 표시한다1 
		while(blockCount <= blockPage && intTemp <= totalPage){  // 페이지 오버 를 체크
				//현재 페이지를 의미함
			if(intTemp == nowPage){  
				pagingStr+="<li class=\"page-item\"><a class=\"page-link\" href='#'><span class=\"text-danger\">"+intTemp+"</span></a></li>";
			}
		     else
		    	 pagingStr+="<li class=\"page-item\"><a class=\"page-link\" href='"+page+"nowPage="+intTemp+"'>"+intTemp+"</a></li>";
		       
			intTemp = intTemp + 1;
			blockCount = blockCount + 1;
		
		}

		//다음 및 마지막을 위한 로직
			
		if(intTemp <= totalPage){
			pagingStr+="<li>\r\n" + 
					"<a class=\"page-link\" href='"+page+"nowPage="+intTemp+"'>\r\n" + 
					"<span>&rsaquo;</span>\r\n" + 
					"</a>\r\n" + 
					"</li>\r\n" + 
					"<li class=\"page-item\">\r\n" + 
					"<a class=\"page-link\" href='"+page+"nowPage="+totalPage+"' >\r\n" + 
					"<span >&raquo;</span>\r\n" + 
					"</a>\r\n" + 
					"</li>";
							   
		}
		pagingStr+="</ul>";
		return pagingStr;
	}//////////////////////////////////////
	//페이징시 필요 속성(키)를 저장한는 맵 설정용 메소드
	//Map map:필요한 키와 값을 담을 비어있는 맵
	//T dao :  DAO계열을 받는다
	//제너릭 메소드로 구현:DAOService 를 상속받은 모든 DAO계열을 인자로 받기위해서-중복 코딩 방지
	public static <T extends DaoService> void setMapForPaging(Map map,T dao,HttpServletRequest request) {
		//페이징을 위한 로직 시작
		//전체 레코드수
		int totalRecordCount = dao.getTotalRecordCount(map);
		//페이지사이즈
		int pageSize = Integer.parseInt(request.getServletContext().getInitParameter("PAGE-SIZE"));
		//블락페이지
		int blockPage= Integer.parseInt(request.getServletContext().getInitParameter("BLOCK-PAGE"));
		//전체 페이지수
		int totalPage = (int)Math.ceil((double)totalRecordCount/pageSize);
		//현재 페이지 번호
		int nowPage =request.getParameter("nowPage")==null?1:Integer.parseInt(request.getParameter("nowPage"));
		//시작 및 끝 ROWNUM구하기
		int start = (nowPage-1)*pageSize+1;
		int end = nowPage*pageSize;	
		//페이징을 위한 로직 끝	
		map.put("start",start);	
		map.put("end",end);	
		map.put("totalRecordCount", totalRecordCount);
		map.put("pageSize", pageSize);
		map.put("blockPage", blockPage);
		map.put("totalPage", totalPage);
		map.put("nowPage", nowPage);		
		
	}////////////////////////////
}
