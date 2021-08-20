package mysouliscry.me.cheatercheck;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.event.player.PlayerQuitEvent;

/**
 * @author MySoulIsCry
 *
 */

public class Listeners implements Listener {

	private DataConfig config;
	private Check check;
	public Listeners(DataConfig config, Check check) {
		this.config = config;
		this.check = check;
	}
	
	@EventHandler(priority = EventPriority.MONITOR)
	public void EntityDamageEvent(EntityDamageEvent e) {
		if(e.getEntity() instanceof Player) {			
			Player player = (Player) e.getEntity();
			if(check.isChecks(player) && config.getReceiveDamageInCheck()) {
				e.setCancelled(true);
			}
		}
	}
	@EventHandler(priority = EventPriority.MONITOR)
	public void PlayerMoveEvent(PlayerMoveEvent e) {
		if(check.isChecks(e.getPlayer()) && config.getMoveInCheck()) {
			e.setCancelled(true);
			Location from = e.getFrom();
			Location to = e.getTo();
			if(to.getX() == from.getX() && to.getZ() == from.getZ() && to.getY() < from.getY()) e.setCancelled(false);
		}
	}
	@EventHandler
	public void PlayerQuitEvent(PlayerQuitEvent e) {
		if(check.isChecks(e.getPlayer()) && config.getQuitInCheck() ) {
			check.suspectLeave(e.getPlayer());
		}
	}
	@EventHandler(ignoreCancelled = true)
	public void  PlayerCommandPreprocessEvent(PlayerCommandPreprocessEvent e) {
		if(check.isChecks(e.getPlayer())) {
			if(!config.getBlockCommand().contains(e.getMessage().split(" ")[0])) {
				e.setCancelled(true);
				e.getPlayer().sendMessage(config.getBlockCommangMessage());
			}
		}

	}
	@EventHandler(ignoreCancelled = true)
	public void  BlockBreakEvent(BlockBreakEvent e) {
		if(check.isChecks(e.getPlayer()) && config.getBlockBreakInCheck()) e.setCancelled(true);
	}
	@EventHandler(ignoreCancelled = true)
	public void  BlockPlaceEvent(BlockPlaceEvent e) {
		if(check.isChecks(e.getPlayer()) && config.getBlockPlaceInCheck()) e.setCancelled(true);
	}
	@EventHandler(priority = EventPriority.MONITOR)
	public void EntityDamageByEntityEvent(EntityDamageByEntityEvent e) {
		if(e.getDamager() instanceof Player) if(check.isChecks((Player)e.getDamager()) && config.getGiveDamageInCheck()) e.setCancelled(true);
	}
	@EventHandler
	public void PlayerDropItemEvent(PlayerDropItemEvent e) {
		if(check.isChecks(e.getPlayer()) && config.getDropItemInCheck()) e.setCancelled(true);
	}
	@EventHandler
	public void PlayerPickupItemEvent(PlayerPickupItemEvent e) {
		if(check.isChecks(e.getPlayer()) && config.getPickupItemInCheck()) e.setCancelled(true);
	}
}
