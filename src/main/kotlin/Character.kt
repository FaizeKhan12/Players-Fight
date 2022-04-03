class Character(
    var name: String,
    var race: String,
    private var hitPoints: Int,
    var currentHitPoints: Int,
    private val strength: Int,
    private val agility: Int,
    var weapon: Weapon,
    var armor: Armor
) {
    override fun toString(): String {
        var string = ""
        string += ("name $name\n")
        string += ("race $race\n")
        string += ("hitPoints $hitPoints\n")
        string += ("currentHitPoints $currentHitPoints\n")
        string += ("strength $strength\n")
        string += ("agility $agility\n")
        string += ("weapon $weapon")
        string += ("armor $armor")
        return string
    }

    fun currentStatus() {
        println("current Status")
    }

    fun reviveCharacter() {
        currentHitPoints = 0
    }

    fun reduceHits(hitsRemoval: Int) {
        currentHitPoints -= hitsRemoval
    }

    fun hitPoints(): Int {
        return hitPoints
    }
}