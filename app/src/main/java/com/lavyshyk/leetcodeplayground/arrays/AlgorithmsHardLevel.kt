package com.lavyshyk.leetcodeplayground.arrays

class AlgorithmsHardLevel {


    fun longestSubstringWithoutRepeatingChar(s: String): String {
        var left = 0
        var right = 0
        var maxLen = 0
        var start = 0
        val seen = mutableSetOf<Char>()

        while (right < s.length) {
            val current = s[right]
            if (!seen.contains(current)) {
                seen.add(current)
                right++
                if (right - left > maxLen) {
                    maxLen = right - left
                    start = left
                }
            } else {
                seen.remove(s[left])
                left++
            }
        }

        return s.substring(start, start + maxLen)
    }
}