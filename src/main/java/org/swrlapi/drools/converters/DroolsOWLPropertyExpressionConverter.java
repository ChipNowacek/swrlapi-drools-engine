package org.swrlapi.drools.converters;

import checkers.nullness.quals.NonNull;
import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLDataProperty;
import org.semanticweb.owlapi.model.OWLDataPropertyExpression;
import org.semanticweb.owlapi.model.OWLObjectProperty;
import org.semanticweb.owlapi.model.OWLObjectPropertyExpression;
import org.swrlapi.bridge.SWRLRuleEngineBridge;
import org.swrlapi.bridge.converters.TargetRuleEngineOWLPropertyExpressionConverter;
import org.swrlapi.drools.owl.properties.DP;
import org.swrlapi.drools.owl.properties.OP;
import org.swrlapi.drools.resolvers.DroolsResolver;

/**
 * This class converts OWLAPI OWL property expressions to their Drools representation.
 *
 * @see org.semanticweb.owlapi.model.OWLObjectPropertyExpression
 * @see org.semanticweb.owlapi.model.OWLDataPropertyExpression
 */
public class DroolsOWLPropertyExpressionConverter extends DroolsConverterBase implements
TargetRuleEngineOWLPropertyExpressionConverter<String>
{
  @NonNull private final DroolsResolver resolver;

  public DroolsOWLPropertyExpressionConverter(@NonNull SWRLRuleEngineBridge bridge, @NonNull DroolsResolver resolver)
  {
    super(bridge);

    this.resolver = resolver;

    reset();
  }

  public void reset()
  {
    this.resolver.reset();
  }

  @NonNull @Override
  public String convert(@NonNull OWLObjectPropertyExpression propertyExpression)
  {
    if (!getOWLObjectResolver().recordsOWLObjectPropertyExpression(propertyExpression)) {

      if (propertyExpression.isAnonymous()) {
        String propertyExpressionID = this.resolver.getDroolsOPEResolver().generatePEID();
        getOWLObjectResolver().recordOWLObjectPropertyExpression(propertyExpressionID,
            propertyExpression);

        return propertyExpressionID;
      } else {
        OWLObjectProperty objectProperty = propertyExpression.asOWLObjectProperty();
        IRI propertyIRI = objectProperty.getIRI();
        String prefixedName = getIRIResolver().iri2PrefixedName(propertyIRI);
        OP op = new OP(prefixedName);

        getOWLObjectResolver().recordOWLObjectPropertyExpression(prefixedName, propertyExpression);
        this.resolver.getDroolsOPEResolver().record(op);

        return prefixedName;
      }
    } else
      return getOWLObjectResolver().resolveOWLObjectPropertyExpression(propertyExpression);
  }

  @NonNull @Override
  public String convert(@NonNull OWLDataPropertyExpression propertyExpression)
  {
    if (!getOWLObjectResolver().recordsOWLDataPropertyExpression(propertyExpression)) {

      if (propertyExpression.isAnonymous()) {
        String propertyExpressionID = this.resolver.getDroolsDPEResolver().generatePEID();
        getOWLObjectResolver().recordOWLDataPropertyExpression(propertyExpressionID, propertyExpression);

        return propertyExpressionID;
      } else {
        OWLDataProperty objectProperty = propertyExpression.asOWLDataProperty();
        IRI propertyIRI = objectProperty.getIRI();
        String prefixedName = getIRIResolver().iri2PrefixedName(propertyIRI);
        DP dp = new DP(prefixedName);

        getOWLObjectResolver().recordOWLDataPropertyExpression(prefixedName, propertyExpression);
        this.resolver.getDroolsDPEResolver().record(dp);

        return prefixedName;
      }
    } else
      return getOWLObjectResolver().resolveOWLDataPropertyExpression(propertyExpression);
  }
}