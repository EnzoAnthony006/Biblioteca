package Modelo;

public class Emprestimo {

    private int id;
    private Usuario usuario;
    private Livro livro;
    private String dataEmprestimo;
    private boolean devolvido;

    public Emprestimo(Usuario usuario, Livro livro, String dataEmprestimo, boolean devolvido) {
        this.usuario = usuario;
        this.livro = livro;
        this.dataEmprestimo = dataEmprestimo;
        this.devolvido = devolvido;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public Livro getLivro() {
        return livro;
    }

    public String getDataEmprestimo() {
        return dataEmprestimo;
    }

    public boolean isDevolvido() {
        return devolvido;
    }

    public void setDevolvido(boolean devolvido) {
        this.devolvido = devolvido;
    }
}
