package commands.moderation;

import commands.core.Command;
import net.dv8tion.jda.core.Permission;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

public class Clear implements Command {

	@Override
	public void execute(MessageReceivedEvent event, String[] args) {
		if (args.length == 1) {
			if (args[0].equalsIgnoreCase("all")) {
				event.getMessage().delete().complete();
				AtomicBoolean running = new AtomicBoolean(true);
				while (running.get()) {
					List<Message> messages = event.getTextChannel().getHistory().retrievePast(100).complete()
							.stream().filter(message -> message.getCreationTime().isAfter(OffsetDateTime.now()
									.minusWeeks(2).plusSeconds(5))).collect(Collectors.toList());

					if (running.getAndSet(messages.size() < 2)) {
						event.getTextChannel().deleteMessages(messages).complete();
					} else if (!messages.isEmpty()) {
						messages.get(0).delete().complete();
					}
				}
			} else {
				int count = 0;
				try {
					count = Integer.parseInt(args[0]);
				} catch (Exception ignored) {
				}
				if (count < 2 || count > 100) {
					error(event);
				} else {
					event.getMessage().delete().complete();
					event.getTextChannel().deleteMessages(event.getTextChannel().getHistory().retrievePast(count).complete()
							.stream().filter(message -> message.getCreationTime().isAfter(OffsetDateTime.now()
									.minusWeeks(2).plusSeconds(5))).collect(Collectors.toList())).complete();
				}
			}
		} else {
			error(event);
		}
	}

	@Override
	public Permission permission() {
		return Permission.MESSAGE_MANAGE;
	}

	@Override
	public String name() {
		return "Clear";
	}

	@Override
	public String usage() {
		return prefix() + name() + " <amount | all>";
	}

	@Override
	public String explanation() {
		return "Clears the specified amount of messages in the channel history.";
	}
}
