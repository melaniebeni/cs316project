//⟨exp⟩ → ⟨id⟩ | ⟨int⟩ | ⟨float⟩ | ⟨floatE⟩ | "null" | "this" | "(" ⟨fun exp⟩ ")" 
import java.util.*;

class ExpInt extends Exp
{
	int val;
	
	ExpInt(int i)
	{
		val = i;
	}

	void semanticCheck()
	{
	}
	Val Eval(HashMap<String, Val> hashMap) {
		 
		return null;
	}
}