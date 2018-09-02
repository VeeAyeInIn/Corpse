package util.function;

import java.io.IOException;
import java.net.URISyntaxException;

/**
 * Simplest function form. Provides an input type and an output type.
 * Basis of parsers and {@link SingleFunction} in this library.
 *
 * @param <T> the parameter type that will be inputted
 * @param <R> the result type of what the output will be
 *
 * @author GrimlyTwisted
 */
@FunctionalInterface
public interface Function<T, R> {

	/**
	 * Run an expression using the input of {@link T} and the resultant
	 * type of {@link R}.
	 *
	 * @param argument the argument being passed into the expression. Will be returned as {@link R} as opposed to being
	 *                 void, null, or of the same type.
	 *
	 * @return the resultant value, {@link R}.
	 */
	R run(T argument) throws IOException, URISyntaxException;
}
