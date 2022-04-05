import events.ProjectileHit;
import items.*;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import thirtyvirus.uber.UberItems;
import thirtyvirus.uber.helpers.*;

import java.util.Arrays;
import java.util.Collections;

public class SurvivalUtilities extends JavaPlugin {

    public void onEnable() {

        // enforce UberItems dependancy
        if (Bukkit.getPluginManager().getPlugin("UberItems") == null) {
            this.getLogger().severe("UberItems Addons requires UberItems! disabled because UberItems dependency not found");
            Bukkit.getPluginManager().disablePlugin(this);
            return;
        }

        // register events and UberItems
        registerEvents();
        registerUberMaterials();
        registerUberItems();

        // post confirmation in chat
        getLogger().info(getDescription().getName() + " V: " + getDescription().getVersion() + " has been enabled");
    }
    public void onDisable() {
        // posts exit message in chat
        getLogger().info(getDescription().getName() + " V: " + getDescription().getVersion() + " has been disabled");
    }
    private void registerEvents() {
        getServer().getPluginManager().registerEvents(new ProjectileHit(), this);
    }

    private void registerUberItems() {
        //UberItems.putItem("empty_item", new empty_item(Material.DIAMOND, "Empty UberItem", UberRarity.COMMON,
        //        false, false, false, Collections.emptyList(), null));

        // TODO add Atlas

        UberItems.putItem("pillow", new pillow(Utilities.getSkull("http://textures.minecraft.net/texture/3088895e90ea5899499e32a3176ecdacd88656cba734d1345175fd11f2844893"), "Pillow",
                UberRarity.COMMON, false, false, true,
                Collections.singletonList(new UberAbility("Well Rested", AbilityType.NONE, "When sleeping in a bed while holding this pillow, you regain health. Cure any active poison effects.", 300)),
                new UberCraftingRecipe(Arrays.asList(
                        new ItemStack(Material.STRING, 8),
                        new ItemStack(Material.FEATHER, 16),
                        new ItemStack(Material.STRING, 8),
                        new ItemStack(Material.FEATHER, 16),
                        new ItemStack(Material.FEATHER, 16),
                        new ItemStack(Material.FEATHER, 16),
                        new ItemStack(Material.STRING, 8),
                        new ItemStack(Material.FEATHER, 16),
                        new ItemStack(Material.STRING, 8)), false, 1)));

        UberItems.putItem("omlette", new omlette(Utilities.getSkull("http://textures.minecraft.net/texture/4e947fe09ebb7e7b3769bb8a5da5cb734646b2c7973ab6a35b6627a7dc1245d1"), "Omlette",
                UberRarity.UNCOMMON, true, true, false, Collections.emptyList(),
                new UberCraftingRecipe(Arrays.asList(
                        new ItemStack(Material.AIR),
                        new ItemStack(Material.EGG, 4),
                        new ItemStack(Material.AIR),
                        new ItemStack(Material.EGG, 4),
                        UberItems.getMaterial("flammable_substance").makeItem(1),
                        new ItemStack(Material.EGG, 4),
                        new ItemStack(Material.AIR),
                        new ItemStack(Material.EGG, 4),
                        new ItemStack(Material.AIR)), false, 4)));

        UberItems.putItem("experience_bottle", new experience_bottle(Material.EXPERIENCE_BOTTLE, "Experience Bottle", UberRarity.COMMON,
                true, true, false, Collections.emptyList(),
                new UberCraftingRecipe(Arrays.asList(
                        new ItemStack(Material.AIR),
                        new ItemStack(Material.LAPIS_LAZULI),
                        new ItemStack(Material.AIR),
                        new ItemStack(Material.LAPIS_LAZULI),
                        new ItemStack(Material.GLASS_BOTTLE),
                        new ItemStack(Material.LAPIS_LAZULI),
                        new ItemStack(Material.LAPIS_LAZULI),
                        new ItemStack(Material.LAPIS_LAZULI),
                        new ItemStack(Material.LAPIS_LAZULI)), false, 1)));

        // TODO add way to convert enchantments into experience bottles?

        // TODO add livna (reverse anvil)

        // TODO add backpack

        // TODO add double backpack

        // TODO add foods on a stick

        UberItems.putItem("homemade_portal_frame", new homemade_portal_frame(Material.END_PORTAL_FRAME, "Homemade Portal Frame",
                UberRarity.EPIC, true, true, false,
                Collections.emptyList(),
                new UberCraftingRecipe(Arrays.asList(
                        new ItemStack(Material.END_STONE, 12),
                        new ItemStack(Material.END_STONE, 12),
                        new ItemStack(Material.END_STONE, 12),
                        new ItemStack(Material.ENDER_PEARL, 16),
                        new ItemStack(Material.NETHER_STAR),
                        new ItemStack(Material.ENDER_PEARL, 16),
                        new ItemStack(Material.END_STONE, 12),
                        new ItemStack(Material.END_STONE, 12),
                        new ItemStack(Material.END_STONE, 12)), false, 12)));

        UberItems.putItem("crystal_ball", new crystal_ball(Utilities.getSkull("http://textures.minecraft.net/texture/5a5f29a76d1f91c165f63baac048670e7b1d37ce785a4d9c21d8c3a177b5"), "Crystal Ball",
                UberRarity.EPIC, false, false, false,
                Arrays.asList(new UberAbility("storage", AbilityType.NONE, "Store Experience in this orb")),
                new UberCraftingRecipe(Arrays.asList(
                        new ItemStack(Material.GLASS),
                        new ItemStack(Material.SEA_LANTERN),
                        new ItemStack(Material.GLASS),
                        new ItemStack(Material.SEA_LANTERN),
                        new ItemStack(Material.BEACON),
                        new ItemStack(Material.SEA_LANTERN),
                        new ItemStack(Material.GLASS),
                        new ItemStack(Material.SEA_LANTERN),
                        new ItemStack(Material.GLASS)), false, 1)));

    }
    private void registerUberMaterials() {
        //UberItems.putMaterial("thumbnail", new UberMaterial(Material.GOLDEN_APPLE,
        //        "Titan Apple", UberRarity.MYTHIC, true, false, false,
        //        "" + ChatColor.GRAY + ChatColor.ITALIC + "look at what they /newline " + ChatColor.ITALIC + "need to mimic a /newline " + ChatColor.ITALIC + "fraction of our power",
        //        null));
    }
}