@file:Suppress("UNUSED_PARAMETER")

package lesson3.task1

import kotlin.math.sqrt
import kotlin.math.abs
import lesson1.task1.sqr
import kotlin.math.PI
import kotlin.math.pow


/**
 * Пример
 *
 * Вычисление факториала
 */
fun factorial(n: Int): Double {
    var result = 1.0
    for (i in 1..n) {
        result *= i // Please do not fix in master
    }
    return result
}

/**
 * Пример
 *
 * Проверка числа на простоту -- результат true, если число простое
 */
fun isPrime(n: Int): Boolean {
    if (n < 2) return false
    if (n == 2) return true
    if (n % 2 == 0) return false
    for (m in 3..sqrt(n.toDouble()).toInt() step 2) {
        if (n % m == 0) return false
    }
    return true
}

/**
 * Пример
 *
 * Проверка числа на совершенность -- результат true, если число совершенное
 */
fun isPerfect(n: Int): Boolean {
    var sum = 1
    for (m in 2..n / 2) {
        if (n % m > 0) continue
        sum += m
        if (sum > n) break
    }
    return sum == n
}

/**
 * Пример
 *
 * Найти число вхождений цифры m в число n
 */
fun digitCountInNumber(n: Int, m: Int): Int =
    when {
        n == m -> 1
        n < 10 -> 0
        else -> digitCountInNumber(n / 10, m) + digitCountInNumber(n % 10, m)
    }

/**
 * Простая
 *
 * Найти количество цифр в заданном числе n.
 * Например, число 1 содержит 1 цифру, 456 -- 3 цифры, 65536 -- 5 цифр.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun digitNumber(n: Int): Int {
    var count = 0
    var balance = abs(n)
    do {
        count++
        balance /= 10
    } while (balance > 0)
    return count
}

/**
 * Простая
 *
 * Найти число Фибоначчи из ряда 1, 1, 2, 3, 5, 8, 13, 21, ... с номером n.
 * Ряд Фибоначчи определён следующим образом: fib(1) = 1, fib(2) = 1, fib(n+2) = fib(n) + fib(n+1)
 */
fun fib(n: Int): Int {
    var number = 1
    var lastNumber = 1
    for (i in 3..n) {
        number += lastNumber
        lastNumber = number - lastNumber
    }
    return number
}

/**
 * Простая
 *
 * Для заданных чисел m и n найти наименьшее общее кратное, то есть,
 * минимальное число k, которое делится и на m и на n без остатка
 */
fun nod(m: Int, n: Int): Int {
    var mChanged = m
    var nChanged = n
    while (mChanged != 0 && nChanged != 0) {
        if (mChanged > nChanged)
            mChanged %= nChanged
        else
            nChanged %= mChanged
    }
    return (mChanged + nChanged)
}

fun lcm(m: Int, n: Int): Int = m * n / nod(m, n)

/**
 * Простая
 *
 * Для заданного числа n > 1 найти минимальный делитель, превышающий 1
 */
fun minDivisor(n: Int): Int {
    var minDivider = 0
    if (isPrime(n)) return n
    for (i in 2..sqrt(n.toDouble()).toInt()) {
        minDivider = i
        if (n % minDivider == 0) break
    }
    return minDivider
}

/**
 * Простая
 *
 * Для заданного числа n > 1 найти максимальный делитель, меньший n
 */
fun maxDivisor(n: Int): Int = when {
    (isPrime(n)) -> 1
    else -> (n / minDivisor(n))
}

/**
 * Простая
 *
 * Определить, являются ли два заданных числа m и n взаимно простыми.
 * Взаимно простые числа не имеют общих делителей, кроме 1.
 * Например, 25 и 49 взаимно простые, а 6 и 8 -- нет.
 */
fun isCoPrime(m: Int, n: Int): Boolean = nod(m, n) == 1

/**
 * Простая
 *
 * Для заданных чисел m и n, m <= n, определить, имеется ли хотя бы один точный квадрат между m и n,
 * то есть, существует ли такое целое k, что m <= k*k <= n.
 * Например, для интервала 21..28 21 <= 5*5 <= 28, а для интервала 51..61 квадрата не существует.
 */
fun squareBetweenExists(m: Int, n: Int): Boolean {
    var k = 0
    for (i in sqrt(m.toDouble()).toInt()..sqrt(n.toDouble()).toInt()) {
        if (sqr(i) in m..n) {
            k++
            break
        }
    }
    return (k != 0)
}


/**
 * Средняя
 *
 * Гипотеза Коллатца. Рекуррентная последовательность чисел задана следующим образом:
 *
 *   ЕСЛИ (X четное)
 *     Xслед = X /2
 *   ИНАЧЕ
 *     Xслед = 3 * X + 1
 *
 * например
 *   15 46 23 70 35 106 53 160 80 40 20 10 5 16 8 4 2 1 4 2 1 4 2 1 ...
 * Данная последовательность рано или поздно встречает X == 1.
 * Написать функцию, которая находит, сколько шагов требуется для
 * этого для какого-либо начального X > 0.
 */
fun collatzSteps(x: Int): Int {
    var count = 0
    var xChanged = x
    while (xChanged != 1) {
        count++
        if (xChanged % 2 == 0) xChanged /= 2
        else xChanged = 3 * xChanged + 1
    }
    return count
}

/**
 * Средняя
 *
 * Для заданного x рассчитать с заданной точностью eps
 * sin(x) = x - x^3 / 3! + x^5 / 5! - x^7 / 7! + ...
 * Нужную точность считать достигнутой, если очередной член ряда меньше eps по модулю.
 * Подумайте, как добиться более быстрой сходимости ряда при больших значениях x.
 * Использовать kotlin.math.sin и другие стандартные реализации функции синуса в этой задаче запрещается.
 */
fun sin(x: Double, eps: Double): Double {
    val xChange = x % (2 * PI)
    var k = 1
    var member = (-1.0).pow(k) * xChange.pow(2 * k + 1) / factorial(2 * k + 1)
    var valueSin = xChange + member
    while (abs(member) >= eps) {
        k++
        member = (-1.0).pow(k) * xChange.pow(2 * k + 1) / factorial(2 * k + 1)
        valueSin += member
    }
    return valueSin
}

/**
 * Средняя
 *
 * Для заданного x рассчитать с заданной точностью eps
 * cos(x) = 1 - x^2 / 2! + x^4 / 4! - x^6 / 6! + ...
 * Нужную точность считать достигнутой, если очередной член ряда меньше eps по модулю
 * Подумайте, как добиться более быстрой сходимости ряда при больших значениях x.
 * Использовать kotlin.math.cos и другие стандартные реализации функции косинуса в этой задаче запрещается.
 */
fun cos(x: Double, eps: Double): Double {
    val xChanged = x % (2 * PI)
    var k = 1
    var member = (-1.0).pow(k) * xChanged.pow(2 * k) / factorial(2 * k)
    var valueCos = 1 + member
    while (abs(member) > eps) {
        k++
        member = (-1.0).pow(k) * xChanged.pow(2 * k) / factorial(2 * k)
        valueCos += member
    }
    return valueCos
}

/**
 * Средняя
 *
 * Поменять порядок цифр заданного числа n на обратный: 13478 -> 87431.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun revert(n: Int): Int {
    var nOld = n
    var nNew = 0
    for (i in 1..digitNumber(n)) {
        nNew = nNew * 10 + nOld % 10
        nOld /= 10
    }
    return nNew
}

/**
 * Средняя
 *
 * Проверить, является ли заданное число n палиндромом:
 * первая цифра равна последней, вторая -- предпоследней и так далее.
 * 15751 -- палиндром, 3653 -- нет.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun isPalindrome(n: Int): Boolean = n == revert(n)

/**
 * Средняя
 *
 * Для заданного числа n определить, содержит ли оно различающиеся цифры.
 * Например, 54 и 323 состоят из разных цифр, а 111 и 0 из одинаковых.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun hasDifferentDigits(n: Int): Boolean {
    var numberPast = n % 10
    var numberNow = (n % 100) / 10
    var nChanged = n / 100
    if (digitNumber(n) == 1) return false
    if (numberPast != numberNow) return true
    while (nChanged != 0) {
        numberPast = numberNow
        numberNow = nChanged % 10
        nChanged /= 10
        if (numberNow != numberPast) break
    }
    return (nChanged != 0)
}

/**
 * Сложная
 *
 * Найти n-ю цифру последовательности из квадратов целых чисел:
 * 149162536496481100121144...
 * Например, 2-я цифра равна 4, 7-я 5, 12-я 6.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun squareSequenceDigit(n: Int): Int {
    var nChanged = n
    var i = 1
    var digit = digitNumber(sqr(i))
    while (nChanged - digit > 0) {
        nChanged -= digit
        i++
        digit = digitNumber(sqr(i))
    }
    digit -= nChanged
    return (sqr(i) / 10.0.pow(digit).toInt()) % 10
}

/**
 * Сложная
 *
 * Найти n-ю цифру последовательности из чисел Фибоначчи (см. функцию fib выше):
 * 1123581321345589144...
 * Например, 2-я цифра равна 1, 9-я 2, 14-я 5.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun fibSequenceDigit(n: Int): Int {
    var nChanged = n
    var i = 1
    var digit = digitNumber(fib(i))
    while (nChanged - digit > 0) {
        nChanged -= digit
        i++
        digit = digitNumber(fib(i))
    }
    digit -= nChanged
    return (fib(i) / 10.0.pow(digit).toInt()) % 10
}