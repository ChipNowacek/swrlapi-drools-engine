package org.swrlapi.drools.owl.axioms;

import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.dataflow.qual.SideEffectFree;
import org.semanticweb.owlapi.model.OWLInverseFunctionalObjectPropertyAxiom;
import org.swrlapi.drools.extractors.DroolsOWLAxiomExtractor;
import org.swrlapi.exceptions.TargetSWRLRuleEngineException;

/**
 * Class representing an OWL inverse functional object property axiom in Drools.
 *
 * @see org.semanticweb.owlapi.model.OWLInverseFunctionalObjectPropertyAxiom
 */
public class IFOPA extends DroolsUnaryPropertyAxiom
{
  private static final long serialVersionUID = 1L;

  public IFOPA(@NonNull String propertyID)
  {
    super(propertyID);
  }

  @NonNull @Override public OWLInverseFunctionalObjectPropertyAxiom extract(@NonNull DroolsOWLAxiomExtractor extractor)
    throws TargetSWRLRuleEngineException
  {
    return extractor.extract(this);
  }

  @Override public void visit(@NonNull AVisitor visitor)
  {
    visitor.visit(this);
  }

  @NonNull @SideEffectFree @Override public String toString()
  {
    return "IFOPA" + super.toString();
  }
}