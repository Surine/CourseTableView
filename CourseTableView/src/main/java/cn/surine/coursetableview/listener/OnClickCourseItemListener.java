package cn.surine.coursetableview.listener;

import java.util.List;

import cn.surine.coursetableview.Pojo.BCourse;

/**
 * Created by Surine on 2019/2/26.
 */

public interface OnClickCourseItemListener {
    void onClickItem(List<BCourse> list, int itemPosition,boolean isThisWeek);
}
