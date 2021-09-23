//⟨parameter list⟩ → ε | ⟨parameter⟩ ⟨parameter list⟩ 
class Parameters
{
	String id;
	
	Parameters(String s)
	{
		id = s;
	}

	void semanticCheck()
	{
		SemanticChecker.currentParameterList.add(id);
	}
}