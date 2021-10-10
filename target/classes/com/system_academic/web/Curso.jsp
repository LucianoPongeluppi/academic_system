<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ page import="View.ControleGeral"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="dominio.Curso"%>

<%
ArrayList<Curso> cursos = (ArrayList<Curso>) request.getAttribute("Cursos");
if (cursos != null) {
%>

<form name="FormCurso" action="FormCurso" method="get">
<table id="tabela">
	<thead>
		<tr>
			<th>Selecionar</th>
			<th>Id</th>
			<th>Nome</th>
			<th>Descricao</th>
		</tr>
	</thead>
	<tbody>
		<%
		for (int i = 0; i < cursos.size(); i++) {
		%>
		<tr>
			<td><input type="radio" id="idEscolhido" name="idEscolhido" value="<%=cursos.get(i).getId()%>" 	class="Botao1"  /></td>
			<td><%=cursos.get(i).getId()%></td>
			<td><%=cursos.get(i).getNome()%></td>
			<td><%=cursos.get(i).getDescricao()%></td>
			
			</td>
		
		</tr>
		
		<%
		}
		%>
	</tbody>
</table>
			<td><input type="submit" id="escolhaWeb" name="escolhaWeb" value="ALTERAR" 	class="Botao1"/></td>
			<td><input type="submit" id="escolhaWeb" name="escolhaWeb" value="EXCLUIR"	class="Botao1"/></td>
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
	<h1>Sistema de Matrícula - Engenharia de Software III</h1>


	<form name="FormCurso" action="FormCurso" method="get">
		<table>
			<tr>
				<td>
					<input type="text" name="txtNome"placeholder="Nome do curso" class="Caixa1">
				</td>
			</tr>
			<tr>
				<td>
					<input type="text" name="txtDescricao" placeholder="Descricao do curso" class="Caixa2">
				</td>
			</tr>

		</table>
		<a href="index" class="Botao1">Página Inicial</a> 
		<input type="submit" id="escolhaWeb" name="escolhaWeb" value="CADASTRAR" class="Botao1" />
		<input type="submit" id="escolhaWeb"name="escolhaWeb" value="CONSULTAR" class="Botao1" />

	</form>
</body>
</html>