package Persistencia;

import Modelo.Emprestimo;
import Modelo.Usuario;
import Modelo.Livro;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmprestimoDAO {

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
                Config.URL,
                Config.USUARIO,
                Config.SENHA
        );
    }

    public void salvarEmprestimo(Emprestimo e) {

        if (livroJaEmprestado(e.getLivro().getId())) {
            JOptionPane.showMessageDialog(
                    null,
                    "Este livro já está emprestado!",
                    "Erro",
                    JOptionPane.ERROR_MESSAGE
            );
            return;
        }

        String sql =
                "INSERT INTO tb_emprestimo (usuario_id, livro_id, data_emprestimo, devolvido) " +
                        "VALUES (?, ?, ?, ?)";

        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, e.getUsuario().getId());
            ps.setInt(2, e.getLivro().getId());
            ps.setString(3, e.getDataEmprestimo());
            ps.setBoolean(4, e.isDevolvido());

            ps.executeUpdate();

            JOptionPane.showMessageDialog(
                    null,
                    "Empréstimo realizado com sucesso!"
            );

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(
                    null,
                    "Erro ao salvar empréstimo!",
                    "Erro",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    private boolean livroJaEmprestado(int livroId) {
        String sql =
                "SELECT COUNT(*) FROM tb_emprestimo " +
                        "WHERE livro_id = ? AND devolvido = false";

        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, livroId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getInt(1) > 0;
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(
                    null,
                    "Erro ao verificar empréstimo!",
                    "Erro",
                    JOptionPane.ERROR_MESSAGE
            );
        }

        return false;
    }

    public void atualizarEmprestimo(Emprestimo e) {
        String sql = "UPDATE tb_emprestimo SET devolvido = ? WHERE id = ?";

        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setBoolean(1, e.isDevolvido());
            ps.setInt(2, e.getId());
            ps.executeUpdate();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(
                    null,
                    "Erro ao atualizar devolução!",
                    "Erro",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    public List<Emprestimo> listarTodos() {
        List<Emprestimo> emprestimos = new ArrayList<>();

        String sql =
                "SELECT e.id, e.data_emprestimo, e.devolvido, " +
                        "u.id AS usuario_id, u.nome, u.cpf, u.email, " +
                        "l.id AS livro_id, l.titulo, l.autor, l.isbn, l.ano " +
                        "FROM tb_emprestimo e " +
                        "JOIN tb_usuario u ON e.usuario_id = u.id " +
                        "JOIN tb_livro l ON e.livro_id = l.id";

        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Usuario u = new Usuario(
                        rs.getInt("usuario_id"),
                        rs.getString("nome"),
                        rs.getString("cpf"),
                        rs.getString("email")
                );

                Livro l = new Livro(
                        rs.getInt("livro_id"),
                        rs.getString("titulo"),
                        rs.getString("autor"),
                        rs.getString("isbn"),
                        rs.getInt("ano")
                );

                Emprestimo e = new Emprestimo(
                        u,
                        l,
                        rs.getString("data_emprestimo"),
                        rs.getBoolean("devolvido")
                );

                e.setId(rs.getInt("id"));
                emprestimos.add(e);
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(
                    null,
                    "Erro ao carregar relatórios!",
                    "Erro",
                    JOptionPane.ERROR_MESSAGE
            );
        }

        return emprestimos;
    }

    public List<Emprestimo> listarEmprestimosEmAberto() {
        List<Emprestimo> emprestimos = new ArrayList<>();

        String sql =
                "SELECT e.id, e.data_emprestimo, e.devolvido, " +
                        "u.id AS usuario_id, u.nome, u.cpf, u.email, " +
                        "l.id AS livro_id, l.titulo, l.autor, l.isbn, l.ano " +
                        "FROM tb_emprestimo e " +
                        "JOIN tb_usuario u ON e.usuario_id = u.id " +
                        "JOIN tb_livro l ON e.livro_id = l.id " +
                        "WHERE e.devolvido = false";

        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Usuario u = new Usuario(
                        rs.getInt("usuario_id"),
                        rs.getString("nome"),
                        rs.getString("cpf"),
                        rs.getString("email")
                );

                Livro l = new Livro(
                        rs.getInt("livro_id"),
                        rs.getString("titulo"),
                        rs.getString("autor"),
                        rs.getString("isbn"),
                        rs.getInt("ano")
                );

                Emprestimo e = new Emprestimo(
                        u,
                        l,
                        rs.getString("data_emprestimo"),
                        rs.getBoolean("devolvido")
                );

                e.setId(rs.getInt("id"));
                emprestimos.add(e);
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(
                    null,
                    "Erro ao listar empréstimos em aberto!",
                    "Erro",
                    JOptionPane.ERROR_MESSAGE
            );
        }

        return emprestimos;
    }
}
