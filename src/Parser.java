/**

This class is a top-down, recursive-descent parser for the following syntactic categories:

<class def list> --> <class def> | <class def> <class def list>
<class def> --> "class" <class name> [ : <class name> ] <class body>
<class name> --> <id>
<class body> --> "{" <field var list> <fun def list> "}"
<field var list> --> epsilon | <field var> <field var list>
<field var> --> <id>
<fun def list> --> epsilon | <fun def> <fun def list>
<fun def> --> "(" <header> <exp> ")"
<header> --> "(" <fun name> <parameter list> ")"
<fun name> --> <id>
<parameter list> --> epsilon | <parameter> <parameter list>
<parameter> --> <id>
<exp> --> <id> | <int> | <float> | <floatE> | "null" | "this" | "(" <fun exp> ")"
<fun exp> --> <fun call> | <binary exp> | <cond> | <not>
<fun call> --> <fun name> <exp list>
<exp list> --> epsilon | <exp> <exp list>
<binary exp> --> <arith exp> | <bool exp> | <comp exp> | <dot exp>
<arith exp> --> <arith op> <exp> <exp>
<bool exp> --> <bool op> <exp> <exp>
<comp exp> --> <comp op> <exp> <exp>
<dot exp> --> "." <exp> <exp>
<cond> --> "if" <exp> <exp> <exp>
<not> --> ! <exp>
<arith op> --> + | - | * | /
<bool op> --> "|" | "&"
<comp op> --> "<" | "<=" | ">" | ">=" | "="

Note: "epsilon" denotes the empty string.
 
The definitions of the tokens are given in the lexical analyzer class file "LexAnalyzer.java". 

The following variables/functions of "IO"/"LexAnalyzer" classes are used:

static void display(String s)
static void displayln(String s)
static void setIO(String inFile, String outFile)
static void closeIO()

static void setLex()
static String t // holds an extracted token
static State state // the current state of the finite automaton
static int getToken() // extracts the next token

An explicit parse tree is constructed in the form of nested class objects.

**/


public abstract class Parser extends LexAnalyzer
{
	static boolean syntaxErrorFound = false;


	public static ClassDefList classDefList()
	
	// <class def list> --> <class def> | <class def> <class def list>
		
	{	
		ClassDef classDef = classDef();	
		if ( state == State.Keyword_class )
		{
			ClassDefList classDefList = classDefList();
			return new ClassDefList(classDef, classDefList);
		}
		else // tail <class def list> is epsilon
			return new ClassDefList(classDef, null);
	}

	public static ClassDef classDef()

	// <class def> --> "class" <class name> [ : <class name> ] <class body>

	{
		if ( state == State.Keyword_class )
		{
			getToken();
			ClassName className = className();
			ClassName superClassName = null;
			if ( state == State.Colon )
			{
				getToken();
				superClassName = className();
			}
			ClassBody classBody = classBody();
			return new ClassDef(className, superClassName, classBody);
		}
		else
		{
			errorMsg(0);
			return null;
		}
	}

	public static ClassName className()

	// <class name> --> <id>

	{
		String id = id();
		return new ClassName(id);
	}

	public static String id() // extract the identifier
	{
		if ( state == State.Id )
		{
			String id = t;
			getToken();
			return id;
		}
		else
		{
			errorMsg(1);
			return null;
		}
	}

	public static ClassBody classBody()

	// <class body> --> "{" <field var list> <fun def list> "}"

	{
		if ( state == State.LBrace )
		{
			getToken();
			FieldVarList fieldVarList = fieldVarList();
			FunDefList funDefList = funDefList();
			if ( state == State.RBrace )
			{
				getToken();
				return new ClassBody(fieldVarList, funDefList);
			}
			else
			{
				errorMsg(2);
				return null;
			}
		}
		else
		{
			errorMsg(3);
			return null;
		}
	}

	public static FieldVarList fieldVarList()
	
	// <field var list> --> epsilon | <field var> <field var list>
		
	{		
		if ( state == State.Id )
		{
			FieldVars fieldVar = fieldVar();
			FieldVarList fieldVarList = fieldVarList();
			return new FieldVarList(fieldVar, fieldVarList);
		}
		else  // <field var list> is epsilon
			return null;
	}

	public static FieldVars fieldVar()

	// <field var> --> <id>

	{
		String id = id();
		return new FieldVars(id);
	}

	public static FunDefList funDefList()
	
	// <fun def list> --> epsilon | <fun def> <fun def list>
		
	{		
		if ( state == State.LParen )
		{
			FunDef funDef = funDef();
			FunDefList funDefList = funDefList();
			return new FunDefList(funDef, funDefList);
		}
		else // <fun def list> is epsilon
			return null;
	}

	public static FunDef funDef()

	// <fun def> --> "(" <header> <exp> ")"

	{
		if ( state == State.LParen )
		{
			getToken();
			Header header = header();
			Exp exp = exp();
			if ( state == State.RParen )
			{
				getToken();
				return new FunDef(header, exp);
			}
			else
			{
				errorMsg(4);
				return null;
			}
		}
		else 
		{
			errorMsg(5);
			return null;
		}
	}

	public static Header header()

	// <header> --> "(" <fun name> <parameter list> ")" 

	{
		if ( state == State.LParen )
		{
			getToken();
			FunName funName = funName();
			ParameterList parameterList = parameterList();
			if ( state == State.RParen )
			{
				getToken();
				return new Header(funName, parameterList);
			}
			else
			{
				errorMsg(4);
				return null;
			}
		}
		else
		{
			errorMsg(5);
			return null;
		}
	}

	public static FunName funName()

	// <fun name> --> <id>

	{
		String id = id();
		return new FunName(id);
	}

	public static ParameterList parameterList()
	
	// <parameter list> --> epsilon | <parameter> <parameter list>
		
	{		
		if ( state == State.Id )
		{
			Parameters parameter = parameter();
			ParameterList parameterList = parameterList();
			return new ParameterList(parameter, parameterList);
		}
		else  // <parameter list> is epsilon
			return null;
	}

	public static Parameters parameter()

	// <parameter> --> <id>

	{
		String id = id();
		return new Parameters(id);
	}

	public static Exp exp()

	// <exp> --> <id> | <int> | <float> | <floatE> | "null" | "this" | "(" <fun exp> ")"
	
	{
		switch ( state )
		{
			case Id:
				String id = t;
				getToken();
				return new ExpId(id);
				
			case Int:
				ExpInt intVal = new ExpInt(Integer.parseInt(t));
				getToken();
				return intVal;
				
			case Float: case FloatE:
				ExpFloat floatVal = new ExpFloat(Float.parseFloat(t));
				getToken();
				return floatVal;

			case Keyword_null:
				getToken();
				return ExpNull.nullExp;

			case Keyword_this:
				getToken();
				return ExpThis.thisExp;

			case LParen:
				getToken();
				FunExp funExp = funExp();
				if ( state == State.RParen )
				{
					getToken();
					return funExp;
				}
				else
				{
					errorMsg(4);
					return null;
				}
			
			default:
				errorMsg(6);
				return null;
		}
	}

	public static FunExp funExp()

	// <fun exp> --> <fun call> | <binary exp> | <cond> | <not>

	{
		if ( state == State.Id )
			return funCall();
		else if ( state.isBinaryOp() ) // isBinaryOp() defined in LexAnalyzer.java
			return binaryExp();
		else if ( state == State.Keyword_if )
			return cond();
		else if ( state == State.Not )
			return not();
		else
		{
			errorMsg(7);
			return null;
		}
	}

	public static FunCall funCall()

	// <fun call> --> <fun name> <exp list>

	{
		FunName funName = funName();
		ExpList expList = expList();
		return new FunCall(funName, expList);
	}

	public static ExpList expList()

	// <exp list> --> epsilon | <exp> <exp list>

	{
		switch ( state )
		{
			case Id: case Int: case Float: case FloatE:
			case Keyword_null: case Keyword_this: case LParen:
				Exp exp = exp();
				ExpList expList = expList();
				return new ExpList(exp, expList);
			
			default: // <exp list> is epsilon
				return null;
		}
	}

	public static BinaryExp binaryExp()

	// <binary exp> --> <arith exp> | <bool exp> | <comp exp> | <dot exp>

	{
		switch ( state )
		{
			case Add: case Sub: case Mul: case Div:
				return arithExp();

			case Or: case And:
				return boolExp();

			case Lt: case Le: case Gt: case Ge: case Eq:
				return compExp();

			default: // case DotOp
				return dotExp();
		}
	}

	public static ArithExp arithExp()

	// <arith exp> --> <arith op> <exp> <exp>
	// <arith op> --> + | - | * | /

	{
		State arithOp = state;
		getToken();
		Exp exp1 = exp();
		Exp exp2 = exp();

		switch ( arithOp )
		{
			case Add: return new Add(exp1, exp2);
			case Mul: return new Mul(exp1, exp2);
			case Sub: return new Sub(exp1, exp2);
			default:  return new Div(exp1, exp2); // case Div:
		}
	}

	public static BoolExp boolExp()

	// <bool exp> --> <bool op> <exp> <exp>
	// <bool op> --> "|" | "&"

	{
		State boolOp = state;
		getToken();
		Exp exp1 = exp();
		Exp exp2 = exp();

		switch ( boolOp )
		{
			case Or: return new Or(exp1, exp2);
			default: return new And(exp1, exp2); // case And:
		}
	}

	public static CompExp compExp()

	// <comp exp> --> <comp op> <exp> <exp>
	// <comp op> --> "<" | "<=" | ">" | ">=" | "="

	{
		State compOp = state;
		getToken();
		Exp exp1 = exp();
		Exp exp2 = exp();

		switch ( compOp )
		{
			case Lt: return new Lt(exp1, exp2);
			case Le: return new Le(exp1, exp2);
			case Gt: return new Gt(exp1, exp2);
			case Ge: return new Ge(exp1, exp2);
			default: return new Eq(exp1, exp2); // case Eq:
		}
	}

	public static DotExp dotExp()

	// <dot exp> --> "." <exp> <exp>

	{
		getToken(); // flush "."
		Exp exp1 = exp();
		Exp exp2 = exp();
		return new DotExp(exp1, exp2);
	}

	public static Cond cond()

	// <cond> --> "if" <exp> <exp> <exp>

	{
		getToken(); // flush "if"
		Exp exp1 = exp();
		Exp exp2 = exp();
		Exp exp3 = exp();
		return new Cond(exp1, exp2, exp3);
	}

	public static Not not()

	// <not> --> ! <exp>

	{
		getToken(); // flush "!"
		Exp exp = exp();
		return new Not(exp);
	}

	public static void errorMsg(int i)
	{
		syntaxErrorFound = true;

		display(t + " : Syntax Error, unexpected symbol where");

		switch ( i )
		{
		case 0: displayln(" \"class\" expected"); return;
		case 1: displayln(" identifier expected"); return;
		case 2: displayln(" } expected"); return;
		case 3: displayln(" { expected"); return;
		case 4: displayln(" ) expected"); return;
		case 5:	displayln(" ( expected"); return;
		case 6: displayln(" identifier, integer, float, null, this, or ( expected"); return;
		case 7: displayln(" identifier, arithmetic/boolean/comparison/dot operator, if, or ! expected"); return;
		}
	}

	public static void main(String argv[])
	{
		// argv[0]: input file containing a string in <class def list>
		// argv[1]: output file displaying error messages, if any 

		setIO( argv[0], argv[1] );
		setLex();

		getToken();
		ClassDefList classDefList = classDefList(); // build a parse tree
		if ( ! t.isEmpty() )
			displayln(t + " : Syntax Error, unexpected symbol");

		closeIO();
	}
}