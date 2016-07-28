package tester;


import analyzercomponents.Lexer;
import analyzercomponents.SyntaxException;

import java.io.*;

/**
 * Created by unorthodox on 28/07/16.
 * This is the tester class for to simulate the function of a lexical analyzer.
 * input:       String
 * output:      tokens of the string
 */
public class LexicalSimulator {
    public static void main(String []args){
        InputStream fileInputStream=null;

        File file = new File("/home/unorthodox/IdeaProjects/LexicalAnalyzer/out/production/LexicalAnalyzer/tester/inputfile");

        try{
             fileInputStream= new FileInputStream(file);
            Lexer lexer= new Lexer(fileInputStream);
            try {
                lexer.scan();
            } catch (SyntaxException|IOException e){
                e.printStackTrace();
            }
        } catch (FileNotFoundException e){
            e.printStackTrace();
        }
    }
}
