package mysouliscry.me.cheatercheck;

import java.util.ArrayList;
import java.util.List;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

/**
 * @author MySoulIsCry
 *
 */


public class CheckTabComplite implements TabCompleter{
	Check check;
	public CheckTabComplite(Check check) {
		this.check = check;
	}


	@Override
	public List<String> onTabComplete(CommandSender sender, Command cmd, String slias, String[] args) {
		List<String> list = new ArrayList<String>();
		if(cmd.getName().equalsIgnoreCase("check")) {	
			if(args.length == 1) {
				if(check.isChecks((Player) sender)) {
					list.add("confess");
					list.add("connect");
				}
				if(sender.hasPermission("CheaterCheck.CMD.start")) list.add("start");
				if(sender.hasPermission("CheaterCheck.CMD.stop")) list.add("stop");	
				if(sender.hasPermission("CheaterCheck.CMD.reload")) list.add("reload");
				if(sender.hasPermission("CheaterCheck.CMD.getLoggs")) list.add("getLoggs");

			}
			if(args.length == 2) {
				for(Player player : Bukkit.getOnlinePlayers()) {
					list.add(player.getName());
				}
			}
		}	
		return list;
	}
}
