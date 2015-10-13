package se.coredev.secure.web;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LeakingServlet extends HttpServlet {
	
	private static final long serialVersionUID = 5083486561122480754L;
	private Thread thread;

	@Override
	public void init(ServletConfig config) throws ServletException {
	
		thread = new Thread("Leaking thread") {
			public void run() {
				try {
					while (true) {
						// Simulates waiting for job processing
						Thread.sleep(Integer.MAX_VALUE);
					}
				}
				catch (InterruptedException e) {}
			}
		}; 
		thread.start();
	} 

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.getWriter().write("doGet() called on " + this.getClass().getSimpleName());
	}
}