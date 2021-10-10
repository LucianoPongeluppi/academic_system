<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ page import="View.ControleGeral"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="dominio.Curso"%>

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
	<h1>ALTERAR materia - JSP</h1>




	<form name="FormMateria" action="FormMateria" method="get">
		<table>
			<tr>
				<td>
					<input type="text" name="idEscolhido" id="caixa3" readonly value="<%out.print(request.getAttribute("id_materia"));%>">
				</td>
			</tr>
			
			<tr>
				<td>
					<input type="text" name="txtNome" class="Caixa2" value="<%out.print(request.getAttribute("nome_materia"));%>">
				</td>
			</tr>
			
					<tr>
				<td>
					<input type="text" name="txtDescricao" class="Caixa2" value="<%out.print(request.getAttribute("descricao_materia"));%>">
				</td>
			</tr>
			
					<tr>
				<td>
					<input type="text" name="txtCargaHoraria" class="Caixa2" value="<%out.print(request.getAttribute("txtCargaHoraria"));%>">
				</td>
			</tr>
			
		
		</table>
		<input type="submit" id="escolhaWeb" name="escolhaWeb" value="SALVAR" class="Botao1" />
		<a href="index" class="Botao2">Cancelar</a> 
	</form>
</body>
</html>