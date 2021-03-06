//
// Generated by JTB 1.3.2
//

package mini.syntaxtree;

/**
 * Grammar production:
 * identifier -> Identifier()
 * nodeToken -> "["
 * exp -> Exp()
 * nodeToken1 -> "]"
 * nodeToken2 -> "="
 * exp1 -> Exp()
 * nodeToken3 -> ";"
 */
public @SuppressWarnings("all") class ArrayAssignmentStatement implements Node {
   private Node parent;
   public Identifier identifier;
   public NodeToken nodeToken;
   public Exp exp;
   public NodeToken nodeToken1;
   public NodeToken nodeToken2;
   public Exp exp1;
   public NodeToken nodeToken3;

   public ArrayAssignmentStatement(Identifier n0, NodeToken n1, Exp n2, NodeToken n3, NodeToken n4, Exp n5, NodeToken n6) {
      identifier = n0;
      if ( identifier != null ) identifier.setParent(this);
      nodeToken = n1;
      if ( nodeToken != null ) nodeToken.setParent(this);
      exp = n2;
      if ( exp != null ) exp.setParent(this);
      nodeToken1 = n3;
      if ( nodeToken1 != null ) nodeToken1.setParent(this);
      nodeToken2 = n4;
      if ( nodeToken2 != null ) nodeToken2.setParent(this);
      exp1 = n5;
      if ( exp1 != null ) exp1.setParent(this);
      nodeToken3 = n6;
      if ( nodeToken3 != null ) nodeToken3.setParent(this);
   }

   public ArrayAssignmentStatement(Identifier n0, Exp n1, Exp n2) {
      identifier = n0;
      if ( identifier != null ) identifier.setParent(this);
      nodeToken = new NodeToken("[");
      if ( nodeToken != null ) nodeToken.setParent(this);
      exp = n1;
      if ( exp != null ) exp.setParent(this);
      nodeToken1 = new NodeToken("]");
      if ( nodeToken1 != null ) nodeToken1.setParent(this);
      nodeToken2 = new NodeToken("=");
      if ( nodeToken2 != null ) nodeToken2.setParent(this);
      exp1 = n2;
      if ( exp1 != null ) exp1.setParent(this);
      nodeToken3 = new NodeToken(";");
      if ( nodeToken3 != null ) nodeToken3.setParent(this);
   }

   public void accept(mini.visitor.Visitor v) {
      v.visit(this);
   }
   public <R,A> R accept(mini.visitor.GJVisitor<R,A> v, A argu) {
      return v.visit(this,argu);
   }
   public <R> R accept(mini.visitor.GJNoArguVisitor<R> v) {
      return v.visit(this);
   }
   public <A> void accept(mini.visitor.GJVoidVisitor<A> v, A argu) {
      v.visit(this,argu);
   }
   public void setParent(Node n) { parent = n; }
   public Node getParent()       { return parent; }
}

