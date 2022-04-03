import java.io.File

fun main() {
    menuItem(0, "", "")
}

fun menuItem(checkMenu: Int, characterName1: String, characterName2: String) {
    if (checkMenu == 0) {
        println("1. Load Character 1")
        println("2. Load Character 2")
        println("3. Fight")
        println("4. Quit")
        print("Please select an option:")
        menuQuery("", "")
    } else if (checkMenu == 1) {
        println("1. Load Character 1: $characterName1")
        println("2. Load Character 2: $characterName2")
        println("3. Fight")
        println("4. Quit")
        print("Please select an option:")
        menuQuery(characterName1, characterName2)
    }
}

fun menuQuery(characterName1: String, characterName2: String) {
    var checkName1 = characterName1
    var checkName2 = characterName2
    val fileNames = filesName()

    val checkMenu = readLine()
    if (checkMenu == "1" && checkMenu != "") {
        print("Enter name of character file: ")
        val checkMenus = readLine()
        if (checkMenus == fileNames[0] || checkMenus == fileNames[1]) {
            checkName1 = playerNameByFile(checkMenus.toString())
            if (checkName1.isNotEmpty()) {
                menuItem(1, checkName1, checkName2)
            } else {
                println("please select correct file name.")
                menuItem(1, checkName1, checkName2)
            }
        } else {
            menuItem(1, checkName1, checkName2)
        }
    } else if (checkMenu == "2" && checkMenu != "") {
        print("Enter name of character file: ")
        val checkMenus = readLine()
        if (checkMenus == fileNames[0] || checkMenus == fileNames[1]) {
            checkName2 = playerNameByFile(checkMenus.toString())
            if (checkName2.isNotEmpty()) {
                menuItem(1, checkName1, checkName2)
            } else {
                println("please select correct file name.")
                menuItem(1, checkName1, checkName2)
            }
        } else {
            menuItem(1, checkName1, checkName2)
        }
    } else if (checkMenu == "3" && checkMenu != "") {
        if (checkName1 == "" || checkName2 == "") {
            println("Please select your characters.")
            menuItem(1, checkName1, checkName2)
        } else {
            loadCharacters(fileNames[0], fileNames[1])
        }
    } else if (checkMenu == "4" && checkMenu != "") {
        println("quit")
    } else {
        menuItem(0, checkName1, checkName2)
    }
}

fun playerNameByFile(character: String): String {
    var line1: Array<String>
    val myList = mutableListOf<String>()
    File(fileLocation(character)).useLines { lines -> myList.addAll(lines) }

    myList.forEachIndexed { i, line ->
        return if (i == 0) {
            line1 = line.split(",").toTypedArray()
            line1[0]
        } else {
            ""
        }
    }
    return ""
}

fun loadCharacters(character1: String, character2: String) {
    var line11 = arrayOf(String())
    var line12 = arrayOf(String())
    var line13 = arrayOf(String())
    var line21 = arrayOf(String())
    var line22 = arrayOf(String())
    var line23 = arrayOf(String())
    val myList1 = mutableListOf<String>()
    val myList2 = mutableListOf<String>()
    File(fileLocation(character1)).useLines { lines -> myList1.addAll(lines) }

    myList1.forEachIndexed { i, line ->
        when (i) {
            0 -> {
                line11 = line.split(",").toTypedArray()
            }
            1 -> {
                line12 = line.split(",").toTypedArray()
            }
            2 -> {
                line13 = line.split(",").toTypedArray()
            }
        }
    }
    File(fileLocation(character2)).useLines { lines -> myList2.addAll(lines) }
    myList2.forEachIndexed { i, line ->
        when (i) {
            0 -> {
                line21 = line.split(",").toTypedArray()
            }
            1 -> {
                line22 = line.split(",").toTypedArray()
            }
            2 -> {
                line23 = line.split(",").toTypedArray()
            }
        }
    }
    val data1 = Character(
        line11[0],
        line11[1],
        line11[2].trim().toInt(),
        line11[2].trim().toInt(),
        line11[3].trim().toInt(),
        line11[4].trim().toInt(),
        Weapon(line12[0]),
        Armor(line13[0])
    )
    data1.weapon.damageHits = line12[1].trim().toInt()
    data1.armor.protectionHits = line13[1].trim().toInt()

    val data2 = Character(
        line21[0],
        line21[1],
        line21[2].trim().toInt(),
        line21[2].trim().toInt(),
        line21[3].trim().toInt(),
        line21[4].trim().toInt(),
        Weapon(line22[0]),
        Armor(line23[0])
    )
    data2.weapon.damageHits = line22[1].trim().toInt()
    data2.armor.protectionHits = line23[1].trim().toInt()
    checkFirstOpponentTurn(
        data1,
        data2,
        line11[4].trim().toInt(),
        line21[4].trim().toInt(),
        line11[3].trim().toInt(),
        line21[3].trim().toInt()
    )
}

fun checkFirstOpponentTurn(
    data1: Character,
    data2: Character,
    agility1: Int,
    agility2: Int,
    strength1: Int,
    strength2: Int
) {
    val dice1 = Dice(agility1)
    val dice2 = Dice(agility1)
    val firstTurn1 = dice1.roll()
    val firstTurn2 = dice2.roll()
    if (firstTurn1 > firstTurn2) {
        println("First player will make first move")
        println("Press enter to fight")
        startFight(
            data1,
            data2,
            agility1,
            agility2,
            strength1,
            strength2,
            1
        )
//    } else if (firstTurn2 > firstTurn1) {
    } else {
        println("Second player will make first move")
        println("Press enter to fight")
        startFight(
            data1,
            data2,
            agility1,
            agility2,
            strength1,
            strength2,
            2
        )
    }

}

fun startFight(
    data1: Character,
    data2: Character,
    agility1: Int,
    agility2: Int,
    strength1: Int,
    strength2: Int,
    scenario: Int
) {

    if (readLine() != null) {
        for (i in 1..2) {
            var scene: Int = scenario
            if(i == 2){
                scene = if(scenario == 1){
                    2
                } else {
                    1
                }
            }
            val hitOrMissDice = Dice(10)
            val strengthRandom4Dice = Dice(4)
            val strengthRandom8Dice = Dice(8)
            val strengthRandom15Dice = Dice(15)
            val hitOrMiss = hitOrMissDice.roll()
            val strengthRandom4 = strengthRandom4Dice.roll()
            val strengthRandom8 = strengthRandom8Dice.roll()
            val strengthRandom15 = strengthRandom15Dice.roll()
            if (scene == 1) {
                println("${data1.name} fights with the ${data1.weapon}:")
                if (hitOrMiss < agility1) {
                    val hit = (strength1 * (1 / strengthRandom4) +
                            data1.weapon.damageHits / strengthRandom8)
                    val armorSave = data2.armor.protectionHits / strengthRandom15
                    println("           Hit: $hit       ${data2.name}'s armor saved $armorSave points")
                    var armored = hit - armorSave
                    if (armored < 0) {
                        armored = 0
                    }
                    println("${data2.name}'s hits are reduced by $armored points.")
                    if (data2.currentHitPoints >= armored) {
                        println("${data2.name} has ${data2.currentHitPoints - armored} left out of ${data2.hitPoints()}.")
                        data2.reduceHits(armored)
                        if (i == 2) {
                            println("Hit return to continue ...")
                            startFight(
                                data1,
                                data2,
                                agility1,
                                agility2,
                                strength1,
                                strength2,
                                2
                            )
                        }
                    } else {
                        println("\n${data1.name} WINS!")
                        println("---------------------------")
                        println("${data2.name} has 0 left out of ${data2.hitPoints()}.")
                        println("${data1.name} has ${data1.hitPoints() - data1.currentHitPoints} left out of ${data1.hitPoints()}.")
                        println("---------------------------\n\n")
                        if (readLine() != null) {
                            menuItem(0, "", "")
                        }
                    }
                } else {
                    println("           Misses!")
                    if (i == 2) {
                        println("Hit return to continue ...")
                        startFight(
                            data1,
                            data2,
                            agility1,
                            agility2,
                            strength1,
                            strength2,
                            2
                        )
                    }
                }
            } else if (scene == 2) {
                println("${data2.name} fights with the ${data2.weapon}:")
                if (hitOrMiss < agility2) {
                    val hit = (strength2 * (1 / strengthRandom4) +
                            data2.weapon.damageHits / strengthRandom8)
                    val armorSave = data1.armor.protectionHits / strengthRandom15
                    println("           Hit: $hit       ${data1.name} armor saved $armorSave points")
                    var armored = hit - armorSave
                    if (armored < 0) {
                        armored = 0
                    }
                    println("${data1.name}'s hits are reduced by $armored points.")
                    if (data1.currentHitPoints >= armored) {
                        println("${data1.name} has ${data1.currentHitPoints - armored} left out of ${data1.hitPoints()}.")
                        data1.reduceHits(armored)
                        if (i == 2) {
                            println("Hit return to continue ...")
                            startFight(
                                data1,
                                data2,
                                agility1,
                                agility2,
                                strength1,
                                strength2,
                                1
                            )
                        }
                    } else {
                        println("\n${data2.name} WINS!")
                        println("---------------------------")
                        println("${data1.name} has 0 left out of ${data1.hitPoints()}.")
                        println("${data2.name} has ${data2.hitPoints() - data2.currentHitPoints} left out of ${data2.hitPoints()}.")
                        println("---------------------------\n\n")
                        if (readLine() != null) {
                            menuItem(0, "", "")
                        }
                    }
                } else {
                    println("           Misses!")
                    if (i == 2) {
                        println("Hit return to continue ...")
                        startFight(
                            data1,
                            data2,
                            agility1,
                            agility2,
                            strength1,
                            strength2,
                            1
                        )
                    }
                }
            }
        }
    }
}

fun fileLocation(fileName: String): String {
    return "src/main/resources/$fileName"
}

fun filesName(): MutableList<String> {
    val fileNames = mutableListOf<String>()
    File("src/main/resources/").walk().forEachIndexed { i, line ->
        if (i > 0) {
            fileNames.add(line.toString().split("\\")[3])
        }
    }
    return fileNames
}