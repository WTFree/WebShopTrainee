package Pages;


import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import models.user.User;
import models.user.DAO.JDBCUserDAO;



@WebServlet()
public class RegistrationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		String login = request.getParameter("login");
		String password = request.getParameter("password");
		String confirmPassword = request.getParameter("conf_password");
		User user = new User(login,password);
		JDBCUserDAO dao = new JDBCUserDAO();
		boolean signUP = false;
		if(confirmPassword.equals(password) && password.length()>4 &&login.length()>4){
			try{
				for(User x : dao.getAllUsers()){
					if(x.getLogin().equals(user.getLogin())) {
						signUP=true;
						break;
					}
				}
			}catch(Exception e){e.getStackTrace();}
		}
		else{
			request.getRequestDispatcher("/ErrorPage.jsp").include(request, response);
			PrintWriter out = response.getWriter();
			out.println("<br><br><br><br><br><br><br><br><br><br><br><br><br><center><h3 text-align='center'>Passwords did not match!</h3></center>");
			out.close();
		}
		if(signUP==false && password.length()>4 &&login.length()>4){ 
			dao.createUser(user.getLogin(),user.getPassword());
			session.setAttribute("user", user);
			response.sendRedirect("http://localhost:8080/ua.shop.vitaly/");
		}
		else{
			request.getRequestDispatcher("/ErrorPage.jsp").include(request, response);
			PrintWriter out = response.getWriter();
			out.println("<br><br><br><br><br><br><br><br><br><br><br><br><br><center><h3>User has already register</h3></center>");
			out.close();
		}
	}
}

