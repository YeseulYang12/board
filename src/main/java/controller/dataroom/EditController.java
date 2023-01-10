package controller.dataroom;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.BBSDao;
import model.BBSDto;

@WebServlet("/DataRoom/Edit.kosmo")
public class EditController extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String no = req.getParameter("no");
		BBSDao dao = new BBSDao(getServletContext());
		BBSDto record= dao.selectOne(no);
		dao.close();
		//리퀘스트 영역에 저장]
		req.setAttribute("record", record);
		//포워드]
		req.getRequestDispatcher("/dataroom/Edit.jsp").forward(req, resp);
	}///////////
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		
		String title= req.getParameter("title");
		String content= req.getParameter("content");
		String no = req.getParameter("no");
		
		//수정 처리로직 호출(데이터베이스 CRUD작업과 관련된 모델 호출)
		BBSDao dao = new BBSDao(getServletContext());
		BBSDto dto = new BBSDto();
		dto.setContent(content);
		dto.setTitle(title);
		dto.setNo(no);
		int affected= dao.update(dto);
		dao.close();
		if(affected ==1){
			req.getRequestDispatcher("/DataRoom/List.kosmo").forward(req, resp);
		}
		else{
			System.out.println("<script>");
			System.out.println("alert('수정 실패했어요');");
			System.out.println("history.back();");
			System.out.println("</script>");
		}
	
		
	}	

		
}/////////
