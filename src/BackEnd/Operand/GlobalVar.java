package BackEnd.Operand;

public class GlobalVar extends Operand{
    public String literal; // String literal or something else;
    // la use name to get address

    public GlobalVar(String _name) {
        super(_name);
        literal = null;
    }

    public GlobalVar(String _name, String _value){
        super(_name);
        literal = _value;
    }

    public String printASM(){
        StringBuilder raw = new StringBuilder();
        if(literal == null){
            raw.append('\t').append(".type").append('\t').append(getName()).append(",@object\n");
            raw.append('\t').append(".section").append('\t').append(".bss\n");
            raw.append('\t').append(".globl").append('\t').append(getName()).append('\n');
            raw.append(getName()).append(":\n");
            raw.append('\t').append(".word").append('\t').append(0).append('\n');
            raw.append('\t').append(".size").append('\t').append(getName()).append(", 4");
        }else{
            raw.append('\t').append(".type").append('\t').append(getName()).append(",@object\n");
            raw.append('\t').append(".section").append('\t').append(".rodata\n");
            raw.append(getName()).append(":\n");
            raw.append('\t').append(".asciz").append('\t').append('\"').append(format(literal)).append('\"').append('\n');
            raw.append('\t').append(".size").append('\t').append(getName()).append(", ").append(literal.length());
        }
        return raw.toString();
    }

    private String format(String raw){
        return raw.replace("\\", "\\\\")
                .replace("\n", "\\n")
                .replace("\0", "")
                .replace("\t", "\\t")
                .replace("\"", "\\\"");
    }
}