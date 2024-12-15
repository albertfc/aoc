import java.io.File

class Day09b: Day {

  override fun run() {
    val input = File("data/input09.txt").readText().toCharArray()

    val blocksSizeList = mutableListOf<Int>()
    val blocksIdxList= mutableListOf<Int>()
    val blocksList = mutableListOf<Int>()
    for (i in input.indices) {
      val id = if (i % 2 == 0) (i/2) else -1
      val size = input[i].code - '0'.code
      if (id != -1) {
        blocksSizeList.add(size)
        blocksIdxList.add(blocksList.size)
      }
      for (j in 0..<size) {
        blocksList.add(id)
      }
    }

    val blocks = blocksList.toTypedArray()
    val blocksSize = blocksSizeList.toTypedArray()
    val blocksIdx = blocksIdxList.toTypedArray()
    var blockId = input.size / 2

    while (blockId >= 0) {
      var found = false
      var freeSpaceSize = 0
      var freeSpaceIdx = 0
      for (i in 0..<blocksIdx[blockId]) {
        if (blocks[i] == -1) {
          freeSpaceSize += 1
          freeSpaceIdx = i
          if (blocksSize[blockId] == freeSpaceSize) {
            found = true
            break
          }
        } else {
          freeSpaceSize = 0
        }
      }

      if (found) {
        for (i in freeSpaceIdx - freeSpaceSize + 1..freeSpaceIdx) {
          blocks[i] = blockId
        }
        for (i in blocksIdx[blockId]..<blocksIdx[blockId] + blocksSize[blockId]) {
          blocks[i] = -1
        }
      }

      blockId--
    }

    var checksum = 0L
    for (i in blocks.indices) {
      if (blocks[i] != -1)  checksum += i * blocks[i]
    }

    println(checksum)
  }
}