package screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class AssetManager {

    public static Texture sheet;

    public static TextureRegion background;

    public static void load() {

        // Carreguem les textures i li apliquem el mètode d'escalat 'nearest'
        sheet = new Texture(Gdx.files.internal("kame_house.gif"));
        sheet.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Nearest);

        // Fons de pantalla
        background = new TextureRegion(sheet, 0, 177,480,480);

    }

    public static void dispose(){

    }
}
