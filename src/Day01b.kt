import java.io.File

class Day01b : Day{

  override fun run() {
    val left  = mutableListOf<Int>()
    val right = mutableMapOf<Int,Int>()
    File("src/input01.txt").useLines { lines ->
      lines.forEach {
        val splited = it.split(" ")
        left.add(splited.first().toInt())
        val value = splited.last().toInt()
        right[value] = runCatching { right.getValue(value) }.getOrDefault(0) + 1
      }
    }

    var total = 0L
    left.forEach{
      total += runCatching { it * right.getValue(it) }.getOrDefault(0)
    }

    println(total)
  }
}