package me.pokerman99.commands;

import java.awt.Color;

import me.pokerman99.constants.PXPlusIPEmbed;
import me.pokerman99.constants.PokeBrawlEmbed;
import me.pokerman99.constants.TheDarkMCEmbed;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.ChannelType;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class IpCommand extends ListenerAdapter {

	@Override
	public void onMessageReceived(MessageReceivedEvent e) {
		;
		if (e.getChannelType().equals(ChannelType.TEXT)) {
			String msg = e.getMessage().getContent().toLowerCase();
			String[] command = msg.split(" ");
			int size = command.length;
			if (command[0].equals("*ip") || command[0].equals(".ip")) {
				if (e.getChannel().getId().equals("401096720244670464")) return;
				if (e.getChannel().getId().equals("401097790153031690")) return;
				if (e.getChannel().getId().equals("401097902354595840")) return;
				if (e.getChannel().getId().equals("401098015248482314")) return;
				switch (e.getGuild().getId()) {
				case "258797004757532672": { // PXPLUS
					if (size > 2)
						return;
					//e.getMessage().delete().queue();
					if (size == 2) {
						String server = command[1];
						switch (server) {
						case "pokedash": {
							e.getChannel().sendMessage(PXPlusIPEmbed.pokedash().build()).queue();
							break;
						}
						case "pokelegends": {
							e.getChannel().sendMessage(PXPlusIPEmbed.pokelegends().build()).queue();
							break;
						}
						case "pokeverse": {
							e.getChannel().sendMessage(PXPlusIPEmbed.pokeverse().build()).queue();
							break;
						}
						case "pokeclub": {
							e.getChannel().sendMessage(PXPlusIPEmbed.pokeclub().build()).queue();
							break;
						}
						/*case "endercraft": {
							e.getChannel().sendMessage(PXPlusIPEmbed.endercraft().build()).queue();
							break;
						}*/
						default: {
							EmbedBuilder error = onError();
							error.getDescriptionBuilder().append("Your message: '" + command[1] + "'");
							e.getChannel().sendMessage(error.build()).queue();
						}
						}
					} else if (size == 1) {
						e.getChannel().sendMessage(PXPlusIPEmbed.noArgs().build()).queue();
					}
					break;
				}
				case "291940579875618816": { // pokebrawl
					if (size > 1)
						return;
					e.getMessage().delete().queue();
					e.getChannel().sendMessage(PokeBrawlEmbed.pokebrawl().build()).queue();
					break;
				}
				case "136168737580515328":{
					if (size > 1)
						return;
					e.getMessage().delete().queue();
					e.getChannel().sendMessage(TheDarkMCEmbed.TheDarkMC().build()).queue();
					break;
				}
				default: {
					e.getChannel().sendMessage(onUnsupported().build()).queue();
				}
				}

			}
		}
	}

	public EmbedBuilder onUnsupported() {
		EmbedBuilder error = new EmbedBuilder();
		error.setColor(Color.RED).setTitle(":x: **Unsupported server!**").getDescriptionBuilder().append(
				"This server is not supported for this command!\nIf you believe this to be an error contact pokerman99#5742");
		return error;
	}

	public EmbedBuilder onError() {
		EmbedBuilder error = new EmbedBuilder();
		error.setColor(Color.RED).setTitle(":x: **Invalid Server!**");
		return error;
	}
	
}
