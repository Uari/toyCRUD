package com.example.demo.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class BoardDao {
  Logger logger = LoggerFactory.getLogger(BoardDao.class);

  @Autowired
  SqlSessionTemplate sqlSessionTemplate = null;

  public List<Map<String, Object>> boardList(Map<String, Object> pmap) {
    logger.info("===== Board 호출 ====");
    List<Map<String, Object>> list = sqlSessionTemplate.selectList("boardList", pmap);
    logger.info(list.toString());
    return list;
  }
}
