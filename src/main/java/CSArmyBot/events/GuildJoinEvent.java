package CSArmyBot.events;

import java.awt.Color;
import java.time.OffsetDateTime;
import java.time.temporal.TemporalAccessor;

import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.TextChannel;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.events.Event;
import net.dv8tion.jda.core.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.core.hooks.EventListener;

public class GuildJoinEvent implements EventListener{

	@Override
	public void onEvent(Event event) {
		if (event instanceof GuildMemberJoinEvent){
			if (!((GuildMemberJoinEvent) event).getGuild().getId().equals("435616811602673688")) return;
			TextChannel channel = ((GuildMemberJoinEvent) event).getGuild().getTextChannelById("443166636891963392");
			
			channel.sendMessage(onJoin(((GuildMemberJoinEvent) event).getUser(), ((GuildMemberJoinEvent) event).getGuild()).build()).queue();
		}
		
	}
	
	public EmbedBuilder onJoin(User user, Guild guild){
		EmbedBuilder embed = new EmbedBuilder();
		
		embed.setColor(Color.green);
		embed.setFooter(String.valueOf(guild.getMembers().size()) + " Users!", null);
		embed.setTitle("User Joined!");
		embed.setThumbnail(user.getEffectiveAvatarUrl());
		embed.getDescriptionBuilder()
		.append("\n**User**\n")
		.append(user.getName() + "#" + user.getDiscriminator())
		.append("\n\n")
		.append("**User ID**\n")
		.append(user.getId());
		embed.setTimestamp(OffsetDateTime.now());
		
		
		
		return embed;
	}

}
