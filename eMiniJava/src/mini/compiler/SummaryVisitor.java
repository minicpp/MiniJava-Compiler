package mini.compiler;
import mini.syntaxtree.*;
import mini.visitor.*;
/**
 * 
 * @author ddrmsdos
 * get detailed errors by traversing the tree
 */
public class SummaryVisitor extends DepthFirstVisitor{
	private StringBuffer buf=new StringBuffer();	
	private int counts=0;
	private String strSummary="";
	public SummaryVisitor(String sum)
	{
		strSummary=sum;
	}
	@Override
	public void visit(NodeToken n) {
		// TODO Auto-generated method stub
		++counts;
		if(counts==1)
		{
			Summary sum=new Summary();
			sum.YieldDetail(strSummary,n);
			buf.append(sum.toString());
		}		
		super.visit(n);
	}	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return buf.toString();
	}
}
