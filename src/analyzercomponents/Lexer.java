package analyzercomponents;

import java.io.IOException;
import java.io.InputStream;
import java.util.Hashtable;


/**
 * Created by unorthodox on 28/07/16.
 * This is the lexer class restructured from the samples in the textbook.
 * input:       String
 * output:      tokens of the string
 */

public class Lexer {
	private int line = 1;
	private char peek = ' ';
	private InputStream stream;
	private Hashtable<String, Word> words = new Hashtable<>();
	
	public Lexer(InputStream stream){
		this.stream = stream;
		reserve(new Word(Tag.TRUE, "true"));
		reserve(new Word(Tag.FALSE, "false"));
	}
	
	private void reserve(Word t){
		words.put(t.lexeme, t);
	}
	
	public Token scan() throws IOException, SyntaxException{
		for(;;peek = (char)stream.read()){
			if(peek == ' ' || peek == '\t'){
				continue;
			}else if(peek == '\n'){
				line = line + 1;
			}else{
				break;
			}
		}
		// exercise 2.6.1 extending the lexer to remove comment
		if(peek == '/'){
			peek = (char) stream.read();
			if(peek == '/'){
				// single line comment
				for(;;peek = (char)stream.read()){
					if(peek == '\n'){
						//end of single line comment, notify of skipp
						System.out.println("Skipped Single line comment");
						break;
					}
				}
			}else if(peek == '*'){
				// block comment
				char prevPeek = ' ';
				for(;;prevPeek = peek, peek = (char)stream.read()){
					if(prevPeek == '*' && peek == '/'){
						//end of multiline comment, notify of skip
						System.out.println("Skipped multi line comment");
						break;
					}
				}
			}else{
				System.out.println("Error: '/' without a '*' or '/'");
				throw new SyntaxException();
			}
		}

		// exercise 2.6.2 extending the lexer to handle relational operator
		if("<=!>".indexOf(peek) > -1){
			StringBuffer b = new StringBuffer();
			b.append(peek);
			b.append(peek);
			peek = (char)stream.read();
			if(peek == '='){
				b.append(peek);
			}
			new Rel(b.toString()).PrintRelOp();
			//return new Rel(b.toString());
		}

		// handle word
		if(Character.isLetter(peek)){
			StringBuffer b = new StringBuffer();
			do{
				b.append(peek);
				peek = (char)stream.read();
			}while(Character.isLetterOrDigit(peek));
			String s = b.toString();
			Word w = words.get(s);
			if(w == null){
				w = new Word(Tag.ID, s);
				words.put(s, w);
				w.PrintWord();
			}
			//return w;
		}
		
		Token t = new Token(peek);
		peek = ' ';
		return t;
	}
}