package mysouliscry.me.cheatercheck;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import mysouliscry.me.cheatercheck.managers.BossBarManager;
import mysouliscry.me.cheatercheck.managers.CheckManager;
import mysouliscry.me.cheatercheck.util.util;

/**
 * @author MySoulIsCry
 *
 */

public class RunnableCheck extends BukkitRunnable{

	private DataConfig config;
	private CheckManager check;
	private BossBarManager bossBarMeneger;
	
	public RunnableCheck(DataConfig config, CheckManager check, BossBarManager bossBarMeneger) {
		this.config = config;
		this.check = check;
		this.bossBarMeneger = bossBarMeneger;
	}

	@Override
	public void run() {
		if(check.getSuspects() != null) {
			for(Player suspect : check.getSuspects()) {
				if (check.getTimeCheck(suspect) < 0) {
					check.timeOut(suspect);
				}else {
					BossBar barSuspect = bossBarMeneger.getSuspectBossBar(suspect);
					BossBar barModerator = bossBarMeneger.getModeratorBossBar(suspect);
					int time = check.getTimeCheck(suspect);
					barModerator.setTitle(config.getBossBarTitleModerator().replace("%time%", util.timeToString(time)).replace("%suspect%", suspect.getName()));
					barSuspect.setTitle(config.getBossBarTitleSuspect().replace("%time%", util.timeToString(time)));
					
					double progressBar = ((time * 100f) / config.getTimeCheck()) / 100f;
					barSuspect.setProgress(progressBar);
					barModerator.setProgress(progressBar);
					suspect.sendTitle(config.getStartCheckTitleSuspect()[0], config.getStartCheckTitleSuspect()[1]);
					check.setTimeCheck(suspect, check.getTimeCheck(suspect) - 1);
				}
			}
		}
	}


}
