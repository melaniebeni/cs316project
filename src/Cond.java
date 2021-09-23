import java.util.*;

class Cond extends FunExp
{
	Exp exp1;
	Exp exp2;
	Exp exp3;

	Cond(Exp e1, Exp e2, Exp e3)
	{
		exp1 = e1;
		exp2 = e2;
		exp3 = e3;
	}

	void semanticCheck()
	{
		exp1.semanticCheck();
		exp2.semanticCheck();
		exp3.semanticCheck();
	}
	Val Eval(HashMap<String, Val> hashMap) {
		 
		return null;
	}
}