package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entities.Usuario;
import util.JdbcUtil;

public class UsuarioDAOImpl implements UsuarioDAO {

	public void inserir(Usuario usuario) {
		String sql = "insert into USUARIO (nome, email, senha) values (?, ?, ?)";

		Connection conexao;
		try {
			conexao = JdbcUtil.getConexao();

			PreparedStatement ps = conexao.prepareStatement(sql);

			ps.setString(1, usuario.getNome());
			ps.setString(2, usuario.getEmail());
			ps.setString(3, usuario.getSenha());

			ps.execute();
			ps.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public Usuario pesquisar(String email) {

		/**
		 * o email passado como parametro vai ser setado, sendo definido como a
		 * restrição da busca no "where" no DB.
		 */

		String sql = "select U.NOME, U.EMAIL, U.SENHA from USUARIO U where email = ?";

		Connection conexao;
		Usuario usuario = null;

		try {
			conexao = JdbcUtil.getConexao();

			PreparedStatement ps = conexao.prepareStatement(sql);

			ps.setString(1, email);

			ResultSet res = ps.executeQuery();

			while (res.next()) {
				usuario = new Usuario();
				usuario.setNome(res.getString("NOME"));
				usuario.setEmail(res.getString("EMAIL"));
				usuario.setSenha(res.getString("SENHA"));
			}

			ps.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return usuario;
	}

	public List<Usuario> listarTodosUsuarios() {

		String sql = "select U.NOME, U.EMAIL, U.SENHA from USUARIO U";

		List<Usuario> listaUsuarios = new ArrayList<Usuario>();

		Connection conexao;
		try {
			conexao = JdbcUtil.getConexao();

			PreparedStatement ps = conexao.prepareStatement(sql);

			ResultSet res = ps.executeQuery();

			while (res.next()) {

				Usuario usuario = new Usuario();
				usuario.setNome(res.getString("NOME"));
				usuario.setEmail(res.getString("EMAIL"));
				usuario.setSenha(res.getString("SENHA"));

				listaUsuarios.add(usuario);
			}

			ps.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return listaUsuarios;

	}

}
