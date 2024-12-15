import java.io.File

class Day10b: Day {

  override fun run() {
    val input= File("data/input10.txt").readLines().map(String::toCharArray).toTypedArray()

    val topo = Array(input.size + 2) { IntArray(input[0].size + 2) }
    val startPositions = mutableSetOf<Position>()
    for (i in topo.indices) {
      for (j in topo[i].indices) {
        if (i == 0 || j == 0 || i == topo.size-1 || j == topo[i].size-1 ) {
          topo[i][j] = -2
        } else {
          val char = input[i-1][j-1]
          val code = if (char == '.') -2 else char.code-'0'.code
          if (code == 0) startPositions.add(Position(i,j))
          topo[i][j] = code
        }
      }
    }

    var total = 0
    for (start in startPositions) {
      val stack = mutableListOf<Position>()
      stack.addLast(start)

      while (stack.isNotEmpty()) {
        val position = stack.removeLast()
        val i = position.first
        val j = position.second

        if (topo[i][j] == 9) total += 1
        else {
          if (topo[i-1][j  ] == topo[i][j] + 1) stack.addLast(Position(i-1,j  ))
          if (topo[i+1][j  ] == topo[i][j] + 1) stack.addLast(Position(i+1,j  ))
          if (topo[i  ][j-1] == topo[i][j] + 1) stack.addLast(Position(i  ,j-1))
          if (topo[i  ][j+1] == topo[i][j] + 1) stack.addLast(Position(i  ,j+1))
        }
      }
    }

    println(total)
  }
}