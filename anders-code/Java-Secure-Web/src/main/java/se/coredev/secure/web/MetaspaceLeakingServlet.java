package se.coredev.secure.web;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nu.xom.Builder;
import nu.xom.Document;
import nu.xom.Node;

@SuppressWarnings("serial")
public class MetaspaceLeakingServlet extends HttpServlet
{

	private static final Level leakingLevel = new Level("LEAKING", 500) {
		{
			try
			{
				Builder parser = new Builder();
				Document doc = parser.build("http://feeds.dzone.com/dzone/frontpage?format=xml");
				process(doc.getRootElement().getChild(1));
			} catch (Exception e){}
		}  
		  
		void process(Node node)
		{ 
			for (int i = 0; i < node.getChildCount(); i++)
			{
				process(node.getChild(i));
			} 
		}
	};

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		Logger.getLogger(this.getClass().getSimpleName()).log(leakingLevel, "doGet called");

		resp.getWriter().write("doGet() called on " + this.getClass().getSimpleName());
	}

}
