package me.shreyasr.hexxer

import com.badlogic.ashley.core.Component

object Components {

  case class Pos(val x: Int, val y: Int) extends Component
  case class Tex(val file: String) extends Component
}
