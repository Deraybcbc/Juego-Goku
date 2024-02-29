    package screen;

    import com.badlogic.gdx.Gdx;
    import com.badlogic.gdx.files.FileHandle;
    import com.badlogic.gdx.graphics.Texture;
    import com.badlogic.gdx.graphics.g2d.BitmapFont;
    import com.badlogic.gdx.graphics.g2d.TextureRegion;

    public class AssetManager {

        public static Texture sheet, sheetgogeta;

        public static TextureRegion background, gogeta, gogetaDown, gogetaUp, gogetaRight, gogetaLeft;

        // Font
        public static BitmapFont font;

        public static void load() {

            // Carreguem les textures i li apliquem el mètode d'escalat 'nearest'
            sheet = new Texture(Gdx.files.internal("house.jpg"));
            sheet.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Nearest);

            sheetgogeta = new Texture(Gdx.files.internal("Gogeta.png"));
            sheetgogeta.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Nearest);

            gogeta = new TextureRegion(sheetgogeta, 4 ,89, 29,49);
            gogeta.flip(false, false);

            gogetaUp = new TextureRegion(sheetgogeta,753,137, 29,49);
            gogetaUp.flip(false, false);

            gogetaDown = new TextureRegion(sheetgogeta, 713, 139, 29,49);
            gogetaDown.flip(false, false);

            gogetaRight = new TextureRegion(sheetgogeta,149, 92, 29,49);
            gogetaRight.flip(false, false);

            gogetaLeft = new TextureRegion(sheetgogeta,192,88,29,49);
            gogetaLeft.flip(false, false);

            // Fons de pantalla
            background = new TextureRegion(sheet);

        }

        public static void dispose(){
            sheet.dispose();
        }
    }
