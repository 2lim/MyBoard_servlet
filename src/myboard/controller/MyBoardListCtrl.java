package myboard.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.model.myBoardVO;

import service.myboard.MyBoardService;

/**
 * Servlet implementation class MyBoardListCtrl
 */
@WebServlet("/myboardList.do")
public class MyBoardListCtrl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyBoardListCtrl() {
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
		MyBoardService mbsv = new MyBoardService();
		
		int pageSize = 10;
		int pageBlock = 5;
		try {
			//총 글 개수
			int mbCount = mbsv.getBoardCount();
			//페이지수 초기화
			String pageNum = request.getParameter("pageNum");
			if(pageNum == null) {
				pageNum ="1";
			} else if(pageNum.equals("")) {
				pageNum ="1";
			}
			//startPage, endPage 구하기
			int currentPage = 1;
			try {
				currentPage = Integer.parseInt(pageNum);
			} catch(Exception e) {
				e.printStackTrace();
			}
			
			int pageCount = (mbCount / pageSize) + (mbCount % pageSize == 0? 0:1);
			int startPage = 1;
			int endPage = 1;
			if(currentPage % pageBlock == 0) {
				startPage = ((currentPage / pageBlock) -1) * pageBlock + 1;
			} else {
				startPage = ((currentPage / pageBlock)) * pageBlock + 1;
			}
			endPage = startPage + pageBlock -1;
			if(endPage > pageCount)
				endPage = pageCount;
			
			//페이징 rownum 구하기
			int startRnum = ((currentPage -1)*pageSize) +1; //공식
			int endRnum = startRnum + pageSize -1;
			System.out.println(startRnum + " - " + endRnum);
			
			int prev = 1;
			int next = 1;
			if(startPage != 1) {
				prev = startPage -1;
			} 
			if(endPage > pageCount) {
				next = endPage + 1;
			} 
			System.out.println(prev + "이전 - 다음" + next);
			List<myBoardVO> list = mbsv.myboardList(startRnum, endRnum);
			
			//데이터 보내주기
			String search_error = (String) request.getAttribute("search_error");
			System.out.println(search_error);
			request.setAttribute("search_error", search_error);
			request.setAttribute("startPage", startPage);
			request.setAttribute("endPage", endPage);
			request.setAttribute("PageNum", currentPage);
			request.setAttribute("pageCount", pageCount);
			request.setAttribute("prev", prev);
			request.setAttribute("next", next);

			request.setAttribute("myboardList", list); /* 변경 : el태그 - jsp이랑 맞추기 */
			System.out.println(list.size() + ", " + startPage + ", " + endPage);
			RequestDispatcher disp = request.getRequestDispatcher("./myboardList.jsp"); 
			disp.forward(request, response);
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}

}
