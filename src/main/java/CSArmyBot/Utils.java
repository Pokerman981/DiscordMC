package CSArmyBot;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import net.dv8tion.jda.core.Permission;
import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.TextChannel;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;

public class Utils {

	// Sendes message with expriy
	public static void MessageChannelWithDelete(String message, int timer, TextChannel channel) throws InterruptedException, ExecutionException {
		channel.sendMessage(message).submit().get().delete().queueAfter(timer, TimeUnit.SECONDS);
	}

	// Sends message to channel
	public static void MessageChannel(String message, TextChannel channel) {
		channel.sendMessage(message).queue();
	}
	
	// Delete the users message
	public static void DeleteUserMessageWithDelay(Message msg, int timer){
		msg.delete().queueAfter(timer, TimeUnit.SECONDS);
	}

	public static Boolean isStaff(GuildMessageReceivedEvent event){
		Member member = event.getMember();
		
		if (member.hasPermission(Permission.MESSAGE_MANAGE)){
			return true;
		}
		return false;
	}
	
}
