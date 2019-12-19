package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import domain.Member;
import repository.DAOImpl;
import repository.MemberDAOImpl;

/**
 * Servlet implementation class MemberController
 */
@WebServlet({"/member-login.do", "/member-register.do", "/member-list.do", "/member-info" ,"/member-delete.do", "/member-edit.do"})
public class MemberController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    // SQL 문을 이용하여 DB로부터 가져옴.
	private String userId = "ParkSM";
	private String userPw = "parkpark";
	private ArrayList<Member> modelList;
	private Connection conn = null;
	private DAOImpl daoImpl = new DAOImpl();

	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberController() {
        super();
        
        this.modelList = new ArrayList<Member>();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		System.out.println("Enter doGet method!!");
		doPost(request, response);
		
		// Encoding setting
		//request.setCharacterEncoding("UTF-8");
		
		// name 매개변수의 값을 get이라는 이름의 속성으로 request에 저장
		//request.setAttribute("get1", request.getParameter("id"));
	
		// request 객체 안에 있는 값들을 활용해서 처리한 후 전달.
		//request.getRequestDispatcher("index.jsp").forward(request,  response);
		
		/*
		PrintWriter out = response.getWriter();
		out.print("<html><head><title></title></head><body>");
		out.print("<h1>출력 " + request.getParameter("n") + "</h1>");
		out.print("</body></html>");
		response.getWriter().append("Served at: ").append(request.getContextPath());
		*/
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		System.out.println("Enter doPost method!!");
		
		// Encoding setting
		// Set korean encoding in post-method.
		request.setCharacterEncoding("UTF-8");
		// Request object is removed short time(a second).
		
		// Get uri
		String uri = request.getRequestURI();
		// last index of '/' on uri
		int index = uri.lastIndexOf("/");
		// Get urn from uri
		String urn = uri.substring(index + 1);
		
		HttpSession session = request.getSession();
		
		
		if (urn.equals("member-register.do")) {
			// Create Connection instance and get.
			
			// Receive from tag of input-type-text on login.jsp
			// Save value that renamed "get" for "name" parameter to request 
			String id = request.getParameter("id");
			String pw = request.getParameter("pw");
			String name = request.getParameter("name");
			String phone = request.getParameter("phone");
			String email = request.getParameter("email");
			String country = request.getParameter("country");
			
			Date now = new Date();
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			
			Member m = new Member(0, id, pw, name, phone, email, country, format.format(now), 0);
			
			// Create DAO instance.
			MemberDAOImpl dao = new MemberDAOImpl();
			//  Execute query.
			if (dao.create(m) > 0) {
				// Successfully case for create member.
				request.setAttribute("member_id", id);
			} else
				; // case for failed to create member.
			
			request.getRequestDispatcher("member-register-view.jsp").forward(request,  response);
			
		} else if (urn.equals("member-login.do")) {
				
			String id = request.getParameter("id");
			String pw = request.getParameter("password");
			
			System.out.println("id: " + id + "\tpw:" + pw);
			
			// Create DAO instance.
			MemberDAOImpl dao = new MemberDAOImpl();
			
			Member retMem;
			if ((retMem = dao.readList(id)) != null && retMem.getPw().equals(pw)) {
				String rank;
				if (retMem.getRank() == 0) rank = "general";
				else rank = "admin";
				
				session.setAttribute("login", "success");
				session.setAttribute("login_id", retMem.getId());
				session.setAttribute("login_rank", rank);
			} else session.setAttribute("login", "fail");
			
			// Process and pass the values ​​of the request object.
			request.getRequestDispatcher("confirmation.jsp").forward(request,  response);
			
		} else if (urn.equals("member-list.do")) {
			String sortMethod = "0";
			if (request.getParameter("sortMethod") != null) {
				sortMethod = request.getParameter("sortMethod");
			}
			String search = "";
			if (request.getParameter("search") != null) {
				search = request.getParameter("search");
			}
			
			MemberDAOImpl dao = new MemberDAOImpl();
			if ((modelList = dao.selectAll(sortMethod, search)) != null) {
				request.setAttribute("memberlist", modelList);
			}
			request.getRequestDispatcher("member-list.jsp").forward(request, response);
			
		} else if (urn.equals("member-info")) {
			
			String id = request.getParameter("id");
			
			System.out.println("id: " + id);
			
			// Create DAO instance.
			MemberDAOImpl dao = new MemberDAOImpl();
			
			Member retModel;
			if ((retModel = dao.readList(id)) != null) {
				request.setAttribute("login", "success");
				request.setAttribute("model_info", retModel);

			} else request.setAttribute("model_info", null);
			
			// Process and pass the values ​​of the request object.
			request.getRequestDispatcher("member-info.jsp").forward(request,  response);
			
		} else if (urn.equals("member-delete.do")) {
			
			String id = request.getParameter("id");
			
			System.out.println("id: " + id);
			
			// Create DAO instance.
			new MemberDAOImpl().delete(id);
			session.setAttribute("login", "fail");
			
			// Process and pass the values ​​of the request object.
			request.getRequestDispatcher("login.jsp").forward(request,  response);
			
		} else if (urn.equals("member-edit.do")) {
			
			System.out.println("id: " + request.getParameter("id"));
			
			Member model = new Member(Integer.parseInt(request.getParameter("pid")), request.getParameter("id"),
					request.getParameter("pw"), request.getParameter("name"), request.getParameter("phone"),
					request.getParameter("email"), request.getParameter("country"), null, 0);
			
			// Create DAO instance.
			new MemberDAOImpl().update(model);
	
			// Process and pass the values ​​of the request object.
			request.getRequestDispatcher("index.jsp").forward(request,  response);
			
		}
			
	}
}


