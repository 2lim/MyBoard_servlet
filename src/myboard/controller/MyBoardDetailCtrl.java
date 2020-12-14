package myboard.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.model.myBoardVO;
import service.myboard.MyBoardService;

/**
 * Servlet implementation class MyBoardDetailCtrl
 */
@WebServlet("/myboardDetail.do")
public class MyBoardDetailCtrl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyBoardDetailCtrl() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		execute(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		execute(request, response);
	}

	private void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		MyBoardService mbsv = new MyBoardService();
		int bno = 0;
		bno = Integer.parseInt(request.getParameter("bno"));
		String pageNum = request.getParameter("pageNum");
		
		try {
			myBoardVO bdetail = mbsv.myboardDetail(bno);
			int myboardView = mbsv.viewCount(bno);
			if (bdetail != null) {
				request.setAttribute("bdetail", bdetail);
				RequestDispatcher disp = request.getRequestDispatcher("./myboardDetail.jsp");
				disp.forward(request, response);
			} else {
				System.out.println("상세페이지 불러오기 실패");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
