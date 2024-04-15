package servlet;

import dao.SemesterDAO;
import model.Semester;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@WebServlet("/SemesterServlet")
public class SemesterServlet extends HttpServlet {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private SemesterDAO semesterDAO = new SemesterDAO();

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
                insertSemester(request, response);
                break;
            case "delete":
                deleteSemester(request, response);
                break;
            case "edit":
                showEditForm(request, response);
                break;
            case "update":
                updateSemester(request, response);
                break;
            case "list":
            default:
                listSemesters(request, response);
                break;
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        switch (action) {
            case "insert":
                insertSemester(request, response);
                break;
            case "update":
                updateSemester(request, response);
                break;
            default:
                listSemesters(request, response);
                break;
        }
    }

    private void listSemesters(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Semester> semesters = SemesterDAO.getAllSemesters();
        request.setAttribute("semesters", semesters);
        request.setAttribute("action", "list");
        RequestDispatcher dispatcher = request.getRequestDispatcher("Semester.jsp");
        dispatcher.forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("semester", new Semester());
        request.setAttribute("action", "insert");
        RequestDispatcher dispatcher = request.getRequestDispatcher("Semester.jsp");
        dispatcher.forward(request, response);
    }

    private void insertSemester(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        String semesterName = request.getParameter("semesterName");
        String startDateStr = request.getParameter("startDate");
        String endDateStr = request.getParameter("endDate");
        LocalDate startDate = LocalDate.parse(startDateStr, DateTimeFormatter.ofPattern("yyyy/MM/dd"));
        LocalDate endDate = LocalDate.parse(endDateStr, DateTimeFormatter.ofPattern("yyyy/MM/dd"));
        Semester newSemester = new Semester(semesterName, startDate, endDate);
        semesterDAO.create(newSemester);
        response.sendRedirect("SemesterServlet");
    }

    private void deleteSemester(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        Long semesterId = Long.parseLong(request.getParameter("semesterId"));
        Semester semester = semesterDAO.read(semesterId);
        semesterDAO.delete(semester);
        response.sendRedirect("SemesterServlet");
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Long semesterId = Long.parseLong(request.getParameter("semesterId"));
        Semester semester = semesterDAO.read(semesterId);
        request.setAttribute("semester", semester);
        request.setAttribute("action", "update");
        RequestDispatcher dispatcher = request.getRequestDispatcher("Semester.jsp");
        dispatcher.forward(request, response);
    }

    private void updateSemester(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        Long semesterId = Long.parseLong(request.getParameter("semesterId"));
        String semesterName = request.getParameter("semesterName");
        String startDateStr = request.getParameter("startDate");
        String endDateStr = request.getParameter("endDate");
        LocalDate startDate = LocalDate.parse(startDateStr, DateTimeFormatter.ofPattern("yyyy/MM/dd"));
        LocalDate endDate = LocalDate.parse(endDateStr, DateTimeFormatter.ofPattern("yyyy/MM/dd"));
        Semester semester = new Semester(semesterName, startDate, endDate);
        semester.setSemesterId(semesterId);
        semesterDAO.update(semester);
        response.sendRedirect("SemesterServlet");
    }
}