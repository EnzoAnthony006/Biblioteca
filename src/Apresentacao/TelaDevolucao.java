package Apresentacao;

import Modelo.Emprestimo;
import Persistencia.EmprestimoDAO;
import Persistencia.LivroDAO;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class TelaDevolucao extends JFrame implements ActionListener {

    private JTable tabela;
    private DefaultTableModel modelo;
    private JButton btnConfirmar;
    private JButton btnCancelar;
    private List<Emprestimo> listaEmprestimos;
    private EmprestimoDAO emprestimoDAO;
    private LivroDAO livroDAO;

    public TelaDevolucao() {
        setTitle("Devolução de Livros");
        setSize(600, 400);
        setLocationRelativeTo(null);

        modelo = new DefaultTableModel();
        modelo.setColumnIdentifiers(new Object[]{
                "Usuário", "Livro", "Data Empréstimo", "Status"
        });

        tabela = new JTable(modelo);
        JScrollPane scroll = new JScrollPane(tabela);

        btnConfirmar = new JButton("Confirmar Devolução");
        btnCancelar = new JButton("Cancelar");

        btnConfirmar.addActionListener(this);
        btnCancelar.addActionListener(this);

        JPanel painelBotoes = new JPanel();
        painelBotoes.add(btnConfirmar);
        painelBotoes.add(btnCancelar);

        setLayout(new BorderLayout());
        add(scroll, BorderLayout.CENTER);
        add(painelBotoes, BorderLayout.SOUTH);

        emprestimoDAO = new EmprestimoDAO();
        livroDAO = new LivroDAO();

        listaEmprestimos = emprestimoDAO.listarEmprestimosEmAberto();
        atualizarTabela();
    }

    private void atualizarTabela() {
        modelo.setRowCount(0);
        for (Emprestimo e : listaEmprestimos) {
            modelo.addRow(new Object[]{
                    e.getUsuario().getNome(),
                    e.getLivro().getTitulo(),
                    e.getDataEmprestimo(),
                    "ATIVO"
            });
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == btnCancelar) {
            dispose();
            return;
        }

        int linha = tabela.getSelectedRow();
        if (linha < 0) {
            JOptionPane.showMessageDialog(this,
                    "Selecione um empréstimo.",
                    "Aviso",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        Emprestimo emprestimo = listaEmprestimos.get(linha);

        emprestimo.setDevolvido(true);
        emprestimoDAO.atualizarEmprestimo(emprestimo);
        livroDAO.atualizarStatus(
                emprestimo.getLivro().getId(),
                "DISPONIVEL"
        );

        listaEmprestimos.remove(linha);
        atualizarTabela();

        JOptionPane.showMessageDialog(this,
                "Livro devolvido com sucesso!");
    }
}
