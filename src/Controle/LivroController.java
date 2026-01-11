package Controle;

import Modelo.Livro;
import Negocio.LivroService;

public class LivroController {

    public void salvar(Livro livro) {
        LivroService service = new LivroService();
        service.salvar(livro);
    }
}
