package commands.core;

import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.Permission;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import util.Util;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.time.OffsetDateTime;
import java.util.Calendar;

public interface Command {

	void execute(MessageReceivedEvent event, String[] args);

	String name();

	String usage();

	String explanation();

	Permission permission();

	default void error(MessageReceivedEvent event) {
		EmbedBuilder builder = new EmbedBuilder();
		event.getTextChannel().sendMessage(builder
				.setAuthor(event.getAuthor().getName() + "#" + event.getAuthor().getDiscriminator(),
						event.getAuthor().getEffectiveAvatarUrl(),
						event.getAuthor().getEffectiveAvatarUrl())
				.setColor(Color.RED)
				.addField("Name", name(), false)
				.addField("Usage", usage(), false)
				.addField("Explanation", explanation(), false)
				.addField("Permission", permission().getName(), false)
				.setFooter(event.getAuthor().getId() + " @ " + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
						.format(Calendar.getInstance().getTime()), event.getGuild().getIconUrl())
				.build()).queue();
	}

	default void help(MessageReceivedEvent event) {
		EmbedBuilder builder = new EmbedBuilder();
		event.getTextChannel().sendMessage(builder
				.setAuthor(event.getAuthor().getName() + "#" + event.getAuthor().getDiscriminator(),
						event.getAuthor().getEffectiveAvatarUrl(),
						event.getAuthor().getEffectiveAvatarUrl())
				.setColor(Color.LIGHT_GRAY)
				.addField("Name", name(), false)
				.addField("Usage", usage(), false)
				.addField("Explanation", explanation(), false)
				.addField("Permission", permission().getName(), false)
				.setFooter(event.getAuthor().getId() + " @ " + OffsetDateTime.now().toLocalDateTime().toString(),
						event.getGuild().getIconUrl())
				.build()).queue();
	}

	default EmbedBuilder template(MessageReceivedEvent event) {
		return new EmbedBuilder().setAuthor(event.getAuthor().getName() + "#" + event.getAuthor().getDiscriminator(),
				event.getAuthor().getEffectiveAvatarUrl(),
				event.getAuthor().getEffectiveAvatarUrl())
				.setFooter(event.getAuthor().getId() + " @ " + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
						.format(Calendar.getInstance().getTime()), event.getGuild().getIconUrl())
				.setColor(Color.GREEN);
	}

	default String prefix() {
		try {
			return Util.prefix();
		} catch (URISyntaxException | IOException e) {
			e.printStackTrace();
			return "//";
		}
	}
}
