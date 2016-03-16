package ctrl;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.SIS;
import model.StudentBean;

/**
 * Servlet implementation class Start
 */
@WebServlet("/Start")
public class Start extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    @Override
	public void init() throws ServletException
	{
		super.init();
		try
		{
			this.getServletContext().setAttribute("SIS", new SIS());
		}
		catch(Exception e)
		{
			e.printStackTrace();
		//	throw new ServletException("Error initializing recording system application");
		}
	}

	/**
     * @see HttpServlet#HttpServlet()
     */
    public Start() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("There is a request");
		HttpSession sn = request.getSession();
		List<StudentBean> sBeans;
		List<StudentBean> syears;

		
		//out.println("hello");
		String n, g, s, y;
		n = request.getParameter("lastName");
		g = request.getParameter("gpa");
		s = request.getParameter("sortBy");
		y = request.getParameter("year");
		
		System.out.println(y);
		System.out.println("*");
		
		if (n == null)
		{
			n = (String) sn.getAttribute("lastName");
		}
		if (g == null)
		{
			g = (String) sn.getAttribute("gpa");
		}
		if (s == null)
		{
			s = (String) sn.getAttribute("sortBy");
		}
		
		sn.setAttribute("lastName",n);
		sn.setAttribute("gpa",g);
		sn.setAttribute("sortBy",s);
		
		request.setAttribute("lastName",n);
		request.setAttribute("gpa",g);
		request.setAttribute("sortBy",s);
		
		
		
		SIS sis = (SIS) this.getServletContext().getAttribute("SIS");
		
		try
		{
			syears = sis.retrieveyear();
			request.setAttribute("syears", syears);
			
		} catch (Exception e)
		{
			e.printStackTrace();
			String errorMsg = e.getMessage();
			//System.out.println(errorMsg);
			request.setAttribute("error",errorMsg);
			
		}
		
		
		String jspTarget = "/Form.jspx";
		
		if(request.getParameter("report")!=null)
		{
			try
			{	

				sBeans = sis.retrieve(n, g, y, s);
				request.setAttribute("sBeans", sBeans);
				jspTarget = "/Form.jspx";
				
			} catch (Exception e)
			{
				request.setAttribute("error", e);
				jspTarget = "/Form.jspx";
				
			}
		}
		else if(request.getParameter("generate")!=null)
		{
			try
			{
				//String filename = this.getServletContext().getRealPath("/export/" + sn.getId() + ".xml");
				request.setAttribute("done", "/export/" + sn.getId() + ".xml");
				sis.export(n, g, y, s, this.getServletContext().getRealPath("/export/" + sn.getId() + ".xml"));
				jspTarget = "/Done.jspx";
			} catch (Exception e)
			{
				request.setAttribute("error", e);
				jspTarget = "/Form.jspx";
				
			}
		}
		request.getRequestDispatcher(jspTarget).forward(request,response);
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}

}
