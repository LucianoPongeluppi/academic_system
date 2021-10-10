<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ page import="View.ControleGeral"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="dominio.Materia"%>

<%
ArrayList<Materia> materias = (ArrayList<Materia>) request.getAttribute("Materias");
if (materias != null) {
%>

	<form name="FormMateria" action="FormMateria" method="get">

<table id="tabela">
	<thead>
		<tr>
			<th>Selecionar</th>
			<th>Id</th>
			<th>Nome</th>
			<th>Descricao</th>
			<th>Nome da Classe</th>
			
		</tr>
	</thead>
	<tbody>
		<%
		for (int i = 0; i < materias.size(); i++) {
		%>
		<tr>
			<td><input type="radio" id="idEscolhido" name="idEscolhido" value="<%=materias.get(i).getId()%>" 	class="Botao1"  /></td>
			<td><%=materias.get(i).getId()%></td>
			<td><%=materias.get(i).getNome() %></td>
			<td><%=materias.get(i).getDescricao()%></td>
			<td><%=materias.get(i).getClass().getName()%></td>

			
			</td>

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

	<form name="FormMateria" action="FormMateria" method="get">
		<table>
			<tr>
				<td><input type="text" name="txtNome"	placeholder="Digite o nome da materia" class="Caixa1">
				</td>
			</tr>
			
			<tr>
				<td>
					<input type="text" name="txtDescricao" placeholder="Digite a descricao" class="Caixa2">
				</td>
			</tr>
			<tr>
				<td>
					<input type="text" name="txtCargaHoraria" placeholder="Digite a carga horaria" class="Caixa2">
				</td>
			</tr>


		</table>
				<a href="index" class="Botao1">Página Inicial</a> 
		<input type="submit" id="escolhaWeb" name="escolhaWeb" value="CADASTRAR" class="Botao1" />
		<input type="submit" id="escolhaWeb"name="escolhaWeb" value="CONSULTAR" class="Botao1" />


	</form>
</body>
</html>