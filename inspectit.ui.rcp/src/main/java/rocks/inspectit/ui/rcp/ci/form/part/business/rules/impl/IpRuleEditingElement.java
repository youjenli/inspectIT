package rocks.inspectit.ui.rcp.ci.form.part.business.rules.impl;

import java.util.Set;

import rocks.inspectit.shared.cs.ci.business.expression.AbstractExpression;
import rocks.inspectit.shared.cs.ci.business.expression.impl.StringMatchingExpression;
import rocks.inspectit.shared.cs.ci.business.valuesource.impl.HostValueSource;
import rocks.inspectit.ui.rcp.ci.form.part.business.MatchingRulesEditingElementFactory.MatchingRuleType;
import rocks.inspectit.ui.rcp.ci.form.part.business.rules.AbstractStringMatchingRuleEditingElement;
import rocks.inspectit.ui.rcp.validation.AbstractValidationManager;
import rocks.inspectit.ui.rcp.validation.ValidationState;

/**
 * Editing element for a IP matching expression.
 *
 * @author Alexander Wert
 *
 */
public class IpRuleEditingElement extends AbstractStringMatchingRuleEditingElement<HostValueSource> {
	/**
	 * The name of the string source.
	 */
	private static final String SOURCE_NAME = "IP Address";

	/**
	 * Description text for the IP/Host matching rule.
	 */
	private static final String DESCRIPTION = "This rule applies if the host name or IP of the server serving the corresponding request\n"
			+ "matches (equals, starts with, etc.) the specified String value.";

	/**
	 * Constructor.
	 *
	 * @param expression
	 *            The {@link AbstractExpression} instance to modify.
	 * @param editable
	 *            indicates whether this editing element should be editable or read-only. If false,
	 *            this element will be read only.
	 * @param upstreamValidationManager
	 *            {@link AbstractValidationManager} instance to be notified on validation state
	 *            changes.
	 */
	public IpRuleEditingElement(StringMatchingExpression expression, boolean editable, AbstractValidationManager<AbstractExpression> upstreamValidationManager) {
		super(expression, MatchingRuleType.IP, DESCRIPTION, SOURCE_NAME, false, editable, upstreamValidationManager);
	}

	/**
	 * Validates the contents of the passed {@link StringMatchingExpression} without the need to
	 * create corresponding editing controls.
	 *
	 * @param expression
	 *            {@link StringMatchingExpression} to validate
	 * @return a set of {@link ValidationState} instances.
	 */
	public static Set<ValidationState> validate(StringMatchingExpression expression) {
		return AbstractStringMatchingRuleEditingElement.validate(expression, SOURCE_NAME);
	}

	@Override
	protected boolean isValidExpression(StringMatchingExpression expression) {
		return expression.getStringValueSource() instanceof HostValueSource;
	}

}
