package servlet;

import model.User;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import model.HibernateTest;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        if (email == null || email.isEmpty() || password == null || password.isEmpty()) {
            response.sendRedirect("Login.jsp?error=invalid_input");
            return;
        }

        Session session = HibernateTest.getSessionFactory().openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            Query<User> query = session.createQuery("FROM User WHERE email = :email", User.class);
            query.setParameter("email", email);
            User user = query.uniqueResult();

            if (user != null && user.getPassword().equals(password)) {
                Cookie emailCookie = new Cookie("email", email);
                emailCookie.setMaxAge(60 * 60 * 24 * 30); // 30 days
                emailCookie.setPath("/");
                response.addCookie(emailCookie);

                Cookie passwordCookie = new Cookie("password", password);
                passwordCookie.setMaxAge(60 * 60 * 24 * 30); // 30 days
                passwordCookie.setPath("/");
                response.addCookie(passwordCookie);

                if (user.getRole().equals("admin")) {
                    response.sendRedirect("admin.jsp");
                } else if (user.getRole().equals("student")) {
                    response.sendRedirect("StudentDashboard.jsp");
                } else if (user.getRole().equals("teacher")) {
                    response.sendRedirect("TeacherDashboard.jsp");
                }
                return;
            }
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }

        response.sendRedirect("Login.jsp?error=invalid_credentials");
    }
}