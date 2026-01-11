package Persistencia;

import Modelo.Livro;

import javax.swing.JOptionPane;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LivroDAO {

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
                Config.URL,
                Config.USUARIO,
                Config.SENHA
        );
    }

    public void inserir(Livro livro) {
        String sql =
                "INSERT INTO tb_livro (titulo, autor, isbn, ano, status) " +
                        "VALUES (?, ?, ?, ?, ?)";

        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, livro.getTitulo());
            ps.setString(2, livro.getAutor());
            ps.setString(3, livro.getIsbn());
            ps.setInt(4, livro.getAno());
            ps.setString(5, livro.getStatus());

            ps.executeUpdate();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(
                    null,
                    "Erro ao inserir livro:\n" + e.getMessage(),
                    "Erro",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    public List<Livro> listarTodos() {
        List<Livro> livros = new ArrayList<>();
        String sql = "SELECT * FROM tb_livro";

        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Livro l = new Livro(
                        rs.getInt("id"),
                        rs.getString("titulo"),
                        rs.getString("autor"),
                        rs.getString("isbn"),
                        rs.getInt("ano"),
                        rs.getString("status")
                );
                livros.add(l);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(
                    null,
                    "Erro ao listar livros:\n" + e.getMessage(),
                    "Erro",
                    JOptionPane.ERROR_MESSAGE
            );
        }

        return livros;
    }

    public List<Livro> listarDisponiveis() {
        List<Livro> livros = new ArrayList<>();
        String sql = "SELECT * FROM tb_livro WHERE status = 'DISPONIVEL'";

        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Livro l = new Livro(
                        rs.getInt("id"),
                        rs.getString("titulo"),
                        rs.getString("autor"),
                        rs.getString("isbn"),
                        rs.getInt("ano"),
                        rs.getString("status")
                );
                livros.add(l);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(
                    null,
                    "Erro ao listar livros disponíveis:\n" + e.getMessage(),
                    "Erro",
                    JOptionPane.ERROR_MESSAGE
            );
        }

        return livros;
    }

    public void atualizarStatus(int idLivro, String status) {
        String sql = "UPDATE tb_livro SET status = ? WHERE id = ?";

        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, status);
            ps.setInt(2, idLivro);
            ps.executeUpdate();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(
                    null,
                    "Erro ao atualizar status do livro:\n" + e.getMessage(),
                    "Erro",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    public Livro[] listarLivros() {

        List<Livro> livros = new ArrayList<>();
        String sql = "SELECT * FROM tb_livro WHERE status = 'DISPONIVEL'";

        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Livro l = new Livro(
                        rs.getInt("id"),
                        rs.getString("titulo"),
                        rs.getString("autor"),
                        rs.getString("isbn"),
                        rs.getInt("ano"),
                        rs.getString("status")
                );
                livros.add(l);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(
                    null,
                    "Erro ao carregar livros:\n" + e.getMessage(),
                    "Erro",
                    JOptionPane.ERROR_MESSAGE
            );
        }

        return livros.toArray(new Livro[0]);
    }
}
