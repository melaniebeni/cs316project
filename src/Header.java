//⟨header⟩ → "(" ⟨fun name⟩ ⟨parameter list⟩ ")" 
import java.util.*;

class Header
{
	FunName funName;
	ParameterList parameterList; // value is null if <parameter list> is empty.

	Header(FunName f, ParameterList p)
	{
		funName = f;
		parameterList = p;
	}

	void semanticCheck()
	{
		SemanticChecker.currentFunName = funName.id;
		SemanticChecker.currentParameterList = new LinkedList<String>();
		SemanticChecker.currentClassDefEntry.funMap.put(funName.id, SemanticChecker.currentParameterList);
		if ( parameterList != null )
			parameterList.semanticCheck();
	}
}
