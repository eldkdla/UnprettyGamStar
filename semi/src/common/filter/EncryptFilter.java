package common.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

import common.encrypt.EncryptWrapper;

@WebFilter(
		servletNames= {
				"PasswordModifyServlet",
				"ChkBeforePw",
				"UnregisterServlet",
				"UserLoginServlet",
				"UserEnrollServlet",
				"CreateNewAdminServlet"
		}
		)
public class EncryptFilter implements Filter {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletRequest request=(HttpServletRequest)req;
		
		EncryptWrapper encW=new EncryptWrapper(request);
		//System.out.println("filter : "+encW.getParameter("newPw"));
		//System.out.println("filter : "+encW.getParameter("beforePw"));
		chain.doFilter(encW, res);	

	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

}
