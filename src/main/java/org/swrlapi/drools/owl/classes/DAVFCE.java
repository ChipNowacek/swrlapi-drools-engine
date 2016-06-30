package org.swrlapi.drools.owl.classes;

import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.dataflow.qual.SideEffectFree;
import org.swrlapi.builtins.arguments.SWRLClassExpressionBuiltInArgument;
import org.swrlapi.drools.extractors.DroolsSWRLBuiltInArgumentExtractor;
import org.swrlapi.drools.owl.core.DroolsTernaryObject;
import org.swrlapi.drools.owl.dataranges.D;
import org.swrlapi.exceptions.TargetSWRLRuleEngineException;

/**
 * This class represents an OWL data all values from class expression in Drools.
 *
 * @see org.semanticweb.owlapi.model.OWLDataAllValuesFrom
 */
public class DAVFCE extends DroolsTernaryObject<String, String, D> implements CE
{
  private static final long serialVersionUID = 1L;

  public DAVFCE(@NonNull String ceid, @NonNull String propertyID, @NonNull String datatypeID)
  {
    super(ceid, propertyID, new D(datatypeID));
  }

  @NonNull @Override public String getceid()
  {
    return getT1();
  }

  @NonNull public String getpid()
  {
    return getT2();
  }

  @NonNull public D getV()
  {
    return getT3();
  }

  @SideEffectFree @NonNull @Override public String toString()
  {
    return "DAVFCE" + super.toString();
  }

  @Override public @NonNull SWRLClassExpressionBuiltInArgument extract(@NonNull DroolsSWRLBuiltInArgumentExtractor extractor)
    throws TargetSWRLRuleEngineException
  {
    return extractor.extract(this);
  }
}
