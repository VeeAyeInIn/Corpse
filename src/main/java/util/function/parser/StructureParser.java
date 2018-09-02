package util.function.parser;

import java.util.stream.Stream;

/**
 * A Parser with basic grammatical function pre-made in the interface.
 * Can be used to split
 *
 * @author GrimlyTwisted
 */
@FunctionalInterface
public interface StructureParser extends Parser {

	/**
	 * Parses a string into a collection of "words."
	 *
	 * @param argument the argument that is being passed, where the words will be taken from.
	 *
	 * @return the array of words contained in the argument
	 */
	default String[] toWords(String argument) {
		return argument.split(" ");
	}

	/**
	 * Parses a string into a collection of "sentences."
	 *
	 * @param argument the argument that is being passed, where the sentences will be taken from.
	 *
	 * @return the array of sentences contained in the argument
	 */
	default String[] toSentences(String argument) {
		return argument.split("! |\\? |\\. ");
	}

	/**
	 * Parses a string into a collection of "sections."
	 *
	 * @param argument the argument that is being passed, where the sections will be taken from.
	 *
	 * @return the array of sections contained in the argument
	 */
	default String[] toSections(String argument) {
		return argument.split(System.getProperty("line.separator"));
	}

	/**
	 * Streams the array of words derived from the parse function
	 *
	 * @param argument the raw argument being passed to stream
	 *
	 * @return the stream
	 */
	default Stream<String> streamWords(String argument) {
		return Stream.of(toWords(argument));
	}

	/**
	 * Streams the array of sentences derived from the parse function
	 *
	 * @param argument the raw argument being passed to stream
	 *
	 * @return the stream
	 */
	default Stream<String> streamSentences(String argument) {
		return Stream.of(toSentences(argument));
	}

	/**
	 * Streams the array of sections derived from the parse function
	 *
	 * @param argument the raw argument being passed to stream
	 *
	 * @return the stream
	 */
	default Stream<String> streamSections(String argument) {
		return Stream.of(toSections(argument));
	}
}
