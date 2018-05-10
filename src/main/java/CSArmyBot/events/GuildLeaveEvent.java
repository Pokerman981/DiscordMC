package CSArmyBot.events;

import java.awt.Color;
import java.time.OffsetDateTime;

import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.TextChannel;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.events.Event;
import net.dv8tion.jda.core.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.core.events.guild.member.GuildMemberLeaveEvent;
import net.dv8tion.jda.core.hooks.EventListener;

public class GuildLeaveEvent implements EventListener{

	@Override
	public void onEvent(Event event) {
		if (event instanceof GuildMemberLeaveEvent){
			if (!((GuildMemberLeaveEvent) event).getGuild().getId().equals("435616811602673688")) return;
			TextChannel channel = ((GuildMemberLeaveEvent) event).getGuild().getTextChannelById("443166636891963392");
			
			channel.sendMessage(onLeave(((GuildMemberLeaveEvent) event).getUser(), ((GuildMemberLeaveEvent) event).getGuild()).build()).queue();
		}
		
	}
	
	public EmbedBuilder onLeave(User user, Guild guild){
		EmbedBuilder embed = new EmbedBuilder();
		
		embed.setColor(Color.red);
		embed.setFooter(String.valueOf(guild.getMembers().size()) + " Users!", null);
		embed.setTitle("User Left!");
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
