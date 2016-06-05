package mini.compiler;
import java.util.*;
import java.util.Map.Entry;

import mini.syntaxtree.ClassDeclaration;
import mini.syntaxtree.ClassExtendsDeclaration;
import mini.syntaxtree.DrawClass;
import mini.syntaxtree.MainClass;
import mini.syntaxtree.MethodDecl;
import mini.syntaxtree.Program;
import mini.visitor.*;

public class InheritVisitor extends GJVoidDepthFirst<SymbolClassTable>{

	private Summary sum = new Summary();
	private SymbolSearch env;
	private Hashtable<String,Integer> graph=new Hashtable<String,Integer>();
	private Hashtable<String,SymbolClass> root=new Hashtable<String,SymbolClass>();
	
	/**
	 *  check if the member in a class is valid
	 */
	private boolean detectFieldValid(SymbolClass sc)
	{
		Object[] array=sc.getFieldsToArray();
		for(int i=0;i<array.length;++i)
		{			
			if(!isFieldValid((SymbolField)(array[i]))){
				sum.YieldSummary("class "+sc.getMeName()+" have invalid field. ");
				return false;
			}
		}
		return true;
	}
	/**
	 * check the type of class is exist.
	 * @param sf
	 * @return
	 */
	private boolean isFieldValid(SymbolField sf){
		SymbolClassTable st;
		st=env.getHtable();
		TypeObject type=sf.getTypeObject();
		if(type.getType()==TypeConstants.CLASS)
		{
			if(!st.isClassExist(type.getTypeName()))
			{
				sum.YieldSummary("variable: "+sf.getMeName()+" is not a valid type!");
				return false;
			}
		}
		return true;
	}
	
	/**
	 * check Inheritance, construct virtual table
	 * @param sc
	 * @return
	 */
	private boolean isCircleExist(SymbolClass sc){
		Integer count=graph.get(sc.getMeName());
		int icount=count.intValue();
		if(icount>0)
		{
			sum.YieldSummary("class "+sc.getMeName()+" exist circle extends.");
			return true;
		}
		else
		{
			++icount;
			graph.put(sc.getMeName(),new Integer(icount));
			//construct virtual table
			constructVirtualTable(sc);
		}
		Object[] array=sc.getChildClassesToArray();
		for(int i=0;i<array.length;++i){
			if(isCircleExist((SymbolClass)(array[i]))){
				return true;
			}
		}
		return false;
	}
	/**
	 * put the array generated from methods into the virtual table
	 * @param methodArray
	 * @param sc
	 * @return
	 */
	private boolean addToVirtualTable(Object[] methodArray,SymbolClass sc)
	{
		for(int i=0;i<methodArray.length;++i)
		{
			if(!sc.addMethodToVirtualTable((SymbolMethod)(methodArray[i]))){
				sum.YieldSummary("Overload method:"+(((SymbolMethod)(methodArray[i])).getMeName()) 
						+"in class:"+sc.getMeName()+" is not permit.");
				return false;
			}
		}
		return true;
	}
	/**
	 * construct virtual table
	 * @param sc
	 */
	private void constructVirtualTable(SymbolClass sc){
		SymbolClass fc=sc.getFatherClass();		
		//put the parent's method into the table
		if(fc!=null){
			Object[] farray=sc.getFatherClass().getVirtualTableToArray();
			addToVirtualTable(farray,sc);
		}
		//put the child's method into the table
		Object[] array=sc.getMethodsToArray();
		addToVirtualTable(array,sc);
	}
	@Override
	public void visit(Program n, SymbolClassTable argu) {
		// TODO Auto-generated method stub
		env=new SymbolSearch(argu);
		env.EnterProgram();
		super.visit(n, argu);
		//check loop inheritance, check the construction of virtual table
		if(env.getHtable().getClassesToArray().length != 0)
		{
			if(root.size()==0)
			{
				sum.YieldSummary("All class exist circle extends.");
			}
			else
			{
				Enumeration<SymbolClass> e=root.elements();	
				while(e.hasMoreElements()){
					SymbolClass sc=e.nextElement();					
					if(isCircleExist(sc))
					{
						sum.YieldSummary("class "+sc.getMeName()+" exist circle extends");
						env.ExitProgram();
						return;
					}
				}
				Set<Entry<String, Integer>> set=graph.entrySet();
				Iterator<Entry<String, Integer>> iter=set.iterator();
				while(iter.hasNext()){
					Entry<String,Integer> entry=iter.next();
					String name=entry.getKey();
					int    count=entry.getValue().intValue();
					if(count==0)
					{
						sum.YieldSummary("class: "+name+" has extends circle");
						env.ExitProgram();
						return;
					}
				}				
			}
		}
		//loop checking finished
		
		//calculate the offset address of member variables
		Object[] array=argu.getClassesToArray();
		for(int i=0; i<array.length;++i)
		{
			SymbolClass sc=(SymbolClass)(array[i]);
			sc.setFiledsOffset(sc.getClassSize()-sc.getFieldsByteSize());
		}
		//~end of the calculation
		
		env.ExitProgram();
	}
	
	@Override
	public void visit(MainClass n, SymbolClassTable argu) {
		// TODO Auto-generated method stub
		//the class of the main should be unique
		if(argu.isClassExist(n.identifier.nodeToken.tokenImage))
		{
			sum.YieldDetail("Redefinition class:", n.identifier.nodeToken);
		}
		super.visit(n, argu);
	}
	@Override
	public void visit(DrawClass n, SymbolClassTable argu) {
		// TODO Auto-generated method stub
		SymbolClass sc=argu.getClassByStr(n.identifier.nodeToken.tokenImage);
		SymbolClass fc=argu.getClassByStr(n.nodeToken2.tokenImage);
		root.put(n.nodeToken2.tokenImage, fc);
		graph.put(fc.getMeName(), new Integer(0));
		graph.put(sc.getMeName(), new Integer(0));
		sc.setFatherClass(fc);
		env.EnterClass(sc.getMeName());
		detectFieldValid(env.getHclass());
		super.visit(n, argu);
		env.ExitClass();
	}
	@Override
	public void visit(ClassDeclaration n, SymbolClassTable argu) {
		// TODO Auto-generated method stub
		root.put(n.identifier.nodeToken.tokenImage,
				argu.getClassByStr(n.identifier.nodeToken.tokenImage));
		graph.put(n.identifier.nodeToken.tokenImage,
				new Integer(0));
		env.EnterClass(n.identifier.nodeToken.tokenImage);
		detectFieldValid(env.getHclass());
		super.visit(n, argu);
		env.ExitClass();
	}

	@Override
	public void visit(ClassExtendsDeclaration n, SymbolClassTable argu) {
		// TODO Auto-generated method stub
		graph.put(n.identifier.nodeToken.tokenImage,
				new Integer(0));
		SymbolClass sc=argu.getClassByStr(n.identifier.nodeToken.tokenImage);
		SymbolClass fc=argu.getClassByStr(n.identifier1.nodeToken.tokenImage);
		if(fc==null)
		{
			root.put(n.identifier.nodeToken.tokenImage, 
					argu.getClassByStr(n.identifier.nodeToken.tokenImage));
			sum.YieldDetail("Extends class is not exist:",n.identifier1.nodeToken);
		}
		else
			sc.setFatherClass(fc);
		
		env.EnterClass(sc.getMeName());
		detectFieldValid(env.getHclass());
		super.visit(n, argu);
		env.ExitClass();
	}

	@Override
	public void visit(MethodDecl n, SymbolClassTable argu) {
		// TODO Auto-generated method stub
		env.EnterMethod(n.identifier.nodeToken.tokenImage);
		SymbolMethod sm=env.getHmethod();
		Object[] array=sm.getVarToArray();
		for(int i=0;i<array.length;++i){
			SymbolField sf=(SymbolField)(array[i]);
			if(!isFieldValid(sf))
			{
				sum.YieldSummary("Method: "+sm.getMeName()+"in class:"+sm.getBelongClass().getMeName()
						+" have invalid variable.");
			}
		}
		//check the return type is valid
		TypeObject type=sm.getReturnTypeObject();
		if(type.getType()==TypeConstants.CLASS){
			if(env.getHtable().getClassByStr(type.getTypeName())==null){
				sum.YieldSummary("Method: "+sm.getMeName()+"in class:"+sm.getBelongClass().getMeName()
						+" have invalid return type.");
			}
		}
		super.visit(n, argu);
		env.ExitMethod();
	}

	
	public Summary getSum() {
		return sum;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return sum.toString();
	}	
}
