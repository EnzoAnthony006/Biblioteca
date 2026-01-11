package Modelo;

public class Livro {
    private int id;
    private String titulo;
    private String autor;
    private String isbn;
    private int ano;
    private String status;

    public Livro() {
        this.status = "DISPONIVEL";
    }

    public Livro(String titulo, String autor, String isbn, int ano) {
        this.titulo = titulo;
        this.autor = autor;
        this.isbn = isbn;
        this.ano = ano;
        this.status = "DISPONIVEL";
    }

    public Livro(int id, String titulo, String autor, String isbn, int ano, String status) {
        this.id = id;
        this.titulo = titulo;
        this.autor = autor;
        this.isbn = isbn;
        this.ano = ano;
        this.status = status;
    }

    public Livro(int livroId, String titulo, String autor, String isbn, int ano) {
        this.id = livroId;
        this.titulo = titulo;
        this.autor = autor;
        this.isbn = isbn;
        this.ano = ano;
        this.status = "DISPONIVEL";
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public String getAutor() { return autor; }
    public void setAutor(String autor) { this.autor = autor; }

    public String getIsbn() { return isbn; }
    public void setIsbn(String isbn) { this.isbn = isbn; }

    public int getAno() { return ano; }
    public void setAno(int ano) { this.ano = ano; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    @Override
    public String toString() {
        return titulo;
    }
}
