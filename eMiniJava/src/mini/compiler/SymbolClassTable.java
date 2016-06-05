package mini.compiler;
import java.util.*;
/**
 * symbolic table class
 * @author ddrmsdos
 *
 */
public class SymbolClassTable {
	//hast table for clss symbols
	private Hashtable<String,SymbolClass> classTable=new Hashtable<String,SymbolClass>();
	/**
	 * check if the class exists
	 * @param strClass String
	 * @return boolean
	 */
	public boolean isClassExist(String strClass)
	{
		return classTable.containsKey(strClass);
	}
	/**
	 * add a symbol of class to the table
	 * @param symbol SymbolClass
	 * @return boolean
	 */
	public boolean addClass(SymbolClass symbol)
	{
		if(isClassExist(symbol.getMeName()))
			return false;
		classTable.put(symbol.getMeName(),symbol);
		return true;
	}
	/**
	 * return the symbol by string
	 * @param strClass String
	 * @return SymbolClass
	 */
	public SymbolClass getClassByStr(String strClass)
	{
		return classTable.get(strClass);
	}
	/**
	 * return the array composed by symbols of classes
	 * @return SymbolClass[]
	 */
	public Object[] getClassesToArray()	
	{							
		ArrayList<SymbolClass> arrayList=new ArrayList<SymbolClass>();
		Enumeration<SymbolClass> e=classTable.elements();	
		while(e.hasMoreElements()){
			arrayList.add(e.nextElement());
		}
		return arrayList.toArray();
	}
	
}






