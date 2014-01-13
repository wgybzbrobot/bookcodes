package cc.pp.account.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import cc.pp.account.service.AccountService;
import cc.pp.account.service.AccountServiceException;

public class LoginServlet extends HttpServlet {

	private static final long serialVersionUID = -3790333835308318155L;

	private ApplicationContext context;

	@Override
	public void init() throws ServletException {
		super.init();
		context = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

		String id = req.getParameter("id");
		String password = req.getParameter("paddword");

		if (id == null || id.length() == 0 || password == null || password.length() == 0) {
			resp.sendError(400, "incomplete parameter");
			return;
		}

		AccountService service = (AccountService) context.getBean("accountService");

		try {
			service.login(id, password);
			resp.getWriter().print("Login Successfil!");
		} catch (AccountServiceException e) {
			resp.sendError(400, e.getMessage());
		}
	}

}
