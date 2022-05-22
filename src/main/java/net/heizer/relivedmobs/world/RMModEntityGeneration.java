package net.heizer.relivedmobs.world;

import net.heizer.relivedmobs.entity.RMModEntityTypes;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraftforge.event.world.BiomeLoadingEvent;

public class RMModEntityGeneration {

    public static void onEntitySpawn(final BiomeLoadingEvent event) {
        //Beluga spawn
        if(doesBiomeMatch(event.getName(), Biomes.COLD_OCEAN)) {
            event.getSpawns().getSpawner(MobCategory.WATER_CREATURE).add(new MobSpawnSettings.SpawnerData(RMModEntityTypes.BELUGA.get(), 6, 1, 3));
        }
        if(doesBiomeMatch(event.getName(), Biomes.DEEP_COLD_OCEAN)) {
            event.getSpawns().getSpawner(MobCategory.WATER_CREATURE).add(new MobSpawnSettings.SpawnerData(RMModEntityTypes.BELUGA.get(), 6, 1, 3));
        }
        if(doesBiomeMatch(event.getName(), Biomes.FROZEN_OCEAN)) {
            event.getSpawns().getSpawner(MobCategory.WATER_CREATURE).add(new MobSpawnSettings.SpawnerData(RMModEntityTypes.BELUGA.get(), 6, 1, 3));
        }
        if(doesBiomeMatch(event.getName(), Biomes.DEEP_FROZEN_OCEAN)) {
            event.getSpawns().getSpawner(MobCategory.WATER_CREATURE).add(new MobSpawnSettings.SpawnerData(RMModEntityTypes.BELUGA.get(), 6, 1, 3));
        }

        //Piranha spawn
        if(doesBiomeMatch(event.getName(), Biomes.JUNGLE)) {
            event.getSpawns().getSpawner(MobCategory.WATER_CREATURE).add(new MobSpawnSettings.SpawnerData(RMModEntityTypes.PIRANHA.get(), 1, 1, 5));
        }
        if(doesBiomeMatch(event.getName(), Biomes.BAMBOO_JUNGLE)) {
            event.getSpawns().getSpawner(MobCategory.WATER_CREATURE).add(new MobSpawnSettings.SpawnerData(RMModEntityTypes.PIRANHA.get(), 1, 1, 5));
        }
        if(doesBiomeMatch(event.getName(), Biomes.SPARSE_JUNGLE)) {
            event.getSpawns().getSpawner(MobCategory.WATER_CREATURE).add(new MobSpawnSettings.SpawnerData(RMModEntityTypes.PIRANHA.get(), 1, 1, 5));
        }
        //Silverside spawn
        if(doesBiomeMatch(event.getName(), Biomes.OCEAN)) {
            event.getSpawns().getSpawner(MobCategory.WATER_CREATURE).add(new MobSpawnSettings.SpawnerData(RMModEntityTypes.SILVERSIDE.get(), 3, 10, 20));
        }
        if(doesBiomeMatch(event.getName(), Biomes.DEEP_OCEAN)) {
            event.getSpawns().getSpawner(MobCategory.WATER_CREATURE).add(new MobSpawnSettings.SpawnerData(RMModEntityTypes.SILVERSIDE.get(), 3, 10, 20));
        }
        if(doesBiomeMatch(event.getName(), Biomes.LUKEWARM_OCEAN)) {
            event.getSpawns().getSpawner(MobCategory.WATER_CREATURE).add(new MobSpawnSettings.SpawnerData(RMModEntityTypes.SILVERSIDE.get(), 3, 10, 20));
        }
        if(doesBiomeMatch(event.getName(), Biomes.DEEP_LUKEWARM_OCEAN)) {
            event.getSpawns().getSpawner(MobCategory.WATER_CREATURE).add(new MobSpawnSettings.SpawnerData(RMModEntityTypes.SILVERSIDE.get(), 3, 10, 20));
        }
    }

    public static boolean doesBiomeMatch(ResourceLocation biomeNameIn, ResourceKey<Biome> biomeIn) {
        return biomeNameIn.getPath().matches(biomeIn.location().getPath());
    }
}
