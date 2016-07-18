package org.swrlapi.drools.owl.classes;

/**
 * A visitor that can visit a Drools representation of OWL class expressions, represented by
 * {@link org.swrlapi.drools.owl.classes.CE}s.
 * <p>
 * It is modeled on the OWLAPI's {@link org.semanticweb.owlapi.model.OWLClassExpressionVisitor}.
 *
 * @see org.swrlapi.drools.owl.classes.CE
 * @see org.semanticweb.owlapi.model.OWLClassExpressionVisitor
 */
public interface CEVisitor
{
  void visit(DAVFCE ce);

  void visit(DECCE ce);

  void visit(DHVCE ce);

  void visit(DMaxCCE ce);

  void visit(DMaxQCCE ce);

  void visit(DMinCCE ce);

  void visit(DSVFCE ce);

  void visit(OAVFCE ce);

  void visit(OECCE ce);

  void visit(OHVCE ce);

  void visit(OIOCE ce);

  void visit(OMaxCCE ce);

  void visit(OMaxQCCE ce);

  void visit(OMinCCE ce);

  void visit(OOCOCE ce);

  void visit(OSVFCE ce);

  void visit(OUOCE ce);
}
