package mysouliscry.me.cheatercheck;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

/**
 * @author MySoulIsCry
 *
 */

public class RunnableCheck extends BukkitRunnable{

	private DataConfig config;
	private Check check;
	private BossBarMeneger bossBarMeneger;
	
	public RunnableCheck(DataConfig config, Check check, BossBarMeneger bossBarMeneger) {
		this.config = config;
		this.check = check;
		this.bossBarMeneger = bossBarMeneger;
	}

	@Override
	public void run() {
		if(check.getSuspects() != null) {
			for(Player suspect : check.getSuspects()) {
				if (check.getTimeCheck(suspect) < 0) {
					check.suspectLeave(suspect);
				}else {
					BossBar bar = bossBarMeneger.getSuspectBossBar(suspect);
					int time = check.getTimeCheck(suspect);
					bar.setTitle(config.getBossBarTitle().replace("%time%", timeToString(time)));
					double progressBar = ((time * 100f) / config.getTimeCheck()) / 100f;
					bar.setProgress(progressBar);
					suspect.sendTitle(config.getStartCheckTitleSuspect()[0], config.getStartCheckTitleSuspect()[1]);
					check.setTimeCheck(suspect, check.getTimeCheck(suspect) - 1);
				}
			}
		}
	}
	private String timeToString(int seccond) {
		int min = seccond / 60 % 60, sec = seccond / 1 % 60;
		return String.format("%02d:%02d", min , sec);
	}

}
