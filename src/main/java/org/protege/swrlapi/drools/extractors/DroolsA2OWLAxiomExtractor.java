package org.protege.swrlapi.drools.extractors;

import org.protege.swrlapi.converters.TargetRuleEngineOWLAxiomConverter;
import org.protege.swrlapi.drools.owl.axioms.A;
import org.protege.swrlapi.drools.owl.axioms.APA;
import org.protege.swrlapi.drools.owl.axioms.APDA;
import org.protege.swrlapi.drools.owl.axioms.CAA;
import org.protege.swrlapi.drools.owl.axioms.CDA;
import org.protege.swrlapi.drools.owl.axioms.DCA;
import org.protege.swrlapi.drools.owl.axioms.DDPA;
import org.protege.swrlapi.drools.owl.axioms.DIA;
import org.protege.swrlapi.drools.owl.axioms.DJDPA;
import org.protege.swrlapi.drools.owl.axioms.DJOPA;
import org.protege.swrlapi.drools.owl.axioms.DOPA;
import org.protege.swrlapi.drools.owl.axioms.DPAA;
import org.protege.swrlapi.drools.owl.axioms.DPDA;
import org.protege.swrlapi.drools.owl.axioms.ECA;
import org.protege.swrlapi.drools.owl.axioms.EDPA;
import org.protege.swrlapi.drools.owl.axioms.EOPA;
import org.protege.swrlapi.drools.owl.axioms.FDPA;
import org.protege.swrlapi.drools.owl.axioms.FOPA;
import org.protege.swrlapi.drools.owl.axioms.IDA;
import org.protege.swrlapi.drools.owl.axioms.IOPA;
import org.protege.swrlapi.drools.owl.axioms.IPA;
import org.protege.swrlapi.drools.owl.axioms.IRPA;
import org.protege.swrlapi.drools.owl.axioms.OPAA;
import org.protege.swrlapi.drools.owl.axioms.OPDA;
import org.protege.swrlapi.drools.owl.axioms.RDPA;
import org.protege.swrlapi.drools.owl.axioms.ROPA;
import org.protege.swrlapi.drools.owl.axioms.SCA;
import org.protege.swrlapi.drools.owl.axioms.SDPA;
import org.protege.swrlapi.drools.owl.axioms.SIA;
import org.protege.swrlapi.drools.owl.axioms.SOPA;
import org.protege.swrlapi.drools.owl.axioms.SPA;
import org.protege.swrlapi.drools.owl.axioms.TPA;
import org.protege.swrlapi.exceptions.TargetRuleEngineException;
import org.protege.swrlapi.extractors.TargetRuleEngineExtractor;
import org.semanticweb.owlapi.model.OWLAsymmetricObjectPropertyAxiom;
import org.semanticweb.owlapi.model.OWLClassAssertionAxiom;
import org.semanticweb.owlapi.model.OWLDataPropertyAssertionAxiom;
import org.semanticweb.owlapi.model.OWLDataPropertyDomainAxiom;
import org.semanticweb.owlapi.model.OWLDataPropertyRangeAxiom;
import org.semanticweb.owlapi.model.OWLDeclarationAxiom;
import org.semanticweb.owlapi.model.OWLDifferentIndividualsAxiom;
import org.semanticweb.owlapi.model.OWLDisjointClassesAxiom;
import org.semanticweb.owlapi.model.OWLDisjointDataPropertiesAxiom;
import org.semanticweb.owlapi.model.OWLDisjointObjectPropertiesAxiom;
import org.semanticweb.owlapi.model.OWLEquivalentClassesAxiom;
import org.semanticweb.owlapi.model.OWLEquivalentDataPropertiesAxiom;
import org.semanticweb.owlapi.model.OWLEquivalentObjectPropertiesAxiom;
import org.semanticweb.owlapi.model.OWLFunctionalDataPropertyAxiom;
import org.semanticweb.owlapi.model.OWLFunctionalObjectPropertyAxiom;
import org.semanticweb.owlapi.model.OWLInverseFunctionalObjectPropertyAxiom;
import org.semanticweb.owlapi.model.OWLInverseObjectPropertiesAxiom;
import org.semanticweb.owlapi.model.OWLIrreflexiveObjectPropertyAxiom;
import org.semanticweb.owlapi.model.OWLObjectPropertyAssertionAxiom;
import org.semanticweb.owlapi.model.OWLObjectPropertyDomainAxiom;
import org.semanticweb.owlapi.model.OWLObjectPropertyRangeAxiom;
import org.semanticweb.owlapi.model.OWLSameIndividualAxiom;
import org.semanticweb.owlapi.model.OWLSubClassOfAxiom;
import org.semanticweb.owlapi.model.OWLSubDataPropertyOfAxiom;
import org.semanticweb.owlapi.model.OWLSubObjectPropertyOfAxiom;
import org.semanticweb.owlapi.model.OWLSymmetricObjectPropertyAxiom;
import org.semanticweb.owlapi.model.OWLTransitiveObjectPropertyAxiom;

/**
 * This interface list methods for converting Drools objects represented by the class {@link A} to their Portability API
 * representation. Only OWL axioms currently supported by the Drools implementation will appear in this list. Supported
 * axioms are targeted to the requirements of an OWL 2 RL reasoner. For a complete list of possible OWL axioms see
 * {@link TargetRuleEngineOWLAxiomConverter}. Some axioms (e.g., annotation axioms) may never be reasoned with so are
 * unlikely to require extraction.
 */
public interface DroolsA2OWLAxiomExtractor extends TargetRuleEngineExtractor
{
	OWLDeclarationAxiom extract(CDA da) throws TargetRuleEngineException;

	OWLSubClassOfAxiom extract(SCA sca) throws TargetRuleEngineException;

	OWLDisjointClassesAxiom extract(DCA dca) throws TargetRuleEngineException;

	OWLEquivalentClassesAxiom extract(ECA eca) throws TargetRuleEngineException;

	OWLObjectPropertyDomainAxiom extract(DOPA dopa) throws TargetRuleEngineException;

	OWLDataPropertyDomainAxiom extract(DDPA ddpa) throws TargetRuleEngineException;

	OWLDeclarationAxiom extract(IDA da) throws TargetRuleEngineException;

	OWLDataPropertyRangeAxiom extract(RDPA rdpa) throws TargetRuleEngineException;

	OWLObjectPropertyRangeAxiom extract(ROPA ropa) throws TargetRuleEngineException;

	OWLDeclarationAxiom extract(OPDA da) throws TargetRuleEngineException;

	OWLDeclarationAxiom extract(DPDA da) throws TargetRuleEngineException;

	OWLDeclarationAxiom extract(APDA da) throws TargetRuleEngineException;

	OWLClassAssertionAxiom extract(CAA caa) throws TargetRuleEngineException;

	OWLObjectPropertyAssertionAxiom extract(OPAA opaa) throws TargetRuleEngineException;

	OWLDataPropertyAssertionAxiom extract(DPAA dpaa) throws TargetRuleEngineException;

	OWLSameIndividualAxiom extract(SIA sia) throws TargetRuleEngineException;

	OWLDifferentIndividualsAxiom extract(DIA dia) throws TargetRuleEngineException;

	OWLSubObjectPropertyOfAxiom extract(SOPA sopa) throws TargetRuleEngineException;

	OWLSubDataPropertyOfAxiom extract(SDPA sdpa) throws TargetRuleEngineException;

	OWLEquivalentObjectPropertiesAxiom extract(EOPA eopa) throws TargetRuleEngineException;

	OWLEquivalentDataPropertiesAxiom extract(EDPA edpa) throws TargetRuleEngineException;

	OWLDisjointObjectPropertiesAxiom extract(DJOPA edpa) throws TargetRuleEngineException;

	OWLDisjointDataPropertiesAxiom extract(DJDPA edpa) throws TargetRuleEngineException;

	OWLFunctionalObjectPropertyAxiom extract(FOPA fopa) throws TargetRuleEngineException;

	OWLFunctionalDataPropertyAxiom extract(FDPA fopa) throws TargetRuleEngineException;

	OWLInverseFunctionalObjectPropertyAxiom extract(IPA ipa) throws TargetRuleEngineException;

	OWLIrreflexiveObjectPropertyAxiom extract(IRPA irpa) throws TargetRuleEngineException;

	OWLAsymmetricObjectPropertyAxiom extract(APA apa) throws TargetRuleEngineException;

	OWLSymmetricObjectPropertyAxiom extract(SPA spa) throws TargetRuleEngineException;

	OWLTransitiveObjectPropertyAxiom extract(TPA spa) throws TargetRuleEngineException;

	OWLInverseObjectPropertiesAxiom extract(IOPA iopa) throws TargetRuleEngineException;
}