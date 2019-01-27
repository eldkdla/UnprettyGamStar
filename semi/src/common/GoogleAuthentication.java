package common;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

public class GoogleAuthentication extends Authenticator{
	PasswordAuthentication passAuth;

	public GoogleAuthentication() {
		passAuth=new PasswordAuthentication("weby0603","trwimrmkmysybblk");

	}

	public PasswordAuthentication getPasswordAuthentication() {
		return passAuth;
	}
}