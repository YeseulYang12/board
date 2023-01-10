package controller.dataroom;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.BBSDao;
import model.BBSDto;

@WebServlet("/DataRoom/View.kosmo")
public class ViewController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//3]요청분석
		String no = req.getParameter("no");
		//4]모델 호출 및 결과값 받기
		BBSDao dao = new BBSDao(getServletContext());
		BBSDto dto= dao.selectOne(no);
		dao.close();
		//줄바꿈 처리
		dto.setContent(dto.getContent().replace("\r\n","<br/>"));
		//5]필요한 값 리퀘스트 영역에 저장
		req.setAttribute("record", dto);
		//6]뷰 선택후 포워딩
		req.getRequestDispatcher("/dataroom/View.jsp").forward(req, resp);
	}
}
