package hongwei.leetcode.playground.leetcode

class _1146SnapshotArray {
    fun test() {
        val sa = SnapshotArray(2)
        sa.snap()
        sa.set(1, 17)
        sa.set(0, 20)
        sa.snap()
        sa.snap()
        sa.snap()
    }
}

class SnapshotArray(val length: Int) {
    companion object {
        const val STEP = 5
    }

    val snaps: MutableList<Any> = mutableListOf()

    val current: IntArray = IntArray(length)

    var snapCount = 0

    var lastSnap = -1

    fun set(index: Int, `val`: Int) {
        current[index] = `val`
        lastSnap = -1
    }

    fun snap(): Int {
        if (lastSnap < 0) {
            val copy: IntArray = IntArray(length).apply {
                forEachIndexed { index, i -> this[index] = current[index] }
            }
            snaps.add(copy)
            lastSnap = snapCount
        } else {
            snaps.add(lastSnap)
        }
        return snapCount++
    }

    fun get(index: Int, snap_id: Int): Int {
        val snap = snaps[snap_id]
        if (snap is IntArray) {
            return snap[index]
        } else if (snap is Int) {
            return (snaps[snap] as IntArray)[index]
        } else {
            return 0
        }
    }
}