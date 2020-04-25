import java.util.*;
import java.io.*;

public class Assignment1{
  public static void main(String[] args){    
    LexicalAnalyzer myLA = new LexicalAnalyzer(args[0]);
    Token token;
    do{
      token = myLA.getToken();
      System.out.println(String.format("Next token is %d, Next lexeme is %s", token.getTokenCode(), token.getLexemeString() ));
    } while(token.getTokenCode()!=-1);
  }
}