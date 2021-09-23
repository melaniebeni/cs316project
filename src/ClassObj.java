import java.util.*;

final class ClassObj extends Val
{
	String className; // class name of this object
	HashMap<String, Val> fields; // field name mapped to its value

	ClassObj(String cn, HashMap<String, Val> fs)
	{
		className = cn;
		fields = fs;
	}

	public String toString()
	{
		return className + " object: " + fields;
	}

	Val cloneVal()

	// In this interpreter, returns this ClassObj object without cloning.

	{
		return this;
	}

	float floatVal()

	// This is not used by the interpreter. For other purposes, this might return some code value of this object.

	{
		return 0.0f;
	}

	boolean isNumber()
	{
		return false;
	}

	boolean isZero()
	{
		return false;
	}
}