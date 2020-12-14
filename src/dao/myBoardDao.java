package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import board.model.myBoardVO;



public class myBoardDao {
	private PreparedStatement pstmt = null;
	private Statement stmt = null;
	private ResultSet rs = null;

	private void close() {
		try {
			if (rs != null) {
				rs.close();
				rs = null;
			}
			if (pstmt != null) {
				pstmt.close();
				pstmt = null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	// 게시판 검색기능
		public List<myBoardVO> myboardSearch(Connection conn, String mbSearch, int start, int end) throws SQLException {
			List<myBoardVO> list = null;
			myBoardVO vo = null;
			String sql = "select * from (select ROWNUM rnum, mb.* from (select * from myboard where btitle like ? or bcontent like ? order by bno desc) mb) where rnum>=? and rnum<=? ";
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, "%" + mbSearch + "%");
				pstmt.setString(2, "%" + mbSearch+ "%");
				pstmt.setInt(3, start);
				pstmt.setInt(4, end);
				rs = pstmt.executeQuery();
				list = new ArrayList<myBoardVO>();
				if (rs.next()) {
					do {
						vo = new myBoardVO();
						vo.setBno(rs.getInt("bno"));
						vo.setBtitle(rs.getString("btitle"));
						vo.setBcontent(rs.getString("bcontent"));
						vo.setBdate(rs.getDate("bdate"));
						vo.setBview(rs.getInt("bview"));
						vo.setBcount(start++);
						System.out.println("start"+	start);
						list.add(vo);
					} while (rs.next());
				}
			} finally {
				close();
			}
			return list;
		}
		
		// 게시판 검색 페이징 - 총 검색결과 수
		public int getSearchCount(Connection conn, String mbSearch) throws SQLException {
			int cnt = 0;
			String sql = "select COUNT(*) from (select ROWNUM rnum, mb.* from (select * from myboard where btitle like ? or bcontent like ? order by bno desc) mb)";
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, "%" + mbSearch + "%");
				pstmt.setString(2, "%" + mbSearch + "%");
				rs = pstmt.executeQuery();
				if (rs.next()) {
					cnt = rs.getInt(1);
				}
			} finally {
				close();
			}
			return cnt;
		}
		
		// 게시판 목록 페이징 - 공지사항 총 글 개수
		public int getBoardCount(Connection conn) throws SQLException {
			int cnt = 0;
			String sql = "select COUNT(*) from myboard";
			try {
				stmt = conn.createStatement();
				rs = stmt.executeQuery(sql);
				if (rs.next()) {
					cnt = rs.getInt(1);
				}
			} finally {
				close();
			}
			return cnt;
		}

		// 게시판 목록 메소드
		public List<myBoardVO> myboardList(Connection conn, int start, int end) throws SQLException {
			List<myBoardVO> list = new ArrayList<myBoardVO>();
			myBoardVO vo = null;
			String sql = "select * from (select ROWNUM rnum, mb.* from (select * from myboard order by bno desc) mb) where rnum >= ? and rnum <= ?";
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, start);
				pstmt.setInt(2, end);
				rs = pstmt.executeQuery();
				if (rs.next()) {
					do {
						vo = new myBoardVO();
						vo.setBno(rs.getInt("bno"));
						vo.setBwriter(rs.getString("bwriter"));
						vo.setBtitle(rs.getString("btitle"));
						vo.setBdate(rs.getTimestamp("bdate"));
						vo.setBcontent(rs.getString("bcontent"));
						vo.setBview(rs.getInt("bview"));
						list.add(vo);
					} while (rs.next());
				}
			} finally {
				close();
			}
			return list;
		}

		// 게시판 상세페이지
		public myBoardVO myboardDetail(Connection conn, int bno) throws SQLException {
			myBoardVO vo = null;
			String sql = "select * from myboard where bno=?";
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, bno);
				rs = pstmt.executeQuery();
				if (rs.next()) {
					vo = new myBoardVO();
					vo.setBno(rs.getInt("bno"));
					vo.setBwriter(rs.getString("bwriter"));
					vo.setBtitle(rs.getString("btitle"));
					vo.setBdate(rs.getTimestamp("bdate"));
					vo.setBcontent(rs.getString("bcontent"));
					vo.setBview(rs.getInt("bview"));
				}
			} finally {
				close();
			}
			return vo;
		}

		// 게시판 글 작성
		public int myboardWrite(Connection conn, myBoardVO vo) throws SQLException {
			int result = 0;

			String bwriter = vo.getBwriter();
			String btitle = vo.getBtitle();
			String bcontent = vo.getBcontent();
			String sql = "insert into myboard values(myb_seq.NEXTVAL,?,?,default,?,0)";

			// insert
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, bwriter); 
				pstmt.setString(2, btitle); 
				pstmt.setString(3, bcontent); 
				result = pstmt.executeUpdate();
				if (result < 1) {
					System.out.println("insert 실패");
				}
			} finally {
				close();
			}
			return result;
		}

		// 게시판 글 수정
		public int myboardUpdate(Connection conn, myBoardVO vo, int bno) throws SQLException {
			int result = 0;
			String sql = "update myboard set btitle=? , bcontent=? where bno=?";
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, vo.getBtitle());
				pstmt.setString(2, vo.getBcontent());
				pstmt.setInt(3, vo.getBno());
				result = pstmt.executeUpdate();
			} finally {
				close();
			}
			return result;
		}

		// 게시판 글 삭제
		public int myboardDelete(Connection conn, int bno) throws SQLException {
			int result = 0;
			String sql = "delete from myboard where bno=?";
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, bno);
				result = pstmt.executeUpdate();
			} finally {
				close();
			}
			return result;
		}
		
		//조회수 메소드
		public int viewCount(Connection conn, int bno) throws SQLException{
			int result = 0;
			String sql = "update myboard set bview=bview+1 where bno=?";
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, bno);
				result = pstmt.executeUpdate();
			}finally {
				close();
			}
			return result;
		}

		

}
