package mini.test;

import mini.compiler.*;

/**
 * Print the records in symbolic table results from the first step
 * @author ddrmsdos
 * 
 */
public class TestFirstStep {
	private SymbolClassTable table;

	public TestFirstStep(SymbolClassTable table) {
		this.table = table;
	}

	public void PrintTable() {
		System.out.println("Information of all classes:");
		Object[] array = table.getClassesToArray();
		System.out.println("The number of classes in symbolic table:" + array.length);
		for (int i = 0; i < array.length; ++i) {
			PrintClass("\t", (SymbolClass) array[i]);
		}
	}

	public void PrintClass(String leftSpace, SymbolClass symbolClass) {
		System.out.println(leftSpace
				+ "---------------------------------------------");
		System.out.println(leftSpace + "Name of class:" + symbolClass.getMeName());
		System.out.println(leftSpace + "Type:" + symbolClass.getMeType()
				+ " Any superclass information:" + symbolClass.isFatherExist());
		System.out.println(leftSpace + "If the pointed table is empty or not:"
				+ (symbolClass.getSymbolClassTable() == null));
		System.out.println(leftSpace + "Current size of sub-class:"
				+ symbolClass.getChildClassesToArray().length + " The number of cureent virtual tables:"
				+ symbolClass.getVirtualTableToArray().length);

		Object[] fieldArray = symbolClass.getFieldsToArray();
		System.out.println(leftSpace + "The number of member variables in the symbolic table of class:" + fieldArray.length);
		for (int i = 0; i < fieldArray.length; ++i) {
			PrintField(leftSpace + "\t", (SymbolField) fieldArray[i]);
		}
		Object[] methodArray = symbolClass.getMethodsToArray();
		System.out.println(leftSpace + "The number of member functions in the symbolic table of class:" + methodArray.length);
		for (int i = 0; i < methodArray.length; ++i) {
			PrintMethod(leftSpace + "\t", (SymbolMethod) methodArray[i]);
		}
	}

	public void PrintField(String leftSpace, SymbolField symbolField) {
		System.out.println(leftSpace
				+ "---------------------------------------------");
		System.out.println(leftSpace + "Variable Name:" + symbolField.getMeName()
				+ " Symbol type:" + symbolField.getMeType());
		PrintTypeObject(leftSpace + "\t", symbolField.getTypeObject());
		System.out.print(leftSpace + "Scope:");
		switch (symbolField.getBelongObject().getMeType()) {
		case SymbolAbstract.CLASS:
			System.out.println("The variable in the scope of class");
			break;
		case SymbolAbstract.METHOD:
			System.out.println("The varialbe in the scope of member function");
			break;
		default:
			System.out.println("Cannot find the scope");
			break;
		}
		switch (symbolField.getBelongInt()) {
		case SymbolField.BELONG_CLASS:
			System.out
					.println(leftSpace
							+ "belongs to the member variable of a class, the name of the class is:"
							+ ((SymbolClass) symbolField.getBelongObject())
									.getMeName());
			break;
		case SymbolField.BELONG_PARAM:
			System.out.println(leftSpace
					+ "Belongs to a member function, the name of the member function is:"
					+ ((SymbolMethod) symbolField.getBelongObject())
							.getMeName());
			break;
		case SymbolField.BELONG_LOCAL:
			System.out.println(leftSpace
					+ "Belongs to a member function, the name of the member function is:"
					+ ((SymbolMethod) symbolField.getBelongObject())
							.getMeName());
			break;
		default:
			System.out.println(leftSpace + "Cannot find the belong of the variable");
			break;
		}
	}

	public void PrintMethod(String leftSpace, SymbolMethod symbolMethod) {
		System.out.println(leftSpace
				+ "---------------------------------------------");
		Object[] varArray = symbolMethod.getVarToArray();
		Object[] paramArray = symbolMethod.getParamToArray();
		Object[] localArray = symbolMethod.getLocalToArray();
		TypeObject returnType = symbolMethod.getReturnTypeObject();
		System.out.println(leftSpace + "Name of member function:" + symbolMethod.getMeName()
				+ " Type:" + symbolMethod.getMeType());
		System.out.println(leftSpace + "Type of return value:");
		PrintTypeObject(leftSpace + "\t", returnType);
		System.out.println(leftSpace + "Number of parameters of the member function:" + varArray.length);
		System.out.println(leftSpace + "Formal parameters:(" + paramArray.length + "¸ö)");
		for (int i = 0; i < paramArray.length; ++i) {
			PrintField(leftSpace + "\t", (SymbolField) paramArray[i]);
		}
		System.out.println(leftSpace + "Local variable:(" + localArray.length + "¸ö)");
		for (int i = 0; i < localArray.length; ++i) {
			PrintField(leftSpace + "\t", (SymbolField) localArray[i]);
		}
	}

	public void PrintTypeObject(String leftSpace, TypeObject typeObject) {
		System.out.println(leftSpace
				+ "---------------------------------------------");
		switch (typeObject.getType()) {
		case TypeConstants.INTARRAY:
			System.out.println(leftSpace + "Integer Array Type");
			break;
		case TypeConstants.BOOLEAN:
			System.out.println(leftSpace + "Boolean Type");
			break;
		case TypeConstants.INT:
			System.out.println(leftSpace + "Integer Type");
			break;
		case TypeConstants.CLASS:
			System.out.println(leftSpace + "Class Type:" + typeObject.getTypeName());
			break;
		default:
			System.out.println(leftSpace + "Unknow Type");
			break;
		}
	}
}
