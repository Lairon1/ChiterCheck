package mysouliscry.me.cheatercheck.managers;

import java.util.HashMap;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import mysouliscry.me.cheatercheck.DataConfig;
import mysouliscry.me.cheatercheck.API.*;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.chat.ClickEvent.Action;

/**
 * @author MySoulIsCry
 *
 */

public class CheckManager {
	private DataConfig config;
	private LogManager logger;
	private BossBarManager bossBarMeneger;
	
	private HashMap<Player, Player> suspectModeratorMap = new HashMap<Player, Player>();
	private HashMap<Player, Integer> suspectTimeMap = new HashMap<>();

	public CheckManager(DataConfig config, LogManager logger, BossBarManager bossBarMeneger) {
		this.config = config;
		this.logger = logger;
		this.bossBarMeneger = bossBarMeneger;
	}
	public boolean isChecks(Player suspect) {	
		return suspectTimeMap.containsKey(suspect);
	}
	public void start(Player suspect, Player moderator) {
		PlayerCheckStartEvent event = new PlayerCheckStartEvent(suspect, moderator);		
		Bukkit.getPluginManager().callEvent(event);
		if(event.isCancelled()) { return; }
		suspect = event.getSuspect();
		moderator = event.getModerator();
		suspectTimeMap.put(suspect, config.getTimeCheck());
		suspectModeratorMap.put(suspect, moderator);
		bossBarMeneger.showSuspectBossBar(suspect, moderator);
		moderator.sendMessage(config.getStartCheckMessageModer().replace("%suspect%", suspect.getName()));
		suspect.sendMessage(config.getStartCheckMessageSuspect().replace("%moderator%", moderator.getName()));
        TextComponent confess = new TextComponent(config.getButtonsSuspect()[1]);
        confess.setClickEvent(new ClickEvent(Action.RUN_COMMAND, "/check confess"));
        TextComponent connect = new TextComponent(config.getButtonsSuspect()[0] + " ");
        connect.setClickEvent(new ClickEvent(Action.SUGGEST_COMMAND, "/check connect "));
        TextComponent comp = new TextComponent(connect , confess); 
        suspect.spigot().sendMessage(comp);
		logger.addLog(moderator.getName(), moderator.getName(), suspect.getName(), "¬€«¬¿À Õ¿ œ–Œ¬≈– ”");
		logger.addLog(suspect.getName(), moderator.getName(), suspect.getName(), "¡€À ¬€«¬¿Õ Õ¿ œ–Œ¬≈– ”");
	}
	public void stop(Player suspect, Player moderator) {
		PlayerCheckStopEvent event = new PlayerCheckStopEvent(suspect, moderator);		
		Bukkit.getPluginManager().callEvent(event);
		if(event.isCancelled()) { return; }
		suspect = event.getSuspect();
		moderator = event.getModerator();
		bossBarMeneger.removeSuspectBossBar(suspect, moderator);
		suspectModeratorMap.remove(suspect);
		suspectTimeMap.remove(suspect);	
		suspect.sendMessage(config.getStopCheckMessageSuspect());
		moderator.sendMessage(config.getStopCheckMessageModer().replace("%suspect%", suspect.getName()));	
		suspect.sendTitle(config.getStopCheckTitleSuspect()[0], config.getStopCheckTitleSuspect()[1]);
		logger.addLog(moderator.getName(), moderator.getName(), suspect.getName(), "«¿¬≈–ÿ»À œ–Œ¬≈– ”");
		logger.addLog(suspect.getName(), moderator.getName(), suspect.getName(), "Œ“ Àﬁ◊»À» œ–Œ¬≈– ”");
	}
	public void confess(Player suspect) {
		PlayerCheckConfessEvent event = new PlayerCheckConfessEvent(suspect, getModerator(suspect));
		Bukkit.getPluginManager().callEvent(event);
		if(event.isCancelled()) { return; }
		suspect = event.getSuspect();
		setModerator(suspect, event.getModerator());
		logger.addLog(suspect.getName(), suspectModeratorMap.get(suspect).getName(), suspect.getName(), "œ–»«Õ¿À—ﬂ ¬ ◊»“¿’");
		suspectModeratorMap.get(suspect).sendMessage(config.getSuspectConfessModeratorMessage().replace("%suspect%", suspect.getName()));
		bossBarMeneger.removeSuspectBossBar(suspect, suspectModeratorMap.get(suspect));
		suspectModeratorMap.remove(suspect);
		suspectTimeMap.remove(suspect);
		for(String s : config.getCommandOnSuspectConfess()) {
			Bukkit.dispatchCommand(Bukkit.getConsoleSender(), s.replace("%suspect%", suspect.getName()));
		}	
	}
	public void suspectLeave(Player suspect) {
		PlayerCheckQuitEvent event = new PlayerCheckQuitEvent(suspect, getModerator(suspect));
		Bukkit.getPluginManager().callEvent(event);
		if(event.isCancelled()) { return; }
		suspect = event.getSuspect();
		setModerator(suspect, event.getModerator());
		logger.addLog(suspect.getName(), suspectModeratorMap.get(suspect).getName(), suspect.getName(), "Œ“ ¿«¿À—ﬂ Œ“ œ–Œ¬≈– »");	
		suspectModeratorMap.get(suspect).sendMessage(config.getLeaveSuspectModeratorMessage().replace("%suspect%", suspect.getName()));
		bossBarMeneger.removeSuspectBossBar(suspect, suspectModeratorMap.get(suspect));	
		suspectModeratorMap.remove(suspect);
		suspectTimeMap.remove(suspect);	
		for(String s : config.getCommandOnSuspectExit()) {
			Bukkit.dispatchCommand(Bukkit.getConsoleSender(), s.replace("%suspect%", suspect.getName()));
		}	
	}
	public void connect(Player suspect, String connect) {
		Player moderator = getModerator(suspect);
        TextComponent connectTC = new TextComponent("ßa" + connect);
        connectTC.setClickEvent(new ClickEvent(Action.SUGGEST_COMMAND, connect));
		moderator.sendMessage(config.getModeratorConnectMessage().replace("%suspect%", suspect.getName()));
		moderator.spigot().sendMessage(connectTC);
	}
	public void timeOut(Player suspect) {
		logger.addLog(suspect.getName(), suspectModeratorMap.get(suspect).getName(), suspect.getName(), " ŒÕ◊»ÀŒ—‹ ¬–≈Ãﬂ");	
		bossBarMeneger.removeSuspectBossBar(suspect, suspectModeratorMap.get(suspect));
		suspect.sendMessage(config.getTimeOutSuspectMessage());
        TextComponent punish = new TextComponent(config.getButtonsModerator()[1]); 
        punish.setClickEvent(new ClickEvent(Action.RUN_COMMAND, config.getButtonModeratorPunishCMD().replace("%suspect%", suspect.getName())));
        TextComponent runAgain = new TextComponent(config.getButtonsModerator()[0] + " ");
        runAgain.setClickEvent(new ClickEvent(Action.RUN_COMMAND, "/check start " + suspect.getName()));
        TextComponent comp = new TextComponent(runAgain , punish);
        suspectModeratorMap.get(suspect).sendMessage(config.getTimeOutSuspectModeratorMessage().replace("%suspect%", suspect.getName()));
        suspectModeratorMap.get(suspect).spigot().sendMessage(comp);
		suspectModeratorMap.remove(suspect);
		suspectTimeMap.remove(suspect);
	}
	
	
	
	public Player getModerator(Player suspect) {
		return suspectModeratorMap.get(suspect);
	}
	public int getTimeCheck(Player suspect) {
		return suspectTimeMap.get(suspect);	
	}
	public void setModerator(Player suspect, Player moderator) {
		suspectModeratorMap.replace(suspect, moderator);
	}
	public void setTimeCheck(Player suspect, int time) {
		suspectTimeMap.replace(suspect, time);
	}
	public Player[] getSuspects() {
		if(suspectModeratorMap.size() !=0) return  suspectModeratorMap.keySet().toArray(new Player[0]);
		return null;
	}
	public Player[] getModerators() {
		if(suspectModeratorMap.size() !=0) return  suspectModeratorMap.values().toArray(new Player[0]);
		return null;
	}
}
