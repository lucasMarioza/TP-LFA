package lexical;

import java.util.Map;
import java.util.HashMap;

class SymbolTable {

    private Map<String, TokenType> st;

    public SymbolTable() {
        st = new HashMap<String, TokenType>();
        
        // symbols
        st.put("(", TokenType.PAROPEN);
        st.put(")", TokenType.PARCLOSE);

        // operators
        st.put("+", TokenType.PLUS);
        st.put("*", TokenType.STAR);
    }

    public boolean contains(String token) {
        return st.containsKey(token);
    }

    public TokenType find(String token) {
        return this.contains(token) ?
            st.get(token) : TokenType.INVALID_TOKEN;
    }
}
