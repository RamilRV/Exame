package painting

import kotlin.math.abs
import kotlin.math.cos
import kotlin.math.sin

class Function {
    fun const(x: Double) =  x
    fun fx(x: Double) = sin(x) - x

    fun tx(t: Double) = -2*cos(t) + 3 * cos(abs(2*t/3))
    fun ty(t: Double) = -2*sin(t) + 3 * sin(abs(2*t/3))
}