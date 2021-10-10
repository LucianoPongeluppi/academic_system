package com.system_academic.view;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import com.system_academic.domain.DomainEntity;
import com.system_academic.domain.Aluno;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class VhAluno implements IViewHelper {

	@Override
	public DomainEntity getEntidade(HttpServletRequest request) {
		try {
			String txtNome = request.getParameter("txtNome");
			String txtCPF = request.getParameter("txtCPF");
			String txtNomeMae = request.getParameter("txtNomeMae");
			String txtEmail = request.getParameter("txtEmail");
			Aluno aluno = new Aluno(txtNome, txtCPF, txtNomeMae, txtEmail);
			return aluno;
		} catch (Exception e) {

		}
		return new Aluno();
	}

	@Override
	public void setView(Object resultado, HttpServletRequest request, HttpServletResponse response)
			throws ServletException {

		if (resultado instanceof ArrayList) {
			request.setAttribute("Alunos", resultado);
			RequestDispatcher rd = request.getRequestDispatcher("Aluno.jsp");
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
				out.println("<h1>Solicita��o conclu�da - Aluno</h1>");
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
			if (resultado instanceof Aluno) {
				request.setAttribute("id_aluno", ((Aluno) resultado).getId());
				request.setAttribute("nome_aluno", ((Aluno) resultado).getNome());
				request.setAttribute("CPF_aluno", ((Aluno) resultado).getCPF());
				request.setAttribute("email_aluno", ((Aluno) resultado).getEmail());
				request.setAttribute("nomeMae_aluno", ((Aluno) resultado).getNome_mae());

				RequestDispatcher rd = request.getRequestDispatcher("editarAluno.jsp");
				rd.forward(request, response);
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