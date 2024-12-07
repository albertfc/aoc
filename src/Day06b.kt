import java.io.File

class Day06b : Day {

  enum class Direction {
    N, S, E, W;
  }

  override fun run() {
    val input = File("data/input06.txt").readLines().toTypedArray()
    val map = Array(input.size + 2) { CharArray(input[0].length + 2) }

    var start: Position = 0 to 0
    for (i in map.indices) {
      for (j in map[i].indices) {
        if (i == 0 || j == 0 || i == map.size-1 || j == map[i].size-1)
          map[i][j] = '0'
        else
          map[i][j] = input[i-1][j-1]
        if (map[i][j] == '^') start = i to j
      }
    }

    var total = 0
    possiblePositions(map, start).forEach{ position ->
      val i = position.first
      val j = position.second
      if (map[i][j] == '.') {
        map[i][j] = '#'
        total += if (isLoop(map, start)) 1 else 0
        map[i][j] = '.'
      }
    }

    println(total)
  }

  private fun possiblePositions(map: Array<CharArray>, start: Position): Set<Position> {
    val positions = mutableSetOf<Position>()
    var current = start
    var direction = Direction.N
    while (map[current.first][current.second] != '0') {
      positions.add(current)
      val i = current.first
      val j = current.second
      if ( (direction == Direction.N && map[i - 1][j] == '#')
        || (direction == Direction.E && map[i][j + 1] == '#')
        || (direction == Direction.S && map[i + 1][j] == '#')
        || (direction == Direction.W && map[i][j - 1] == '#')
      ) {
        var found = false
        while (!found) {
          rotate(direction, current).let {
            direction = it.first
            if (map[it.second.first][it.second.second] != '#') {
              current = it.second
              found = true
            }
          }
        }
      } else {
        current = advance(direction, current)
      }
    }
    return positions
  }

  private fun isLoop(map: Array<CharArray>, start: Position): Boolean {
    val positions = mutableSetOf<Pair<Position,Direction>>()
    var direction = Direction.N
    var current = start
    while (map[current.first][current.second] != '0') {

      if (!positions.add(current to direction)) return true

      val i = current.first
      val j = current.second
      if ( (direction == Direction.N && map[i-1][j  ] == '#')
        || (direction == Direction.E && map[i  ][j+1] == '#')
        || (direction == Direction.S && map[i+1][j  ] == '#')
        || (direction == Direction.W && map[i  ][j-1] == '#')) {
        var found = false
        while (!found) {
          rotate(direction, current).let {
            direction = it.first
            if (map[it.second.first][it.second.second] != '#') {
              current = it.second
              found = true
            }
          }
        }
      } else {
        current = advance(direction, current)
      }
    }
    return false
  }

  private fun advance(direction: Direction, position: Position) =
    when (direction) {
      Direction.N -> position.first-1 to position.second
      Direction.E -> position.first   to position.second+1
      Direction.S -> position.first+1 to position.second
      Direction.W -> position.first   to position.second-1
    }

  private fun rotate(direction: Direction, position: Position) =
    when (direction) {
      Direction.N -> Direction.E to (position.first   to position.second+1)
      Direction.E -> Direction.S to (position.first+1 to position.second  )
      Direction.S -> Direction.W to (position.first   to position.second-1)
      Direction.W -> Direction.N to (position.first-1 to position.second  )
    }
}