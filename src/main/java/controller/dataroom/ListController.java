package controller.dataroom;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.BBSDao;
import model.BBSDto;
import model.PagingUtil;


//1]사용자 요청을 받을 수 있도록 서블릿 클래스로 만들기(HttpServlet상속)즉 컨트롤러로 만들기
@WebServlet("/DataRoom/List.kosmo")
public class ListController extends HttpServlet{

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//가]사용자 요청을 받는다	
		//나]요청을 분석한다.
		//다]모델에서 필요한 로직 호출해서 결과값이 있으면 받기 
		Map map = new HashMap();
		BBSDao dao =  new BBSDao(req.getServletContext());
		
		//페이징과 관련된 로직 호출
		PagingUtil.setMapForPaging(map, dao, req);
		int totalRecordCount=Integer.parseInt(map.get("totalRecordCount").toString());
		int blockPage=Integer.parseInt(map.get("blockPage").toString());
		int pageSize=Integer.parseInt(map.get("pageSize").toString());
		int totalPage=Integer.parseInt(map.get("totalPage").toString());
		int nowPage=Integer.parseInt(map.get("nowPage").toString());	
	
		String pagingString=PagingUtil.pagingBootStrapStyle(totalRecordCount, pageSize, blockPage, nowPage,req.getContextPath()+"/DataRoom/List.kosmo?");
		
		//게시판목록받기
		List<BBSDto> records= dao.selectList(map);
		//라]결과값이 있으면 리퀘스트 영역에 저장
		req.setAttribute("records", records);
		req.setAttribute("pagingString", pagingString);
		//마]결과값을 뿌려줄 뷰(JSP페이지) 선택후 포워딩 
		//뷰선택]
		RequestDispatcher dispatcher = req.getRequestDispatcher("/dataroom/List.jsp");
		//포워딩]
		dispatcher.forward(req, resp);
		
	}////////////////////
}
