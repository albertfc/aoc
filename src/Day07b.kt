import java.io.File

class Day07b: Day {

  override fun run() {
    var total = 0L
    File("data/input07.txt").forEachLine { line ->
      val split = line.split(":")
      val left = split.first().toLong()
      val right = split.last().split(" ").filter(String::isNotEmpty).map(String::toLong)
      total += if (isTrue(left, right.first(), right.subList(1, right.size))) left else 0
    }
    println(total)
  }

  private fun isTrue(left: Long, first: Long, right: List<Long>): Boolean {
    return if (right.isEmpty()) {
      left == first
    } else if (first > left) {
      false
    } else {
      val newRight = right.subList(1, right.size)
      val newFirst = right.first()
         isTrue(left, first + newFirst, newRight)
      || isTrue(left, first * newFirst, newRight)
      || isTrue(left, "%d%d".format(first, newFirst).toLong(), newRight)
    }
  }
}