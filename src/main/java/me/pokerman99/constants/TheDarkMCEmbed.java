package me.pokerman99.constants;

import java.awt.Color;

import net.dv8tion.jda.core.EmbedBuilder;

public class TheDarkMCEmbed {
	
	public static EmbedBuilder TheDarkMC() {
		EmbedBuilder TheDarkMC = new EmbedBuilder();
		TheDarkMC.setTitle("TheDarkGamesMC Info").setColor(Color.magenta)
				.setThumbnail("https://i.gyazo.com/911b1ee65ef59f36343d5bc5da046d12.png").getDescriptionBuilder()
				.append("**IP:**\tplay.thedarkgamesmc.com\n**Version:**\t 1.10.2\n**Pixelmon Version:**\t Generations 1.1");
		return TheDarkMC;
	}

}
