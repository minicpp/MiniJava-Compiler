package mini.compiler;
import mini.syntaxtree.*;
import mini.visitor.*;
import java.util.*;
public class TypeVisitor extends GJDepthFirst<TypeObject,TypeObject> {
	
	private Summary sum=new Summary();
	private SymbolSearch env;
	private AsmEmit asm=new AsmEmit();
	private String mainEntry;
	private SymbolMethod callMethod=null;
	private SymbolClassTable table;
	
	private void generateSummary(String str,Node n){
		SummaryVisitor sv=new SummaryVisitor(str);
		n.accept(sv);
		sum.YieldSummary(sv.toString());
	}
	
	private boolean convertType(TypeObject source,TypeObject dest){
		if(source.equals(dest)){
			return true;
		}
		if(source.getType()==dest.getType())
		{
			SymbolClass cs,as;
			cs=table.getClassByStr(source.getTypeName());
			as=cs.getAncestorClass(dest.getTypeName());
			if(as!=null)
				return true;
		}
		return false;
	}
	
	/**
	 * generage reference for local variables, put the address of the variable to the eax
	 * @param varName
	 * @param type
	 * @return
	 */
	private boolean emitReference(String varName,TypeObject type,Node n)
	{
		SymbolField sf=env.getVar(varName);
		if(sf==null){
			generateSummary("Can not find variable:", n);
			return false;
		}
		if(!convertType(sf.getTypeObject(),type))
		{
			generateSummary("The type here is error:",n);
			return false;
		}
		int offset=sf.getRelativeOffset();
		if(sf.getBelongInt()==SymbolField.BELONG_CLASS)
		{
			asm.emit("lea eax,[ecx+"+offset+"]");
		}
		else
		{
			String stroffset=""+offset;
			if(offset>0){
				stroffset="+"+offset;
			}
			asm.emit("lea eax,[ebp"+stroffset+"]");
		}		
		return true;
	}
	
	private boolean emitExp(TypeObject dest,Exp exp)
	{
		TypeObject source=exp.accept(this,dest);
		if(!convertType(source, dest))
		{
			generateSummary("The expresion have error in type:", exp);
			return false;
		}
		return true;
	}
	
	/**
	 * This call can generate left and right expression by asm, and test types
	 * Firstly, push ebx
	 * ebx keeps the value of left express, eax keeps the value of right express
	 * after call, recover by pop ebx
	 * @param dest null means any types
	 * @param pr1  left expression
	 * @param pr2 right expression
	 * @return    return true/false
	 */
	private boolean emitPrimaryOpPrimary
	(TypeObject dest,PrimaryExpression pr1,PrimaryExpression pr2)
	{
		TypeObject type=new TypeObject(TypeConstants.NULL);
		TypeObject left=pr1.accept(this,type);
		asm.emit("push ebx");
		asm.emit("mov ebx,eax");
		TypeObject right=pr2.accept(this,type);
		if( !convertType(left, right)  && !convertType(right,left) )
		{
			generateSummary("The left and right of the PrimaryExpression different in type:", pr1);
			return false;
		}
		if(dest==null)
		{
			return true;
		}
		if(!convertType(left, dest) || !convertType(right,dest) )
		{
			generateSummary("We need correct type here:",pr1);
			return false;
		}
		return true;
	}

	public TypeVisitor(SymbolClassTable table){
		env=new SymbolSearch(table);	
		this.table=table;
	}
	public String getAsm(){
		return asm.toString();
	}
	public Summary getSum() {
		return sum;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return sum.toString();
	}

	@Override
	public TypeObject visit(PlusExpression n, TypeObject argu) {
		// TODO Auto-generated method stub
		//return super.visit(n, argu);
		TypeObject type=new TypeObject(TypeConstants.INT);
		//generate left value of add expression, save the value in eax		
		if(!n.primaryExpression.accept(this,type).equals(type))
			generateSummary("Need int type here:", n);
		else
		{
			//push stack to ebx
			//save the left size eax into ebx
			asm.emit("push ebx");
			asm.emit("mov ebx,eax");
		}
		//generate right value in the add expression, add it in eax
		if(!n.primaryExpression1.accept(this,type).equals(type))
			generateSummary("Need int type here:",n);
		else
		{
			//add left ebx and right eax, put it in eax
			asm.emit("add eax,ebx");
			//recover ebx
			asm.emit("pop ebx");
			//The result is saved in eax
		}
		return type;
	}	

	@Override
	public TypeObject visit(MainClass n, TypeObject argu) {
		// TODO Auto-generated method stub
		mainEntry="main@"+n.identifier.nodeToken.tokenImage;
		asm.emit(mainEntry+":");
		//generate codes for main
		super.visit(n, argu);
		//exit the call
		asm.emitRuntimeExit();
		return null;
	}

	@Override
	public TypeObject visit(ClassDecl n, TypeObject argu) {
		// TODO Auto-generated method stub
		Identifier id=null;
		switch(n.nodeChoice.which)
		{
		case 0:
			ClassDeclaration cd=(ClassDeclaration)(n.nodeChoice.choice);
			id=cd.identifier;
			break;
		case 1:
			ClassExtendsDeclaration ce=(ClassExtendsDeclaration)(n.nodeChoice.choice);
			id=ce.identifier;
			break;
		}
		env.EnterClass(id.nodeToken.tokenImage);
		super.visit(n, argu);
		env.ExitClass();
		return null;
	}

	@Override
	public TypeObject visit(MethodDecl n, TypeObject argu) {
		// TODO Auto-generated method stub
		env.EnterMethod(n.identifier.nodeToken.tokenImage);
		asm.emitMethodStart(env.getHmethod());
		//generate codes for internal statements
		n.nodeListOptional1.accept(this,new TypeObject(TypeConstants.NULL));
		//generate return codes
		TypeObject type=n.exp.accept(this,env.getHmethod().getReturnTypeObject());
		if(!convertType(type, env.getHmethod().getReturnTypeObject()))
			generateSummary("The return type is not correct", n.exp);
		asm.emitMethodRet(env.getHmethod());
		env.ExitMethod();
		return null;
	}

	@Override
	public TypeObject visit(Program n, TypeObject argu) {
		// TODO Auto-generated method stub
		env.EnterProgram();
		asm.emitOption("This asm generate from MiniJava Parser.Present by HanDong:)");
		asm.emitConstSegment();
		SymbolClassTable table=env.getHtable();
		Object[] array=table.getClassesToArray();
		for(int i=0;i<array.length;++i)
		{
			//generate virtual tables
			asm.emitVirtualTable((SymbolClass)(array[i]));
		}
		//begin code segment
		asm.emitCodeSegment();
		super.visit(n, argu);
		//end code segment, specify entry for main
		asm.emitCodeEnd(mainEntry);
		env.ExitProgram();
		return null;
	}

	@Override
	public TypeObject visit(AssignmentStatement n, TypeObject argu) {
		// TODO Auto-generated method stub
		SymbolField sf=env.getVar(n.identifier.nodeToken.tokenImage);
		if(sf==null)
		{
			sum.YieldDetail("The variable here isn't definited before using", n.identifier.nodeToken);
			return null;
		}
		TypeObject type=sf.getTypeObject();
		//generate code for expression, save it in eax
		TypeObject typeExp=n.exp.accept(this,type);
		if(!convertType(typeExp, type)){
			sum.YieldDetail("The type of left and right of the Assignment don't match",
					n.identifier.nodeToken);
			return null;
		}
		/*
		 *  Generage assignment asm
		 *  if it is local variable, use ebp to address it
		 *  then assign the value in eax into the memory specified by ebp+x
		 *  mov [ebp+x], eax
		 *  If it is member variable of a class, use ecx as base address, then
		 *  put eax into [ecx+x]
		 *  mov [ecx+x], eax
		 */
		int offset=sf.getRelativeOffset();
		if(sf.getBelongInt()==SymbolField.BELONG_CLASS)
		{
			asm.emit("mov [ecx+"+offset+"],eax");
		}
		else
		{
			String stroffset=""+offset;
			if(offset>0){
				stroffset="+"+offset;
			}
			asm.emit("mov [ebp"+stroffset+"],eax");
		}		
		return (new TypeObject(TypeConstants.NULL));
	}

	@Override
	public TypeObject visit(Exp n, TypeObject argu) {
		// TODO Auto-generated method stub
		return n.nodeChoice.choice.accept(this,argu);
	}

	@Override
	public TypeObject visit(PrimaryExpression n, TypeObject argu) {
		// TODO Auto-generated method stub
		//Identifier
		return (n.nodeChoice.choice.accept(this,argu));
	}


	@Override
	public TypeObject visit(IntegerLiteral n, TypeObject argu) {
		// TODO Auto-generated method stub
		TypeObject type=new TypeObject(TypeConstants.INT);
		//pub the value into eax
		asm.emit("mov eax,"+n.nodeToken.tokenImage);
		return type;
	}

	@Override
	public TypeObject visit(CharLiteral n, TypeObject argu) {
		// TODO Auto-generated method stub
		TypeObject type=new TypeObject(TypeConstants.INT);
		//put the value into eax
		String str=n.nodeToken.tokenImage;
		char s=str.charAt(1);
		int i=s;
		asm.emit("mov eax,"+i);
		return type;
	}

	@Override
	public TypeObject visit(FalseLiteral n, TypeObject argu) {
		// TODO Auto-generated method stub
		TypeObject type=new TypeObject(TypeConstants.BOOLEAN);
		asm.emit("xor eax,eax");
		return type;
	}

	@Override
	public TypeObject visit(TrueLiteral n, TypeObject argu) {
		// TODO Auto-generated method stub
		TypeObject type=new TypeObject(TypeConstants.BOOLEAN);
		asm.emit("mov eax,1");
		return type;
	}

	@Override
	public TypeObject visit(AllocationExpression n, TypeObject argu) {
		// TODO Auto-generated method stub
		TypeObject type=new TypeObject(TypeConstants.CLASS,n.identifier.nodeToken.tokenImage);
		SymbolClass sc=env.getHtable().getClassByStr(type.getTypeName());
		if(sc==null)
		{
			sum.YieldDetail("The class can not find,please define it.",n.identifier.nodeToken);
		}
		else
		{
			//allocate memory
			asm.emit("push ebx");//save ebx
			asm.emit("push "+sc.getClassSize());
			asm.emitRuntimAlloc();
			//initialize memory of virtual table
			//mov ebx,offset table@class name
			//mov [eax],ebx
			asm.emit("mov ebx,offset "+sc.getVirtualTableName());
			asm.emit("mov [eax],ebx");
			asm.emit("pop ebx");
		}
		//eax keeps the new memory address
		return type;
	}

	@Override
	public TypeObject visit(ArrayAllocationExpression n, TypeObject argu) {
		// TODO Auto-generated method stub
		TypeObject type=new TypeObject(TypeConstants.INTARRAY);
		//generate expression, test the type of express, and put the final value into eax
		TypeObject type_2=n.exp.accept(this,new TypeObject(TypeConstants.INT));
		if(!type_2.equals(new TypeObject(TypeConstants.INT)))
		{
			generateSummary("The expression is not int type:", n.exp);
		}
		else
		{
			asm.emit("push ebx");//save ebx
			asm.emit("push edx");//save edx
			asm.emit("push eax");//eax keeps the length
			asm.emit("mov ebx,4");
			asm.emit("inc eax"); //eax+1, the 1 is used to save the length of array
			asm.emit("mul ebx"); //eax*ebx=eax*4=edx:eax
			asm.emit("push eax");
			asm.emitRuntimAlloc();
			asm.emit("pop ebx");			
			asm.emit("mov [eax],ebx");
			asm.emit("add eax,4"); //length is set to eax-4, adjust eax to the appropriate position
			asm.emit("pop edx");//recover
			asm.emit("pop ebx");
		}
		return type;
	}

	@Override
	public TypeObject visit(BracketExpression n, TypeObject argu) {
		// TODO Auto-generated method stub
		return n.exp.accept(this,argu);
	}

	@Override
	public TypeObject visit(NotExpression n, TypeObject argu) {
		// TODO Auto-generated method stub
		TypeObject type_need=new TypeObject(TypeConstants.BOOLEAN);
		TypeObject type=n.exp.accept(this,type_need);
		if(!type.equals(type_need))
		{
			generateSummary("The expression should be boolean type:", n);
		}
		else
		{
			asm.emit("xor eax,1");
		}
		return type_need;
	}

	@Override
	public TypeObject visit(AndExpression n, TypeObject argu) {
		// TODO Auto-generated method stub
		TypeObject type=new TypeObject(TypeConstants.BOOLEAN);
		if(!n.primaryExpression.accept(this,type).equals(type))
		{
			generateSummary("The left expression should be boolean type", n.primaryExpression);
		}
		else
		{
			asm.emit("push ebx");
			asm.emit("mov ebx,eax");
		}
		if(!n.primaryExpression1.accept(this,type).equals(type))
		{
			generateSummary("The right expression should be boolean type", n.primaryExpression);
		}
		else
		{
			asm.emit("and eax,ebx");
			asm.emit("pop ebx");
		}
		return type;
	}

	@Override
	public TypeObject visit(OrExpression n, TypeObject argu) {
		// TODO Auto-generated method stub	
		TypeObject retType=new TypeObject(TypeConstants.BOOLEAN);
		if(!emitPrimaryOpPrimary(retType, n.primaryExpression,n.primaryExpression1))
		{
			return retType;
		}
		asm.emit("or eax,ebx");
		asm.emit("pop ebx");
		return retType;
	}

	@Override
	public TypeObject visit(DivExpression n, TypeObject argu) {
		// TODO Auto-generated method stub
		TypeObject type=new TypeObject(TypeConstants.INT);		
		if(!emitPrimaryOpPrimary(type, n.primaryExpression1,n.primaryExpression))
		{
			return type;
		}
		asm.emit("push edx");
		asm.emit("cdq"); //exend the symbol from eax to edx !!!
		asm.emit("idiv ebx");//put the result in eax, reminder into edx
		asm.emit("pop edx");
		asm.emit("pop ebx");
		return type;
	}
	
	@Override
	public TypeObject visit(ModExpression n, TypeObject argu) {
		// TODO Auto-generated method stub
		TypeObject type=new TypeObject(TypeConstants.INT);		
		if(!emitPrimaryOpPrimary(type, n.primaryExpression1,n.primaryExpression))
		{
			return type;
		}
		asm.emit("push edx");
		asm.emit("cdq"); //extend the +/1 symbol from eax to edx!!!
		asm.emit("idiv ebx");//sve result in eax, reminder in edx
		asm.emit("mov eax,edx");//save reminder to eax
		asm.emit("pop edx");
		asm.emit("pop ebx");
		return type;
	}

	@Override
	public TypeObject visit(CompareExpression n, TypeObject argu) {
		// TODO Auto-generated method stub
		TypeObject type=new TypeObject(TypeConstants.INT);
		//generate the compared left value, save it in eax		
		if(!n.primaryExpression.accept(this,type).equals(type))
			generateSummary("Need int type here:", n);
		else
		{
			//push ebx
			//put the left eax into ebx
			asm.emit("push ebx");
			asm.emit("mov ebx,eax");
		}
		//generate the compared right value, put it into eax
		if(!n.primaryExpression1.accept(this,type).equals(type))
			generateSummary("Need int type here:",n);
		else
		{
			//compare the two side, ebx is left, eax is right
			String label_j=asm.genLabel();
			String label_e=asm.genLabel();
			asm.emit("cmp ebx,eax");
			asm.emit("jge "+label_j);
			asm.emit("mov eax,1");
			asm.emit("jmp "+label_e);
			asm.emit(label_j+":");
			asm.emit("xor eax,eax");
			asm.emit(label_e+":");
			asm.emit("pop ebx");
			//put the final result into eax
		}
		return (new TypeObject(TypeConstants.BOOLEAN));
	}

	@Override
	public TypeObject visit(CompareBigExpression n, TypeObject argu) {
		// TODO Auto-generated method stub		
		TypeObject type=new TypeObject(TypeConstants.INT);		
		TypeObject retType=new TypeObject(TypeConstants.BOOLEAN);
		if(!emitPrimaryOpPrimary(type, n.primaryExpression,n.primaryExpression1))
		{
			return retType;
		}
		String label_j=asm.genLabel();
		String label_e=asm.genLabel();
		asm.emit("cmp ebx,eax");
		asm.emit("jle "+label_j);
		asm.emit("mov eax,1");
		asm.emit("jmp "+label_e);
		asm.emit(label_j+":");
		asm.emit("xor eax,eax");
		asm.emit(label_e+":");		
		asm.emit("pop ebx");
		return retType;
	}

	@Override
	public TypeObject visit(CompareBigEquExpression n, TypeObject argu) {
		// TODO Auto-generated method stub
		TypeObject type=new TypeObject(TypeConstants.INT);	
		TypeObject retType=new TypeObject(TypeConstants.BOOLEAN);
		if(!emitPrimaryOpPrimary(type, n.primaryExpression,n.primaryExpression1))
		{
			return retType;
		}
		String label_j=asm.genLabel();
		String label_e=asm.genLabel();
		asm.emit("cmp ebx,eax");
		asm.emit("jl "+label_j);
		asm.emit("mov eax,1");
		asm.emit("jmp "+label_e);
		asm.emit(label_j+":");
		asm.emit("xor eax,eax");
		asm.emit(label_e+":");		
		asm.emit("pop ebx");
		return retType;
	}

	@Override
	public TypeObject visit(CompareSmallEquExpression n, TypeObject argu) {
		// TODO Auto-generated method stub
		TypeObject type=new TypeObject(TypeConstants.INT);
		TypeObject retType=new TypeObject(TypeConstants.BOOLEAN);
		if(!emitPrimaryOpPrimary(type, n.primaryExpression,n.primaryExpression1))
		{
			return retType;
		}
		String label_j=asm.genLabel();
		String label_e=asm.genLabel();
		asm.emit("cmp ebx,eax");
		asm.emit("jg "+label_j);
		asm.emit("mov eax,1");
		asm.emit("jmp "+label_e);
		asm.emit(label_j+":");
		asm.emit("xor eax,eax");
		asm.emit(label_e+":");		
		asm.emit("pop ebx");
		return retType;
	}

	@Override
	public TypeObject visit(CompareEquExpression n, TypeObject argu) {
		// TODO Auto-generated method stub
		TypeObject retType=new TypeObject(TypeConstants.BOOLEAN);
		if(!emitPrimaryOpPrimary(null, n.primaryExpression,n.primaryExpression1))
		{
			return retType;
		}
		String label_j=asm.genLabel();
		String label_e=asm.genLabel();
		asm.emit("cmp ebx,eax");
		asm.emit("jne "+label_j);
		asm.emit("mov eax,1");
		asm.emit("jmp "+label_e);
		asm.emit(label_j+":");
		asm.emit("xor eax,eax");
		asm.emit(label_e+":");		
		asm.emit("pop ebx");
		return retType;
	}

	@Override
	public TypeObject visit(CompareNotEquExpression n, TypeObject argu) {
		// TODO Auto-generated method stub
		TypeObject retType=new TypeObject(TypeConstants.BOOLEAN);
		if(!emitPrimaryOpPrimary(null, n.primaryExpression,n.primaryExpression1))
		{
			return retType;
		}
		String label_j=asm.genLabel();
		String label_e=asm.genLabel();
		asm.emit("cmp ebx,eax");
		asm.emit("je "+label_j);
		asm.emit("mov eax,1");
		asm.emit("jmp "+label_e);
		asm.emit(label_j+":");
		asm.emit("xor eax,eax");
		asm.emit(label_e+":");		
		asm.emit("pop ebx");
		return retType;
	}

	@Override
	public TypeObject visit(MinusExpression n, TypeObject argu) {
		// TODO Auto-generated method stub
		TypeObject type=new TypeObject(TypeConstants.INT);
		//the result of minus at left side, keep it in eax
		if(!n.primaryExpression.accept(this,type).equals(type))
			generateSummary("Need int type here:", n);
		else
		{
			//push ebx into stack
			//save the value of eax into ebx
			asm.emit("push ebx");
			asm.emit("mov ebx,eax");
		}
		//generate the right side of minus, save it into eax
		if(!n.primaryExpression1.accept(this,type).equals(type))
			generateSummary("Need int type here:",n);
		else
		{
			//sub the left ebx and right eax, the result is kept in eax
			asm.emit("sub ebx,eax");
			asm.emit("mov eax,ebx");
			//recover ebx
			asm.emit("pop ebx");
			//put the final result in eax
		}
		return type;		
	}

	@Override
	public TypeObject visit(TimesExpression n, TypeObject argu) {
		// TODO Auto-generated method stub
		TypeObject type=new TypeObject(TypeConstants.INT);
		//generate multiple left value, save it at eax
		if(!n.primaryExpression.accept(this,type).equals(type))
			generateSummary("Need int type here:", n);
		else
		{
			//push ebx into stack
			//save eax to ebx
			asm.emit("push ebx");
			asm.emit("push edx");
			asm.emit("mov ebx,eax");
		}
		//generate right value of multiply expression, save it in eax
		if(!n.primaryExpression1.accept(this,type).equals(type))
			generateSummary("Need int type here:",n);
		else
		{
			//multiple the left ebx, and right eax, put it into eax
			asm.emit("imul ebx");
			asm.emit("pop edx");	
			//recover ebx
			asm.emit("pop ebx");
			//put the final result in eax
		}
		return type;	
	}

	@Override
	public TypeObject visit(ArrayLength n, TypeObject argu) {
		// TODO Auto-generated method stub
		TypeObject type_need=new TypeObject(TypeConstants.INTARRAY);
		if(!n.primaryExpression.accept(this,type_need).equals(type_need))
		{
			generateSummary("The type should be int array", n.primaryExpression);
		}
		else
		{
			asm.emit("mov eax,[eax-4]");
		}		
		return (new TypeObject(TypeConstants.INT));
	}

	@Override
	public TypeObject visit(ArrayLookup n, TypeObject argu) {
		// TODO Auto-generated method stub
		TypeObject type_need=new TypeObject(TypeConstants.INTARRAY);
		if(!n.primaryExpression.accept(this,type_need).equals(type_need))
		{
			generateSummary("The type should be int array:", n.primaryExpression);
		}
		else
		{
			asm.emit("push ebx");
			asm.emit("mov ebx,eax");
		}	
		TypeObject type_need2=new TypeObject(TypeConstants.INT);
		if(!n.primaryExpression1.accept(this,type_need2).equals(type_need2))
		{
			generateSummary("The type should be int:",n.primaryExpression1);
		}
		else
		{
			//base address ebx is ponter, eax is index, 4 is size of a unit: ebx+eax*4->eax
			asm.emit("mov eax,[ebx][eax*4]");
			asm.emit("pop ebx");
		}		
		return (new TypeObject(TypeConstants.INT));
	}

	@Override
	public TypeObject visit(MessageSend n, TypeObject argu) {
		// TODO Auto-generated method stub
		TypeObject type_need=new TypeObject(TypeConstants.CLASS);
		//after the call, eax is tored the pointer to the memory of the class
		TypeObject type=n.primaryExpression.accept(this,type_need);
		if(type.getType()!=TypeConstants.CLASS){
			generateSummary("To invoke the function,you should have class type variable:", n.primaryExpression);
			return (new TypeObject(TypeConstants.NULL));
		}
		SymbolMethod sm=env.getMethodFromClass(type.getTypeName(),n.identifier.nodeToken.tokenImage);
		if(sm==null){
			generateSummary("Can not find the method:",n.identifier);
			return (new TypeObject(TypeConstants.NULL));
		}
		//asm.emit("push ecx");
		//asm.emit("mov ecx,eax"); //save the opinter into ecx as "this"
		asm.emit("push ebx");
		asm.emit("push ecx");    //save the current this
		asm.emit("mov ebx,eax"); //the new memory pointer is stored in ebx
		if(n.nodeOptional.present())
		{
			callMethod=sm;
			//generate the parameters that to be pushed into stack
			ExpList el=(ExpList)(n.nodeOptional.node);
			el.accept(this,new TypeObject(TypeConstants.NULL));
		}		
		asm.emit("mov ecx,ebx"); //this pointer context exchange
		asm.emit("mov eax,[ecx]"); //eax is first item of virtual table
		asm.emit("add eax,"+sm.getRelativeOffset()); //specified to the particular record
		asm.emit("call dword ptr [eax]"); //begin the call
		asm.emit("pop ecx"); //after the call, recover the this, save the call result in eax
		asm.emit("pop ebx"); //recover ebx
		return sm.getReturnTypeObject();		
	}

	@Override
	public TypeObject visit(ExpList n, TypeObject argu) {
		// TODO Auto-generated method stub
		ArrayList<Exp> expArray=new ArrayList<Exp>();
		ArrayList<TypeObject> typeArray=new ArrayList<TypeObject>();
		//reverse the order of parameters
		if(n.nodeListOptional.present())
		{
			for(int i=n.nodeListOptional.nodes.size()-1;i>-1;--i)
			{
				ExpRest rest=(ExpRest)(n.nodeListOptional.nodes.get(i));
				expArray.add(rest.exp);
			}
		}
		//The first parameter is pushed at last
		expArray.add(n.exp);
		//reverse the order of each parameter
		for(int i=callMethod.getParamLength()-1;i>-1;--i)
		{
			typeArray.add(callMethod.getParamByIndex(i).getTypeObject());
		}
		//check the number of parameters is correct
		if(expArray.size()!=typeArray.size())
		{
			generateSummary("The method do not have correct number of param", n);
		}
		else
		{
			//check if the type of parameter is the same as declared when genreating asm
			for(int i=0;i<expArray.size();++i){
				//generate code of each parameter
				TypeObject type=expArray.get(i).accept(this,typeArray.get(i));
				if(!convertType(type, typeArray.get(i)))
				{
					generateSummary("The param type is not correct",n);
				}
				else
				{
					asm.emit("push eax");
				}
			}			
		}
		return callMethod.getReturnTypeObject();
	}

	@Override
	public TypeObject visit(Identifier n, TypeObject argu) {
		// TODO Auto-generated method stub
		if(argu==null)
			return (new TypeObject(TypeConstants.NULL));
		//search context table
		SymbolField sf=env.getVar(n.nodeToken.tokenImage);
		if(sf==null)
		{
			sum.YieldDetail("The variable here isn't definited before using", n.nodeToken);
			return (new TypeObject(TypeConstants.NULL));
		}
		/*
		 * put value into eax
		 * generate asm codes
		 * if it is local variable, use ebp for addressing
		 * put [ebp+x] into eax
		 * mov eax,[ebp+x] 
		 * if it is class local variable, use ecx to search, then put [ecx+x] into eax
		 * mov eax,[ecx+x]
		 */
		int offset=sf.getRelativeOffset();
		if(sf.getBelongInt()==SymbolField.BELONG_CLASS)
		{
			asm.emit("mov eax,[ecx+"+offset+"]");
		}
		else
		{
			String stroffset=""+offset;
			if(offset>0){
				stroffset="+"+offset;
			}
			asm.emit("mov eax,[ebp"+stroffset+"]");
		}		
		return sf.getTypeObject();
	}

	@Override
	public TypeObject visit(ThisExpression n, TypeObject argu) {
		// TODO Auto-generated method stub
		//put this into eax
		asm.emit("mov eax,ecx");
		return (new TypeObject(TypeConstants.CLASS,env.getHclass().getMeName()));
	}

	@Override
	public TypeObject visit(ArrayAssignmentStatement n, TypeObject argu) {
		// TODO Auto-generated method stub
		//calcuate the left address, and save it into eax
		TypeObject type_need=new TypeObject(TypeConstants.INTARRAY);
		if(!n.identifier.accept(this,type_need).equals(type_need))
		{
			sum.YieldDetail("Should be int array type here:", n.identifier.nodeToken);
			return type_need;
		}
		else
		{
			//the value of eax is base address
			//put base address into ebx
			asm.emit("push ebx");
			asm.emit("mov ebx,eax");
		}
		type_need.setType(TypeConstants.INT);
		if(!n.exp.accept(this,type_need).equals(type_need)){
			generateSummary("Should be int type here as index",n.exp);
			return type_need;
		}
		else
		{
			//eax keeps the index address, multiply factor 4 + base address to get address of array
			asm.emit("lea ebx,[ebx][eax*4]");
		}
		type_need.setType(TypeConstants.INT);
		if(!n.exp1.accept(this,type_need).equals(type_need)){
			generateSummary("Should be int type at right:", n.exp1);
			return type_need;
		}
		else{
			asm.emit("mov [ebx],eax");
			asm.emit("pop ebx");
		}
		return type_need;
	}

	@Override
	public TypeObject visit(IfStatement n, TypeObject argu) {
		// TODO Auto-generated method stub		
		TypeObject type=new TypeObject(TypeConstants.BOOLEAN);
		TypeObject nullType=new TypeObject(TypeConstants.NULL);
		if(!emitExp(type, n.exp)){
			return type;
		}
		String label_f=asm.genLabel();
		asm.emit("test eax,eax");
		asm.emit("jz "+label_f);
		n.statement.accept(this,nullType);
		//If there is else, label_f jumps to else, o/w label_e is exit of if
		if(n.nodeOptional.present()){
			String label_e=asm.genLabel();
			asm.emit("jmp "+label_e);
			asm.emit(label_f+":");
			ElseStatement es=(ElseStatement)(n.nodeOptional.node);
			es.accept(this,nullType);
			asm.emit(label_e+":");
		}
		else
			asm.emit(label_f+":");
		
		return type;
	}

	@Override
	public TypeObject visit(WhileStatement n, TypeObject argu) {
		// TODO Auto-generated method stub
		String label_f=asm.genLabel();
		String label_r=asm.genLabel();
		TypeObject type=new TypeObject(TypeConstants.BOOLEAN);
		
		asm.emit(label_r+":");
		//generage boolean expression
		if(!n.exp.accept(this,type).equals(type))
		{
			generateSummary("Should be boolean type in while statement:",n.exp);
			return type;
		}
		else
		{			
			type.setType(TypeConstants.NULL);
			asm.emit("test eax,eax");
			asm.emit("jz "+label_f);
			n.statement.accept(this,type);
			asm.emit("jmp "+label_r);
			asm.emit(label_f+":");
		}
		return type;
	}

	@Override
	public TypeObject visit(DrawClass n, TypeObject argu) {
		// TODO Auto-generated method stub
		env.EnterClass(n.identifier.nodeToken.tokenImage);
		super.visit(n, argu);
		env.ExitClass();
		return null;
	}

	@Override
	public TypeObject visit(MessageHandler n, TypeObject argu) {
		// TODO Auto-generated method stub
		env.EnterMethod(n.nodeToken3.tokenImage);
		asm.emit(env.getHmethod().getBelongClass().getFatherClass().
				getMethod(n.nodeToken3.tokenImage).getMethodLabel()+":");
		asm.emitMethodStart(env.getHmethod());
		n.nodeListOptional1.accept(this,new TypeObject(TypeConstants.NULL));
		//generate asm code, return result into eax
		TypeObject type=n.exp.accept(this,env.getHmethod().getReturnTypeObject());
		if(!convertType(type, env.getHmethod().getReturnTypeObject()))
			generateSummary("The return type is not correct", n.exp);
		asm.emitMethodRet(env.getHmethod());
		env.ExitMethod();
		return null;
	}

	@Override
	public TypeObject visit(Print n, TypeObject argu) {
		// TODO Auto-generated method stub
		TypeObject typeint=new TypeObject(TypeConstants.INT);
		TypeObject typeret=new TypeObject(TypeConstants.NULL);
		if(!emitExp(typeint,n.exp))
		{
			return typeret;
		}
		asm.emit("push eax");
		asm.emitMinijavaPrint();
		return typeret;
	}

	@Override
	public TypeObject visit(Println n, TypeObject argu) {
		// TODO Auto-generated method stub
		TypeObject type=new TypeObject(TypeConstants.NULL);
		type=n.exp.accept(this,type);
		switch(type.getType())
		{
		case TypeConstants.CLASS:
		case TypeConstants.INTARRAY:
			generateSummary("Can not print class or array type:", n.exp);
			return type;
		case TypeConstants.BOOLEAN:
			String label_z=asm.genLabel();
			String label_e=asm.genLabel();
			asm.emit("test eax,eax");
			asm.emit("jz "+label_z);
			asm.emit("push 116"); //t
			asm.emitMinijavaPrint(); 
			asm.emit("push 114"); //r
			asm.emitMinijavaPrint();
			asm.emit("push 117"); //u
			asm.emitMinijavaPrint();
			asm.emit("push 101"); //e
			asm.emitMinijavaPrint();
			asm.emit("push 10"); //\n
			asm.emitMinijavaPrint();
			asm.emit("jmp "+label_e);
			//f 102	 a 97 l 108 s115 e101
			asm.emit(label_z+":");
			asm.emit("push 102");
			asm.emitMinijavaPrint();
			asm.emit("push 97");
			asm.emitMinijavaPrint();
			asm.emit("push 108");
			asm.emitMinijavaPrint();
			asm.emit("push 115");
			asm.emitMinijavaPrint();
			asm.emit("push 101");
			asm.emitMinijavaPrint();
			asm.emit("push 10"); //\n
			asm.emitMinijavaPrint();
			asm.emit(label_e+":");
			return type;
		case TypeConstants.INT:
			asm.emit("push eax");
			asm.emitSystemOutPrintln();
			return type;
			
		}
		return type;
	}

	@Override
	public TypeObject visit(Read n, TypeObject argu) {
		// TODO Auto-generated method stub
		TypeObject type=new TypeObject(TypeConstants.INT);
		TypeObject retType=new TypeObject(TypeConstants.NULL);
		if(!emitReference(n.identifier.nodeToken.tokenImage, type, n.identifier))
			return retType;
		asm.emit("push eax");
		asm.emitMinijavaScanf();
		return type;
	}
	
	@Override
	public TypeObject visit(Bitblt n, TypeObject argu) {
		// TODO Auto-generated method stub
		TypeObject typeint=new TypeObject(TypeConstants.INT);
		TypeObject typearray=new TypeObject(TypeConstants.INTARRAY);
		TypeObject typeret=new TypeObject(TypeConstants.NULL);
		if(!emitExp(typeint,n.exp4))
			return typeret;
		asm.emit("push eax");
		if(!emitExp(typeint,n.exp3))
			return typeret;
		asm.emit("push eax");
		if(!emitExp(typeint,n.exp2))
			return typeret;
		asm.emit("push eax");
		if(!emitExp(typeint,n.exp1))
			return typeret;
		asm.emit("push eax");
		if(!emitExp(typearray,n.exp))
			return typeret;
		asm.emit("push eax");
		asm.emitMinijavaBitblt();
		return typeret;
	}

	@Override
	public TypeObject visit(Color n, TypeObject argu) {
		// TODO Auto-generated method stub
		TypeObject type=new TypeObject(TypeConstants.INT);
		TypeObject retType=new TypeObject(TypeConstants.NULL);
		if(!emitReference(n.identifier.nodeToken.tokenImage,type,n.identifier))
			return retType;
		asm.emit("push eax");
		if(!emitExp(type,n.exp3))
			return retType;
		asm.emit("push eax");
		if(!emitExp(type,n.exp2))
			return retType;
		asm.emit("push eax");
		if(!emitExp(type,n.exp1))
			return retType;
		asm.emit("push eax");
		if(!emitExp(type,n.exp))
			return retType;
		asm.emit("push eax");
		asm.emitMinijavaColor();
		return retType;
	}

/*	@Override
	public TypeObject visit(Delta n, TypeObject argu) {
		// TODO Auto-generated method stub
		TypeObject type=new TypeObject(TypeConstants.INT);
		TypeObject retType=new TypeObject(TypeConstants.NULL);
		if(!emitReference(n.identifier.nodeToken.tokenImage, type, n.identifier))
			return retType;
		asm.emit("push eax");
		asm.emitMinijavaDelta();
		return type;
	}*/

	@Override
	public TypeObject visit(Wait n, TypeObject argu) {
		// TODO Auto-generated method stub
		TypeObject type=new TypeObject(TypeConstants.INT);
		TypeObject retType=new TypeObject(TypeConstants.NULL);
		if(!emitExp(type,n.exp))
			return retType;
		asm.emit("push eax");
		asm.emitMinijavaWait();
		return retType;
	}

	@Override
	public TypeObject visit(Window n, TypeObject argu) {
		// TODO Auto-generated method stub		
		TypeObject typeclass=new TypeObject(TypeConstants.CLASS,
				"MiniDraw");	
		TypeObject typearray=new TypeObject(TypeConstants.INTARRAY);
		TypeObject typeint=new TypeObject(TypeConstants.INT);
		TypeObject typeret=new TypeObject(TypeConstants.NULL);
		if(table.getClassByStr("MiniDraw")==null)
		{
			generateSummary("No class extends from MiniDraw:", n);
		}
		if(!emitExp(typeclass,n.exp3))
			return typeret;
		asm.emit("push eax"); //MiniDraw
		
		if(!emitExp(typearray,n.exp2))
			return typeret;
		asm.emit("push eax"); //int[] title
		if(!emitExp(typeint,n.exp1))
			return typeret;
		asm.emit("push eax"); //height
		if(!emitExp(typeint,n.exp))
			return typeret;
		asm.emit("push eax"); //width
		asm.emitMinijavaWindow();				
		
		return typeret;
	}

	@Override
	public TypeObject visit(GetKeyState n, TypeObject argu) {
		// TODO Auto-generated method stub	
		TypeObject typearray=new TypeObject(TypeConstants.INT);
		TypeObject typeret=new TypeObject(TypeConstants.NULL);
		if(!emitReference(n.identifier.nodeToken.tokenImage, typearray, n.identifier))
			return typeret;
		asm.emit("push eax");
		if(!emitExp(typearray, n.exp))
			return typeret;
		asm.emit("push eax");
		asm.emitMinijavaGetkeystate();
		return typeret;
	}

	@Override
	public TypeObject visit(Present n, TypeObject argu) {
		// TODO Auto-generated method stub
		TypeObject typearray=new TypeObject(TypeConstants.INT);
		TypeObject typeret=new TypeObject(TypeConstants.NULL);
		if(!emitExp(typearray, n.exp1))
			return typeret;
		asm.emit("push eax");
		if(!emitExp(typearray, n.exp))
			return typeret;
		asm.emit("push eax");
		asm.emitMinijavaPresent();
		return typeret;
	}

	@Override
	public TypeObject visit(Rand n, TypeObject argu) {
		// TODO Auto-generated method stub
		TypeObject typearray=new TypeObject(TypeConstants.INT);
		TypeObject typeret=new TypeObject(TypeConstants.NULL);
		if(!emitReference(n.identifier.nodeToken.tokenImage, typearray, n.identifier))
			return typeret;
		asm.emit("push eax");		
		asm.emitMinijavaRand();
		return typeret;
		
	}

	@Override
	public TypeObject visit(Srand n, TypeObject argu) {
		// TODO Auto-generated method stub
		TypeObject typearray=new TypeObject(TypeConstants.INT);
		TypeObject typeret=new TypeObject(TypeConstants.NULL);
		if(!emitExp(typearray, n.exp))
			return typeret;
		asm.emit("push eax");
		asm.emitMinijavaSrand();
		return typeret;
		
	}



}

