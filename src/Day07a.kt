import java.io.File

class Day07a: Day {

  override fun run() {
    var total = 0L
    File("data/input07.txt").forEachLine { line ->
      val split = line .split(":")
      val left = split.first().toLong()
      val right = split.last().split(" ").filter{ it.isNotEmpty() }.map{ it.toLong() }
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
         isTrue(left, first + right.first(), right.subList(1, right.size))
      || isTrue(left, first * right.first(), right.subList(1, right.size))
    }
  }
}