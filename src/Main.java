import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		List<Integer> operarandos = new ArrayList<Integer>();
		List<String> operacoes = new ArrayList<String>();
		Scanner sc = new Scanner(System.in);
		
		String op;
		
		do {
			operarandos.add(obterOperandor());
			System.out.print("Digite C para calcular o valor da expressão: ");
			op = sc.nextLine();
			if(!op.toLowerCase().equals("c"))
			{
				operacoes.add(obterOperando());	
			}
		}while(!op.toLowerCase().equals("c"));
		
		System.out.println("O resultado é: "+calcularResultado(operarandos, operacoes));
		
	}
	private static void imprirExmpressao(List<Integer> operarandos, List<String> operacoes) {
		
		System.out.print(operarandos.get(0));
		for(int index = 1; index < operacoes.size(); index = index + 1)
		{
			System.out.print(operarandos.get(index)+" "+operacoes.get(index)+" "+operarandos.get(index+1));
		}
	}
	private static Integer calcularResultado(List<Integer> operarandos, List<String> operacoes) {
		List<Integer> operarandosMultiplicaDivide = new ArrayList<Integer>();
		List<String> operacoesSomaDiminuir = new ArrayList<String>();
		if(operarandos.size() == 1)
		{
			return operarandos.get(0);
		}
		for(int index = 0; index < operacoes.size(); index ++)
		{
			String operacaoAtual = operacoes.get(index);
			if(operacaoAtual.equals("*") || operacaoAtual.equals("/")){
				Integer subResultado = subResultao(operarandos.get(index), operarandos.get(index+1), operacaoAtual);
				operarandosMultiplicaDivide.add(subResultado);
			}else
			{
				operacoesSomaDiminuir.add(operacaoAtual);
			}
		}
		Integer resultado = 0;
		while(!operarandosMultiplicaDivide.isEmpty())
		{
			if(operarandosMultiplicaDivide.size() == 1)
			{
				resultado = subResultao(resultado, operarandosMultiplicaDivide.get(0), operacoesSomaDiminuir.get(0));
			}else
			{
				resultado = resultado + subResultao(operarandosMultiplicaDivide.get(0), operarandosMultiplicaDivide.get(1), operacoesSomaDiminuir.get(0));
				operarandosMultiplicaDivide.remove(0);
				operacoesSomaDiminuir.remove(0);
			}
			operarandosMultiplicaDivide.remove(0);
			
		}
		return resultado;
	}
	
	private static Integer subResultao(Integer operando1, Integer operando2, String operacao)
	{
		if(operacao.equals("+"))
		{
			return operando1 + operando2;
		}else if(operacao.equals("-")){
			return operando1 - operando2;
		}else if(operacao.equals("*")){
			return multiplicacao(operando1, operando2);
		}else{
			return divisao(operando1, operando2);
		}
	}
	
	private static Integer obterOperandor()
	{
		Scanner sc = new Scanner(System.in);
		Integer operando = null;
		do {
			System.out.print("Digite um operando: ");
			String strNum = sc.nextLine();
			operando =  checarOperando(strNum);
		}while(operando == null);
		return operando;
	}	
	
	private static String obterOperando()
	{
		Scanner sc = new Scanner(System.in);
		String strOp = null;
		do {
			System.out.print("Digite um operandor: ");
			strOp = sc.nextLine();
			try {
				checarOperandor(strOp);
			} catch (IllegalArgumentException e) {
				System.out.println(e.getMessage());
				strOp = null;
			}
			
		}while(strOp == null);
		return strOp;
	}	
	
	
	private static Integer checarOperando(String strNum)
	{
		try {
	        return Integer.parseInt(strNum);
	    } catch (NumberFormatException nfe) {
	    	System.out.println("O valor "+strNum+" não é um operandor");
	        return null;
	    }
	}
	
	private static void checarOperandor(String strOp)
	{
		if(!(strOp.equals("+") || strOp.equals("-") || strOp.equals("*") || strOp.equals("/")))
		{
			throw new IllegalArgumentException("Operado "+strOp+" é invalido");
		}
	}
	
	private static Integer multiplicacao(Integer multiplicando, Integer multiplicador)
	{
		Integer resuldado = 0;
		for(int i = 1; i <= multiplicador; i++)
		{
			resuldado = resuldado + multiplicando;
		}
		return resuldado;
	}
	
	private static Integer divisao(Integer dividendo, Integer divisor)
	{
		if(dividendo < divisor)
		{
			return 0;
		}else
		{
			Integer resuldado = 1;
			int resto = dividendo -  divisor;
			while(resto >= divisor)
			{
				resuldado ++;
				resto = resto -  divisor;
			}
			return resuldado;
		}
		
	}
	
	

}
