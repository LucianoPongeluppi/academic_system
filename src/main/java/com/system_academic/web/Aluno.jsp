<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ page import="View.ControleGeral"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="dominio.Aluno"%>

<%
ArrayList<Aluno> alunos = (ArrayList<Aluno>) request.getAttribute("Alunos");
if (alunos != null) {
%>

<form name="FormAluno" action="FormAluno" method="get">
<table id="tabela">
	<thead>
		<tr>
			<th>Selecionar</th>
			<th>Id</th>
			<th>Nome</th>
			<th>CPF</th>
			<th>Nome da mãe</th>
			<th>Email</th>
			<th>Matricular Realizadas</th>
		</tr>
	</thead>
	<tbody>
		<%
		for (int i = 0; i < alunos.size(); i++) {
		%>
		<tr>
			<td><input type="radio" id="idEscolhido" name="idEscolhido" value="<%=alunos.get(i).getId()%>" 	class="Botao1"  /></td>
			</td>
			<td><%=alunos.get(i).getId()%></td>
			<td><%=alunos.get(i).getNome()%></td>
			<td><%=alunos.get(i).getCPF()%></td>
			<td><%=alunos.get(i).getNome_mae()%></td>
			<td><%=alunos.get(i).getEmail()%></td>
			<td><%=alunos.get(i).getTurmas().toString().replace("[", "").replace(",", "").replace("]", "")%></td>

			

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
	<h1>Sistema de Matrícula - Engenharia de Software III - Aluno.jsp</h1>

	<form name="FormAluno" action="FormAluno" method="get">
		<table>
			<tr>
				<td><input type="text" name="txtNome"
					placeholder="Nome do aluno" class="Caixa1"></td>
			</tr>
			<tr>
				<td><input type="text" name="txtNomeMae"
					placeholder="Nome da mae do aluno" class="Caixa2"></td>
			</tr>
			<tr>
				<td><input type="text" name="txtCPF"
					placeholder="CPF com 11 digitos" class="Caixa2"></td>
			</tr>
			<tr>
				<td><input type="text" name="txtEmail"
					placeholder="Email" class="Caixa2"></td>
			</tr>

			
		</table>
				<a href="index" class="Botao1">Página Inicial</a> 
		<input type="submit" id="escolhaWeb" name="escolhaWeb" value="CADASTRAR" class="Botao1" />
		<input type="submit" id="escolhaWeb"name="escolhaWeb" value="CONSULTAR" class="Botao1" />
	</form>
</body>
</html>