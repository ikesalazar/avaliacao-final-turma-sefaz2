package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entities.Funcionario;
import util.JdbcUtil;

public class FuncionarioDaoImpl implements FuncionarioDao {

	public void inserir(Funcionario funcionario) {
		String sql = "INSERT INTO funcionario(cpf, nome, email, telefone) " + "  values(?, ?, ?, ?)";

		Connection conexao;
		try {
			conexao = JdbcUtil.getConexao();

			PreparedStatement ps = conexao.prepareStatement(sql);

			ps.setString(1, funcionario.getCpf());
			ps.setString(2, funcionario.getNome());
			ps.setString(3, funcionario.getEmail());
			ps.setString(4, funcionario.getTelefone());

			ps.execute();

			ps.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void alterar(Funcionario funcionario) {
		String sql = "UPDATE funcionario SET nome = ?, email = ?, telefone = ? where cpf = ?";

		Connection conexao;
		try {
			conexao = JdbcUtil.getConexao();

			PreparedStatement ps = conexao.prepareStatement(sql);

			ps.setString(1, funcionario.getNome());
			ps.setString(2, funcionario.getEmail());
			ps.setString(3, funcionario.getTelefone());
			ps.setString(4, funcionario.getCpf());

			ps.executeUpdate();

			ps.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void remover(Funcionario funcionario) {
		String sql = "DELETE FROM funcionario WHERE cpf = ?";

		Connection conexao;
		try {
			conexao = JdbcUtil.getConexao();

			PreparedStatement ps = conexao.prepareStatement(sql);

			ps.setString(1, funcionario.getCpf());

			ps.execute();
			ps.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public Funcionario pesquisar(String telefone) {
		String sql = "select F.CPF, F.NOME, F.EMAIL, F.TELEFONE from FUNCIONARIO F where telefone = ?";

		Funcionario funcionario = null;

		Connection conexao;
		try {
			conexao = JdbcUtil.getConexao();

			PreparedStatement ps = conexao.prepareStatement(sql);

			ps.setString(1, telefone);

			ResultSet res = ps.executeQuery();

			while (res.next()) {
				funcionario = new Funcionario();
				funcionario.setCpf(res.getString("CPF"));
				funcionario.setNome(res.getString("NOME"));
				funcionario.setEmail(res.getString("EMAIL"));
				funcionario.setTelefone(res.getString("TELEFONE"));

			}

			ps.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return funcionario;
	}

	public List<Funcionario> listarTodosFuncionarios() {

		String sql = "select F.CPF, F.NOME, F.EMAIL, F.TELEFONE from FUNCIONARIO F";

		List<Funcionario> listaFuncionarios = new ArrayList<Funcionario>();

		Connection conexao;
		try {
			conexao = JdbcUtil.getConexao();

			PreparedStatement ps = conexao.prepareStatement(sql);

			ResultSet res = ps.executeQuery();

			while (res.next()) {

				Funcionario funcionario = new Funcionario();
				funcionario.setCpf(res.getString("CPF"));
				funcionario.setNome(res.getString("NOME"));
				funcionario.setEmail(res.getString("EMAIL"));
				funcionario.setTelefone(res.getString("TELEFONE"));
				listaFuncionarios.add(funcionario);
			}

			ps.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return listaFuncionarios;
	}

	public List<Funcionario> pesquisar(Funcionario funcionario) {

		return null;
	}

}
