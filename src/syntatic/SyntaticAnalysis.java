package syntatic;

import java.util.Stack;

import lexical.Lexeme;
import lexical.TokenType;
import machine.*;
import lexical.LexicalAnalysis;


public class SyntaticAnalysis {

    private LexicalAnalysis lex;
    private Lexeme current;

    public SyntaticAnalysis(LexicalAnalysis lex){
        this.lex = lex;
        this.current = lex.nextToken();
    }

    public AFNLambda start()  {
    	Stack<State> opens = new Stack<>(),closes = new Stack<>();
    	State lastSt = null,closeRet = null;
    	Lexeme prev=null;
    	String retSymbol="";
    	AFNLambda m = new AFNLambda();
    	State ini = m.newState();
    	m.addBegin(ini);
    	lastSt = ini;
    	while(current.type != TokenType.END_OF_FILE) {
    		if(current.type == TokenType.PAROPEN) {
    			State aux =  m.newState();
    			m.newTransaction(lastSt, aux, '-');
    			lastSt = aux;
    			opens.push(lastSt);
    			closes.push(m.newState());
    		}else if(current.type == TokenType.PARCLOSE) {
    			if(opens.isEmpty()) showError();
    			State aux = closes.pop();
    			m.newTransaction(lastSt,aux , '-');
    			lastSt = aux;
    			closeRet = opens.pop();
    		}else if(current.type == TokenType.PLUS) {
    			if(closes.isEmpty()) {
    				m.addEnd(lastSt);
    				lastSt = ini;
    			}else {
    				State aux = closes.pop();
    				closes.add(aux);
    				m.newTransaction(lastSt,aux,'-');
    				
    				aux = opens.pop();
    				opens.add(aux);
    				
    				lastSt = aux;
    			}
    		}else if(current.type == TokenType.STAR) {
    			if(prev.type == TokenType.SYMBOL) {
    				m.newTransaction(lastSt,lastSt,retSymbol.charAt(0));
    			}else if(prev.type == TokenType.PARCLOSE) {
    				m.newTransaction(closeRet, lastSt, '-');
    				m.newTransaction(lastSt,closeRet, '-');
    			}else {
    				showError();
    			}
    		}else if(current.type == TokenType.SYMBOL) {
    			State aux = m.newState();
    			m.newTransaction(lastSt, aux, current.token.charAt(0));
    			lastSt = aux;
    			retSymbol = current.token;
    		}else {
    			showError();
    		}
    		prev = current;
    		current = lex.nextToken();
    	}
    	m.addEnd(lastSt);
    	if(!opens.isEmpty()) showError();
        return m;
    }

    private void showError() {
        System.out.printf("%02d: ", lex.getPos());

        switch (current.type) {
            case INVALID_TOKEN:
                System.out.printf("Lexema inválido [%s]\n", current.token);
                break;
            case UNEXPECTED_EOF:
            case END_OF_FILE:
                System.out.printf("Fim de arquivo inesperado\n");
                break;
            default:
                System.out.printf("Lexema não esperado [%s]\n", current.token);
                break;
        }

        System.exit(1);
    }
}
