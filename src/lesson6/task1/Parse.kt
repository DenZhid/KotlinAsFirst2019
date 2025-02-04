@file:Suppress("UNUSED_PARAMETER", "ConvertCallChainIntoSequence")

package lesson6.task1

import lesson2.task2.daysInMonth
import java.lang.IllegalArgumentException
//import java.lang.IllegalStateException
import java.lang.NumberFormatException
import java.lang.IndexOutOfBoundsException

/**
 * Пример
 *
 * Время представлено строкой вида "11:34:45", содержащей часы, минуты и секунды, разделённые двоеточием.
 * Разобрать эту строку и рассчитать количество секунд, прошедшее с начала дня.
 */
fun timeStrToSeconds(str: String): Int {
    val parts = str.split(":")
    var result = 0
    for (part in parts) {
        val number = part.toInt()
        result = result * 60 + number
    }
    return result
}

/**
 * Пример
 *
 * Дано число n от 0 до 99.
 * Вернуть его же в виде двухсимвольной строки, от "00" до "99"
 */
fun twoDigitStr(n: Int) = if (n in 0..9) "0$n" else "$n"

/**
 * Пример
 *
 * Дано seconds -- время в секундах, прошедшее с начала дня.
 * Вернуть текущее время в виде строки в формате "ЧЧ:ММ:СС".
 */
fun timeSecondsToStr(seconds: Int): String {
    val hour = seconds / 3600
    val minute = (seconds % 3600) / 60
    val second = seconds % 60
    return String.format("%02d:%02d:%02d", hour, minute, second)
}

/**
 * Пример: консольный ввод
 */
fun main() {
    println("Введите время в формате ЧЧ:ММ:СС")
    val line = readLine()
    if (line != null) {
        val seconds = timeStrToSeconds(line)
        if (seconds == -1) {
            println("Введённая строка $line не соответствует формату ЧЧ:ММ:СС")
        } else {
            println("Прошло секунд с начала суток: $seconds")
        }
    } else {
        println("Достигнут <конец файла> в процессе чтения строки. Программа прервана")
    }
}


/**
 * Средняя
 *
 * Дата представлена строкой вида "15 июля 2016".
 * Перевести её в цифровой формат "15.07.2016".
 * День и месяц всегда представлять двумя цифрами, например: 03.04.2011.
 * При неверном формате входной строки вернуть пустую строку.
 *
 * Обратите внимание: некорректная с точки зрения календаря дата (например, 30.02.2009) считается неверными
 * входными данными.
 */
fun treatmentOfMonth(month: String): Int? = when (month) {
    "января" -> 1
    "февраля" -> 2
    "марта" -> 3
    "апреля" -> 4
    "мая" -> 5
    "июня" -> 6
    "июля" -> 7
    "августа" -> 8
    "сентября" -> 9
    "октября" -> 10
    "ноября" -> 11
    "декабря" -> 12
    else -> null
}

fun dateStrToDigit(str: String): String {
    val parts = str.split(" ").toMutableList()
    val e = NumberFormatException()
    return try {
        val day = parts[0].toInt()
        val month = treatmentOfMonth(parts[1])!!.toInt()
        val year = parts[2].toInt()
        if (parts.size > 3) throw e
        if (daysInMonth(month, year) < day) throw e
        String.format("%02d.%02d.%d", day, month, year)
    } catch (e: NumberFormatException) {
        ""
    } catch (e: IndexOutOfBoundsException) {
        ""
    } catch (e: KotlinNullPointerException) {
        ""
    }
}

/**
 * Средняя
 *
 * Дата представлена строкой вида "15.07.2016".
 * Перевести её в строковый формат вида "15 июля 2016".
 * При неверном формате входной строки вернуть пустую строку
 *
 * Обратите внимание: некорректная с точки зрения календаря дата (например, 30 февраля 2009) считается неверными
 * входными данными.
 */
fun backTreatmentOfMonth(month: String): String = when (month) {
    "01" -> "января"
    "02" -> "февраля"
    "03" -> "марта"
    "04" -> "апреля"
    "05" -> "мая"
    "06" -> "июня"
    "07" -> "июля"
    "08" -> "августа"
    "09" -> "сентября"
    "10" -> "октября"
    "11" -> "ноября"
    "12" -> "декабря"
    else -> ""
}

fun dateDigitToStr(digital: String): String = when {
    Regex("""^\d\d\.\d\d\.\d*$""").containsMatchIn(digital) -> {
        val parts = digital.split(".")
        val day = parts[0].toInt()
        val month = backTreatmentOfMonth(parts[1])
        val year = parts[2].toInt()
        if (daysInMonth(parts[1].toInt(), year) < day) ""
        else {
            if (month == "") ""
            else String.format("%s %s %s", day, month, year)

        }
    }
    else -> ""
}

/**
 * Средняя
 *
 * Номер телефона задан строкой вида "+7 (921) 123-45-67".
 * Префикс (+7) может отсутствовать, код города (в скобках) также может отсутствовать.
 * Может присутствовать неограниченное количество пробелов и чёрточек,
 * например, номер 12 --  34- 5 -- 67 -89 тоже следует считать легальным.
 * Перевести номер в формат без скобок, пробелов и чёрточек (но с +), например,
 * "+79211234567" или "123456789" для приведённых примеров.
 * Все символы в номере, кроме цифр, пробелов и +-(), считать недопустимыми.
 * При неверном формате вернуть пустую строку.
 *
 * PS: Дополнительные примеры работы функции можно посмотреть в соответствующих тестах.
 */
fun flattenPhoneNumber(phone: String): String {
    val matchResult =
        Regex("""^\+\d[\d -]*\(\d[\d -]*\)[\d -]*\d$|^[\d -]*\d$|^\+\d[\d -]*\d$|^[\d -]*\d\(\d[\d -]*\)[\d -]*\d$""")
    if (matchResult.find(phone) == null) return ""
    val parts = phone.split("")
    return parts.filter { it != "-" && it != "(" && it != ")" && it != " " }.joinToString(separator = "")
}

/**
 * Средняя
 *
 * Результаты спортсмена на соревнованиях в прыжках в длину представлены строкой вида
 * "706 - % 717 % 703".
 * В строке могут присутствовать числа, черточки - и знаки процента %, разделённые пробелами;
 * число соответствует удачному прыжку, - пропущенной попытке, % заступу.
 * Прочитать строку и вернуть максимальное присутствующее в ней число (717 в примере).
 * При нарушении формата входной строки или при отсутствии в ней чисел, вернуть -1.
 */
fun bestLongJump(jumps: String): Int = when {
    !jumps.matches(Regex("""[\d %-]+\d[\d %-]+""")) -> -1
    else -> jumps.split(" ").filter { it != "-" && it != "%" }.map { it.toInt() }.max() ?: -1
}

/**
 * Сложная
 *
 * Результаты спортсмена на соревнованиях в прыжках в высоту представлены строкой вида
 * "220 + 224 %+ 228 %- 230 + 232 %%- 234 %".
 * Здесь + соответствует удачной попытке, % неудачной, - пропущенной.
 * Высота и соответствующие ей попытки разделяются пробелом.
 * Прочитать строку и вернуть максимальную взятую высоту (230 в примере).
 * При нарушении формата входной строки, а также в случае отсутствия удачных попыток,
 * вернуть -1.
 */
fun bestHighJump(jumps: String): Int {
    if (!Regex("""^(\d* [+\-%]*)+$""").matches(jumps)) return -1
    val someList = Regex("""(\d* [+\-%]*)""").findAll(jumps)
    val someList2 = mutableListOf<String>()
    for (element in someList)
        if (element.value.contains(Regex("""\+"""))) someList2.add(element.value)
    return if (someList2.isNotEmpty()) {
        var max = 0
        for (i in someList2.indices) {
            val number = (someList2[i].split(" "))[0].toInt()
            if (number > max) max = number
        }
        max
    } else -1
}

/**
 * Сложная
 *
 * В строке представлено выражение вида "2 + 31 - 40 + 13",
 * использующее целые положительные числа, плюсы и минусы, разделённые пробелами.
 * Наличие двух знаков подряд "13 + + 10" или двух чисел подряд "1 2" не допускается.
 * Вернуть значение выражения (6 для примера).
 * Про нарушении формата входной строки бросить исключение IllegalArgumentException
 */
fun plusMinus(expression: String): Int = when {
    !expression.matches(Regex("""\d+( [+-] \d+)*""")) -> throw IllegalArgumentException()
    else -> {
        val parts = expression.split(" ")
        var res = parts[0].toInt()
        for (i in 1..parts.size - 2) {
            when (parts[i]) {
                "+" -> res += parts[i + 1].toInt()
                "-" -> res -= parts[i + 1].toInt()
            }
        }
        res
    }
}

/**
 * Сложная
 *
 * Строка состоит из набора слов, отделённых друг от друга одним пробелом.
 * Определить, имеются ли в строке повторяющиеся слова, идущие друг за другом.
 * Слова, отличающиеся только регистром, считать совпадающими.
 * Вернуть индекс начала первого повторяющегося слова, или -1, если повторов нет.
 * Пример: "Он пошёл в в школу" => результат 9 (индекс первого 'в')
 */
fun firstDuplicateIndex(str: String): Int {
    val parts = str.toLowerCase().split(" ")
    var res = -1
    var currentIndex = 0
    for (i in 0 until parts.size - 1) {
        if (parts[i] == parts[i + 1]) {
            res = currentIndex
            break
        } else currentIndex += parts[i].length + 1
    }
    return res
}

/**
 * Сложная
 *
 * Строка содержит названия товаров и цены на них в формате вида
 * "Хлеб 39.9; Молоко 62; Курица 184.0; Конфеты 89.9".
 * То есть, название товара отделено от цены пробелом,
 * а цена отделена от названия следующего товара точкой с запятой и пробелом.
 * Вернуть название самого дорогого товара в списке (в примере это Курица),
 * или пустую строку при нарушении формата строки.
 * Все цены должны быть больше либо равны нуля.
 */
fun mostExpensive(description: String): String {
    TODO()/*val parts = description.split("; ")
    val mapOfProduct = mutableMapOf<String, Double>()
    try {
        for (elements in parts) {
            val forMap = elements.split(" ")
            if (forMap[1].toDouble() < 0.0) return ""
            mapOfProduct[forMap[0]] = forMap[1].toDouble()
        }
    } catch (e: NumberFormatException) {
        return ""
    } catch (e: IndexOutOfBoundsException) {
        return ""
    }
    var maxPrice = 0.0
    var nameOfMaxPricedProduct = ""
    for ((name, price) in mapOfProduct) if (price > maxPrice) {
        maxPrice = price
        nameOfMaxPricedProduct = name
    }
    return nameOfMaxPricedProduct*/
}

/**
 * Сложная
 *
 * Перевести число roman, заданное в римской системе счисления,
 * в десятичную систему и вернуть как результат.
 * Римские цифры: 1 = I, 4 = IV, 5 = V, 9 = IX, 10 = X, 40 = XL, 50 = L,
 * 90 = XC, 100 = C, 400 = CD, 500 = D, 900 = CM, 1000 = M.
 * Например: XXIII = 23, XLIV = 44, C = 100
 *
 * Вернуть -1, если roman не является корректным римским числом
 */
/*
forThousands^M*$
forHundreads^CM$|^CD$|^DC*$|^C*$
forDozens^XC$|^XL$|^LX*$|^X*$
forUnits^IX$|^IV$|^VI*$|^I*$
 */
fun fromRoman(roman: String): Int = TODO()

/**
 * Очень сложная
 *
 * Имеется специальное устройство, представляющее собой
 * конвейер из cells ячеек (нумеруются от 0 до cells - 1 слева направо) и датчик, двигающийся над этим конвейером.
 * Строка commands содержит последовательность команд, выполняемых данным устройством, например +>+>+>+>+
 * Каждая команда кодируется одним специальным символом:
 *	> - сдвиг датчика вправо на 1 ячейку;
 *  < - сдвиг датчика влево на 1 ячейку;
 *	+ - увеличение значения в ячейке под датчиком на 1 ед.;
 *	- - уменьшение значения в ячейке под датчиком на 1 ед.;
 *	[ - если значение под датчиком равно 0, в качестве следующей команды следует воспринимать
 *  	не следующую по порядку, а идущую за соответствующей следующей командой ']' (с учётом вложенности);
 *	] - если значение под датчиком не равно 0, в качестве следующей команды следует воспринимать
 *  	не следующую по порядку, а идущую за соответствующей предыдущей командой '[' (с учётом вложенности);
 *      (комбинация [] имитирует цикл)
 *  пробел - пустая команда
 *
 * Изначально все ячейки заполнены значением 0 и датчик стоит на ячейке с номером N/2 (округлять вниз)
 *
 * После выполнения limit команд или всех команд из commands следует прекратить выполнение последовательности команд.
 * Учитываются все команды, в том числе несостоявшиеся переходы ("[" при значении под датчиком не равном 0 и "]" при
 * значении под датчиком равном 0) и пробелы.
 *
 * Вернуть список размера cells, содержащий элементы ячеек устройства после завершения выполнения последовательности.
 * Например, для 10 ячеек и командной строки +>+>+>+>+ результат должен быть 0,0,0,0,0,1,1,1,1,1
 *
 * Все прочие символы следует считать ошибочными и формировать исключение IllegalArgumentException.
 * То же исключение формируется, если у символов [ ] не оказывается пары.
 * Выход за границу конвейера также следует считать ошибкой и формировать исключение IllegalStateException.
 * Считать, что ошибочные символы и непарные скобки являются более приоритетной ошибкой чем выход за границу ленты,
 * то есть если в программе присутствует некорректный символ или непарная скобка, то должно быть выброшено
 * IllegalArgumentException.
 * IllegalArgumentException должен бросаться даже если ошибочная команда не была достигнута в ходе выполнения.
 *
 */
/*fun cycle(arrayOfCells: Array<Int>, commands: String, i: Int, j: Int, command: Char): Pair<Int, Array<Int>> =
    when (command) {
        '[' -> {
            var mutableI = i
            if (arrayOfCells[mutableI] == 0) {
                var k = 0
                var mutableJ = j
                while (k >= 0) {
                    mutableJ++
                    when (commands[mutableJ]) {
                        '[' -> k++
                        ']' -> k--
                    }
                }
                when (commands[mutableJ]) {
                    '>' -> mutableI++
                    '<' -> mutableI--
                    '+' -> arrayOfCells[i]++
                    '-' -> arrayOfCells[i]--
                    '[' -> cycle(arrayOfCells, commands, mutableI, j, commands[mutableJ])
                    ']' -> cycle(arrayOfCells, commands, mutableI, j, commands[mutableJ])
                }
            }
            Pair(mutableI, arrayOfCells)
        }
        else -> {
            var mutableI = i
            if (arrayOfCells[mutableI] != 0) {
                var k = 0
                var mutableJ = j
                while (k >= 0) {
                    mutableJ--
                    when (commands[mutableJ]) {
                        '[' -> k--
                        ']' -> k++
                    }
                }
                mutableJ += 2
                when (commands[mutableJ]) {
                    '>' -> mutableI++
                    '<' -> mutableI--
                    '+' -> arrayOfCells[i]++
                    '-' -> arrayOfCells[i]--
                    '[' -> cycle(arrayOfCells, commands, mutableI, j, commands[mutableJ])
                    ']' -> cycle(arrayOfCells, commands, mutableI, j, commands[mutableJ])
                }
            }
            Pair(mutableI, arrayOfCells)
        }
    }*/

fun computeDeviceCells(cells: Int, commands: String, limit: Int): List<Int> {
    TODO()
    /*if (Regex("""^[+\-<> ]*(\[[+\-<> \[\]]*\])*$""").matches(commands)) {
        var arrayOfCells = Array(cells) { 0 }
        var i = cells / 2
        var mutableLimit = limit
        for (j in commands.indices) {
            when (commands[j]) {
                '>' -> i++
                '<' -> i--
                '+' -> arrayOfCells[i]++
                '-' -> arrayOfCells[i]--
                '[' -> {
                    val resOfCycle = cycle(arrayOfCells, commands, i, j, commands[j])
                    i = resOfCycle.first
                    arrayOfCells = resOfCycle.second
                }
                ']' -> {
                    val resOfCycle = cycle(arrayOfCells, commands, i, j, commands[j])
                    i = resOfCycle.first
                    arrayOfCells = resOfCycle.second
                }
            }
            if (i > commands.length) throw IllegalStateException()
            mutableLimit--
            if (mutableLimit == 0) break
        }
        return arrayOfCells.toList()
    } else throw IllegalArgumentException()*/
}

/** Тест */
fun resultsOfMatch(text: String): Map<String, Int> {
    val res = mutableMapOf<String, Int>()
    val parts1 = text.split("; ")
    try {
        for (elements in parts1) {
            val count = elements.split(" ")[1].split(":")
            val players = elements.split(" ")[0].split("-")
            when {
                count[0].toInt() > count[1].toInt() -> {
                    res[players[0]] = res.getOrDefault(players[0], 0) + 3
                    res[players[1]] = res.getOrDefault(players[1], 0) + 0
                }
                count[1].toInt() > count[0].toInt() -> {
                    res[players[1]] = res.getOrDefault(players[1], 0) + 3
                    res[players[0]] = res.getOrDefault(players[0], 0) + 0
                }
                else -> {
                    res[players[0]] = res.getOrDefault(players[0], 0) + 1
                    res[players[1]] = res.getOrDefault(players[1], 0) + 1
                }
            }
        }
    } catch (e: NumberFormatException) {
        throw IllegalArgumentException()
    } catch (e: IndexOutOfBoundsException) {
        throw IllegalArgumentException()
    }
    return res
}
