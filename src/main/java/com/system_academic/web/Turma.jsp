<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ page import="View.ControleGeral"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="dominio.Turma"%>

<%
ArrayList<Turma> turmas = (ArrayList<Turma>) request.getAttribute("Turmas");
if (turmas != null) {
%>

<form name="FormTurma" action="FormTurma" method="get">
<table id="tabela">
	<thead>
		<tr>
			<th>Selecionar</th>
			<th>Id</th>
			<th>Turno</th>
			<th>Modulo</th>
			<th>Dia da Semana</th>
			<th>Ano</th>
			<th>Semestre</th>
			<th>Materia</th>
			<th>Descricao Materia</th>
			<th>Alunos Matriculados</th>
			
			
		</tr>
	</thead>
	<tbody>
		<%
		for (int i = 0; i < turmas.size(); i++) {
		
		%>
		<tr>
			<td><input type="radio" id="idEscolhido" name="idEscolhido" value="<%=turmas.get(i).getId()%>" 	class="Botao1"  /></td>
			<td><%=turmas.get(i).getId()%></td>
			<td><%=turmas.get(i).getTurno().name()%></td>
			<td><%=turmas.get(i).getModulo().name()%></td>
			<td><%=turmas.get(i).getDiaSemana()%></td>
			<td><%=turmas.get(i).getAno()%></td>
			<td><%=turmas.get(i).getSemestre()%></td>
			<td><%=turmas.get(i).getMateria().getNome()%></td>
			<td><%=turmas.get(i).getMateria().getDescricao()%></td>
			<td><%=turmas.get(i).getAlunos().toString().replace("[", "").replace(",", "").replace("]", "")%></td>
			
			

		</tr>
		<%
		}
		%>
	</tbody>
</table>
			<td><input type="submit" id="escolhaWeb" name="escolhaWeb" value="ALTERAR"	class="Botao1" /></td>
			<td><input type="submit" id="escolhaWeb" name="escolhaWeb" value="EXCLUIR"	class="Botao2" /> </td>
</form>
<%
}
%>
<!DOCTYPE html>
<html lang="pt-br">
<head>
<meta charset="utf-8">

<title>Sistema de Matrícula</title>
<link rel="icon" href=imagens/favicon.png>
<link rel="stylesheet" href=style.css>
</head>
<body>
	<img src="imagens/menuprincipal.png">
	<h1>Sistema de Matrícula - Engenharia de Software III - Curso.html</h1>




	<form name="FormTurma" action="FormTurma" method="get">
		<table>
			
			<tr>
				<td>
					<input type="number" name="txtAno"placeholder="Digite o ano da turma" class="Caixa2">
				</td>
			</tr>
			<tr>
				<td>
					<input type="number" name="txtSemestre" placeholder="Digite o semestre da turma" class="Caixa2">
				</td>
			</tr>
						<tr>
				<td>
					<input type="text" name="txtDiaSemana" placeholder="Digite o dia da semana SEGUNDA" class="Caixa2">
				</td>
			</tr>
			<tr>
				<td>
					<input type="text" name="txtTurno" placeholder="Digite turno MANHA" class="Caixa2">
				</td>
			</tr>
			<tr>
				<td>
					<input type="text" name="txtModulo" placeholder="Digite o modulo PRIMEIRO" class="Caixa2">
				</td>
			</tr>
		</table>
			<a href="index" class="Botao1">Página Inicial</a> 
		<input type="submit" id="escolhaWeb" name="escolhaWeb" value="CADASTRAR" class="Botao1" />
		<input type="submit" id="escolhaWeb"name="escolhaWeb" value="CONSULTAR" class="Botao1" />

	</form>
</body>
</html>