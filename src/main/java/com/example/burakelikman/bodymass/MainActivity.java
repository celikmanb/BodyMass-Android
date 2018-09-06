package com.example.burakelikman.bodymass;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText editText;
    private TextView boy_tv,durum_tv,kilo_tv,ideal_tv;
    private SeekBar seekBar;
    private RadioGroup radioGroup;
    private boolean is_boy=true;
    private double boy=0.0;
    private int kilo=50;

    private RadioGroup.OnCheckedChangeListener radioGroup_event=new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {

            if (checkedId==R.id.bay){
                is_boy=true;
                update();
            }else if (checkedId==R.id.bayan){
                is_boy=false;
                update();
            }

        }
    };

    private SeekBar.OnSeekBarChangeListener seekBar_event=new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            kilo=30+progress;
            update();
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }
    };

    private TextWatcher editText_event=new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

            try{
                boy=Double.parseDouble(s.toString())/100.0;
            }catch (NumberFormatException e){
                boy=0.0;
            }

            update();
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText=(EditText) findViewById(R.id.editText);
        boy_tv= (TextView) findViewById(R.id.boy_tv);
        durum_tv= (TextView) findViewById(R.id.durum_tv);
        ideal_tv= (TextView) findViewById(R.id.ideal_tv);
        kilo_tv= (TextView) findViewById(R.id.kilo_tv);
        radioGroup=(RadioGroup) findViewById(R.id.radioGroup);
        seekBar=(SeekBar) findViewById(R.id.seekBar);

        editText.addTextChangedListener(editText_event);
        seekBar.setOnSeekBarChangeListener(seekBar_event);
        radioGroup.setOnCheckedChangeListener(radioGroup_event);

        update();


    }

    private void update() {
        kilo_tv.setText(String.valueOf(kilo)+" kg");
        boy_tv.setText(String.valueOf(boy)+" m");

        int ideal_kiloM=(int) (50+2.3*(boy*100*0.4-60));
        int ideal_kiloF=(int) (45+2.3*(boy*100*0.4-60));
        double bmi=kilo/(boy*boy);

        if (is_boy){
            ideal_tv.setText(String.valueOf(ideal_kiloM));

            if (bmi<=20.7){
                durum_tv.setText(R.string.weak);
                durum_tv.setBackgroundResource(R.color.weak);
            }else if(20.7<bmi && bmi<=26.4){
                durum_tv.setText(R.string.ideal);
                durum_tv.setBackgroundResource(R.color.ideal);
            }else if(26.4<bmi && bmi<=27.8){
                durum_tv.setText(R.string.more_ideal);
                durum_tv.setBackgroundResource(R.color.more_ideal);
            }else if(27.8<bmi && bmi<=31.1){
                durum_tv.setText(R.string.much);
                durum_tv.setBackgroundResource(R.color.much);
            }else if(31.1<bmi && bmi<=34.9){
                durum_tv.setText(R.string.too_much);
                durum_tv.setBackgroundResource(R.color.too_much);
            }else{
                durum_tv.setText(R.string.doctor);
                durum_tv.setBackgroundResource(R.color.doctor);
            }
        }else {
            ideal_tv.setText(String.valueOf(ideal_kiloF));

            if (bmi<=19.1){
                durum_tv.setText(R.string.weak);
                durum_tv.setBackgroundResource(R.color.weak);
            }else if(19.1<bmi && bmi<=25.8){
                durum_tv.setText(R.string.ideal);
                durum_tv.setBackgroundResource(R.color.ideal);
            }else if(25.8<bmi && bmi<=27.3){
                durum_tv.setText(R.string.more_ideal);
                durum_tv.setBackgroundResource(R.color.more_ideal);
            }else if(27.3<bmi && bmi<=32.3){
                durum_tv.setText(R.string.much);
                durum_tv.setBackgroundResource(R.color.much);
            }else if(32.3<bmi && bmi<=34.9){
                durum_tv.setText(R.string.too_much);
                durum_tv.setBackgroundResource(R.color.too_much);
            }else{
                durum_tv.setText(R.string.doctor);
                durum_tv.setBackgroundResource(R.color.doctor);
            }
        }
    }
}
