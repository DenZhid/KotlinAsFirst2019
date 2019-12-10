package lesson5.task1

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.Test

class Tests {
    @Test
    @Tag("Example")
    fun shoppingListCostTest() {
        val itemCosts = mapOf(
            "Хлеб" to 50.0,
            "Молоко" to 100.0
        )
        assertEquals(
            150.0,
            shoppingListCost(
                listOf("Хлеб", "Молоко"),
                itemCosts
            )
        )
        assertEquals(
            150.0,
            shoppingListCost(
                listOf("Хлеб", "Молоко", "Кефир"),
                itemCosts
            )
        )
        assertEquals(
            0.0,
            shoppingListCost(
                listOf("Хлеб", "Молоко", "Кефир"),
                mapOf()
            )
        )
    }

    @Test
    @Tag("Example")
    fun filterByCountryCode() {
        val phoneBook = mutableMapOf(
            "Quagmire" to "+1-800-555-0143",
            "Adam's Ribs" to "+82-000-555-2960",
            "Pharmakon Industries" to "+1-800-555-6321"
        )

        filterByCountryCode(phoneBook, "+1")
        assertEquals(2, phoneBook.size)

        filterByCountryCode(phoneBook, "+1")
        assertEquals(2, phoneBook.size)

        filterByCountryCode(phoneBook, "+999")
        assertEquals(0, phoneBook.size)
    }

    @Test
    @Tag("Example")
    fun removeFillerWords() {
        assertEquals(
            "Я люблю Котлин".split(" "),
            removeFillerWords(
                "Я как-то люблю Котлин".split(" "),
                "как-то"
            )
        )
        assertEquals(
            "Я люблю Котлин".split(" "),
            removeFillerWords(
                "Я как-то люблю таки Котлин".split(" "),
                "как-то",
                "таки"
            )
        )
        assertEquals(
            "Я люблю Котлин".split(" "),
            removeFillerWords(
                "Я люблю Котлин".split(" "),
                "как-то",
                "таки"
            )
        )
    }

    @Test
    @Tag("Example")
    fun buildWordSet() {
        assertEquals(
            buildWordSet("Я люблю Котлин".split(" ")),
            mutableSetOf("Я", "люблю", "Котлин")
        )
        assertEquals(
            buildWordSet("Я люблю люблю Котлин".split(" ")),
            mutableSetOf("Котлин", "люблю", "Я")
        )
        assertEquals(
            buildWordSet(listOf()),
            mutableSetOf<String>()
        )
    }

    @Test
    @Tag("Easy")
    fun buildGrades() {
        assertEquals(
            mapOf<Int, List<String>>(),
            buildGrades(mapOf())
        )
        assertEquals(
            mapOf(5 to listOf("Михаил", "Семён"), 3 to listOf("Марат")),
            buildGrades(mapOf("Марат" to 3, "Семён" to 5, "Михаил" to 5))
                .mapValues { (_, v) -> v.sorted() }
        )
        assertEquals(
            mapOf(3 to listOf("Марат", "Михаил", "Семён")),
            buildGrades(mapOf("Марат" to 3, "Семён" to 3, "Михаил" to 3))
                .mapValues { (_, v) -> v.sorted() }
        )
        assertEquals(
            mapOf(0 to listOf("")),
            buildGrades(mapOf("" to 0))
        )
    }

    @Test
    @Tag("Easy")
    fun containsIn() {
        assertTrue(containsIn(mapOf("a" to "z"), mapOf("a" to "z", "b" to "sweet")))
        assertFalse(containsIn(mapOf("a" to "z"), mapOf("a" to "zee", "b" to "sweet")))
        assertTrue(containsIn(mapOf(), mapOf()))
        assertTrue(
            containsIn(
                mapOf(),
                mapOf("eGlD*%&O:p]W#6e\$-][|`</uV)-E<st|J&rxB,NzwP]1,f \\\"cKbm>0jC4KT=B\\np7DrP-Z-Cl>Xp0F>;0*o\\nI6\\n3\\n6R&.8]kG*0NDk/%pY,n-^T|o|RDB+RXj|7\\tnl\\t(HLpmhdP}t{\\t.g%,w_vS(L<BWjo" to "")
            )
        )
        assertFalse(
            containsIn(
                mapOf("53Zh1!I\$`HM>nI\"&\$hNP&{3vErg/\"Y*CK294zp3LC>L;\")W\t>5)<7|Diq]J%>`ZjsA=)&)=AHR+NByx\\q}[ZywltLn_07mdNL~716eF1pVm-^PfD+ 9(pG\"2mpnIP\\8PMz@U&2}!{?[QXrbl5]\n;r^g=u(1%c$4L};#WRQ)@&UKp^CLL+1.~\$F{]C]3O^" to ""),
                mapOf("" to "")
            )
        )
        assertFalse(
            containsIn(
                mapOf("E\\\"eBLz[8TG.)z" to ""),
                mapOf("{xb0kr_5oITf>6/YqA%\\g\"3u1U^<\tNl<tD5:<OH8{Ik\\k'{UudZ\\;\"H2<;e}){)P=Q:@,q&2QF4l=$<N" to "")
            )
        )
    }

    @Test
    @Tag("Easy")
    fun subtractOf() {
        val from = mutableMapOf("a" to "z", "b" to "c")

        subtractOf(from, mapOf())
        assertEquals(from, mapOf("a" to "z", "b" to "c"))

        subtractOf(from, mapOf("b" to "z"))
        assertEquals(from, mapOf("a" to "z", "b" to "c"))

        subtractOf(from, mapOf("a" to "z"))
        assertEquals(from, mapOf("b" to "c"))
    }

    @Test
    @Tag("Easy")
    fun whoAreInBoth() {
        assertEquals(
            emptyList<String>(),
            whoAreInBoth(emptyList(), emptyList())
        )
        assertEquals(
            listOf("Marat"),
            whoAreInBoth(listOf("Marat", "Mikhail"), listOf("Marat", "Kirill"))
        )
        assertEquals(
            emptyList<String>(),
            whoAreInBoth(listOf("Marat", "Mikhail"), listOf("Sveta", "Kirill"))
        )
    }

    @Test
    @Tag("Normal")
    fun mergePhoneBooks() {
        assertEquals(
            mapOf("Emergency" to "112"),
            mergePhoneBooks(
                mapOf("Emergency" to "112"),
                mapOf("Emergency" to "112")
            )
        )
        assertEquals(
            mapOf("Emergency" to "112", "Police" to "02"),
            mergePhoneBooks(
                mapOf("Emergency" to "112"),
                mapOf("Emergency" to "112", "Police" to "02")
            )
        )
        assertEquals(
            mapOf("Emergency" to "112, 911", "Police" to "02"),
            mergePhoneBooks(
                mapOf("Emergency" to "112"),
                mapOf("Emergency" to "911", "Police" to "02")
            )
        )
        assertEquals(
            mapOf("Emergency" to "112, 911", "Fire department" to "01", "Police" to "02"),
            mergePhoneBooks(
                mapOf("Emergency" to "112", "Fire department" to "01"),
                mapOf("Emergency" to "911", "Police" to "02")
            )
        )
    }

    @Test
    @Tag("Normal")
    fun averageStockPrice() {
        assertEquals(
            mapOf<String, Double>(),
            averageStockPrice(listOf())
        )
        assertEquals(
            mapOf("MSFT" to 100.0, "NFLX" to 40.0),
            averageStockPrice(listOf("MSFT" to 100.0, "NFLX" to 40.0))
        )
        assertEquals(
            mapOf("MSFT" to 150.0, "NFLX" to 40.0),
            averageStockPrice(listOf("MSFT" to 100.0, "MSFT" to 200.0, "NFLX" to 40.0))
        )
        assertEquals(
            mapOf("MSFT" to 150.0, "NFLX" to 45.0),
            averageStockPrice(listOf("MSFT" to 100.0, "MSFT" to 200.0, "NFLX" to 40.0, "NFLX" to 50.0))
        )
    }

    @Test
    @Tag("Normal")
    fun findCheapestStuff() {
        assertNull(
            findCheapestStuff(
                mapOf("Мария" to ("печенье" to 20.0), "Орео" to ("печенье" to 100.0)),
                "торт"
            )
        )
        assertEquals(
            "Мария",
            findCheapestStuff(
                mapOf("Мария" to ("печенье" to 20.0), "Орео" to ("печенье" to 100.0)),
                "печенье"
            )
        )
        assertEquals(
            "a",
            findCheapestStuff(
                mapOf("" to ("a" to 0.0), "a" to ("" to 0.0)),
                ""
            )
        )
    }

    @Test
    @Tag("Normal")
    fun canBuildFrom() {
        assertFalse(canBuildFrom(emptyList(), "foo"))
        assertTrue(canBuildFrom(listOf('a', 'b', 'o'), "baobab"))
        assertFalse(canBuildFrom(listOf('a', 'm', 'r'), "Marat"))
        assertTrue(canBuildFrom(listOf('V'), "v"))
        assertFalse(canBuildFrom(listOf('a', 'a'), " "))
    }

    @Test
    @Tag("Normal")
    fun extractRepeats() {
        assertEquals(
            emptyMap<String, Int>(),
            extractRepeats(emptyList())
        )
        assertEquals(
            mapOf("a" to 2),
            extractRepeats(listOf("a", "b", "a"))
        )
        assertEquals(
            emptyMap<String, Int>(),
            extractRepeats(listOf("a", "b", "c"))
        )
    }

    @Test
    @Tag("Normal")
    fun hasAnagrams() {
        assertFalse(hasAnagrams(emptyList()))
        assertTrue(hasAnagrams(listOf("рот", "свет", "тор")))
        assertFalse(hasAnagrams(listOf("рот", "свет", "код", "дверь")))
        assertFalse(hasAnagrams(listOf("тор", "торро")))
        assertFalse(
            hasAnagrams
                (
                listOf(
                    "",
                    "a",
                    "i",
                    "aaaaaaaaaaaaaaraaaaaaaaaaaaaaaa/v&|^g{|q| )ay`(mb5J_#{YM",
                    "(^r",
                    ";S.(USF-7%ou&-r=YE\n",
                    "#sRsOg",
                    "*O!",
                    "=i(9$.Sp*tWcQl^+%z",
                    "JP'NYs+d:;}']I",
                    "Fcw?C)Jt(ow.oo3W+l0|=(<Co([B}A*C0K[SLM.nU}$6rZ+m/;h`dM\t5&qaRo:c#f>"
                )
            )
        )
        assertFalse(
            hasAnagrams(
                listOf(
                    "a",
                    ""
                )
            )
        )
    }

    @Test
    @Tag("Hard")
    fun propagateHandshakes() {
        assertEquals(
            mapOf(
                "Marat" to setOf("Mikhail", "Sveta"),
                "Sveta" to setOf("Mikhail"),
                "Mikhail" to setOf()
            ),
            propagateHandshakes(
                mapOf(
                    "Marat" to setOf("Sveta"),
                    "Sveta" to setOf("Mikhail")
                )
            )
        )
        assertEquals(
            mapOf(
                "Marat" to setOf("Mikhail", "Sveta"),
                "Sveta" to setOf("Marat", "Mikhail"),
                "Mikhail" to setOf("Sveta", "Marat")
            ),
            propagateHandshakes(
                mapOf(
                    "Marat" to setOf("Mikhail", "Sveta"),
                    "Sveta" to setOf("Marat"),
                    "Mikhail" to setOf("Sveta")
                )
            )
        )
        assertEquals(
            mapOf(
                "1" to setOf("0"),
                "2" to setOf("1", "0"),
                "3" to setOf("2", "1", "0"),
                "0" to setOf()
            ),
            propagateHandshakes(
                mapOf(
                    "1" to setOf("0"),
                    "2" to setOf("1"),
                    "3" to setOf("2")
                )
            )
        )
        assertEquals(
            mapOf(
                "1" to setOf(),
                "2" to setOf("0", "3", "1"),
                "0" to setOf("3", "1"),
                "3" to setOf("1")
            ),
            propagateHandshakes(
                mapOf(
                    "1" to setOf(),
                    "2" to setOf("0"),
                    "0" to setOf("3"),
                    "3" to setOf("1")
                )
            )
        )
        assertEquals(
            mapOf(
                "1f0" to setOf("136", "1"),
                "108" to setOf("1f0", "136", "1"),
                "1e" to setOf(),
                "1" to setOf(),
                "136" to setOf("1f0", "1")
            ),
            propagateHandshakes(
                mapOf(
                    "1f0" to setOf("136"),
                    "108" to setOf("1f0"),
                    "136" to setOf("1f0", "1"),
                    "1e" to setOf()
                )
            )
        )
        assertEquals(
            mapOf(
                "0" to setOf("1", "2"),
                "2" to setOf(),
                "3" to setOf(),
                "1" to setOf("2"),
                "4" to setOf("0", "1", "2")

            ),
            propagateHandshakes(
                mapOf(
                    "0" to setOf("1"),
                    "2" to setOf(),
                    "3" to setOf(),
                    "1" to setOf("2"),
                    "4" to setOf("0")
                )
            )
        )
        assertEquals(
            mapOf(
                "0" to setOf("1"),
                "1" to setOf(),
                "2" to setOf("3", "0", "1"),
                "3" to setOf("0", "1")
            ),
            propagateHandshakes(
                mapOf(
                    "0" to setOf("1"),
                    "1" to setOf(),
                    "2" to setOf("3"),
                    "3" to setOf("0")
                )
            )
        )
    }

    @Test
    @Tag("Hard")
    fun findSumOfTwo() {
        assertEquals(
            Pair(-1, -1),
            findSumOfTwo(emptyList(), 1)
        )
        assertEquals(
            Pair(0, 2),
            findSumOfTwo(listOf(1, 2, 3), 4)
        )
        assertEquals(
            Pair(-1, -1),
            findSumOfTwo(listOf(1, 2, 3), 6)
        )
        assertEquals(
            Pair(0, 1),
            findSumOfTwo(listOf(2, 2), 4)
        )
    }

    @Test
    @Tag("Impossible")
    fun bagPacking() {
        assertEquals(
            setOf("Кубок"),
            bagPacking(
                mapOf("Кубок" to (500 to 2000), "Слиток" to (1000 to 5000)),
                850
            )
        )
        assertEquals(
            emptySet<String>(),
            bagPacking(
                mapOf("Кубок" to (500 to 2000), "Слиток" to (1000 to 5000)),
                450
            )
        )
        assertEquals(
            setOf("0"),
            bagPacking(
                mapOf("0" to (436 to 120), "1" to (413 to 116)),
                716
            )
        )
        assertEquals(
            setOf("10", "8", "7", "6", "5", "4", "3", "2", "1", "0"),
            bagPacking(
                mapOf(
                    "0" to (1 to 1),
                    "1" to (457 to 229),
                    "2" to (1 to 1),
                    "3" to (1 to 1),
                    "4" to (1 to 1),
                    "5" to (167 to 87),
                    "6" to (1 to 1),
                    "7" to (1 to 1),
                    "8" to (1 to 1),
                    "9" to (472 to 219),
                    "10" to (418 to 148)
                ),
                1066
            )
        )
        assertEquals(
            setOf("0"),
            bagPacking(
                mapOf("0" to (1 to 1)),
                1
            )
        )
    }

    // TODO: map task tests
}
