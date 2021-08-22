package mysouliscry.me.cheatercheck.managers;

import java.util.HashMap;
import org.bukkit.Bukkit;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;

import mysouliscry.me.cheatercheck.DataConfig;
import mysouliscry.me.cheatercheck.util.util;

/**
 * @author MySoulIsCry
 *
 */
public class BossBarManager {
	
	private HashMap<Player, BossBar> suspectBossBar = new HashMap<>();
	private HashMap<Player, BossBar> moderatorBossBar = new HashMap<>();
	
	
	
	
	
	private DataConfig config;
	public BossBarManager(DataConfig config) {
		this.config = config;
	}

	public BossBar getSuspectBossBar(Player suspect) {
		return suspectBossBar.get(suspect);
	}
	public BossBar getModeratorBossBar(Player moderator) {
		return moderatorBossBar.get(moderator);
	}
	

	public void showSuspectBossBar(Player suspect, Player moderator) {
		int time = config.getTimeCheck();
		BossBar barSuspect = Bukkit.createBossBar(config.getBossBarTitleSuspect().replace("%time%", util.timeToString(time)), config.getBossBarColorSuspect(), config.getBossBarStyleSuspect());
		BossBar barModerator = Bukkit.createBossBar(config.getBossBarTitleModerator().replace("%time%", util.timeToString(time)).replace("%suspect%", suspect.getName()), config.getBossBarColorModerator(), config.getBossBarStyleModerator());
		barSuspect.addPlayer(suspect);
		barSuspect.setVisible(true);
		barSuspect.setProgress(1);
		suspectBossBar.put(suspect, barSuspect);
		barModerator.addPlayer(moderator);
		barModerator.setVisible(true);
		barModerator.setProgress(1);
		moderatorBossBar.put(moderator, barModerator);
		
	}
	public void removeSuspectBossBar(Player suspect, Player moderator) {
		getSuspectBossBar(suspect).removeAll();
		suspectBossBar.remove(suspect);
		getModeratorBossBar(moderator).removeAll();
		moderatorBossBar.remove(moderator);
	}
}
