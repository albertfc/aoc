import java.io.File
import kotlin.math.abs

class Day01a : Day{

  override fun run() {
    val left  = mutableListOf<Int>()
    val right = mutableListOf<Int>()
    File("data/input01.txt").useLines { lines ->
      lines.forEach {
        val splited = it.split(" ")
        left.add(splited.first().toInt())
        right.add(splited.last().toInt())
      }
    }

    left.sort()
    right.sort()
    val total = left.zip(right).fold(0) { acc, pair ->
      acc + abs(pair.first - pair.second)
    }

    println(total)
  }
}