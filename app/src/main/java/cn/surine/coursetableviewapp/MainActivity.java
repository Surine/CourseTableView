package cn.surine.coursetableviewapp;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import cn.surine.coursetableview.Pojo.BCourse;
import cn.surine.coursetableview.View.CourseTableView;
import cn.surine.coursetableview.View.DataConfig;
import cn.surine.coursetableview.View.UIConfig;

public class MainActivity extends AppCompatActivity {
    private List<BCourse> data = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        CourseTableView ctv = (CourseTableView) findViewById(R.id.ctv);
        initData();
        DataConfig dataConfig = new DataConfig();
        dataConfig.setCurTermStartDate("2019-2-25");
        dataConfig.setCourseList(data);
        dataConfig.setCurrentWeek(ctv.getRealWeekByCurTermStartDate("2019-2-25"));
        ctv.update(dataConfig);
    }

    private void initData() {
        BCourse bCourse = new BCourse();
        List<Integer> weeks = new ArrayList<>();
        weeks.add(1);
        weeks.add(2);
        bCourse.setWeek(weeks);
        bCourse.setColor(Color.RED);
        bCourse.setDay(2);
        bCourse.setSectionStart(1);
        bCourse.setSectionContinue(2);
        bCourse.setName("Android");
        data.add(bCourse);
    }
}
