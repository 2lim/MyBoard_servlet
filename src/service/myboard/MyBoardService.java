package service.myboard;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static common.filter.JDBCTemplete.close;
import static common.filter.JDBCTemplete.commit;
import static common.filter.JDBCTemplete.getConnection;
import static common.filter.JDBCTemplete.rollback;

import board.model.myBoardVO;
import dao.myBoardDao;

public class MyBoardService {
	public List<myBoardVO> myboardSearch(String mbSearch, int start, int end) throws SQLException {
		Connection conn = getConnection();
		List<myBoardVO> list = null;
		try {
			list = new myBoardDao().myboardSearch(conn, mbSearch, start, end);
		} finally {
			close(conn);
		}
		return list;
	}

	public int getSearchCount(String mbSearch) throws SQLException {
		Connection conn = getConnection();
		int result = 0;
		try {
			result = new myBoardDao().getSearchCount(conn, mbSearch);
		} finally {
			close(conn);
		}
		return result;
	}

	public int getBoardCount() throws SQLException {
		Connection conn = getConnection();
		int result = 0;
		try {
			result = new myBoardDao().getBoardCount(conn);
		} finally {
			close(conn);
		}
		return result;
	}

	public List<myBoardVO> myboardList(int start, int end) throws SQLException {
		Connection conn = getConnection();
		List<myBoardVO> list = null;
		try {
			list = new myBoardDao().myboardList(conn, start, end);
		} finally {
			close(conn);
		}
		return list;
	}

	public myBoardVO myboardDetail(int bno) throws SQLException {
		Connection conn = getConnection();
		myBoardVO vo = null;
		try {
			vo = new myBoardDao().myboardDetail(conn, bno);
		} finally {
			close(conn);
		}
		return vo;
	}

	public int myboardWrite(myBoardVO vo) throws SQLException {
		Connection conn = getConnection();
		int result = 0;
		try {
			result = new myBoardDao().myboardWrite(conn, vo);
			System.out.println(result + "행 추가됨");
		} finally {
			close(conn);
		}
		return result;
	}

	public int myboardUpdate(myBoardVO vo, int bno) throws SQLException {
		Connection conn = getConnection();
		int result = 0;
		try {
			result = new myBoardDao().myboardUpdate(conn, vo, bno);
		} finally {
			close(conn);
		}
		return result;
	}

	public int myboardDelete(int bno) throws SQLException {
		Connection conn = getConnection();
		int result = 0;
		try {
			result = new myBoardDao().myboardDelete(conn, bno);
		} finally {
			close(conn);
		}
		return result;
	}

	public int viewCount(int bno) throws SQLException {
		Connection conn = getConnection();
		int result = 0;
		try {
			result = new myBoardDao().viewCount(conn, bno);
		} finally {
			close(conn);
		}
		return result;
	}
}
