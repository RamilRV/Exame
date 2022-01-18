package painting

import kotlin.math.abs
import kotlin.math.cos
import kotlin.math.sin

class FunctionParam : FunctionIn {

    override fun function1(t : Double)= -2* cos(t) + 3 * cos(abs(2*t/3))
    override fun function2(t : Double)  = -2*sin(t) + 3 * sin(abs(2*t/3))

    //fun xt(t : Double)= -2* cos(t) + 3 * cos(abs(2*t/3))
    //fun yt(t : Double)  = -2*sin(t) + 3 * sin(abs(2*t/3))
}