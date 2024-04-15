package servlet;

import dao.TeacherDAO;
import model.EQualification;
import model.Teacher;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/TeacherServlet")
public class TeacherServlet extends HttpServlet {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private TeacherDAO teacherDAO = new TeacherDAO();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "list";
        }

        switch (action) {
            case "new":
                showNewForm(request, response);
                break;
            case "insert":
                insertTeacher(request, response);
                break;
            case "delete":
                deleteTeacher(request, response);
                break;
            case "edit":
                showEditForm(request, response);
                break;
            case "update":
                updateTeacher(request, response);
                break;
            case "list":
            default:
                listTeachers(request, response);
                break;
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        switch (action) {
            case "insert":
                insertTeacher(request, response);
                break;
            case "update":
                updateTeacher(request, response);
                break;
            default:
                listTeachers(request, response);
                break;
        }
    }

    private void listTeachers(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Teacher> teachers = TeacherDAO.getAllTeachers();
        request.setAttribute("teachers", teachers);
        request.setAttribute("action", "list");
        request.setAttribute("qualifications", teacherDAO.getAllQualifications());
        RequestDispatcher dispatcher = request.getRequestDispatcher("Teacher.jsp");
        dispatcher.forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("teacher", new Teacher());
        request.setAttribute("action", "insert");
        request.setAttribute("qualifications", teacherDAO.getAllQualifications());
        RequestDispatcher dispatcher = request.getRequestDispatcher("Teacher.jsp");
        dispatcher.forward(request, response);
    }

    private void insertTeacher(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String qualificationStr = request.getParameter("qualification");
        EQualification qualification = EQualification.valueOf(qualificationStr);
        Teacher newTeacher = new Teacher(firstName, lastName, qualification);
        teacherDAO.create(newTeacher);
        response.sendRedirect("TeacherServlet");
    }

    private void deleteTeacher(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        Long teacherId = Long.parseLong(request.getParameter("teacherId"));
        Teacher teacher = teacherDAO.read(teacherId);
        teacherDAO.delete(teacher);
        response.sendRedirect("TeacherServlet");
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Long teacherId = Long.parseLong(request.getParameter("teacherId"));
        Teacher teacher = teacherDAO.read(teacherId);
        request.setAttribute("teacher", teacher);
        request.setAttribute("action", "update");
        request.setAttribute("qualifications", teacherDAO.getAllQualifications());
        RequestDispatcher dispatcher = request.getRequestDispatcher("Teacher.jsp");
        dispatcher.forward(request, response);
    }

    private void updateTeacher(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        Long teacherId = Long.parseLong(request.getParameter("teacherId"));
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String qualificationStr = request.getParameter("qualification");
        EQualification qualification = EQualification.valueOf(qualificationStr);
        Teacher teacher = new Teacher(firstName, lastName, qualification);
        teacher.setTeacherId(teacherId);
        teacherDAO.update(teacher);
        response.sendRedirect("TeacherServlet");
    }
    
    
}
