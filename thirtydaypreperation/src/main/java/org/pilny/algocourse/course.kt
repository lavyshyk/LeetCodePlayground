package org.pilny.algocourse


fun main() {
//    println("work")
//    val word = "ventriloquism"
//    println(reverse(word))

    val intArray = intArrayOf(-8, -7, 0, 1, 7, 12)
    println(squaresOfSortedArray(intArray).joinToString())
}


///arrays

//reverse a string

fun reverse(word: String): String {

    val asArray = word.toCharArray()
    var left = 0
    var right = asArray.lastIndex
    while (left < right) {
        asArray[left] = asArray[right].also { asArray[right] = asArray[left] }
        left++
        right--
    }

    return asArray.joinToString(separator = "")
}

fun squaresOfSortedArray(array: IntArray): IntArray {

    val result = IntArray(array.size)
    var left = 0
    var right = array.lastIndex
    var position = right

    while (left <= right) {
        result[position] = when {
            kotlin.math.abs(array[left]) > kotlin.math.abs(array[right]) -> {
                (array[left] * array[left]).also { left++ }
            }

            else -> {
                array[right] * array[right].also { right-- }
            }
        }
        position--
    }


    return result
}

fun containsDuplicate(nums: IntArray): Boolean {
    val set = mutableSetOf<Int>()
    for (i in nums) {
        if (i in set) return true
        else set.add(i)
    }
    return false
}

fun findMaxSumSubArrayBrutForce(nums: IntArray, sizeOfSubArray: Int): Int {
    var result = Int.MIN_VALUE

    for (i in 0..nums.size - sizeOfSubArray) {
        var sum = 0
        for (j in i until i + sizeOfSubArray) {
            sum += nums[j]
        }

        result = maxOf(result, sum)

    }

    return result
}

fun findMaxSumOfSubarrayBySlidingWindow(nums: IntArray, sizeOfSubArray: Int): Int {
    var maxSum = Int.MIN_VALUE
    var windowSum = 0
    for (i in 0 until sizeOfSubArray) {
        windowSum += nums[i]
    }
    maxSum = windowSum
    for (k in sizeOfSubArray until nums.size) {
        windowSum += +nums[k] - nums[k - sizeOfSubArray]
        maxSum = maxOf(maxSum, windowSum)
    }
    return windowSum
}


// check second day

//On2 -time  O1 - space
fun maxSumOfTwoBruteForce(nums: IntArray): Int {
    var sum = 0
    for (i in nums) {
        for (k in 1 until nums.size) {
            sum = maxOf(sum, i + k)
        }
    }
    return sum
}

fun sumOfTwoBruteForce(nums: IntArray, target: Int): Pair<Int, Int> {
    var sum = 0
    for (i in nums.indices) {
        for (k in i + 1 until nums.size) {
            sum = nums[i] + nums[k]
            if (sum == target) return i to k
        }
    }
    return -1 to -1
}

fun sumOfTwoOptimized(nums: IntArray, target: Int): Pair<Int, Int> {
    var sum = 0
    val map = mutableMapOf<Int, Int>()

    for (i in nums.indices) {
        val complement = target - nums[i]
        if (map.containsKey(complement)) return map[complement]!! to i

        map[nums[1]] = i

    }
    return -1 to -1
}

fun getSubArrayOfMaxSumBruteForce(array: IntArray): Int {
    var sum = 0
    for (i in array.indices) {
        var temp = 0
        for (j in i until array.size) {
            temp += array[j]
            sum = maxOf(sum, temp)

        }
    }
    return sum
}
fun getSubArrayOfMaxSlidingWindow(array: IntArray): Int {
    var sum = 0

    for (i in array.indices) {
        var temp = 0
        for (j in i until array.size) {
            temp += array[j]
            sum = maxOf(sum, temp)

        }
    }
    return sum
}

//Optional Enhancement: Kadane + Track Subarray
fun maxSubArrayWithIndices(nums: IntArray): Triple<Int, Int, Int> {
    var maxSoFar = nums[0]
    var currentMax = nums[0]
    var start = 0
    var end = 0
    var tempStart = 0

    for (i in 1 until nums.size) {
        if (nums[i] > currentMax + nums[i]) {
            currentMax = nums[i]
            tempStart = i
        } else {
            currentMax += nums[i]
        }

        if (currentMax > maxSoFar) {
            maxSoFar = currentMax
            start = tempStart
            end = i
        }
    }

    return Triple(maxSoFar, start, end)
}


// product of array except self
//O(n2) and space On
fun productOfArrayBrute(nums: IntArray): IntArray{
    val result = IntArray(nums.size)
    for(i in nums.indices){
        var  product = 1
        for(j in nums.indices){
             if(j != i) {
                 product *= nums[j]
             }
        }
        result[i] = product

    }

    return result
}

//efficient way.

fun productOfArrayBestApproach(nums: IntArray): IntArray {
    val result = IntArray(nums.size)
    var baseProduct = 1
    for(i in nums.indices){
        baseProduct *= nums[i]
    }
    for(i in nums.indices){
        result[i] = baseProduct/nums[i] // common issue
    }
    return result
}

fun productExceptSelf(nums: IntArray): IntArray {
    val result = IntArray(nums.size)

    // Step 1: prefix products
    result[0] = 1
    for (i in 1 until nums.size) {
        result[i] = result[i - 1] * nums[i - 1]
    }

    // Step 2: suffix products
    var suffix = 1
    for (i in nums.size - 1 downTo 0) {
        result[i] *= suffix
        suffix *= nums[i]
    }

    return result
}

