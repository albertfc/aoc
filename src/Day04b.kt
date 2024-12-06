import java.io.File

class Day04b : Day {

  override fun run() {

    val wordsOrig = File("data/input04.txt").readLines().toTypedArray()
    val words = Array(wordsOrig.size + 6) { CharArray(wordsOrig[0].length + 6) }
    // padding
    for (i in wordsOrig.indices) {
      for (j in wordsOrig[i].indices) {
        words[i+3][j+3] = wordsOrig[i][j]
      }
    }

    var total = 0
    for (i in 3..<words.size - 3) {
      for (j in 3..<words[i].size - 3) {
        total += if (
             (words[i  ][j  ] == 'A' && words[i-1][j-1] == 'M' && words[i+1][j+1] == 'S' && words[i+1][j-1] == 'M' && words[i-1][j+1] == 'S')
          || (words[i  ][j  ] == 'A' && words[i-1][j-1] == 'S' && words[i+1][j+1] == 'M' && words[i+1][j-1] == 'M' && words[i-1][j+1] == 'S')
          || (words[i  ][j  ] == 'A' && words[i-1][j-1] == 'S' && words[i+1][j+1] == 'M' && words[i+1][j-1] == 'S' && words[i-1][j+1] == 'M')
          || (words[i  ][j  ] == 'A' && words[i-1][j-1] == 'M' && words[i+1][j+1] == 'S' && words[i+1][j-1] == 'S' && words[i-1][j+1] == 'M')
        ) 1 else 0
      }
    }

    println(total)
  }
}