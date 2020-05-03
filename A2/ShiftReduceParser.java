import java.util.*;

public class ShiftReduceParser {

    private static final int E_NON_TERM = 30;
    private static final int T_NON_TERM = 31;
    private static final int F_NON_TERM = 32;
    
    private static final int IDENT = 11;
    private static final int ADD_OP = 21;
    private static final int MULT_OP = 23;
    private static final int LEFT_PAREN = 25;
    private static final int RIGHT_PAREN = 26;

    private static final int S4 = 50;
    private static final int S5 = 51;
    private static final int S6 = 52;
    private static final int S7 = 53;
    private static final int S11 = 54;
    private static final int R2 = 55;
    private static final int R4 = 56;
    private static final int R6 = 57;
    private static final int ACCEPT = 58;

    private static final Map<Integer, Integer[]> rules = 
        Map.of(1, new Integer[]{E_NON_TERM, 3},
                2, new Integer[]{E_NON_TERM, 1},
                3, new Integer[]{T_NON_TERM, 3},
                4, new Integer[]{T_NON_TERM, 1},
                5, new Integer[]{F_NON_TERM, 3},
                6, new Integer[]{F_NON_TERM, 1});

    private static final Map<Integer, Map<Integer, Integer>> parseTable =
        Map.of(0, Map.of(IDENT, S5,
                            LEFT_PAREN, S4,
                            E_NON_TERM, 1,
                            T_NON_TERM, 2,
                            F_NON_TERM, 3),
                1, Map.of(ADD_OP, S6,
                            -1, ACCEPT),
                2, Map.of(ADD_OP, R2,
                            MULT_OP, S7,
                            RIGHT_PAREN, R2,
                            -1, R2),
                3, Map.of(ADD_OP, R4,
                            MULT_OP, R4,
                            RIGHT_PAREN, R4,
                            -1, R4),
                4, Map.of(IDENT, S5,
                            LEFT_PAREN, S4,
                            E_NON_TERM, 8,
                            T_NON_TERM, 2,
                            F_NON_TERM, 3),
                5, Map.of(ADD_OP, R6,
                            MULT_OP, R6,
                            RIGHT_PAREN, R6,
                            -1, R6),
                6, Map.of(IDENT, S5,
                            LEFT_PAREN, S4,
                            T_NON_TERM, 9,
                            F_NON_TERM, 3),
                7, Map.of(IDENT, S5,
                            LEFT_PAREN, S4,
                            F_NON_TERM, 10),
                8, Map.of(ADD_OP, S6,
                            RIGHT_PAREN, S11));

    private LexicalAnalyzer la;
    private Stack<Integer> stack;
    
    public ShiftReduceParser(String fileName){
        la = new LexicalAnalyzer(fileName);
        stack = new Stack<>();
        stack.push(0);
    }
    

    public boolean beginParse(){
        Token token = la.getToken();
        while(token.getTokenCode()!=-1){
            int state = stack.peek();
            Integer action = parseTable.get(state).getOrDefault(token.getTokenCode(), null);
            System.out.printf("next lexeme: %s\n", token.getLexemeString());

            if(action == null){
                System.out.println("The sentence is not syntactically correct.");
                return false;
            }
            else if(action == S4 || action == S5 || action == S6 || action == S7 || action == S11){
                stack.push(token.getTokenCode());
                if(action == S4) stack.push(4);
                if(action == S5) stack.push(5);
                if(action == S6) stack.push(6);
                if(action == S7) stack.push(7);
                if(action == S11) stack.push(11);
            }else if(action == R2 || action == R4 || action == R6){
                
                int[] rule;
                if(action == R2) rule = rules.get(2);
                if(action == R4) rule = rules.get(4);
                if(action == R6) rule = rules.get(5);

                int nonTerminal = rule[0];
                int ruleLength = rule[1];

                for(int i=0; i< ruleLength*2; i++){
                    stack.pop();
                }

                state = stack.peek();
                int nextState = parseTable.get(state).get(nonTerminal);

                stack.push(nonTerminal);
                stack.push(nextState);
            }else{
                return true;
            }
            token = la.getToken();
        }
        
    }


    
    
}