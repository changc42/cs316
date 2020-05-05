public class Assignment2{
    public static void main(String[] args){
        if(args.length == 0){
            System.out.println("Must provide input file");

        }else{
            ShiftReduceParser srp = new ShiftReduceParser(args[0]);
            srp.beginParse();
        }
        

    }
}