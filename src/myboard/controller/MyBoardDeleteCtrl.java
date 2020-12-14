package myboard.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.myboard.MyBoardService;

/**
 * Servlet implementation class MyBoardDeleteCtrl
 */
@WebServlet("/myboardDelete.do")
public class MyBoardDeleteCtrl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyBoardDeleteCtrl() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		execute(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		execute(request, response);
	}
	private void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("삭제 서블릿 진입");
		PrintWriter out = response.getWriter();
		MyBoardService mbsv = new MyBoardService();
		try {
			int bno = Integer.parseInt(request.getParameter("bno").trim());
			int result = mbsv.myboardDelete(bno);
			if(result == 1) {
				System.out.println("삭제 성공");
				out.println("삭제 되었습니다.");
				RequestDispatcher disp = request.getRequestDispatcher("./myboardList.do");
				disp.forward(request, response);
			}else {
				RequestDispatcher disp = request.getRequestDispatcher("./myboardDetail.jsp");
				disp.forward(request, response);
				System.out.println("삭제 실패");
				out.append("<script>alert('삭제에 실패 했습니다. 다시 시도해 주세요.')</script>");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("삭제 중 오류 발생");
		}
		out.flush();
		out.close();
	}

}
