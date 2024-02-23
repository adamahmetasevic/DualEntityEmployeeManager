package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

@WebServlet("/editEmployeeServlet")
public class editEmployeeServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String employeeId = request.getParameter("id");

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("EmployeeManager");
        EntityManager em = emf.createEntityManager();

        try {
            Employee employee = em.find(Employee.class, Long.parseLong(employeeId));

            TypedQuery<Department> query = em.createQuery("SELECT d FROM Department d", Department.class);
            List<Department> departments = query.getResultList();

            request.setAttribute("employee", employee);
            request.setAttribute("departments", departments);

            request.getRequestDispatcher("editEmployee.jsp").forward(request, response);
        } finally {
            em.close();
            emf.close();
        }
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String employeeId = request.getParameter("id");
        String name = request.getParameter("name");
        String position = request.getParameter("position");
        Long departmentId = Long.parseLong(request.getParameter("departmentId"));

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("EmployeeManager");
        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();

            Employee employee = em.find(Employee.class, Long.parseLong(employeeId));
            Department department = em.find(Department.class, departmentId);

            employee.setName(name);
            employee.setPosition(position);
            employee.setDepartment(department);

            em.getTransaction().commit();

            response.sendRedirect("viewEmployeeServlet");
        } catch (Exception ex) {
            ex.printStackTrace();
            em.getTransaction().rollback();
            response.sendRedirect("index.jsp");
        } finally {
            em.close();
            emf.close();
        }
    }
}
