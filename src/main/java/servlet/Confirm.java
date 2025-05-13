package servlet;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import model.MailContents;
import model.TestJavaMail;

@WebServlet("/Confirm")
public class Confirm extends HttpServlet {
  private static final long serialVersionUID = 1L;
  
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	// フォワード先を設定
	String forwardPath = null;
	
	// サーブレットクラスの動作を決定する「action」の値をリクエストパラメータから取得
	String action = request.getParameter("action");
	
	// 「送信内容の確認」をリクエストされた時の処理
	if (action == null) {
		// フォワード先を設定
		forwardPath = "WEB-INF/jsp/confirm.jsp";
	}
	// 送信確認画面から「送信実行」をリクエストされた時の処理
	else if (action.equals("done")) {
	    // セッションスコープに保存されたお問い合わせ情報を取得
	    request.setCharacterEncoding("UTF-8");
	    HttpSession session = request.getSession();
	    MailContents confMailContents = (MailContents)session.getAttribute("confMailContents");
	    
	    // お問い合わせ情報を送信
	    TestJavaMail testJavaMail = new TestJavaMail();
	    testJavaMail.execute(confMailContents);
	    
	    // 送信者の情報を格納
	    String confName = confMailContents.getName();
	    MailContents sendMailContents = new MailContents();
	    sendMailContents.setName(confName);
	    
	    // リクエストスコープに送信内容を保存する
	    request.setAttribute("sendMailContents", confMailContents);
	    
	    // セッションスコープの情報を削除する
	    session.removeAttribute("confMailContents");

	    // フォワード先を設定
	    forwardPath = "Thanks";
	}
	else {
		System.out.println("Confirm.java: 期待されない結果です");
	}
	// フォワード
	RequestDispatcher dispatcher = request.getRequestDispatcher(forwardPath);
	dispatcher.forward(request, response);
  }
}