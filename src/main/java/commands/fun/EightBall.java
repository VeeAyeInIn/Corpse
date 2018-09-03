package commands.fun;

import commands.core.Command;
import net.dv8tion.jda.core.Permission;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

import java.util.concurrent.ThreadLocalRandom;

public class EightBall implements Command {

	private final String[] responses = new String[]{

			"It is certain",
			"It is decidedly so",
			"Without a doubt",
			"Yes -- definitely",
			"You may rely on it",
			"As I see it, yes",
			"Most likely",
			"Outlook good",
			"Yes",
			"Signs point to yes",
			"Reply hazy, try again",
			"Ask again later",
			"Better not tell you now",
			"Cannot predict now",
			"Concentrate and ask again",
			"Don't count on it",
			"My reply is no",
			"My sources say no",
			"Outlook not so good",
			"Very doubtful"

	};

	@Override
	public void execute(MessageReceivedEvent event, String[] args) {
		if (args.length > 0) {
			StringBuilder argument = new StringBuilder();
			for (String arg : args) {
				argument.append(arg).append(" ");
			}
			event.getTextChannel().sendMessage(
					template(event).addField("Magic 8 Ball, " + argument,
							responses[ThreadLocalRandom.current().nextInt(responses.length)],
							false).build()
			).queue();
		} else {
			error(event);
		}
	}

	@Override
	public String name() {
		return "8 Ball";
	}

	@Override
	public String usage() {
		return prefix() + "8 <arguments>";
	}

	@Override
	public String explanation() {
		return "Using specialized magic, the 8 ball can answer any question.";
	}

	@Override
	public Permission permission() {
		return Permission.MESSAGE_WRITE;
	}
}
