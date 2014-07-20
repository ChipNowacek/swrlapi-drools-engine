package org.swrlapi.drools.owl.axioms;

import org.semanticweb.owlapi.model.OWLDisjointDataPropertiesAxiom;
import org.swrlapi.drools.extractors.DroolsOWLAxiomExtractor;
import org.swrlapi.exceptions.TargetRuleEngineException;

/**
 * Class representing a disjoint data property axiom in Drools.
 *
 * @see org.semanticweb.owlapi.model.OWLDisjointDataPropertiesAxiom
 */
public class DJDPA extends DroolsBinaryPropertiesAxiom
{
	public DJDPA(String property1ID, String property2ID)
	{
		super(property1ID, property2ID);
	}

	@Override
	public OWLDisjointDataPropertiesAxiom extract(DroolsOWLAxiomExtractor converter) throws TargetRuleEngineException
	{
		return converter.extract(this);
	}

	@Override
	public String toString()
	{
		return "DJDPA" + super.toString();
	}
}
