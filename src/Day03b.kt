import java.io.File

class Day03b : Day{

  companion object {
    private val REGEX = Regex("(mul)\\((\\d{1,3}),(\\d{1,3})\\)|(do)\\(\\)|(don't)\\(\\)")
  }

  override fun run() {
    val input = File("data/sample03b.txt").readText()

    var isActive = true
    var total = 0
    for (it in REGEX.findAll(input)) {
      val groups = it.groups.filterNotNull().toList()
      when (groups[1].value) {
        "do"    -> isActive = true
        "don't" -> isActive = false
        "mul"   -> if (isActive) {
          val op1 = groups[2].value.toInt()
          val op2 = groups[3].value.toInt()
          total += op1 * op2
        }
      }
    }

    println(total)
  }
}