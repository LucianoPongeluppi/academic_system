package com.system_academic.view;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import com.system_academic.controller.CreateCommand;
import com.system_academic.controller.DeleteCommand;
import com.system_academic.controller.ICommand;
import com.system_academic.controller.ReadCommand;
import com.system_academic.controller.SelectCommand;
import com.system_academic.controller.UpdateCommand;
import com.system_academic.domain.DomainEntity;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = { "/index", "/Curso", "/Aluno", "/Materia", "/Turma", "/MatricularAluno" })
public class ControleGeral extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Map<String, ICommand> commands;
	private static Map<String, IViewHelper> vhs;
	private static Map<String, String> navegador;

	public ControleGeral() {
		super();
		commands = new HashMap<String, ICommand>();
		commands.put("CADASTRAR", new CreateCommand());
		commands.put("EXCLUIR", new DeleteCommand());
		commands.put("CONSULTAR", new ReadCommand());
		commands.put("ALTERAR", new SelectCommand());
		commands.put("SALVAR", new UpdateCommand());

		vhs = new HashMap<String, IViewHelper>();
		vhs.put("/SistemaMatricula/FormCurso", new VhCurso());
		vhs.put("/SistemaMatricula/FormAluno", new VhAluno());
		vhs.put("/SistemaMatricula/FormMateria", new VhMateria());
		vhs.put("/SistemaMatricula/FormTurma", new VhTurma());
		vhs.put("/SistemaMatricula/FormMatricula", new VhMatricula());

		navegador = new HashMap<String, String>();
		navegador.put("/Aluno", "Aluno.jsp");
		navegador.put("/Curso", "Curso.jsp");
		navegador.put("/Materia", "Materia.jsp");
		navegador.put("/Turma", "Turma.jsp");
		navegador.put("/index", "index.jsp");
		navegador.put("/MatricularAluno", "MatricularAluno.jsp");
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			String escolhaWeb = null;
			Integer idEscolhido = null;
			escolhaWeb = request.getParameter("escolhaWeb");
			if (escolhaWeb != null && escolhaWeb.equals("CADASTRAR")) {
				String uri = request.getRequestURI();
				IViewHelper vh = vhs.get(uri);
				DomainEntity entidade = vh.getEntidade(request);
				ICommand cmd = commands.get(escolhaWeb);
				Object msg = cmd.execute(entidade);
				System.out.println("Valor da mensagem: " + msg + "   Valor da entidade: " + entidade.toString());
				vh.setView(msg, request, response);

			} else if (escolhaWeb != null && escolhaWeb.equals("CONSULTAR")) {
				String uri = request.getRequestURI();
				IViewHelper vh = vhs.get(uri);
				DomainEntity entidade = vh.getEntidade(request);
				ICommand cmd = commands.get(escolhaWeb);
				Object msg = cmd.execute(entidade);
				vh.setView(msg, request, response);

			} else if (escolhaWeb != null && escolhaWeb.equals("EXCLUIR")) {
				idEscolhido = Integer.parseInt(request.getParameter("idEscolhido"));
				String uri = request.getRequestURI();
				System.out.println("Entrou na request para alterar");
				IViewHelper vh = vhs.get(uri);
				DomainEntity entidade = vh.getEntidade(request);
				entidade.setId(idEscolhido);
				ICommand cmd = commands.get(escolhaWeb);
				Object msg = cmd.execute(entidade);
				vh.setView(msg, request, response);

			} else if (escolhaWeb != null && escolhaWeb.equals("ALTERAR")) {
				idEscolhido = Integer.parseInt(request.getParameter("idEscolhido"));
				String uri = request.getRequestURI();
				System.out.println("Entrou na request para alterar");
				IViewHelper vh = vhs.get(uri);
				System.out.println(uri);
				DomainEntity entidade = vh.getEntidade(request);
				entidade.setId(idEscolhido);
				ICommand cmd = commands.get(escolhaWeb);
				System.out.println("Classe da entidade: " + entidade.getClass());
				Object msg = cmd.execute(entidade);
				vh.updateView(msg, request, response);

			} else if (escolhaWeb != null && escolhaWeb.equals("SALVAR")) {
				String uri = request.getRequestURI();
				System.out.println("Entrou na request para SALVAR");
				idEscolhido = Integer.parseInt(request.getParameter("idEscolhido"));
				System.out.println();
				System.out.println("Valor do id");
				System.out.println(idEscolhido);
				IViewHelper vh = vhs.get(uri);
				DomainEntity entidade = vh.getEntidade(request);
				entidade.setId(idEscolhido);
				ICommand cmd = commands.get(escolhaWeb);
				System.out.println("Classe da entidade: " + entidade.getClass());
				Object msg = cmd.execute(entidade);
				vh.setView(msg, request, response);
				response.sendRedirect("index.jsp");

			} else {
				String pagina = request.getServletPath();
				response.sendRedirect(navegador.get(pagina));
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}
