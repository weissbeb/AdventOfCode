package dayTwo

class Position (private val useAim : Boolean) {
    private var horizontal : Int = 0
    private var depth : Int = 0
    private var aim : Int = 0

    fun result() : Int = horizontal * depth
    fun changeHorizontal(raise : Int){
        horizontal += raise
        if(useAim){
            depth+=(aim*raise)
        }
    }
    fun changeDepth(raise : Int){
        if(useAim){
            aim += raise
        }else{
            depth += raise
        }
    }
}