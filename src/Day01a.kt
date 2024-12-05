import java.io.File
import kotlin.math.abs

class Day01a : Day{

  override fun run() {
    val left  = mutableListOf<Int>()
    val right = mutableListOf<Int>()
    File("src/input01.txt").useLines { lines ->
      lines.forEach {
        val splited = it.split(" ")
        left.add(splited.first().toInt())
        right.add(splited.last().toInt())
      }
    }

    left.sort()
    right.sort()
    var total = 0L
    left.zip(right).forEach{ pair ->
      total += abs(pair.first - pair.second)
    }

    println(total)
  }
}