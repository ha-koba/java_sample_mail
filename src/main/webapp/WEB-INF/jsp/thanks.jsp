<%@ page language="java" contentType="text/html; charset=UTF-8" 
	pageEncoding="UTF-8" %>
<%@ page import="model.MailContents" %>
<%
// セッションスコープからお問い合わせ内容を取得
MailContents sendMailContents = (MailContents)request.getAttribute("sendMailContents");
%>
<!DOCTYPE html>
<html lang="ja">
<head>
	<meta charset="utf-8">
	<title>CONTACT</title>
	<link rel="stylesheet" href="css/styles.css">
	<!--[if lt IE 9]>
	<script src="/mt-static/support/theme_static/rainier/js/html5shiv.js"></script>
	<![endif]-->
</head>
<body>
<main class="page-main page-other">
	<header class="page-header-other">
		<h1>お問い合わせ完了</h1>
	</header>

	<div class="container">
		<div class="other-container">
			<p>この度は、お問い合わせありがとうございました。<br>
				<b><%= sendMailContents.getName() %>様</b>からのお問い合わせを受付けました。<br>
				内容を確認後に、ご返答させていただきます。<br>
				また、状況により、ご返答が数日、遅れてしまう場合がございますことをご了承ください。<br>
				今後ともどうぞよろしくお願いいたします。<br><br>
			</p>
			<div style="text-align: center;">
				<a href="Contact" class="contact-button">TOPページへ戻る</a>
			</div>
		</div>
	</div>
</main>
</body>
</html>