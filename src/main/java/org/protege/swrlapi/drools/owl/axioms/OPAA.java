package org.protege.swrlapi.drools.owl.axioms;

import org.protege.swrlapi.drools.extractors.DroolsA2OWLAxiomExtractor;
import org.protege.swrlapi.drools.owl.DroolsTernaryObject;
import org.protege.swrlapi.drools.owl.entities.I;
import org.protege.swrlapi.drools.owl.entities.OP;
import org.protege.swrlapi.exceptions.TargetRuleEngineException;
import org.semanticweb.owlapi.model.OWLObjectPropertyAssertionAxiom;

/**
 * This class represents an OWL object property assertion axiom.
 * <p>
 * We need to have 8 possible constructors for the different argument combinations. This approach makes the rules more
 * readable.
 */
public class OPAA extends DroolsTernaryObject<I, OP, I> implements A
{
	public OPAA(I subject, OP property, I object)
	{
		super(subject, property, object);
	}

	public OPAA(I subject, OP property, String objectName)
	{
		this(subject, property, new I(objectName));
	}

	public OPAA(I subject, String propertyName, I object)
	{
		this(subject, new OP(propertyName), object);
	}

	public OPAA(I subject, String propertyName, String objectName)
	{
		this(subject, new OP(propertyName), new I(objectName));
	}

	public OPAA(String subjectName, OP property, I object)
	{
		this(new I(subjectName), property, object);
	}

	public OPAA(String subjectName, OP property, String objectName)
	{
		this(new I(subjectName), property, new I(objectName));
	}

	public OPAA(String subjectName, String propertyName, I object)
	{
		this(new I(subjectName), new OP(propertyName), object);
	}

	public OPAA(String subjectName, String propertyName, String objectName)
	{
		this(new I(subjectName), new OP(propertyName), new I(objectName));
	}

	public I getS()
	{
		return getT1();
	}

	public OP getP()
	{
		return getT2();
	}

	public I getO()
	{
		return getT3();
	}

	@Override
	public OWLObjectPropertyAssertionAxiom extract(DroolsA2OWLAxiomExtractor extractor) throws TargetRuleEngineException
	{
		return extractor.extract(this);
	}

	@Override
	public String toString()
	{
		return "OPAA" + super.toString();
	}
}