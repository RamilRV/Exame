package painting

import kotlin.math.sin

class Function : FunctionIn{

    override fun function1(x : Double) =  x
    override fun function2(x : Double) = (sin(x) - x)

    //fun const(x : Double) =  x
    //fun fx(x : Double) = (sin(x) - x)

}