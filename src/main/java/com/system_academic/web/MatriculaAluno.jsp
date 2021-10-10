<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ page import="View.ControleGeral"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="dominio.AlunoTurma"%>

<%
ArrayList<AlunoTurma> alunosTurmas = (ArrayList<AlunoTurma>) request.getAttribute("AlunosTurmas");
if (alunosTurmas != null) {
%>

<form name="FormMatricula" action="FormMatricula" method="get">
<table id="tabela">
	<thead>
		<tr>
			<th>Id do Aluno</th>
			<th>Nome do Aluno</th>
			<th>Nome do Curso</th>
			<th>Matéria </th>
			<th>Id da Turma</th>
			
		</tr>
	</thead>
	<tbody>
		<%
		for (int i = 0; i < alunosTurmas.size(); i++) {
		%>
		<tr>
			<td><%=alunosTurmas.get(i).getId_aluno()%></td>
			<td><%=alunosTurmas.get(i).getNomeAluno()%></td>
			<td><%=alunosTurmas.get(i).getNomeCurso()%></td>
			<td><%=alunosTurmas.get(i).getNomeMateria()%></td>
			<td><%=alunosTurmas.get(i).getId_turma()%></td>

		</tr>
		<%
		}
		%>
	</tbody>
</table>
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

	<form name="FormMatricula" action="FormMatricula" method="get">
		<table>
			
			
			<tr>	
				<td><input type="text" name="id_turma" placeholder="Digite o id da turma" class="Caixa2"></td>
			</tr>
			<tr>
				<td><input type="text" name="id_aluno"	placeholder="Digite o id do aluno" class="Caixa1"></td>
			</tr>

		</table>
				<a href="index" class="Botao1">Página Inicial</a> 
		<input type="submit" id="escolhaWeb" name="escolhaWeb" value="CADASTRAR" class="Botao1" />
		<input type="submit" id="escolhaWeb"name="escolhaWeb" value="CONSULTAR" class="Botao1" />


	</form>
</body>
</html>>