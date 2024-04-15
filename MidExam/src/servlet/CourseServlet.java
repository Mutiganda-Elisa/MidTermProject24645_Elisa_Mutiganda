package servlet;

import dao.CourseDAO;
import model.AcademicUnit;
import model.Course;
import model.Semester;
import model.Teacher;
import org.hibernate.SessionFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/courses")
public class CourseServlet extends HttpServlet implements ServletContextListener {

    private static final long serialVersionUID = 1L;
    

    private CourseDAO courseDAO;
    private SessionFactory sessionFactory;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        
       
    }
    
    @Override
    public void init() throws ServletException {
   
        sessionFactory = (SessionFactory) getServletContext().getAttribute("sessionFactory");
        courseDAO = new CourseDAO(sessionFactory);
    }

 

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            List<Course> courses = courseDAO.getAllCourses();
            request.setAttribute("courses", courses);
            List<Semester> semesters = courseDAO.getAllSemesters();
            request.setAttribute("semesters", semesters);
            List<AcademicUnit> academicUnits = courseDAO.getAllAcademicUnits();
            request.setAttribute("academicUnits", academicUnits);
            List<Teacher> teachers = courseDAO.getAllTeachers();
            request.setAttribute("teachers", teachers);
            RequestDispatcher dispatcher = request.getRequestDispatcher("Course.jsp");
            dispatcher.forward(request, response);
        } else if (action.equals("create")) {
            // Prepare the necessary data for the create form
            List<Semester> semesters = courseDAO.getAllSemesters();
            request.setAttribute("semesters", semesters);
            List<AcademicUnit> academicUnits = courseDAO.getAllAcademicUnits();
            request.setAttribute("academicUnits", academicUnits);
            List<Teacher> teachers = courseDAO.getAllTeachers();
            request.setAttribute("teachers", teachers);
            RequestDispatcher dispatcher = request.getRequestDispatcher("Course.jsp");
            dispatcher.forward(request, response);
        } else if (action.equals("edit")) {
            Long courseId = Long.parseLong(request.getParameter("id"));
            Course course = courseDAO.getCourseById(courseId);
            request.setAttribute("course", course);
            List<Semester> semesters = courseDAO.getAllSemesters();
            request.setAttribute("semesters", semesters);
            List<AcademicUnit> academicUnits = courseDAO.getAllAcademicUnits();
            request.setAttribute("academicUnits", academicUnits);
            List<Teacher> teachers = courseDAO.getAllTeachers();
            request.setAttribute("teachers", teachers);
            RequestDispatcher dispatcher = request.getRequestDispatcher("Course.jsp");
            dispatcher.forward(request, response);
        } else if (action.equals("delete")) {
            Long courseId = Long.parseLong(request.getParameter("id"));
            courseDAO.deleteCourse(courseId);
            response.sendRedirect(request.getContextPath() + "/courses");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action.equals("create")) {
            String courseCode = request.getParameter("courseCode");
            String name = request.getParameter("name");
            Long semesterId = Long.parseLong(request.getParameter("semester"));
            Semester semester = courseDAO.getSemesterById(semesterId);
            Long departmentId = Long.parseLong(request.getParameter("department"));
            AcademicUnit department = courseDAO.getAcademicUnitById(departmentId);
            Long teacherId = Long.parseLong(request.getParameter("teacher"));
            Teacher teacher = courseDAO.getTeacherById(teacherId);
            Course newCourse = new Course(courseCode, name, semester, department, teacher);
            courseDAO.createCourse(newCourse);
            response.sendRedirect(request.getContextPath() + "/courses");
        } else if (action.equals("edit")) {
            Long courseId = Long.parseLong(request.getParameter("id"));
            String courseCode = request.getParameter("courseCode");
            String name = request.getParameter("name");
            Long semesterId = Long.parseLong(request.getParameter("semester"));
            Semester semester = courseDAO.getSemesterById(semesterId);
            Long departmentId = Long.parseLong(request.getParameter("department"));
            AcademicUnit department = courseDAO.getAcademicUnitById(departmentId);
            Long teacherId = Long.parseLong(request.getParameter("teacher"));
            Teacher teacher = courseDAO.getTeacherById(teacherId);
            Course course = new Course(courseCode, name, semester, department, teacher);
            course.setCourseId(courseId);
            courseDAO.updateCourse(course);
            response.sendRedirect(request.getContextPath() + "/courses");
        }
    }

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}