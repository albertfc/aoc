import java.io.File

class Day08b: Day {

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
      antinodes.addAll(positionsSet)
      val positions = positionsSet.toTypedArray()
      for (i in positions.indices) {
        val ai = positions[i].first
        val aj = positions[i].second
        for (j in positions.indices) {
          if (i==j) continue
          val bi = positions[j].first
          val bj = positions[j].second

          var coeff = 1
          while (true) {
            val ci = bi + coeff*(bi - ai)
            val cj = bj + coeff*(bj - aj)
            coeff += 1
            if (ci in 0..<isize && cj in 0..<jsize)
              antinodes.add(Position(ci, cj))
            else
              break
          }
        }
      }
    }

    println(antinodes.size)
  }
}