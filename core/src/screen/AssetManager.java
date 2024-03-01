package screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class AssetManager {

    public static Texture sheet, sheetgogeta, sheetenemigos;

    public static TextureRegion background, gogeta, gogetaDown, gogetaUp, gogetaRight, gogetaLeft;

    public static Animation robotsAnim;
    public static TextureRegion[] robots;

    public static TextureRegion[] explosion;


    // Font
    public static BitmapFont font;

    public static void load() {

        // Carreguem les textures i li apliquem el mètode d'escalat 'nearest'
        sheet = new Texture(Gdx.files.internal("house.jpg"));
        sheet.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Nearest);

        sheetgogeta = new Texture(Gdx.files.internal("Gogeta.png"));
        sheetgogeta.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Nearest);

        sheetenemigos = new Texture(Gdx.files.internal("enemigos.png"));
        sheetenemigos.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Nearest);

        gogeta = new TextureRegion(sheetgogeta, 384, 213, 39, 49);
        gogeta.flip(false, false);

        gogetaUp = new TextureRegion(sheetgogeta, 753, 137, 29, 49);
        gogetaUp.flip(false, false);

        gogetaDown = new TextureRegion(sheetgogeta, 713, 139, 29, 49);
        gogetaDown.flip(false, false);

        gogetaRight = new TextureRegion(sheetgogeta, 149, 92, 29, 49);
        gogetaRight.flip(false, false);

        gogetaLeft = new TextureRegion(sheetgogeta, 192, 88, 29, 49);
        gogetaLeft.flip(false, false);


        robots = new TextureRegion[2];

        robots[0] = new TextureRegion(sheetenemigos, 358, 168, 55, 48);
        robots[1] = new TextureRegion(sheetenemigos, 415, 168, 55, 48);

        robotsAnim = new Animation(0.30f, robots);
        robotsAnim.setPlayMode(Animation.PlayMode.LOOP);


        // Fons de pantalla
        background = new TextureRegion(sheet);

    }

    public static void dispose() {
        sheet.dispose();
    }
}
