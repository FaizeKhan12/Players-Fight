class Armor(name:String):Item(name) {
    var protectionHits: Int = 0
    override fun toString(): String {
        var string = ""
        string += ("protectionHits $protectionHits\n")
        return name
    }

}
