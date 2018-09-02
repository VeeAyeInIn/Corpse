package util;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

public final class Util {

	public static String token() throws URISyntaxException, IOException {

		final String[] token = {""};

		Path config = Paths.get(Objects.requireNonNull(Util.class.getClassLoader().getResource("config.yml")).toURI());
		Files.lines(config).filter(string -> string.startsWith("token: ")).findFirst().ifPresent(string -> {
			if (string.startsWith("token: ")) {
				token[0] = string.replaceFirst("token: ", "");
			}
		});

		return token[0];
	}

	public static String prefix() throws URISyntaxException, IOException {

		final String[] prefix = {""};

		Path config = Paths.get(Objects.requireNonNull(Util.class.getClassLoader().getResource("config.yml")).toURI());
		Files.lines(config).filter(string -> string.startsWith("prefix: ")).findFirst().ifPresent(string -> {
			if (string.startsWith("prefix: ")) {
				prefix[0] = string.replaceFirst("prefix: ", "");
			}
		});

		return prefix[0];
	}
}
