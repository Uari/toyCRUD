package com.example.demo.controller;

import com.example.demo.service.BoardLogic;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/board/*")
public class BoardController {
  Logger logger = LoggerFactory.getLogger(BoardController.class);

  @Autowired
  private BoardLogic boardLogic;

  // 조회만 하는 기능 구현
  @GetMapping("boardList")
  public String boardTest(@RequestParam Map<String, Object> pmap, Model model) {
    logger.info("=========== boardList 호출 =========");
    // 서비스 주입받음
    List<Map<String, Object>> list = new ArrayList<>();
    list = boardLogic.boardList(pmap);
    model.addAttribute("bList", list);
    return "forward:/board/boardList.jsp";
  }
}
