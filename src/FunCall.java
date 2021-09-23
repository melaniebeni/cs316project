//⟨fun call⟩ → ⟨fun name⟩ ⟨exp list⟩ 
	
import java.util.*;

class FunCall extends FunExp
{
	FunName funName;
        ExpList expList; // value can be null

	FunCall(FunName fn, ExpList el)
	{
		funName = fn;
		expList = el;
	}

	void semanticCheck()
	{
		if ( expList != null )
			expList.semanticCheck();
	}
	Val Eval(HashMap<String, Val> hashMap) {
		 
		return null;
	}
}