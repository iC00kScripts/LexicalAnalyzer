package analyzercomponents;

public class Num extends Token {
	public final int value;
	public Num(int v){
		super(Tag.NUM);
		value = v;
	}
    public void PrintDigit(){
        System.out.println(Tag.NUM+" - "+value);
    }
}
