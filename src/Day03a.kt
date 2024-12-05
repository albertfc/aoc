import java.io.File

class Day03a : Day{

  companion object {
    private val REGEX = Regex("mul\\((\\d{1,3}),(\\d{1,3})\\)")
  }

  override fun run() {
    val input = File("data/input03.txt").readText()

    val total = REGEX.findAll(input).map {
      val op1 = it.groups[1]!!.value.toInt()
      val op2 = it.groups[2]!!.value.toInt()
      op1 * op2
    }.sum()

    println(total)
  }
}