package com.xiaoxin.projectinterface.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xiaoxin.projectinterface.common.ResultVO;
import com.xiaoxin.projectinterface.entity.Student;
import com.xiaoxin.projectinterface.entity.Teacher;
import com.xiaoxin.projectinterface.mapper.StudentMapper;
import com.xiaoxin.projectinterface.mapper.TeacherMapper;
import com.xiaoxin.projectinterface.service.AccountService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xiaoxin.projectinterface.utils.Utils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 14290
 * @since 2022-01-26
 */
@Service
public class AccountServiceImpl extends ServiceImpl<TeacherMapper, Teacher> implements AccountService {

    @Resource
    TeacherMapper teacherMapper;

    @Resource
    StudentMapper studentMapper;

    /**
     * 根据type指定的用户角色查询数据库，进行登录
     *
     * @param type     用户角色
     * @param account  用户账号
     * @param password 用户密码
     * @return ResultVO
     */
    @Override
    public ResultVO<?> accountLogin(Integer type, String account, String password) {
        switch (type) {
            case 1:
                QueryWrapper<Teacher> teacherQuery = new QueryWrapper<Teacher>().eq("teacher_account", account).eq("teacher_password", password);
                Teacher teacher = teacherMapper.selectOne(teacherQuery);
                if (!Utils.isEmpty(teacher)) {
                    return ResultVO.successResponse(teacher);
                }
                break;
            case 2:
                QueryWrapper<Student> studentQuery = new QueryWrapper<Student>().eq("student_account", account).eq("student_password", password);
                Student student = studentMapper.selectOne(studentQuery);
                if (!Utils.isEmpty(student)) {
                    return ResultVO.successResponse(student);
                }
                break;
            default:
                return ResultVO.paramErrorResponse("角色类型错误");
        }
        return ResultVO.userLoginErrorResponse("账号或密码错误");
    }

    @Override
    public boolean registerTeacher(Teacher teacher) {
        QueryWrapper<Teacher> teacherQuery = new QueryWrapper<Teacher>().eq("teacher_account", teacher.getTeacherAccount());
        Teacher t = teacherMapper.selectOne(teacherQuery);
        if (Utils.isEmpty(t)) {
            return teacherMapper.insert(teacher) == 1;
        }
        return false;
    }

    @Override
    public boolean registerStudent(Student student) {
        QueryWrapper<Student> studentQuery = new QueryWrapper<Student>().eq("student_account", student.getStudentAccount());
        Student s = studentMapper.selectOne(studentQuery);
        if (Utils.isEmpty(s)) {
            return studentMapper.insert(student) == 1;
        }
        return false;
    }

    @Override
    public boolean delete(Integer type, Integer id) {
        switch (type){
            case 1:
                return teacherMapper.deleteById(id) == 1;
            case 2:
                return studentMapper.deleteById(id) == 1;
            default:
                return false;
        }
    }

    @Override
    public List<Teacher> findAllTeacher() {
        return teacherMapper.selectList(null);
    }

    @Override
    public List<Student> findAllStudent() {
        return studentMapper.selectList(null);
    }

    @Override
    public List<Teacher> findTeacher(String column, Object value) {
        return teacherMapper.selectList(new QueryWrapper<Teacher>().like(column, value));
    }

    @Override
    public List<Student> findStudent(String column, Object value) {
        return studentMapper.selectList(new QueryWrapper<Student>().like(column, value));
    }

    @Override
    public ResultVO<String> confirmAccount(Integer type, String account) {
        Integer count;
        if (type == 1) {
            count = teacherMapper.selectCount(new QueryWrapper<Teacher>().eq("teacher_account", account));
        } else {
            count = studentMapper.selectCount(new QueryWrapper<Student>().eq("student_account", account));
        }
        if (count == 0) {
            return ResultVO.dataEmptyResponse(String.valueOf(count));
        } else {
            return ResultVO.successResponse(String.valueOf(count));
        }
    }

    @Override
    public ResultVO<String> confirmAccount(Integer type, String account, String phone) {
        List<Teacher> accountTeachers = new ArrayList<>();
        List<Student> accountStudents = new ArrayList<>();
        if (type == 1) {
            accountTeachers = teacherMapper.selectList(new QueryWrapper<Teacher>().eq("teacher_account", account).eq("teacher_phone", phone));
        } else {
            accountStudents = studentMapper.selectList(new QueryWrapper<Student>().eq("student_account", account).eq("student_phone", phone));
        }
        if (accountStudents.size() + accountTeachers.size() == 0) {
            return ResultVO.dataEmptyResponse("0");
        } else if (accountStudents.size() != 0) {
            return ResultVO.successResponse(String.valueOf(accountStudents.get(0).getStudentId()));
        } else {
            return ResultVO.successResponse(String.valueOf(accountTeachers.get(0).getTeacherId()));
        }
    }
}


