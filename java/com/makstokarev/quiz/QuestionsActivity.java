package com.makstokarev.quiz;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class QuestionsActivity extends AppCompatActivity {
    TextView tv;
    Button submitbutton, quitbutton;
    RadioGroup radio_g;
    RadioButton rb1,rb2,rb3,rb4;

    String questions[] = {
                            "Структура компьютера — это:",
                            "Основная функция ЭВМ:",
                            "Персональный компьютер состоит из:",
                            "Системный блок включает в себя:",
                            "От разрядности микропроцессора зависит:",
                            "В состав микропроцессора входят:",
                            "Основная память содержит:",
                            "Устройствами внешней памяти являются:",
                            "К манипуляторам (устройствам указания) относятся:",
                            "Свойствами алгоритма являются:",
                            "В QBASIC существуют следующие типы данных:"
                         };
    String answers[] = {
            "некоторая модель, устанавливающая состав, по- рядок и принципы взаимодействия входящих в нее компонентов",
            "принцип программного управления",
            "монитора",
            "блок питания",
            "максимальный объем внутренней памяти и производительность компьютера",
            "арифметико-логическое устройство",
            "постоянное запоминающее устройство",
            "накопители на гибких магнитных дисках",
            "планшет",
            "дискретность",
            "текстовые, числовые"
    };
    String opt[] = {
                    "комплекс электронных устройств, осуществляющих обработку информации","некоторая модель, устанавливающая состав, по- рядок и принципы взаимодействия входящих в нее компонентов","комплекс программных и аппаратных средств","что это такое?",
                    "общение человека и машины"," разработка задач","принцип программного управления","без понятия",
                    "вилки","ложки","монитора","каких то проводочек не понятных",
                    "модулятор-демодулятор","средства связи и коммуникаций","блок питания","железные штучки",
                    "максимальный объем внутренней памяти и производительность компьютера","возможность подключения к сети","количество используемых внешних устройств","....",
                    "постоянное запоминающее устройство (ПЗУ)"," арифметико-логическое устройство","кодовая шина данных","кодовая шина инструкций",
                    "порты ввода-вывода","кодовую шину инструкций (КШИ)"," КЭШ-память","постоянное запоминающее устройство",
                    "накопители на гибких магнитных дисках","оперативные запоминающие устройства","плоттеры","стриммеры",
                    "клавиатура","планшет","трекбол","сканер",
                    "дискретность","цикличность","оперативность","информативность",
                    "текстовые, числовые","указатели","типы данных","записи"
                   };
    int flag=0;
    public static int marks=0,correct=0,wrong=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions);

        final TextView score = (TextView)findViewById(R.id.textView4);
        TextView textView=(TextView)findViewById(R.id.DispName);
        Intent intent = getIntent();
        String name= intent.getStringExtra("myname");

        if (name.trim().equals(""))
            textView.setText("Привет игрок");
        else
        textView.setText("Привет " + name);

        submitbutton=(Button)findViewById(R.id.button3);
        quitbutton=(Button)findViewById(R.id.buttonquit);
        tv=(TextView) findViewById(R.id.tvque);

        radio_g=(RadioGroup)findViewById(R.id.answersgrp);
        rb1=(RadioButton)findViewById(R.id.radioButton);
        rb2=(RadioButton)findViewById(R.id.radioButton2);
        rb3=(RadioButton)findViewById(R.id.radioButton3);
        rb4=(RadioButton)findViewById(R.id.radioButton4);
        tv.setText(questions[flag]);
        rb1.setText(opt[0]);
        rb2.setText(opt[1]);
        rb3.setText(opt[2]);
        rb4.setText(opt[3]);
        submitbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(radio_g.getCheckedRadioButtonId()==-1)
                {
                    Toast.makeText(getApplicationContext(), "Пожалуйста, выберите один вариант", Toast.LENGTH_SHORT).show();
                    return;
                }
                RadioButton uans = (RadioButton) findViewById(radio_g.getCheckedRadioButtonId());
                String ansText = uans.getText().toString();
//                Toast.makeText(getApplicationContext(), ansText, Toast.LENGTH_SHORT).show();
                if(ansText.equals(answers[flag])) {
                    correct++;
                    Toast.makeText(getApplicationContext(), "Верно", Toast.LENGTH_SHORT).show();
                }
                else {
                    wrong++;
                    Toast.makeText(getApplicationContext(), "Неправильно", Toast.LENGTH_SHORT).show();
                }

                flag++;

                if (score != null)
                    score.setText(""+correct);

                if(flag<questions.length)
                {
                    tv.setText(questions[flag]);
                    rb1.setText(opt[flag*4]);
                    rb2.setText(opt[flag*4 +1]);
                    rb3.setText(opt[flag*4 +2]);
                    rb4.setText(opt[flag*4 +3]);
                }
                else
                {
                    marks=correct;
                    Intent in = new Intent(getApplicationContext(),ResultActivity.class);
                    startActivity(in);
                }
                radio_g.clearCheck();
            }
        });

        quitbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),ResultActivity.class);
                startActivity(intent);
            }
        });
    }

}