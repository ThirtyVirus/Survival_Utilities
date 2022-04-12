package events;

import helpers.SurvivalMenuUtils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import thirtyvirus.uber.UberItems;
import thirtyvirus.uber.helpers.Utilities;

public class InventoryEvents implements Listener {

    @EventHandler
    private static void onInventoryClick(InventoryClickEvent event) {
        if (!event.getView().getTitle().equals("UberItems - Backpack")) return;

        if (UberItems.getItem("small_backpack").compare(event.getCurrentItem())) event.setCancelled(true);
        if (UberItems.getItem("big_backpack").compare(event.getCurrentItem())) event.setCancelled(true);
        if (SurvivalMenuUtils.emptySlot.equals(event.getCurrentItem())) event.setCancelled(true);

        // cancel all inventory swap actions to prevent putting a backpack inside itself
        if (event.getAction().name().contains("HOTBAR")) { event.setCancelled(true); }
    }

    @EventHandler
    private static void onInventoryClose(InventoryCloseEvent event) {
        // verify that the closed inventory is a backpack
        if (!event.getView().getTitle().equals("UberItems - Backpack")) return;

        Inventory backpackInv = event.getInventory();
        Inventory playerInv = event.getPlayer().getInventory();

        // store the backpack inventory in the itemstack, place back in player inventory
        ItemStack[] items = new ItemStack[backpackInv.getSize() - 9];
        for (int counter = 9; counter < backpackInv.getSize(); counter++) {
            items[counter - 9] = backpackInv.getItem(counter);
        }
        ItemStack backpack = backpackInv.getItem(4);
        if (backpack != null) {
            Utilities.saveCompactInventory(backpack, items);

            String UUID = Utilities.getStringFromItem(backpack, "UUID");
            boolean alreadyFinished = false;
            for (int counter = 0; counter < playerInv.getSize(); counter++) {
                ItemStack item = playerInv.getItem(counter);
                if (UUID.equals(Utilities.getStringFromItem(item, "UUID"))) {
                    // prevent duping backpacks, delete duplicates
                    if (alreadyFinished)
                        playerInv.setItem(counter, new ItemStack(Material.AIR));
                    else {
                        playerInv.setItem(counter, backpack.clone());
                        alreadyFinished = true;
                    }

                }
            }

            // backpack inventory closed and the backpack is no longer in the inventory (DUPE ATTEMPTED)
            if (!alreadyFinished) {
                Bukkit.getLogger().info("hi");
                for (ItemStack item : playerInv) {
                    // DELETE ALL ITEMS FROM MISSING BACKPACK
                    int fromBackpack = 0;
                    if (item != null) fromBackpack = Utilities.getIntFromItem(item, "frombackpack");
                    if (fromBackpack == 1) playerInv.remove(item);
                }
            }
            else {
                for (ItemStack item : playerInv) Utilities.storeIntInItem(item, 0, "frombackpack");
            }

            Player player = (Player)event.getPlayer();
            player.playSound(player.getLocation(), Sound.BLOCK_CHEST_CLOSE, 1, 1);
        }

    }
}
