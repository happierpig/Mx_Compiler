package Utils;

public class SyntaxError extends RuntimeException{
    private Position pos;

    public SyntaxError(String msg,Position tmp){
        super(msg);
        this.pos=tmp;
    }

    @Override
    public String getMessage(){
        return "[Syntax Error] : " + super.getMessage() + " in " + pos.toString();
    }
}