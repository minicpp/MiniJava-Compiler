package mini.compiler;

public class SymbolSearch {
	private int CurrentLevel;
	private SymbolClassTable htable = null;
	private SymbolClass hclass = null;
	private SymbolMethod hmethod = null;

	public static final int OUTSIDE = 0;
	public static final int PROGRAM = 1;
	public static final int CLASS = 2;
	public static final int METHOD = 3;

	/**
	 * 
	 * @param htable
	 */
	public SymbolSearch(SymbolClassTable htable) {
		this.htable = htable;
	}
	
	/**
	 * search variable by name in the current environment
	 * @param var String , name of variable
	 * @return SymbolField
	 */
	public SymbolField getVar(String var){
		if(GetLevel()!=METHOD)
			return null;
		//find variable in current function
		SymbolField sf=hmethod.getVar(var);
		if(sf!=null)
			return sf;
		//find in class, and parent class
		for(SymbolClass sc=hclass;sc!=null;sc=sc.getFatherClass())
		{			
			sf=sc.getField(var);
			if(sf!=null)
				return sf;
		}
		return null;
	}
	
	public SymbolMethod getMethodFromClass(String strClass,String strMethod)
	{
		SymbolClass sc=this.htable.getClassByStr(strClass);
		if(sc==null){
			return null;
		}
		return sc.getMethodInVirtualTable(strMethod);
	//	return sc.getMethod(strMethod);
	}

	/**
	 * get the layer of current environment
	 * 
	 * @return
	 */
	public int GetLevel() {
		return CurrentLevel;
	}

	/**
	 * get symbolic table of current environment
	 * 
	 * @return SymbolClassTable 如果没有进入程序环境则返回null
	 */
	public SymbolClassTable getHtable() {
		if (CurrentLevel == OUTSIDE)
			return null;
		return htable;
	}

	/**
	 * get class symbol in the current environment
	 * 
	 * @return SymbolClass return null if the current environment is no in the scale of class
	 */
	public SymbolClass getHclass() {
		if (CurrentLevel == CLASS || CurrentLevel == METHOD)
			return hclass;
		return null;
	}

	/**
	 * get current object of function
	 * 
	 * @return SymbolMethod, return null if not in the function
	 */
	public SymbolMethod getHmethod() {
		if(CurrentLevel==METHOD)
			return hmethod;
		return null;
	}

	/**
	 * enter program environment
	 * 
	 * @param table
	 *            SymbolClassTable
	 * @return boolean
	 */
	public boolean EnterProgram() {		
		CurrentLevel = PROGRAM;
		return true;
	}

	/**
	 * exit program environment
	 * 
	 * @return boolean
	 */
	public boolean ExitProgram() {
		if (CurrentLevel != PROGRAM)
			return false;
		hmethod = null;
		CurrentLevel = OUTSIDE;
		return true;
	}

	/**
	 * enter class environment
	 * 
	 * @param strClass
	 *            String
	 * @return boolean
	 */
	public boolean EnterClass(String strClass) {
		if (CurrentLevel != PROGRAM)
			return false;
		hclass = htable.getClassByStr(strClass);
		if (hclass == null)
			return false;
		CurrentLevel = CLASS;
		return true;
	}

	/**
	 * exit class environment
	 * 
	 * @return boolean
	 */
	public boolean ExitClass() {
		if (CurrentLevel != CLASS)
			return false;
		hclass = null;
		CurrentLevel = PROGRAM;
		return true;
	}

	/**
	 * enter member function environment
	 * 
	 * @param method
	 *            String
	 * @return boolean
	 */
	public boolean EnterMethod(String method) {
		if (CurrentLevel != CLASS)
			return false;
		hmethod = hclass.getMethod(method);
		if (hmethod == null)
			return false;
		CurrentLevel = METHOD;
		return true;
	}

	/**
	 * exit member function environment
	 * 
	 * @return boolean
	 */
	public boolean ExitMethod() {
		if (CurrentLevel != METHOD)
			return false;
		hmethod = null;
		CurrentLevel = CLASS;
		return true;
	}
}
