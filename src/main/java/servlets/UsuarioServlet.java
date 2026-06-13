package servlets;

import beans.Usuario;
import daos.UsuarioDao;
import utils.HashUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "UsuarioServlet", urlPatterns = {"/usuarios"})
public class UsuarioServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        if(session.getAttribute("usuarioLogueado") == null) {
            response.sendRedirect(request.getContextPath() + "/");
            return;
        }

        String action = request.getParameter("action") == null ? "listar" : request.getParameter("action");

        if (action.equals("listar")) {
            UsuarioDao dao = new UsuarioDao();
            request.setAttribute("listaUsuarios", dao.listarUsuarios());
            request.getRequestDispatcher("usuarios.jsp").forward(request, response);
        } else if (action.equals("formulario")) {
            request.getRequestDispatcher("usuario_form.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");

        if ("crear".equals(action)) {
            String nombre = request.getParameter("nombre");
            String apellido = request.getParameter("apellido");
            String dni = request.getParameter("dni");
            String correo = request.getParameter("correo");
            String passPlana = request.getParameter("pass");

            // Validaciones
            boolean hasLetras = passPlana.matches(".*[a-zA-Z]+.*");
            boolean hasNumeros = passPlana.matches(".*[0-9]+.*");

            if (passPlana.length() < 8 || !hasLetras || !hasNumeros) {
                request.setAttribute("error", "La contraseña debe tener mínimo 8 caracteres, números y letras.");
                request.getRequestDispatcher("usuario_form.jsp").forward(request, response);
                return;
            }

            UsuarioDao dao = new UsuarioDao();
            if (dao.existeDniOCorreo(dni, correo)) {
                request.setAttribute("error", "El DNI o Correo ya se encuentran registrados.");
                request.getRequestDispatcher("usuario_form.jsp").forward(request, response);
                return;
            }

            Usuario u = new Usuario();
            u.setNombre(nombre);
            u.setApellido(apellido);
            u.setDni(dni);
            u.setCorreo(correo);
            u.setPass(HashUtil.cifrarSHA256(passPlana));

            dao.crearUsuario(u);
            response.sendRedirect(request.getContextPath() + "/usuarios");
        }
    }
}
