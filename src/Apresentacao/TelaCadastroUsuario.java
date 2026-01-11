package Apresentacao;

import Modelo.Usuario;
import Persistencia.UsuarioDAO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaCadastroUsuario extends JFrame implements ActionListener {
    private JButton btnVoltar;
    private JButton btnSalvar;
    private JTextField txtNome;
    private JTextField txtEmail;
    private JTextField txtCpf;

    public TelaCadastroUsuario() {
        setTitle("Cadastro de Usuário");
        setSize(400, 250);
        setLocationRelativeTo(null);

        JPanel painel = new JPanel(new GridLayout(4, 2, 10, 10));
        painel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Campos de entrada
        painel.add(new JLabel("Nome:"));
        txtNome = new JTextField();
        painel.add(txtNome);

        painel.add(new JLabel("CPF/RG:"));
        txtCpf = new JTextField();
        painel.add(txtCpf);

        painel.add(new JLabel("Email:"));
        txtEmail = new JTextField();
        painel.add(txtEmail);

        // Botões
        btnSalvar = new JButton("Salvar");
        btnSalvar.addActionListener(this);

        btnVoltar = new JButton("Voltar");
        btnVoltar.addActionListener(this);

        painel.add(btnSalvar);
        painel.add(btnVoltar);

        add(painel);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        Object source = event.getSource();

        if (source == btnSalvar) {
            String nome = txtNome.getText().trim();
            String cpf = txtCpf.getText().trim();
            String email = txtEmail.getText().trim();

            if (nome.isEmpty() || cpf.isEmpty() || email.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Todos os campos devem ser preenchidos!");
                return;
            }

            try {
                Usuario u = new Usuario(nome, cpf, email);

                UsuarioDAO dao = new UsuarioDAO();
                dao.salvarUsuario(u);

                JOptionPane.showMessageDialog(this, "Usuário salvo com sucesso!");

                txtNome.setText("");
                txtCpf.setText("");
                txtEmail.setText("");

            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Erro ao salvar usuário: " + e.getMessage());
                e.printStackTrace();
            }

        } else if (source == btnVoltar) {
            this.dispose();
        }
    }
}
