package dao;

import java.util.List;

import entities.Funcionario;

public interface FuncionarioDao {
	public void inserir(Funcionario funcionario);

	public void alterar(Funcionario funcionario);

	public void remover(Funcionario funcionario);

	public Funcionario pesquisar(String telefone);

	public List<Funcionario> listarTodosFuncionarios();

	public List<Funcionario> pesquisar(Funcionario funcionario);

}
