package commands.informational;

import commands.core.Command;
import net.dv8tion.jda.core.Permission;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class Invite implements Command {

	@Override
	public void execute(MessageReceivedEvent event, String[] args) {
		if (args.length == 0) {
			event.getAuthor().openPrivateChannel().queue(channel ->
					channel.sendMessage("https://discordapp.com/api/oauth2/authorize?client_id=446786543345205248&permissions=0&scope=bot").queue());
		} else {
			error(event);
		}
	}

	@Override
	public String name() {
		return "Invite";
	}

	@Override
	public String usage() {
		return prefix() + "Invite";
	}

	@Override
	public String explanation() {
		return "Gives the invite link for the bot";
	}

	@Override
	public Permission permission() {
		return Permission.MESSAGE_WRITE;
	}
}
