package com.system_academic.business;

import com.system_academic.domain.Aluno;
import com.system_academic.controller.IStrategy;
import com.system_academic.domain.DomainEntity;

public class ValidadorCpf implements IStrategy {
    @Override
	public String process(DomainEntity entidade) {
		if (entidade instanceof Aluno) {
			Aluno a = (Aluno) entidade;
			System.out.println("Na hora de processar");
			System.out.println(a.getCPF());
			System.out.println(a.getEmail());
			System.out.println(a.getId());
			System.out.println(a.getNome());
			System.out.println(a.getNome_mae());
			
			if (a.getId()==0) {
			
			if (a.getCPF().equals("00000000000") || a.getCPF().equals("11111111111") ||
				a.getCPF().equals("22222222222") || a.getCPF().equals("33333333333") ||
				a.getCPF().equals("44444444444") || a.getCPF().equals("55555555555") ||
				a.getCPF().equals("66666666666") || a.getCPF().equals("77777777777") ||
				a.getCPF().equals("88888888888") || a.getCPF().equals("99999999999") ||
	            (a.getCPF().length() != 11))
	            return "CPF Inválido!";

			char dig10, dig11;
	        int total, i, resultado, num, peso;
	        
	        try {
	            total = 0;
	            peso = 10;
	            for (i=0; i<9; i++) {
		            num = (int)(a.getCPF().charAt(i) - 48);
		            total = total + (num * peso);
		            peso = peso - 1;
	            }

	            resultado = 11 - (total % 11);
	            if ((resultado == 10) || (resultado == 11))
	                dig10 = '0';
	            else 
	            	dig10 = (char)(resultado + 48); 
	            total = 0;
	            peso = 11;
	            for(i=0; i<10; i++) {
		            num = (int)(a.getCPF().charAt(i) - 48);
		            total = total + (num * peso);
		            peso = peso - 1;
	            }

	            resultado = 11 - (total % 11);
	            if ((resultado == 10) || (resultado == 11))
	                 dig11 = '0';
	            else 
	            	dig11 = (char)(resultado + 48);
	            
	            if ((dig10 == a.getCPF().charAt(9)) && (dig11 == a.getCPF().charAt(10)))
	                 return null;
	            else
	            	return "CPF Inválido!";
	        } catch (Exception erro) {
	                return "Algo inesperado aconteceu!";
	        }
		}
		return null;
	}
	return null;
    }
}