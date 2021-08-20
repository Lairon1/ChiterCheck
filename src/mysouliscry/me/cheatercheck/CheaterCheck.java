package mysouliscry.me.cheatercheck;

import java.io.File;
import java.io.IOException;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * @author MySoulIsCry
 *
 */

public class CheaterCheck extends JavaPlugin{
	private File config = new File(getDataFolder() + File.separator + "config.yml");
	public File logsFile = new File(getDataFolder() + File.separator + "logs.yml");
	private FileConfiguration logs = YamlConfiguration.loadConfiguration(logsFile);
	private DataConfig dataConfig = new DataConfig(this);
	private CheckLogger logger = new CheckLogger(this);
	private BossBarMeneger bossBarMeneger = new BossBarMeneger(dataConfig);
	private Check check = new Check(dataConfig, logger, bossBarMeneger);
	
	
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
		Bukkit.getConsoleSender().sendMessage("§aCheaterCheck: Enable");	
		Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new RunnableCheck(dataConfig, check, bossBarMeneger), 20, 20);	

	}

	public FileConfiguration getLog() {return logs;}
	public Check getCheck() {return check;}
	public CheckLogger getCheckLogger() {return logger;}
	public BossBarMeneger getBossBarMeneger( ) { return bossBarMeneger; }

	@Override
	public void onDisable() {
		Bukkit.getConsoleSender().sendMessage("§cCheaterCheck: Disable");
	}




}
