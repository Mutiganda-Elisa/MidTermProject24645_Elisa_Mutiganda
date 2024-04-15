package servlet;

import dao.StudentDAO;
import model.Student;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/StudentServlet")
public class StudentServlet extends HttpServlet {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private StudentDAO studentDAO;

    public StudentServlet() {
        this.studentDAO = new StudentDAO();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        switch (action) {
            case "create":
                createStudent(request, response);
                break;
            case "update":
                updateStudent(request, response);
                break;
            case "delete":
                deleteStudent(request, response);
                break;
            default:
                getAllStudents(request, response);
                break;
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action != null) {
            switch (action) {
                case "edit":
                    editStudent(request, response);
                    break;
                case "delete":
                    deleteStudent(request, response);
                    break;
                default:
                    getAllStudents(request, response);
                    break;
            }
        } else {
            getAllStudents(request, response);
        }
    }

    private void createStudent(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String dateOfBirth = request.getParameter("dateOfBirth");

        Student student = new Student(firstName, lastName, dateOfBirth);
        studentDAO.saveStudent(student);

        response.sendRedirect("StudentServlet");
    }

    private void updateStudent(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Long studentId = Long.parseLong(request.getParameter("studentId"));
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String dateOfBirth = request.getParameter("dateOfBirth");

        Student student = new Student(firstName, lastName, dateOfBirth);
        student.setStudentId(studentId);
        studentDAO.updateStudent(student);

        response.sendRedirect("StudentServlet");
    }

    private void deleteStudent(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Long studentId = Long.parseLong(request.getParameter("studentId"));
        studentDAO.deleteStudent(studentId);

        response.sendRedirect("StudentServlet");
    }

    private void editStudent(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String studentIdParam = request.getParameter("studentId");
        if (studentIdParam == null || studentIdParam.isEmpty()) {
            // If no studentId is provided, redirect to the default view
            getAllStudents(request, response);
            return;
        }

        Long studentId = Long.parseLong(studentIdParam);
        Student student = studentDAO.getStudentById(studentId);

        if (student == null) {
            // If the student is not found, redirect to the default view
            getAllStudents(request, response);
            return;
        }

        request.setAttribute("student", student);
        request.getRequestDispatcher("Student.jsp").forward(request, response);
    }

    private void getAllStudents(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Student> students = studentDAO.getAllStudents();
        request.setAttribute("students", students);
        request.getRequestDispatcher("Student.jsp").forward(request, response);
    }
}