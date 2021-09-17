package cnav.approval.dao;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.context.request.RequestContextHolder;

import cnav.approval.dto.ApprovalDTO;

// 3번 데이터 담당자 

@Repository // DB관련 작업이라고 알려주자. 
public class ApprovalDAOImpl implements ApprovalDAO {
	
	@Autowired //자동 주입 
	private SqlSessionTemplate sqlSession = null;
	
	@Override
	public void insertApp(ApprovalDTO dto) throws SQLException{
		// 팀장이 데이터 주면서 db에 저장하라고 했어! -> 창고 관리자에게.. 
		sqlSession.insert("approval.insertApp", dto);
		
	}
	
	// sendAppList 전체 글 개수 가져오기
	@Override 
	public int getAppCount() throws SQLException {
		int result = sqlSession.selectOne("approval.countAll");
		
		return result;
	}
	
	// 한페이지 게시글 목록 가져오기 
	@Override
	public List<ApprovalDTO> getApprovals(int start, int end) throws SQLException{
		
		HashMap map = new HashMap();
		map.put("start", start);
		map.put("end", end);
		
		List<ApprovalDTO> approvalList = sqlSession.selectList("approval.getApprovals", map);
		
		return approvalList;
	}
	// 검색 게시글 수 가져오기 
		@Override
		public int searchSendAppCount(String sel, String search) throws SQLException {
			
			HashMap map = new HashMap();
			map.put("sel", sel);
			map.put("search", search);
			
			int result = sqlSession.selectOne("approval.countSearch", map);
			
			return result;
		}
		// 검색 게시글 목록 가져오기 
		@Override
		public List<ApprovalDTO> sendSearchApprovals(int start, int end, String sel, String search) throws SQLException {
			
			HashMap map = new HashMap();
			map.put("start", start);
			map.put("end", end);
			map.put("sel", sel);
			map.put("search", search);
			
			List<ApprovalDTO> sendAppList = sqlSession.selectList("approval.sendSearchApprovals", map);
			
			return sendAppList;
		}
	
	// 게시글 1개 가져오기 
	@Override
	public ApprovalDTO getAppCont(int appNum) throws SQLException {
		ApprovalDTO approval = sqlSession.selectOne("approval.getOneApproval", appNum); 
		
		return approval;
	}

	// 보낸 결재 수정 
	@Override
	public void updateApp(ApprovalDTO dto) throws SQLException{
		sqlSession.update("approval.updateApp", dto);
	}
	
	// 보낸 결재 삭제 
	@Override
	public void deleteApp(int appNum) throws SQLException{
		sqlSession.delete("approval.deleteApp", appNum);
	}
	
//////////////////////////////////////////////////////////////////////////
	
	// takeAppList 전체 글 개수 가져오기
	@Override 
	public int takeAppCount() throws SQLException {
		int result = sqlSession.selectOne("approval.takeCountAll");
		
		return result;
	}
	
	// 한페이지 게시글 목록 가져오기
	@Override
	public List<ApprovalDTO> takeApprovals(int start, int end) throws SQLException{
		
		HashMap map = new HashMap();
		map.put("start", start);
		map.put("end", end);
		//RequestContextHolder 로session 꺼내서 확인 
		String id = "java02"; 
		map.put("id", id);
		
		
		List<ApprovalDTO> takeApprovalList = sqlSession.selectList("approval.takeApprovals", map);
		
		return takeApprovalList;
	}
	
		
	// 검색 게시글 수 가져오기 
	@Override
	public int searchTakeAppCount(String sel, String search) throws SQLException {
		
		HashMap map = new HashMap();
		map.put("sel", sel);
		map.put("search", search);
		
		int result = sqlSession.selectOne("approval.takeCountSearch", map);
		
		return result;
	}
	// 검색 게시글 목록 가져오기 
	@Override
	public List<ApprovalDTO> takeSearchApprovals(int start, int end, String sel, String search) throws SQLException {
		
		HashMap map = new HashMap();
		map.put("start", start);
		map.put("end", end);
		map.put("sel", sel);
		map.put("search", search);
		
		List<ApprovalDTO> takeAppList = sqlSession.selectList("approval.takeSearchApprovals", map);
		
		return takeAppList;
	}
	
	// 받은 게시글 1개 가져오기 
	@Override
	public ApprovalDTO takeAppCont(int appNum) throws SQLException {
		ApprovalDTO approval = sqlSession.selectOne("approval.takeOneApproval", appNum); 
		
		return approval;
	}
	// 반려 사유 
	@Override
	public void insertReject(ApprovalDTO dto) throws SQLException{
		sqlSession.update("approval.insertReject", dto);
	}
	
	// 결재 상태 
	@Override
	public void updateAppState(Integer appNum, int sign, int newState) throws SQLException {
		HashMap map = new HashMap();
		map.put("appNum", appNum);
		map.put("sign", newState);
		if(sign == 1) { // 승인자1번일경우 쿼리 호출 
			sqlSession.selectOne("approval.updateState1", map); 
		}else if(sign == 2) {
			sqlSession.selectOne("approval.updateState2", map); 
		}else if(sign == 3) {
			sqlSession.selectOne("approval.updateState3", map); 
		}
	}
	
	// 2번째 승인자 추가
	@Override
	public void insertAddTake(ApprovalDTO dto) throws SQLException {
		sqlSession.update("approval.insertAddTake2", dto);
		
	}
	
	
	
	// 결재 상태 
	@Override
	public void updateAppState2(Integer appNum, int sign, int newState) throws SQLException {
		HashMap map = new HashMap();
		map.put("appNum", appNum);
		map.put("sign", newState);
		if(sign == 1) { // 승인자1번일경우 쿼리 호출 
			sqlSession.selectOne("approval.updateState1", map); 
		}else if(sign == 2) {
			sqlSession.selectOne("approval.updateState2", map); 
		}else if(sign == 3) {
			sqlSession.selectOne("approval.updateState3", map); 
		}
	}
	
	// 2번째 승인자 추가
	@Override
	public void insertAddTake2(ApprovalDTO dto) throws SQLException {
		sqlSession.update("approval.insertAddTake3", dto);
		
	}
	
	// 결재 상태 
		@Override
		public void updateAppState3(Integer appNum, int sign, int newState) throws SQLException {
			HashMap map = new HashMap();
			map.put("appNum", appNum);
			map.put("sign", newState);
			if(sign == 1) { // 승인자1번일경우 쿼리 호출 
				sqlSession.selectOne("approval.updateState1", map); 
			}else if(sign == 2) {
				sqlSession.selectOne("approval.updateState2", map); 
			}else if(sign == 3) {
				sqlSession.selectOne("approval.updateState3", map); 
			}
		}

}