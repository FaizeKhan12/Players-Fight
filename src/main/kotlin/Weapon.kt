class Weapon(name:String):Item(name) {
    var damageHits: Int = 0
    override fun toString(): String {
        var string = ""
        string += ("damageHits $damageHits\n")
        return name
    }

}
