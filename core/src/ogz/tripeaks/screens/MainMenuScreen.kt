package ogz.tripeaks.screens

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.ui.TextButton
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener
import ktx.app.KtxScreen
import ktx.scene2d.Scene2DSkin
import ogz.tripeaks.Const
import ogz.tripeaks.Game
import ogz.tripeaks.TextureAssets
import ogz.tripeaks.get

class MainMenuScreen(val game: Game) : KtxScreen {

    private val stage = Stage(
            IntegerScalingViewport(
                    Const.CONTENT_WIDTH.toInt(),
                    Const.CONTENT_HEIGHT.toInt(),
                    OrthographicCamera()
            )
    )

    override fun render(delta: Float) {
        stage.act(delta)
        Gdx.gl.glClearColor(0.39f, 0.64f, 0.28f, 1f)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)
        stage.draw()
    }

    override fun show() {
        val skin = Scene2DSkin.defaultSkin
        stage.actors.add(
                Image(game.assets[TextureAssets.Title]).apply {
                    setSize(118f, 50f)
                    setPosition((Const.CONTENT_WIDTH - width) / 2, Const.CONTENT_HEIGHT / 2f + 8f)
                },

                TextButton("Start", skin).apply {
                    addListener(object : ChangeListener() {
                        override fun changed(event: ChangeEvent?, actor: Actor?) {
                            game.addScreen(GameScreen(game))
                            game.setScreen<GameScreen>()
                            game.removeScreen<MainMenuScreen>()
                            dispose()
                        }

                    })
                    width = 100f
                    setPosition(Const.CONTENT_WIDTH / 2f - 50f, Const.CONTENT_HEIGHT / 2f - height - 8f)
                }
        )
        Gdx.input.inputProcessor = stage
    }

    override fun resize(width: Int, height: Int) {
        stage.viewport.update(width, height)
    }
}
