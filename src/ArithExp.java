import java.util.HashMap;

abstract class ArithExp extends BinaryExp
{
	ArithExp(Exp e1, Exp e2)
	{
		super(e1, e2);
	}
	Val Eval(HashMap<String, Val> hashMap) {
		 
		return null;
	}
}