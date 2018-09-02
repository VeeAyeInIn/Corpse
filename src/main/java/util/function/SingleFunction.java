package util.function;

import java.io.IOException;
import java.net.URISyntaxException;

/**
 * Creates a Function using a singular type, unlike {@link Function},
 * where it takes in an input, and output type.
 *
 * @param <T> the parameter type, of both the argument, and resultant
 *
 * @author GrimlyTwisted
 */
@FunctionalInterface
public interface SingleFunction<T> extends Function<T, T> {

	@Override
	T run(T argument) throws IOException, URISyntaxException;
}
