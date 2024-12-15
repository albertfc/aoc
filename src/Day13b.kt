import java.io.File
import kotlin.math.min

class Day13b: Day {

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

    var tokens = 0L

    for (config in configs) {
      val ax = config[0].first
      val ay = config[0].second
      val bx = config[1].first
      val by = config[1].second
      val px = 10000000000000L + config[2].first
      val py = 10000000000000L + config[2].second

      val b = (py*ax - ay*px) / (ax*by - bx*ay)
      val a = (px - bx*b) / ax
      if (px == a*ax + b*bx && py == a*ay + b*by) {
        tokens += 3*a + b
      }
    }

    println(tokens)
  }

}