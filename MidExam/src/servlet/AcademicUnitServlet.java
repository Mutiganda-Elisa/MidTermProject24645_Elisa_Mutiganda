package servlet;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import dao.AcademicUnitDAO;
import model.AcademicUnit;
import model.EAcademicUnit;
import model.HibernateTest;

@WebServlet("/academicunit")
public class AcademicUnitServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private AcademicUnitDAO academicUnitDAO;

    public AcademicUnitServlet() {
        super();
        academicUnitDAO = new AcademicUnitDAO(HibernateTest.sessionFactory);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action != null) {
            switch (action) {
                case "create":
                    showCreateForm(request, response);
                    break;
                case "read":
                    showAcademicUnitDetails(request, response);
                    break;
                case "update":
                    showUpdateForm(request, response);
                    break;
                case "delete":
                    deleteAcademicUnit(request, response);
                    break;
                default:
                    listAcademicUnits(request, response);
                    break;
            }
        } else {
            listAcademicUnits(request, response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action != null) {
            switch (action) {
                case "create":
                    createAcademicUnit(request, response);
                    break;
                case "update":
                    updateAcademicUnit(request, response);
                    break;
                default:
                    listAcademicUnits(request, response);
                    break;
            }
        } else {
            listAcademicUnits(request, response);
        }
    }

    private void listAcademicUnits(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<AcademicUnit> academicUnits = academicUnitDAO.getAllAcademicUnits();
        request.setAttribute("academicUnits", academicUnits);
        request.getRequestDispatcher("/AcademicUnit.jsp").forward(request, response);
    }

    private void showCreateForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("academicUnit", new AcademicUnit());
        request.setAttribute("EAcademicUnit", EAcademicUnit.values());
        request.getRequestDispatcher("/AcademicUnit.jsp").forward(request, response);
    }

    private void createAcademicUnit(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String academicCode = request.getParameter("academicCode");
        String academicName = request.getParameter("academicName");
        EAcademicUnit type = EAcademicUnit.valueOf(request.getParameter("type"));
        Long parentId = Long.parseLong(request.getParameter("parentId"));
        AcademicUnit parent = parentId > 0 ? academicUnitDAO.getAcademicUnitById(parentId) : null;
        AcademicUnit academicUnit = new AcademicUnit(academicCode, academicName, type, parent);
        academicUnitDAO.createAcademicUnit(academicUnit);
        response.sendRedirect("academicunit");
    }

    private void showAcademicUnitDetails(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long academicId = Long.parseLong(request.getParameter("id"));
        AcademicUnit academicUnit = academicUnitDAO.getAcademicUnitById(academicId);
        request.setAttribute("academicUnit", academicUnit);
        request.getRequestDispatcher("/AcademicUnit.jsp").forward(request, response);
    }

    private void showUpdateForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long academicId = Long.parseLong(request.getParameter("id"));
        AcademicUnit academicUnit = academicUnitDAO.getAcademicUnitById(academicId);
        request.setAttribute("academicUnit", academicUnit);
        request.setAttribute("EAcademicUnit", EAcademicUnit.values());
        request.getRequestDispatcher("/AcademicUnit.jsp").forward(request, response);
    }

    private void updateAcademicUnit(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Long academicId = Long.parseLong(request.getParameter("academicId"));
        String academicCode = request.getParameter("academicCode");
        String academicName = request.getParameter("academicName");
        EAcademicUnit type = EAcademicUnit.valueOf(request.getParameter("type"));
        Long parentId = Long.parseLong(request.getParameter("parentId"));
        AcademicUnit parent = parentId > 0 ? academicUnitDAO.getAcademicUnitById(parentId) : null;
        AcademicUnit academicUnit = new AcademicUnit(academicCode, academicName, type, parent);
        academicUnit.setAcademicId(academicId);
        academicUnitDAO.updateAcademicUnit(academicUnit);
        response.sendRedirect("academicunit");
    }

    private void deleteAcademicUnit(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Long academicId = Long.parseLong(request.getParameter("id"));
        academicUnitDAO.deleteAcademicUnit(academicId);
        response.sendRedirect("academicunit");
    }
}
