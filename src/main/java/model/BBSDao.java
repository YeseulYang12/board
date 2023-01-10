package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import jakarta.servlet.ServletContext;
import service.DaoService;

public class BBSDao implements DaoService<BBSDto>{
	//멤버변수(속성들)
	private Connection conn;
	private ResultSet rs;
	private PreparedStatement psmt;
	
	//생성자
	public BBSDao(ServletContext context) {
		try {
			//커넥션 풀]
			DataSource source= (DataSource)context.getAttribute("DataSource");
			conn = source.getConnection();
			System.out.println("데이터베이스 연결 성공:"+conn);
		}
		catch(SQLException e) {e.printStackTrace();}
		
	}////////

	@Override
	public void close() {
		try {
			if(rs !=null) rs.close(); //메모리해제
			if(psmt !=null) psmt.close(); //메모리해제
			if(conn !=null) conn.close(); //커넥션풀에 커넥션객체 반납
		}
		catch(SQLException e) {e.printStackTrace();}
	}////////

	@Override
	public List<BBSDto> selectList(Map map) {
		List<BBSDto> records = new Vector<>();
		//페이징 적용 - 구간 쿼리
		String sql="SELECT * FROM (SELECT T.*,ROWNUM R FROM (SELECT b.*,name FROM bbs b JOIN users u ON b.id=u.id ";
		
		sql+=" ORDER BY no DESC) T) WHERE R BETWEEN ? AND ?";
		System.out.println("목록쿼리:"+sql);
		try {
			psmt = conn.prepareStatement(sql);
			//페이징을 위한 시작 및 종료 rownum설정
			psmt.setString(1, map.get("start").toString());
			psmt.setString(2, map.get("end").toString());
			
			rs = psmt.executeQuery();
			while(rs.next()) {
				BBSDto dto = new BBSDto();
				dto.setNo(rs.getString(1));
				dto.setTitle(rs.getString(2));
				dto.setContent(rs.getString(3));
				dto.setHitCount(rs.getString(4));
				dto.setPostDate(rs.getDate(5));
				dto.setId(rs.getString(6));
				dto.setName(rs.getString(7));
				records.add(dto);
			}
		}
		catch(SQLException e) {e.printStackTrace();}		
		return records;
	}////////
	
	//이전글/다음글 조회
	public Map<String, BBSDto> prevNext(String currentNo){
			Map<String, BBSDto> map = new HashMap<>();
			try {
				//이전글 얻기]
				String sql="SELECT NO,TITLE FROM BBS WHERE no=(SELECT Min(NO) FROM bbs WHERE NO > ?)";
				psmt = conn.prepareStatement(sql);
				psmt.setString(1, currentNo);
				rs = psmt.executeQuery();
				if(rs.next()) {
					map.put("PREV", new BBSDto(rs.getString(1), rs.getString(2), null, null, null, null));
				}
				
				//다음글 얻기]
				sql="SELECT NO,TITLE FROM BBS WHERE no=(SELECT MAX(NO) FROM bbs WHERE NO < ?)";
				psmt = conn.prepareStatement(sql);
				psmt.setString(1, currentNo);
				rs = psmt.executeQuery();
				if(rs.next()) {
					map.put("NEXT", new BBSDto(rs.getString(1), rs.getString(2), null, null, null, null ));
				}
			}
			catch(SQLException e) {e.printStackTrace();}
			return map;
		}////////////////////

	@Override
	public int getTotalRecordCount(Map map) {
		int totalRecordCount=0;
		//검색시에도 페이징 적용
		String sql="SELECT COUNT(*) FROM bbs b JOIN users u ON u.id=b.id ";
		try {
			psmt = conn.prepareStatement(sql);
			rs=psmt.executeQuery();
			rs.next();
			totalRecordCount=rs.getInt(1);
		}
		catch(SQLException e) {e.printStackTrace();}
		return totalRecordCount;
	}////////

	@Override
	public int insert(BBSDto dto) {
		int affected=0;
		String sql="INSERT INTO bbs VALUES(SEQ_BBS.NEXTVAL,?,?,DEFAULT,SYSDATE,?)";
		try {
			psmt = conn.prepareStatement(sql,new String[] {"no"});
			psmt.setString(1, dto.getTitle());
			psmt.setString(2, dto.getContent());
			psmt.setString(3, dto.getId());
			affected = psmt.executeUpdate();
		}
		catch(SQLException e) {e.printStackTrace();}
		return affected;
	}////////

	@Override
	public BBSDto selectOne(String... one) {
		BBSDto dto=null;
		
		try {
			if(one.length>=2)
				System.out.println("이전 페이지명:"+one[1]);
			//목록에서 넘어온 경우에만 조회수 증가
			if(one.length>=2 && one[1].toUpperCase().startsWith("LIST")) {
				//조회 수 업데이트
				psmt = conn.prepareStatement("UPDATE bbs SET hitcount=hitcount+1 WHERE no=?");
				psmt.setString(1, one[0]);
				psmt.executeUpdate();
			}
			
			String sql="SELECT b.*,name FROM bbs b JOIN users u ON b.id=u.id WHERE no=?";
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, one[0]);
			rs=psmt.executeQuery();
			if(rs.next()) {
				dto = new BBSDto();
				dto.setNo(rs.getString(1));
				dto.setTitle(rs.getString(2));
				dto.setContent(rs.getString(3));
				dto.setHitCount(rs.getString(4));
				dto.setPostDate(rs.getDate(5));
				dto.setId(rs.getString(6));
				dto.setName(rs.getString(7));			
			}
		} 
		catch (SQLException e) {e.printStackTrace();}
		return dto;
	}////////

	@Override
	public int update(BBSDto dto) {
		int affected=0;
		String sql="UPDATE bbs SET title=?,content=? WHERE no=?";
		try {			
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, dto.getTitle());
			psmt.setString(2, dto.getContent());
			psmt.setString(3, dto.getNo());
			affected=psmt.executeUpdate();		
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return affected;
	}////////

	@Override
	public int delete(String no) {
		return 0;
	}////////
	
	//회원여부 판단용
	public boolean isMember(String id,String pwd) {
			String sql="SELECT COUNT(*) FROM users WHERE id=? AND pwd=?";
			try {
				psmt= conn.prepareStatement(sql);
				psmt.setString(1, id);
				psmt.setString(2, pwd);
				rs = psmt.executeQuery();
				rs.next();
				if(rs.getInt(1)==0) return false;
			}
			catch(SQLException e) {
				e.printStackTrace();
				return false;
			}
			return true;
	}/////////////////////////
	

}
