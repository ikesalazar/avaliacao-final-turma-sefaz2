package controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import dao.FuncionarioDao;
import dao.FuncionarioDaoImpl;
import entities.Funcionario;
import entities.Usuario;

@ManagedBean(name = "FuncionarioBean")
@SessionScoped
public class FuncionarioBean {

	private Funcionario funcionario;

	private FuncionarioDao funcionarioDAO;

	private List<Funcionario> listaFuncionarios;

	private Usuario sessionUsuario;

	public FuncionarioBean() {

		this.funcionarioDAO = new FuncionarioDaoImpl();

		this.funcionario = new Funcionario();

		this.listaFuncionarios = new ArrayList<Funcionario>();

		this.atualizarSessionUsuario();
	}

	public void atualizarSessionUsuario() {
		HttpSession sessao = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		this.sessionUsuario = (Usuario) sessao.getAttribute("usuarioLogado");
	}

	public void salvar() {

		this.funcionarioDAO.inserir(this.funcionario);
		this.funcionario = new Funcionario();
	}

	public void pesquisar() {

		this.listaFuncionarios = this.funcionarioDAO.pesquisar(this.funcionario);
	}

	public void atualizar() {

		this.funcionarioDAO.alterar(this.funcionario);
		this.funcionario = new Funcionario();
	}

	public void deletar() {

		this.funcionarioDAO.remover(this.funcionario);
		this.funcionario = new Funcionario();
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public List<Funcionario> getListaFuncionarios() {
		return listaFuncionarios;
	}

	public void setListaFuncionarios(List<Funcionario> listaFuncionarios) {
		this.listaFuncionarios = listaFuncionarios;
	}

	public Usuario getSessionUsuario() {
		return sessionUsuario;
	}

	public void setSessionUsuario(Usuario sessionUsuario) {
		this.sessionUsuario = sessionUsuario;
	}
}
