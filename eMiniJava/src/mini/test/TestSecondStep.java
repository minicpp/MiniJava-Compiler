package mini.test;
import mini.compiler.*;
public class TestSecondStep {
	private SymbolClassTable table;

	public TestSecondStep(SymbolClassTable table) {
		this.table = table;
	}

	public void PrintTable() {
		Object[] array = table.getClassesToArray();
		for (int i = 0; i < array.length; ++i) {
			PrintClass("", (SymbolClass) array[i]);
		}
	}

	public void PrintClass(String leftSpace, SymbolClass symbolClass) {
		System.out.println(leftSpace
				+ "---------------------------------------------");
		System.out.print(leftSpace + "class Name:" + symbolClass.getMeName());
		System.out.print("Super class:"+symbolClass.isFatherExist());
		System.out.print((symbolClass.isFatherExist()?
				symbolClass.getFatherClass().getMeName():""));
		System.out.println(" Class Size:"+symbolClass.getClassSize());
		System.out.print(leftSpace + "Current sub classes:");
		Object[] childArray=symbolClass.getChildClassesToArray();
		System.out.print(leftSpace);
		for(int i=0;i<childArray.length;++i){
			SymbolClass child=(SymbolClass)(childArray[i]);
			System.out.print(child.getMeName()+" ");
		}
		System.out.println();
		Object[] virtualArray=symbolClass.getVirtualTableToArray();
		System.out.println(leftSpace+"Virtual Table Name:"+symbolClass.getVirtualTableName());
		for(int i=0;i<virtualArray.length;++i){
			SymbolMethod method=(SymbolMethod)(virtualArray[i]);
			System.out.println(leftSpace+"\t"+method.getMethodLabel()+"  offset:"
					+method.getRelativeOffset());
		}
		System.out.println(leftSpace+"Size of member variables:"+symbolClass.getFieldsToArray().length);
		Object[] fieldArray=symbolClass.getFieldsToArray();
		for(int i=0;i<fieldArray.length;++i){
			SymbolField field=(SymbolField)(fieldArray[i]);
			System.out.println(leftSpace+"\t"+"Variable Name:"+field.getMeName()+"Relative Offset:"+field.getRelativeOffset());
		}
		System.out.println(leftSpace+"Size of member functions:"+symbolClass.getMethodsToArray().length);
		Object[] methodArray=symbolClass.getMethodsToArray();
		for(int i=0;i<methodArray.length;++i){
			SymbolMethod method=(SymbolMethod)(methodArray[i]);
			System.out.println("_______________________________________________________");
			PrintMethod(leftSpace+"\t",method);
		}
		
	}
	public void PrintMethod(String leftSpace,SymbolMethod method){
		Object[] paramArray=method.getParamToArray();
		Object[] localArray=method.getLocalToArray();
		SymbolField sf;
		System.out.println(leftSpace+"Member functions");
		System.out.println(leftSpace+"Parameter");
		for(int i=0;i<paramArray.length;++i){
			sf=(SymbolField)(paramArray[i]);
			System.out.println(leftSpace+"\t"+sf.getMeName()+"Offset:"+sf.getRelativeOffset());
		}
		System.out.println(leftSpace+"Local Variables");
		for(int i=0;i<localArray.length;++i){
			sf=(SymbolField)(localArray[i]);
			System.out.println(leftSpace+"\t"+sf.getMeName()+"Offset:"+sf.getRelativeOffset());
		}
		
	}
}
