//⟨fun def list⟩ → ε | ⟨fun def⟩ ⟨fun def list⟩ 
class FunDefList {
	FunDefList funDefList;
	FunDef funDef;
	FunDefList(FunDef f, FunDefList fl)
	{
		funDef = f;
		funDefList = fl;
	}

	void semanticCheck()
	{
		funDef.semanticCheck();
		if ( funDefList != null )
			funDefList.semanticCheck();
	}
}
