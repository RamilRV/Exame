package painting

import java.awt.*

class FunctionPainter(
    private val plane: CartesianPlane,
    var function1: (Double)->Double = Math::cos,
    var function2: (Double)->Double = Math::cos
) : Painter{

    var funColor: Color = Color.BLUE

    override fun paint(g: Graphics){
        with (g as Graphics2D){
            color = funColor
            stroke = BasicStroke(4F, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND)
            val rh = mapOf(
                RenderingHints.KEY_ANTIALIASING to RenderingHints.VALUE_ANTIALIAS_ON,
                RenderingHints.KEY_INTERPOLATION to RenderingHints.VALUE_INTERPOLATION_BICUBIC,
                RenderingHints.KEY_RENDERING to RenderingHints.VALUE_RENDER_QUALITY,
                RenderingHints.KEY_DITHERING to RenderingHints.VALUE_DITHER_ENABLE
            )
            setRenderingHints(rh)
            with (plane) {
                var t = -100.0
                while (t<=100){
                    drawLine(
                        xCrt2Scr(function2(t)),
                        yCrt2Scr(function1(t)),
                        xCrt2Scr(function2(t+0.1)),
                        yCrt2Scr(function1(t+0.1))
                    )
                    t = t +0.1
                }
            }
        }
    }
}