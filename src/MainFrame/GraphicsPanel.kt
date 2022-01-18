package MainFrame

import painting.CartesianPainter
import painting.FunctionPainter
import painting.Painter
import java.awt.Graphics
import javax.swing.JPanel

class GraphicsPanel(private val painters: List<Painter>) : JPanel() {

    override fun paint(g: Graphics?) {
        super.paint(g) // класс предок (сначала отрабатывает он)
        // проверка, что график не нулевой(null)
        g?.let{
            painters.forEach { p ->
                p.paint(it)
            }
        }
    }
}