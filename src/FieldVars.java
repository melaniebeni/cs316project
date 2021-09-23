//⟨field var list⟩ → ε | ⟨field var⟩ ⟨field var list⟩ 
class FieldVars {
	
	String id;

	FieldVars(String s)
	{
		id = s;
	}

	void semanticCheck()
	{
		SemanticChecker.currentClassDefEntry.fields.add(id);
	}
	
}
