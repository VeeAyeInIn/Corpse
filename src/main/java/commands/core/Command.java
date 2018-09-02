package commands.core;

import net.dv8tion.jda.core.Permission;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import util.Util;

import java.io.IOException;
import java.net.URISyntaxException;

public interface Command {

	void execute(MessageReceivedEvent event, String[] args);

	void noPermission(MessageReceivedEvent event);

	Permission permission();

	String name();

	String usage();

	String explanation();

	default String prefix() {
		try {
			return Util.prefix();
		} catch (URISyntaxException | IOException e) {
			e.printStackTrace();
			return "//";
		}
	}
}
