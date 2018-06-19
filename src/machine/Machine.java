package machine;

import java.util.*;

public abstract class Machine {
	protected String name;
	protected ArrayList<State> states;
	protected ArrayList<Character> alphabet;
	protected ArrayList<State> begins,ends;
	protected ArrayList<Transition> transitions;
	
	protected Machine(String name) {
			this.name = name;
			states = new ArrayList<State>();
			alphabet = new ArrayList<Character>();
			begins = new ArrayList<State>();
			ends = new ArrayList<State>();
			transitions = new ArrayList<Transition>();
	}

	
	public String toString() {
		String s = "{ \""+name+"\": [\n    [";
		for(int i=0;i<states.size();i++) {
			if(i!=0)s+=", ";
			State st = states.get(i);
			s += st;
		}
		s+="],\n    [";
		for(int i=0;i<alphabet.size();i++) {
			if(i!=0)s+=", ";
			char ch = alphabet.get(i);
			s += "\""+ch+"\"";
		}
		s+="],\n    [";
		for(int i=0;i<transitions.size();i++) {
			Transition tr = transitions.get(i);
			s += "\n      "+tr+"";
			if(i!=transitions.size()-1)s+=", ";
		}
		s+="\n    ],\n    [";
		for(int i=0;i<begins.size();i++) {
			if(i!=0)s+=", ";
			State st = begins.get(i);
			s += ""+st;
		}
		s+="],\n    [";
		for(int i=0;i<ends.size();i++) {
			if(i!=0)s+=", ";
			State st = ends.get(i);
			s += ""+st;
		}
		s+="]\n  ]\n}";
		return s;
	}
}
