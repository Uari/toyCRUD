package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.BoardDao;
import com.example.demo.vo.BoardVO;

//5. 서비스단(Logic)
@Service
public class BoardLogic {
  Logger logger = LoggerFactory.getLogger(BoardLogic.class);
  //6.1 Repository 호출
  @Autowired
  BoardDao boardDao = null;

  public List<Map<String, Object>> boardList(Map<String, Object> pmap) {
    logger.info("==== BoardLogic 호출 ====");

    List<Map<String, Object>> list = new ArrayList<>();
    //6. 현재 로직에서 처리할 것이 없음 그래서 바로 Dao 호출
    list = boardDao.boardList(pmap);
    //10. DB에서 가져온 데이터 return
    return list;
  }

  public int boardInsert(Map<String, Object> pmap) {
    logger.info("==== boardIsert 호출 ====");
    int result = 0;
    result = boardDao.boardInsert(pmap);
    return result;
  }

  public int boardDelete(Map<String, Object> pmap) {
    logger.info("==== boardDelete 호출 ====");
    int result = 0;
    result = boardDao.boardDelete(pmap);
    return result;
  }

  public int boardUpdate(Map<String, Object> pmap) {
     logger.info("==== boardUpdate 호출 ====");
    int result = 0;
    result = boardDao.boardUpdate(pmap);
    return result;
  }
}
