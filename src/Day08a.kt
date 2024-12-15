import java.io.File

class Day08a: Day {

  override fun run() {
    val lines = File("data/input08.txt").readLines()
      .map(String::toCharArray)
      .toTypedArray()

    val isize = lines.size
    val jsize = lines[0].size

    val antennas = mutableMapOf<Char, MutableSet<Position>>()
    for (i in lines.indices) {
      for (j in lines[i].indices) {
        val antenna = lines[i][j]
        if (antenna != '.') {
          val position = Position(i, j)
          antennas[antenna]?.add(position) ?: run { antennas[antenna] = mutableSetOf(position) }
        }
      }
    }

    val antinodes = mutableSetOf<Position>()
    antennas.forEach { (_, positionsSet) ->
      val positions = positionsSet.toTypedArray()
      for (i in positions.indices) {
        val ai = positions[i].first
        val aj = positions[i].second
        for (j in positions.indices) {
          if (i==j) continue
          val bi = positions[j].first
          val bj = positions[j].second

          val ci = 2*bi - ai
          val cj = 2*bj - aj
          if (ci in 0..<isize && cj in 0..< jsize)
            antinodes.add(Position(ci,cj))
        }
      }
    }

    println(antinodes.size)
  }
}