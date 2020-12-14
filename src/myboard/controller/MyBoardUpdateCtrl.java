package myboard.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.model.myBoardVO;
import service.myboard.MyBoardService;

/**
 * Servlet implementation class MyBoardUpdateCtrl
 */
@WebServlet("/myboardUpdate.do")
public class MyBoardUpdateCtrl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyBoardUpdateCtrl() {
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
		execute(request, response);
	}

	private void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		MyBoardService mbsv = new MyBoardService();
		PrintWriter out = response.getWriter();
		
		int bno = Integer.parseInt(request.getParameter("bno").trim());
//		String pageNum = request.getParameter("pageNum");
		String bwriter = request.getParameter("bwriter");
		String btitle = request.getParameter("btitle");
		String bcontent = request.getParameter("bcontent");
		
		myBoardVO vo = new myBoardVO();
		vo.setBno(bno);
		vo.setBwriter(bwriter);
		vo.setBtitle(btitle);
		vo.setBcontent(bcontent);
		
		try {
			int result = mbsv.myboardUpdate(vo, bno);
			System.out.println(vo);
			if(result == 1) {
				System.out.println(result+"행이 수정되었습니다");
				request.setAttribute("noticeUpdate", "수정이 완료되었습니다.");
				RequestDispatcher disp = request.getRequestDispatcher("./myboardDetail.do");
				disp.forward(request, response);
				out.println("수정되었습니다.");
			} else {
				RequestDispatcher disp = request.getRequestDispatcher("./myboardDetail.do");
				disp.forward(request, response);
				System.out.println("수정하지 못했습니다");
			}
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println("수정 오류 발생");
		}
		out.flush();
		out.close();
	}

}
