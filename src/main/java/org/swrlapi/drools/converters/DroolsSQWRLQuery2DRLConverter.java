package org.swrlapi.drools.converters;

import java.util.HashSet;
import java.util.Set;

import org.semanticweb.owlapi.model.SWRLAtom;
import org.swrlapi.converters.TargetRuleEngineConverterBase;
import org.swrlapi.core.SWRLRuleEngineBridge;
import org.swrlapi.drools.DroolsNames;
import org.swrlapi.drools.DroolsSWRLRuleEngine;
import org.swrlapi.exceptions.BuiltInException;
import org.swrlapi.exceptions.TargetRuleEngineException;
import org.swrlapi.ext.SWRLAPIBuiltInAtom;
import org.swrlapi.sqwrl.SQWRLNames;
import org.swrlapi.sqwrl.SQWRLQuery;
import org.swrlapi.sqwrl.TargetRuleEngineSQWRLQueryConverter;

public class DroolsSQWRLQuery2DRLConverter extends TargetRuleEngineConverterBase implements
		TargetRuleEngineSQWRLQueryConverter
{
	private final DroolsSWRLBodyAtom2DRLConverter bodyAtom2DRLConverter;
	private final DroolsSWRLHeadAtom2DRLConverter headAtom2DRLConverter;

	private final DroolsSWRLRuleEngine droolsEngine;

	public DroolsSQWRLQuery2DRLConverter(SWRLRuleEngineBridge bridge, DroolsSWRLRuleEngine droolsEngine)
	{
		super(bridge);

		this.droolsEngine = droolsEngine;
		this.bodyAtom2DRLConverter = new DroolsSWRLBodyAtom2DRLConverter(bridge);
		this.headAtom2DRLConverter = new DroolsSWRLHeadAtom2DRLConverter(bridge);
	}

	public void reset()
	{
		this.bodyAtom2DRLConverter.reset();
		this.headAtom2DRLConverter.reset();
	}

	@Override
	public void convert(SQWRLQuery query) throws TargetRuleEngineException, BuiltInException
	{
		getDroolsSWRLBodyAtomConverter().reset();
		getDroolsSWRLHeadAtomConverter().reset();

		sqwrlQuery2DRL(query);
	}

	private void sqwrlQuery2DRL(SQWRLQuery query) throws TargetRuleEngineException
	{
		if (!query.hasCollections())
			sqwrlNonCollectionQuery2DRL(query);
		else
			sqwrlCollectionQuery2DRL(query);
	}

	// System.err.println("----------------------------------------------------------------------------------------------------");
	// System.err.println("SWRL: " + rule.getRuleText());
	// System.err.println("DRL:\n" + drlRule);

	private void sqwrlNonCollectionQuery2DRL(SQWRLQuery query) throws TargetRuleEngineException
	{
		Set<String> variableNames = new HashSet<String>();
		String ruleName = query.getName();
		String drlRule = getQueryPreamble(ruleName);

		for (SWRLAtom atom : query.getBodyAtoms())
			drlRule += "\n   " + getDroolsSWRLBodyAtomConverter().convert(atom, variableNames) + " ";

		drlRule = addQueryThenClause(drlRule);

		for (SWRLAtom atom : query.getHeadAtoms())
			drlRule += "\n   " + getDroolsSWRLHeadAtomConverter().convert(atom) + " ";

		drlRule = addQueryEndClause(drlRule);

		getDroolsEngine().defineDRLSQWRLPhase1Rule(query.getName(), ruleName, drlRule);
	}

	private void sqwrlCollectionQuery2DRL(SQWRLQuery query) throws TargetRuleEngineException
	{
		Set<String> variableNames = new HashSet<String>();
		String queryName = query.getName();
		String phase1RuleName = queryName + "-makeCollection";
		String phase2RuleName = queryName + "-operateCollection";
		String drlPhase1Rule = getQueryPreamble(phase1RuleName);
		String drlPhase2Rule = getQueryPreamble(phase2RuleName);

		for (SWRLAtom atom : query.getSQWRLPhase1BodyAtoms())
			drlPhase1Rule += "\n  " + getDroolsSWRLBodyAtomConverter().convert(atom, variableNames) + " ";

		drlPhase1Rule = addQueryThenClause(drlPhase1Rule);

		if (query.hasCollections()) { // Assert existence of all relevant collections returned from collection construction
																	// built-ins
			try {
				for (SWRLAPIBuiltInAtom atom : query.getBuiltInAtomsFromBody(SQWRLNames.getCollectionMakeBuiltInNames())) {
					String collectionVariableName = atom.getArgumentVariableName(0);
					drlPhase1Rule += "\n  sqwrlInferrer.infer($" + collectionVariableName + "); ";
				}
			} catch (RuntimeException e) {
				throw new TargetRuleEngineException("error processing SQWRL collection make in query " + queryName + ": "
						+ e.getMessage(), e);
			}
		}
		drlPhase1Rule = addQueryEndClause(drlPhase1Rule);
		getDroolsEngine().defineDRLSQWRLPhase1Rule(queryName, phase1RuleName, drlPhase1Rule);

		variableNames.clear();

		if (query.hasCollections()) { // Match relevant collections
			try {
				for (SWRLAPIBuiltInAtom atom : query.getBuiltInAtomsFromBody(SQWRLNames.getCollectionMakeBuiltInNames())) {
					String collectionVariableName = atom.getArgumentVariableName(0);
					if (!variableNames.contains(collectionVariableName)) {
						String collectionName = collectionVariableName;
						drlPhase2Rule += "\n  $" + collectionVariableName + ":" + DroolsNames.SQWRL_COLLECTION_CLASS_NAME + "("
								+ DroolsNames.QUERY_NAME_FIELD_NAME + "==\"" + queryName + "\", " + DroolsNames.COLLECTION_NAME_FIELD_NAME
								+ "==\"" + collectionName + "\")";
						variableNames.add(collectionVariableName);
					}
				}
			} catch (RuntimeException e) {
				throw new TargetRuleEngineException("error processing SQWRL collection operate in query " + queryName + ": "
						+ e.getMessage(), e);
			}
		}
		for (SWRLAtom atom : query.getSQWRLPhase2BodyAtoms())
			drlPhase2Rule += "\n  " + getDroolsSWRLBodyAtomConverter().convert(atom, variableNames) + " ";

		drlPhase2Rule = addQueryThenClause(drlPhase2Rule);
		for (SWRLAtom atom : query.getHeadAtoms())
			drlPhase2Rule += "\n  " + getDroolsSWRLHeadAtomConverter().convert(atom);

		drlPhase2Rule = addQueryEndClause(drlPhase2Rule);
		getDroolsEngine().defineDRLSQWRLPhase2Rule(queryName, phase2RuleName, drlPhase2Rule);
	}

	private String getQueryPreamble(String queryName)
	{
		return "rule \"" + queryName + "\" \nwhen ";
	}

	private String addQueryEndClause(String queryText)
	{
		return queryText + "\nend";
	}

	private String addQueryThenClause(String queryText)
	{
		return queryText + "\nthen ";
	}

	private DroolsSWRLBodyAtom2DRLConverter getDroolsSWRLBodyAtomConverter()
	{
		return this.bodyAtom2DRLConverter;
	}

	private DroolsSWRLHeadAtom2DRLConverter getDroolsSWRLHeadAtomConverter()
	{
		return this.headAtom2DRLConverter;
	}

	private DroolsSWRLRuleEngine getDroolsEngine()
	{
		return this.droolsEngine;
	}
}
