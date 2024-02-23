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

@WebServlet("/addEmployeeServlet")
public class addEmployeeServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String position = request.getParameter("position");
        Long departmentId = Long.parseLong(request.getParameter("departmentId"));

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("EmployeeManager");
        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();

            Department department = em.find(Department.class, departmentId);

            Employee newEmployee = new Employee();
            newEmployee.setName(name);
            newEmployee.setPosition(position);
            newEmployee.setDepartment(department);

            em.persist(newEmployee);

            em.getTransaction().commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.close();
            emf.close();
        }

        response.sendRedirect("index.jsp");
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("EmployeeManager");
        EntityManager em = emf.createEntityManager();

        try {
            TypedQuery<Department> query = em.createQuery("SELECT d FROM Department d", Department.class);
            List<Department> departments = query.getResultList();

            request.setAttribute("departments", departments); // Set departments as an attribute

            // Forward the request to addEmployee.jsp
            request.getRequestDispatcher("/addEmployee.jsp").forward(request, response);
        } finally {
            em.close();
            emf.close();
        }
    }
    }
