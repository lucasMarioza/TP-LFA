package machine;

import java.util.*;

public abstract class Machine {
	protected String name;
	protected ArrayList<State> states;
	protected ArrayList<Character> alphabet;
	protected ArrayList<String> begins,ends;
	protected ArrayList<Transaction> transactions;
	
	protected Machine() {
			states = new ArrayList<State>();
			alphabet = new ArrayList<Character>();
			begins = new ArrayList<String>();
			ends = new ArrayList<String>();
			transactions = new ArrayList<Transaction>();
	}

	
	public String toString() {
		String s = "{ \""+name+"\": [\n\t\t[";
		for(int i=0;i<states.size();i++) {
			if(i!=0)s+=", ";
			State st = states.get(i);
			s += st;
		}
		s+="],\n\t\t[";
		for(int i=0;i<alphabet.size();i++) {
			if(i!=0)s+=", ";
			char ch = alphabet.get(i);
			s += "\""+ch+"\"";
		}
		s+="],\n\t\t[";
		for(int i=0;i<transactions.size();i++) {
			Transaction tr = transactions.get(i);
			s += "\n\t\t\t"+tr+"";
			if(i!=transactions.size()-1)s+=", ";
		}
		s+="\n\t\t],\n\t\t[";
		for(int i=0;i<begins.size();i++) {
			if(i!=0)s+=", ";
			String st = begins.get(i);
			s += "\""+st+"\"";
		}
		s+="],\n\t\t[";
		for(int i=0;i<ends.size();i++) {
			if(i!=0)s+=", ";
			String st = ends.get(i);
			s += "\""+st+"\"";
		}
		s+="]\n\t]\n}";
		return s;
	}
}
