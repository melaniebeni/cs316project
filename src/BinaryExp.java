import java.util.HashMap;

//⟨binary exp⟩ → ⟨arith exp⟩ | ⟨bool exp⟩ | ⟨comp exp⟩ | ⟨dot exp⟩ 
    	//⟨arith exp⟩ → ⟨arith op⟩ ⟨exp⟩ ⟨exp⟩ 
    	//⟨bool exp⟩ → ⟨bool op⟩ ⟨exp⟩ ⟨exp⟩ 
    	//⟨comp exp⟩ → ⟨comp op⟩ ⟨exp⟩ ⟨exp⟩ 
    	//⟨dot exp⟩ → "." ⟨exp⟩ ⟨exp⟩ 
abstract class BinaryExp extends FunExp
{
	Exp exp1;
	Exp exp2;

	BinaryExp(Exp e1, Exp e2)
	{
		exp1 = e1;
		exp2 = e2;
	}

	void semanticCheck()
	{
		exp1.semanticCheck();
		exp2.semanticCheck();
	}
	Val Eval(HashMap<String, Val> hashMap) {
		 
		return null;
	}
}

