package com.example.demo.controller;

import com.example.demo.service.BoardLogic;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;

//1. 컨트롤러
@Controller
@RequestMapping("/board/*") // 2. 첫번째 분류
public class BoardController {
  Logger logger = LoggerFactory.getLogger(BoardController.class);

  // 4.1 서비스 주입받음
  @Autowired
  private BoardLogic boardLogic;

  // 3. 조회만 하는 기능 구현
  @GetMapping("boardList")
  public String boardTest(@RequestParam Map<String, Object> pmap, Model model) {
    // @RequestParam : 브라우저에서 데이터를 받아오고싶다!
    // Model : 브라우저로 데이터를 보낼거다!!
    logger.info("=========== boardList 호출 =========");

    List<Map<String, Object>> list = new ArrayList<>();

    // 4. Logic단 호출
    list = boardLogic.boardList(pmap);
    // 11.브라우저로 DB에서 가져온 데이터 보내기
    model.addAttribute("bList", list);
    /*
     * Gson g = new Gson();
     * g.toJson(g)
     */
    // 12. forward방식으로 화면 URL로
    return "forward:/board/boardList.jsp";
  }
  @GetMapping("boardDetail")
  public String boardDetail(@RequestParam Map<String, Object> pmap, Model model) {
    // @RequestParam : 브라우저에서 데이터를 받아오고싶다!
    // Model : 브라우저로 데이터를 보낼거다!!
    logger.info("=========== boardDetail 호출 =========");

    List<Map<String, Object>> list = new ArrayList<>();

    // 4. Logic단 호출
    list = boardLogic.boardList(pmap);
    // 11.브라우저로 DB에서 가져온 데이터 보내기
    model.addAttribute("bList", list);
    /*
     * Gson g = new Gson();
     * g.toJson(g)
     */
    // 12. forward방식으로 화면 URL로
    return "forward:/board/boardDetail.jsp";
  }

  @PostMapping("boardInsert")
  public String boardInsert(@RequestParam Map<String, Object> pmap) {
    logger.info("==== boardIsert 호출 ====");
    int result = 0;
    logger.info(pmap.toString());
    result = boardLogic.boardInsert(pmap);
    return "redirect:/board/boardList";
  }

  @PostMapping("boardUpdate")
  public String boardUpdate(@RequestParam Map<String, Object> pmap) {
    logger.info("==== boardUpdate 호출 ====");
    int result = 0;
    logger.info(pmap.toString());
    result = boardLogic.boardUpdate(pmap);
    return "redirect:/board/boardList";
  }

  @GetMapping("boardDelete")
  public String boardDelete(@RequestParam Map<String, Object> pmap) {
    logger.info("==== boardDelete 호출 ====" + pmap);
    int result = 1;
    String test = null;
    result = boardLogic.boardDelete(pmap);
    return "redirect:/board/boardList";
  }



}
