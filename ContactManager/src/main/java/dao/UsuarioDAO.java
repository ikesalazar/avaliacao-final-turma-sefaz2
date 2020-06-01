package dao;

import java.util.List;

import entities.Usuario;

public interface UsuarioDAO {

	public void inserir(Usuario usuario);

	public Usuario pesquisar(String email);

	public List<Usuario> listarTodosUsuarios();

}
