
import java.util.*;

class ExpNull extends Exp
{	
	static final ExpNull nullExp = new ExpNull();

	void semanticCheck()
	{
	}
	Val Eval(HashMap<String, Val> hashMap) {
		 
		return null;
	}
}