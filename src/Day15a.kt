import java.io.File

class Day15a: Day {

  override fun run() {
    val map = File("data/input15.map.txt").readLines().map(String::toCharArray).toTypedArray()
    val moves = File("data/input15.moves.txt").readLines().map(String::toCharArray)

    var pos = Position(0,0)
    for (i in map.indices)
      for (j in map[i].indices)
        if (map[i][j] == '@') pos = Position(i,j)

    moves.forEach { it.forEach { move ->
      when(move) {
        '>' -> pos = right(map, pos)
        '<' -> pos = left(map, pos)
        '^' -> pos = up(map, pos)
        'v' -> pos = down(map, pos)
      } }
    }

    var total = 0L
    for (i in map.indices)
      for (j in map[i].indices)
        if (map[i][j] == 'O') total += 100*i + j

    println(total)
  }

  private fun down(map: Array<CharArray>, position: Position): Position {
    val i = position.first
    val j = position.second

    if (map[i+1][j] == '#') return Position(i,j)

    if (map[i+1][j] == '.') {
      map[i  ][j] = '.'
      map[i+1][j] = '@'
      return Position(i+1, j)
    }

    for (ii in i+1..<map.size)
      if      (map[ii][j] == '#') break
      else if (map[ii][j] == '.') {
        map[i  ][j] = '.'
        map[i+1][j] = '@'
        map[ii ][j] = 'O'
        return Position(i+1, j)
      }

    return Position(i,j)
  }

  private fun up(map: Array<CharArray>, position: Position): Position {
    val i = position.first
    val j = position.second

    if (map[i-1][j] == '#') return Position(i,j)

    if (map[i-1][j] == '.') {
      map[i  ][j] = '.'
      map[i-1][j] = '@'
      return Position(i-1, j)
    }

    for (ii in (0..i-1).reversed())
      if      (map[ii][j] == '#') break
      else if (map[ii][j] == '.') {
        map[i  ][j] = '.'
        map[i-1][j] = '@'
        map[ii ][j] = 'O'
        return Position(i-1, j)
      }

    return Position(i,j)
  }

  private fun left(map: Array<CharArray>, position: Position): Position {
    val i = position.first
    val j = position.second

    if (map[i][j-1] == '#') return Position(i,j)

    if (map[i][j-1] == '.') {
      map[i][j  ] = '.'
      map[i][j-1] = '@'
      return Position(i, j-1)
    }

    for (jj in (0..j-1).reversed())
      if      (map[i][jj] == '#') break
      else if (map[i][jj] == '.') {
        map[i][j  ] = '.'
        map[i][j-1] = '@'
        map[i][jj ] = 'O'
        return Position(i, j-1)
      }

    return Position(i,j)
  }

  private fun right(map: Array<CharArray>, position: Position): Position {
    val i = position.first
    val j = position.second

    if (map[i][j+1] == '#') return Position(i,j)

    if (map[i][j+1] == '.') {
      map[i][j  ] = '.'
      map[i][j+1] = '@'
      return Position(i, j+1)
    }

    for (jj in j+1..<map[i].size)
      if      (map[i][jj] == '#') break
      else if (map[i][jj] == '.') {
        map[i][j  ] = '.'
        map[i][j+1] = '@'
        map[i][jj ] = 'O'
        return Position(i, j+1)
      }

    return Position(i,j)
  }
}