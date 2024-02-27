    package screen;

    import com.badlogic.gdx.Gdx;
    import com.badlogic.gdx.Screen;
    import com.badlogic.gdx.graphics.GL20;
    import com.badlogic.gdx.graphics.OrthographicCamera;
    import com.badlogic.gdx.graphics.Texture;
    import com.badlogic.gdx.graphics.g2d.BitmapFont;
    import com.badlogic.gdx.graphics.g2d.SpriteBatch;
    import com.badlogic.gdx.graphics.g2d.TextureRegion;
    import com.badlogic.gdx.scenes.scene2d.Stage;
    import com.badlogic.gdx.utils.viewport.StretchViewport;

    import cat.xtec.ioc.objects.Background;
    import cat.xtec.ioc.objects.ScrollHandler;
    import utils.Settings;

    public class PantallaPrincipal implements Screen {

        private Texture gifTexture;

        Background background, bg_back;

        private Stage stage;
        private SpriteBatch batch;
        private BitmapFont font;

        public PantallaPrincipal() {

            // Crear el objeto SpriteBatch
            batch = new SpriteBatch();
        }

        @Override
        public void show() {

            // Cargar los recursos utilizando AssetManager
            AssetManager.load();


            background = new Background(0, 0 , Settings.GAME_WIDTH *2,Settings.GAME_HEIGHT, Settings.BG_SPEED);
            bg_back = new Background(background.getTailX(),0 ,Settings.GAME_WIDTH *2,Settings.GAME_HEIGHT, Settings.BG_SPEED);

        }

        @Override
        public void render(float delta) {

            // Limpiar la pantalla
            Gdx.gl.glClearColor(0, 0, 0, 1);
            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

            // Actualizar y dibujar el fondo
            batch.begin();
            background.act(delta);
            bg_back.act(delta);
            background.draw(batch, 1); // El valor de parentAlpha es 1, se puede ajustar según sea necesario
            bg_back.draw(batch,1);
            batch.end();
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
            batch.dispose();
            font.dispose();
            stage.dispose();
        }
    }
