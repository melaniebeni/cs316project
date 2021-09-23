import java.util.*;

public abstract class Interpreter extends SemanticChecker
{
	public static void main(String argv[])

	/* 
	   argv[0]: source program file containing class definitions
	   argv[1]: lexical/syntactic/semantic error messages for the source program in argv[0]
	   argv[2]: single expression to be evaluated
	   argv[3]: lexical/syntactic error messages for the expression in argv[2]
	 
	   The evaluation result and runtime errors will be displayed on the terminal.
	*/

	{
		setIO( argv[0], argv[1] );
		setLex();

		getToken();
		ClassDefList classDefList = classDefList(); // build a parse tree
		if ( ! t.isEmpty() )
			displayln(t + " : Syntax Error, unexpected symbol");
		else if ( ! syntaxErrorFound )
		{
			classDefList.semanticCheck(); // build the symbol table and do semantic checking

			if ( ! semanticErrorFound )
			{
				closeIO();
				setIO( argv[2], argv[3] );

				getToken();
				Exp exp = exp(); // build a parse tree of the expression to be evaluated
				if ( ! t.isEmpty() )
					displayln(t + " : Syntax Error, unexpected symbol");
				else if ( ! syntaxErrorFound )
				{
					Val v = exp.Eval(new HashMap<String,Val>());  // evaluate the given expression
					if ( v != null )
						System.out.println( v.toString() );   // print the value on the terminal
				}				
			}
		}
		
		closeIO();
	}
}