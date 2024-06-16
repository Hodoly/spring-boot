$("[name='send']").off().on("click", function() {
	var subject = "sbb 회원인증번호 입니다.";
	var body = "안녕하세요 sbb 입니다. \n 인증번호 : ";
	var username = $("[id='username']").val();

	var params = {
		username: username
		, subject: subject
		, body: body
	}

	var token = $("meta[name='_csrf']").attr("content");
	var header = $("meta[name='_csrf_header']").attr("content");
	$(function() {
		$(document).ajaxSend(function(e, xhr, options) {
			xhr.setRequestHeader(header, token);
		});
	});
	$.ajax({
		url: "/user/email"
		, beforeSend: function(xhr) {
			xhr.setRequestHeader(header, token);
		}
		, type: "POST"
		, contentType: "application/json; charset=utf-8"
		, data: JSON.stringify(params)
		, dataType: "json"
		, success: function(data) {
			alert("이메일 전송에 성공하였습니다.");
			$("[name='username']").attr("readonly", true)
		},

		error: function(jqXHR, textStatus, errorThrown) {
			alert("이메일 전송에 실패하였습니다.");
		}
	});
});

$("[name='authcheck']").off().on("click", function() {

	var token = $("meta[name='_csrf']").attr("content");
	var header = $("meta[name='_csrf_header']").attr("content");
	var username = $("[id='username']").val();
	var auth = $("[id='auth']").val();

	var params = {
		username: username
		, auth: auth
	}

	$(function() {
		$(document).ajaxSend(function(e, xhr, options) {
			xhr.setRequestHeader(header, token);
		});
	});
	$.ajax({
		url: "/user/auth"
		, beforeSend: function(xhr) {
			xhr.setRequestHeader(header, token);
		}
		, type: "POST"
		, contentType: "application/json; charset=utf-8"
		, data: JSON.stringify(params)
		, dataType: "json"
		, success: function(data) {
			alert("이메일 전송에 성공하였습니다.");
		},

		error: function(jqXHR, textStatus, errorThrown) {
			alert("인증에 실패하였습니다. 인증번호를 다시 입력해주세요.");
		}
	});
});