package org.pilny.gokaem_algorithms

// recursion fun sum

fun sum(array: IntArray): Int {
    if (array.size == 1) return array.first()
    return array.first() + sum(array.copyOfRange(1, array.size))
}


fun coutIteminList(array: IntArray): Int {
    if (array.isEmpty()) return 0
    return 1 + coutIteminList(array.copyOfRange(1, array.size))
}

fun maxValue(array: IntArray): Int {
    if (array.size == 1) return array.first()

    val maxValue =
        if (array.first() > maxValue(array.copyOfRange(1, array.size))) array.first() else maxValue(
            array.copyOfRange(1, array.size)
        )
    return maxValue
}

fun binarySearch(array: IntArray, value: Int): Int {
    if (array.isEmpty()) return -1
    val mid = array.size / 2
    return when {
        array[mid] == value -> mid
        value > array[mid] ->  binarySearch(array.copyOfRange(mid + 1, array.size), value)
        else ->  binarySearch(array.copyOfRange(0, mid), value)
    }
}

fun multyplyTabel(array: IntArray){
    array.forEach { multyplaer ->
        array.forEach { second ->
            println("$second * $multyplaer = ${multyplaer * second}")
        }
        println("---------------")
    }
}


///improved by LLM


fun binarySearch(
    array: IntArray,
    value: Int,
    left: Int = 0,
    right: Int = array.lastIndex
): Int {
    if (left > right) return -1

    val mid = left + (right - left) / 2

    return when {
        array[mid] == value -> mid
        value < array[mid] ->
            binarySearch(array, value, left, mid - 1)
        else ->
            binarySearch(array, value, mid + 1, right)
    }
}



fun sum(array: IntArray, index: Int = 0): Int {
    if (index == array.size) return 0
    return array[index] + sum(array, index + 1)
}

fun countItems(array: IntArray, index: Int = 0): Int {
    if (index == array.size) return 0
    return 1 + countItems(array, index + 1)
}

fun maxValue(array: IntArray, index: Int = 0): Int {
    require(array.isNotEmpty())
    if (index == array.lastIndex) return array[index]

    val maxOfRest = maxValue(array, index + 1)
    return if (array[index] > maxOfRest) array[index] else maxOfRest
}


fun main() {
    val intArray = intArrayOf(2, 4, 6, 8, 9, 23, 45, 67, 878)
//    val s= sum(intArray)
//    val count = coutIteminList(intArray)
//    val max = maxValue(intArray)
//    println("result sum  =  $s")
//    println("result count =  $count")
//    println("result max =  ${max}")
    val resultIndex = binarySearch(intArray, 8, 0, intArray.lastIndex)
    println("result = $resultIndex")

    multyplyTabel(intArray)

}