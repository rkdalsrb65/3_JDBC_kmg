package edu.kh.jdbc.board.model.dao;

import static edu.kh.jdbc.common.JDBCTemplate.*;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import edu.kh.jdbc.board.model.vo.Board;
import edu.kh.jdbc.member.vo.Member;

public class BoardDAO {

	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	private Properties prop;

	public BoardDAO() {
		try {
			prop = new Properties();
			prop.loadFromXML(new FileInputStream("board-query.xml"));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/** 게시글 목록 조회 DAO
	 * @param conn
	 * @return boardList
	 * @throws Exception
	 */
	public List<Board> selectAllBoard(Connection conn) throws Exception{
		// 결과 저장용 변수 선언
		List<Board> boardList = new ArrayList<>();
		
		try {
			// SQL ~
			String sql = prop.getProperty("selectAllBoard");
			// Statement ~
			stmt = conn.createStatement();
			// SQL 수행 후~ ResultSet ~
			rs = stmt.executeQuery(sql);
			// ResultSet에 저장된 값을 List 옮겨 담기
			while(rs.next()) {
				// 컬럼 값을 얻어와 Member 객체에 저장 후 List에 추가

				int boardNo = rs.getInt("BOARD_NO");
				String boardTitle = rs.getString("BOARD_TITLE");
				String memberName = rs.getString("MEMBER_NM");
				int readCount = rs.getInt("READ_COUNT");
				String createDate = rs.getString("CREATE_DT");
				int commentCount = rs.getInt("COMMENT_COUNT");
				
				Board board = new Board();
				board.setBoardNo(boardNo);
				board.setBoardTitle(boardTitle);
				board.setMemberName(memberName);
				board.setReadCount(readCount);
				board.setCreateDate(createDate);
				board.setCommentCount(commentCount);
				
				boardList.add(board);// List 담기
			}			
			
		} finally {
			// JDBC 객체 자원 반환
			close(rs);
			close(stmt);
		}
		
		// 조회 결과 반환
		return boardList;
		
	}

	
}