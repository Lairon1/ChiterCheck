package mysouliscry.me.cheatercheck;

import java.util.HashMap;
import org.bukkit.Bukkit;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;

/**
 * @author MySoulIsCry
 *
 */
public class BossBarMeneger {
	
	private HashMap<Player, BossBar> suspectBossBar = new HashMap<>();
	private DataConfig config;
	public BossBarMeneger(DataConfig config) {
		this.config = config;
	}

	public BossBar getSuspectBossBar(Player suspect) {
		return suspectBossBar.get(suspect);
	}

	public void showSuspectBossBar(Player suspect) {
		int time = config.getTimeCheck();
		BossBar bar = Bukkit.createBossBar(config.getBossBarTitle().replace("%time%", timeToString(time)), config.getBossBarColor(), config.getBossBarStyle());
		bar.addPlayer(suspect);
		bar.setVisible(true);
		bar.setProgress(1);
		suspectBossBar.put(suspect, bar);
	}
	public void removeSuspectBossBar(Player suspect) {
		BossBar bar = suspectBossBar.get(suspect);
		bar.removeAll();
		suspectBossBar.remove(suspect);
	}
	private String timeToString(int seccond) {
		int min = seccond / 60 % 60, sec = seccond / 1 % 60;
		return String.format("%02d:%02d", min , sec);
	}
	

}
