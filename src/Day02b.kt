import java.io.File

class Day02b : Day{

  override fun run() {
    val levels = mutableListOf<MutableList<Int>>()
    File("data/input02.txt").useLines { lines ->
      lines.forEach { line ->
        val split = line.split(" ")
          .filter { it != " " }
          .map { it.toInt() }
          .toMutableList()
        levels.add(split)
      }
    }

    val total = levels.fold(0) {acc, level ->
      acc + if (level.isSafe()) 1 else 0
    }

    println(total)
  }

  private fun MutableList<Int>.isSafe(): Boolean {
    var isSafe = this.isSafeInner()
    val iterator = this.listIterator()
    while (iterator.hasNext() && !isSafe) {
      val element = iterator.next()
      iterator.remove()
      isSafe = this.isSafeInner()
      iterator.add(element)
    }
    return isSafe
  }


  private fun List<Int>.isSafeInner(): Boolean {
    var isSafe = true
    for (i in 1..<size) {
      if (!isSafe) break
      isSafe = (this[i] - this[i-1]) >= 1
            && (this[i] - this[i-1]) <= 3
    }

    if (isSafe) return true

    isSafe = true
    for (i in 1..<size) {
      if (!isSafe) return false
      isSafe = (this[i-1] - this[i]) >= 1
            && (this[i-1] - this[i]) <= 3
    }

    return isSafe
  }

}