import java.io.File

class Day09a: Day {

  override fun run() {
    val input = File("data/input09.txt").readText().toCharArray()

    val blocksList = mutableListOf<Int>()
    for (i in input.indices) {
      val id = if (i % 2 == 0) (i/2) else -1
      for (j in 0..<input[i].code-'0'.code) {
        blocksList.add(id)
      }
    }

    val blocks = blocksList.toTypedArray()
    val result = mutableListOf<Int>()
    var i = 0
    var j =  blocks.size-1
    while (i <= j) {
      if (blocks[i] == -1) {
        while (j >= 0 && blocks[j] == -1) {j--}
        result.add(blocks[j])
        j--
      } else {
        result.add(blocks[i])
      }
      i++;
    }

    var checksum = 0L
    for ((id, index) in result.zip(result.indices)) {
      checksum += id * index
    }

    println(checksum)
  }
}