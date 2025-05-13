package model;
import java.io.Serializable;

public class MailContents implements Serializable {
  private String name; 	// ご氏名
  private String mail; 	// メールアドレス
  private String title; // タイトル
  private String body; 	// お問い合わせ内容

  public MailContents() { 
	  this.name = "";
	  this.mail = "";
	  this.title = "";
	  this.body = "";
  }
  public MailContents(String name, String mail, String title, String body) {
	this.name = name;
	this.mail = mail;
	this.title = title;
	this.body = body;
  }
  public String getName() { return name; }
  public void setName(String name) { this.name = name; }
  public String getMail() { return mail; }
  public String getTitle() {return title; }
  public String getBody() { return body; }
}