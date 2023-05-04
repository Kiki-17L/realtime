package com.example.realtimeapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener,Runnable {


    private LineChart lineChart;
    private LineData lineData;//线集合

    private LineDataSet dataSet;//第一通道

    private LineDataSet secdataSet;//第二通道

    private LineDataSet thirdDataSet;//第三通道


    private List<Entry> entries= new ArrayList<>();//点集合


    private Handler handler= new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);

        lineChart=findViewById(R.id.linechart);
        InitChart();//初始化图表
        InitDataSet();//初始化数据集







        //第二个通道通道
        secdataSet= getNewDataSet(2,Color.RED,Color.YELLOW);
        //第三个通道
        thirdDataSet =getNewDataSet(3,Color.GREEN,Color.BLACK);

        lineData= new LineData(dataSet,secdataSet,thirdDataSet);//添加数据集线集合
        lineChart.setData(lineData);//添加线到图表


        Switch line1= findViewById(R.id.switch3);
        Switch line2= findViewById(R.id.switch4);
        Switch line3= findViewById(R.id.switch5);


        line1.setChecked(true);//通道1默认开启
        line1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){

                    dataSet.setVisible(true);


                }
                else {

                    dataSet.setVisible(false);

                }
            }
        });

        line2.setChecked(true);//通道2默认开启
        line2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){

                    secdataSet.setVisible(true);


                }
                else {

                    secdataSet.setVisible(false);

                }
            }
        });


        line3.setChecked(true);//通道3默认开启
        line3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){

                    thirdDataSet.setVisible(true);


                }
                else {

                    thirdDataSet.setVisible(false);

                }
            }
        });




        handler.post(this);




    }




    public void InitChart(){

        Legend legend=lineChart.getLegend();

        legend.setTextColor(Color.BLACK);//设置图例文本颜色

        lineChart.setDrawBorders(false);  //绘制边框
//        lineChart.setBorderWidth(1);
//        lineChart.setBorderColor(Color.RED);//设置边框颜色为红色
        lineChart.setPinchZoom(false);//设置不可缩放
        lineChart.setDoubleTapToZoomEnabled(false);//设置双击不可缩放
        lineChart.setDragEnabled(true);//设置拖动

//        lineChart.setDrawGridBackground(true);//绘制网格背景
//        lineChart.setGridBackgroundColor(Color.BLUE);


        Description description=lineChart.getDescription();
        description.setEnabled(false);//禁用描述


        InitYAxis();
        InitXAxis();


    }

    public  void InitXAxis(){




        XAxis xAxis= lineChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);//将x轴移动到图表底部

        xAxis.setDrawGridLines(false);//关闭竖直网格线

        xAxis.setLabelRotationAngle(35);//设置x轴标签的去倾斜度
        xAxis.setGranularity(1);
        xAxis.setLabelCount(5,false);//设置标签数量，强制执行


        xAxis.setAxisMinimum(1);
        xAxis.setAxisMaximum(1);
    }

    public  void InitYAxis(){

        YAxis yright=lineChart.getAxisRight();//关闭右Y轴
        yright.setEnabled(false);

        YAxis yleft=lineChart.getAxisLeft();
        yleft.setDrawAxisLine(false);//关闭左轴线
        yleft.setDrawGridLines(false);//关闭水平网格线

        yleft.setAxisMaximum(45);
        yleft.setAxisMinimum(0);
        yleft.setGranularity(5);


    }

    public  void InitDataSet(){
        entries.add(new Entry(0,0));
        dataSet=new LineDataSet(entries,"通道1");

        dataSet.setColor(Color.BLUE);//设置折线颜色为蓝色
        dataSet.setLineWidth(1);//设置线的宽度

//        dataSet.setDrawFilled(true);//开启折线下部分填充颜色的绘制

        // 设置填充颜色，可以是纯色，也可以是渐变色
//        Drawable drawable = ContextCompat.getDrawable(this, R.drawable.fade_blue);
//        dataSet.setFillDrawable(drawable);

    }



    //生成随机数据点
    public Entry generateData(LineDataSet line ){


        float x;
        float value= (float) (20+Math.random()*20);

        if (line.getEntryCount()==0){
           x=0;
        }
        else {
            x = line.getEntryCount();
        }
        Entry entry= new Entry(x,value);

        return entry;
    }


    public void onClick(View v) {
//        int count= dataSet.getEntryCount();
//
//        if (count==1){
//
//            lineChart.getXAxis().resetAxisMaximum();
//            lineChart.getXAxis().resetAxisMinimum();
//        }
//
//        dataSet.addEntry(generateData());//随机生成一个数据点，加入数据集
//
//        dataSet.notifyDataSetChanged();//通知数据改变
//        lineData.notifyDataChanged();
//        lineChart.notifyDataSetChanged();
//
//        count= dataSet.getEntryCount();//获取数据长度
//
//        Toast.makeText(this,"添加成功,总数:"+count,Toast.LENGTH_SHORT).show();
//
//            if (count>5){
//                lineChart.setVisibleXRangeMaximum(5);
//                lineChart.moveViewToX(dataSet.getEntryCount() - 5);
//
//
//            }
//            else {
//
//                lineChart.invalidate();
//            }
    }




    //实时更新数据
    @Override
    public void run() {
        int count= dataSet.getEntryCount();

        if (count==1){

            lineChart.getXAxis().resetAxisMaximum();
            lineChart.getXAxis().resetAxisMinimum();
        }

        dataSet.addEntry(generateData(dataSet));//随机生成一个数据点，加入数据集
        secdataSet.addEntry(generateData(secdataSet));//随机生成一个数据点，加入数据集
        thirdDataSet.addEntry(generateData(thirdDataSet));//随机生成一个数据点，加入数据集

        dataSet.notifyDataSetChanged();//通知数据改变
        secdataSet.notifyDataSetChanged();//通知数据改变
        thirdDataSet.notifyDataSetChanged();//通知数据改变
        lineData.notifyDataChanged();
        lineChart.notifyDataSetChanged();

        count= dataSet.getEntryCount();//获取数据长度

//        Toast.makeText(this,"添加成功,总数:"+count,Toast.LENGTH_SHORT).show();

        if (count>5){
            lineChart.setVisibleXRangeMaximum(5);
            lineChart.moveViewToX(dataSet.getEntryCount() - 5);
            lineChart.moveViewToAnimated(dataSet.getEntryCount() - 5,0,YAxis.AxisDependency.RIGHT,500);


        }
        else {

            lineChart.invalidate();
        }

        handler.postDelayed(this,1000);
    }


    public LineDataSet getNewDataSet(int label,int LineColor,int CircleColor) {

        List<Entry> entries = new ArrayList<>();
        entries.add(new Entry(0,0));//初始化一个点

        LineDataSet newdataSet= new LineDataSet(entries,"通道"+label);//增加一条线

        newdataSet.setColor(LineColor);//设置线的颜色

        newdataSet.setLineWidth(1);//线的宽度为2

        newdataSet.setDrawCircleHole(true);//设置画空心圈

        newdataSet.setCircleColor(CircleColor);//设置圈的颜色









        return newdataSet;
    }
}