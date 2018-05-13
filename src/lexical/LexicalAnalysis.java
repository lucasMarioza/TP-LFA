package lexical;


public class LexicalAnalysis {

    private int pos;
    private SymbolTable st;
    private String input;

    public LexicalAnalysis(String regex) {
        input = regex;

        st = new SymbolTable();
        pos = 0;
    }
    
    public int getPos() {
    	return pos;
    }

    public Lexeme nextToken()  {
         Lexeme lex = new Lexeme("", TokenType.END_OF_FILE);
         if(pos >= input.length()) {
        	 lex.type = TokenType.END_OF_FILE;
        	 return lex;
         }
         char c = input.charAt(pos);
         lex.token = ""+ c;
         
         while (c == ' ' || c == '\t' || c == '\r' || c == '\n') {
        	 pos++;
        	 c = input.charAt(pos);
             lex.token = ""+ c;
         }
         if (Character.isLetter(c)) {
        	 lex.type = TokenType.SYMBOL;
         } else {
        	 lex.type = st.find(""+c);
         }
         pos++;
         return lex;
    }
}
