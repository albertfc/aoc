import java.io.File

class Day11b: Day {

  override fun run() {
    val stones = File("data/input11.txt").readText()
      .split(" ")
      .filter(String::isNotEmpty)
      .map(String::toLong)

    var total = 0L
    stones.forEach{
      total += length(it, 75)
    }

    println(total)
  }

  private val mem = mutableMapOf<Long, MutableMap<Int, Long>>()

  private fun length(value: Long, blinks: Int): Long {

    if (blinks == 0) return 1L

    mem[value]?.get(blinks)?.let { return it }

    val result: Long
    if (value == 0L) {
      result = length(1, blinks - 1)
    } else if (value.toString().length % 2 == 0) {
      val string = value.toString()
      val left = string.substring(0, string.length / 2)
      val right = string.substring(string.length / 2, string.length)
      result = length(left.toLong(), blinks - 1) + length(right.toLong(), blinks - 1)
    } else {
      result = length(value * 2024, blinks - 1)
    }
    mem[value]?.set(blinks, result) ?: run { mem[value] = mutableMapOf(blinks to result) }
    return result
  }
}