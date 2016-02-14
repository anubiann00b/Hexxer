package me.shreyasr.hexxer;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;

public enum Assets {

    GRASS (Texture.class, "terrain/Grass/grass_07.png");

    private final Class type;
    private final String file;

    public String getFile() {
        return file;
    }

    Assets(Class type, String file) {
        this.type = type;
        this.file = file;
    }

    public static void loadAll(AssetManager assetManager) {
//        assetManager.setLoader(TiledMap.class, new TmxMapLoader(new InternalFileHandleResolver()));
        for (Assets asset : Assets.values()) {
            System.out.println(asset.getFile());
            assetManager.load(asset.getFile(), asset.type);
        }
    }
}