
class FunDef
{
	Header header;
	Exp exp;

	FunDef(Header h, Exp e)
	{
		header = h;
		exp = e;
	}

	void semanticCheck()
	{
		header.semanticCheck();
		exp.semanticCheck();
	}
}