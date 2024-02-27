    package screen;

    import com.badlogic.gdx.Gdx;
    import com.badlogic.gdx.Screen;
    import com.badlogic.gdx.graphics.GL20;
    import com.badlogic.gdx.graphics.OrthographicCamera;
    import com.badlogic.gdx.graphics.Texture;
    import com.badlogic.gdx.graphics.g2d.SpriteBatch;
    import com.badlogic.gdx.scenes.scene2d.Stage;
    import com.badlogic.gdx.utils.viewport.StretchViewport;

    import cat.xtec.ioc.objects.Background;
    import cat.xtec.ioc.objects.ScrollHandler;
    import utils.Settings;

    public class PantallaPrincipal implements Screen {

        private Texture gifTexture;

        Background background, bg_back;

        ScrollHandler scrollHandler;


        private SpriteBatch batch; // Declaración del SpriteBatch

        Stage stage;

        public PantallaPrincipal() {

            // Crear el objeto SpriteBatch
            batch = new SpriteBatch();

            // Cargar los recursos utilizando AssetManager
            AssetManager.load();


        }

        @Override
        public void show() {


        }

        @Override
        public void render(float delta) {

        }

        @Override
        public void resize(int width, int height) {

        }

        @Override
        public void pause() {

        }

        @Override
        public void resume() {

        }

        @Override
        public void hide() {

        }

        @Override
        public void dispose() {

        }
    }
