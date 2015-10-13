package se.coredev.secure.web;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LeakingServlet2 extends HttpServlet {
	private static final long serialVersionUID = 5083486561122480754L;
	private ExecutorService executor;

	@Override
	public void init(ServletConfig config) throws ServletException {
		executor = Executors.newFixedThreadPool(20);
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		resp.getWriter().write("doGet() called on " + this.getClass().getSimpleName() + " starting runnable\n");
		
		executor.execute(new Runnable() {
			public void run() {
			}
		});
		
		resp.getWriter().write("Runnable started");
	}
}