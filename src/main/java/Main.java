import commands.core.Command;
import commands.core.CommandParser;
import commands.fun.EightBall;
import commands.fun.Insult;
import commands.informational.Ping;
import commands.moderation.Clear;
import listeners.MessageReceivedListener;
import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.OnlineStatus;
import net.dv8tion.jda.core.entities.Game;
import util.Util;

import javax.security.auth.login.LoginException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;

public class Main {

	private static final JDABuilder builder = new JDABuilder(AccountType.BOT);
	private static final HashMap<String, Command> commands = new HashMap<>();

	public static void main(String... args) {

		try {
			builder.setToken(Util.token());
			System.out.println("Corpse Token: " + Util.token());
		} catch (URISyntaxException | IOException e) {
			e.printStackTrace();
		}

		builder.setGame(Game.playing("//help"))
				.setStatus(OnlineStatus.ONLINE)
				.setIdle(false)
				.setAutoReconnect(true);

		try {
			registerListeners();
			registerCommands();
		} catch (IOException | URISyntaxException e) {
			e.printStackTrace();
		}

		try {
			builder.buildAsync();
		} catch (LoginException e) {
			e.printStackTrace();
		}
	}

	private static void registerListeners() throws IOException, URISyntaxException {
		builder.addEventListener(new MessageReceivedListener());
	}

	private static void registerCommands() {
		commands.put("ping", new Ping());
		commands.put("clear", new Clear());
		commands.put("8", new EightBall());
		commands.put("insult", new Insult());
		CommandParser.setCommands(commands);
	}
}
