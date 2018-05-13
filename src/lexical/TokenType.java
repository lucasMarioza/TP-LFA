package lexical;

public enum TokenType {
    // special tokens
    INVALID_TOKEN,
    UNEXPECTED_EOF,
    END_OF_FILE,

    // symbols
    PAROPEN,
    PARCLOSE,
    // operators
    PLUS,
    STAR,
    
    // others
    SYMBOL
};
