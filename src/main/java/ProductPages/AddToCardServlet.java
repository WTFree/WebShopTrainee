package ProductPages;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Product.DAO.JDBCProductDAO;

@WebServlet()
public class AddToCardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public AddToCardServlet() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		String userID = request.getParameter("userID");
		String productID = request.getParameter("productID");

		request.getRequestDispatcher("/WEB-INF/includes/header.jsp").include(request, response);
		PrintWriter out = response.getWriter();
		out.print("<button onclick=\"history.back()\" value=\"back\"> >>>Back<<< </button>");
		request.getRequestDispatcher("/WEB-INF/includes/footer.jsp").include(request, response);
		out.close();
		
		JDBCProductDAO dao = new JDBCProductDAO();
		dao.AddToBasket(Integer.parseInt(userID), Integer.parseInt(productID));
		request.getRequestDispatcher("index.jsp").forward(request, response);
		
	}

}
