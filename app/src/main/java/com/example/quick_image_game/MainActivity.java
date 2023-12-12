package com.example.quick_image_game;

import androidx.appcompat.app.AppCompatActivity;

import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private int[] pics;
    private Playground field;
    private ImageButton[][] buttons;
    private final int SIZE_X = 4;
    private final int SIZE_Y = 4;

    Timer timer = null;
    private int secs = 0;
    private int img;
    private int score;
    private boolean button = true;

    @Override
    public void onClick(View view) {
        if(!button) {
            Position clickedImg = (Position) view.getTag();
            if(field.getImage(clickedImg) == img) {
                score++;
                img = (int) (Math.random()*16);
                setImage(img);
                ((TextView)findViewById(R.id.score)).setText("Score: " + score);
            }
        }

    }

    class SecondsTask extends TimerTask
    {
        @Override
        public void run() {
            runOnUiThread(() -> {
                TextView secsView = findViewById(R.id.time);
                secsView.setText(secs++ +" sec");
            });
        }
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buttons = new ImageButton[SIZE_X][SIZE_Y];
        field = new Playground(SIZE_X, SIZE_Y);
        field.init();
        generateGrid(SIZE_X,SIZE_Y);
        pics = getPicsArray();
        Button b = findViewById(R.id.start);
        b.setOnClickListener( l -> {
            if(button) {
                b.setText("Stop");
                timer = new Timer();
                timer.schedule(new SecondsTask(), 0, 1000);
                img = (int) (Math.random()*16);
                setImage(img);
                secs = 0;
                score = 0;
                ((TextView)findViewById(R.id.score)).setText("Score: " + score);
            } else {
                b.setText("Start");
                timer.cancel();
            }
            button = !button;
        });
    }


    private void generateGrid(int nrCols, int nrRows){
        TableLayout playField = findViewById(R.id.playfield);
        for(int i = 0; i < nrRows; i++){
            TableRow tr1 = generateAndAddRows(i, nrCols);
            playField.addView(tr1);
        }
    }

    private TableRow generateAndAddRows(int row, int nrCols){
        TableRow.LayoutParams tableRowParams=
                new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT,TableRow.LayoutParams.MATCH_PARENT);
        TableRow tr = new TableRow(this);
        tr.setLayoutParams(tableRowParams);
        for(int i = 0; i < nrCols; i++){
            Position p1 = new Position(i, row);
            tr.addView(generateButton(p1));
        }
        return tr;
    }

    private ImageButton generateButton(Position pos){
        ImageButton b = new ImageButton(this);
        b.setImageResource(getPicsArray()[field.getImages()[pos.getX()][pos.getY()]]);
        b.setTag(pos); //We store the coordinates for each card
        b.setOnClickListener(this);
        buttons[pos.getX()][pos.getY()] = b;
        return b;
    }

    public void setImage(int img) {
        TableLayout tl = (TableLayout) findViewById(R.id.img);
        ImageView iv = new ImageView(this);
        tl.removeAllViews();
        iv.setImageResource(getPicsArray()[img]);
        tl.addView(iv);
    }

    public static int[] getPicsArray() {
        int[] c = new int[20];

        c[0] = R.drawable.i000;
        c[1] = R.drawable.i001;
        c[2] = R.drawable.i002;
        c[3] = R.drawable.i003;
        c[4] = R.drawable.i004;
        c[5] = R.drawable.i005;
        c[6] = R.drawable.i006;
        c[7] = R.drawable.i007;
        c[8] = R.drawable.i008;
        c[9] = R.drawable.i009;
        c[10] = R.drawable.i010;
        c[11] = R.drawable.i011;
        c[12] = R.drawable.i012;
        c[13] = R.drawable.i013;
        c[14] = R.drawable.i014;
        c[15] = R.drawable.i015;
        c[16] = R.drawable.i016;
        c[17] = R.drawable.i017;
        c[18] = R.drawable.i018;
        c[19] = R.drawable.i019;
        return c;
    }
}