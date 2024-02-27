    package com.mygdx.game;

    import com.badlogic.gdx.Game;
    import com.badlogic.gdx.Screen;

    import screen.AssetManager;
    import screen.GameScreen;
    import screen.PantallaPrincipal;

    public class JuegoGoku extends Game {
        @Override
        public void create() {

            // A l'iniciar el joc carreguem els recursos
            AssetManager.load();
            // I definim la pantalla principal com a la pantalla
            setScreen(new PantallaPrincipal());

        }

        // Cridem per descartar els recursos carregats.
        @Override
        public void dispose() {
            super.dispose();
            AssetManager.dispose();
        }
    }
