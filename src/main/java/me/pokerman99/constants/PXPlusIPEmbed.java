package me.pokerman99.constants;

import java.awt.Color;

import net.dv8tion.jda.core.EmbedBuilder;

public class PXPlusIPEmbed {

	public static EmbedBuilder pokedash() {
		EmbedBuilder pokedash = new EmbedBuilder();
		pokedash.setTitle("Pokedash Info").setColor(Color.decode("#3cb0d6"))
				.setThumbnail("https://i.gyazo.com/13255ea852d4551df36e9c1c9c3d4e92.png").getDescriptionBuilder()
				.append("**IP:**\tplay.pokedash.org\n**Version:**\t 1.10.2");
		return pokedash;
	}
	public static EmbedBuilder pokeverse() {
		EmbedBuilder pokeverse = new EmbedBuilder();
		pokeverse.setTitle("Pokeverse Info").setColor(Color.yellow)
				.setThumbnail("https://i.gyazo.com/911b1ee65ef59f36343d5bc5da046d12.png").getDescriptionBuilder()
				.append("**IP:**\tplay.pokeverse.org\n**Version:**\t 1.10.2");
		return pokeverse;
	}
	public static EmbedBuilder pokeclub() {
		EmbedBuilder pokeclub = new EmbedBuilder();
		pokeclub.setTitle("Pokeclub Info").setColor(Color.decode("#1e7d72"))
				.setThumbnail("https://i.gyazo.com/21380d908820fc0c78e95a813e66b5cd.png").getDescriptionBuilder()
				.append("**IP:**\tplay.pokeclub.net\n**Version:**\t 1.10.2");
		return pokeclub;
	}
	public static EmbedBuilder pokelegends() {
		EmbedBuilder pokelegends = new EmbedBuilder();
		pokelegends.setTitle("Pokelegends Info").setColor(Color.decode("#FF4500"))
				.setThumbnail("https://i.gyazo.com/70f77a6428a14045674f94ccd5134941.png").getDescriptionBuilder()
				.append("**IP:**\tplay.pokelegends.net\n**Version:**\t 1.10.2");
		return pokelegends;
	}
	public static EmbedBuilder endercraft() {
		EmbedBuilder endercraft = new EmbedBuilder();
		endercraft.setTitle("Endercraft Info").setColor(Color.MAGENTA.darker().darker())
				.setThumbnail("https://i.gyazo.com/862a0ccdf95105cf32edfc3aea30dce5.png").getDescriptionBuilder()
				.append("**IP:**\tplay.endercraft.org\n**Version:**\t 1.10.2");
		return endercraft;
	}
	
	public static EmbedBuilder noArgs() {
		EmbedBuilder noArgs = new EmbedBuilder();
		noArgs.setTitle("Server IPs").setColor(Color.red).getDescriptionBuilder()
				.append("**.ip (server name)**\n\n**Servers:**\n\t*pokedash*\n\t*pokeverse*\n\t*pokeclub*\n\t*pokelegends*");
		return noArgs;
	}
	
	
	
}
