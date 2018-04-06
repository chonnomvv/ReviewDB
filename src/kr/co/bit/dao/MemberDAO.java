package kr.co.bit.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import kr.co.bit.database.ConnectionManager;
import kr.co.bit.vo.MemberVO;

public class MemberDAO {

	public boolean insert(MemberVO vo) {
		
		boolean flag = false;
		ConnectionManager mgr = new ConnectionManager();
		Connection conn = mgr.getConnection();
		String sql = "insert into member_tbl values(?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement stmt = null; //
		System.out.println("입력완료");
		try {
			stmt = conn.prepareStatement(sql); // prepareStatment 는 인스턴트를 만들 떄 보낸다.
			// execute보내기 전에 데이터 세팅하기
			stmt.setString(1, vo.getId());
			stmt.setString(2, vo.getPw());
			stmt.setString(3, vo.getName());
			stmt.setString(4, vo.getEmail());
			stmt.setString(5, vo.getZipcode());
			stmt.setString(6, vo.getAddr1());
			stmt.setString(7, vo.getAddr2());
			stmt.setString(8, vo.getTool());
			String[] temp = vo.getLangs();
			StringBuffer sb = new StringBuffer(temp[0]);
			for (int i = 1; i < temp.length; i++) {
				// -를 구분자로 해서 구현
				sb.append("-" + temp[i]);
			}
			/*
			 * String[] langs = vo.getLangs();
			 * 
			 * String temp = ""; for (String tempx : langs) { temp += tempx; }
			 */
			stmt.setString(9, String.valueOf(sb));
			stmt.setString(10, vo.getProject());
			int affectedCount = stmt.executeUpdate(); // 몇개의 레코드가 영향을 받았느냐
			if (affectedCount > 0) {
				flag = true;
				System.out.println("insert 완료");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally { // 무조건 실행하기 위하여
			
			mgr.connectClose(conn, stmt, null);
		}
		return flag;
	}

	public ArrayList<MemberVO> select() {

		ArrayList<MemberVO> list = null;

		ConnectionManager mgr = new ConnectionManager();
		Connection conn = mgr.getConnection(); // 코드가 지나가는 길

		Statement stmt;

		try {
			stmt = conn.createStatement(); // 쿼리를 보내는 것
			String sql = "select * from member_tbl"; // 통로로 보내는 쿼리
			ResultSet rs = stmt.executeQuery(sql); // 통로를 통해 결과 요청하고 받는 라인
			list = new ArrayList();
			MemberVO dao = null;
			while (rs.next()) { // 데이터베이스 읽기(한 줄 한 줄 내려가며 레코드를 읽는 것)

				dao = new MemberVO();
				dao.setId(rs.getString(1));
				dao.setPw(rs.getString(2));
				dao.setName(rs.getString(3));
				dao.setEmail(rs.getString(4));
				dao.setZipcode(rs.getString(5));
				dao.setAddr1(rs.getString(6));
				dao.setAddr2(rs.getString(7));
				dao.setTool(rs.getString(8));
				String temp = rs.getString(9);
				String[] langs = temp.split("-");
				String[] vals = {"","","",""};
				for(String index : langs) {
					int idx = Integer.parseInt(index);
					vals[idx]=index;
				}
				
				dao.setLangs(vals);
				dao.setProject(rs.getString(10));
				list.add(dao);

			}
			mgr.connectClose(conn, stmt, rs); // 연결된 통로들 닫기.
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
	public ArrayList<MemberVO> search(String id) {

		ArrayList<MemberVO> list = null;

		ConnectionManager mgr = new ConnectionManager();
		Connection conn = mgr.getConnection(); // 코드가 지나가는 길

		Statement stmt;

		try {
			stmt = conn.createStatement(); // 쿼리를 보내는 것
		
			String sql = "select * from member_tbl where "+ id; // 통로로 보내는 쿼리
			ResultSet rs = stmt.executeQuery(sql); // 통로를 통해 결과 요청하고 받는 라인
			list = new ArrayList();
			MemberVO dao = null;
			while (rs.next()) { // 데이터베이스 읽기(한 줄 한 줄 내려가며 레코드를 읽는 것)

				dao = new MemberVO();
				dao.setId(rs.getString(1));
				dao.setPw(rs.getString(2));
				dao.setName(rs.getString(3));
				dao.setEmail(rs.getString(4));
				dao.setZipcode(rs.getString(5));
				dao.setAddr1(rs.getString(6));
				dao.setAddr2(rs.getString(7));
				dao.setTool(rs.getString(8));
				String temp = rs.getString(9);
				String[] langs = temp.split("-");
				String[] vals = {"","","",""};
				for(String index : langs) {
					int idx = Integer.parseInt(index);
					vals[idx]=index;
				}
				
				dao.setLangs(vals);
				dao.setProject(rs.getString(10));
				list.add(dao);

			}
			mgr.connectClose(conn, stmt, rs); // 연결된 통로들 닫기.
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
}
