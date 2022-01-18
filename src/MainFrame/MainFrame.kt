package MainFrame

import painting.*
import painting.Function
import java.awt.Button
import java.awt.Checkbox
import java.awt.Color
import java.awt.Dimension
import java.awt.event.*
import javax.swing.*
import javax.swing.event.ChangeEvent
import javax.swing.event.ChangeListener
import kotlin.math.abs

class MainFrame : JFrame(){

    val mainPanel: GraphicsPanel
    val controlPanel: JPanel
    val lXMin: JLabel
    val lXMax: JLabel
    val lYMin: JLabel
    val lYMax: JLabel

    val xMin: JSpinner
    val xMinM: SpinnerNumberModel
    val xMax: JSpinner
    val xMaxM: SpinnerNumberModel
    val yMin: JSpinner
    val yMinM: SpinnerNumberModel
    val yMax: JSpinner
    val yMaxM: SpinnerNumberModel


    init {

        defaultCloseOperation = EXIT_ON_CLOSE
        minimumSize = Dimension(600, 600)

        lXMin = JLabel()
        lXMax = JLabel()
        lYMin = JLabel()
        lYMax = JLabel()
        lXMin.text = "Xmin"
        lXMax.text = "Xmax"
        lYMin.text = "Ymin"
        lYMax.text = "Ymax"

        xMinM = SpinnerNumberModel(-10.0, -100.0, 9.9, 0.1)
        xMin = JSpinner(xMinM)
        xMaxM = SpinnerNumberModel(10.0, -9.9, 100.0, 0.1)
        xMax = JSpinner(xMaxM)
        yMinM = SpinnerNumberModel(-10.0, -100.0, 9.9, 0.1)
        yMin = JSpinner(yMinM)
        yMaxM = SpinnerNumberModel(10.0, -9.9, 100.0, 0.1)
        yMax = JSpinner(yMaxM)

        val plane = CartesianPlane(
            xMin.value as Double,
            xMax.value as Double,
            yMin.value as Double,
            yMax.value as Double,
        )
        val function = Function()
        val functionparam = FunctionParam()
        val f1 = FunctionPainter(plane, function::function2, function::function1)
        val f2 = FunctionPainter(plane,functionparam::function2 , functionparam::function1)
        val cartesianPainter = CartesianPainter(plane)
        f1.funColor = Color.RED
        val painters = mutableListOf(cartesianPainter , f2, f1)

        mainPanel = GraphicsPanel(painters).apply {
            background = Color.WHITE
        }
        plane.pixelSize=mainPanel.size


        mainPanel.addComponentListener(object : ComponentAdapter() { // реализация с пустыми методами
            override fun componentResized(e: ComponentEvent?) {
                plane.pixelSize = mainPanel.size
                mainPanel.repaint()
            }
        })

        controlPanel = JPanel().apply{
            background = Color.WHITE
        }
        layout = GroupLayout(contentPane).apply{
            setHorizontalGroup(
                createSequentialGroup()
                    .addGap(4)
                    .addGroup(
                        createParallelGroup()
                            .addComponent(mainPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE)
                            .addComponent(controlPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE)
                    )
                    .addGap(4)

            )

            setVerticalGroup(
                createSequentialGroup()
                    .addGap(4)
                    .addComponent(mainPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE)
                    .addGap(4)
                    .addComponent(controlPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addGap(4)
            )
        }

        //лямда выражение
        xMin.addChangeListener{
            xMaxM.minimum = xMin.value as Double + 0.1
            plane.xSegment = Pair(xMin.value as Double, xMax.value as Double)
            mainPanel.repaint()
        }
        xMax.addChangeListener{
            xMinM.maximum = xMax.value as Double - 0.1
            plane.xSegment = Pair(xMin.value as Double, xMax.value as Double)
            mainPanel.repaint()
        }
        yMin.addChangeListener{
            yMaxM.minimum = yMin.value as Double + 0.1
            plane.ySegment = Pair(yMin.value as Double, yMax.value as Double)
            mainPanel.repaint()
        }
        yMax.addChangeListener{
            yMinM.maximum = yMax.value as Double - 0.1
            plane.ySegment = Pair(yMin.value as Double, yMax.value as Double)
            mainPanel.repaint()
        }

        controlPanel.layout = GroupLayout(controlPanel).apply {
            setHorizontalGroup(
                createSequentialGroup()
                    .addGap(4)
                    .addGroup(
                        createParallelGroup()
                            .addGroup(
                                createSequentialGroup()
                                    .addComponent(
                                        lXMin,
                                        GroupLayout.PREFERRED_SIZE,
                                        GroupLayout.PREFERRED_SIZE,
                                        GroupLayout.PREFERRED_SIZE
                                    )
                                    .addGap(10)
                                    .addComponent(xMin, 100, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
                                    .addGap(10)
                                    .addComponent(
                                        lXMax,
                                        GroupLayout.PREFERRED_SIZE,
                                        GroupLayout.PREFERRED_SIZE,
                                        GroupLayout.PREFERRED_SIZE
                                    )
                                    .addGap(10)
                                    .addComponent(xMax, 100, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
                            )
                            .addGap(10)
                            .addGroup(
                                createSequentialGroup()
                                    .addComponent(
                                        lYMin,
                                        GroupLayout.PREFERRED_SIZE,
                                        GroupLayout.PREFERRED_SIZE,
                                        GroupLayout.PREFERRED_SIZE
                                    )
                                    .addGap(10)
                                    .addComponent(yMin, 100, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
                                    .addGap(10)
                                    .addComponent(
                                        lYMax,
                                        GroupLayout.PREFERRED_SIZE,
                                        GroupLayout.PREFERRED_SIZE,
                                        GroupLayout.PREFERRED_SIZE
                                    )
                                    .addGap(10)
                                    .addComponent(yMax, 100, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
                            )
                    )
                    .addGap(100, 100, Int.MAX_VALUE)
            )
            setVerticalGroup(
                createParallelGroup()
                    .addGroup(
                        createSequentialGroup()
                            .addGroup(
                                createParallelGroup()
                                    .addComponent(
                                        lXMin,
                                        GroupLayout.PREFERRED_SIZE,
                                        GroupLayout.PREFERRED_SIZE,
                                        GroupLayout.PREFERRED_SIZE
                                    )
                                    .addGap(10)
                                    .addComponent(
                                        xMin,
                                        GroupLayout.PREFERRED_SIZE,
                                        GroupLayout.PREFERRED_SIZE,
                                        GroupLayout.PREFERRED_SIZE
                                    )
                                    .addGap(10)
                                    .addComponent(
                                        lXMax,
                                        GroupLayout.PREFERRED_SIZE,
                                        GroupLayout.PREFERRED_SIZE,
                                        GroupLayout.PREFERRED_SIZE
                                    )
                                    .addGap(10)
                                    .addComponent(
                                        xMax,
                                        GroupLayout.PREFERRED_SIZE,
                                        GroupLayout.PREFERRED_SIZE,
                                        GroupLayout.PREFERRED_SIZE
                                    )
                            )
                            .addGap(10)
                            .addGroup(
                                createParallelGroup()
                                    .addComponent(
                                        lYMin,
                                        GroupLayout.PREFERRED_SIZE,
                                        GroupLayout.PREFERRED_SIZE,
                                        GroupLayout.PREFERRED_SIZE
                                    )
                                    .addGap(10)
                                    .addComponent(
                                        yMin,
                                        GroupLayout.PREFERRED_SIZE,
                                        GroupLayout.PREFERRED_SIZE,
                                        GroupLayout.PREFERRED_SIZE
                                    )
                                    .addGap(10)
                                    .addComponent(
                                        lYMax,
                                        GroupLayout.PREFERRED_SIZE,
                                        GroupLayout.PREFERRED_SIZE,
                                        GroupLayout.PREFERRED_SIZE
                                    )
                                    .addGap(10)
                                    .addComponent(
                                        yMax,
                                        GroupLayout.PREFERRED_SIZE,
                                        GroupLayout.PREFERRED_SIZE,
                                        GroupLayout.PREFERRED_SIZE
                                    )
                            )
                    )
                    .addGap(10)
            )
        }
        pack()

        plane.width = mainPanel.width
        plane.height = mainPanel.height
    }
}