
class ExpList
{
	Exp exp;
	ExpList expList; // value can be null

	void semanticCheck()
	{
		exp.semanticCheck();
		if ( expList != null )
			expList.semanticCheck();
	}
	
	ExpList(Exp e, ExpList el)
	{
		exp = e;
		expList = el;
	}
	
	Exp firstExp()
	{
		return exp;
	}
	
	Exp secondExp()
	{
		return expList.firstExp();
	}

	ExpList tailExpList()
	{
		return expList;
	}
}