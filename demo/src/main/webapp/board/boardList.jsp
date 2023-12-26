<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*, com.google.gson.Gson" %>
<%
	int size = 0;//전체 레코드 수
	List<Map<String,Object>> bList = (List)request.getAttribute("bList");
	if(bList !=null){
		size = bList.size();
	}
	//out.print(bList);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>boardList {WEBAPP}</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<%@include file="/common/bootstrap_common.jsp"%>
<link rel="stylesheet" href="/css/board.css">
<script type="text/javascript">
	const boardList = () =>{
		location.href = "/board/boardList";
	}
	const searchEnter = (event) => {
		if (window.event.keyCode == 13) {
		console.log('searchEnter')
		console.log(window.event.keyCode);
		boardSearch();
	    }
	}
	
	const  boardSearch = () =>{
		console.log('boardSearch');
		const gubun = document.querySelector("#gubun").value;
		const keyword = document.querySelector("#keyword").value;
		console.log(`${gubun} , ${keyword}`);
		location.href="/board/boardList?gubun="+gubun+"&keyword="+keyword;
	}
	const boardInsert = () =>{
		console.log("insert 저장 버튼 클릭 ")
		//input type = text name=b_tile
		//input type = text name=b_content
		//input type = text name=b_writer
		//Enumeration<String> em = req.getParameterNames();
		document.querySelector('#f_board').submit();//form태그에 묶인 componunt값들이 전송된다.
	}
	
	const boardUpdate = () =>{
		//input type = text name=b_tile
		//input type = text name=b_content
		//input type = text name=b_writer
		//Enumeration<String> em = req.getParameterNames();
		document.querySelector('#f_boardUpdate').submit();//form태그에 묶인 componunt값들이 전송된다.
	}
	
	const fileDown = (b_file) =>{//b_file  = image.png
		location.href = "downLoad.jsp?b_file="+b_file;
	}

	const boardDelete = (b_no) =>{
		console.log('boardSearch');
		location.href="/board/boardDelete?b_no="+b_no;
	}

	const transValues = (pmap) =>{
		let mmap = pmap.replace(/\'/g, '\"');
		mmap = JSON.parse(mmap);
		$('#b_no').val(mmap.b_no);
		$('label[for="b_title"]').text(mmap.b_title);
		$('label[for="b_writer"]').text(mmap.b_writer);
		$('#b_contentUpdate').text(mmap.b_content);
	}
	
	</script>
</head>
<body>
	<!-- header start -->
	<%@include file="/include/gym_header.jsp"%>
	<!-- header end    -->
	<!-- body start    -->
	<div class="container">
		<div class="page-header">
			<h2>
				게시판 <small>게시글목록</small>
			</h2>
			<hr />
		</div>
		<!-- 검색기 시작 -->
		<div class="row">
			<div class="col-3">
				<select id="gubun" class="form-select" aria-label="분류선택">
					<option value="none">분류선택</option>
					<option value="b_title">제목</option>
					<option value="b_writer">작성자</option>
					<option value="b_content">내용</option>
				</select>
			</div>
			<div class="col-6">
				<input type="text" id="keyword" class="form-control"
					placeholder="검색어를 입력하세요" aria-label="검색어를 입력하세요"
					aria-describedby="btn_search" onkeyup="searchEnter()" />
			</div>
			<div class="col-3">
				<button id="btn_search" class="btn btn-danger"
					onclick="boardSearch()">검색</button>
			</div>
		</div>
		<!-- 검색기 끝 -->

		<!-- 회원목록 시작 -->
		<div class='board-list'>
			<table class="table table-hover">
				<thead>
					<tr>
						<th width="10%">#</th>
						<th width="40%">제목</th>
						<th width="20%">첨부파일</th>
						<th width="15%">작성자</th>
						<th width="15%">조회수</th>
					</tr>
				</thead>
				<tbody>
				<%
					for(int i=0;i<size;i++){
						Map<String,Object> rmap = bList.get(i);
							request.setAttribute("pmap", rmap);
				%>					
									<tr>
										<td><%=rmap.get("B_NO")%></td>
										<td><%=rmap.get("B_TITLE") %></td>
										<td><%=rmap.get("B_FILE") %></td>
										<td><%=rmap.get("B_WRITER") %></td>
										<td><%=rmap.get("B_HIT") %></td>
										<td>
											<button id="btn_update" class="btn btn-success" data-bs-toggle="modal"
											data-bs-target="#boardUpdateForm" onclick="transValues(`{'b_no':'${pmap.B_NO}','b_title':'${pmap.B_TITLE}','b_writer':'${pmap.B_WRITER}','b_content':'${pmap.B_CONTENT}'}`)">수정</button>
										</td>
										<td>
											<button id="btn_delete" class="btn btn-danger" onclick="boardDelete(`${pmap.B_NO}`)">삭제</button>
										</td>
									</tr>		
				<%			
					}
				%>
					
				</tbody>
			</table>
			<hr />
			<!-- [[ Bootstrap 페이징 처리  구간  ]] -->
			<div style="display: flex; justify-content: center;">
				<ul class="pagination">

				</ul>
			</div>
			<!-- [[ Bootstrap 페이징 처리  구간  ]] -->
			<div class='board-footer'>
				<button class="btn btn-warning" onclick="boardList()">전체조회
				</button>
				&nbsp;
				<button type="button" class="btn btn-success" data-bs-toggle="modal"
					data-bs-target="#boardForm">글쓰기</button>
			</div>
		</div>
		<!-- 회원목록   끝  -->

	</div>
	<!-- body end       -->
	<!-- footer start -->
	<%@include file="/include/gym_footer.jsp"%>

	<!-- ========================== [[ 게시판 Modal ]] ========================== -->
	<div class="modal" id="boardForm">
		<div class="modal-dialog modal-dialog-centered">
			<div class="modal-content">

				<!-- Modal Header -->
				<div class="modal-header">
					<h4 class="modal-title">게시판</h4>
					<button type="button" class="btn-close" data-bs-dismiss="modal"></button>
				</div>
				<!-- Modal body -->
				<div class="modal-body">
					<!-- <form id="f_board" method="get" action="./boardInsert.pj2"> -->
					<form id="f_board" method="post" enctype="multipart/form-data"
						action="./boardInsert">
						<input type="hidden" name="method" value="boardInsert">
						<div class="form-floating mb-3 mt-3">
							<input type="text" class="form-control" id="b_title"
								name="b_title" placeholder="Enter 제목" /> <label for="b_title">제목</label>
						</div>
						<div class="form-floating mb-3 mt-3">
							<input type="text" class="form-control" id="b_writer"
								name="b_writer" placeholder="Enter 작성자" /> <label
								for="b_writer">작성자</label>
						</div>
						<div class="form-floating mb-3 mt-3">
							<textarea rows="5" class="form-control h-25"
								aria-label="With textarea" id="b_content" name="b_content"></textarea>
						</div>
						<div class="input-group mb-3">
							<input type="file" class="form-control" id="b_file" name="b_file">
							<label class="input-group-text" for="b_file">Upload</label>
						</div>
					</form>
				</div>
				<div class="modal-footer">
					<input type="button" class="btn btn-warning"
						data-bs-dismiss="modal" onclick="boardInsert()" value="저장">
					<input type="button" class="btn btn-danger" data-bs-dismiss="modal"
						value="닫기">
				</div>

			</div>
		</div>
	</div>
	<!-- ========================== [[ 게시판 Modal ]] ========================== -->
	<!-- ========================== [[ 게시판 Update Modal ]] ========================== -->
	<div class="modal" id="boardUpdateForm">
		<div class="modal-dialog modal-dialog-centered">
			<div class="modal-content">

				<!-- Modal Header -->
				<div class="modal-header">
					<h4 class="modal-title">게시판 수정</h4>
					<button type="button" class="btn-close" data-bs-dismiss="modal"></button>
				</div>
				<!-- Modal body -->
				<div class="modal-body">
					<form id="f_boardUpdate" method="post" enctype="multipart/form-data"
						action="./boardUpdate">
						<input type="hidden" name="method" value="boardUpdate">
						<input type="hidden" id ="b_no" name="b_no" value="0">
						<div class="form-floating mb-3 mt-3">
							<input type="text" class="form-control" id="b_title"
								name="b_title" placeholder="Enter 제목" /><label for="b_title">제목</label> 
						</div>
						<div class="form-floating mb-3 mt-3">
							<input type="text" class="form-control" id="b_writerUpdate"
								name="b_writer" placeholder="Enter 작성자" /> <label
								for="b_writer">작성자</label>
						</div>
						<div class="form-floating mb-3 mt-3">
							<textarea rows="5" class="form-control h-25"
								aria-label="With textarea" id="b_contentUpdate" name="b_content"></textarea>
						</div>
						<div class="input-group mb-3">
							<input type="file" class="form-control" id="b_fileUpdate" name="b_file">
							<label class="input-group-text" for="b_file">Upload</label>
						</div>
					</form>
				</div>
				<div class="modal-footer">
					<input type="button" class="btn btn-warning"
						data-bs-dismiss="modal" onclick="boardUpdate()" value="저장">
					<input type="button" class="btn btn-danger" data-bs-dismiss="modal"
						value="닫기">
				</div>

			</div>
		</div>
	</div>
	<!-- ========================== [[ 게시판 Update Modal ]] ========================== -->

</body>
</html>