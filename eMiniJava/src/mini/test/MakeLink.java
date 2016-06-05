package mini.test;
import java.io.*;
public class MakeLink {
	//file and jar should be in the same folder
	public MakeLink(String path,String filename)
	{
		FileWriter writer;
		try {
			writer=new FileWriter(path+"\\"+"MakeFile");
			writer.write(MakeString(filename));
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		ProcessBuilder proc=new ProcessBuilder(path+"\\"+"nmake.exe");
		try {
			proc.start();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	private String MakeString(String filename)
	{
/*
 * EXE = Test.exe		#define the output file
OBJS = Test.obj		#required object file
RES = Test.res		#required resource file

LINK_FLAG = /subsystem:console	#link options
ML_FLAG = /c /coff		#compile options

$(EXE): $(OBJS) $(RES)
	Link $(LINK_FLAG) $(OBJS) $(RES)

.asm.obj:
	ml $(ML_FLAG) $<
.rc.res:
	rc $<

clean:
	del *.obj
	del *.res
 */
		String str;
		str="EXE = "+filename+".exe\r\n";
		str+="OBJS = "+filename+".obj\r\n";
		str+="RES = "+"test"+".res\r\n";
		str+="LINK_FLAG = /subsystem:console\r\n"
			+"ML_FLAG = /c /coff\r\n"
			+"$(EXE): $(OBJS) $(RES)\r\n"+
			"    Link $(LINK_FLAG) $(OBJS) $(RES)\r\n"
			+".asm.obj:\r\n"
			+"   ml $(ML_FLAG) $<\r\n"
			+".rc.res:\r\n"
			+"    rc $<\r\n";
		return str;
	}
}
