import java.io.File

class Day12a: Day {

  override fun run() {
    val input = File("data/input12.txt").readLines().map(String::toCharArray).toTypedArray()

    val positions = mutableSetOf<Position>()
    val garden = Array(input.size+2) {CharArray(input[0].size+2)}
    for (i in garden.indices) {
      for (j in garden[i].indices) {
        if (i == 0 || i == garden.size-1 || j == 0 || j == garden[i].size-1) {
          garden[i][j] = '0'
        } else {
          garden[i][j] = input[i-1][j-1]
          positions.add(Position(i,j))
        }
      }
    }

    var total = 0L

    while (positions.isNotEmpty()) {
      var area = 0; var perimeter = 0
      val neighbors = mutableListOf(positions.iterator().next())
      while (neighbors.isNotEmpty()) {
        val curr = neighbors.removeFirst()
        if (!positions.remove(curr)) continue
        val i = curr.first
        val j = curr.second

        area += 1
        if (garden[i][j] == garden[i+1][j  ]) neighbors.add(Position(i+1,j  )) else perimeter+=1
        if (garden[i][j] == garden[i-1][j  ]) neighbors.add(Position(i-1,j  )) else perimeter+=1
        if (garden[i][j] == garden[i  ][j+1]) neighbors.add(Position(i  ,j+1)) else perimeter+=1
        if (garden[i][j] == garden[i  ][j-1]) neighbors.add(Position(i  ,j-1)) else perimeter+=1
      }
      total += area * perimeter
    }

    println(total)
  }
}