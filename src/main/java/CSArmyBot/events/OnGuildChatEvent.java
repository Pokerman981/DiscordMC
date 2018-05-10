package CSArmyBot.events;

import java.awt.Color;

import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.events.Event;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.core.hooks.EventListener;

public class OnGuildChatEvent implements EventListener {

	@Override
	public void onEvent(Event event) {
		if (event instanceof GuildMessageReceivedEvent) {
			if (((GuildMessageReceivedEvent) event).getAuthor().isBot())return;
			if (((GuildMessageReceivedEvent) event).getAuthor().isFake())return;
			
			String msg = ((GuildMessageReceivedEvent) event).getMessage().getContentRaw().toLowerCase();
			
			if (msg.equals("f")) {
				((GuildMessageReceivedEvent) event).getChannel().sendMessage("Respects Paid :pray:").queue();
			}
			if (msg.equals("x")) {
				((GuildMessageReceivedEvent) event).getChannel().sendMessage("I doubt that").queue();
			}
			if (msg.equals(".links") || msg.equals(".social")) {
				((GuildMessageReceivedEvent) event).getChannel().sendMessage(onLinks().build()).queue();
			}
		}

	}
	
	public EmbedBuilder onLinks(){
		EmbedBuilder links = new EmbedBuilder();
		links.setTitle("Devon Crawford's Social Links");
		links.getDescriptionBuilder().append("<:youtube_circle:421813459852722176> **YouTube:** https://www.youtube.com/channel/UCDrekHmOnkptxq3gUU0IyfA \n\n")
		.append("<:twitter_circle:421814582365913088> **Twitter:** https://twitter.com/DevonCrawford13 \n\n")
		.append("<:snapchat_circle:421814911166054403> **SnapChat:** https://www.snapchat.com/add/devoncrawfordyt \n\n")
		.append("<:instagram_circle:421813978960887816> **Instagram:** https://www.instagram.com/devoncrawford_/ \n\n")
		.append("<:github:439579187435667466> **GitHub:** https://github.com/devoncrawford \n\n")
		.append("<:linkedin:439579610313916436> **LinkedIn:** https://www.linkedin.com/in/devon-crawford-463592130/ \n\n");
		links.setColor(Color.decode("006400"));
		return links;
	}

}
