package com.gamstar.user.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.GoogleAuthentication;

/**
 * Servlet implementation class MailSendServlet
 */
@WebServlet("/mailSend")
public class MailSendServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MailSendServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
  //인증번호(난수발생)
  	public static String randomNum() {
  		StringBuffer buffer = new StringBuffer();
  		for (int i = 0; i <= 6; i++) {
  			int n = (int)(Math.random()*10);
  			buffer.append(n);
  		}
  		return buffer.toString();
  	}


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		//클라이언트 페이지에서 메일 전송에 사용하기 위해 전송되어온 파라미터 값들을 받는 부분.
		//String sender =request.getParameter("sender");//보내는사람
		String sender ="언프리티감스타<weby0603@gmail.com>";//보내는사람
		String receiver = request.getParameter("receiver");//받는사람
		//String subject = request.getParameter("subject");//제목
		String subject = "언프리티 감스타 인증번호입니다.";//제목
		//String content = request.getParameter("content");//보내질 내용
		String randomNo = randomNum();
		
		String content = "인증번호는 : [ "+randomNo+" ] 입니다.";//보내질 내용
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		
		try {
			//서버 정보를 Properties 객체에 저장.
			Properties properties = System.getProperties();
			//SMTP 서버를 저장.
			properties.put("mail.smtp.starttls.enable","true");
			//AUTH command를 사용해 사용자 인증을 할 수 있도록.
			properties.put("mail.smtp.host","smtp.gmail.com");
			properties.put("mail.smtp.auth", "true");
			//서버 포트를 지정하는 부분
			properties.put("mail.smtp.port","587");	//gmail 포트
			
			//인증 정보를 생성하는 부분.
			Authenticator auth = new GoogleAuthentication();
			//메일을 전송하는 역할을 하는 단위인 Session 객체를 생성하는 부분.
			Session s = Session.getDefaultInstance(properties, auth);
			//생성한 Session 객체를 사용해 전송할 Message 객체를 생성하는 부분.
			Message message = new MimeMessage(s);
			//메일을 송신할 송신 주소 생성 부분
			Address sender_address = new InternetAddress(sender);
			//메일을 수신할 수신 주소 생성 부분.
			Address receiver_address = new InternetAddress(receiver);
			
			message.setHeader("content-type", "text/html;charset=utf-8");
			message.addRecipient(Message.RecipientType.TO,receiver_address);
			message.setSubject(subject);
			message.setContent(content,"text/html;charset=utf-8");
			message.setSentDate(new java.util.Date());
			Transport.send(message);
			
			//out.println("<h3>메일이 정상적으로 전송되었습니다.</h3>");
			System.out.println("메일 보냈숑");
			System.out.println(randomNo);
			out.println(randomNo);
		}catch(Exception e){
			//out.println("SMTP 서버가 잘못 되었거나, 서비스에 문제가 있습니다.");
			out.println(false);
			System.out.println("이상한 주소로 보냈는데 들어와?");
			e.printStackTrace();
		}
		
		
		
	}


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
