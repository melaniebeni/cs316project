final class NullObj extends Val
{
	static final NullObj nullObj = new NullObj(); // In this interpreter, this is the only object of NullObj used.
	
	public String toString()
	{
		return "null";
	}

	Val cloneVal()

	// In this interpreter, returns the above NullObj.nullObj without cloning.

	{
		return NullObj.nullObj;
	}

	float floatVal()
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