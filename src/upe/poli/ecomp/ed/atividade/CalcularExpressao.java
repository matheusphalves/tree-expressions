package upe.poli.ecomp.ed.atividade;

import java.util.Scanner;
import java.util.StringTokenizer;
import upe.poli.ecomp.ed.basicas.BinNode;
import upe.poli.ecomp.ed.basicas.LinkedList;
import upe.poli.ecomp.ed.basicas.LinkedListOrdinated;
import upe.poli.ecomp.ed.basicas.Stack;

/**
@author Matheus Phelipe e Nilton Vieira.
* @category
*  Universidade de Pernambuco - UPE,
*  Escola Politécnica de Pernambuco - POLI,
*  Estruturas de Dados - 18.2
*  @objective
*  Algoritmo para resoluçãoo de expressões fornecidas como strings utilizando árvores, e entre outras estruturas
*  Prof. Dr Byron Leite
 */
public class CalcularExpressao {
	
	//Atributos necessários para a preparação da árvore, bem como o cálculo da expressão
	private Stack<String> operadores;
	private Stack<BinNode<String>> subArvore;
	private LinkedListOrdinated<String> var;
	private LinkedList<Double> valores;
	private BinNode<String> root;
	
	/**
	 * Classe contém algoritmo para resolução de expressões fornecidas como string, utilizando árvores e extruturas de dados auxiliares.
	 */
	public CalcularExpressao() {
			operadores = new Stack<>();
			subArvore = new Stack<>();
			var = new LinkedListOrdinated<>();
			valores = new LinkedList<>();
	}
	
	/**
	 * M�todo inicial. A partir deste, todo o processo para cálculo da expressão é realizado.
	 * @param a
	 * @return String
	 * @throws InvalidoException
	 */
	public String principalCalcular(String a) throws InvalidoException {
		
		String valor="";
		if(this.separaString(a)) {
			
			for(int i=0; i<var.size() ;i++) {			
				System.out.println("Informe o valor da variável: " + var.get(var.size()-1-i));
				valores.add(new Scanner(System.in).nextDouble());	
			}
			valor = this.substituiValores(root); //devolve expressão ao conjunto com valores substituidos
			System.out.println("Entrada: " + valor);
			valor = String.valueOf(this.calcularExpressao(root));	
			
		}else
			throw new InvalidoException("Expressão informada não está bem formatada. Revise-a.");
		
		return ".:Resultado  " + valor;
	}
	
	/**
	 *
	 * M�todo efetua segrega��o entre os elementos constituintes da express�o informada. Isso se deve ao fato de reconhecer as suas vari�veis, constantes e operadores. 
	 * O m�todo constr�i a �rvore com os dados "separados".
	 * @param expressao
	 * @return boolean
	 */
	private boolean separaString(String expressao){
		
		BinNode<String> a ; //nó binário pois após futuras alterações, teremos uma árvore
		String apoio="";
		boolean status = true;	
		expressao = this.addParenteses(expressao); //adição de parênteses nas extremidades
		
	if(this.validarExpressao(expressao)) { //método verifica a formatação da expressão
		
		StringTokenizer aux = new StringTokenizer(expressao, " "); //quebra string a partir de espa�os em branco
		while(aux.hasMoreElements()) {
			
			apoio = aux.nextToken();
			
			if(apoio.matches("[\\)]")) { //realizar remo��es das pilhas e montagem da arvore	
				 
				if(!operadores.isEmpty()) { //deve existir algum operador para que arvore não tenha raiz nula ap�s adiçao
					a = new BinNode<String>(operadores.pop());
					a.setRight(subArvore.pop());
					a.setLeft(subArvore.pop());		
					subArvore.push(a); //subArvore retorna a pilha
				}
			}else if(apoio.matches("^[\\+\\-\\*\\/]")) { 	//salva operador			
				operadores.push(apoio);
			
			}else if(!apoio.equals("(")&&!apoio.matches("^[��������������������������~_�]*$")) { //adição de constantes e variáveis
				
				a = new BinNode<String>(apoio); 	
				if(!apoio.matches("(\\+|-)?([0-9]+(\\.[0-9]+))")) { //adição de variáveis
					if(!apoio.matches("(\\+|-)?[0-9]+")&&!var.contains(apoio))	
						var.add(apoio);
				}	
				subArvore.push(a);	
			}
		}
	}else
		status = false;
	
	root = subArvore.top(); // raiz principal que contém sub arvores	
	return status;
	}
	/**
	 * Método efetua as substituições dos valores das variáveis mediante as informações informadas pelo usuário.
	 * @param a
	 * @return String
	 */
	private String substituiValores(BinNode<String> a) {
		String r="";
		
		if(a!=null) { //não se faz nada em folha nulas
			if(a.getData().matches("^[\\+\\-\\*\\/]")) { //encontrou um operador, olhar seus filhos	
					r+= "( " + this.substituiValores(a.getLeft());
					r+= " " + a.getData() + " ";
					r+= this.substituiValores(a.getRight()) + " )";		
			}else {	
				//efetua checagem pra substituir valor da variável
				for(int i=0; i<var.size(); i++) { //alteração no nó da árvore pelo respectivo valor
					
					if(a.getData().equals(var.get(var.size()-1-i)))
						a.setData(a.getData().replaceAll(var.get(var.size()-1-i), String.valueOf(valores.get(valores.size()-1-i))));
		
				}		
				r = a.getData();
			}
		}
		return r;
	}
	
	/**
	 * Método efetua o cálculo da expressão, utilizando-se da recursividade.
	 * @param a
	 * @return double
	 */
	private double calcularExpressao(BinNode<String> a) throws InvalidoException {
		double r = 0;
		
		if(a!=null) {	
				if(a.getData().equals("+")) {
					
					r+= calcularExpressao(a.getLeft()) + calcularExpressao(a.getRight());
					
				}else if(a.getData().equals("-")) {
					
					r+= calcularExpressao(a.getLeft()) - calcularExpressao(a.getRight());
					
				}else if(a.getData().equals("*")){
					
					r+= calcularExpressao(a.getLeft()) * calcularExpressao(a.getRight());
					
				}else if(a.getData().equals("/")) {
					
					
					r+= calcularExpressao(a.getLeft()) / calcularExpressao(a.getRight());
					
					if(calcularExpressao(a.getRight())==0.0) {
						throw new InvalidoException("Não é possível dividir por 0.");
					}
					
				}else {		
					r=Double.parseDouble(a.getData());
				}
			}
		return  r;
	}
	
	
	/**
	 * M�todo realiza checagem da composição da expressão informada. Caso seja válida, dar-se-á prosseguimento ao processo.
	 * @param texto
	 * @return boolean
	 */
	public boolean validarExpressao(String texto){
		boolean status=true;
		Stack<Character> a = new Stack<>();
		int i=0;
		
		if(texto!=null) {
			for(i=0;i<texto.length();i++) {//verifica se existe espaço entre os parenteses
				if(i!=0&&i+1!=texto.length()) {
					if(texto.charAt(i)=='('||texto.charAt(i)==')') {
						if(texto.charAt(i-1)!=' '||texto.charAt(i+1)!=' ') {
							status=false;
						}
					}
				}
				else if(i==0) {
					if(texto.charAt(i)=='('||texto.charAt(i)==')') {
						if(texto.charAt(i+1)!=' ') {
							status=false;
						}
					}
				}
				else{
					if(texto.charAt(i)=='('||texto.charAt(i)==')') {
						if(texto.charAt(i-1)!=' ') {
							status=false;
						}
					}
				}
			}
			i=0;
	
			if(status==true) {
					while(i<texto.length()) {
						
						if(texto.charAt(i)=='(') //elementos de inicio salvos
							a.push(texto.charAt(i));	
						i++;
					}	
					i=0;	//contador zerado
					
					while(i<texto.length()) {//pilha deve conter algo para analisar express�o
						
						if(!a.isEmpty()) {	
							char dado = a.top();	
							if(texto.charAt(i)==')') {//verifica apenas os parenteses
								
								if(dado=='(') {
									a.pop();
								}else {
									status=false;
									break;
								}	
							}	
						}else if(i<texto.length()) {
							status=false;
						}
						i++;
					}	
					if(!a.isEmpty())
						status=false;
			}
			
			if(status==true) {//se passar do teste dos parenteses verifica se a express�o informada est� sintaticamente correta 
				String r="";
		        for(i=0;i<texto.length();i++) {//cria uma nova string sem os parenteses
		        	if(texto.charAt(i)!='('&&texto.charAt(i)!=')') {
		        		r+=texto.charAt(i);
		            }
		            else{
		                i++;
		            }
		        }
				String tokens[] = r.split(" ");
				for(i=0; i<tokens.length;i++) {//verifica��o
					if(i+1!=tokens.length) {
						if(tokens[i].matches("[0-9]+")) {
							if(tokens[i+1].matches("[0-9]+")||tokens[i+1].matches("^[a-zA-Z]+")) {
								status = false;
								break;
							}
						}
						else if(tokens[i].matches("^[\\+\\-\\*\\/]")&&tokens[i+1].matches("^[\\+\\-\\*\\/]")) {
							status = false;
							break;
						}
						else if(tokens[i].matches("^[a-zA-Z]+")) {
							if(tokens[i+1].matches("[0-9]+")||tokens[i+1].matches("^[a-zA-Z]+")) {
								status = false;
								break;
							}
						}
					}
				}
			}
		}else
			status=false;
		return status;
	}
	
	public String addParenteses(String a) { //adiciona parenteses inicial e final	
		String aux = "( ";
		for(int i=0; i<a.length(); i++)
				aux += a.charAt(i);
			
		return aux + " )";
	}
}
