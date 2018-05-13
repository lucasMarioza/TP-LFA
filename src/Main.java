import lexical.LexicalAnalysis;
import machine.*;
import syntatic.SyntaticAnalysis;

public class Main {

    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Numero de Argumentos Incorreto");
            return;
        }

        try  {
        	LexicalAnalysis l = new LexicalAnalysis(args[0]);
            SyntaticAnalysis s = new SyntaticAnalysis(l);
            AFNLambda m = s.start();
            AFN m2 = new AFN(m);
            System.out.println(m2);
            // c.execute()
        } catch (Exception e) {
            System.err.println("Internal error: " + e.getMessage());
        }
    }

}

