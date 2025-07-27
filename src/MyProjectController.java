//The class will draw a graph showing the average temperature for each month between 2010-2014

import java.awt.TextArea;
import java.util.Random;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class MyProjectController {

    @FXML
    private Canvas cnv;
    private GraphicsContext gc;
    private final int RECT_WIDTH = 20;
    private final int MONTHS = 12;
    private final int MAX_HEIGTH = 250;
    private final int MAX_TEMPER = 46;
    private final int YEARS[] = {2010,2011,2012,2013,2014};
    private int YEAR_INDEX = -1;

    public void initialize() {
        gc = cnv.getGraphicsContext2D();
        gc.setFill(Color.DARKBLUE);
        gc.fillText("Welcome to the Weather Forecast Viewer!", 100, 100);
        gc.fillText("Click 'Next' to view the average monthly temperatures for 2010–2014.", 100, 130);
    }

    @FXML
        //The method will draw the graph after clicking the "Next" button. Pressing the "next" button again will show the next year.
    void nextBtn(ActionEvent event) {

        Random r = new Random();
        int temper = 0;
        int maxMonth = -1;
        int minMonth = 251;
        int maxMonthNum = 0; int minMonthNum = 0;

        //If the last graph is displayed then clicking the "Next" button will show the first year
        YEAR_INDEX++;
        if (YEAR_INDEX == 5) {
            YEAR_INDEX = 0;
        }
        gc.clearRect(0, 0, cnv.getWidth(),cnv.getHeight());// clean the canvas
        gc.setFill(Color.BLACK); //color for X-axis and Y-axis
        gc.fillRect(25 , 20 , 1,cnv.getHeight()-50);// Y-axis
        gc.fillRect(25, cnv.getHeight()-50,cnv.getWidth()- 50 ,1 );// X-axis
        gc.strokeText(""+YEARS[YEAR_INDEX], cnv.getWidth()/2 , 10);//write the year

        //write the temperature on Y-axis
        for (int j = 0 ; j < MAX_TEMPER ; j= j+5 ) {
            gc.strokeText(""+j, 5 , cnv.getHeight()- 45 - temper);
            temper = temper + 30;

        }

        //Draw rectangles representing the average temperature each month
        for (int i = 0; i < MONTHS; i++) {
            int hight = r.nextInt(MAX_HEIGTH);
            int monthNum = i+1;

            //Save the month with the highest temperature
            if (hight > maxMonth) {
                maxMonth = hight;
                maxMonthNum = monthNum ;
            }
            //Save the month with the lowest temperature
            else if (hight < minMonth) {
                minMonth = hight;
                minMonthNum = monthNum ;
            }

            //Color the rectangles gray by default
            gc.setFill(Color.GRAY);
            gc.fillRect(RECT_WIDTH * monthNum * 2, cnv.getHeight()- 50 - hight, RECT_WIDTH ,hight);
            gc.strokeText(""+monthNum, RECT_WIDTH * monthNum * 2, cnv.getHeight()- 30);//write mount's number on X-axis

        }
        //Color the month with the highest temperature in red
        gc.setFill(Color.RED);
        gc.fillRect(RECT_WIDTH * maxMonthNum * 2, cnv.getHeight()- 50 - maxMonth, RECT_WIDTH ,maxMonth);

        //Color the month with the lowest temperature in blue
        gc.setFill(Color.BLUE);
        gc.fillRect(RECT_WIDTH * minMonthNum * 2, cnv.getHeight()- 50 - minMonth, RECT_WIDTH ,minMonth);

    }


}
