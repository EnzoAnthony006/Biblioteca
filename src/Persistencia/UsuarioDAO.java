package Persistencia;

import Modelo.Usuario;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(Config.URL, Config.USUARIO, Config.SENHA);
    }

    public static void closeConnection(Connection con) {
        if (con != null) {
            try { con.close(); } catch (SQLException ex) { ex.printStackTrace(); }
        }
    }

    public void salvarUsuario(Usuario u) {
        Connection con = null;
        try {
            con = getConnection();
            String sql = "INSERT INTO tb_usuario (nome, cpf, email) VALUES (?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, u.getNome());
            ps.setString(2, u.getCpf());
            ps.setString(3, u.getEmail());
            ps.executeUpdate();
        } catch (SQLException e) { e.printStackTrace(); }
        finally { closeConnection(con); }
    }

    public List<Usuario> listarUsuarios() {
        List<Usuario> usuarios = new ArrayList<>();
        Connection con = null;
        try {
            con = getConnection();
            String sql = "SELECT * FROM tb_usuario";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Usuario u = new Usuario(rs.getInt("id"), rs.getString("nome"), rs.getString("cpf"), rs.getString("email"));
                usuarios.add(u);
            }
        } catch (SQLException e) { e.printStackTrace(); }
        finally { closeConnection(con); }
        return usuarios;
    }
}
