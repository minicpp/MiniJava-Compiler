package mini.compiler;

import mini.syntaxtree.*;
import mini.visitor.*;

/**
 * 
 * Add all symbols in the table for the first visit
 * 
 * @author ddrmsdos
 */
public class SymbolVisitor extends GJVoidDepthFirst<Object> {

	private Summary sum = new Summary();

	/**
	 * Based on the express of type, generate type symbolics
	 * 
	 * @param typeProduction
	 * @return
	 */
	private TypeObject GenerateCorrectType(Type typeProduction) {
		TypeObject typeObject = new TypeObject();
		int type = TypeConstants
				.MapChoiceToType(typeProduction.nodeChoice.which);
		typeObject.setType(type);
		if (type == TypeConstants.CLASS) {
			Identifier token = (Identifier) typeProduction.nodeChoice.choice;
			typeObject.setTypeName(token.nodeToken.tokenImage);
		}
		return typeObject;
	}

	/**
	 * add class to symbolic table, return the symbol of the class
	 * @param identifier Identifier
	 * @param nodeListOptional NodeListOptional
	 * @param table SymbolClassTable
	 * @return SymbolClass
	 */
	private SymbolClass addClassToTable(Identifier identifier,
			NodeListOptional nodeListOptional, SymbolClassTable table) {
		SymbolClass declClass = new SymbolClass(
				identifier.nodeToken.tokenImage, table);
		if (!table.addClass(declClass)) {
			sum.YieldDetail("Redefinition class:", identifier.nodeToken);
		}
		for (int i = 0; i < nodeListOptional.nodes.size(); ++i) {
			VarDecl declVar = (VarDecl) nodeListOptional.nodes.get(i);
			SymbolField field = new SymbolField(
					GenerateCorrectType(declVar.type),
					declVar.identifier.nodeToken.tokenImage, declClass,
					SymbolField.BELONG_CLASS);
			if (!declClass.addField(field)) {
				sum
						.YieldDetail("Redefinition var in class:",
								declVar.nodeToken);
			}
		}
		return declClass;

	}

	@Override
	public void visit(FormalList n, Object argu) {
		// TODO Auto-generated method stub
		// add parameters of a member function into symbolic table

		SymbolMethod method = (SymbolMethod) argu;

		// establish parameter symbols and add member function symbol
		SymbolField field = new SymbolField(
				GenerateCorrectType(n.formalParameter.type),
				n.formalParameter.identifier.nodeToken.tokenImage, method,
				SymbolField.BELONG_PARAM);
		if (!method.pushBackParam(field)) {
			sum.YieldDetail("Redefinition parameter in method:",
					n.formalParameter.identifier.nodeToken);
		}

		for (int i = 0; i < n.nodeListOptional.size(); ++i) {
			FormalRest rest = (FormalRest) n.nodeListOptional.nodes.get(i);
			FormalParameter param = (FormalParameter) rest.formalParameter;
			field = new SymbolField(GenerateCorrectType(param.type),
					param.identifier.nodeToken.tokenImage, method,
					SymbolField.BELONG_PARAM);
			if (!method.pushBackParam(field)) {
				sum.YieldDetail("Redefinition parameter in method:",
						param.identifier.nodeToken);
			}
		}
		super.visit(n, argu);
	}

	@Override
	public void visit(MethodDecl n, Object argu) {
		// TODO Auto-generated method stub
		// add member function to symbolic table
		SymbolClass declClass = (SymbolClass) argu;
		SymbolMethod method = new SymbolMethod(GenerateCorrectType(n.type),
				n.identifier.nodeToken.tokenImage, declClass);

		if (!declClass.addMethod(method))
			sum
					.YieldDetail(
							"Redefinition method in class,currently do not support overload:",
							n.identifier.nodeToken);
		// add local variables of function into symbolic table
		for (int i = 0; i < n.nodeListOptional.nodes.size(); ++i) {
			VarDecl declVar = (VarDecl) n.nodeListOptional.nodes.get(i);
			SymbolField field = new SymbolField(
					GenerateCorrectType(declVar.type),
					declVar.identifier.nodeToken.tokenImage, method,
					SymbolField.BELONG_LOCAL);
			if (!method.pushBackLocal(field)) {
				sum.YieldDetail("Redefinition var in method:",
						declVar.nodeToken);
			}
		}
		super.visit(n, method);
	}
	
	@Override
	public void visit(MessageHandler n, Object argu) {
		// TODO Auto-generated method stub
		// add MessageHandler function into symbolic table
		SymbolClass declClass = (SymbolClass) argu;
		SymbolMethod method = new SymbolMethod(new TypeObject(TypeConstants.INT),
				n.nodeToken3.tokenImage, declClass);
		//add parameters
		method.pushBackParam(new SymbolField(new TypeObject(TypeConstants.INTARRAY),
				n.identifier.nodeToken.tokenImage,method,SymbolField.BELONG_PARAM));
		method.pushBackParam(new SymbolField(new TypeObject(TypeConstants.INT),
				n.identifier1.nodeToken.tokenImage,method,SymbolField.BELONG_PARAM));
		if (!declClass.addMethod(method))
			sum
					.YieldDetail(
							"Redefinition method in class,currently do not support overload:",
							n.nodeToken3);
		//add local variables
		for (int i = 0; i < n.nodeListOptional.nodes.size(); ++i) {
			VarDecl declVar = (VarDecl) n.nodeListOptional.nodes.get(i);
			SymbolField field = new SymbolField(
					GenerateCorrectType(declVar.type),
					declVar.identifier.nodeToken.tokenImage, method,
					SymbolField.BELONG_LOCAL);
			if (!method.pushBackLocal(field)) {
				sum.YieldDetail("Redefinition var in method:",
						declVar.nodeToken);
			}
		}
		////////////////////////////////add same methods for MiniDraw
		//add the member functions to MiniDraw
		SymbolClassTable st=declClass.getSymbolClassTable();		
		SymbolClass sc=st.getClassByStr(((DrawClass)(n.getParent())).
				nodeToken2.tokenImage);
		method = new SymbolMethod(new TypeObject(TypeConstants.INT),
				n.nodeToken3.tokenImage, sc);
		//add parameters
		method.pushBackParam(new SymbolField(new TypeObject(TypeConstants.INTARRAY),
				n.identifier.nodeToken.tokenImage,method,SymbolField.BELONG_PARAM));
		method.pushBackParam(new SymbolField(new TypeObject(TypeConstants.INT),
				n.identifier1.nodeToken.tokenImage,method,SymbolField.BELONG_PARAM));
		sc.addMethod(method);
		super.visit(n, argu);
	}

	@Override
	public void visit(ClassDeclaration n, Object argu) {
		// TODO Auto-generated method stub
		super.visit(n, addClassToTable(n.identifier, n.nodeListOptional,
				(SymbolClassTable) argu));
	}

	@Override
	public void visit(ClassExtendsDeclaration n, Object argu) {
		// TODO Auto-generated method stub
		super.visit(n, addClassToTable(n.identifier, n.nodeListOptional,
				(SymbolClassTable) argu));
	}
	
	@Override
	public void visit(DrawClass n, Object argu) {
		// TODO Auto-generated method stub
		//add virtual class
		NodeToken token=new NodeToken(n.nodeToken2.tokenImage);
		Identifier id=new Identifier(token);		
		NodeListOptional op=new NodeListOptional();
		addClassToTable(id,op,(SymbolClassTable)argu);
		super.visit(n, addClassToTable(n.identifier,n.nodeListOptional,
				(SymbolClassTable)argu));
	}

	public String GetSummary() {
		return sum.toString();
	}

	public boolean IsSummaryExist() {
		return sum.IsSummaryExist() ? true : false;
	}

	

}
