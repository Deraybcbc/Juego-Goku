package screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class AssetManager {

    public static Texture sheet, sheetgogeta, sheetenemigos;

    public static TextureRegion background, gogeta, gogetaDown, gogetaUp, gogetaRight, gogetaLeft, gogetaDaño, disparo;

    public static Animation robotsAnim;

    public static Animation muertegogeta;

    public static Animation disparosgogeta;

    public static Animation explosionRobot;

    public static TextureRegion[] robots;

    public static TextureRegion[] explosion;

    public static TextureRegion[] gogetamuerto;

    public static TextureRegion[] gogetaDisparo;


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

        gogetaDaño = new TextureRegion(sheetgogeta, 111, 208, 29, 49);
        gogetaDaño.flip(false, false);


        robots = new TextureRegion[2];

        robots[0] = new TextureRegion(sheetenemigos, 358, 168, 55, 48);
        robots[1] = new TextureRegion(sheetenemigos, 415, 168, 55, 48);

        robotsAnim = new Animation(0.30f, robots);
        robotsAnim.setPlayMode(Animation.PlayMode.LOOP);

        gogetamuerto = new TextureRegion[3];
        gogetamuerto[0] = new TextureRegion(sheetgogeta, 227, 212, 29, 49);
        gogetamuerto[1] = new TextureRegion(sheetgogeta, 262, 208, 29, 49);
        gogetamuerto[2] = new TextureRegion(sheetgogeta, 293, 205, 29, 49);
        //gogetamuerto[3] = new TextureRegion(sheetgogeta, 327, 230, 29, 49);


        muertegogeta =  new Animation(0.50f, gogetamuerto);
        muertegogeta.setPlayMode(Animation.PlayMode.LOOP);

        disparo = new TextureRegion(sheetgogeta, 394,804,20,18);
        disparo.flip(false, false);

        gogetaDisparo = new TextureRegion[2];

        gogetaDisparo[0] = new TextureRegion(sheetgogeta, 4,270,29,49);
        gogetaDisparo[1] = new TextureRegion(sheetgogeta, 3,329,29,49);

        disparosgogeta = new Animation(0.50f, gogetaDisparo);
        disparosgogeta.setPlayMode(Animation.PlayMode.LOOP);

        explosion = new TextureRegion[11];

        explosion[0] = new TextureRegion(sheetenemigos, 820,788, 92,73);
        explosion[1] = new TextureRegion(sheetenemigos, 725,787, 92,73);
        explosion[2] = new TextureRegion(sheetenemigos, 641,789, 92,73);
        explosion[3] = new TextureRegion(sheetenemigos, 556,785, 92,73);
        explosion[4] = new TextureRegion(sheetenemigos, 472,783, 92,73);
        explosion[5] = new TextureRegion(sheetenemigos, 394,783, 92,73);
        explosion[6] = new TextureRegion(sheetenemigos, 310,781, 92,73);
        explosion[7] = new TextureRegion(sheetenemigos, 237,778, 92,73);
        explosion[8] = new TextureRegion(sheetenemigos, 167,777, 92,73);
        explosion[9] = new TextureRegion(sheetenemigos, 106,776, 92,73);
        explosion[10] = new TextureRegion(sheetenemigos, 50,776, 92,73);

        explosionRobot = new Animation(0.50f, explosion);
        explosionRobot.setPlayMode(Animation.PlayMode.LOOP);


        // Fons de pantalla
        background = new TextureRegion(sheet);

    }

    public static void dispose() {
        sheet.dispose();
    }
}
