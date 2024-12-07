import java.io.File

class Day05a : Day{

  override fun run() {
    var action = this::addRule
    File("data/sample05a.txt").forEachLine { line->
      if (line.isEmpty()) {
        action = this::processPages
      } else {
        action(line)
      }
    }
    println(total)
  }

  private val rules = mutableMapOf<Int,MutableSet<Int>>()
  private var total = 0

  private fun addRule(line: String) {
    val split = line.split("|")
    val a = split.first().toInt()
    val b = split.last().toInt()

    rules[a]?.add(b) ?: run{ rules[a] = mutableSetOf(b) }
  }

  private fun processPages(line: String) {
    val update = line.split(",").map{ it.toInt() }.toTypedArray()
    val before = mutableSetOf<Int>()
    for (page in update) {
      if (before.intersect(rules[page] ?: emptySet()).isNotEmpty()) {
        return
      }
      before.add(page)
    }
    total += update[update.size/2]
  }
}