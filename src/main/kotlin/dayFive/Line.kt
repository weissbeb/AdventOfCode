package dayFive

class Line (x1 : Int, y1 : Int, x2 : Int, y2 : Int){

    val NORTHEAST = 0
    val EAST = 1
    val SOUTHEAST = 2
    val SOUTH = 3

    var p1 : Coordinate = Coordinate(x1,y1)
    var p2 : Coordinate= Coordinate(x2,y2)
    var coordinatesICorrupt = mutableListOf<Coordinate>()
    var length = 0
    var direction = NORTHEAST //0 = NE, 1 = E, 2 = SE, 3 = S

    init{
        rotateIfNecessary()
        fetchDirection()
        calcLength()
        fetchConsumption()
    }

    private fun rotateIfNecessary(){
        val rotationNeeded = (p1.x == p2.x && p2.y < p1.y) || (p1.y == p2.y && p2.x < p1.x)
                || (p1.x > p2.x && p1.y > p2.y) || (p1.x > p2.x && p1.y < p2.y)

        if (rotationNeeded){
            val pTemp = p1
            p1 = p2
            p2 = pTemp
        }

    }

    private fun fetchDirection(){
        direction =
            if(p1.x == p2.x) SOUTH
            else if (p1.y == p2.y) EAST
            else if (p1.y > p2.y) NORTHEAST
            else SOUTHEAST
    }

    private fun calcLength(){
        length =
            if (p1.x == p2.x)
                p2.y - p1.y
            else //case: either y1==y2 or line is not straight - doesn't matter
                p2.x - p1.x
    }

    private fun fetchConsumption(){
        for(i in 0..length){
            var x = p1.x
            var y = p1.y

            if(direction == EAST) x += i
            else if(direction == SOUTH) y += i
            else if(direction == SOUTHEAST){
                x += i
                y += i
            }
            else if(direction == NORTHEAST){
                x += i
                y -= i
            }

            coordinatesICorrupt.add(Coordinate(x,y))
        }
    }

}