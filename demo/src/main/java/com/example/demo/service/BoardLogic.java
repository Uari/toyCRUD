package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.BoardDao;

@Service
public class BoardLogic {
  Logger logger = LoggerFactory.getLogger(BoardLogic.class);

  @Autowired
  BoardDao boardDao = null;

  public List<Map<String, Object>> boardList(Map<String, Object> pmap) {
    logger.info("==== BoardLogic 호출 ====");
    List<Map<String, Object>> list = new ArrayList<>();
    list = boardDao.boardList(pmap);
    return list;
  }
}
