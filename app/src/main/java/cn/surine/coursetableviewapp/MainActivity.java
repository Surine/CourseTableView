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
        dataConfig.setCurTermStartDate("2019-8-26");
        dataConfig.setCourseList(data);
        dataConfig.setCurrentWeek(ctv.getRealWeekByCurTermStartDate("2019-8-26"));
        ctv.update(dataConfig);
    }

    private void initData() {
        BCourse bCourse = new BCourse();
        List<Integer> weeks = new ArrayList<>();
        weeks.add(1);
        weeks.add(2);
        bCourse.setWeek(weeks);
        bCourse.setColor(Color.CYAN);
        bCourse.setDay(2);
        bCourse.setSectionStart(1);
        bCourse.setSectionContinue(2);
        bCourse.setName("Android");
        data.add(bCourse);

        BCourse bCourse2 = new BCourse();
        List<Integer> weeks2 = new ArrayList<>();
        weeks2.add(1);
        weeks2.add(2);
        bCourse2.setWeek(weeks2);
        bCourse2.setColor(Color.RED);
        bCourse2.setDay(1);
        bCourse2.setSectionStart(1);
        bCourse2.setSectionContinue(3);
        bCourse2.setName("C");
        data.add(bCourse2);

        BCourse bCourse3 = new BCourse();
        List<Integer> weeks3 = new ArrayList<>();
        weeks3.add(3);
        weeks3.add(4);
        bCourse3.setWeek(weeks3);
        bCourse3.setColor(Color.BLUE);
        bCourse3.setDay(3);
        bCourse3.setSectionStart(3);
        bCourse3.setSectionContinue(2);
        bCourse3.setName("E");
        data.add(bCourse3);

    }
}
