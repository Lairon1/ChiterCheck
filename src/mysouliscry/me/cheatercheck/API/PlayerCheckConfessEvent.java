package mysouliscry.me.cheatercheck.API;

import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class PlayerCheckConfessEvent extends Event implements Cancellable{
	private Player suspect;
	private Player moderator;
	private boolean isCancelled = false;
	public static final HandlerList handlers = new HandlerList();
	
	
	public PlayerCheckConfessEvent(Player suspect, Player moderator) {
		this.moderator = moderator;
		this.suspect = suspect;
	}
	@Override
	public boolean isCancelled() {
		return isCancelled;
	}
	@Override
	public void setCancelled(boolean arg0) {
		this.isCancelled = arg0;	
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
