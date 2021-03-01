package com.lagou.service;

import com.lagou.domain.Course;
import com.lagou.domain.CourseVO;
import com.lagou.domain.Teacher;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

public interface CourseService {

    /*
        多条件课程查询
    */
    public List<Course> findCourseByCondition(CourseVO courseVO);

    /*
        新增课程（老师）信息
    */
    public void saveCourseOrTeacher(CourseVO courseVO) throws InvocationTargetException, IllegalAccessException;

    /*
        更新课程（老师）信息
    */
    public void updateCourseOrTeacher(CourseVO courseVO) throws InvocationTargetException, IllegalAccessException;

    /*
    根据课程id查询课程信息及讲师信息
 */
    public CourseVO findCourseById(Integer id);

    /*
    更新课程状态
 */
    public void updateCourseStatus(Integer id,  int status);
}
