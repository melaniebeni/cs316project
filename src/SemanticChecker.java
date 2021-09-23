import java.util.*;

public abstract class SemanticChecker extends Parser
{
	static HashMap<String, ClassDefEntry> symbolTable = new HashMap<String, ClassDefEntry>();

	// The following are helper variables used to build the symbol table.

	static ClassDefEntry currentClassDefEntry;
	static LinkedList<String> currentFieldVarList;
	static LinkedList<String> currentParameterList;
	static String currentClassName;
	static String currentFunName;

	static boolean semanticErrorFound = false;

	public static void main(String argv[])
	{
		// argv[0]: input file containing a program
		// argv[1]: output file displaying class names and their fields, function names and their parameters, error messages 

		setIO( argv[0], argv[1] );
		setLex();

		getToken();
		ClassDefList classDefList = classDefList(); // build a parse tree
		if ( ! t.isEmpty() )
			displayln(t + " : Syntax Error, unexpected symbol");
		else if ( ! syntaxErrorFound )
		{
			classDefList.semanticCheck(); // build the symbol table and do semantic checking

			displayln("\nClass names and their field variables, function names, parameters are displayed below.\n");

			Set<Map.Entry<String, ClassDefEntry>> classSymbolSet = symbolTable.entrySet();
			for ( Map.Entry<String, ClassDefEntry> entry: classSymbolSet )
			{
				displayln(entry.toString());
				displayln("");
			}
		}
		
		closeIO();
	}
}