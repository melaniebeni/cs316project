//⟨parameter list⟩ → ε | ⟨parameter⟩ ⟨parameter list⟩ 
 class ParameterList {
	 Parameters parameter;
		ParameterList parameterList; // value can be null
		
		ParameterList(Parameters p, ParameterList pl)
		{
			parameter = p;
			parameterList = pl;
		}

		void semanticCheck()
		{
			parameter.semanticCheck();
			if ( parameterList != null )
				parameterList.semanticCheck();
		}
}
