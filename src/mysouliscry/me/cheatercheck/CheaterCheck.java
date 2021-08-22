package mysouliscry.me.cheatercheck;

import java.io.File;
import java.io.IOException;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import mysouliscry.me.cheatercheck.cmd.CMD;
import mysouliscry.me.cheatercheck.cmd.CheckTabComplite;
import mysouliscry.me.cheatercheck.managers.BossBarManager;
import mysouliscry.me.cheatercheck.managers.CheckManager;
import mysouliscry.me.cheatercheck.managers.LogManager;

/**
 * @author MySoulIsCry
 *
 */

public class CheaterCheck extends JavaPlugin{
	private File config = new File(getDataFolder() + File.separator + "config.yml");
	public File logsFile = new File(getDataFolder() + File.separator + "logs.yml");
	private FileConfiguration logs = YamlConfiguration.loadConfiguration(logsFile);
	private DataConfig dataConfig = new DataConfig(this);
	private LogManager logger = new LogManager(this);
	private BossBarManager bossBarMeneger = new BossBarManager(dataConfig);
	private CheckManager check = new CheckManager(dataConfig, logger, bossBarMeneger);
	
	
	@Override
	public void onEnable() {
		if(!config.exists()) {
			getConfig().options().copyDefaults(true);
			saveDefaultConfig();
		}
		if (!logsFile.exists()) {
			try {
				logs.save(logsFile);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		getCommand("check").setExecutor(new CMD(this, dataConfig, check, logger));	
		getCommand("check").setTabCompleter(new CheckTabComplite(check));;	
		Bukkit.getPluginManager().registerEvents(new Listeners(dataConfig, check), this);		
		Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new RunnableCheck(dataConfig, check, bossBarMeneger), 20, 20);	

	}

	public FileConfiguration getLog() {return logs;}
	public CheckManager getCheck() {return check;}
	public LogManager getCheckLogger() {return logger;}
	public BossBarManager getBossBarMeneger( ) { return bossBarMeneger; }

	@Override
	public void onDisable() {
		
		
		
	}




}
