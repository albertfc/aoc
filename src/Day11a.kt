import java.io.File

class Day11a: Day {

  override fun run() {
    val stones = File("data/input11.txt").readText()
      .split(" ")
      .filter(String::isNotEmpty)
      .map(String::toLong)
      .toMutableList()

    for (i in 1..25) {
      val iterator = stones.listIterator()
      while (iterator.hasNext()) {
        val current = iterator.next()
        iterator.remove()
        if (current == 0L) {
          iterator.add(1L)
        } else if (current.toString().length % 2 == 0) {
          val string = current.toString()
          val left = string.substring(0, string.length/2)
          val right = string.substring(string.length/2, string.length)
          iterator.add(left.toLong())
          iterator.add(right.toLong())
        } else {
          iterator.add(current * 2024)
        }
      }
    }

    println(stones.size)
  }
}