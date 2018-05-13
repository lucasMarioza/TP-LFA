package machine;

public class AFN extends Machine {
	public AFN(AFNLambda m) {
		super();
		this.name="afn";
		this.alphabet = m.alphabet;
		this.states = m.states;

		for(int i=0;i<m.ends.size();i++) {
			this.ends.add(m.ends.get(i));
		}

		for(int i=0;i<m.begins.size();i++) {
			this.begins.add(m.begins.get(i));
		}
		for(int i =0;i<m.transactions.size();i++) {
			Transaction tr = m.transactions.get(i);
			if(tr.getSymbol() == '-') {
				for(int j =0;j<m.transactions.size();j++) {
					Transaction aux = m.transactions.get(i);
					if(aux.getTo() == tr.getFrom()) {
						this.transactions.add(new Transaction(aux.getFrom(),tr.getTo(),aux.getSymbol()));
					}
				}
				if(m.begins.contains(tr.getFrom().getName()) && !this.begins.contains(tr.getTo().getName())) {
					this.begins.add(tr.getTo().getName());
				}
			}else {
				this.transactions.add(tr);
			}
		}
	}
}
