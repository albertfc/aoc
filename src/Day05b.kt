import java.io.File

class Day05b : Day{

  override fun run() {
    var action = this::addRule
    File("data/input05.txt").forEachLine { line->
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
    val fixedUpdate = mutableListOf<Int>()
    val before = mutableSetOf<Int>()
    var fixed = false
    for (page in update) {
      val intersect = before.intersect(rules[page] ?: emptySet())
      if (intersect.isNotEmpty()) {
        fix(fixedUpdate, intersect, page)
        fixed = true
      } else {
        fixedUpdate.add(page)
      }
      before.add(page)
    }
    if (fixed) {
      total += fixedUpdate[update.size / 2]
    }
  }

  private fun fix(list: MutableList<Int>, intersect: Set<Int>, page: Int) {
    for (i in list.indices) {
      if (intersect.contains(list[i])) {
        val iterator = list.listIterator(i)
        iterator.add(page)
        return
      }
    }
  }
}