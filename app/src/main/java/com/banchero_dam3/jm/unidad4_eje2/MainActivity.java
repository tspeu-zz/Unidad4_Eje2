package com.banchero_dam3.jm.unidad4_eje2;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private ImageButton laserGun, pistolaSP;
    MediaPlayer mp;
    private int balas, disparosPistolaSP, opcion;
    private boolean loaded = false;
    private SeekBar seekBar;
    private TextView textViewSeekBar;
    SoundPool soundPool;
    private RadioGroup rdg;
    private RadioButton r1,r2,r3,r4,r5;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        seekBar = (SeekBar) findViewById(R.id.seekBarDisparos);

        textViewSeekBar= (TextView) findViewById(R.id.textViewds);
        textViewSeekBar.setText("Balas: 1");

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                int valor = progress;
                textViewSeekBar.setText("Balas: " + (valor + 1));
                balas = valor +1;
              //  Log.d("balas", " balas seekbar " + balas);
            }

            @Override
            public void onStartTrackingTouch(SeekBar arg0) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar arg0) {

            }
        });

        rdg = (RadioGroup) findViewById(R.id.GrbGrupo1);
        int idSeleccionado = rdg.getCheckedRadioButtonId();
       // rdg.clearCheck();
        r1 = (RadioButton) findViewById(R.id.radioButton1);
        r2 = (RadioButton) findViewById(R.id.radioButton2);
        r3 = (RadioButton) findViewById(R.id.radioButton3);
        r4 = (RadioButton) findViewById(R.id.radioButton4);
        r5 = (RadioButton) findViewById(R.id.radioButton5);

        rdg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                 //opcion = 0;
                switch(checkedId) {
                    case R.id.radioButton1:
                        opcion = 1;
                        Log.d("radio es1", opcion+"");
                        break;
                    case R.id.radioButton2:
                        opcion = 2;
                        Log.d("radio es3", opcion+"");
                        break;
                    case R.id.radioButton3:
                        opcion = 3;
                        Log.d("radio es43", opcion+"");
                        break;
                    case R.id.radioButton4:
                        opcion = 4;
                        Log.d("radio es", opcion+"");
                        break;
                    case R.id.radioButton5:
                        opcion = 5;
                        Log.d("radio es5", opcion+"");
                        break;
                }

                Log.d("radio es", opcion+"");
            }
        });

        //inicializar el SoundPool
        soundPool = new SoundPool( 5, AudioManager.STREAM_MUSIC , 0);

    }

    public void pistolaSimple(View v){
        destruir(mp);
        mp = MediaPlayer.create(this, R.raw.disparo);
        mp.start();

    }

    public void destruir(MediaPlayer pm) {
        if (pm != null)
            pm.release();
    }

    public void soundPoolPistola(View v){

        disparosPistolaSP = soundPool.load(MainActivity.this, R.raw.explosion, 0);



        soundPool.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener() {
            @Override
            public void onLoadComplete(SoundPool soundPool, int sampleId, int status) {

                // la variable  balas  para  usar el slide
                soundPool.play(disparosPistolaSP, 1, 1, 1, balas, 2);
                loaded = true;
        //test
                Log.d("balas SoundPool", balas + "");
                Log.d("radio SoundPoll", opcion + "");

            }
        });
    }

    public void soundPoolPistolaradio(View v){

        disparosPistolaSP = soundPool.load(MainActivity.this, R.raw.explosion, 0);

        soundPool.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener() {
            @Override
            public void onLoadComplete(SoundPool soundPool, int sampleId, int status) {


                // la variable opcion es para usar los radios button
                 soundPool.play(disparosPistolaSP, 1, 1, 1, opcion, 2);
                loaded = true;
                //test
                Log.d("balas SoundPool", balas + "");
                Log.d("radio SoundPoll", opcion + "");

            }
        });
    }


}
