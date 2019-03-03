package cn.surine.coursetableview.View;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import cn.surine.coursetableview.Pojo.BCourse;
import cn.surine.coursetableview.R;
import cn.surine.coursetableview.Utils.ScreenUtil;
import cn.surine.coursetableview.Utils.TimeUtil;
import cn.surine.coursetableview.listener.OnClickCourseItemListener;

import static cn.surine.coursetableview.Utils.TimeUtil.getWeekDay;

/**
 * Created by Surine on 2019/2/25.
 * 课程表视图
 */

public class CourseTableView extends LinearLayout {

    int w = View.MeasureSpec.makeMeasureSpec(0,
            View.MeasureSpec.UNSPECIFIED);
    int h = View.MeasureSpec.makeMeasureSpec(0,
            View.MeasureSpec.UNSPECIFIED);

    /*UI 配置器*/
    private UIConfig mUiConfig = new UIConfig();
    /*数据集 配置器*/
    private DataConfig mDataConfig = new DataConfig();
    /*点击监听器*/
    private OnClickCourseItemListener mClickCourseItemListener;
    /*周view视图*/
    private LinearLayout mWeekView;
    /*节次view视图*/
    private LinearLayout mSectionView;
    /*Week Panel 视图*/
    private RelativeLayout weekPanel1;
    private RelativeLayout weekPanel2;
    private RelativeLayout weekPanel3;
    private RelativeLayout weekPanel4;
    private RelativeLayout weekPanel5;
    private RelativeLayout weekPanel6;
    private RelativeLayout weekPanel7;
    private List<RelativeLayout> layoutList = new ArrayList<>();
    /*上下文*/
    private Context mContext;
    /*侧边栏是否加载*/
    private boolean isSectionViewInit = false;
    /*最大周临时变量*/
    private int myMaxWeekNum = 0;

    public CourseTableView(Context context) {
        super(context);
        init(context);
    }

    public CourseTableView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public CourseTableView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public CourseTableView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }


    /**
     * 更新UI及数据集
     * @param uiConfig UI配置
     * @param dataConfig
     * @return this
     * */
    public CourseTableView update(UIConfig uiConfig, DataConfig dataConfig){
        mUiConfig = uiConfig;
        mDataConfig = dataConfig;

        updateWeekView();  //加载周视图
        updateSectionView(); //加载侧边栏
        updateData();  //加载数据

        return this;
    }

    /**
     * 更新数据集
     * @param dataConfig 数据集
     * @return this
     * */
    public CourseTableView update(DataConfig dataConfig){
        mDataConfig = dataConfig;
        //数据加载
        //如果侧边栏未加载则进行加载
        if(isSectionViewInit)
            updateSectionView();

        //顶部栏要跟着更新时间数据
        updateWeekView();
        updateData();

        return this;
    }

    /**
     * 更新UI
     * @param uiConfig UI配置
     * @return this
     * */
    public CourseTableView update(UIConfig uiConfig){
        return update(uiConfig,mDataConfig);
    }

    /**
     * 获取UI配置
     * @return UIConfig
     * */
    public UIConfig getUiConfig() {
        return mUiConfig;
    }

    /**
     * 获取课程数据配置
     * @return DataConfig
     * */
    public DataConfig getDataConfig() {
        return mDataConfig;
    }


    /**
     * 根据当前设置的开学日期算出现在是第几周
     * @return 返回当前周，负数代表当前在开学日期过去，正数在之前
     * */
    public int getRealWeekByCurTermStartDate(){
        try {
            return TimeUtil.getRealWeek(mDataConfig.getCurTermStartDate());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * 重载
     * */
    public int getRealWeekByCurTermStartDate(String date){
        try {
            return TimeUtil.getRealWeek(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }


    /**
     * 配置监听器
     * @param mClickCourseItemListener 监听器
     * */
    public void setmClickCourseItemListener(OnClickCourseItemListener mClickCourseItemListener) {
        this.mClickCourseItemListener = mClickCourseItemListener;
    }



    /*初始化周视图*/
    private void initWeekView(int maxClassDay){
        //清空
        mWeekView.removeViews(0,mWeekView.getChildCount());
        for (int i = 0; i <= maxClassDay ; i++) {
            TextView tvWeekName = new TextView(mContext);

            if(i == 0){  //添加月份
                LinearLayout.LayoutParams lp0 = new LinearLayout.LayoutParams(mUiConfig.getSectionViewWidth(),ViewGroup.LayoutParams.WRAP_CONTENT);
                lp0.gravity = Gravity.CENTER;
                tvWeekName.setText(TimeUtil.getWeekInfoString(mDataConfig.getCurTermStartDate(),mDataConfig.getCurrentWeek())+"\n月");
                tvWeekName.setTextColor(mUiConfig.getColorUI());
                tvWeekName.setTextSize(12);
                tvWeekName.setGravity(Gravity.CENTER);
                tvWeekName.setLayoutParams(lp0);
            }else{
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
                lp.gravity = Gravity.CENTER;
                lp.weight = 1f;
                String today = TimeUtil.getTodayInfoString(mDataConfig.getCurTermStartDate(),mDataConfig.getCurrentWeek(),i);
                tvWeekName.setText(mUiConfig.getWeekInfoStyle()[i - 1] + "\n"+ today);
                int color = (i==getWeekDay()) ? mUiConfig.getChooseWeekColor() : mUiConfig.getColorUI();
                tvWeekName.setTextColor(color);
                tvWeekName.setGravity(Gravity.CENTER);
                tvWeekName.setLayoutParams(lp);
            }
            mWeekView.addView(tvWeekName);
        }

    }
    /*初始化课节次布局*/
    private void initSectionView(int maxSection){

        LinearLayout.LayoutParams lpx = new LinearLayout.LayoutParams(mUiConfig.getSectionViewWidth(), ViewGroup.LayoutParams.WRAP_CONTENT);
        mSectionView.setLayoutParams(lpx);

        mSectionView.removeViews(0,mSectionView.getChildCount());
        for (int i = 1; i <= maxSection; i++) {
            TextView tvSection = new TextView(mContext);
            tvSection.setTextColor(mUiConfig.getColorUI());
            //侧边栏单项高度 = 小课课高度 + padding
            int height = mUiConfig.getSectionHeight() + mUiConfig.getItemTopMargin();
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,height);
            lp.gravity = Gravity.CENTER;
            tvSection.setGravity(Gravity.CENTER);
            tvSection.setText(String.valueOf(i));
            tvSection.setLayoutParams(lp);
            mSectionView.addView(tvSection);
        }
    }


    /*加载课程项目信息  */
    @SuppressLint("SetTextI18n")
    private void initCourseItemView(){

        //清除布局
        for (int j = 0; j < 7; j++) {
            layoutList.get(j).removeAllViews();
        }

        int itemPosition;
        List<BCourse> mBcourses = mDataConfig.getCourseList();
        int courseSize = mBcourses.size();
        if(courseSize <= 0){
            return;
        }

//        //如果没修改过最大周了，就允许再修改。
//        if(myMaxWeekNum == 0){
//            myMaxWeekNum = mDataConfig.getMaxWeek();
//        }

//        //如果已经修改过最大周，并且又传入了新的最大周数据，就不允许再修改了
//        if(myMaxWeekNum != 0 && myMaxWeekNum != mDataConfig.getMaxWeek()){
//            throw new IllegalArgumentException("you can not modify ‘MaxWeek’ more than once！");
//        }

        //再次判断当前周不合法
        if(mDataConfig.getCurrentWeek() > mDataConfig.getMaxWeek()){
            throw new  IllegalArgumentException("currentWeek is greater than maxweek");
        }

        for (int i = 0; i < courseSize; i++) {
            itemPosition = i;
            BCourse bCourse = mBcourses.get(i);
            boolean isCurWeek;
            //对象判空
            if(bCourse == null)
                return;

            RelativeLayout curDayLayout = layoutList.get(bCourse.getDay() - 1);

            //课程是否在本周山
            isCurWeek = bCourse.getWeek().contains(mDataConfig.getCurrentWeek());

            //判断课程位置的合法性
            if(bCourse.getSectionStart() + bCourse.getSectionContinue() > mUiConfig.getMaxSection()){
                throw new IllegalArgumentException("out of the section view limit");
            }

            CornerView frameLayout = new CornerView(mContext, mUiConfig.getItemCornerRadius());

            TextView tv = new TextView(mContext);

            //课程高度 = 课持续节数 * 小课课高度 + 课内间隔数 * padding
            int bCourseHeight = bCourse.getSectionContinue() * mUiConfig.getSectionHeight()
                    + (bCourse.getSectionContinue() - 1) * mUiConfig.getItemTopMargin();


            LinearLayout.LayoutParams flp = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    bCourseHeight);

            LinearLayout.LayoutParams tlp = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.MATCH_PARENT);

            //课表间距
            int topMargin = (bCourse.getSectionStart() - 1) * mUiConfig.getSectionHeight()
                    + bCourse.getSectionStart() * mUiConfig.getItemTopMargin();
            flp.setMargins(0, topMargin, 0, 0);

            //是否本周上
            if(isCurWeek){
                frameLayout.setBackgroundColor(bCourse.getColor());
                tv.setTextColor(Color.WHITE);
                tv.setText(bCourse.getName() + "\n @" + bCourse.getPosition());
            }
            else{
                frameLayout.setBackgroundColor(mUiConfig.getItemNotCurWeekCourseColor());
                tv.setTextColor(Color.BLACK);
                tv.setText(bCourse.getName() + "\n @" + bCourse.getPosition()+"\n[非本周]");
            }

            tv.setLayoutParams(tlp);
            tv.setGravity(Gravity.CENTER);
            tv.setTextSize(mUiConfig.getItemTextSize());

            frameLayout.setLayoutParams(flp);
            frameLayout.addView(tv);

            frameLayout.setPadding(2, 2, 2, 2);
            curDayLayout.setPadding(mUiConfig.getItemSideMargin(),0,mUiConfig.getItemSideMargin(),0);

            //显示非本周上的课
            if(mUiConfig.isShowCurWeekCourse()){
                curDayLayout.addView(frameLayout);
            }else{
                //不显示非本周上的课
                //如果这节课是在本周上，就加上，不是在本周上，就不加
                if(isCurWeek){
                    curDayLayout.addView(frameLayout);
                }
            }

            final int finalItemPostion = itemPosition;
            final boolean tempIsCurWeek = isCurWeek;
            //单击监听
            frameLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mClickCourseItemListener.onClickItem(mDataConfig.getCourseList(), finalItemPostion,tempIsCurWeek);
                }
            });

            //课表显示与隐藏
            if(mUiConfig.getMaxClassDay() == 5){
                layoutList.get(5).setVisibility(GONE);
                layoutList.get(6).setVisibility(GONE);
            }else{
                layoutList.get(5).setVisibility(VISIBLE);
                layoutList.get(6).setVisibility(VISIBLE);
            }
        }
    }

    /*初始化*/
    private void init(Context context) {
        mContext = context;
        LayoutInflater.from(context).inflate(R.layout.main,this);
        mWeekView = findViewById(R.id.weekView);
        mSectionView = findViewById(R.id.sectionView);
        weekPanel1 = findViewById(R.id.weekPanel_1);
        weekPanel2 = findViewById(R.id.weekPanel_2);
        weekPanel3 = findViewById(R.id.weekPanel_3);
        weekPanel4 = findViewById(R.id.weekPanel_4);
        weekPanel5 = findViewById(R.id.weekPanel_5);
        weekPanel6 = findViewById(R.id.weekPanel_6);
        weekPanel7 = findViewById(R.id.weekPanel_7);
        if(mWeekView == null || mSectionView == null
                || weekPanel1 == null || weekPanel2 == null
                || weekPanel3 == null || weekPanel4 == null
                || weekPanel5 == null || weekPanel6 == null
                || weekPanel7 == null
                ){
            throw new NullPointerException("childView is null");
        }
        layoutList.add(weekPanel1);
        layoutList.add(weekPanel2);
        layoutList.add(weekPanel3);
        layoutList.add(weekPanel4);
        layoutList.add(weekPanel5);
        layoutList.add(weekPanel6);
        layoutList.add(weekPanel7);
    }


    //加载侧边栏
    private void updateSectionView() {
        isSectionViewInit = false;
        //初始化上课节数视图
        initSectionView(mUiConfig.getMaxSection());
        isSectionViewInit = true;
    }

    //加载顶部栏
    private void updateWeekView() {
        //初始化课表上课日
        initWeekView(mUiConfig.getMaxClassDay());
    }

    /*更新数据*/
    private void updateData(){
        initCourseItemView();
    }

}
