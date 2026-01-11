package Apresentacao;

import Modelo.Usuario;
import Modelo.Livro;
import Modelo.Emprestimo;
import Persistencia.UsuarioDAO;
import Persistencia.LivroDAO;
import Persistencia.EmprestimoDAO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

public class TelaEmprestimo extends JFrame implements ActionListener {

    private JComboBox<Usuario> cbUsuario;
    private JComboBox<Livro> cbLivro;
    private JButton btnConfirmar;
    private JButton btnCancelar;

    public TelaEmprestimo() {
        setTitle("Empréstimo de Livro");
        setSize(400, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel painel = new JPanel(new GridLayout(3, 2, 10, 10));
        painel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        painel.add(new JLabel("Usuário:"));
        cbUsuario = new JComboBox<>();
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        for (Usuario u : usuarioDAO.listarUsuarios()) {
            cbUsuario.addItem(u);
        }
        painel.add(cbUsuario);

        painel.add(new JLabel("Livro:"));
        cbLivro = new JComboBox<>();
        LivroDAO livroDAO = new LivroDAO();
        for (Livro l : livroDAO.listarLivros()) {
            cbLivro.addItem(l);
        }
        painel.add(cbLivro);

        btnConfirmar = new JButton("Confirmar");
        btnCancelar = new JButton("Cancelar");

        btnConfirmar.addActionListener(this);
        btnCancelar.addActionListener(this);

        painel.add(btnConfirmar);
        painel.add(btnCancelar);

        add(painel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == btnConfirmar) {

            if (cbUsuario.getSelectedItem() == null || cbLivro.getSelectedItem() == null) {
                JOptionPane.showMessageDialog(
                        this,
                        "Selecione usuário e livro!",
                        "Aviso",
                        JOptionPane.WARNING_MESSAGE
                );
                return;
            }

            Usuario usuarioSelecionado = (Usuario) cbUsuario.getSelectedItem();
            Livro livroSelecionado = (Livro) cbLivro.getSelectedItem();

            String data = LocalDate.now().toString();

            Emprestimo emprestimo = new Emprestimo(
                    usuarioSelecionado,
                    livroSelecionado,
                    data,
                    false
            );

            EmprestimoDAO dao = new EmprestimoDAO();
            dao.salvarEmprestimo(emprestimo);

            cbUsuario.setSelectedIndex(-1);
            cbLivro.setSelectedIndex(-1);

        } else if (e.getSource() == btnCancelar) {
            dispose();
        }
    }
}
