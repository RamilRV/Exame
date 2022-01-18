package painting

import java.awt.*

class CartesianPainter(private val plane: CartesianPlane) : Painter{

    var mainFont: Font = Font("Cambria", Font.BOLD, 16)
    var maxTickColor: Color = Color.RED

    override fun paint(g: Graphics) {
        with(plane) {
            (g as Graphics2D).apply {
                stroke = BasicStroke(3F)
                drawLine(xCrt2Scr(0.0), 0, xCrt2Scr(0.0), height)
                drawLine(0, yCrt2Scr(0.0), width, yCrt2Scr(0.0))
                drawXLabels(g)
                drawYLabels(g)
                drawXString(g)
                drawYString(g)
            }
        }
    }

    private fun drawXLabels(g: Graphics) {
        with (g as Graphics2D){
            stroke = BasicStroke(2F)
            color = maxTickColor
            font = mainFont
            //var tickValue = 0.0
            var k = plane.xDen.toInt()
            var t = plane.xDen.toInt()
            if(plane.xMin.toInt()>0.1) t=0
            if(plane.xMax.toInt()<-0.1) k=0
            with(plane) {
                for(i in plane.xMin.toInt()*t .. (plane.xMax).toInt()*k) {
                    color = Color.RED
                    var tSize = 8
                    if((i%5)!=0){
                        tSize -=3
                        color= Color.GRAY
                    }
                    if((i%10)!=0 && (i%5)==0){
                        tSize -=2
                        color= Color.BLUE
                    }
                    //tickValue = tickValue + 1
                    drawLine(xCrt2Scr(i/10.0), yCrt2Scr(0.0) + tSize, xCrt2Scr(i/10.0), yCrt2Scr(0.0) - tSize)
                    if(yMin>0){
                        drawLine(xCrt2Scr(i/10.0), height, xCrt2Scr(i/10.0), height - 5)
                    }
                    if(yMax<0){
                        drawLine(xCrt2Scr(i/10.0), 0, xCrt2Scr(i/10.0),  5)
                    }
                }
            }
        }
    }

    private fun drawXString(g : Graphics){
        with (g as Graphics2D){
            stroke = BasicStroke(2F)
            color = maxTickColor
            font = mainFont
            //var tickValue = 0.0
            var k = plane.xDen.toInt()
            var t = plane.xDen.toInt()
            if(plane.xMin.toInt()>0) t=0
            if(plane.xMax.toInt()<0) k=0
            var tSize = 8
            with(plane) {
                for (i in plane.xMin.toInt() * t..(plane.xMax).toInt() * k) {
                    //tickValue = tickValue + 1
                    //val (tW, tH) = with(fontMetrics.getStringBounds(tickValue.toString(), g)) {
                    //    Pair(width.toInt(), height.toInt())
                    //}
                    if(i>0) drawString(i.toString(), xCrt2Scr(i * 1.0) - 5, yCrt2Scr(0.0) + tSize + 14)
                    if(i<0) drawString(i.toString(), xCrt2Scr(i * 1.0) - 10, yCrt2Scr(0.0) + tSize + 14)
                    if(yMin>0){
                        if(i>0) drawString(i.toString(), xCrt2Scr(i * 1.0) - 5, height - tSize )
                        if(i<0) drawString(i.toString(), xCrt2Scr(i * 1.0) - 10, height - tSize )
                    }
                    if(yMax<0){
                        if(i>0) drawString(i.toString(), xCrt2Scr(i * 1.0) - 5,  tSize*2 )
                        if(i<0) drawString(i.toString(), xCrt2Scr(i * 1.0) - 10,  tSize * 2 )
                    }
                }
            }
        }
    }

    private fun drawYLabels(g: Graphics) {
        with (g as Graphics2D){
            stroke = BasicStroke(2F)
            color = maxTickColor
            font = mainFont
            var tickValue = -1.0
            var k = plane.yDen.toInt()
            var t = plane.yDen.toInt()
            if(plane.yMin.toInt()>0) t=0
            if(plane.yMax.toInt()<0) k=0
            with(plane) {
                for(i in plane.yMin.toInt()*t.. (plane.yMax).toInt()*k) {
                    color = Color.RED
                    var tSize = 10
                    if((i%5)!=0){
                        tSize -=6
                        color= Color.GRAY
                    }
                    if((i%10)!=0 && (i%5)==0){
                        tSize -=4
                        color= Color.BLUE
                    }
                    drawLine(xCrt2Scr(0.0)- tSize, yCrt2Scr(i/10.0) , xCrt2Scr(0.0)+tSize, yCrt2Scr(i/10.0))
                    /*
                    tickValue = tickValue + 1
                    val (tW, tH) = with(fontMetrics.getStringBounds(tickValue.toString(), g)) {
                        Pair(width.toInt(), height.toInt())
                    }
                    drawString(i.toString(), xCrt2Scr(0.0) , yCrt2Scr(i*1.0) + tSize + tH)*/
                    if(xMin>0){
                        drawLine(0, yCrt2Scr(i/10.0), 5, yCrt2Scr(i/10.0))
                    }
                    if(xMax<0){
                        drawLine(width, yCrt2Scr(i/10.0), width-5,  yCrt2Scr(i/10.0))
                    }
                }
            }
        }
    }

    private fun drawYString(g : Graphics){
        with (g as Graphics2D){
            stroke = BasicStroke(2F)
            color = maxTickColor
            font = mainFont
            var tickValue = 0.0
            var k = plane.yDen.toInt()
            var t = plane.yDen.toInt()
            if(plane.yMin.toInt()>0) t=0
            if(plane.yMax.toInt()<0) k=0
            var tSize = 8
            with(plane) {
                for (i in plane.yMin.toInt() * t..(plane.yMax).toInt() * k) {
                    tickValue = tickValue + 1
                    val (tW, tH) = with(fontMetrics.getStringBounds(tickValue.toString(), g)) {
                        Pair(width.toInt(), height.toInt())
                    }
                    if(i!=0) drawString(i.toString(), xCrt2Scr(0.0) + tW/2 - tSize, yCrt2Scr(i*1.0) - tSize + tH/2)
                    if(i==0) drawString(i.toString(), xCrt2Scr(0.0) + tW/2 - tSize -5, yCrt2Scr(i*1.0) + tH)
                    if(xMin>0){
                        if(i!=0) drawString(i.toString(), tSize, yCrt2Scr(i*1.0) - tSize + tH/2)
                    }
                    if(xMax<0){
                        if(i!=0) drawString(i.toString(), width - tSize*2-4, yCrt2Scr(i*1.0) - tSize + tH/2)
                    }
                }
            }
        }
    }
}