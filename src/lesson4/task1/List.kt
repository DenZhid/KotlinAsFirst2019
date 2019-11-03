@file:Suppress("UNUSED_PARAMETER", "ConvertCallChainIntoSequence")

package lesson4.task1

import lesson1.task1.discriminant
import kotlin.math.sqrt
import lesson1.task1.sqr
import lesson3.task1.digitNumber
import lesson3.task1.isPrime
import kotlin.math.pow

/**
 * Пример
 *
 * Найти все корни уравнения x^2 = y
 */
fun sqRoots(y: Double) =
    when {
        y < 0 -> listOf()
        y == 0.0 -> listOf(0.0)
        else -> {
            val root = sqrt(y)
            // Результат!
            listOf(-root, root)
        }
    }

/**
 * Пример
 *
 * Найти все корни биквадратного уравнения ax^4 + bx^2 + c = 0.
 * Вернуть список корней (пустой, если корней нет)
 */
fun biRoots(a: Double, b: Double, c: Double): List<Double> {
    if (a == 0.0) {
        return if (b == 0.0) listOf()
        else sqRoots(-c / b)
    }
    val d = discriminant(a, b, c)
    if (d < 0.0) return listOf()
    if (d == 0.0) return sqRoots(-b / (2 * a))
    val y1 = (-b + sqrt(d)) / (2 * a)
    val y2 = (-b - sqrt(d)) / (2 * a)
    return sqRoots(y1) + sqRoots(y2)
}

/**
 * Пример
 *
 * Выделить в список отрицательные элементы из заданного списка
 */
fun negativeList(list: List<Int>): List<Int> {
    val result = mutableListOf<Int>()
    for (element in list) {
        if (element < 0) {
            result.add(element)
        }
    }
    return result
}

/**
 * Пример
 *
 * Изменить знак для всех положительных элементов списка
 */
fun invertPositives(list: MutableList<Int>) {
    for (i in 0 until list.size) {
        val element = list[i]
        if (element > 0) {
            list[i] = -element
        }
    }
}

/**
 * Пример
 *
 * Из имеющегося списка целых чисел, сформировать список их квадратов
 */
fun squares(list: List<Int>) = list.map { it * it }

/**
 * Пример
 *
 * Из имеющихся целых чисел, заданного через vararg-параметр, сформировать массив их квадратов
 */
fun squares(vararg array: Int) = squares(array.toList()).toTypedArray()

/**
 * Пример
 *
 * По заданной строке str определить, является ли она палиндромом.
 * В палиндроме первый символ должен быть равен последнему, второй предпоследнему и т.д.
 * Одни и те же буквы в разном регистре следует считать равными с точки зрения данной задачи.
 * Пробелы не следует принимать во внимание при сравнении символов, например, строка
 * "А роза упала на лапу Азора" является палиндромом.
 */
fun isPalindrome(str: String): Boolean {
    val lowerCase = str.toLowerCase().filter { it != ' ' }
    for (i in 0..lowerCase.length / 2) {
        if (lowerCase[i] != lowerCase[lowerCase.length - i - 1]) return false
    }
    return true
}

/**
 * Пример
 *
 * По имеющемуся списку целых чисел, например [3, 6, 5, 4, 9], построить строку с примером их суммирования:
 * 3 + 6 + 5 + 4 + 9 = 27 в данном случае.
 */
fun buildSumExample(list: List<Int>) = list.joinToString(separator = " + ", postfix = " = ${list.sum()}")

/**
 * Простая
 *
 * Найти модуль заданного вектора, представленного в виде списка v,
 * по формуле abs = sqrt(a1^2 + a2^2 + ... + aN^2).
 * Модуль пустого вектора считать равным 0.0.
 */
fun abs(v: List<Double>): Double {
    var result = 0.0
    for (element in v) {
        result += sqr(element)
    }
    return sqrt(result)
}

/**
 * Простая
 *
 * Рассчитать среднее арифметическое элементов списка list. Вернуть 0.0, если список пуст
 */
fun mean(list: List<Double>): Double = when {
    list.isEmpty() -> 0.0
    else -> list.sum() / list.size
}

/**
 * Средняя
 *
 * Центрировать заданный список list, уменьшив каждый элемент на среднее арифметическое всех элементов.
 * Если список пуст, не делать ничего. Вернуть изменённый список.
 *
 * Обратите внимание, что данная функция должна изменять содержание списка list, а не его копии.
 */
fun center(list: MutableList<Double>): MutableList<Double> {
    val mean = mean(list)
    for (i in 0 until list.size) {
        list[i] -= mean
    }
    return list
}

/**
 * Средняя
 *
 * Найти скалярное произведение двух векторов равной размерности,
 * представленные в виде списков a и b. Скалярное произведение считать по формуле:
 * C = a1b1 + a2b2 + ... + aNbN. Произведение пустых векторов считать равным 0.
 */
fun times(a: List<Int>, b: List<Int>): Int = when {
    a.isEmpty() || b.isEmpty() -> 0
    else -> {
        var c = 0
        for (i in a.indices) {
            c += a[i] * b[i]
        }
        c
    }
}

/**
 * Средняя
 *
 * Рассчитать значение многочлена при заданном x:
 * p(x) = p0 + p1*x + p2*x^2 + p3*x^3 + ... + pN*x^N.
 * Коэффициенты многочлена заданы списком p: (p0, p1, p2, p3, ..., pN).
 * Значение пустого многочлена равно 0 при любом x.
 */
fun polynom(p: List<Int>, x: Int): Int {
    var pValue = 0
    val doubleX = x.toDouble()
    for (i in p.indices) {
        pValue += p[i] * (doubleX.pow(i)).toInt()
    }
    return pValue
}


/**
 * Средняя
 *
 * В заданном списке list каждый элемент, кроме первого, заменить
 * суммой данного элемента и всех предыдущих.
 * Например: 1, 2, 3, 4 -> 1, 3, 6, 10.
 * Пустой список не следует изменять. Вернуть изменённый список.
 *
 * Обратите внимание, что данная функция должна изменять содержание списка list, а не его копии.
 */
fun accumulate(list: MutableList<Int>): MutableList<Int> {
    for (i in 1 until list.size) {
        list[i] += list[i - 1]
    }
    return list
}

/**
 * Средняя
 *
 * Разложить заданное натуральное число n > 1 на простые множители.
 * Результат разложения вернуть в виде списка множителей, например 75 -> (3, 5, 5).
 * Множители в списке должны располагаться по возрастанию.
 */
fun factorize(n: Int): List<Int> {
    val result = mutableListOf<Int>()
    var nChanged = n
    var i = 2
    while (nChanged != 1) {
        when {
            isPrime(nChanged) -> {
                result.add(nChanged)
                nChanged /= nChanged
            }
            else -> {
                if (nChanged % i == 0) {
                    result.add(i)
                    nChanged /= i
                } else i++
            }
        }
    }
    return result.sorted()
}

/**
 * Сложная
 *
 * Разложить заданное натуральное число n > 1 на простые множители.
 * Результат разложения вернуть в виде строки, например 75 -> 3*5*5
 * Множители в результирующей строке должны располагаться по возрастанию.
 */
fun factorizeToString(n: Int): String = factorize(n).joinToString(separator = "*")

/**
 * Средняя
 *
 * Перевести заданное целое число n >= 0 в систему счисления с основанием base > 1.
 * Результат перевода вернуть в виде списка цифр в base-ичной системе от старшей к младшей,
 * например: n = 100, base = 4 -> (1, 2, 1, 0) или n = 250, base = 14 -> (1, 3, 12)
 */
fun convert(n: Int, base: Int): List<Int> {
    val result = mutableListOf<Int>()
    var nChanged = n
    var balance: Int
    do {
        balance = nChanged % base
        result.add(0, balance)
        nChanged /= base
    } while (nChanged != 0)
    return result
}

/**
 * Сложная
 *
 * Перевести заданное целое число n >= 0 в систему счисления с основанием 1 < base < 37.
 * Результат перевода вернуть в виде строки, цифры более 9 представлять латинскими
 * строчными буквами: 10 -> a, 11 -> b, 12 -> c и так далее.
 * Например: n = 100, base = 4 -> 1210, n = 250, base = 14 -> 13c
 *
 * Использовать функции стандартной библиотеки, напрямую и полностью решающие данную задачу
 * (например, n.toString(base) и подобные), запрещается.
 */
fun convertToString(n: Int, base: Int): String =
    convert(n, base).map {
        if (it <= 9) (it + 48).toChar() //сместить
        else (it + 87).toChar()
    }.joinToString(separator = "")


/**
 * Средняя
 *
 * Перевести число, представленное списком цифр digits от старшей к младшей,
 * из системы счисления с основанием base в десятичную.
 * Например: digits = (1, 3, 12), base = 14 -> 250
 */
fun decimal(digits: List<Int>, base: Int): Int {
    val size = digits.size
    var result = 0
    for (i in digits.indices) {
        result += digits[i] * (base.toDouble().pow(size - (i + 1))).toInt()
    }
    return result
}

/**
 * Сложная
 *
 * Перевести число, представленное цифровой строкой str,
 * из системы счисления с основанием base в десятичную.
 * Цифры более 9 представляются латинскими строчными буквами:
 * 10 -> a, 11 -> b, 12 -> c и так далее.
 * Например: str = "13c", base = 14 -> 250
 *
 * Использовать функции стандартной библиотеки, напрямую и полностью решающие данную задачу
 * (например, str.toInt(base)), запрещается.
 */
fun decimalFromString(str: String, base: Int): Int {
    val list = mutableListOf<Int>()
    for (i in str.indices) {
        val strInt = str[i].toInt()
        if (strInt <= 57) list.add(strInt - 48)
        else list.add(strInt - 87)
    }
    return decimal(list, base)
}

/**
 * Сложная
 *
 * Перевести натуральное число n > 0 в римскую систему.
 * Римские цифры: 1 = I, 4 = IV, 5 = V, 9 = IX, 10 = X, 40 = XL, 50 = L,
 * 90 = XC, 100 = C, 400 = CD, 500 = D, 900 = CM, 1000 = M.
 * Например: 23 = XXIII, 44 = XLIV, 100 = C
 */
fun forThousandsRoman(element: Int): String {
    var result = ""
    for (i in 1..element) {
        result += "M"
    }
    return result
}

fun forHundredsRoman(element: Int): String =
    when (element) {
        0 -> ""
        in 1..3 -> {
            var result = ""
            for (i in 1..element) result += "C"
            result
        }
        4 -> "CD"
        in 5..8 -> {
            var result = "D"
            for (i in 1..element - 5) result += "C"
            result
        }
        else -> "CM"
    }


fun forDozensRoman(element: Int): String =
    when (element) {
        0 -> ""
        in 1..3 -> {
            var result = ""
            for (i in 1..element) result += "X"
            result
        }
        4 -> "XL"
        in 5..8 -> {
            var result = "L"
            for (i in 1..element - 5) result += "X"
            result
        }
        else -> "XC"
    }

fun forUnitsRoman(element: Int): String =
    when (element) {
        0 -> ""
        in 1..3 -> {
            var result = ""
            for (i in 1..element) result += "I"
            result
        }
        4 -> "IV"
        in 5..8 -> {
            var result = "V"
            for (i in 1..element - 5) result += "I"
            result
        }
        else -> "IX"
    }

fun roman(n: Int): String {
    var nChanged = n
    val list = mutableListOf<Int>()
    for (i in 1..digitNumber(n)) {
        list.add(nChanged % 10)
        nChanged /= 10
    }
    return when (list.size) {
        4 ->
            forThousandsRoman(list[3]) + forHundredsRoman(list[2]) + forDozensRoman(list[1]) + forUnitsRoman(list[0])
        3 -> forHundredsRoman(list[2]) + forDozensRoman(list[1]) + forUnitsRoman(list[0])
        2 -> forDozensRoman(list[1]) + forUnitsRoman(list[0])
        else -> forUnitsRoman(list[0])
    }
}

/**
 * Очень сложная
 *
 * Записать заданное натуральное число 1..999999 прописью по-русски.
 * Например, 375 = "триста семьдесят пять",
 * 23964 = "двадцать три тысячи девятьсот шестьдесят четыре"
 */
/*fun forThousandsRussian(element: Int): String =
    if (element == 0) ""
    else when {
        (element % 100 in 11..19) -> forHundredsRussian(element) + forDozensRussian(element % 100) + "тысяч"
        (element % 10 == 0) || ((element % 10 in 5..9) && (element % 100 !in 15..19)) ->
            forHundredsRussian(element) + forDozensRussian(element % 100) + forUnitsRussian(element % 10) + "тысяч"
        (element % 10 == 1) -> forHundredsRussian(element) + forDozensRussian(element % 100) + "одна тысяча"
        (element % 10 == 2) -> forHundredsRussian(element) + forDozensRussian(element % 100) + "две тысячи"
        else -> forHundredsRussian(element) + forDozensRussian(element % 100) + forUnitsRussian(element % 10) + " тысячи"
    }

fun forHundredsRussian(element: Int): String =
    when (element / 100) {
        0 -> ""
        1 -> "сто"
        2 -> "двести"
        3 -> "триста"
        4 -> "честыреста"
        5 -> "пятьсот"
        6 -> "шестьсот"
        7 -> "семьсот"
        8 -> "восемьсот"
        else -> "девятьсот"
    }

fun forDozensRussian(element: Int): String =
    if (element in 10..19) when (element) {
        10 -> "десять"
        11 -> "одиннадцать"
        12 -> "двенадцать"
        13 -> "тринадцать"
        14 -> "четырнадцать"
        15 -> "пятнадцать"
        16 -> "шестнадцать"
        17 -> "семнадцать"
        18 -> "восемнадцать"
        else -> "девятнадцать"
    }
    else when (element / 10) {
        0 -> ""
        2 -> "двадцать"
        3 -> "тридцать"
        4 -> "сорок"
        5 -> "пятьдесят"
        6 -> "шестьдесят"
        7 -> "семьдесят"
        8 -> "восемьдесят"
        else -> "девяносто"
    }


fun forUnitsRussian(element: Int): String =
    when (element) {
        0 -> ""
        1 -> "один"
        2 -> "два"
        3 -> "три"
        4 -> "четыре"
        5 -> "пять"
        6 -> "шесть"
        7 -> "семь"
        8 -> "восемь"
        else -> "девять"
    }*/


fun russian(n: Int): String = TODO() /* when (digitNumber(n)) {
    1 -> forUnitsRussian(n)
    2 -> forDozensRussian(n) + " " + forUnitsRussian(n % 10)
    3 -> forHundredsRussian(n) + " " + forDozensRussian(n % 100) + " " + forUnitsRussian(n % 10)
    4 ->
}*/

