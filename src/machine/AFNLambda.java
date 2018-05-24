package machine;

public class AFNLambda extends Machine {
	public AFNLambda() {
		super();
		this.name="afnl";
	}
	
	public State newState() {
		String name = states.isEmpty()?"0":"" +( Integer.parseInt(states.get(states.size()-1).getName())+1);
		State st = new State(name );
		states.add(st);
		return st;
	}
	
	public Transaction newTransaction(State from,State to, char symbol) {
		Transaction tr = new Transaction(from, to, symbol);
		if(symbol != '-' && !alphabet.contains(symbol))alphabet.add(symbol);
		transactions.add(tr);
		return tr;
	}
	public void addBegin(State st) {
		begins.add(st);
	}
	public void addEnd(State st) {
		ends.add(st);
	}
}
