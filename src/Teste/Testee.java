package Teste;

import Modelo.Usuario;
import Persistencia.UsuarioDAO;

public class Testee {
    public static void main(String[] args) {
        Usuario u = new Usuario();
        u.setNome("Enzo Anthony");
        u.setEmail("enzo@example.com");

        System.out.println("Nome antes de salvar: " + u.getNome());
        System.out.println("Email antes de salvar: " + u.getEmail());

        UsuarioDAO dao = new UsuarioDAO();
        dao.salvarUsuario(u);
    }
}
