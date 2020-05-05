import java.io.*;


public class LexicalAnalyzer {
  private File input;
  private FileReader fr;
  private int nextToken;
  private String lexeme;
  private int nextChar;
  private Token token;
  private int charClass;
  

  private static final int LETTER = 0;
  private static final int DIGIT = 1;
  private static final int UNKNOWN = 99;

  private static final int INT_LIT = 10;
  private static final int IDENT = 11;
  private static final int ASSIGN_OP = 20;
  private static final int ADD_OP = 21;
  private static final int SUB_OP = 22;
  private static final int MULT_OP = 23;
  private static final int DIV_OP = 24;
  private static final int LEFT_PAREN = 25;
  private static final int RIGHT_PAREN = 26;

  public LexicalAnalyzer(String fileName){
    input = new File(fileName);

    try{
      fr = new FileReader(input);
    } catch(FileNotFoundException e){
      e.printStackTrace();
    }

    token = new Token();
  }

  private void lookup(char c){
    switch(c){
      case '(': addChar();
                nextToken = LEFT_PAREN;
                break;
      case ')': addChar();
                nextToken = RIGHT_PAREN;
                break;
      case '+': addChar();
                nextToken = ADD_OP;
                break;
      case '-': addChar();
                nextToken = SUB_OP;
                break;
      case '*': addChar();
                nextToken = MULT_OP;
                break;
      case '/': addChar();
                nextToken = DIV_OP;
                break;
      default:  addChar();
                nextToken = -1;
                break;
    }
  }

  private void getChar(){
    try{
      if((nextChar = fr.read()) != -1){
        if (Character.isAlphabetic(nextChar))
          charClass = LETTER;
        else
          if (Character.isDigit(nextChar))
            charClass = DIGIT;
          else
            charClass = UNKNOWN;
      }else charClass = -1;
    }catch(IOException e){
      e.printStackTrace();
    }
  }

  private void getNonBlank(){
    while(Character.isWhitespace(nextChar) || nextChar==0) getChar();
  }

  private void addChar(){
    if(lexeme.length()<=99) lexeme += (char)nextChar;
    else throw new RuntimeException("lexeme length cannot exceed 100");
  }

  public Token getToken(){
    lexeme="";
    getNonBlank();

    switch(charClass){

      case LETTER:
        addChar();
        getChar();
        while(charClass == LETTER || charClass == DIGIT){
          addChar();
          getChar();
        }
        nextToken = IDENT;
        break;

      case DIGIT:
        addChar();
        getChar();
        while (charClass == DIGIT) {
          addChar();
          getChar();
        }
        nextToken = INT_LIT;
        break;

      case UNKNOWN: 
        lookup((char)nextChar);
        getChar();
        break;

      case -1:
        nextToken = -1;
        lexeme = "EOF";
        break;

    }

    token.setTokenCode(nextToken);
    token.setLexemeString(lexeme);
    return token;
  }
}