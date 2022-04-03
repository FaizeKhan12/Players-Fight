class Dice(num_sides: Int) {
    private var sidesOfDice: Int = num_sides
    fun roll(): Int {
        //random function to return number between 1 and numSides
        return (1..sidesOfDice).random()
    }
}
