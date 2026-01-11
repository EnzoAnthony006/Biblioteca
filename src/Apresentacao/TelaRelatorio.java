package Apresentacao;

import Modelo.Emprestimo;
import Persistencia.EmprestimoDAO;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class TelaRelatorio extends JFrame implements ActionListener {

    private JButton btnLivrosEmprestados;
    private JButton btnEmprestimosAtivos;
    private JButton btnHistorico;
    private JButton btnOK;

    private JTable tabela;
    private DefaultTableModel modelo;

    private EmprestimoDAO emprestimoDAO;

    public TelaRelatorio() {
        setTitle("Relatórios");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        emprestimoDAO = new EmprestimoDAO();

        btnLivrosEmprestados = new JButton("Livros Emprestados");
        btnEmprestimosAtivos = new JButton("Empréstimos Ativos");
        btnHistorico = new JButton("Histórico");
        btnOK = new JButton("Voltar");

        btnLivrosEmprestados.addActionListener(this);
        btnEmprestimosAtivos.addActionListener(this);
        btnHistorico.addActionListener(this);
        btnOK.addActionListener(this);

        JPanel painelBotoes = new JPanel(new GridLayout(1, 4, 10, 10));
        painelBotoes.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        painelBotoes.add(btnLivrosEmprestados);
        painelBotoes.add(btnEmprestimosAtivos);
        painelBotoes.add(btnHistorico);
        painelBotoes.add(btnOK);

        modelo = new DefaultTableModel();
        tabela = new JTable(modelo);

        setLayout(new BorderLayout());
        add(painelBotoes, BorderLayout.NORTH);
        add(new JScrollPane(tabela), BorderLayout.CENTER);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        modelo.setRowCount(0);
        modelo.setColumnCount(0);

        List<Emprestimo> emprestimos = emprestimoDAO.listarTodos();

        if (e.getSource() == btnLivrosEmprestados) {

            modelo.setColumnIdentifiers(new Object[]{
                    "Livro", "Usuário", "Data"
            });

            for (Emprestimo emp : emprestimos) {
                modelo.addRow(new Object[]{
                        emp.getLivro().getTitulo(),
                        emp.getUsuario().getNome(),
                        emp.getDataEmprestimo()
                });
            }

        } else if (e.getSource() == btnEmprestimosAtivos) {

            modelo.setColumnIdentifiers(new Object[]{
                    "Livro", "Usuário", "Data"
            });

            for (Emprestimo emp : emprestimos) {
                if (!emp.isDevolvido()) {
                    modelo.addRow(new Object[]{
                            emp.getLivro().getTitulo(),
                            emp.getUsuario().getNome(),
                            emp.getDataEmprestimo()
                    });
                }
            }

        } else if (e.getSource() == btnHistorico) {

            modelo.setColumnIdentifiers(new Object[]{
                    "Livro", "Usuário", "Data", "Status"
            });

            for (Emprestimo emp : emprestimos) {
                modelo.addRow(new Object[]{
                        emp.getLivro().getTitulo(),
                        emp.getUsuario().getNome(),
                        emp.getDataEmprestimo(),
                        emp.isDevolvido() ? "Devolvido" : "Ativo"
                });
            }

        } else if (e.getSource() == btnOK) {
            dispose();
        }
    }
}
