package de.mcmdev.displayformatter.common;

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

    public void load() {
        this.teamManager = new TeamManager<>(this.platform);
        this.mainConfiguration = this.platform.getConfiguration("config.yml");

        loadPermissionSourceFromConfig();
    }

    public void unload() {
        this.teamManager = null;
        this.mainConfiguration = null;
        this.permissionSource = null;
    }

    private void loadPermissionSourceFromConfig()   {
        String permissionSourceName = this.mainConfiguration.getString("permission-source");
        PermissionSource<P> permissionSource;
        switch (permissionSourceName)   {
            default:
                permissionSource = new LuckpermsPermissionSource<>(this.platform);
        }

        this.permissionSource = permissionSource;
    }
}
