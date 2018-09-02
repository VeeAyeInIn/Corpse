package commands.core;

import util.Util;
import util.function.parser.Parser;
import util.function.parser.StructureParser;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;

public class CommandParser {

	private static final HashMap<String, Command> commands = new HashMap<>();
	private final Parser behead;
	private final Parser label;
	private final StructureParser args;
	private String prefix;

	public CommandParser() throws IOException, URISyntaxException {

		prefix = Util.prefix();
		behead = argument -> argument.replaceFirst(prefix, "");
		label = argument -> behead.run(argument).split(" ")[0];
		args = argument -> {
			if (argument.startsWith(prefix + label.run(argument) + " ")) {
				return argument.replaceFirst(prefix + label.run(argument) + " ", "");
			} else {
				return null;
			}
		};
	}

	public static HashMap<String, Command> getCommands() {
		return commands;
	}

	public static void setCommands(HashMap<String, Command> map) {
		commands.clear();
		map.forEach((string, command) -> commands.put(string.toLowerCase(), command));
	}

	public String behead(String argument) {
		return behead.run(argument);
	}

	public String label(String argument) {
		return label.run(argument);
	}

	public String[] args(String argument) {
		try {
			return args.toWords(args.run(argument));
		} catch (NullPointerException ignored) {
			return new String[]{};
		}
	}
}
