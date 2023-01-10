package controller.dataroom;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/DataRoom/index.kosmo")
public class IndexController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//리퀘스트 영역에 저장
		req.setAttribute("INDEX","누구나 자료를 올릴수 있습니다.");
		//dataroom/index.jsp로 포워드
		req.getRequestDispatcher("/dataroom/index.jsp").forward(req, resp);
	}
}
