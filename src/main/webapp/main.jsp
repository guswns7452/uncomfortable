<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">

<head>
<link
	href="https://cdnjs.cloudflare.com/ajax/libs/flowbite/1.6.6/flowbite.min.css"
	rel="stylesheet" />
<link href="<c:url value="/resource/main.css"/>" rel="stylesheet" />

<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link
	href="https://fonts.googleapis.com/css2?family=Oswald:wght@700&display=swap"
	rel="stylesheet">


</head>


<body>
	<nav class="bg-white border-gray-200 dark:bg-gray-900">
		<div
			class="max-w-screen-xl flex flex-wrap items-center justify-between mx-auto p-4">
			<a href="/uncomfortable/board" class="flex items-center"> <!-- ${pageContext.request.contextPath}/resources/views/CP_CoP_front/icon -->
				<div class="font-bold text-2xl" style="font-family: 'Oswald';">Uncomfortable
					University</div>
			</a>
			<div class="flex items-center md:order-2">
				<!-- 로그인 버튼 -->
				<c:choose>
					<c:when test="${sessionScope.user eq null}">
						<a href="/uncomfortable/user/login">
							<button type="button"
								class="text-white bg-gradient-to-br from-pink-500 to-orange-400 hover:bg-gradient-to-bl focus:ring-4 focus:outline-none focus:ring-pink-200 dark:focus:ring-pink-800 font-medium rounded-lg text-sm px-5 py-2.5 text-center mr-2 mb-2">로그인</button>
						</a>
					</c:when>
					<c:otherwise>
						<button type="button"
							class="flex mr-3 text-sm bg-gray-800 rounded-full md:mr-0 focus:ring-4 focus:ring-gray-300 dark:focus:ring-gray-600"
							id="user-menu-button" aria-expanded="false"
							data-dropdown-toggle="user-dropdown"
							data-dropdown-placement="bottom">
							<span class="sr-only">Open user menu</span> <img
								class="w-8 h-8 rounded-full background_color_white"
								src="<c:url value="/resource/OtterDog.jpg"/>" alt="user photo">
						</button>
					</c:otherwise>
				</c:choose>
				<!-- Dropdown menu -->
				<div
					class="z-50 hidden my-4 text-base list-none bg-white divide-y divide-gray-100 rounded-lg shadow dark:bg-gray-700 dark:divide-gray-600"
					id="user-dropdown">
					<div class="px-4 py-3">
						<span class="block font-bold text-gray-900 dark:text-white">${user.id}</span>
						<span class="block text-sm text-gray-900 dark:text-white">${user.userName}</span>
						<span
							class="block text-sm  text-gray-500 truncate dark:text-gray-400">${user.studentId}</span>
					</div>
					<ul class="py-2" aria-labelledby="user-menu-button">
						<li><a href="/uncomfortable/user/mypage"
							class="block px-4 py-2 text-sm text-gray-700 hover:bg-gray-100 dark:hover:bg-gray-600 dark:text-gray-200 dark:hover:text-white">Mypage</a>
						</li>
						<form class="hover:bg-gray-100 dark:hover:bg-gray-600"
							action="/uncomfortable/user/logout.do" method="post">
							<button type="submit"
								class="block px-4 py-2 text-sm text-gray-700 hover:bg-gray-100 dark:hover:bg-gray-600 dark:text-gray-200 dark:hover:text-white">
								Log out</button>
						</form>
					</ul>
				</div>
				<button data-collapse-toggle="navbar-user" type="button"
					class="inline-flex items-center p-2 w-10 h-10 justify-center text-sm text-gray-500 rounded-lg md:hidden hover:bg-gray-100 focus:outline-none focus:ring-2 focus:ring-gray-200 dark:text-gray-400 dark:hover:bg-gray-700 dark:focus:ring-gray-600"
					aria-controls="navbar-user" aria-expanded="false">
					<span class="sr-only">Open main menu</span>
					<svg class="w-5 h-5" aria-hidden="true"
						xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 17 14">
                        <path stroke="currentColor"
							stroke-linecap="round" stroke-linejoin="round" stroke-width="2"
							d="M1 1h15M1 7h15M1 13h15" />
                    </svg>
				</button>
			</div>
			<!--  -->
			<div
				class="alignCenter items-center justify-between hidden w-full md:flex md:w-auto md:order-1"
				id="navbar-user">
				<ul style="margin-top: 0px;"
					class="flex flex-col font-medium p-4 md:p-0 mt-4 border border-gray-100 rounded-lg bg-gray-50 md:flex-row md:space-x-8 md:mt-0 md:border-0 md:bg-white dark:bg-gray-800 md:dark:bg-gray-900 dark:border-gray-700">
					<li><a href="/uncomfortable/board"
						class="block py-2 pl-3 pr-4 text-white bg-blue-700 rounded md:bg-transparent md:text-blue-700 md:p-0 md:dark:text-blue-500"
						aria-current="page">Home</a></li>
					<li><a href="/uncomfortable/board/top"
						class="block py-2 pl-3 pr-4 text-gray-900 rounded hover:bg-gray-100 md:hover:bg-transparent md:hover:text-blue-700 md:p-0 dark:text-white md:dark:hover:text-blue-500 dark:hover:bg-gray-700 dark:hover:text-white md:dark:hover:bg-transparent dark:border-gray-700">Top
							10</a></li>
					<li><a href="/uncomfortable/user/mypage"
						class="block py-2 pl-3 pr-4 text-gray-900 rounded hover:bg-gray-100 md:hover:bg-transparent md:hover:text-blue-700 md:p-0 dark:text-white md:dark:hover:text-blue-500 dark:hover:bg-gray-700 dark:hover:text-white md:dark:hover:bg-transparent dark:border-gray-700">마이페이지</a>
					</li>
				</ul>
			</div>
		</div>
	</nav>

	<div class="banner">
		<h1>Uncomfortable University</h1>
		<p>대학 생활을 하면서 불편한 점을 서로 나누어 보세요!</p>
	</div>
	<!-- 여기부터 수정 및 추가 -->
	<div class="mt-4 text-center">
		<label for="comment" class="block fontShare font-medium text-gray-700">📢
			여러분들의 의견을 공유해 보세요!</label>

		<form class="submitForm" action="/uncomfortable/board/write.do"
			method="post">
			<input type="text" id="comment" name="content"
				class="mt-1 p-2 border rounded-md content"></input>

			<!-- 의견 등록 버튼 추가 -->
			<button type="button"
				class="mt-2 p-2 bg-blue-500 text-white rounded-md"
				onclick="submitComment()">의견 등록</button>
		</form>
	</div>

	<div class="text-3xl font-bold" style="text-align:center; margin-top:100px;">글 둘러보기</div>
	
	<div class="Boards">
		<c:forEach items="${boardList}" var="board" varStatus="i">
			<div id="output-box1" class="relative">
				<label for="output-text"
					class="block text-lg font-bold text-gray-700"
					style="position: absolute; top: 10px; left: 15px;">익명
					${board.boardNumber}</label>
				<div id="output-text"
					class="mt-1 p-2 border rounded-md overflow-auto"
					style="position: relative; top: 40px; left: 5px; max-height: 150px;">${board.content}</div>

				<!-- 좋아요와 싫어요 버튼 -->
				<div
					class="flex mt-2 absolute bottom-0 left-0 right-0 justify-center"
					style="margin-bottom: 10px;">
					<form action="/uncomfortable/board/like.do" method="post">
						<input class="boardNumber" type="hidden" name="boardNumber"
							value="${board.boardNumber}">
						<button onclick="likeComment(${board.boardNumber})" type="button"
							name="likeNumber" value="${board.likeNumber}"
							class="mr-2 p-2 bg-green-500 text-white rounded-md likeNumber">좋아요
							${board.likeNumber}</button>
					</form>
					<form action="/uncomfortable/board/dislike.do" method="post">
						<input type="hidden" name="boardNumber"
							value="${board.boardNumber}">
						<button onclick="dislikeComment(${board.boardNumber})"
							type="button" name="dislikeNumber" value="${board.dislikeNumber}"
							class="p-2 bg-red-500 text-white rounded-md dislikeNumber">싫어요
							${board.dislikeNumber}</button>
					</form>
				</div>
			</div>
		</c:forEach>
	</div>
	<!-- 출력 박스 -->


	<script>
    // 좋아요 버튼 클릭 시 동작
    function likeComment(boardNumber) {
    	alert ('좋아요 반영되었습니다!');
    	
        // 서버로 좋아요 클릭을 전송
        fetch('/uncomfortable/board/like.do', { 
        	method: 'POST',
        	 cache: 'no-cache',
        	    headers: {
        	        'Content-Type': 'application/x-www-form-urlencoded'
        	    },
        	    body: 'boardNumber='+document.querySelectorAll(".boardNumber")[boardNumber-1].value+'&likeNumber='+document.querySelectorAll(".likeNumber")[boardNumber-1].value
        	})
            .then(response => response.json());    
            
        location.reload();
    }

    // 싫어요 버튼 클릭 시 동작
    function dislikeComment(boardNumber) {
        // 서버로 싫어요 클릭을 전송
		alert (' 싫어요 반영되었습니다!');
    	
        // 서버로 좋아요 클릭을 전송
        fetch('/uncomfortable/board/dislike.do', { 
        	method: 'POST',
        	 cache: 'no-cache',
        	    headers: {
        	        'Content-Type': 'application/x-www-form-urlencoded'
        	    },
        	    body: 'boardNumber='+document.querySelectorAll(".boardNumber")[boardNumber-1].value+'&dislikeNumber='+document.querySelectorAll(".dislikeNumber")[boardNumber-1].value
        	})
            .then(response => response.json());    
            
        location.reload();
    }

    function submitComment() {
        // 백엔드에서 가져올 텍스트 대신에 임시로 "텍스트"를 표시
        if(document.querySelector(".content").value == ''){
        	alert('의견을 작성해주세요.')
        }
        else {
        	alert ('의견 등록했습니다!');
        	
            // 서버로 좋아요 클릭을 전송
            fetch('/uncomfortable/board/write.do', { 
            	method: 'POST',
            	 cache: 'no-cache',
            	    headers: {
            	        'Content-Type': 'application/x-www-form-urlencoded'
            	    },
            	    body: 'content='+document.querySelector(".content").value
            	})
                .then(response => response.json());    
                
            location.reload();
        }
		
    }
</script>

	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/flowbite/1.7.0/flowbite.min.js"></script>