package commands.informational;

import commands.core.Command;
import net.dv8tion.jda.core.Permission;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class Ping implements Command {

	@Override
	public void execute(MessageReceivedEvent event, String[] args) {
		if (args.length == 0) {
			event.getTextChannel().sendMessage("Ping of " + event.getJDA().getPing() + "ms").queue();
		}
	}

	@Override
	public void noPermission(MessageReceivedEvent event) {

	}

	@Override
	public Permission permission() {
		return Permission.MESSAGE_WRITE;
	}

	@Override
	public String name() {
		return "Ping";
	}

	@Override
	public String usage() {
		return prefix() + name();
	}

	@Override
	public String explanation() {
		return "Allows users to check the ping of the bot.";
	}
}
