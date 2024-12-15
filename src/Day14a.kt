import java.io.File

class Day14a: Day {

  companion object {
    private val REGEX = Regex("p=([+|-]?\\d+),([+|-]?\\d+) v=([+|-]?\\d+),([+|-]?\\d+)")
    const val LENX = 103
    const val LENY = 101
    const val SECONDS = 100
  }
  
  override fun run() {
    val q = IntArray(4){0}
    File("data/input14.txt").readLines().map(this::simulate)
      .forEach { val px = it.first; val py = it.second
        if      (px < LENX/2 && py < LENY/2) q[0]+=1
        else if (px < LENX/2 && py > LENY/2) q[1]+=1
        else if (px > LENX/2 && py < LENY/2) q[2]+=1
        else if (px > LENX/2 && py > LENY/2) q[3]+=1
      }
    val res = q.reduce{acc, value -> acc*value }
    println(res)
  }

  private fun simulate(line: String): Position {
    val match = REGEX.find(line)?.groups!!
    val py0 = match[1]!!.value.toInt()
    val px0 = match[2]!!.value.toInt()
    val vy0 = match[3]!!.value.toInt()
    val vx0 = match[4]!!.value.toInt()

    val px = ((px0 + SECONDS*vx0) % LENX).let { if (it<0) LENX+it else it }
    val py = ((py0 + SECONDS*vy0) % LENY).let { if (it<0) LENY+it else it }
    return Position(px, py)
  }
}