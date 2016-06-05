package mini.compiler;

import java.util.ArrayList;

/**
 * member function class
 * 
 * @author ddrmsdos
 * 
 */
public class SymbolMethod extends SymbolAbstract {
	
	static final int BYTE_SIZE = 4; // the memory size of a member function
	static final int SAVE_SIZE = 8; // IP and required pushed size of EBP
	private SymbolClass belongClass = null;
	private ArrayList<SymbolField> paramArray = new ArrayList<SymbolField>();
	private ArrayList<SymbolField> localArray = new ArrayList<SymbolField>();
	private TypeObject returnTypeObject = new TypeObject();
	private int relativeOffset=0;
	private String methodLabel;

	/**
	 * get label name of a function
	 * @return
	 */
	public String getMethodLabel() {
		return methodLabel;
	}	

	/**
	 * get relative offset address of a member function in virtual table
	 * @return integer
	 */
	public int getRelativeOffset() {
		return relativeOffset;
	}

	/**
	 * set the relative offset address of a member function in virtual table
	 * @param relativeOffset
	 */
	public void setRelativeOffset(int relativeOffset) {
		this.relativeOffset = relativeOffset;
	}

	public SymbolMethod() {
		this.setMeType(METHOD);
	}

	public SymbolMethod(TypeObject typeObject, String strMethod,
			SymbolClass symbolClass) {
		this();
		this.setReturnTypeObject(typeObject);
		this.setBelongClass(symbolClass);
		this.setMeName(strMethod);		
	}

	// operate member variables of a function

	/**
	 * check if the variable in the scale of a function
	 * 
	 * @param varName
	 *            String
	 * @return boolean non-existence returns false; existence returns true
	 */
	public boolean isVarExist(String varName) {
		if (isParamExist(varName) || isLocalExist(varName)) {
			return true;
		}
		return false;
	}

	/**
	 * based on the name of variable get the object in the scale of a member function
	 * @param varName
	 *            String
	 * @return SymbolField return the handle of the variable, or null
	 */
	public SymbolField getVar(String varName) {
		SymbolField field = this.getParamByName(varName);
		if (field == null)
			return field = this.getLocalByName(varName);
		return field;
	}

	/**
	 * return all variables from the scale of a member function
	 * 
	 * @return SymbolField[]
	 */
	public Object[] getVarToArray() {
		ArrayList<SymbolField> field = new ArrayList<SymbolField>();
		field.addAll(paramArray);
		field.addAll(localArray);
		return  field.toArray();
	}

	// member functions related to parameters

	/**
	 * register a variable to a member function
	 * 
	 * @param field
	 *            SymbolField
	 * @return boolean success return true£¬if the name exists, return false
	 */
	public boolean pushBackParam(SymbolField field) {
		if (this.isVarExist(field.getMeName()))
			return false;
		field.setRelativeOffset(paramArray.size()*SymbolField.BYTE_SIZE+
				SymbolMethod.SAVE_SIZE); 
		paramArray.add(field);
		return true;
	}

	/**
	 * Based on the variable name, check if the variable is a parameter of a member function
	 * @param param
	 *            String
	 * @return boolean
	 */
	public boolean isParamExist(String param) {
		for (int i = 0; i < paramArray.size(); ++i) {
			if (paramArray.get(i).getMeName().equals(param))
				return true;
		}
		return false;
	}

	/**
	 * get the number of parameters
	 * 
	 * @return integer
	 */
	public int getParamLength() {
		return paramArray.size();
	}

	/**
	 * get the memory size of parameters
	 * 
	 * @return integer
	 */
	public int getParamByteSize() {
		return paramArray.size() * SymbolField.BYTE_SIZE;
	}

	/**
	 * get parameter objects from name
	 * 
	 * @param field
	 *            String
	 * @return SymbolField find nothing return null,return handle of object for the returned parameter
	 */
	public SymbolField getParamByName(String field) {
		for (int i = 0; i < paramArray.size(); ++i) {
			if (field.equals(paramArray.get(i).getMeName()))
				return paramArray.get(i);
		}
		return null;
	}

	/**
	 * get parameter handles
	 * 
	 * @param index
	 * @return SymbolField find nothing return null£¬return handle of parameter handle
	 */
	public SymbolField getParamByIndex(int index) {
		return paramArray.get(index);
	}

	/**
	 * return array from objects of parameters
	 * 
	 * @return SymbolField[]
	 */
	public Object[] getParamToArray() {
		return  paramArray.toArray();
	}

	// local variables
	/**
	 * add a local variable
	 * 
	 * @param field
	 *            SymbolField
	 * @return boolean
	 */
	public boolean pushBackLocal(SymbolField field) {
		if (this.isVarExist(field.getMeName()))
			return false;
		field.setRelativeOffset((localArray.size()+1)*SymbolField.BYTE_SIZE*(-1));
		localArray.add(field);
		return true;
	}

	/**
	 * check the existence of a local variable
	 * 
	 * @param param
	 *            String
	 * @return boolean
	 */
	public boolean isLocalExist(String param) {
		for (int i = 0; i < localArray.size(); ++i) {
			if (localArray.get(i).getMeName().equals(param))
				return true;
		}
		return false;
	}

	/**
	 * get the number of local variables
	 * 
	 * @return integer
	 */
	public int getLocalLength() {
		return localArray.size();
	}

	/**
	 * get member size of all local variables
	 * 
	 * @return integer
	 */
	public int getLocalByteSize() {
		return localArray.size() * SymbolField.BYTE_SIZE;
	}

	/**
	 * get local variables object from name
	 * 
	 * @param strImage
	 *            String
	 * @return SymbolField
	 */
	public SymbolField getLocalByName(String strImage) {
		for (int i = 0; i < localArray.size(); ++i) {
			if (strImage.equals(localArray.get(i).getMeName()))
				return localArray.get(i);
		}
		return null;
	}

	/**
	 * get local variable by index
	 * 
	 * @param index
	 *            integer
	 * @return SymbolField
	 */
	public SymbolField getLocalByIndex(int index) {
		return localArray.get(index);
	}

	/**
	 * get array composed by local variables
	 * 
	 * @return
	 */
	public Object[] getLocalToArray() {
		return localArray.toArray();
	}

	/**
	 * get class symbol of the member function
	 * 
	 * @return SymbolClass
	 */
	public SymbolClass getBelongClass() {
		return belongClass;
	}

	/**
	 * set blong of class for the current member function
	 * 
	 * @param belongClass
	 *            SymbolClass
	 * @return void
	 */
	public void setBelongClass(SymbolClass belongClass) {
		this.belongClass = belongClass;
	}

	/**
	 * get return type of the member function
	 * 
	 * @return TypeObject
	 */
	public TypeObject getReturnTypeObject() {
		return returnTypeObject;
	}

	/**
	 * set return type of the member function
	 * 
	 * @param returnTypeObject
	 *            TypeObject
	 * @return void
	 */
	public void setReturnTypeObject(TypeObject returnTypeObject) {
		this.returnTypeObject.setTypeObject(returnTypeObject.getType(),
				returnTypeObject.getTypeName());
	}

	/**
	 * check if the two member functions are identical:
	 * same return type, same parameter numbers and types.
	 */
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		SymbolMethod method = (SymbolMethod) obj;
		// same return type
		if (!this.returnTypeObject.equals(method.returnTypeObject))
			return false;
		// same number of parameters
		if (!(this.getParamLength() == method.getParamLength()))
			return false;
		// same type for each parameter
		for (int i = 0; i < this.getParamLength(); ++i) {
			SymbolField field_0 = this.getParamByIndex(i);
			SymbolField field_1 = method.getParamByIndex(i);
			if (!field_0.equals(field_1))
				return false;
		}
		return true;
	}

	/**
	 * overload the name of member function, and set the assembly label
	 */
	@Override
	public void setMeName(String meName) {
		// TODO Auto-generated method stub
		super.setMeName(meName);
		methodLabel= "method"+"@"+this.belongClass.getMeName()+"@"+meName;
	}

}