package controller.dataroom;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.BBSDao;
import model.BBSDto;



@WebServlet("/DataRoom/Write.kosmo")
public class WriteController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/dataroom/Write.jsp").forward(req, resp);
	}///////////

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//파라미터 받기
		String title = req.getParameter("title");
		String content = req.getParameter("content");
		//작성자의 아이디 얻기:세션영역에서
		//HttpSession session;
		String id = req.getSession().getAttribute("USER-ID").toString();
		//데이타를 전달할 DTO객체 생성및 데이터 설정
		BBSDto dto = new BBSDto();
		dto.setContent(content);
		dto.setTitle(title);
		dto.setId(id);
		//CRUD작업용 DAO계열 객체 생성
		BBSDao dao = new BBSDao(getServletContext());
		int affected=dao.insert(dto);
		dao.close();
		if(affected ==1){
			req.getRequestDispatcher("/DataRoom/List.kosmo").forward(req, resp);
		}
		else{
			System.out.println("<script>");
			System.out.println("alert('입력 실패했어요');");
			System.out.println("history.back();");
			System.out.println("</script>");
		}
			
	}////////////////////

	
}
