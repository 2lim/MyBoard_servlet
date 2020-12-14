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
 * Servlet implementation class MyBoardWriteCtrl
 */
@WebServlet("/myboardWrite.do")
public class MyBoardWriteCtrl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyBoardWriteCtrl() {
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
		
		String btitle = request.getParameter("btitle");
		String bcontent = request.getParameter("bcontent");
//		bcontent.replaceAll("\n", "<br>");
		System.out.println(btitle);
		myBoardVO vo = new myBoardVO();
		
		vo.setBtitle(btitle);
		vo.setBcontent(bcontent);

		try {
			int result = mbsv.myboardWrite(vo);
			if (result == 1) {
				System.out.println(result + "행 추가되었습니다. myboardWrite controller");
				request.setAttribute("myboardWrite", result);
				response.sendRedirect("./myboardList.do");
			} else {
				System.out.println("공지사항 등록 실패");
				request.setAttribute("register_fail", "등록에 실패했습니다. 다시 시도해 주세요.");
				RequestDispatcher disp = request.getRequestDispatcher("./myboardWrite.jsp");
				disp.forward(request, response);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
