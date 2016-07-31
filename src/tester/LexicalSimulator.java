package tester;


import analyzercomponents.*;

import java.io.*;


/**
 * Created by unorthodox on 28/07/16.
 * This is the tester class for to simulate the function of a lexical analyzer in generating a symbol table.
 * input:       String
 * output:      tokens of the string
 */
public class LexicalSimulator {
    public static void main(String [] args){
        //Scanner input= new Scanner(System.in);
        //System.out.println("Enter the code snippet: ");
        //String myCode= input.nextLine();
        InputStream is; int i=30000;

        File file = new File("/home/unorthodox/IdeaProjects/LexicalAnalyzer/out/production/LexicalAnalyzer/tester/inputfile");

       try{
            is= new FileInputStream(file);
           //InputStream is = new ByteArrayInputStream(myCode.getBytes("UTF-8"));
           Lexer lexer= new Lexer(is);
            try {

                while (i>0) {
                    lexer.scan(); //scan the input and extract tokens - word, relational operators, comments
                    i--;
                }

            } catch (SyntaxException|IOException |ClassCastException e){
                e.printStackTrace();
            }
        } catch (FileNotFoundException e){
            e.printStackTrace();
        }

    }
}
