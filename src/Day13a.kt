import java.io.File
import kotlin.math.min

class Day13a: Day {

  companion object {
    private val BUTTON_REGEX = Regex("Button .: X(.\\d+), Y(.\\d+)")
    private val PRIZE_REGEX = Regex("Prize: X=(\\d+), Y=(\\d+)")
  }

  override fun run() {

    val configs = mutableListOf<Array<Position>>()
    val input = File("data/input13.txt").readLines().toTypedArray()
    var a: Position = Position(0,0);
    var b: Position = Position(0,0);
    var prize: Position = Position(0,0)
    for (i in input.indices) {
      when (i % 4) {
        0 -> { val x = BUTTON_REGEX.findAll(input[i]).first().groups[1]!!.value.toInt()
               val y = BUTTON_REGEX.findAll(input[i]).first().groups[2]!!.value.toInt()
               a = Position(x,y) }
        1 -> { val x = BUTTON_REGEX.findAll(input[i]).first().groups[1]!!.value.toInt()
               val y = BUTTON_REGEX.findAll(input[i]).first().groups[2]!!.value.toInt()
               b = Position(x, y) }
        2 -> { val x = PRIZE_REGEX.findAll(input[i]).first().groups[1]!!.value.toInt()
               val y = PRIZE_REGEX.findAll(input[i]).first().groups[2]!!.value.toInt()
               prize = Position(x, y) }
        3 -> configs.add(arrayOf(a,b,prize))
      }
    }

    var tokens = 0

    for (config in configs) {
      val ax = config[0].first
      val ay = config[0].second
      val bx = config[1].first
      val by = config[1].second
      val px = config[2].first
      val py = config[2].second

      var  min = Int.MAX_VALUE
      for (i in 1..100) {
        for (j in 1..100) {
            if (px == i*ax + j*bx && py == i*ay + j*by) {
              min = min(min, i*3 + j)
            }
        }
      }

      if (min < Int.MAX_VALUE) tokens += min
    }

    println(tokens)
  }

}