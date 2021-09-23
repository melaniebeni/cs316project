//⟨class body⟩ → "{" ⟨field var list⟩ ⟨fun def list⟩ "}" 
 class ClassBody {
        FieldVarList fvl;
        FunDefList fdl;
        
        ClassBody(FieldVarList fv, FunDefList fd){
        	this.fvl = fv;
            this.fdl = fd;
        }
        void semanticCheck()
    	{
    		if ( fvl != null )
    			fvl.semanticCheck();
    		SemanticChecker.currentFieldVarList = SemanticChecker.currentClassDefEntry.fields;
    		if ( fdl != null )
    			fdl.semanticCheck();
    	}
}
