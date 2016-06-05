package mini.compiler;

public class AsmEmit {
	private String endl="\n";
	private StringBuffer asm=new StringBuffer();
	private static int label=0;
	private String strlabel="";
	private void appendln(String str)
	{
		asm.append(str);
		append(endl);
	}
	/**
	 * add string "str" to the instruction buffer
	 * @param str String
	 */
	private void append(String str){
		asm.append(str);
	}
	/**
	 * generate an unique label
	 * @return
	 */
	public String genLabel()
	{
		strlabel="Label@"+label;
		++label;
		return strlabel;
	}
	/**
	 * generate directive at the begining.
	 * @param comment String for comment
	 */
	public void emitOption(String comment)
	{		
		appendln(";"+comment);
		appendln(".386");
		appendln(".model flat,stdcall");
		appendln("option casemap:none");
		appendln("include minijava.inc");
		appendln("includelib minijava.lib");		
	}
	/**
	 * the declaration of only read data segment
	 */
	public void emitConstSegment()
	{
		appendln(".const");
		appendln("@version@ db 'MiniJava 0.0.1 Alpha Powered by DDRMSDOS (minicpp)',0");
	}
	/**
	 * declaration of code segment
	 */
	public void emitCodeSegment()
	{
		appendln(".code");
	}
	/**
	 * end of code segment
	 * @param mainEntry write program entry
	 */
	public void emitCodeEnd(String mainEntry)
	{
		append("end  "+mainEntry);
	}
	/**
	 * call runtime function to end the process
	 */
	public void emitRuntimeExit()
	{
		appendln("call "+"minij_runtime_exit");
	}
	
	public void emitSystemOutPrintln()
	{
		appendln("call "+"minij_runtime_system_out_println");
	}
	public void emitMinijavaPrint()
	{
		appendln("call "+"minij_runtime_minijava_print");
	}
	public void emitMinijavaScanf()
	{
		appendln("call "+"minij_runtime_minijava_scanf");
	}
	public void emitMinijavaWindow()
	{
		appendln("call "+"minij_runtime_minijava_window");
	}
	public void emitMinijavaGetkeystate()
	{
		appendln("call "+"minij_runtime_minijava_getkeystate");
	}
	public void emitMinijavaBitblt()
	{
		appendln("call "+"minij_runtime_minijava_bitblt");
	}
	public void emitMinijavaWait()
	{	
		appendln("call "+"minij_runtime_minijava_wait");
	}
	public void emitMinijavaColor()
	{
		appendln("call "+"minij_runtime_minijava_color");
	}
	
	public void emitMinijavaPresent()
	{
		appendln("call "+"minij_runtime_minijava_present");
	}
	
	public void emitMinijavaSrand()
	{
		appendln("call "+"minij_runtime_minijava_srand");
	}
	
	public void emitMinijavaRand()
	{
		appendln("call "+"minij_runtime_minijava_rand");
	}
	
	/**
	 * 
	 */
	public void emitRuntimAlloc()
	{		
		appendln("call "+"minij_runtime_alloc");
	}
	/**
	 * push to stack at the beginning of a function
	 * @param method
	 */	
	public void emitMethodStart(SymbolMethod method)
	{
		appendln(method.getMethodLabel()+":");
		appendln("push ebp");
		appendln("mov ebp,esp");
		int size=method.getLocalByteSize();
		size*=(-1);
		appendln("add esp,"+size);
	}

	/**
	 * std_call style of poping stack
	 * @param method
	 */
	public void emitMethodRet(SymbolMethod method)
	{
		appendln("leave");
		int size=method.getParamByteSize();
		appendln("retn "+size);
	}
	
	/**
	 * generate virtual function entry table at the read only data segment
	 * @param sc SymbolClass
	 */
	public void emitVirtualTable(SymbolClass sc)
	{
		Object[] array=sc.getVirtualTableToArray();
		if(array.length==0)
		{
			
			appendln(sc.getVirtualTableName()+"  \\"); // '\' is appending symbol
			appendln("dd  0");
		}
		else
		{
			appendln(sc.getVirtualTableName()+"  \\"); // '\' is appending symbol
			for(int i=0;i<array.length;++i)
			{
				SymbolMethod sm=(SymbolMethod)(array[i]);
				appendln("dd  "+sm.getMethodLabel());
			}
		}
	}
	/**
	 * generate a line of assembly langauge
	 * @param asm String
	 */
	public void emit(String asm)
	{
		appendln(asm);
	}
	/**
	 * output generated code
	 */
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return asm.toString();
	}
}
