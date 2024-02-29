    package com.mygdx.game;

    import com.badlogic.gdx.Game;
    import com.badlogic.gdx.Screen;
    import com.badlogic.gdx.graphics.g2d.BitmapFont;
    import com.badlogic.gdx.graphics.g2d.SpriteBatch;

    import screen.AssetManager;
    import screen.GameScreen;
    import screen.PantallaPrincipal;

    public class JuegoGoku extends Game {

        private SpriteBatch spriteBatch;
        private BitmapFont bitmapFont;

        @Override
        public void create() {

            spriteBatch = new SpriteBatch();
            bitmapFont = new BitmapFont();

            // I definim la pantalla principal com a la pantalla
            setScreen(new PantallaPrincipal(this));

        }

        // Cridem per descartar els recursos carregats.
        @Override
        public void dispose() {
            super.dispose();
            spriteBatch.dispose();
            bitmapFont.dispose();
        }

        public SpriteBatch getSpriteBatch() {
            return spriteBatch;
        }

        public void setSpriteBatch(SpriteBatch spriteBatch) {
            this.spriteBatch = spriteBatch;
        }

        public BitmapFont getBitmapFont() {
            return bitmapFont;
        }

        public void setBitmapFont(BitmapFont bitmapFont) {
            this.bitmapFont = bitmapFont;
        }
    }
