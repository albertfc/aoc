import java.io.File

typealias Vel = Pair<Int,Int>
typealias Robot = Pair<Position,Vel>

class Day14b: Day {

  companion object {
    private val REGEX = Regex("p=([+|-]?\\d+),([+|-]?\\d+) v=([+|-]?\\d+),([+|-]?\\d+)")
    const val LENX = 103
    const val LENY = 101
  }

  val tree = mutableSetOf<Position>()

  override fun run() {
    val robots = File("data/input14.txt").readLines().map{ line ->
      val match = REGEX.find(line)?.groups!!
      val py0 = match[1]!!.value.toInt()
      val px0 = match[2]!!.value.toInt()
      val vy0 = match[3]!!.value.toInt()
      val vx0 = match[4]!!.value.toInt()
      Robot(Position(px0,py0), Vel(vx0,vy0))
    }.toSet()

    for (sec in 0..<(LENY*LENX)) {
      val config = robots.map { simulate(it, sec) }.toSet()

//      var out = ""
//      for (i in 0..<LENX) {
//        for (j in 0..<LENY) {
//          out += if (config.contains(Position(i, j))) '*' else ' '
//        }
//        out += '\n'
//      }
//      File("/tmp/tree/$sec.txt").writeText(out)

      if (config.size == robots.size) {
        println(sec)
        return
      }
    }
    println("Not Found!")
  }

  private fun simulate(robot: Robot, seconds: Int): Position {
    val py0 = robot.first.second
    val px0 = robot.first.first
    val vy0 = robot.second.second
    val vx0 = robot.second.first

    val px = ((px0 + seconds*vx0) % LENX).let { if (it<0) LENX+it else it }
    val py = ((py0 + seconds*vy0) % LENY).let { if (it<0) LENY+it else it }
    return Position(px, py)
  }
}