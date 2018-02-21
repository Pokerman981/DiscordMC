package me.pokerman99.bots.discordbots.events;

import net.dv8tion.jda.core.events.guild.GuildJoinEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class JoinEvent extends ListenerAdapter{
	
	public void onGuildJoin(GuildJoinEvent event){
		System.out.printf("[+] Joined %s (%s Members)\n", event.getGuild().getName(), event.getGuild().getMembers().size());
	}
}
