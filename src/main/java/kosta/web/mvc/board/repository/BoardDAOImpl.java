package kosta.web.mvc.board.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.map.HashedMap;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kosta.web.mvc.board.dto.ElectronicsDTO;

@Repository
public class BoardDAOImpl implements BoardDAO {
	
	@Autowired
	private SqlSession session;

	@Override
	public List<ElectronicsDTO> selectAll() {
		return session.selectList("boardMapper.selectAll");
	}
	
	@Override
	public ElectronicsDTO selectByModelNum(String modelNum) {
		return session.selectOne("boardMapper.selectAll", modelNum);
	}
	

	@Override
	public int readnumUpdate(String modelNum) {
		return session.update("boardMapper.readnumUpdate" , modelNum);
	}

	@Override
	public int insert(ElectronicsDTO electronics) {
		return session.insert("boardMapper.insert", electronics);
	}

	@Override
	public int delete(String modelNum, String password) {
		Map<String, String> map = new HashMap<>();
		map.put("modelNum", modelNum);
		map.put("password", password);
		
		return session.delete("boardMapper.delete" , map);
	}

	@Override
	public int update(ElectronicsDTO electronics) {
		return session.update("boardMapper.update", electronics);
	}

}





