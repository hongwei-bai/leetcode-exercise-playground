package hongwei.leetcode.playground.common

abstract class AbstractProblem<S, T, O> {
    abstract val input1: S
    abstract val input2: T
    abstract val expectedOutput: O

    protected abstract val data: Array<Array<Any>>


}