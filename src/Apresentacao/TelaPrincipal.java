package Apresentacao;

import Modelo.Livro;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaPrincipal extends JFrame implements ActionListener {
    private JButton btnLivros;
    private JButton btnUsuarios;
    private JButton btnEmprestimos;
    private JButton btnRelatorios;
    private JButton btnDevolucao;

    public TelaPrincipal() {
        setTitle("Sistema de Biblioteca");
        setSize(500, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel painel = new JPanel(new GridLayout(2, 2, 15, 15));
        painel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));


        btnDevolucao = new JButton ("Devolução");
        btnLivros = new JButton("Livros");
        btnUsuarios = new JButton("Usuários");
        btnEmprestimos = new JButton("Empréstimos");
        btnRelatorios = new JButton("Relatórios");

        btnLivros.addActionListener((ActionListener) this);
        btnUsuarios.addActionListener((ActionListener) this);
        btnEmprestimos.addActionListener((ActionListener) this);
        btnRelatorios.addActionListener((ActionListener) this);
        btnDevolucao.addActionListener((ActionListener) this);

        painel.add(btnDevolucao);
        painel.add(btnLivros);
        painel.add(btnUsuarios);
        painel.add(btnEmprestimos);
        painel.add(btnRelatorios);

        add(painel);
    }

    public static void main(String[] args) {
        new TelaPrincipal().setVisible(true);


    }


    @Override
    public void actionPerformed(ActionEvent event) {


        Object source = event.getSource();

        if (source == btnLivros) {
            new TelaCadastroLivro().setVisible(true);
        } else if (source == btnUsuarios) {
            new TelaCadastroUsuario().setVisible(true);
        } else if (source == btnEmprestimos) {
            new TelaEmprestimo().setVisible(true);
        } else if (source == btnRelatorios) {
            new TelaRelatorio().setVisible(true);
        }else if (source == btnDevolucao) {
            new TelaDevolucao().setVisible(true);            };


        }


    }



