package mini.compiler;

/**
 * @author ddrmsdos variable type
 */
public class SymbolField extends SymbolAbstract {
	/**
	 * NULL type
	 */
	public static final int BELONG_NULL = 0;
	/**
	 * class type
	 */
	public static final int BELONG_CLASS = 1;
	/**
	 * name of function
	 */
	public static final int BELONG_PARAM = 2;
	/**
	 * local parameters in a function
	 */
	public static final int BELONG_LOCAL = 3;

	/**
	 * memory size for the type
	 */
	static final int BYTE_SIZE = 4; // size of a variable
	private TypeObject typeObject = new TypeObject(); // variable type
	private int belongInt = BELONG_NULL; // integer identification of a object
	private SymbolAbstract belongObject = null; // the object of the current variable
	private int            relativeOffset=0;
	
	public SymbolField(){
		this.setMeType(VAR);
	}
	/**
	 * construction function
	 * @param typeObject TypeObject variable type
	 * @param fieldName  String     variable name
	 */
	public SymbolField(TypeObject typeObject,String fieldName){
		this();
		this.setTypeObject(typeObject);
		this.setMeName(fieldName);
	}
	/**
	 * construction function
	 * @param typeObject TypeObject
	 * @param fieldName String
	 * @param method    SymbolMethod
	 * @param belongint int as BELONG_CLASS BELONG_PARAM BELONG_LOCAL
	 */
	public SymbolField(TypeObject typeObject,String fieldName,SymbolAbstract belongobject,int belongint){
		this(typeObject,fieldName);
		this.setBelongInt(belongint);
		this.setBelongObject(belongobject);
	}
	/**
	 * get variable type
	 * 
	 * @return TypeObject
	 */
	public TypeObject getTypeObject() {
		return typeObject;
	}

	/**
	 * set variable type
	 * 
	 * @param typeObject
	 *            TypeObject
	 */
	public void setTypeObject(TypeObject typeObject) {
		this.typeObject.setTypeObject(typeObject.getType(), typeObject
				.getTypeName());
	}

	/**
	 * get the type of belong BELONG_CLASS BELONG_PARAM BELONG_LOCAL
	 * 
	 * @return integer
	 */
	public int getBelongInt() {
		return belongInt;
	}

	/**
	 * set the type of belong BELONG_CLASS BELONG_PARAM BELONG_LOCAL
	 * 
	 * @param belongInt
	 *            integer
	 * @return void
	 */
	public void setBelongInt(int belongInt) {
		this.belongInt = belongInt;
	}

	/**
	 * return variables that keep the Object
	 * 
	 * @return SymbolAbstract as SymbolClass or SymbolMethod
	 */
	public SymbolAbstract getBelongObject() {
		return belongObject;
	}

	/**
	 *  
	 * @param belongObject
	 *            as SymbolClass or SymbolMethod
	 * @return void
	 */
	public void setBelongObject(SymbolAbstract belongObject) {
		this.belongObject = belongObject;
	}

	/**
	 * get relative offset address
	 * @return integer
	 */
	public int getRelativeOffset() {
		return relativeOffset;
	}

	/**
	 * set relative offset
	 * @param relativeOffset
	 * @return void
	 */
	public void setRelativeOffset(int relativeOffset) {
		this.relativeOffset = relativeOffset;
	}

	/**
	 * check the types of two variables
	 */
	@Override
	public boolean equals(Object arg0) {
		// TODO Auto-generated method stub
		SymbolField arg = (SymbolField) arg0;
		if (arg.typeObject.equals(this.typeObject))//Same type means the same variable
			return true;
		return false;
	}
	
}