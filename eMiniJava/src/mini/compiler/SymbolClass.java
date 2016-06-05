package mini.compiler;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;

/**
 * the class for the "class symbol"
 * 
 * @author ddrmsdos
 * 
 */
public class SymbolClass extends SymbolAbstract {

	// Hash table for its variable members
	private Hashtable<String, SymbolField> fieldTable = new Hashtable<String, SymbolField>();
	// Hash table for its functions members
	private Hashtable<String, SymbolMethod> methodTable = new Hashtable<String, SymbolMethod>();
	// virtual function table
	private ArrayList<SymbolMethod> virtualTable = new ArrayList<SymbolMethod>();
	//assemble labels for the virtual table
	private String virtualTableName;
	

	// tables for the children's class
	private ArrayList<SymbolClass> childrenClassTable = new ArrayList<SymbolClass>();
	// tables for the parent's class
	private SymbolClass fatherClass = null;
	// Total symble table
	private SymbolClassTable symbolClassTable = null;

	public SymbolClass() {
		this.setMeType(CLASS);
	}

	public SymbolClass(String strClass, SymbolClassTable table) {
		this();
		this.setMeName(strClass);
		this.setSymbolClassTable(table);
	}

	/**
	 * 
	 * Get member variable object from variable name
	 * 
	 * @param field
	 *            String
	 * @return SymbolField
	 */
	public SymbolField getField(String field) {
		return fieldTable.get(field);
	}

	/**
	 * Add a member variable object
	 * 
	 * @param field
	 *            SymbolField
	 * @return boolean
	 */
	public boolean addField(SymbolField field) {
		if (isFieldExist(field.getMeName()))
			return false;
		field.setRelativeOffset(fieldTable.size()*SymbolField.BYTE_SIZE);
		fieldTable.put(field.getMeName(), field);
		return true;
	}
		
	/**
	 * Based on the member string, check if the member symbol exist.
	 * @param field
	 *            String
	 * @return boolean
	 */
	public boolean isFieldExist(String field) {
		if (fieldTable.get(field) == null)
			return false;
		return true;
	}
	
	/**
	 * Get the memory size required for the member variables.
	 */
	public int getFieldsByteSize()
	{
		return (fieldTable.size()*SymbolField.BYTE_SIZE);
	}

	/**
	 * set the offset of variables
	 * @param beforeBytes Begin offset Address: because of virtual table, non Inheritance beforeBytes=4;
	 * 
	 */
	public void setFiledsOffset(int beforeBytes){
		Enumeration<SymbolField> e = fieldTable.elements();
		while (e.hasMoreElements()) {
			SymbolField sf=e.nextElement();
			sf.setRelativeOffset(sf.getRelativeOffset()+beforeBytes);
		}
	}
	
	/**
	 * Get the size of memory of a class, including size of the child class with all parent class and size of a virtual table's pointer
	 */
	public int getClassSize(){
		int sum=0;
		for(SymbolClass sc=this;sc!=null;sc=sc.getFatherClass())
		{			
			sum+=sc.getFieldsByteSize();
		}		
		return sum+SymbolField.BYTE_SIZE; //The size of all members variable and a pointer of a virtual table
	}
	

	/**
	 * Return array of member variable based on the hash table of member variable
	 * @return
	 */
	public Object[] getFieldsToArray() {
		ArrayList<SymbolField> array = new ArrayList<SymbolField>();
		Enumeration<SymbolField> e = fieldTable.elements();
		while (e.hasMoreElements()) {
			array.add(e.nextElement());
		}
		return array.toArray();
	}

	/**
	 * Get member function from name of string
	 * @param method
	 *            String
	 * @return SymbolMethod
	 */
	public SymbolMethod getMethod(String method) {
		return methodTable.get(method);
	}

	/**
	 * Add member function symbol object
	 * @param method
	 *            SymbolMethod
	 * @return boolean
	 */
	public boolean addMethod(SymbolMethod method) {
		if (isMethodExist(method.getMeName()))
			return false;
		methodTable.put(method.getMeName(), method);
		return true;
	}

	/**
	 * Check if the member function exist.
	 * @param method
	 * @return
	 */
	public boolean isMethodExist(String method) {
		if (methodTable.get(method) == null)
			return false;
		return true;
	}

	public Object[] getMethodsToArray() {
		ArrayList<SymbolMethod> array = new ArrayList<SymbolMethod>();
		Enumeration<SymbolMethod> e = methodTable.elements();
		while (e.hasMoreElements()) {
			array.add(e.nextElement());
		}
		return array.toArray();
	}

	/**
	 * Find the member function from virtual table
	 * @param method
	 *            String
	 * @return SymbolMethod
	 */
	public SymbolMethod getMethodInVirtualTable(String method) {
		for (int i = 0; i < virtualTable.size(); ++i) {
			if (method.equals(virtualTable.get(i).getMeName()))
				return virtualTable.get(i);
		}
		return null;
	}

	/**
	 * Add member function to the virtual table.
	 * It check if there is parent class. If there is parent function, the child class will add all functions in parent.
	 * Now the algorithm is not very efficient, it can be further optimized.
	 * @param method
	 *            SymbolMethod
	 * @return boolean: Usually, it returns true, if the method exists in both parent and child,
	 * and they have different parameters and return value, it returns false.
	 */
	public boolean addMethodToVirtualTable(SymbolMethod method) {
		for (int i = 0; i < virtualTable.size(); ++i) {
			SymbolMethod itemMethod = virtualTable.get(i);
			if (method.getMeName().equals(itemMethod.getMeName())) {
				// override the same function
				if (method.equals(itemMethod)) {
					// check the parameters are same or not
					method.setRelativeOffset(itemMethod.getRelativeOffset());
					virtualTable.set(i, method);
					return true;
				} else {
					// report error for not identical methods with same function name
					return false;
				}
			}
		}
		// add new function directly
		method.setRelativeOffset(virtualTable.size()*SymbolMethod.BYTE_SIZE);
		virtualTable.add(method);
		return true;
	}

	/**
	 * return the array with elements from virtual table
	 * 
	 * @return SymbolMethod[]
	 */
	public Object[] getVirtualTableToArray() {
		return virtualTable.toArray();
	}

	/**
	 * Add a child class symbol to the current object
	 * @param childClass
	 *            SymbolClass
	 * @return boolean
	 */
	public boolean addChildClass(SymbolClass childClass) {
		if (isChildClassExist(childClass.getMeName())) {
			return false;
		}
		childrenClassTable.add(childClass);
		return true;
	}

	/**
	 * Based on the string of child class, test if the symbol exists in current class
	 * @param childClass
	 *            String
	 * @return boolean
	 */
	public boolean isChildClassExist(String childClass) {
		for (int i = 0; i < childrenClassTable.size(); ++i) {
			SymbolClass symbolClass = childrenClassTable.get(i);
			if (symbolClass.getMeName().equals(childClass)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * return the array composed by the direct child class
	 * @return SymbolClass[]
	 */
	public Object[] getChildClassesToArray() {
		return childrenClassTable.toArray();
	}

	/**
	 * get object of parent class
	 * @return SymbolClass 如果没有父类返回null
	 */
	public SymbolClass getFatherClass() {
		return fatherClass;
	}

	/**
	 * Set the parent symbol of the current class
	 * @param fatherClass
	 *            SymbolClass
	 * @return boolean 如果在父类中已经设置过该子类,则返回失败
	 */
	public boolean setFatherClass(SymbolClass fatherClass) {
		this.fatherClass = fatherClass;
		return fatherClass.addChildClass(this);		
	}

	/**
	 * Check if there is parent symbol in current class
	 * @return boolean
	 */
	public boolean isFatherExist() {
		if (this.fatherClass != null)
			return true;
		return false;
	}
	
	/**
	 * find ancestor class (including itself)
	 * @param ancestor String
	 * @return SymbolClass
	 */
	public SymbolClass getAncestorClass(String ancestor){
		for(SymbolClass sc=this;sc!=null;sc=sc.getFatherClass()){
			if(sc.getMeName().equals(ancestor)){
				return sc;
			}
		}
		return null;
	}

	/**
	 * Get symbol table
	 * @return SymbolClassTable
	 */
	public SymbolClassTable getSymbolClassTable() {
		return symbolClassTable;
	}

	/**
	 * Set symbolic table
	 * 
	 * @param symbolClassTable
	 *            SymbolClassTable
	 */
	public void setSymbolClassTable(SymbolClassTable symbolClassTable) {
		this.symbolClassTable = symbolClassTable;
	}

	public String getVirtualTableName() {
		return virtualTableName;
	}
	
	/**
	 * reset the name, and label of virtual table in assembly language
	 */
	@Override
	public void setMeName(String meName) {
		// TODO Auto-generated method stub
		super.setMeName(meName);
		virtualTableName="table"+"@"+meName;
	}
}