package com.system_academic.view;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.system_academic.domain.DomainEntity;
import com.system_academic.domain.Curso;

public class VhCurso implements IViewHelper {

	@Override
	public DomainEntity getEntidade(HttpServletRequest request) {

		try {
			String txtDescricao = request.getParameter("txtDescricao");
			String txtNome = request.getParameter("txtNome");
			Curso curso = new Curso(txtNome, txtDescricao);
			return curso;

		} catch (Exception e) {
		}
		return new Curso();

	}

	@Override
	public void setView(Object resultado, HttpServletRequest request, HttpServletResponse response)
			throws ServletException {

		if (resultado instanceof ArrayList) {
			request.setAttribute("Cursos", resultado);
			RequestDispatcher rd = request.getRequestDispatcher("Curso.jsp");
			try {
				rd.forward(request, response);
			} catch (ServletException | IOException e) {
				e.printStackTrace();
			}

		}

		PrintWriter out;
		StringBuilder paginaInicial = new StringBuilder("<a href=\"index\" class=\"Botao1\">P�gina Inicial</a>");

		try {
			System.out.println(resultado);

			out = response.getWriter();
			if (resultado != null) {
				out.println(resultado);
			} else {
				out.println("<h1>Solicita��o conclu�da - Curso</h1>");
				out.println(paginaInicial);

			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void updateView(Object resultado, HttpServletRequest request, HttpServletResponse response)
			throws ServletException {
		System.out.println("Na alterarView antes do if");
		System.out.println(resultado);

		try {
			if (resultado instanceof Curso) {
				request.setAttribute("id_curso", ((Curso) resultado).getId());
				request.setAttribute("nome_curso", ((Curso) resultado).getNome());
				request.setAttribute("descricao_curso", ((Curso) resultado).getDescricao());

				// enviar para o arquivo editar.jsp
				RequestDispatcher rd = request.getRequestDispatcher("editarCurso.jsp");
				rd.forward(request, response);

				// teste de recebimento do curso selecionado
				// System.out.println("Na alterarView");
				// System.out.println(((Curso) resultado).getId());
				// System.out.println(((Curso) resultado).getNome());
				// System.out.println(((Curso) resultado).getDescricao());

			}
		} catch (Exception e) {
		}

		PrintWriter out;
		try {
			System.out.println(resultado);

			out = response.getWriter();
			if (resultado != null) {
				out.println(resultado);
			} else {
				out.println("<h1>Cliente alterado!</h1>");
			}
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println(e);

		}
	}
}