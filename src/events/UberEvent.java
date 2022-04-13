package events;

import org.bukkit.entity.Squid;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import thirtyvirus.uber.UberItems;

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
}
