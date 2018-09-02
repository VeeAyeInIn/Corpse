package util.function.parser;

import util.function.SingleFunction;

/**
 * Functional Interface for Parsing Data.
 * <p>
 * Currently contains preset instructions for several
 * types of parsing, such as words, sentences, and
 * sections / paragraphs. Such arrays can be streamed
 * if need be.
 * <p>
 * Contains two boolean result methods to check the
 * state of a character, in order to determine if it
 * is uppercase, or lowercase.
 * <p>
 * Interface can be extended or implemented, and can
 * be changed to fit how the extension should function,
 * for instance, to parse commands.
 *
 * @author GrimlyTwisted
 */
@FunctionalInterface
public interface Parser extends SingleFunction<String> {

	/**
	 * Parses strings sent through the parameter.
	 *
	 * @param argument the argument that is being passed, what is the
	 *                 String that will be effected.
	 *
	 * @return the result of the lambda expression, or override of the
	 * 		method.
	 */
	@Override
	String run(String argument);

	/**
	 * Check if the character is uppercase
	 *
	 * @param character the character being checked
	 *
	 * @return the result
	 */
	default boolean isUppercase(char character) {
		String value = String.valueOf(character);
		return value.equals(value.toUpperCase());
	}

	/**
	 * Check if the character is lowercase
	 *
	 * @param character the character being checked
	 *
	 * @return the result
	 */
	default boolean isLowercase(char character) {
		String value = String.valueOf(character);
		return value.equals(value.toLowerCase());
	}
}
