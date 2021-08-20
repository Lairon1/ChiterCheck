package mysouliscry.me.cheatercheck.API;

import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class PlayerCheckQuitEvent extends Event implements Cancellable{
	private Player suspect;
	private Player moderator;
	private boolean is—ancelled = false;
	public static final HandlerList handlers = new HandlerList();
	
	
	public PlayerCheckQuitEvent(Player suspect, Player moderator) {
		this.moderator = moderator;
		this.suspect = suspect;
	}
	@Override
	public boolean isCancelled() {
		return is—ancelled;
	}
	@Override
	public void setCancelled(boolean arg0) {
		this.is—ancelled = arg0;	
	}
	@Override
	public HandlerList getHandlers() {
		return handlers;
	}
	public Player getModerator() {
		return moderator;
	}
	public void setModerator(Player moderator) {
		this.moderator = moderator;
	}
	public Player getSuspect() {
		return suspect;
	}
	public void setSuspect(Player suspect) {
		this.suspect = suspect;
	}
    public static HandlerList getHandlerList() {
        return handlers;
    }

}
