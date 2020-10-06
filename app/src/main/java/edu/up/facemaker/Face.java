/**
 * @author Emily Vo
 * Date: September 9th 2020
 * Face Class
 * includes components that will randomize and make up a face
 */

package edu.up.facemaker;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import java.util.Random;

public class Face extends SurfaceView implements View.OnClickListener,
        SeekBar.OnSeekBarChangeListener, RadioGroup.OnCheckedChangeListener,
        AdapterView.OnItemSelectedListener {

    //get references to seekbars
    private SeekBar redBar = null;
    private SeekBar greenBar = null;
    private SeekBar blueBar = null;


    //instance variables for randomizing values
    private int skinColor;
    private int eyeColor;
    private int hairColor;
    private int hairStyle;
    private int radioButton = 0;

    //these values store the rgb values of each component
    private int skinR;
    private int skinG;
    private int skinB;
    private int eyeR;
    private int eyeG;
    private int eyeB;
    private int hairR;
    private int hairG;
    private int hairB;

    //paints we will use to draw the face
    Paint facePaint = new Paint();
    Paint eyePaint = new Paint();
    Paint hairPaint = new Paint();
    Paint eyeWhite = new Paint();
    Paint pupilBlack = new Paint();
    Paint mouthColor = new Paint();

    /**
     * Face() Constructor
     * Constructor creates a randomized face using randomize().
     * Sets background to white and creates a random face
     */

    public Face(Context context, AttributeSet attrs) {
        super(context, attrs);
        setWillNotDraw(false);
        setBackgroundColor(Color.WHITE);
        randomize();
    }


    /**
     * randomize() method
     * Randomizes instance variables for a Face object.
     */

    public void randomize() {
        Random r = new Random();
        //randomizes values of colors
        skinR = r.nextInt(256);
        skinG = r.nextInt(256);
        skinB = r.nextInt(256);
        eyeR = r.nextInt(256);
        eyeG = r.nextInt(256);
        eyeB = r.nextInt(256);
        hairR = r.nextInt(256);
        hairG = r.nextInt(256);
        hairB = r.nextInt(256);

        //assigns colors with randomized values
        this.skinColor = Color.argb(255, skinR, skinG, skinB);
        this.eyeColor = Color.argb(255, eyeR, eyeG, eyeB);
        this.hairColor = Color.argb(255, hairR, hairG, hairB);


        //randomizes the assigned hair number
        this.hairStyle = r.nextInt(4);

        //set corresponding colors and styles for the paints
        eyeWhite.setColor(Color.WHITE);
        eyeWhite.setStyle(Paint.Style.FILL);
        pupilBlack.setColor(Color.BLACK);
        pupilBlack.setStyle(Paint.Style.FILL);
        facePaint.setStyle(Paint.Style.FILL);
        facePaint.setColor(this.skinColor);
        eyePaint.setColor(this.eyeColor);
        hairPaint.setColor(this.hairColor);
        mouthColor.setColor(Color.WHITE);
    }
    /**
     * External Citation
     * Date: September 9, 2020
     * Problem: Did not remember how to get random numbers
     * Resource: https://stackoverflow.com/questions/5887709/getting-random-numbers-in-java
     * Solution: Found the import and functions required.
     *
     * Date: September 9, 2020
     * Problem: Forgot that we can use hexadecimals to store colors in java
     * Resource: Andrew Nuxoll
     * Solution: Reminded me that colors can be stored using hexadecimals
     */


    /**
     * onDraw method
     * @param canvas
     * A method for the surfaceview to draw what we want onto it
     */
    public void onDraw(Canvas canvas){
        //set the seekBar values based on what is drawn
        setSeekBarProgress(this.radioButton);

        //draw the hair
        drawHair(canvas);

        //draw the base of the face
        canvas.drawCircle(800.0f, 550.0f, 400, this.facePaint);

        //draw eye whites
        canvas.drawCircle(600.0f, 450.0f, 100, eyeWhite);
        canvas.drawCircle(1000.0f, 450.0f, 100, eyeWhite);
        //eye iris
        canvas.drawCircle(600f, 450.0f, 70, this.eyePaint);
        canvas.drawCircle(1000f, 450f, 70f, eyePaint);
        //eye pupils
        canvas.drawCircle(600f, 450f, 50f, pupilBlack);
        canvas.drawCircle(1000f, 450f, 50f, pupilBlack);

        //draw smile
        canvas.drawArc(600f, 500f, 1000f, 800f, 0,
                180, false, mouthColor);
    }

    /**
     * drawHair method is a helper method to draw different hair
     * It will check what hairStyle state it is in, and draw the according hair value
     * @param canvas
     */
    public void drawHair(Canvas canvas){
        if(this.hairStyle == 0){
            //mohawk hairstyle
            canvas.drawRect(650f, 50f, 950f, 600f, hairPaint);
        }
        else if(this.hairStyle == 1){
            //reverse mohawk
            canvas.drawRect(400f, 50f, 650f, 550f, hairPaint);
            canvas.drawRect(950f, 50f, 1200, 550f, hairPaint);
        }
        else if(this.hairStyle == 2){
            //fullhead
            canvas.drawRect(400f, 50f, 1200f, 550f, hairPaint);
        }
        else{
            //bald hairstyle, nothing is drawn
        }
        return;
    }

    //LISTENERS
    /**
     * onClick method, randomizes face
     */
    @Override
    public void onClick(View v) {
        Log.i("clicked","yes"); //checks if button has been clicked in logcat
        //calls to randomize variables then redraws the face
        randomize();
        invalidate();
    }

    /**
     * implementing onProgressChanged seekbars
     * @param seekBar
     * @param progress
     * @param fromUser
     */
    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        //switch statement checks which radio button is checked, changing the correct trait
        switch(this.radioButton){
            case 0:
                //repetitive switch statements in each radio button case
                //these statements check which seekbar was changed, and changed correct color
                switch(seekBar.getId()){
                    case R.id.redbar:
                        this.eyeColor = Color.argb(255, progress, eyeG, eyeB);
                        this.eyeR = progress;
                        eyePaint.setColor(this.eyeColor);
                        break;
                    case R.id.greenbar:
                        this.eyeColor = Color.argb(255, eyeR, progress, eyeB);
                        this.eyeG = progress;
                        eyePaint.setColor(this.eyeColor);
                        break;
                    case R.id.bluebar:
                        this.eyeColor = Color.argb(255, eyeR, eyeG, progress);
                        this.eyeB = progress;
                        eyePaint.setColor(this.eyeColor);
                        break;
                }
                break;
            case 1:
                switch(seekBar.getId()){
                    case R.id.redbar:
                        this.hairColor = Color.argb(255, progress, hairG, hairB);
                        this.hairR = progress;
                        hairPaint.setColor(this.hairColor);
                        break;
                    case R.id.greenbar:
                        this.hairColor = Color.argb(255, hairR, progress, hairB);
                        this.hairG = progress;
                        hairPaint.setColor(this.hairColor);
                        break;
                    case R.id.bluebar:
                        this.hairColor = Color.argb(255, hairR, hairG, progress);
                        this.hairB = progress;
                        hairPaint.setColor(this.hairColor);
                        break;
                }
                break;
            case 2:
                switch(seekBar.getId()){
                    case R.id.redbar:
                        this.skinColor = Color.argb(255, progress, skinG, skinB);
                        this.skinR = progress;
                        facePaint.setColor(this.skinColor);
                        break;
                    case R.id.greenbar:
                        this.skinColor = Color.argb(255, skinR, progress, skinB);
                        this.skinG = progress;
                        facePaint.setColor(this.skinColor);
                        break;
                    case R.id.bluebar:
                        this.skinColor = Color.argb(255, skinR, skinG, progress);
                        this.skinB = progress;
                        facePaint.setColor(this.skinColor);
                        break;
                }
                break;
        }

        invalidate();
    }

    /**
     * setSeekBarProgress method
     * A helper method to help set the seekbar values whenever the rgb values are changed
     * @param radioButton
     */
    public void setSeekBarProgress(int radioButton){
        //depending on which radioButton state we are in, update according values
        switch(radioButton){
            case 0:
                this.redBar.setProgress(this.eyeR);
                this.greenBar.setProgress(this.eyeG);
                this.blueBar.setProgress(this.eyeB);
                break;
            case 1:
                this.redBar.setProgress(this.hairR);
                this.greenBar.setProgress(this.hairG);
                this.blueBar.setProgress(this.hairB);
                break;
            case 2:
                this.redBar.setProgress(this.skinR);
                this.greenBar.setProgress(this.skinG);
                this.blueBar.setProgress(this.skinB);
                break;
        }
    }

    /**
     * setSeekBar method
     * gets references to seekBars from MainActivity
     * @param redBar
     * @param greenBar
     * @param blueBar
     */
    public void setSeekBar(SeekBar redBar, SeekBar greenBar, SeekBar blueBar){
        this.redBar = redBar;
        this.greenBar = greenBar;
        this.blueBar = blueBar;
        return;
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
        //not required
    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        //not required
    }

    /**
     * onCheckedChanged method
     * allows us to see which radio button is checked
     * and changes the state of radioButton accordingly
     * radioButton is an int tracker
     * @param group
     * @param checkedId
     */
    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        //assigns variable to keep track of which option we are on
        switch(checkedId){
            case R.id.eyebutton:
                this.radioButton = 0;
                Log.i("0:","selected");
                break;
            case R.id.hairbutton:
                this.radioButton = 1;
                Log.i("1:","selected");
                break;
            case R.id.skinbutton:
                this.radioButton = 2;
                Log.i("2:","selected");
                break;
        }
        invalidate(); //will call ondraw again to update seekbars
    }

    /**
     * onItemSelected method
     * listens to the spinner and lets us know which hairstyle the user wants from spinner
     * @param parent
     * @param view
     * @param position
     * @param id
     */
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        this.hairStyle = position; //sets hairStyle to current position
        invalidate();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        //not required for this code
    }
}

