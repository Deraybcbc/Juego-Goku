    package screen;

    import com.badlogic.gdx.Screen;
    import com.badlogic.gdx.graphics.Texture;

    public class PantallaPrincipal implements Screen {

        private Texture gifTexture;


        @Override
        public void show() {
            // Cargar el GIF utilizando AssetManager
            gifTexture = AssetManager.sheet("nombre_del_archivo.gif", Texture.class);
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
