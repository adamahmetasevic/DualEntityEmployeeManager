package controller;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/viewEmployeeServlet")
public class viewEmployeeServlet extends HttpServlet {
    private static final long serialVersionUID = 1L; 

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("EmployeeManager");
        EntityManager em = emf.createEntityManager();

        try {
            TypedQuery<Employee> query = em.createQuery("SELECT e FROM Employee e JOIN FETCH e.department", Employee.class);

            List<Employee> employees = query.getResultList();

            request.setAttribute("employees", employees);

            request.getRequestDispatcher("viewEmployees.jsp").forward(request, response);
        } finally {
            em.close();
            emf.close();
        }
    }
}
