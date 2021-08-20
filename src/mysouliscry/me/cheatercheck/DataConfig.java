package mysouliscry.me.cheatercheck;


import java.util.ArrayList;

import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ClickEvent.Action;
import net.md_5.bungee.api.chat.TextComponent;

/**
 * @author MySoulIsCry
 *
 */

public class DataConfig {
	private CheaterCheck main;
	public DataConfig(CheaterCheck main) {
		this.main = main;
	}
	public String getNoPermMessage() {
		String noPermMessage = main.getConfig().getString("Messages.NoPermMessage");
		return ChatColor.translateAlternateColorCodes('&', noPermMessage);
	}
	public String getSuspectNoOnlineMessage() {
		String suspectNoOnlineMessage = main.getConfig().getString("Messages.SuspectNoOnlineMessage");
		return ChatColor.translateAlternateColorCodes('&', suspectNoOnlineMessage);
	}
	public String getSuspectIsChecksMessage() {
		String suspeckIsChecksMessage = main.getConfig().getString("Messages.SuspeckIsChecks");
		return ChatColor.translateAlternateColorCodes('&', suspeckIsChecksMessage);
	}
	public String getStartCheckMessageModer() {
		String startCheckMessage = main.getConfig().getString("Messages.StartCheckMessageModer");
		return ChatColor.translateAlternateColorCodes('&', startCheckMessage);	
	}
	public String getStartCheckMessageSuspect() {
		String startCheckMessageSuspect = ChatColor.translateAlternateColorCodes('&', main.getConfig().getString("Messages.StartCheckMessageSuspect"));

        
		return startCheckMessageSuspect;
	}
	public String[] getStartCheckTitleSuspect() {
		String startCheckTitleSuspect = main.getConfig().getString("Titles.StartCheckTitleSuspect");
		String startCheckSubTitleSuspect = main.getConfig().getString("Titles.StartCheckSubTitleSuspect");
		String[] title = new String[2];
		title[0] = ChatColor.translateAlternateColorCodes('&', startCheckTitleSuspect);		
		title[1] = ChatColor.translateAlternateColorCodes('&', startCheckSubTitleSuspect);	
		return title;
	}
	public String getSuspectNoIsCheckMessage() {
		String suspectNoIsChecksMessage = main.getConfig().getString("Messages.SuspectNoIsChecksMessage");
		return ChatColor.translateAlternateColorCodes('&', suspectNoIsChecksMessage);
	}
	public String getStopCheckMessageModer() {
		String stopCheckMessage = main.getConfig().getString("Messages.StopCheckMessageModer");
		return ChatColor.translateAlternateColorCodes('&', stopCheckMessage);	
	}
	public String getStopCheckMessageSuspect() {
		String stopCheckMessageSuspect = main.getConfig().getString("Messages.StopCheckMessageSuspect");
		return ChatColor.translateAlternateColorCodes('&', stopCheckMessageSuspect);
	}
	public String[] getStopCheckTitleSuspect() {
		String stopCheckTitleSuspect = main.getConfig().getString("Titles.StopCheckTitleSuspect");
		String stopCheckSubTitleSuspect = main.getConfig().getString("Titles.StopCheckSubTitleSuspect");
		String[] title = new String[2];
		title[0] = ChatColor.translateAlternateColorCodes('&', stopCheckTitleSuspect);		
		title[1] = ChatColor.translateAlternateColorCodes('&', stopCheckSubTitleSuspect);	
		return title;
	}
	public String getCommandOnSuspectExit() {
		return main.getConfig().getString("CommandOnSuspectExit");
	}
	public ArrayList<String> getBlockCommand(){
		ArrayList listCommand = (ArrayList) main.getConfig().getList("BlockCommand");
		return listCommand;
	}
	public String getBlockCommangMessage() {
		String blockCommangMessage = main.getConfig().getString("Messages.BlockCommangMessage");
		return ChatColor.translateAlternateColorCodes('&', blockCommangMessage);
	}
	public String[] getButtons(){
		String connectButton = main.getConfig().getString("Buttons.Connect");
		String confess = main.getConfig().getString("Buttons.Confess");
		String[] buttons = new String[2];
		buttons[0] = ChatColor.translateAlternateColorCodes('&', connectButton);
		buttons[1] = ChatColor.translateAlternateColorCodes('&', confess);
		return buttons;
	}
	public String getImmunityMessage() {
		String immunityMessage = main.getConfig().getString("Messages.ImmunityMessage");
		return ChatColor.translateAlternateColorCodes('&', immunityMessage);
	}
	public String getCheckByMyselfMessage() {
		String CheckByMyselfMessage = main.getConfig().getString("Messages.CheckByMyselfMessage");
		return ChatColor.translateAlternateColorCodes('&', CheckByMyselfMessage);
	}
	public String getCommandOnSuspectConfess() {
		return main.getConfig().getString("CommandOnSuspectConfess");
	}
	public String getSuspectConfessNoInCheckMessage() {
		String suspectConfessNoInCheckMessage = main.getConfig().getString("Messages.SuspectConfessNoInCheckMessage");
		return ChatColor.translateAlternateColorCodes('&', suspectConfessNoInCheckMessage);
	}
	public String getLeaveSuspectModeratorMessage() {
		String leaveSuspectModeratorMessage = main.getConfig().getString("Messages.LeaveSuspectModeratorMessage");
		return ChatColor.translateAlternateColorCodes('&', leaveSuspectModeratorMessage);
	}
	public String getSuspectConfessModeratorMessage() {
		String leaveSuspectModeratorMessage = main.getConfig().getString("Messages.SuspectConfessModeratorMessage");
		return ChatColor.translateAlternateColorCodes('&', leaveSuspectModeratorMessage);
	}
	public String getBossBarTitle() {
		String BossBarTitle = main.getConfig().getString("BossBar.Title");
		return ChatColor.translateAlternateColorCodes('&', BossBarTitle);
	}
	public BarColor getBossBarColor() {
		String BossBarColor = main.getConfig().getString("BossBar.Color");
		return BarColor.valueOf(BossBarColor);
	}
	public BarStyle getBossBarStyle() {
		String BossBarStyle = main.getConfig().getString("BossBar.Style");
		return BarStyle.valueOf(BossBarStyle);
	}
	public String getModeratorConnectMessage() {
		String moderatorConnectMessage = main.getConfig().getString("Messages.ModeratorConnectMessage");
		return ChatColor.translateAlternateColorCodes('&', moderatorConnectMessage);
	}
	public String getSuspectConnectMessage() {
		String suspectConnectMessage = main.getConfig().getString("Messages.SuspectConnectMessage");
		return ChatColor.translateAlternateColorCodes('&', suspectConnectMessage);
	}
	public int getTimeCheck() { return main.getConfig().getInt("TimeToCheck"); }
	public boolean getGiveDamageInCheck() { return main.getConfig().getBoolean("GiveDamageInCheck"); }
	public boolean getReceiveDamageInCheck() { return main.getConfig().getBoolean("ReceiveDamageInCheck"); }
	public boolean getMoveInCheck() { return main.getConfig().getBoolean("MoveInCheck"); }
	public boolean getQuitInCheck() { return main.getConfig().getBoolean("QuitInCheck"); }
	public boolean getBlockBreakInCheck() { return main.getConfig().getBoolean("BlockBreakInCheck"); }
	public boolean getBlockPlaceInCheck() { return main.getConfig().getBoolean("BlockPlaceInCheck"); }
	public boolean getDropItemInCheck() { return main.getConfig().getBoolean("DropItemInCheck"); }
	public boolean getPickupItemInCheck() { return main.getConfig().getBoolean("PickupItemInCheck"); }
}
































