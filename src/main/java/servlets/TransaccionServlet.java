package servlets;

import beans.Transaccion;
import beans.Usuario;
import daos.TransaccionDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;

@WebServlet(name = "TransaccionServlet", urlPatterns = {"/transacciones"})
public class TransaccionServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Usuario usuario = (Usuario) session.getAttribute("usuarioLogueado");
        if(usuario == null) {
            response.sendRedirect(request.getContextPath() + "/");
            return;
        }

        String action = request.getParameter("action") == null ? "listar" : request.getParameter("action");
        TransaccionDao dao = new TransaccionDao();

        if (action.equals("listar")) {
            request.setAttribute("listaTransacciones", dao.listarTransaccionesPorUsuario(usuario.getIdusuarios()));
            request.getRequestDispatcher("transacciones.jsp").forward(request, response);
        } else if (action.equals("formulario")) {
            request.getRequestDispatcher("transaccion_form.jsp").forward(request, response);
        } else if (action.equals("borrar")) {
            int idTransaccion = Integer.parseInt(request.getParameter("id"));
            dao.borrarTransaccion(idTransaccion, usuario.getIdusuarios());
            response.sendRedirect(request.getContextPath() + "/transacciones");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();
        Usuario usuario = (Usuario) session.getAttribute("usuarioLogueado");

        if ("crear".equals(request.getParameter("action")) && usuario != null) {
            String titulo = request.getParameter("titulo");
            String tipo = request.getParameter("tipo");
            String descripcion = request.getParameter("descripcion");
            double monto = Double.parseDouble(request.getParameter("monto"));

            if (monto <= 0) {
                request.setAttribute("error", "El monto debe ser mayor a 0.");
                request.getRequestDispatcher("transaccion_form.jsp").forward(request, response);
                return;
            }

            Transaccion t = new Transaccion();
            t.setTitulo(titulo);
            t.setTipo(tipo);
            t.setDescripcion(descripcion);
            t.setMonto(monto);
            t.setFecha(Date.valueOf(LocalDate.now())); // Fecha actual automatica
            t.setUsuariosIdusuarios(usuario.getIdusuarios());

            TransaccionDao dao = new TransaccionDao();
            dao.crearTransaccion(t);
            response.sendRedirect(request.getContextPath() + "/transacciones");
        }
    }
}
