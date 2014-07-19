package org.swrlapi.drools.owl.axioms;

import org.semanticweb.owlapi.model.OWLDeclarationAxiom;
import org.swrlapi.drools.extractors.DroolsOWLAxiomExtractor;
import org.swrlapi.drools.owl.properties.AP;
import org.swrlapi.exceptions.TargetRuleEngineException;

/**
 * This class represents an OWL annotation property declaration.
 */
public class APDA extends DA<AP>
{
	public APDA(String propertyID)
	{
		super(new AP(propertyID));
	}

	public APDA(AP property)
	{
		super(property);
	}

	public AP getP()
	{
		return getE();
	}

	@Override
	public String toString()
	{
		return "APDA(" + super.toString() + ")";
	}

	@Override
	public OWLDeclarationAxiom extract(DroolsOWLAxiomExtractor extractor) throws TargetRuleEngineException
	{
		return extractor.extract(this);
	}
}