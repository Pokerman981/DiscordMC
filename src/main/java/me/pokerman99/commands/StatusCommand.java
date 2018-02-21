package me.pokerman99.commands;

import java.awt.Color;
import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import ch.jamiete.mcping.MinecraftPing;
import ch.jamiete.mcping.MinecraftPingOptions;
import ch.jamiete.mcping.MinecraftPingReply;
import me.pokerman99.constants.Ref;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class StatusCommand extends ListenerAdapter {
	@Override
	public void onMessageReceived(MessageReceivedEvent event) {
		try { // Changed this around if shit breaks
			String[] command = event.getMessage().getContent().split(" ");

			if (command.length > 1)
				return;

			if (command[0].equals("*status") || command[0].equals(".status")) {
				if (event.getGuild().getId().equals("258797004757532672")) {
					if (event.getChannel().getId().equals("401096720244670464")) return;
					if (event.getChannel().getId().equals("401097790153031690")) return;
					if (event.getChannel().getId().equals("401097902354595840")) return;
					if (event.getChannel().getId().equals("401098015248482314")) return;
					//event.getMessage().delete().submit();
					event.getChannel().sendMessage("Getting server status... " + event.getAuthor().getAsMention())
					.submit().get().delete().queueAfter(1, TimeUnit.SECONDS);
					MinecraftPingReply pokedash;
					MinecraftPingReply pokeverse;
					MinecraftPingReply pokelegends;
					MinecraftPingReply pokeclub;
					pokedash = new MinecraftPing()
							.getPing(new MinecraftPingOptions().setHostname("play.pokedash.org").setPort(25565));
					pokeverse = new MinecraftPing()
							.getPing(new MinecraftPingOptions().setHostname("play.pokeverse.org").setPort(25565));
					pokelegends = new MinecraftPing()
							.getPing(new MinecraftPingOptions().setHostname("play.pokelegends.net").setPort(25565));
					pokeclub = new MinecraftPing()
							.getPing(new MinecraftPingOptions().setHostname("play.pokeclub.net").setPort(25565));

					EmbedBuilder eb = onPXPlusEmbed();
					eb.getDescriptionBuilder().append("\n<:pokedash:375433829764169741> **Pokedash**\n \t\t**"
							+ pokedash.getPlayers().getOnline() + "** players online!\n");
					eb.getDescriptionBuilder().append("\n<:pokeverse:375434343071481857> **Pokeverse**\n \t\t**"
							+ pokeverse.getPlayers().getOnline() + "** players online!\n");
					eb.getDescriptionBuilder().append("\n<:pokelegends:375434322339168256> **PokeLegends**\n \t\t**"
							+ pokelegends.getPlayers().getOnline() + "** players online!\n");
					eb.getDescriptionBuilder().append("\n<:pokeclub:375434302730665994> **Pokeclub**\n \t\t**"
							+ pokeclub.getPlayers().getOnline() + "** players online!\n");
					int total = pokedash.getPlayers().getOnline() + pokeverse.getPlayers().getOnline() + pokelegends.getPlayers().getOnline() + pokeclub.getPlayers().getOnline();
					eb.getDescriptionBuilder().append("\n<:pixelmon:375785337412386828> **Pixelmon+ Total**\n \t\t**"
							+ total + "** players online!\n");
					event.getChannel().sendMessage(eb.build()).queue();
				}

				if (event.getGuild().getId().equals("291940579875618816")) {
					event.getMessage().delete().submit();
					event.getChannel().sendMessage("Getting server status... " + event.getAuthor().getAsMention())
					.submit().get().delete().queueAfter(1, TimeUnit.SECONDS);
					MinecraftPingReply darkbrawl;
					MinecraftPingReply lightbrawl;
					MinecraftPingReply pokebrawl;
					darkbrawl = new MinecraftPing()
							.getPing(new MinecraftPingOptions().setHostname("66.70.181.42").setPort(10004));
					lightbrawl = new MinecraftPing()
							.getPing(new MinecraftPingOptions().setHostname("66.70.181.42").setPort(10002));
					pokebrawl = new MinecraftPing()
							.getPing(new MinecraftPingOptions().setHostname("play.poke-brawl.com").setPort(25565));

					EmbedBuilder eb = onPokeBrawlEmbed();
					eb.getDescriptionBuilder().append("\n<:pokebrawl:375470241121042442> **PokeBrawl**\n \t\t**"
							+ pokebrawl.getPlayers().getOnline() + "** players online!\n");
					eb.getDescriptionBuilder().append("\n<:pokebrawl:375470241121042442> **DarkBrawl**\n \t\t**"
							+ darkbrawl.getPlayers().getOnline() + "** players online!\n");
					eb.getDescriptionBuilder().append("\n<:pokebrawl:375470241121042442> **LightBrawl**\n \t\t**"
							+ lightbrawl.getPlayers().getOnline() + "** players online!\n");
					event.getChannel().sendMessage(eb.build()).queue();
				}
				if (event.getGuild().getId().equals("136168737580515328")) {
					event.getMessage().delete().submit();
					event.getChannel().sendMessage("Getting server status... " + event.getAuthor().getAsMention())
					.submit().get().delete().queueAfter(1, TimeUnit.SECONDS);
					MinecraftPingReply thedarkmc;
					thedarkmc = new MinecraftPing()
							.getPing(new MinecraftPingOptions().setHostname("play.thedarkgamesmc.com").setPort(25565));

					EmbedBuilder eb = onTheDarkMCEmbed();
					eb.getDescriptionBuilder().append("\n<:logo:363295751218135040> **Pixelmon**\n \t\t**"
							+ thedarkmc.getPlayers().getOnline() + "** players online!\n");
					event.getChannel().sendMessage(eb.build()).submit().get().delete().queueAfter(10, TimeUnit.SECONDS);
				}

			}
		} catch (IOException | InterruptedException | ExecutionException e) {
			event.getChannel().sendMessage("A server is either down or rebooting.").submit();
			e.printStackTrace();
		}
	}

	public EmbedBuilder onPXPlusEmbed() {
		EmbedBuilder eb = new EmbedBuilder().setTitle("**Pixelmon+ Server Stats**\n").setColor(Color.decode("#19750b"));
		return eb;
	}
	
	public EmbedBuilder onPokeBrawlEmbed() {
		EmbedBuilder eb = new EmbedBuilder().setTitle("**PokeBrawl Server Stats**\n").setColor(Color.cyan);
		return eb;
	}
	
	public EmbedBuilder onTheDarkMCEmbed() {
		EmbedBuilder eb = new EmbedBuilder().setTitle("**TheDarkGamesMC Server Stats**\n").setColor(Color.MAGENTA);
		return eb;
	}
}