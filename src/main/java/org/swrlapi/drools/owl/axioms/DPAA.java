package org.swrlapi.drools.owl.axioms;

import org.semanticweb.owlapi.model.OWLDataPropertyAssertionAxiom;
import org.swrlapi.drools.extractors.DroolsOWLAxiomExtractor;
import org.swrlapi.drools.owl.core.DroolsTernaryObject;
import org.swrlapi.drools.owl.core.L;
import org.swrlapi.drools.owl.properties.DP;
import org.swrlapi.drools.owl.core.I;
import org.swrlapi.exceptions.TargetRuleEngineException;

/**
 * This class represents an OWL data property assertion axiom.
 *
 * @see org.semanticweb.owlapi.model.OWLDataPropertyAssertionAxiom
 */
public class DPAA extends DroolsTernaryObject<I, String, L> implements A
{
	public DPAA(String subjectName, String propertyID, L object)
	{
		super(new I(subjectName), propertyID, object);
	}

	public DPAA(I subject, String propertyID, L object)
	{
		super(subject, propertyID, object);
	}

	public I gets()
	{
		return getT1();
	}

	public String getpid()
	{
		return getT2();
	}

	public L geto()
	{
		return getT3();
	}

	@Override
	public OWLDataPropertyAssertionAxiom extract(DroolsOWLAxiomExtractor converter) throws TargetRuleEngineException
	{
		return converter.extract(this);
	}

	@Override
	public String toString()
	{
		return "DPAA" + super.toString();
	}
}