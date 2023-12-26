package com.example.demo.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.demo.vo.BoardVO;

//7.Dao(Repository)
@Repository
public class BoardDao {
  Logger logger = LoggerFactory.getLogger(BoardDao.class);

  //8.1 DB연결 할수 있게 SqlSessionTemplate주입
  @Autowired
  SqlSessionTemplate sqlSessionTemplate = null;

  public List<Map<String, Object>> boardList(Map<String, Object> pmap) {
    logger.info("===== Board 호출 ====");
    //8. mapper 단으로 ID값으로 호출
    List<Map<String, Object>> list = sqlSessionTemplate.selectList("boardList", pmap);
    //9. DB에서 가져온 데이터 Log에 찍어보기 
    logger.info(list.toString());
    return list;
  }

  public int boardInsert(Map<String, Object> pmap) {
    int result = 0;
    result = sqlSessionTemplate.insert("boardInsert", pmap);
    return result;
  }

  public int boardDelete(Map<String, Object> pmap) {
    int result = 0;
    result = sqlSessionTemplate.delete("boardDelete", pmap);
    return result;
  }

  public int boardUpdate(Map<String, Object> pmap) {
    int result = 0;
    result = sqlSessionTemplate.update("boardUpdate", pmap);
    return result;
  }
}
