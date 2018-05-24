package machine;

public class Transaction {
	public Transaction(State from, State to, char symbol) {
		this.from = from;
		this.to = to;
		this.symbol = symbol;
	}

	private State from, to;
	private char symbol;

	public State getFrom() {
		return from;
	}

	public State getTo() {
		return to;
	}

	public char getSymbol() {
		return symbol;
	}

	@Override
	public String toString() {
		return " [" + from + ", \"" + symbol + "\", " + to + "]";
	}

	public void setLambda() {
		this.symbol = '-';
	}

}
