package events;

import org.bukkit.entity.Player;
import org.bukkit.entity.Squid;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;
import thirtyvirus.uber.UberItems;
import thirtyvirus.uber.helpers.Utilities;

import java.util.Random;

public class UberEvent implements Listener {

    private Random rand = new Random();

    @EventHandler
    private void onKillSquid(EntityDeathEvent event) {

        // 10% chance to drop 1-3 calamari from squid kills
        if (event.getEntity() instanceof Squid) {
            int r = rand.nextInt(100) + 1;
            int d = rand.nextInt(3) + 1;
            if (r < 20)
                event.getEntity().getWorld().dropItemNaturally(event.getEntity().getLocation(), UberItems.getItem("calamari").makeItem(d));
        }
    }

    @EventHandler
    private void onPlayerDeath(PlayerDeathEvent event) {
        Player player = event.getEntity();

        ItemStack item = Utilities.searchFor(player.getInventory(), UberItems.getItem("soul_anchor"));
        if (item != null) {
            event.getDrops().remove(item);
            Utilities.storeStringInItem(item, Utilities.toLocString(event.getEntity().getLocation()), "deathloc");
            if (!event.getKeepInventory()) returnSoulAnchor(player, item);
        }
    }

    // loop until the player respawns to return the death anchor
    private void returnSoulAnchor(Player player, ItemStack item) {
        if (player.isDead()) Utilities.scheduleTask(()->returnSoulAnchor(player, item), 10);
        else player.getInventory().addItem(item);
    }
 }
