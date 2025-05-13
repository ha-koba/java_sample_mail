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

@WebServlet("/Contact")
public class Contact extends HttpServlet {
  private static final long serialVersionUID = 1L;
  
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	// 確認画面から戻る時に変数を初期化するため
	request.setAttribute("isError", true);
	
	// 入力途中のお問い合わせ情報が保存されているか確認
	HttpSession session = request.getSession();
	MailContents mailContents = (MailContents)session.getAttribute("confMailContents");

	if (mailContents == null) {
		// お問い合わせ情報がない場合は初期化
		mailContents = new MailContents();
		session.setAttribute("confMailContents", mailContents);
		request.setAttribute("isError", false);  // 初回は情報が入っていないことはエラーではないため警告を出さない
	}
	// お問い合わせ画面にフォワード 
	RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/contact.jsp");
	dispatcher.forward(request, response);
  }
	
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    // リクエストパラメータの取得
    request.setCharacterEncoding("UTF-8");
    String name = request.getParameter("name");
    String mail = request.getParameter("mail");
    String title = request.getParameter("title");
    String body = request.getParameter("body");
    
    // お問い合わせ情報インスタンスの生成
    // サーバー起動時に最初からページが読み込まれている可能性を考慮して既存のインスタンスを利用しない
    MailContents mailContents = new MailContents(name, mail, title, body);
    
    // お問い合わせ情報をセッションスコープに保存
    HttpSession session = request.getSession();
    session.setAttribute("confMailContents", mailContents);

    if (name == null || mail == null || title == null || body == null ||
    	name.isBlank() || mail.isBlank() || title.isBlank() || body.isBlank()) {
    	// 警告文を出力して確認画面に遷移させない
        // お問い合わせ画面にフォワード
    	request.setAttribute("isError", true);
        RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/contact.jsp");
        dispatcher.forward(request, response);
        return;
    }
	// 確認画面にフォワード
    request.setAttribute("isError", false);
	RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/confirm.jsp");
	dispatcher.forward(request, response);

  }
}