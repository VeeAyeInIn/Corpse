package listeners;

import commands.core.Command;
import commands.core.CommandParser;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;
import net.dv8tion.jda.core.hooks.SubscribeEvent;

import java.io.IOException;
import java.net.URISyntaxException;

public class MessageReceivedListener extends ListenerAdapter {

	private CommandParser parser = new CommandParser();

	public MessageReceivedListener() throws IOException, URISyntaxException {
	}

	@SubscribeEvent
	public void onMessageReceived(MessageReceivedEvent event) {

		if (!event.getAuthor().isBot()) {
			String message = event.getMessage().getContentDisplay().toLowerCase();

			if (CommandParser.getCommands().containsKey(parser.label(message))) {
				Command command = CommandParser.getCommands().get(parser.label(message));

				if (event.getTextChannel().getGuild().getMember(event.getAuthor()).hasPermission(command.permission())) {
					command.execute(event, parser.args(message));
				} else {
					command.noPermission(event);
				}
			}
		}
	}
}
