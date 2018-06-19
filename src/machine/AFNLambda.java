package machine;

public class AFNLambda extends Machine {
	public AFNLambda() {
		super("afnl");
	}
	
	public State newState() {
		String name = states.isEmpty()?"0":"" +( Integer.parseInt(states.get(states.size()-1).getName())+1);
		State st = new State(name );
		states.add(st);
		return st;
	}
	
	public Transition newTransaction(State from,State to, char symbol) {
		Transition tr = new Transition(from, to, symbol);
		if(symbol != '-' && !alphabet.contains(symbol))alphabet.add(symbol);
		transitions.add(tr);
		return tr;
	}
	public void addBegin(State st) {
		begins.add(st);
	}
	public void addEnd(State st) {
		ends.add(st);
	}
}
