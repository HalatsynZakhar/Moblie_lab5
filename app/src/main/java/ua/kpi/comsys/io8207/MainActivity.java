package ua.kpi.comsys.io8207;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import static android.view.View.INVISIBLE;
import static android.view.View.VISIBLE;

public class MainActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private Lab4_ViewPagerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tabLayout = findViewById(R.id.navigation_id);
        viewPager = findViewById(R.id.viewpager_id);
        adapter = new Lab4_ViewPagerAdapter(getSupportFragmentManager());

        adapter.AddFragment(new Lab1_title(), "");
        adapter.AddFragment(new Lab2_graph(), "");
        adapter.AddFragment(new Lab4_BookFragment(), "");
        adapter.AddFragment(new Lab5_Fragm_Photo_list(), "");

        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

        tabLayout.getTabAt(0).setIcon(R.drawable.ic_home_black_24dp);
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_lab2_graph);
        tabLayout.getTabAt(2).setIcon(R.drawable.ic_lab3_book);
        tabLayout.getTabAt(3).setIcon(R.drawable.ic_lab5_photo);

        //Shadow removing
        ActionBar actionBar = getSupportActionBar();
        actionBar.setElevation(0);

        lab12();
    }


    public void startGraph(View view) {
        System.out.println("startGraph");
        PieChart pieChart = findViewById(R.id.pieChart);
        pieChart.setVisibility(INVISIBLE);

        LineChart lineChart = findViewById(R.id.graph);
        lineChart.setVisibility(VISIBLE);
        ArrayList<Entry> visitors = new ArrayList<>();
        for (float i=-2.0f*(float)Math.PI; i<2.0f*(float)Math.PI; i = i + 0.01f){
            float x = i;
            float y = (float)Math.sin(i);
            visitors.add(new Entry(x , y));
        }

        LineDataSet lineDataSet = new LineDataSet(visitors, "Graph y = sin(x)");
        lineDataSet.setColors(ColorTemplate.MATERIAL_COLORS);
        lineDataSet.setValueTextColor(Color.BLACK);
        lineDataSet.setValueTextSize(16f);

        LineData lineData = new LineData(lineDataSet);

        lineChart.setData(lineData);
        lineChart.getDescription().setText("Graph y = sin(x)");
        lineChart.animate();

    }

    public void startDiagram(View view) {
        System.out.println("startDiagram");

        LineChart lineChart = findViewById(R.id.graph);
        lineChart.setVisibility(INVISIBLE);

        PieChart pieChart = findViewById(R.id.pieChart);
        pieChart.setVisibility(VISIBLE);
        ArrayList<PieEntry> visitors = new ArrayList<>();
        visitors.add(new PieEntry(5, "brown"));
        visitors.add(new PieEntry(5, "light blue"));
        visitors.add(new PieEntry(10, "orange"));
        visitors.add(new PieEntry(80, "blue"));

        PieDataSet pieDataSet = new PieDataSet(visitors, "Diagram");
        pieDataSet.setColors(Color.rgb(96,71,56), Color.rgb(106, 192, 208), Color.rgb(253, 191, 154), Color.rgb(0,0,255));
        //pieDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        pieDataSet.setValueTextColor(Color.BLACK);
        pieDataSet.setValueTextSize(16f);

        PieData pieData = new PieData(pieDataSet);

        pieChart.setData(pieData);
        pieChart.getDescription().setEnabled(false);
        pieChart.setCenterText("Diagram");
        pieChart.animate();

    }

    public void lab12() {
        System.out.println(" \n\nLab1.2 Start-------------------------------------------------\n\n ");


        //Завдання 1--------------------------------

        String studentsStr = "Дмитренко Олександр - ІП-84; Матвійчук Андрій - ІВ-83; Лесик Сергій - ІО-82;" +
                " Ткаченко Ярослав - ІВ-83; Аверкова Анастасія - ІО-83; Соловйов Даніїл - ІО-83; " +
                "Рахуба Вероніка - ІО-81; Кочерук Давид - ІВ-83; Лихацька Юлія - ІВ-82; " +
                "Головенець Руслан - ІВ-83; Ющенко Андрій - ІО-82; Мінченко Володимир - ІП-83; " +
                "Мартинюк Назар - ІО-82; Базова Лідія - ІВ-81; Снігурець Олег - ІВ-81; " +
                "Роман Олександр - ІО-82; Дудка Максим - ІО-81; Кулініч Віталій - ІВ-81; " +
                "Жуков Михайло - ІП-83; Грабко Михайло - ІВ-81; Іванов Володимир - ІО-81; " +
                "Востриков Нікіта - ІО-82; Бондаренко Максим - ІВ-83; Скрипченко Володимир - ІВ-82; " +
                "Кобук Назар - ІО-81; Дровнін Павло - ІВ-83; Тарасенко Юлія - ІО-82; " +
                "Дрозд Світлана - ІВ-81; Фещенко Кирил - ІО-82; Крамар Віктор - ІО-83; Іванов Дмитро - ІВ-82";

        HashMap<String, ArrayList> studentDict = new HashMap<>();

        String[] studentsList = studentsStr.split("; ");
        String[] ListKey_and_Value;

        for (int i = 0; i < studentsList.length; i++) {
            ListKey_and_Value = studentsList[i].split(" - ");
            ArrayList<String> listValue = new ArrayList<String>();
            if (studentDict.containsKey(ListKey_and_Value[1])) {
                listValue = studentDict.get(ListKey_and_Value[1]);
                listValue.add(ListKey_and_Value[0]);
            } else {
                listValue.add(ListKey_and_Value[0]);
                studentDict.put(ListKey_and_Value[1], listValue);
            }
        }
        System.out.println("Завдання 1");
        System.out.println(studentDict.toString());

        //Завдання 2--------------------------------
        int min = 1;
        int max = 20;
        Random random = new Random();

        HashMap<String, HashMap> studentDict2 = new HashMap<>();

        String[] studentsList2 = studentsStr.split("; ");
        String[] ListKey_and_Value2;


        for (int i = 0; i < studentsList2.length; i++) {
            ListKey_and_Value2 = studentsList2[i].split(" - ");
            HashMap<String, ArrayList> listValue2 = new HashMap<>();
            ArrayList<Integer> listNumber = new ArrayList<Integer>();
            listNumber.add(random.nextInt(max - min + 1) + min);
            listNumber.add(random.nextInt(max - min + 1) + min);
            listNumber.add(random.nextInt(max - min + 1) + min);
            listNumber.add(random.nextInt(max - min + 1) + min);
            listNumber.add(random.nextInt(max - min + 1) + min);
            if (studentDict2.containsKey(ListKey_and_Value2[1])) {
                HashMap<String, ArrayList> stud = new HashMap<>();
                listValue2 = studentDict2.get(ListKey_and_Value2[1]);
                listValue2.put(ListKey_and_Value2[0], listNumber);
            } else {
                HashMap<String, ArrayList> stud = new HashMap<>();
                stud.put(ListKey_and_Value2[0], listNumber);
                studentDict2.put(ListKey_and_Value2[1], stud);
            }
        }
        System.out.println("Завдання 2");
        System.out.println(studentDict2.toString());

        //Завдання 3--------------------------------

        HashMap<String, HashMap> studentDict3 = new HashMap<>();

        String[] studentsList3 = studentsStr.split("; ");
        String[] ListKey_and_Value3;


        for (int i = 0; i < studentsList3.length; i++) {
            ListKey_and_Value3 = studentsList3[i].split(" - ");
            HashMap<String, Integer> listValue3 = new HashMap<>();
            HashMap<String, ArrayList> HashNumber3 = new HashMap<>();
            HashNumber3 = studentDict2.get(ListKey_and_Value3[1]);
            ArrayList<Integer> sums = HashNumber3.get(ListKey_and_Value3[0]);
            int sum = sums.get(0) + sums.get(1) + sums.get(2) + sums.get(3) + sums.get(4);
            if (studentDict3.containsKey(ListKey_and_Value3[1])) {
                HashMap<String, Integer> stud = new HashMap<>();
                listValue3 = studentDict3.get(ListKey_and_Value3[1]);
                listValue3.put(ListKey_and_Value3[0], sum);
            } else {
                HashMap<String, Integer> stud = new HashMap<>();
                stud.put(ListKey_and_Value3[0], sum);
                studentDict3.put(ListKey_and_Value3[1], stud);
            }
        }
        System.out.println("Завдання 3");
        System.out.println(studentDict3.toString());

        //Завдання 4--------------------------------

        HashMap<String, Double> studentDict4 = new HashMap<>();

        Double srznach = 0.0;
        HashMap<String, Integer> temp = new HashMap<>();
        for (String i : studentDict3.keySet()) {
            temp = studentDict3.get(i);
            for (Integer j : temp.values()) {
                srznach += j;
            }
            srznach /= temp.size();
            studentDict4.put(i, srznach);
            srznach = 0.0;
        }
        System.out.println("Завдання 4");
        System.out.println(studentDict4.toString());


        //Завдання 5--------------------------------

        HashMap<String, ArrayList> studentDict5 = new HashMap<>();
        HashMap<String, Integer> temp5 = new HashMap<>();
        for (String i : studentDict3.keySet()) {
            ArrayList<String> goodStudents = new ArrayList<String>();
            temp5 = studentDict3.get(i);
            for (String j : temp5.keySet()) {
                if (temp5.get(j) >= 60) {
                    goodStudents.add(j);
                }
            }
            studentDict5.put(i, goodStudents);
        }
        System.out.println("Завдання 5");
        System.out.println(studentDict5.toString());

        //Завдання 6-12--------------------------------
        System.out.println("Приклад Ініціалізаціх з заданими параметрами (15-16)");
        Lab1_2_CoordinateZH a = new Lab1_2_CoordinateZH(-12,30,40,false);
        Lab1_2_CoordinateZH false_test1 = new Lab1_2_CoordinateZH(90, 30, 40, true);
        System.out.println(false_test1);
        Lab1_2_CoordinateZH false_test2 = new Lab1_2_CoordinateZH(180, 30, 40, false);
        System.out.println(false_test2);
        System.out.println(a);
        System.out.println("Приклад Ініціалізаціх без заданих параметрів (15-16)");
        Lab1_2_CoordinateZH b = new Lab1_2_CoordinateZH();
        System.out.println(b);
        System.out.println("Координати в звичайному вигляді (16)");
        System.out.println(a.outCoordinate());
        System.out.println("Координати в десятичному вигляді (16)");
        System.out.println(a.outCoordinate2());
        System.out.println("Середнє між координатами (16)");
        System.out.println(a.outCoordinate3(12, 30, 40, false));
        System.out.println("Середнє між довільними (17)");
        System.out.println(Lab1_2_CoordinateZH.outCoordinate4(12, 30, 40, false, -12, 30, 40, false));
    }
}