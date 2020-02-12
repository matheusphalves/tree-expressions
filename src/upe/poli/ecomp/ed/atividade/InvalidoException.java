package upe.poli.ecomp.ed.atividade;

/**
 * Classe exception para informar ao usuário de alguma inconsistência durante o processo de cálculo da expressão.
 * @author Matheus Phelipe e Nilton Vieira
 *
 */
public class InvalidoException extends Exception {

	private static final long serialVersionUID = 1L;
	
	public InvalidoException(String a) {
		super(a);
	}
	

}
