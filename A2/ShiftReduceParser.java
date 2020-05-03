import java.util.*;

public class ShiftReduceParser {
    private static final int E_NON_TERM = 30;
    private static final int T_NON_TERM = 31;
    private static final int F_NON_TERM = 32;

    public static final Map<Integer, Integer> ruleLengths = 
        Map.of(1, 3,
                2, 1,
                3, 3,
                4, 1,
                5, 3,
                6, 1);

    private static final Map<Integer, Map<Integer, Integer>> parseTable =
        Map.of(0, Map.of(),
                1, Map.of(),
                2, Map.of(),
                3, Map.of(),
                4, Map.of(),
                5, Map.of(),
                6, Map.of(),
                7, Map.of(),
                8, Map.of());

    private static final int IDENT = 11;
    private static final int ASSIGN_OP = 20;
    private static final int ADD_OP = 21;
    private static final int DIV_OP = 24;
    private static final int LEFT_PAREN = 25;
    private static final int RIGHT_PAREN = 26;
    
}