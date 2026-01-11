package Apresentacao;

import Controle.LivroController;
import Modelo.Livro;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaCadastroLivro extends JFrame implements ActionListener {

    private JFrame parent;
    private JTextField txttitulo;
    private JTextField txtautor;
    private JTextField txtano;
    private JTextField txtISBN;
    private JButton btnOk;
    private JButton btnVoltar;

    public TelaCadastroLivro() {
        this.parent = parent;

        setTitle("Cadastro de Livros");
        setResizable(false);
        setSize(378, 335);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setLayout(null);

        btnOk = new JButton("OK");
        btnOk.setBounds(134, 223, 89, 23);
        btnOk.setActionCommand("ok");
        btnOk.addActionListener(this);
        getContentPane().add(btnOk);

        btnVoltar = new JButton("Voltar");
        btnVoltar.setBounds(0, 273, 362, 23);
        btnVoltar.addActionListener(e -> {
            if (parent != null) {
                parent.setVisible(true);
            }
            dispose();
        });
        getContentPane().add(btnVoltar);

        JLabel lblTitulo = new JLabel("Titulo:");
        lblTitulo.setBounds(69, 56, 46, 14);
        getContentPane().add(lblTitulo);

        JLabel lblAutor = new JLabel("Autor:");
        lblAutor.setBounds(69, 81, 46, 14);
        getContentPane().add(lblAutor);

        JLabel lblISBN = new JLabel("ISBN:");
        lblISBN.setBounds(69, 106, 46, 14);
        getContentPane().add(lblISBN);

        JLabel lblAno = new JLabel("Ano:");
        lblAno.setBounds(69, 131, 36, 14);
        getContentPane().add(lblAno);

        txttitulo = new JTextField();
        txttitulo.setBounds(137, 53, 120, 20);
        getContentPane().add(txttitulo);

        txtautor = new JTextField();
        txtautor.setBounds(137, 78, 120, 20);
        getContentPane().add(txtautor);

        txtISBN = new JTextField();
        txtISBN.setBounds(137, 103, 120, 20);
        getContentPane().add(txtISBN);

        txtano = new JTextField();
        txtano.setBounds(137, 128, 120, 20);
        getContentPane().add(txtano);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if ("ok".equals(e.getActionCommand())) {

            Livro livro = new Livro();
            livro.setTitulo(txttitulo.getText());
            livro.setAutor(txtautor.getText());
            livro.setIsbn(txtISBN.getText());
            livro.setAno(Integer.parseInt(txtano.getText()));

            LivroController controller = new LivroController();
            controller.salvar(livro);

            JOptionPane.showMessageDialog(this, "Livro cadastrado com sucesso");

            txttitulo.setText("");
            txtautor.setText("");
            txtISBN.setText("");
            txtano.setText("");
        }
    }
}
