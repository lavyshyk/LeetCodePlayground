package com.lavyshyk.leetcodeplayground.arrays

import android.graphics.Interpolator
import kotlinx.coroutines.flow.merge

class Arrays {
    /**
     * Given an array of integers arr, return true if and only if it is a valid mountain array.
     *
     * Recall that arr is a mountain array if and only if:
     *
     * arr.length >= 3
     * There exists some i with 0 < i < arr.length - 1 such that:
     * arr[0] < arr[1] < ... < arr[i - 1] < arr[i]
     * arr[i] > arr[i + 1] > ... > arr[arr.length - 1]
     */

    fun isValidMountainArray(arr: IntArray): Boolean {
        var isIncreasing = false
        var isDecreasing = false
        var maxValue = 0

        for (i in 0 until arr.size - 1) {
            when {
                arr[i] < arr[i + 1] && isDecreasing.not() -> {
                    isIncreasing = true
                    maxValue = arr[i + 1]
                }

                arr[i] > arr[i + 1] && maxValue > arr[i + 1] -> {
                    isDecreasing = true
                }

                else -> return false
            }

        }
        return maxValue != 0 && isIncreasing && isDecreasing
    }
    

    fun removeDuplicates(nums: IntArray): Int {

        var lenght = nums.size

        for (i in lenght - 2 downTo 0) {
            if (nums[i] == nums[i + 1]) {
                for (j in i until lenght - 1) {
                    nums[j] = nums[j + 1]
                }
                lenght--
            }
        }

        return lenght
    }

    fun optimazedRemoveDublicates(nums: IntArray): Int {
        if (nums.isEmpty()) return 0
        var k = 1
        for (i in 1 until nums.size) {
            if (nums[i] != nums[i - 1]) {
                nums[k] = nums[i]
            }
            k++

        }
        return k
    }
    //nums = [0,1,0,3,12]

    fun moveZeroes(nums: IntArray): Unit {
        var k = 0
        for (i in 0 until nums.size) {
            if (nums[i] != 0) {
                if (i != k) {
                    nums[k] = nums[i]
                    nums[i] = 0
                }
                k++
            }
        }
    }

    fun sortArrayByParity(nums: IntArray): IntArray {
        var k = 0
        for (i in 0 until nums.size) {
            if (nums[i] % 2 == 0) {
                if (k != i) {
                    val temp = nums[k]
                    nums[k] = nums[i]
                    nums[i] = temp

                }
                k++
            }
        }
        return nums
    }

    fun replaceElements(arr: IntArray): IntArray {
        var maxRight = -1

        for (i in arr.size - 1 downTo 0) {
            val temp = arr[i]
            arr[i] = maxRight
            maxRight = maxOf(maxRight, temp)
        }
        return arr
    }

    fun removeElement(nums: IntArray, `val`: Int): Int {
        var sizeResult = nums.size
        var currentIndex = 0
        if (nums.isEmpty()) return 0

        for (i in 0 until nums.size) {
            if (nums[i] != `val`) {
                nums[currentIndex] = nums[i]
                currentIndex++
            } else {
                sizeResult--
            }
        }

        return sizeResult
    }

    fun removeElementImproved(nums: IntArray, `val`: Int): Int {
        var currentIndex = 0

        for (i in nums.indices) {
            if (nums[i] != `val`) {
                nums[currentIndex] = nums[i]
                currentIndex++
            }
        }

        return currentIndex
    }

    fun heightChecker(heights: IntArray): Int {
//        val newArray = mergeSort(heights)
        val newArray = heights.sorted().toIntArray()

        var result = 0
        for (i in heights.indices) {
            if (heights[i] != newArray[i]) {
                result++
            }
        }
        return result

    }

    fun heightCheckerImproved(heights: IntArray): Int {
        val maxPossibleHeight = 100
        val heightCounts = IntArray(maxPossibleHeight + 1)

        // Step 1: Count the frequency of each height (O(N))
        for (h in heights) {
            if (h <= maxPossibleHeight) {
                heightCounts[h]++
            }
        }

        var result = 0
        var expectedHeight = 1 // Start checking from the smallest possible height (1)

        // Step 2 & 3: Iterate through the original array and determine the expected height on the fly (O(N))
        for (currentHeight in heights) {
            // Find the next expected height that still has remaining students
            while (expectedHeight <= maxPossibleHeight && heightCounts[expectedHeight] == 0) {
                expectedHeight++
            }

            // At this point, expectedHeight holds the height of the student who SHOULD be next.
            // Since we are iterating through the original array, we compare the current student
            // with the student who is *expected* to be in this position.

            // Step 4: Compare current height with the expected height
            if (currentHeight != expectedHeight) {
                result++
            }

            // Step 5: Mark one instance of the expected height as used
            if (expectedHeight <= maxPossibleHeight) {
                heightCounts[expectedHeight]--
            }
        }

        return result

    }

    fun mergeSort(array: IntArray): IntArray {
        if (array.size <= 1) return array

        val middle = array.size / 2
        val left = array.copyOfRange(0, middle)
        val right = array.copyOfRange(middle, array.size)
        val sortedLeft = mergeSort(left)
        val sortedRight = mergeSort(right)
        return merge(sortedLeft, sortedRight)
    }

    fun merge(leftArray: IntArray, rightArray: IntArray): IntArray {
        var leftIndex = 0
        var rightIndex = 0
        var resultIndex = 0
        val result = IntArray(leftArray.size + rightArray.size)

        while (leftIndex < leftArray.size && rightIndex < rightArray.size) {
            if (leftArray[leftIndex] <= rightArray[rightIndex]) {
                result[resultIndex] = leftArray[leftIndex]
                leftIndex++
            } else {
                result[resultIndex] = rightArray[rightIndex]
                rightIndex++
            }
            resultIndex++
        }
        while (leftIndex < leftArray.size) {
            result[resultIndex] = leftArray[leftIndex]
            leftIndex++
            resultIndex++
        }
        while (rightIndex < rightArray.size) {
            result[resultIndex] = rightArray[rightIndex]
            rightIndex++
            resultIndex++
        }
        return result
    }

    fun thirdMax(nums: IntArray): Int {
        var first: Long = Long.MIN_VALUE
        var second: Long = Long.MIN_VALUE
        var third: Long = Long.MIN_VALUE

        for (num in nums) {
            val n = num.toLong()

            // Skip duplicates
            if (n == first || n == second || n == third) continue

            when {
                n > first -> {
                    third = second
                    second = first
                    first = n
                }

                n > second -> {
                    third = second
                    second = n
                }

                n > third -> {
                    third = n
                }
            }
        }

        return if (third == Long.MIN_VALUE) first.toInt() else third.toInt()


    }

    fun findDisappearedNumbers(nums: IntArray): List<Int> {

        // Mark numbers as seen by negating the value at their corresponding index
        for (num in nums) {
            val index = kotlin.math.abs(num) - 1
            if (nums[index] > 0) {
                nums[index] = -nums[index]
            }
        }
        println("modified nums ${nums.joinToString("|")}")

        // Collect indices where the value is still positive
        val result = mutableListOf<Int>()
        for (i in nums.indices) {
            if (nums[i] > 0) {
                result.add(i + 1)
            }
        }
        return result

    }

    fun groupNumbersEfficient(nums: IntArray): List<List<Int>> {
        // A map to store groups, where the key is a Long representing the canonical form
        // and the value is the list of numbers belonging to that group.
        val groups = mutableMapOf<Long, MutableList<Int>>()

        // An array to count occurrences of digits 1-9 for each number.
        // Index 0 for digit 1, index 1 for digit 2, ..., index 8 for digit 9.
        val digitCounts = IntArray(9)

        for (num in nums) {
            // Reset digit counts for the new number.
            digitCounts.fill(0)

            // --- Step 1: Create the canonical key using arithmetic ---
            var tempNum = num

            if (tempNum == 0) {
                // Handle the edge case of the number 0.
                // Depending on requirements, it could form its own group or be ignored.
                // Here, we'll give it a unique key of 0.
                groups.getOrPut(0L) { mutableListOf() }.add(num)
                continue
            }
            if (tempNum < 0) tempNum = -tempNum // Work with positive numbers

            // Count the non-zero digits of the current number.
            while (tempNum > 0) {
                val digit = tempNum % 10
                if (digit != 0) {
                    // Decrement digit by 1 to use as a 0-based index.
                    digitCounts[digit - 1]++
                }
                tempNum /= 10
            }

            // Build the canonical key (as a Long to avoid overflow) from the counts.
            var key = 0L
            for (i in 0..8) { // Corresponds to digits 1 through 9
                repeat(digitCounts[i]) {
                    key = key * 10 + (i + 1)
                }
            }
            // --- End of key creation ---

            // --- Step 2: Add the number to the corresponding group ---
            groups.getOrPut(key) { mutableListOf() }.add(num)
        }

        // --- Step 3: Return the grouped values ---
        return groups.values.toList()
    }

    fun sortedSquares(nums: IntArray): IntArray {

        val n = nums.size
        val result = IntArray(n)
        var left = 0
        var right = n - 1
        var pos = n - 1

        while (left <= right) {
            val leftSquare = nums[left] * nums[left]
            val rightSquare = nums[right] * nums[right]

            if (leftSquare > rightSquare) {
                result[pos] = leftSquare
                left++
            } else {
                result[pos] = rightSquare
                right--
            }
            pos--
        }

        return result
    }

    ///  bad On3
    fun threeSum(nums: IntArray): List<Triple<Int, Int, Int>> {
        val result = mutableListOf<Triple<Int, Int, Int>>()
        var firstPointer = 0
        var secondPointer = 0

        for (i in 0..nums.size - 3) {
            firstPointer = i
            for (k in i + 1..nums.size - 2) {
                secondPointer = k

                for (j in k + 1..nums.size - 1) {
                    val sum = nums[i] + nums[k] + nums[j]
                    if (sum == 0) {
                        result.add(Triple(nums[i], nums[k], nums[j]))
                    }

                }
            }
        }

        return result
    }

    fun threeSumImprove(nums: IntArray): List<List<Int>> {
        val result = mutableListOf<List<Int>>()
        val sorted = nums.sorted()

        for (i in 0 until sorted.size - 2){
            if(i > 0 && sorted[i] == sorted[i - 1 ]) continue
            var left = i + 1
            var right = sorted.lastIndex
            while (left < right) {
                val sum = sorted[i] + sorted[left] + sorted[right]

                when {
                    sum == 0 -> {
                        result.add(listOf(sorted[i], sorted[left], sorted[right]))

                        // Move both pointers and skip duplicates
                        left++
                        right--
                        while (left < right && sorted[left] == sorted[left - 1]) left++
                        while (left < right && sorted[right] == sorted[right + 1]) right--
                    }
                    sum < 0 -> left++
                    else -> right--  // because sorted
                }
            }
        }
            return result
    }


}

fun main() {
    val testList = intArrayOf(0, 1, 2, 1, 2)
    println("test list 1 : length = ${testList.count()} list = ${testList.joinToString(",")}")
    val arrays = Arrays()
    println("isValidMountainArray = ${arrays.isValidMountainArray(testList)}")

    val testRemoveDuplicates = intArrayOf(-13, 12, 12, 83, 96, 96, 100)
    println("testRemoveDuplicates = $testRemoveDuplicates")
    val result = arrays.removeDuplicates(testRemoveDuplicates)
    println("result = $result")
    var nums = intArrayOf(0, 1, 0, 3, 0, 12, 0, 15, 17, 89)

    arrays.moveZeroes(nums)

    val mm = intArrayOf(3, 4, 6, 7, 8, 9, 13, 56)
    val res = arrays.sortArrayByParity(mm)
    println("sortArrayByParty = ${res.joinToString(",")}")


    val arr = intArrayOf(17, 18, 5, 4, 6, 1)
    val rees = arrays.replaceElements(arr)
    println("replaceElements = ${rees.joinToString(",")}")


    val nummm = intArrayOf(3, 2, 1, 3, 3, 5, 5, 6)
    val resThirdMax = arrays.thirdMax(nummm)
    println("thirdMax = ${resThirdMax}")

    val uu = arrays.findDisappearedNumbers(nummm)
    println("findDisappears $uu")
}