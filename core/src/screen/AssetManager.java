    package screen;

    import com.badlogic.gdx.Gdx;
    import com.badlogic.gdx.graphics.Texture;
    import com.badlogic.gdx.graphics.g2d.TextureRegion;

    public class AssetManager {

        public static Texture sheet, sheet2;

        public static TextureRegion background, backgroundP;

        public static void load() {

            // Carreguem les textures i li apliquem el mètode d'escalat 'nearest'
            sheet = new Texture(Gdx.files.internal("house.jpg"));
            sheet.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Nearest);


            // Fons de pantalla
            background = new TextureRegion(sheet);

        }

        public static void dispose(){
            sheet.dispose();
        }
    }
