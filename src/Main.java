import AST.ASTBuilder;
import AST.RootNode;
import FrontEnd.BuiltInInitiator;
import FrontEnd.PreProcessor;
import FrontEnd.SemanticChecker;
import IR.IRModule;
import IR.Infrastructure.IRBuilder;
import Utils.GlobalScope;
import Utils.MxErrorListener;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import Parser.MxParser;
import Parser.MxLexer;
import java.io.FileInputStream;
import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception{

        String name = "try.mx";
        InputStream input = new FileInputStream(name);
//        InputStream input = System.in;

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

            // AST
            ASTBuilder test = new ASTBuilder();
            RootNode rt = (RootNode) test.visit(parseTreeRoot);

            // Preprocess && Built-in set
            GlobalScope gScope = new GlobalScope(null);
            BuiltInInitiator initialer = new BuiltInInitiator();
            gScope = initialer.init(gScope);
            PreProcessor preprocess = new PreProcessor(gScope);
            preprocess.visit(rt);

            // Semantic Checker
            SemanticChecker semanticCheck = new SemanticChecker(gScope);
            semanticCheck.visit(rt);

            // IR builder
            IRModule module = new IRModule();
            IRBuilder irb = new IRBuilder(module,gScope);
            irb.visit(rt);
            irb.processGlobalInit();
            System.out.println(module);

        } catch (RuntimeException er) {
            System.err.println(er.getMessage());
            throw new RuntimeException();
        }
    }
}