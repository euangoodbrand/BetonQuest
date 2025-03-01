package org.betonquest.betonquest.modules.config.patcher.migration;

import org.betonquest.betonquest.modules.config.patcher.migration.migrators.EffectLib;
import org.betonquest.betonquest.modules.config.patcher.migration.migrators.EventScheduling;
import org.betonquest.betonquest.modules.config.patcher.migration.migrators.MmoUpdates;
import org.betonquest.betonquest.modules.config.patcher.migration.migrators.NpcHolograms;
import org.betonquest.betonquest.modules.config.patcher.migration.migrators.PackageSection;
import org.betonquest.betonquest.modules.config.patcher.migration.migrators.PackageStructure;
import org.betonquest.betonquest.modules.config.patcher.migration.migrators.RPGMenuMerge;
import org.betonquest.betonquest.modules.config.patcher.migration.migrators.RemoveEntity;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

/**
 * Handles the migration process.
 */
public class Migrator {
    /**
     * The migrations to use.
     */
    private final List<Migration> migrations;

    /**
     * Creates a new migration process.
     */
    public Migrator() {
        this.migrations = new LinkedList<>();
        final FileConfigurationProvider provider = new FileConfigurationProvider();

        migrations.add(new RPGMenuMerge());
        migrations.add(new PackageStructure());
        migrations.add(new EventScheduling(provider));
        migrations.add(new PackageSection(provider));
        migrations.add(new NpcHolograms(provider));
        migrations.add(new EffectLib(provider));
        migrations.add(new MmoUpdates(provider));
        migrations.add(new RemoveEntity(provider));
    }

    /**
     * Migrates all configs.
     *
     * @throws IOException if an error occurs
     */
    public void migrate() throws IOException {
        for (final Migration migration : migrations) {
            migration.migrate();
        }
    }
}
