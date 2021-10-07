import Utils.MxErrorListener;
import Utils.Position;
import Utils.SyntaxError;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import Parser.MxParser;
import Parser.MxLexer;
import java.io.FileInputStream;
import java.io.InputStream;

public class Main {
    public static void main(String[] args) throws Exception{

        String name = "test.yx";
        // create new input stream
        InputStream input = new FileInputStream(name);

        try {
            // CharStreams is ANTLR's built-in string of 01;
            // new a lexer to process the charStream
            MxLexer lexer = new MxLexer(CharStreams.fromStream(input));
            // remove original error methods
            lexer.removeErrorListeners();
            lexer.addErrorListener(new MxErrorListener());
            // new a Parser from the lexer
            MxParser parser = new MxParser(new CommonTokenStream(lexer));
            // use customized error methods to alter the original one
            parser.removeErrorListeners();
            parser.addErrorListener(new MxErrorListener());
            // start parsing according to the program rule
            ParseTree parseTreeRoot = parser.program();

        } catch (RuntimeException er) {
            System.err.println(er.getMessage());
            throw new RuntimeException();
        }
    }
}