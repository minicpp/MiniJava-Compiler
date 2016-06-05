package mini.compiler;

public class TypeConstants {
	public static final int INTARRAY = 0;
	public static final int BOOLEAN = 1;
	public static final int INT = 2;
	public static final int CLASS = 3;
	public static final int VOID = 4;
	public static final int NULL = 5;

	/**
	 * mapping function
	 * Type -> int []  ... INTARRAY
	 *      -> boolean ... BOOLEAN
	 *      -> int     ... INT
	 *      -> id      ... CLASS
	 *
	 * @param choice int
	 * @return   int
	 */
	public static int MapChoiceToType(int choice) {
		return choice;
	}

}
