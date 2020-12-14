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
 * Servlet implementation class MyBoardSearchCtrl
 */
@WebServlet("/myboardSearch.do")
public class MyBoardSearchCtrl extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MyBoardSearchCtrl() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
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
		// TODO Auto-generated method stub
		execute(request, response);
	}

	protected void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		MyBoardService mbsv = new MyBoardService();
		String mbSearch = request.getParameter("mbSearch");

		int pageSize = 10;
		int pageBlock = 5;
		try {
			// 총 글 개수
			int mbCount = mbsv.getBoardCount();
			// 페이지수 초기화
			String pageNum = request.getParameter("pageNum");
			if (pageNum == null) {
				pageNum = "1";
			} else if (pageNum.equals("")) {
				pageNum = "1";
			}
			// startPage, endPage 구하기
			int currentPage = 1;
			try {
				currentPage = Integer.parseInt(pageNum);
			} catch (Exception e) {
				e.printStackTrace();
			}

			int pageCount = (mbCount / pageSize) + (mbCount % pageSize == 0 ? 0 : 1);
			int startPage = 1;
			int endPage = 1;
			if (currentPage % pageBlock == 0) {
				startPage = ((currentPage / pageBlock) - 1) * pageBlock + 1;
			} else {
				startPage = ((currentPage / pageBlock)) * pageBlock + 1;
			}
			endPage = startPage + pageBlock - 1;
			if (endPage > pageCount)
				endPage = pageCount;

			// 페이징 rownum 구하기
			int startRnum = ((currentPage - 1) * pageSize) + 1; // 공식
			int endRnum = startRnum + pageSize - 1;
			System.out.println(startRnum + " - " + endRnum);

			int prev = 1;
			int next = 1;
			if (startPage != 1) {
				prev = startPage - 1;
			}
			if (endPage > pageCount) {
				next = endPage + 1;
			}
			System.out.println(prev + "이전 - 다음" + next);

			// 보내주기
			if (mbSearch == null || mbSearch == "") {
				request.setAttribute("search_error", "검색어를 입력해주세요");
				System.out.println("검색어가 null 인 경우");
				RequestDispatcher disp = request.getRequestDispatcher("myboardList.do");
				disp.forward(request, response);
			} else {
				try {
					List<myBoardVO> searchList = mbsv.myboardSearch(mbSearch, startRnum, endRnum);
					if (searchList != null) {
						request.setAttribute("startPage", startPage);
						request.setAttribute("endPage", endPage);
						request.setAttribute("PageNum", currentPage);
						request.setAttribute("pageCount", pageCount);
						request.setAttribute("prev", prev);
						request.setAttribute("next", next);
						request.setAttribute("searchList", searchList);
						request.setAttribute("nSearch", mbSearch);
						System.out.println("검색 수 " + searchList.size() + ", " + startPage + ", " + endPage);
						RequestDispatcher disp = request.getRequestDispatcher("./myboardSearch.jsp");
						disp.forward(request, response);

					} else {
						// null elert
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
