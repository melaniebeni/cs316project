//⟨exp⟩ → ⟨id⟩ | ⟨int⟩ | ⟨float⟩ | ⟨floatE⟩ | "null" | "this" | "(" ⟨fun exp⟩ ")" 
import java.util.*;

abstract class Exp
{
	//abstract Val Eval(HashMap<String,Val> state); You implement this function.
	abstract void semanticCheck();

	 Val Eval(HashMap<String, Val> hashMap) {
		 
		return null;
	}
}