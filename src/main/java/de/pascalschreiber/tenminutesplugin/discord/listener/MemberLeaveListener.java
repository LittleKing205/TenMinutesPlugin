package de.pascalschreiber.tenminutesplugin.discord.listener;

import de.pascalschreiber.tenminutesplugin.discord.BotMain;
import org.javacord.api.event.server.member.ServerMemberLeaveEvent;
import org.javacord.api.listener.server.member.ServerMemberLeaveListener;

public class MemberLeaveListener implements ServerMemberLeaveListener {

    private BotMain bot;

    private MemberLeaveListener(BotMain bot) {
        this.bot = bot;
    }

    @Override
    public void onServerMemberLeave(ServerMemberLeaveEvent event) {
        
    }
}
