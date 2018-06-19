package main;
import lexical.LexicalAnalysis;
import machine.AFD;
import machine.AFN;
import machine.AFNLambda;
import syntatic.SyntaticAnalysis;

public class Main {

	public static void main(String[] args) {
		if (args.length != 1) {
			System.out.println("Numero de Argumentos Incorreto");
			return;
		}

		try {
			LexicalAnalysis l = new LexicalAnalysis(args[0]);
			SyntaticAnalysis s = new SyntaticAnalysis(l);
			AFNLambda m = s.start();
			//System.out.println(m);
			AFN m2 = new AFN(m);
			AFD m3 = new AFD(m2);
			System.out.println(m3);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
