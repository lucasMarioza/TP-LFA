package machine;

import java.util.ArrayList;

public class AFN extends Machine {
	public AFN(AFNLambda m) {
		super("afn");
		this.alphabet = m.alphabet;
		this.states = m.states;
		this.ends = m.ends;
		this.begins = m.begins;

		addTransacoesNaoLambda(m);

		corrigeTransacoes(m);

		ArrayList<State> estadosInuteis = detectaEstadosInuteis();

		removeEstadosInuteis(estadosInuteis);
	}

	private void removeEstadosInuteis(ArrayList<State> estadosInuteis) {
		for (int i = 0; i < estadosInuteis.size(); i++) {
			State estado = estadosInuteis.get(i);
			for(int i1 = 0; i1 < this.transitions.size(); i1++) {
				Transition tr = this.transitions.get(i1);
				if(tr.getFrom() == estado || tr.getTo() == estado) {
					this.transitions.remove(i1);
					i1--;
				}
			}
			this.states.remove(estado);
			this.begins.remove(estado);
			this.ends.remove(estado);
		}
	}

	private void corrigeTransacoes(AFNLambda m) {
		for (int i = 0; i < m.transitions.size(); i++) {
			Transition trLambda = m.transitions.get(i);
			if (trLambda.getSymbol() == '-') {
				for (int i1 = 0; i1 < this.transitions.size(); i1++) {
					Transition tr = this.transitions.get(i1);
					if (tr.getTo() == trLambda.getFrom()) {

						this.transitions.add(new Transition(tr.getFrom(), trLambda.getTo(), tr.getSymbol()));
					}
				}
				for (int i1 = 0; i1 < m.transitions.size(); i1++) {
					Transition tr = m.transitions.get(i1);
					if (tr.getTo() == trLambda.getFrom() && tr.getFrom() != trLambda.getTo()) {

						m.transitions.add(new Transition(tr.getFrom(), trLambda.getTo(), tr.getSymbol()));
					}
				}
				if (this.begins.contains(trLambda.getFrom()) && !this.begins.contains(trLambda.getTo())) {
					this.begins.add(trLambda.getTo());
				}
				m.transitions.remove(trLambda);
				i--;
			}
		}
	}

	private void addTransacoesNaoLambda(AFNLambda m) {
		for (int i = 0; i < m.transitions.size(); i++) {
			Transition tr = m.transitions.get(i);
			// System.out.println(tr.toString());
			if (tr.getSymbol() != '-') {
				this.transitions.add(tr);
				m.transitions.remove(tr);
				i--;
			}

		}
	}

	private ArrayList<State> detectaEstadosInuteis() {
		ArrayList<State> estadosInuteis = new ArrayList<>();
		for (int i = 0; i < this.states.size(); i++) {
			State estado = this.states.get(i);
			if (!this.begins.contains(estado)) {
				boolean hasTrTo = false;
				for (int i1 = 0; i1 < this.transitions.size(); i1++) {
					Transition tr = this.transitions.get(i1);
					if (tr.getTo() == estado) {
						hasTrTo = true;
					}
				}
				if (!hasTrTo) {
					estadosInuteis.add(estado);
				}
			}
			if (!estadosInuteis.contains(estado) && !this.ends.contains(estado)) {
				boolean hasTrFrom = false;
				for (int i1 = 0; i1 < this.transitions.size(); i1++) {
					Transition tr = this.transitions.get(i1);
					if (tr.getFrom() == estado) {
						hasTrFrom = true;
					}
				}
				if (!hasTrFrom) {
					estadosInuteis.add(estado);
				}
			}
		}
		return estadosInuteis;
	}
}
