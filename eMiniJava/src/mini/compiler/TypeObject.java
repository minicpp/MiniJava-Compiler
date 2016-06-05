package mini.compiler;

/**
 * @author ddrmsdos type
 * the class defines type
 */
public class TypeObject {
	private int type = TypeConstants.NULL; // variable type
	private  String typeName = "";//name of class type

	/*
	 * default construction
	 */
	public TypeObject() {

	}

	/**
	 * set construction of integer type
	 * @param paramType integer
	 */
	public TypeObject(int paramType) {
		this.type = paramType;
		this.typeName="";
	}

	/**
	 * Set construction of integer type and respective string name of class
	 * @param paramType integer
	 * @param paramTypeName String
	 */
	public TypeObject(int paramType, String paramTypeName) {
		this(paramType);
		this.typeName = paramTypeName;
	}

	/**
	 * return id of type
	 * @return integer
	 */ 
	public int getType() {
		return type;
	}

	/**
	 * set id of type
	 * @param type integer
	 * @return void
	 */	
	public void setType(int type) {
		this.type = type;
	}

	/**
	 * Get type of a class
	 * @return String
	 */
	public String getTypeName() {
		return typeName;
	}

	/**
	 * Set the name of a class type
	 * @param typeName
	 * @return void
	 */
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	/**
	 * the class type is composed by integer type and class name
	 * @param paramType integer
	 * @param paramTypeName String
	 * @return void
	 */
	public void setTypeObject(int paramType, String paramTypeName) {
		this.type = paramType;
		this.typeName = paramTypeName;
	}

	/**
	 * check if the types are same.
	 */
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		if (type != TypeConstants.CLASS)
			return (type == ((TypeObject) obj).type);
		return ((type == ((TypeObject) obj).type) && typeName
				.equals(((TypeObject) obj).typeName));
	}
}