package com.system_academic.business;

import com.system_academic.controller.IStrategy;
import com.system_academic.domain.Aluno;
import com.system_academic.domain.DomainEntity;

public class ValidadorAluno implements IStrategy {
    @Override
    public String process(DomainEntity entidade) {

        if(entidade instanceof Aluno){
            Aluno aluno = (Aluno)entidade;

            String nome = aluno.getNome();
            String nomeMae = aluno.getNome_mae();
            String email = aluno.getEmail();

            StringBuilder sb = new StringBuilder();
            if(nome == null){
                sb.append("Nome é de preenchimento obrigatório!\n");
            }
            if(nomeMae == null) {
                sb.append("Nome da mãe é de preenchimento obrigatório!\n");
            }
            if(email == null){
                sb.append("Email é de preenchimento obrigatório!\n");
            }
            if(nome.trim().equals("")){
                sb.append("O Nome deve ser preenchido corretamente!\n");
            }
            if(sb.length()>0) {
                return sb.toString();
            }else {
                return null;
            }
        }else{
            return "Deve ser registrado um Aluno!";
        }
    }
}