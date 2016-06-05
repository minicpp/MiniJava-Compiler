package mini.compiler;

/**
 * the parents' class of each symobol
 * 
 * @author ddrmsdos
 * 
 */
public class SymbolAbstract {
	public static final int NULL = 0;
	public static final int CLASS = 1;
	public static final int METHOD = 2;
	public static final int VAR = 3;

	private String meName;
	private int meType;

	/**
	 * return name of a symbol
	 * 
	 * @return String
	 */
	public String getMeName() {
		return meName;
	}

	/**
	 * set the name of a symbol
	 * 
	 * @param meName
	 *            String
	 * @return void
	 */
	public void setMeName(String meName) {
		this.meName = meName;
	}

	/**
	 * return the type of the symbol itself
	 * 
	 * @return integer as NULL CLASS METHOD VAR
	 */
	public int getMeType() {
		return meType;
	}

	/**
	 * set the type of the symbol
	 * 
	 * @param meType
	 *            integer as NULL CLASS METHOD VAR
	 * @return void
	 */
	public void setMeType(int meType) {
		this.meType = meType;
	}
}
