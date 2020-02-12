package upe.poli.ecomp.ed.atividade;

import java.util.Scanner;

public class TestesExpressoes {

	
	public static void main(String[] args) throws InvalidoException {
		CalcularExpressao a = new CalcularExpressao();

		@SuppressWarnings("resource")
		Scanner read = new Scanner(System.in);
		System.out.println("Usuário, informe uma expressão para computar seu resultado.\nTecle enter ao finalizar.\n.: DIGITE EXPRESSÃO");
		System.out.println(a.principalCalcular(read.nextLine()));
		
	}
	
}
