
public class Token {
  private int tokenCode;
  private String lexemeString;

  public Token(){
    tokenCode = -69;
    lexemeString = "";
  }
  public Token(int tc, String ls){
    tokenCode = tc;
    lexemeString = ls;
  }

  public int getTokenCode(){
    return tokenCode;
  }

  public void setTokenCode(int i){
    tokenCode = i;
  }

  public String getLexemeString(){
    return lexemeString;
  }

  public void setLexemeString(String s){
    lexemeString = s;
  }

}