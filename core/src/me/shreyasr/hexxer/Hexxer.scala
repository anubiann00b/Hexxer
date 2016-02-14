package me.shreyasr.hexxer

import com.badlogic.ashley.core.{Engine, Entity, EntitySystem, Family}
import com.badlogic.ashley.systems.IteratingSystem
import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.{GL20, Texture}
import com.badlogic.gdx.{ApplicationAdapter, Gdx}

class Hexxer extends ApplicationAdapter {

  var batch: SpriteBatch = null
  val engine: Engine = new Engine()
  val assetManager = new AssetManager()
  val player = new Entity()

  override def create() {
    Assets.loadAll(assetManager)
    assetManager.finishLoading()

    batch = new SpriteBatch
    player.add(Components.Pos(4,5))
    player.add(Components.Tex(Assets.GRASS.getFile))
    engine.addEntity(player)

    val p = { var i = 0; () => { i += 1; i} }

    engine.addSystem(new EntitySystem(p()) { override def update(deltaTime: Float): Unit = {
      Gdx.gl.glClearColor(0, 0, 0, 1)
      Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)
      batch.begin()
    }})
    engine.addSystem(new IteratingSystem(Family.all(classOf[Components.Tex]).get(), p()) {
      override def processEntity(entity: Entity, deltaTime: Float): Unit = {
        val pos = entity.getComponent(classOf[Components.Pos])
        val texture = assetManager.get(entity.getComponent(classOf[Components.Tex]).file, classOf[Texture])
        texture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear)
        batch.draw(texture, pos.x, pos.y)
      }
    })
    engine.addSystem(new EntitySystem(p()) { override def update(deltaTime: Float): Unit = batch.end() })
  }

  override def render() {
    engine.update(1)
  }
}
