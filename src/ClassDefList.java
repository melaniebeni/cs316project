//⟨class def list⟩ → ⟨class def⟩ | ⟨class def⟩ ⟨class def list⟩ 
 class ClassDefList {
	ClassDef   classDef;
	ClassDefList   classDefList;
	ClassDefList(ClassDef c, ClassDefList cl)
	{
		classDef = c;
		classDefList = cl;
	}

	void semanticCheck()
	{
		classDef.semanticCheck();
		if ( classDefList != null )
			classDefList.semanticCheck();
	}
}
 