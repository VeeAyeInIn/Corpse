package commands.fun;

import commands.core.Command;
import net.dv8tion.jda.core.Permission;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

import java.util.concurrent.ThreadLocalRandom;

public class Insult implements Command {

	private final String[] insults = new String[]{
			"{NAME}, if I wanted to kill myself, I would climb your ego and jump to your IQ.",
			"You're as useless at the ueue in queue.",
			"Oh, you got an idea {NAME}? Explains the smell of smoke.",
			"If I had a dollar for every time you've said something smart, I'd be $50 in debt.",
			"Oh shut up, {NAME}, you'll never be the man your mother is.",
			"You're so ugly, Hello Kitty said goodbye to you.",
			"It looks like your face caught on fire and someone tried to put it out with a fork.",
			"Do you have to leave so soon? I was just about to poison the tea.",
			"Your so ugly when you popped out the doctor said aww what a treasure and your mom said yeah lets bury it.",
			"We all sprang from apes, but you didn't spring far enough.",
			"{NAME}, I hear when you were a child your mother wanted to hire somebody to take care of you, but the mafia wanted too much.",
			"I could eat a bowl of alphabet soup and crap out a smarter comeback than what you just said.",
			"Yo {NAME}, why don't you check up on eBay and see if they have a life for sale.",
			"Here's 20 cents, call all your friends and give me back the change, {NAME}.",
			"Why don't you go to walmart and get a new personality because clearly the one you got from k-mart has expired.",
			"{NAME} is about as sharp as a shoelace",
			"All the branches fell off of the {NAME} family tree when you were born.",
			"Your asinine simian countenance alludes that your fetid stench has annulled the anthropoid ape species diversity.",
			"{NAME}'s mom is so ugly, she made Chuck Norris have a heart attack.",
			"Are you in a costume? It's not Halloween yet... oh, never mind.",
			"Look, you ain't funny, {NAME}. Your life is just a joke.",
			"{NAME} couldn't hit water, even if they fell out of a boat.",
			"As an outsider, what do you think of the human race?",
			"Don’t feel bad, there are many people who have no talent!",
			"Don’t think, it might sprain your brain.",
			"Fellows like you don’t grow from trees, they swing from them.",
			"I don’t know what it is that makes you so stupid but it really works.",
			"I’ve seen people like you but I had to pay admission.",
			"Any similarity between you and a human is purely coincidental.",
			"{NAME} is always lost in thought. It’s unfamiliar territory.",
			"{NAME} is living proof that man can live without a brain.",
			"I can’t seem to remember your name, and please don’t help me.",
			"I could make a monkey out of you, but why should I take all the credit?",
			"I’d like to kick you in the teeth, but why should I improve your looks?"
	};

	@Override
	public void execute(MessageReceivedEvent event, String[] args) {
		if (args.length == 1 && !event.getMessage().getMentionedUsers().isEmpty()) {
			User mentioned = event.getMessage().getMentionedUsers().get(0);
			if (mentioned.getId().equals("338417454462009365")) {
				error(event);
			} else {
				event.getTextChannel().sendMessage(
						template(event)
								.addField("Insulting " + mentioned.getName(),
										insults[ThreadLocalRandom.current().nextInt(insults.length)].replaceAll("\\{NAME}",
												mentioned.getName()), false).build()
				).queue();
			}
		} else {
			error(event);
		}
	}

	@Override
	public String name() {
		return "Insult";
	}

	@Override
	public String usage() {
		return prefix() + "insult <user>";
	}

	@Override
	public String explanation() {
		return "Insult people in your discord guild";
	}

	@Override
	public Permission permission() {
		return Permission.MESSAGE_WRITE;
	}
}
