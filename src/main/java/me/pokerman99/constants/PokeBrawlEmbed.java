package me.pokerman99.constants;

import java.awt.Color;

import net.dv8tion.jda.core.EmbedBuilder;

public class PokeBrawlEmbed {
	
	public static EmbedBuilder pokebrawl() {
		EmbedBuilder pokedash = new EmbedBuilder();
		pokedash.setTitle("Pokebrawl Info").setColor(Color.cyan)
				.setThumbnail("https://i.gyazo.com/534eedcedf4b1d456466a848ee7ead96.png").getDescriptionBuilder()
				.append("**IP:**\tplay.poke-brawl.com\n**Version:**\t 1.10.2\n**Pixelmon Version:**\t Generations 1.1");
		return pokedash;
	}

}
