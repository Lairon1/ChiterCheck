package mysouliscry.me.cheatercheck;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * @author MySoulIsCry
 *
 */

public class CMD implements CommandExecutor {

	private CheaterCheck main;
	private DataConfig config;
	private Check check;
	private CheckLogger logger;

	public CMD(CheaterCheck main, DataConfig config, Check check, CheckLogger logger) {
		this.main = main;
		this.config = config;
		this.check = check;
		this.logger = logger;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command arg1, String arg2, String[] args) {
		if(sender instanceof Player) {			
			Player player = (Player) sender;
			if(args.length == 1) {
				if(args[0].equalsIgnoreCase("confess")) {
					if(check.isChecks(player)) {
						check.confess(player);
						return false;
					}else {
						player.sendMessage(config.getSuspectConfessNoInCheckMessage());
						return false;
					}	
				}
				if(args[0].equalsIgnoreCase("reload")) {
					if(player.hasPermission("CheaterCheck.CMD.reload")) {
						main.reloadConfig();
						player.sendMessage("§aCheaterCheck: Конфиг перезагружен!");
						return false;
					}else {
						player.sendMessage(config.getNoPermMessage());
						return false;
					}
				}
			}
			if(args.length >= 2) {
				if(args[0].equalsIgnoreCase("connect")) {
					if(check.isChecks(player)) {
						StringBuilder connectMess = new StringBuilder();

						for(int i = 1; i != args.length; i++) {
							connectMess.append(args[i] + " ");
						}

						check.connect(player, connectMess.toString());
						player.sendMessage(config.getSuspectConnectMessage());
						return false;
					}else {
						player.sendMessage(config.getSuspectConfessNoInCheckMessage());
						return false;
					}
				}
				if(args[0].equalsIgnoreCase("getLoggs")) {
					if(player.hasPermission("CheaterCheck.CMD.getLoggs")) {
						player.sendMessage("§7[§aCC§7] Логи для игрока §a" + args[1]);
						player.sendMessage("§a====");
						for(String log : logger.getLoggs(args[1])) {
							player.sendMessage("§c"+log);
							player.sendMessage("§a====");
						}	
					}
				}
				if(args[0].equalsIgnoreCase("start")) {
					if(player.hasPermission("CheaterCheck.CMD.start")) {
						Player suspect = Bukkit.getPlayer(args[1]);
						if(suspect != null) {
							if(player != suspect) {
								if(!suspect.hasPermission("CheaterCheck.immunity")) {
									if(!check.isChecks(suspect)) {
										check.start(suspect, player);
										return false;
									}else {
										player.sendMessage(config.getSuspectIsChecksMessage().replace("%suspect%", args[1]));
									}
								}else {
									player.sendMessage(config.getImmunityMessage().replace("%suspect%", suspect.getName()));
								}
							}else {
								player.sendMessage(config.getCheckByMyselfMessage());
							}
						}else {
							player.sendMessage(config.getSuspectNoOnlineMessage().replace("%suspect%", args[1]));
						}
					}else {
						player.sendMessage(config.getNoPermMessage());
					}
				}else if (args[0].equalsIgnoreCase("stop")) {
					if(player.hasPermission("CheaterCheck.CMD.stop")) {
						Player suspect = Bukkit.getPlayer(args[1]);
						if(suspect != null) {							
							if(check.isChecks(suspect)) {
								check.stop(suspect, player);
								return false;
							}else {
								player.sendMessage(config.getSuspectNoIsCheckMessage().replace("%suspect%", args[1]));
							}
						}else {
							player.sendMessage(config.getSuspectNoOnlineMessage().replace("%suspect%", args[1]));
						}
					}else {
						player.sendMessage(config.getNoPermMessage());
					}
				}
			}else {
				player.sendMessage("§7[§aCC§7] Неправильное использование комманды §a/check§7!\n"
						+ "Доступные вам комманды:");
				if(check.isChecks(player)) {
					player.sendMessage("§a/check connect <Данные для связи> §7- Отправить модератору данные для связи с вами во время проверки.");
					player.sendMessage("§a/check confess §7- Признатся в читах во время проверки.");
				}
				if(player.hasPermission("CheaterCheck.CMD.start")) player.sendMessage("§a/check start <nick> §7- Запустить проверку для игрока.");
				if(player.hasPermission("CheaterCheck.CMD.stop")) player.sendMessage("§a/check stop <nick> §7- Остановить проверку для игрока.");
				if(player.hasPermission("CheaterCheck.CMD.reload")) player.sendMessage("§a/check reload §7- Перезагрузить конфиг плагина.");
				if(player.hasPermission("CheaterCheck.CMD.getLoggs")) player.sendMessage("§a/check getLoggs <nick> §7- Получить список логов по никнейму.");	
			}

		}else {
			sender.sendMessage("§cЭту комманду может использовать только игрок!");
		}
		return false;
	}

}
