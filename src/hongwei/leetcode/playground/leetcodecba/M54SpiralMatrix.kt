package hongwei.leetcode.playground.leetcodecba

class M54SpiralMatrix {
    companion object {
        const val OFF = 444
    }

    fun test() {
        val input = arrayOf(intArrayOf(1, 2, 3), intArrayOf(4, 5, 6), intArrayOf(7, 8, 9))
        val answer = listOf(1, 2, 3, 6, 9, 8, 7, 4, 5)

        val result = spiralOrder(input)
        print("pass: ${result == answer}, result: $result")
    }

    enum class Direction {
        RIGHT, DOWN, LEFT, UP;

        fun getNext(): Direction = when (this) {
            RIGHT -> DOWN
            DOWN -> LEFT
            LEFT -> UP
            UP -> RIGHT
        }

        fun getXAfterMove(x: Int): Int = when (this) {
            RIGHT -> x + 1
            LEFT -> x - 1
            else -> x
        }

        fun getYAfterMove(y: Int): Int = when (this) {
            DOWN -> y + 1
            UP -> y - 1
            else -> y
        }
    }

    fun spiralOrder(matrix: Array<IntArray>): List<Int> {
        var direction = Direction.RIGHT
        val sizeX = matrix[0].size
        val sizeY = matrix.size
        val maxCount = sizeX * sizeY
        val list = mutableListOf<Int>()

        var x = 0
        var y = 0

        list.add(matrix[0][0])
        matrix[y][x] = OFF
        while (list.size < maxCount) {
            while (direction.getXAfterMove(x) in 0 until sizeX
                && direction.getYAfterMove(y) in 0 until sizeY
                && matrix[direction.getYAfterMove(y)][direction.getXAfterMove(x)] != OFF
            ) {
                x = direction.getXAfterMove(x)
                y = direction.getYAfterMove(y)
                list.add(matrix[y][x])
                matrix[y][x] = OFF
            }
            direction = direction.getNext()
        }
        return list
    }

    fun spiralOrder2(matrix: Array<IntArray>): List<Int> {
        var i = 0
        var j = 0
        val list = mutableListOf<Int>()

        var x0 = 0
        var y0 = 0
        var x1 = matrix[0].size - 1
        var y1 = matrix.size - 1

        while (x0 <= x1 && y0 <= y1) {
            y0++
            while (i <= x1) {
//                println("d1-- p($i,$j) is ${matrix[j][i]}, boundary: x[$x0~$x1], y[$y0~$y1]")
                list.add(matrix[j][i++])
            }
            i = x1
            j++
            x1--

            while (j <= y1) {
//                println("d2-- p($i,$j) is ${matrix[j][i]}, boundary: x[$x0~$x1], y[$y0~$y1]")
                list.add(matrix[j++][i])
            }
            j = y1
            i--
            y1--

            while (i >= x0) {
//                println("d3-- p($i,$j) is ${matrix[j][i]}, boundary: x[$x0~$x1], y[$y0~$y1]")
                list.add(matrix[j][i--])
            }
            i = x0
            j--
            x0++

            while (j >= y0) {
//                println("d4-- p($i,$j) is ${matrix[j][i]}, boundary: x[$x0~$x1], y[$y0~$y1]")
                list.add(matrix[j--][i])
            }
            j = y0
            i++
        }
        return list
    }
}

/*
https://leetcode.com/problems/spiral-matrix/
Given an m x n matrix, return all elements of the matrix in spiral order.



Example 1:


Input: matrix = [[1,2,3],[4,5,6],[7,8,9]]
Output: [1,2,3,6,9,8,7,4,5]
Example 2:


Input: matrix = [[1,2,3,4],[5,6,7,8],[9,10,11,12]]
Output: [1,2,3,4,8,12,11,10,9,5,6,7]


Constraints:

m == matrix.length
n == matrix[i].length
1 <= m, n <= 10
-100 <= matrix[i][j] <= 100
 */