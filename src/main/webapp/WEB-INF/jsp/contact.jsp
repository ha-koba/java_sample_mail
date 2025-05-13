<%@ page language="java" contentType="text/html; charset=UTF-8" 
	pageEncoding="UTF-8" %>
<%@ page import="model.MailContents" %>
<%
// セッションスコープからお問い合わせ内容を取得
MailContents confMailContents = (MailContents)session.getAttribute("confMailContents");
boolean isError = (boolean)request.getAttribute("isError");
String name = "", mail = "", title = "", body = "";
try {
	name = confMailContents.getName();
} catch (java.lang.NullPointerException e) {
	System.out.println("name: " + e);
}
try {
	mail = confMailContents.getMail();
} catch (java.lang.NullPointerException e) {
	System.out.println("mail: " + e);
}
try {
	title = confMailContents.getTitle();
} catch (java.lang.NullPointerException e) {
	System.out.println("title: " + e);
}
try {
	body = confMailContents.getBody();
} catch (java.lang.NullPointerException e) {
	System.out.println("body: " + e);
}
System.out.println("name: " + name + ", mail: " + mail + ", title: " + title + ", body: " + body);
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
<main class="page-main page-form page-contact">
	<header class="page-header-contact">
		<h1>CONTACT</h1>
	</header>

	<div class="container">
		<form class="form-container" action="Contact" method="post">
			<div class="form-table">
				<section class="form-table-row form-table-name">
					<h2>ご氏名 <span class="required">必須</span></h2>
					<div class="form-table-col">
						<input class="input" type="text" name="name" placeholder="" value="<%= name %>">
						<% if (name=="" && isError) { %>
						<p><span style="color:#ff0000;">お名前は必須項目となります。</span></p>
						<% } %>
					</div>
				</section>

				<section class="form-table-row form-table-email">
					<h2>メールアドレス <span class="required">必須</span></h2>
					<div class="form-table-col">
						<input class="input" type="text" name="mail" placeholder="" value="<%= mail %>">
						<% if (mail=="" && isError) { %>
						<p><span style="color:#ff0000;">メールアドレスは必須項目となります。</span></p>
						<% } %>
					</div>
				</section>

				<section class="form-table-row form-table-company-name">
					<h2>タイトル <span class="required">必須</span></h2>
					<div class="form-table-col">
						<input class="input" type="text" name="title" placeholder="" value="<%= title %>">
						<% if (title=="" && isError) { %>
						<p><span style="color:#ff0000;">タイトルは必須項目となります。</span></p>
						<% } %>
					</div>
				</section>

				<section class="form-table-row form-table-pr">
					<h2>お問い合わせ内容 <span class="required">必須</span></h2>
					<div class="form-table-col">
						<textarea class="textarea" placeholder="1,000文字以内でご記入ください" maxlength="1000" name="body"><%= body %></textarea>
						<% if (body=="" && isError) { %>
						<span style="color:#ff0000;">お問い合わせ内容は必須項目となります。</span>
						<% } %>
					</div>
				</section>
			</div>

			<p />

			<div style="text-align: center;">
				<button type="submit" class="form-button" name="edit"><span>確認画面へ</span></button>
			</div>
		</form>
	</div>
</main>
</body>
</html>