import java.util.*;

public class ClassDefEntry {
		String superClassName; // value is "" if superclass is absent
		LinkedList<String> fields = new LinkedList<String>();
		HashMap<String, LinkedList<String>> funMap = new HashMap<String, LinkedList<String>>();
			// function names mapped to their parameters

		public String toString()
		{
			return "\nsuperclass=" + superClassName + "\nfields=" + fields + "\nfunctions=" + funMap;
		}
	}