import java.io.File

class Day01b : Day{

  override fun run() {
    val left  = mutableListOf<Int>()
    val right = mutableMapOf<Int,Int>()
    File("data/input01.txt").useLines { lines ->
      lines.forEach {
        val split = it.split(" ")
        left.add(split.first().toInt())
        val key = split.last().toInt()
        right[key] = (right[key] ?: 0) + 1
      }
    }

    val total = left.fold(0){ acc, key -> acc +  key * (right[key] ?: 0) }

    println(total)
  }
}