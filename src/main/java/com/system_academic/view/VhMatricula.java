package com.system_academic.view;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.system_academic.domain.DomainEntity;
import com.system_academic.domain.AlunoTurma;

public class VhMatricula implements IViewHelper {

	@Override
	public DomainEntity getEntidade(HttpServletRequest request) {

		try {
			int id_aluno = Integer.parseInt(request.getParameter("id_aluno"));
			int id_turma = Integer.parseInt(request.getParameter("id_turma"));
			AlunoTurma aluTur = new AlunoTurma(id_turma, id_aluno);
			return aluTur;
		} catch (Exception e) {
		}
		return new AlunoTurma();

	}

	@Override
	public void setView(Object resultado, HttpServletRequest request, HttpServletResponse response)
			throws ServletException {

		if (resultado instanceof ArrayList) {
			request.setAttribute("AlunosTurmas", resultado);
			RequestDispatcher rd = request.getRequestDispatcher("MatricularAluno.jsp");
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
				out.println("<h1>Solicita��o conclu�da - Matricula</h1>");
				out.println(paginaInicial);

			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void updateView(Object resultado, HttpServletRequest request, HttpServletResponse response)
			throws ServletException {

		PrintWriter out;
		try {
			System.out.println(resultado);

			out = response.getWriter();
			if (resultado != null) {
				out.println(resultado);
			} else {
				out.println("<h1>Matr�cula excluida!</h1>");
			}
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println(e);

		}
	}

}
