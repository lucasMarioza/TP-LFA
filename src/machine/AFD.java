package machine;

import java.util.ArrayList;
import java.util.Arrays;

public class AFD extends Machine{
	public AFD(AFN m) {
		super("af");
		this.alphabet = m.alphabet;
		State begin = newState(m.begins);
		this.begins.add(begin);
		ArrayList<State> fila = new ArrayList<>();
		fila.add(begin);
		while(!fila.isEmpty()) {
			State st = fila.get(0);
			fila.remove(0);
			ArrayList<String> names = getNames(st);
			for(char ch:m.alphabet) {
				ArrayList<State> afdStates = new ArrayList<>();
				for(Transition t:m.transitions) {
					if(t.getSymbol() == ch) {
						for(String n:names) {
							if(n.equals(t.getFrom().getName())) {
								afdStates.add(t.getTo());
							}
						}
					}
				}
				String name;
				if(!afdStates.isEmpty()) 
					name = genStateName(afdStates);
				else 
					name = "error";
				State newStAFD = getState(name);
				if(newStAFD == null) {
					newStAFD = newState(afdStates);
					fila.add(newStAFD);
				}
				this.transitions.add(new Transition(st,newStAFD,ch));
				
			}
		}
		
		for(State st:m.ends) {
			for(State afdSt:this.states) {
				if(this.ends.contains(afdSt))continue;
				ArrayList<String> names = getNames(afdSt);
				for(String name:names) {
					if(name.equals(st.getName())) {
						this.ends.add(afdSt);
						break;
					}
				}
				
			}
		}
	}
	
	public State newState(ArrayList<State> states) {
		String name= genStateName(states);
		if(getState(name) != null) return null;
		State st =  new State(name);
		this.states.add(st);
		return st;
	}
	
	public String genStateName(ArrayList<State> states) {
		states.sort(new StateComp());
		for(int i=0;i<states.size()-1;i++) {
			if(states.get(i)==states.get(i+1)) {
				states.remove(i);
				i--;
			}
		}
		String name = "";
		boolean prim = true;
		for(State st : states) {
			if(prim) prim = !prim;
			else name+=",";
			
			name += st.getName();
		}
		return name;
	}
	
	public ArrayList<String> getNames(State st){
		
		return new ArrayList<String>(Arrays.asList(st.getName().split(",")));
	}
	
	public State getState(String name) {
		
		for(State st:this.states) {
			if(st.getName().equals(name)) return  st;
		}
		return null;
	}
}
