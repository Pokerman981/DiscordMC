package CSArmyBot.commands;

import java.util.concurrent.ExecutionException;

import CSArmyBot.Main;
import CSArmyBot.Utils;
import net.dv8tion.jda.core.Permission;
import net.dv8tion.jda.core.entities.Icon;
import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.entities.TextChannel;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.events.Event;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.core.hooks.EventListener;
import net.dv8tion.jda.core.requests.Route.Roles;

public class RemovePrivate implements EventListener{

	@Override
	public void onEvent(Event event) {
		try {
		if (event instanceof GuildMessageReceivedEvent) {
			String[] cmd = ((GuildMessageReceivedEvent) event).getMessage().getContentRaw().split(" ");
			if (cmd[0].equals("!removeuser")) {
				
				User user = ((GuildMessageReceivedEvent) event).getAuthor();
				TextChannel channel = ((GuildMessageReceivedEvent) event).getChannel();
				
				if (!Main.userData.containsKey(user.getId())){
					Utils.MessageChannelWithDelete("You do not own a channel, " + user.getAsMention() + "! Create one with !private", 5, channel);
					Utils.DeleteUserMessageWithDelay(((GuildMessageReceivedEvent) event).getMessage(), 5);
					return;
				}
				
				if (!channel.getId().equals(Main.userData.get(user.getId()).get(0))){
					Utils.MessageChannelWithDelete("You do not own this channel, " + user.getAsMention() + "!", 5, channel);
					Utils.DeleteUserMessageWithDelay(((GuildMessageReceivedEvent) event).getMessage(), 5);
					return;
				}
				
				if (cmd.length != 2){
					Utils.MessageChannelWithDelete("You must supply a user in the second arguement! Or you have to many arguments!", 5, channel);
					Utils.DeleteUserMessageWithDelay(((GuildMessageReceivedEvent) event).getMessage(), 5);
					return;
				}
				
				try {((GuildMessageReceivedEvent) event).getGuild().getMemberById(cmd[1].replaceAll("<@", "").replaceAll(">", ""));} catch (NumberFormatException e){
					Utils.MessageChannelWithDelete("The user you supplied is invalid " + user.getAsMention() + "!" , 5, channel);
					Utils.DeleteUserMessageWithDelay(((GuildMessageReceivedEvent) event).getMessage(), 5);
					return;
				}
				
				
				Member user2 = ((GuildMessageReceivedEvent) event).getGuild().getMemberById(cmd[1].replaceAll("<@", "").replaceAll(">", ""));
				
				if (channel.getPermissionOverride(user2).getAllowed() == null){
					Utils.MessageChannelWithDelete("This user does not have access to this channel, " + user.getAsMention() + "!", 5, channel);
					Utils.DeleteUserMessageWithDelay(((GuildMessageReceivedEvent) event).getMessage(), 5);
					return;
				}
				
				channel.getPermissionOverride(user2).getManager().clear(Permission.VIEW_CHANNEL).queue();
				Utils.MessageChannel("Successfully removed " + user2.getAsMention() + "!", channel);
				
			}
		}
	} catch (InterruptedException | ExecutionException e){
		
	}
		
	}

}
