package analyzercomponents;

public class Word extends Token{
	public final String lexeme;
	public Word(int t, String s){
		super(t);
		lexeme = new String(s);
	}

	public void PrintWord(){
		System.out.println(super.tag+" - "+lexeme);
	}
}
