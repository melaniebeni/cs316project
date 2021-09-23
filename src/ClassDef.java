//⟨class def⟩ → "class" ⟨class name⟩ [ : ⟨class name⟩ ] ⟨class body⟩   // ⟨class name⟩ after ":" is the direct superclass name. 
class ClassDef
{
	ClassName className;
	ClassName superClassName; // value can be null
	ClassBody classBody;

	ClassDef(ClassName cn, ClassName scn, ClassBody cb)
	{
		className = cn;
		superClassName = scn;
		classBody = cb;
	}

	void semanticCheck()
	{
		SemanticChecker.currentClassName = className.id;
		SemanticChecker.currentClassDefEntry = new ClassDefEntry();
		if ( superClassName == null )
			SemanticChecker.currentClassDefEntry.superClassName = "";
		else
			SemanticChecker.currentClassDefEntry.superClassName = superClassName.id;
		SemanticChecker.symbolTable.put(className.id, SemanticChecker.currentClassDefEntry);
		classBody.semanticCheck();
	}
}
