package org.sattdev.mukscorelegacy;

import io.github.thatkawaiisam.assemble.Assemble;
import io.github.thatkawaiisam.assemble.AssembleAdapter;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public final class MukScoreLegacy extends JavaPlugin {

    @Override
    public void onEnable() {
        if (!Bukkit.getPluginManager().isPluginEnabled("PlaceholderAPI")) {
            Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "PlaceholderAPI nao encontrado!");
            Bukkit.getPluginManager().disablePlugin(this);
            return;
        }
        saveDefaultConfig();

        if (!Bukkit.getPluginManager().isPluginEnabled("PlaceholderAPI")) {
            Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "PlaceholderAPI nao encontrado!");
            Bukkit.getPluginManager().disablePlugin(this);
            return;
        }

        Assemble assemble = new Assemble(this, new AssembleAdapter() {
            @Override
            public String getTitle(Player player) {
                String rawTitle = getConfig().getString("scoreboard.title", "&5&lMukPlugins");
                return TextColor.colorir(PlaceholderAPI.setPlaceholders(player, rawTitle));
            }

            @Override
            public List<String> getLines(Player player) {
                List<String> rawLines = getConfig().getStringList("scoreboard.lines");
                List<String> finalLines = new ArrayList<>();

                for (String line : rawLines) {
                    String parsed = PlaceholderAPI.setPlaceholders(player, line);
                    finalLines.add(TextColor.colorir(parsed));
                }

                return finalLines;
            }
        });
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
