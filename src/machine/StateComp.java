package machine;

import java.util.Comparator;

public class StateComp implements Comparator<State> {
    @Override
    public int compare(State o1, State o2) {
        return o1.getName().compareTo(o2.getName());
    }
}