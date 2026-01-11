package Negocio;

import Modelo.Livro;
import Persistencia.LivroDAO;

public class LivroService {

    public void salvar(Livro livro) {
        LivroDAO dao = new LivroDAO();
        dao.inserir(livro);
    }
}
