package CSArmyBot.events;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import CSArmyBot.Main;
import CSArmyBot.Utils;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.events.Event;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.core.hooks.EventListener;

public class LinkBlockerEvent implements EventListener {

    //private static final String httpLinkRegex = "((([A-Za-z]{3,9}:(?:\\/\\/)?)(?:[-;:&=\\+\\$,\\w]+@)?[A-Za-z0-9.-]+|(?:www.|[-;:&=\\+\\$,\\w]+@)[A-Za-z0-9.-]+)((?:\\/[\\+~%\\/.\\w-_]*)?\\??(?:[-\\+=&;%@.\\w_]*)#?(?:[\\w]*))?)";
    private static final String httpLinkRegex = "(?:(?:https?|ftp):\\/\\/)?[\\w\\/\\-?=%.]+\\.[\\w\\/\\-?=%.]+";
	private static final String BotGUID = "435616811602673688";
    private static final String AllowedChannelID = "439598709299347456";

    @Override
    public void onEvent(Event event) {
        if (event instanceof GuildMessageReceivedEvent) {
            GuildMessageReceivedEvent message = (GuildMessageReceivedEvent) event;

            if (!message.getGuild().getId().equals(BotGUID)) return;
            if (message.getAuthor().isBot()) return;
            if (!message.getChannel().getId().equals(AllowedChannelID)) return;

            //Allow mods to bypass link checker
            //if (Utils.isStaff(message)) return;

            Matcher messageHyperlinks = Pattern.compile(httpLinkRegex).matcher(message.getMessage().getContentRaw());
            List<String> links = new ArrayList<String>(Main.userData.get("links").values());

            Pattern[] blockedLinks = new Pattern[links.size()];
            for (int i = 0; i < links.size(); i++) blockedLinks[i] = Pattern.compile(links.get(i));

            while (messageHyperlinks.find()) {
                //System.out.println(messageHyperlinks.group() + "\n");
                String messageLink = messageHyperlinks.group();

                for (Pattern link : blockedLinks) {
                    System.out.println(link.pattern());
                    System.out.println(messageLink);
                    System.out.println(link.matcher(messageLink).find() + "\n");
                    
                    
                    if (link.matcher(messageLink).find()) {
                        User user = message.getAuthor();
                        message.getChannel().sendMessage("We do not allow this link, " + user.getAsMention() + "! (Warning)\n" + "Blocked by `" + link.pattern() + "` rule, to see all blocked links use `!showlinks`").queue();
                        message.getMessage().delete().queue();
                        return;
                    }
                }
            }
            return;
        }
    }
}