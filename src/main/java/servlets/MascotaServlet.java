package servlets;

import beans.*;
import daos.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/MascotaServlet")
public class MascotaServlet extends HttpServlet {

    private MascotaDao mascotaDao   = new MascotaDao();
    private EspecieDao especieDao   = new EspecieDao();
    private VeterinarioDao vetDao   = new VeterinarioDao();
    private DuenoDao duenoDao       = new DuenoDao();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String accion = req.getParameter("accion");
        if (accion == null) accion = "listar";

        try {
            switch (accion) {
                case "listar" -> listar(req, resp);
                case "nuevo"  -> mostrarFormulario(req, resp);
                case "borrar" -> borrar(req, resp);
                case "filtrar"-> filtrar(req, resp);
            }
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        try {
            crear(req, resp);
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }

    private void listar(HttpServletRequest req, HttpServletResponse resp)
            throws SQLException, ServletException, IOException {
        List<Mascota> mascotas = mascotaDao.listar();
        List<Especie> especies = especieDao.listar();
        req.setAttribute("mascotas", mascotas);
        req.setAttribute("especies", especies);
        req.getRequestDispatcher("/mascotas/listaMascotas.jsp").forward(req, resp);
    }

    private void filtrar(HttpServletRequest req, HttpServletResponse resp)
            throws SQLException, ServletException, IOException {

        String param = req.getParameter("especieId");
        int especieId = (param != null && !param.isEmpty()) ? Integer.parseInt(param) : 0;

        List<Mascota> mascotas;
        if (especieId == 0) {
            mascotas = mascotaDao.listar(); // trae TODAS
        } else {
            mascotas = mascotaDao.listarPorEspecie(especieId);
        }

        List<Especie> especies = especieDao.listar();
        req.setAttribute("mascotas", mascotas);
        req.setAttribute("especies", especies);
        req.setAttribute("especieSeleccionada", especieId);
        req.getRequestDispatcher("/mascotas/listaMascotas.jsp").forward(req, resp);
    }

    private void mostrarFormulario(HttpServletRequest req, HttpServletResponse resp)
            throws SQLException, ServletException, IOException {
        req.setAttribute("especies",     especieDao.listar());
        req.setAttribute("veterinarios", vetDao.listar());
        req.setAttribute("duenos",       duenoDao.listar());
        req.getRequestDispatcher("/mascotas/nuevoMascota.jsp").forward(req, resp);
    }

    private void crear(HttpServletRequest req, HttpServletResponse resp)
            throws SQLException, IOException {
        Mascota m = new Mascota();
        m.setNombre(req.getParameter("nombre"));
        m.setEdad(Integer.parseInt(req.getParameter("edad")));
        m.setPeso(Double.parseDouble(req.getParameter("peso")));

        Especie e = new Especie();
        e.setIdespecie(Integer.parseInt(req.getParameter("especieId")));
        m.setEspecie(e);

        Veterinario v = new Veterinario();
        v.setIdveterinario(Integer.parseInt(req.getParameter("veterinarioId")));
        m.setVeterinario(v);

        Dueno d = new Dueno();
        d.setIddueno(Integer.parseInt(req.getParameter("duenoId")));
        m.setDueno(d);

        mascotaDao.crear(m);
        resp.sendRedirect(req.getContextPath() + "/MascotaServlet?accion=listar");
    }

    private void borrar(HttpServletRequest req, HttpServletResponse resp)
            throws SQLException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        mascotaDao.borrar(id);
        resp.sendRedirect(req.getContextPath() + "/MascotaServlet?accion=listar");
    }
}