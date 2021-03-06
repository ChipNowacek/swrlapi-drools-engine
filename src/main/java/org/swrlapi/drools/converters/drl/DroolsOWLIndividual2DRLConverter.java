package org.swrlapi.drools.converters.drl;

import org.checkerframework.checker.nullness.qual.NonNull;
import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLIndividual;
import org.swrlapi.bridge.SWRLRuleEngineBridge;
import org.swrlapi.bridge.converters.TargetRuleEngineConverterBase;
import org.swrlapi.bridge.converters.TargetRuleEngineOWLIndividualConverter;
import org.swrlapi.drools.core.DroolsNames;

/**
 * Converts an OWLAPI OWL individual to its Drools representation.
 *
 * @see org.semanticweb.owlapi.model.OWLIndividual
 * @see org.semanticweb.owlapi.model.OWLAnonymousIndividual
 * @see org.semanticweb.owlapi.model.OWLNamedIndividual
 */
public class DroolsOWLIndividual2DRLConverter extends TargetRuleEngineConverterBase
  implements TargetRuleEngineOWLIndividualConverter<String>
{
  public DroolsOWLIndividual2DRLConverter(@NonNull SWRLRuleEngineBridge bridge)
  {
    super(bridge);
  }

  @NonNull @Override public String convert(@NonNull OWLIndividual individual)
  {
    if (individual.isNamed()) {
      IRI individualIRI = individual.asOWLNamedIndividual().getIRI();
      String prefixedName = iri2PrefixedName(individualIRI);
      return "new " + DroolsNames.INDIVIDUAL_CLASS_NAME + "(\"" + prefixedName + "\")";
    } else {
      String individualID = individual.asOWLAnonymousIndividual().getID().getID();
      return "new " + DroolsNames.INDIVIDUAL_CLASS_NAME + "(\"" + individualID + "\")";
    }
  }
}
