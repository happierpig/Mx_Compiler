package Utils;

public class SemanticError extends RuntimeException{
    private Position pos;

    public SemanticError(String msg,Position tmp){
        super(msg);
        this.pos=tmp;
    }

    @Override
    public String getMessage(){
        return "[Semantic Error] : " + super.getMessage() + " in " + pos.toString();
    }
}