//⟨exp⟩ → ⟨id⟩ | ⟨int⟩ | ⟨float⟩ | ⟨floatE⟩ | "null" | "this" | "(" ⟨fun exp⟩ ")" 
import java.util.*;

class ExpFloat extends Exp
{
	float val;
	
	ExpFloat(float f)
	{
		val = f;
	}

	void semanticCheck()
	{
	}
	Val Eval(HashMap<String, Val> hashMap) {
		 
		return null;
	}
}