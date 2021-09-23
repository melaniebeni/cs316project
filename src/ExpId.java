//⟨exp⟩ → ⟨id⟩ | ⟨int⟩ | ⟨float⟩ | ⟨floatE⟩ | "null" | "this" | "(" ⟨fun exp⟩ ")" 
import java.util.*;

class ExpId extends Exp
{
	String id;
	
	ExpId(String s)
	{
		id = s;
	}

	void semanticCheck()
	{
		if ( !SemanticChecker.currentFieldVarList.contains(id) &&
		     !SemanticChecker.currentParameterList.contains(id) )
		{
			IO.displayln( "Error: variable "+id+" in "+
			               SemanticChecker.currentClassName+"."+SemanticChecker.currentFunName+" is not declared" );
			SemanticChecker.semanticErrorFound = true;
		}
		
	}
	Val Eval(HashMap<String, Val> hashMap) {
		 
		return null;
	}
}