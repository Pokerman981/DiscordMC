package me.pokerman99.bots.discordmc;

import javax.security.auth.login.LoginException;

import me.pokerman99.bots.discordbots.events.JoinEvent;
import me.pokerman99.bots.discordbots.events.LinkBlockerEvent;
import me.pokerman99.bots.discordbots.events.SwearFilterEvent;
import me.pokerman99.commands.IpCommand;
import me.pokerman99.commands.PingCommand;
import me.pokerman99.commands.StatusCommand;
import me.pokerman99.commands.TestCommand;
import me.pokerman99.commands.banCommand;
import me.pokerman99.commands.evalCommand;
import me.pokerman99.commands.kickCommand;
import me.pokerman99.commands.supportCommands;
import me.pokerman99.constants.Ref;
import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.entities.Game;
import net.dv8tion.jda.core.exceptions.RateLimitedException;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class Main extends ListenerAdapter {

	public static JDA jda;

	public static void main(String[] args) {
		try {
			jda = new JDABuilder(AccountType.BOT).setToken(Ref.main_token).buildBlocking();
			jda.getPresence().setGame(Game.of("¯\\_(ツ)_/¯"));
			jda.addEventListener(new PingCommand());
			jda.addEventListener(new JoinEvent());
			jda.addEventListener(new SwearFilterEvent());
			jda.addEventListener(new StatusCommand());
			jda.addEventListener(new IpCommand());
			jda.addEventListener(new supportCommands());
			jda.addEventListener(new evalCommand());
			jda.addEventListener(new banCommand());
			jda.addEventListener(new kickCommand());
			// jda.addEventListener(new LinkPermitCommand());
			jda.addEventListener(new LinkBlockerEvent());
			jda.addEventListener(new TestCommand());
		} catch (LoginException | IllegalArgumentException | InterruptedException | RateLimitedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
