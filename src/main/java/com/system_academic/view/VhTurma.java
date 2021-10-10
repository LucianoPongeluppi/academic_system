package com.system_academic.view;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import com.system_academic.domain.DomainEntity;
import com.system_academic.domain.Turma;
import com.system_academic.domain.enums.Modulo;
import com.system_academic.domain.enums.Semana;
import com.system_academic.domain.enums.Turno;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class VhTurma implements IViewHelper {
	@Override
	public DomainEntity getEntidade(HttpServletRequest request) {

		try {
			int txtAno = Integer.parseInt(request.getParameter("txtAno"));
			int txtSemestre = Integer.parseInt(request.getParameter("txtSemestre"));
			Turno txtTurno = Turno.valueOf(request.getParameter("txtTurno"));
			Modulo txtModulo = Modulo.valueOf(request.getParameter("txtModulo"));
			Semana txtDiaSemana = Semana.valueOf(request.getParameter("txtDiaSemana"));
			Turma turma = new Turma(txtAno, txtSemestre, txtModulo, txtTurno, txtDiaSemana);
			return turma;
		} catch (Exception e) {

		}
		return new Turma();
	}

	@Override
	public void setView(Object resultado, HttpServletRequest request, HttpServletResponse response)
			throws ServletException {

		if (resultado instanceof ArrayList) {
			request.setAttribute("Turmas", resultado);
			RequestDispatcher rd = request.getRequestDispatcher("Turma.jsp");
			try {
				rd.forward(request, response);
			} catch (ServletException | IOException e) {
				e.printStackTrace();
			}
		}
		PrintWriter out;
		StringBuilder paginainicial = new StringBuilder("<a href=\"index\" class=\"Botao1\">P�gina Inicial</a>");

		try {
			System.out.println(resultado);

			out = response.getWriter();
			if (resultado != null) {
				out.println(resultado);
			} else {
				out.println("<h1>Solicita��o conclu�da - Turma</h1>");
				out.println(paginainicial);
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
			if (resultado instanceof Turma) {
				request.setAttribute("id_turma", ((Turma) resultado).getId());
				request.setAttribute("txtAno", ((Turma) resultado).getAno());
				request.setAttribute("txtSemestre", ((Turma) resultado).getSemestre());
				request.setAttribute("txtTurno", ((Turma) resultado).getTurno().name());
				request.setAttribute("txtModulo", ((Turma) resultado).getModulo().name());
				request.setAttribute("txtDiaSemana", ((Turma) resultado).getDiaSemana().name());

				RequestDispatcher rd = request.getRequestDispatcher("editarTurma.jsp");
				rd.forward(request, response);
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
				out.println("<h1>Turma alterada!</h1>");
			}
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println(e);
		}
	}
}