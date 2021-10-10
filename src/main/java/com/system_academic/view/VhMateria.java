package com.system_academic.view;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.system_academic.domain.DomainEntity;
import com.system_academic.domain.Materia;

public class VhMateria implements IViewHelper {

	@Override
	public DomainEntity getEntidade(HttpServletRequest request) {
		try {
			String txtNome = request.getParameter("txtNome");
			String txtDescricao = request.getParameter("txtDescricao");
			Integer txtCargaHoraria = Integer.parseInt(request.getParameter("txtCargaHoraria"));
			Materia materia = new Materia(txtNome, txtCargaHoraria, txtDescricao);
			return materia;
		} catch (Exception e) {
		}
		return new Materia();
	}

	@Override
	public void setView(Object resultado, HttpServletRequest request, HttpServletResponse response)
			throws ServletException {
		if (resultado instanceof ArrayList) {
			request.setAttribute("Materias", resultado);
			RequestDispatcher rd = request.getRequestDispatcher("Materia.jsp");
			try {
				rd.forward(request, response);
			} catch (ServletException | IOException e) {
				e.printStackTrace();
			}

		}
		StringBuilder paginaInicial = new StringBuilder("<a href=\"index\" class=\"Botao1\">P�gina Inicial</a>");

		PrintWriter out;
		try {
			System.out.println(resultado);

			out = response.getWriter();
			if (resultado != null) {
				out.println(resultado);
			} else {
				out.println("<h1>Solicita��o conclu�da - Materia</h1>");
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
			if (resultado instanceof Materia) {

				request.setAttribute("id_materia", ((Materia) resultado).getId());
				request.setAttribute("nome_materia", ((Materia) resultado).getNome());
				request.setAttribute("descricao_materia", ((Materia) resultado).getDescricao());
				request.setAttribute("txtCargaHoraria", ((Materia) resultado).getId());

				// enviar para o arquivo editar.jsp
				RequestDispatcher rd = request.getRequestDispatcher("editarMateria.jsp");
				rd.forward(request, response);

				// teste de recebimento do curso selecionado
				// System.out.println("Na alterarView");
				// System.out.println(((Curso) resultado).getId());
				// System.out.println(((Curso) resultado).getNome());
				// System.out.println(((Curso) resultado).getDescricao());

			}
		} catch (Exception e) {
			System.out.println(e);
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
