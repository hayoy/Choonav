package cnav.mypage.dao;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cnav.mail.dto.MailDTO;
import cnav.main.dto.UserDTO;
import cnav.mypage.dto.TopicCommDTO;
import cnav.mypage.service.MypageService;
import cnav.project.dto.ProjectDTO;
import cnav.reservation.dto.ReservationDTO;
import cnav.topic.dto.TopicDTO;

@Service
public class MypageDAOImpl implements MypageDAO{

	@Autowired
	private SqlSessionTemplate sqlSession = null;
	
	@Override
	public int getSearchMypjCount(String userId, String code) throws SQLException {
		HashMap map = new HashMap();
		map.put("userId", userId);
		map.put("code", code);
		
		return sqlSession.selectOne("my.countMypj", map);
	}
	@Override
	public int getSearchMytopicCount(String userId, String code) throws SQLException {
		HashMap map = new HashMap();
		map.put("userId", userId);
		map.put("code", code);
		
		return sqlSession.selectOne("my.countMytopic", map);
	}

	@Override
	public List getMypjList(String userId, String code, int start, int end) throws SQLException {
		HashMap map = new HashMap();
		map.put("start", start);
		map.put("end", end);
		map.put("code", code);
		map.put("userId", userId);
		
		
		List<ProjectDTO> list = sqlSession.selectList("my.selectMypjlist", map);
		
		return list;
	}

	@Override
	public List getMytopicList(String userId, String code, int start, int end) throws SQLException {
		System.out.println("topic service");
		HashMap map = new HashMap();
		map.put("start", start);
		map.put("end", end);
		map.put("code", code);
		map.put("userId", userId);
		
		
		List<TopicDTO> list = sqlSession.selectList("my.selectMytopiclist", map);
		System.out.println("dao"+list);
		return list;
	}
	@Override
	public int getTopicCommCount(String userId, String code) throws SQLException {
		HashMap map = new HashMap();
		map.put("userId", userId);
		map.put("code", code);
		
		return sqlSession.selectOne("my.countMytopicComm", map);
	}
	@Override
	public List getMytopicCommList(String userId, String code, int start, int end) throws SQLException {
		System.out.println("topic service");
		HashMap map = new HashMap();
		map.put("start", start);
		map.put("end", end);
		map.put("code", code);
		map.put("userId", userId);
		
		
		List<TopicCommDTO> list = sqlSession.selectList("my.selectMytopicCommlist", map);
		System.out.println("dao"+list);
		
		return list;
	}
	
	// 관리자 맞는지, 코드 맞는지 확인
	@Override
	public int manageCheck(String id, String code) throws SQLException {
		HashMap map = new HashMap();
		map.put("id", id);
		map.put("code", code);
		int count = sqlSession.selectOne("my.manageCheck", map);
		return count;
	}
	
	// 회사 사원 정보 count
	@Override
	public int userListCount(String code) throws SQLException {
		int result = sqlSession.selectOne("my.userListCount", code);
		return result;
	}
	// 회사 사원 정보 list
	@Override
	public List userList(int start, int end, String code) throws SQLException {
		HashMap map = new HashMap();
		map.put("start", start);
		map.put("end", end);
		map.put("code", code);
		
		List<UserDTO> userList = sqlSession.selectList("my.userList", map);
		
		return userList;
	}
	// 검색한 회사 사원 정보 count
	@Override
	public int userListSearchCount(String sel, String search, String code) throws SQLException {
		HashMap map = new HashMap();
		map.put("sel", sel);
		map.put("search", search);
		map.put("code", code);
		
		int result = sqlSession.selectOne("my.userListSearchCount", map);
		return result;
	}
	// 검색한 회사 사원 정보 list
	@Override
	public List userListSearch(int start, int end, String sel, String search, String code) throws SQLException {
		HashMap map = new HashMap();
		map.put("start", start);
		map.put("end", end);
		map.put("sel", sel);
		map.put("search", search);
		map.put("code", code);
		
		List<UserDTO> userListSearch = sqlSession.selectList("my.userListSearch", map);
		return userListSearch;
	}
	// 팝업 -> 한명 정보 가져오기
	@Override
	public UserDTO userOne(String id) throws SQLException {
		UserDTO userOne = sqlSession.selectOne("my.userOne", id);
		return userOne;
	}
	// dept 수정
	@Override
	public int updateDepUser(UserDTO dto) throws SQLException {
		int result = sqlSession.update("my.updateDeUser", dto);
		return result;
	}
	// posi 수정
	@Override
	public int updatePosUser(UserDTO dto) throws SQLException {
		int result = sqlSession.update("my.updatePosUser", dto);
		return result;
	}

}
