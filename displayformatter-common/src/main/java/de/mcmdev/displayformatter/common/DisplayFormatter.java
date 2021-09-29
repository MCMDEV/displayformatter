package de.mcmdev.displayformatter.common;

import de.mcmdev.displayformatter.common.display.DisplayDataProvider;
import de.mcmdev.displayformatter.common.permissionsource.PermissionSource;
import de.mcmdev.displayformatter.common.permissionsource.impl.LuckpermsPermissionSource;
import de.mcmdev.displayformatter.common.platform.Configuration;
import de.mcmdev.displayformatter.common.platform.Platform;
import de.mcmdev.displayformatter.common.team.TeamManager;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class DisplayFormatter<P> {

    private final Platform<P> platform;

    private TeamManager<P> teamManager;
    private Configuration mainConfiguration;
    private PermissionSource<P> permissionSource;
    private DisplayDataProvider<P> displayDataProvider;

    public void load() {
        this.mainConfiguration = this.platform.getConfiguration("config.yml");

        loadPermissionSourceFromConfig();
        this.displayDataProvider = new DisplayDataProvider<>(this);
        this.teamManager = new TeamManager<>(this.platform, this.displayDataProvider);
    }

    public void unload() {
        this.teamManager = null;
        this.permissionSource = null;
        this.mainConfiguration = null;
        this.displayDataProvider = null;
    }

    private void loadPermissionSourceFromConfig() {
        String permissionSourceName = this.mainConfiguration.getString("permission-source");
        PermissionSource<P> permissionSource;
        switch (permissionSourceName) {
            default:
                permissionSource = new LuckpermsPermissionSource<>(this);
        }

        this.permissionSource = permissionSource;
    }

    public DisplayDataProvider<P> getDisplayDataProvider() {
        return displayDataProvider;
    }
}
