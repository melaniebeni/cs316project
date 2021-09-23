//⟨field var list⟩ → ε | ⟨field var⟩ ⟨field var list⟩ 
 class FieldVarList {
	 FieldVars fieldVar;
		FieldVarList fieldVarList; // value can be null
		
		FieldVarList(FieldVars f, FieldVarList fl)
		{
			fieldVar = f;
			fieldVarList = fl;
		}

		void semanticCheck()
		{
			fieldVar.semanticCheck();
			if ( fieldVarList != null )
				fieldVarList.semanticCheck();
		}
 }
