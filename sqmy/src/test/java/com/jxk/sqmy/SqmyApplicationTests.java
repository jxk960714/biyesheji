package com.jxk.sqmy;

import com.jxk.sqmy.dao.AdminDao;
import com.jxk.sqmy.dao.JobDao;
import com.jxk.sqmy.entity.*;
import com.jxk.sqmy.service.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

@SpringBootTest
class SqmyApplicationTests {
    @Autowired
    private AdminService adminService;
    @Autowired
    private SqmyTiJiaoService service;
    @Autowired
    private SqmyService sqmyService;
    @Autowired
    private UserService userService;
    @Autowired
    private JobDao jobDao;
    @Autowired
            private AdminDao adminDao;
    @Autowired
            private  FabuService fabuService;

   // @Test
    void contextLoads() {
       /* Fabu fabu1=new Fabu();
        fabu1.setSqmyTitle("dasd");*/
       Sqmy sqmy=new Sqmy();
        System.out.println(adminService.queryAllSqmyList(sqmy,0,4).get(0).getIsFaBu());

    }
    @Test
    void  testdenglu(){
        User user=new User();
        user.setName("jxk22");
        user.setPassword("12322");
        Role role =new Role();
        role.setRoleId(1);
        user.setRole(role);
        System.out.println(userService.insert(user));
       /* Sqmy sqmy =new Sqmy();
        sqmy.setUser(user);
        System.out.println(sqmyService.querySqmyListByUserId(sqmy,0,8));*/

        //System.out.println(userService.queryUser(user).getUserid());
    }
    @Test
    public  void  testsqueryjob(){
        System.out.println(fabuService.getFabu(2,10).size());

        /*List<Job> jobList=jobDao.queryByTypeId(1);
        Iterator<Job> iterable=jobList.iterator();
        while (iterable.hasNext()){
            System.out.println(iterable.next().getJobname());
        }*/
       /* User user=new User();
        user.setUserid(22);
        System.out.println(adminDao.querySqmyByUserandStatus(user));*/
       /* List<User> userList=adminService.queryalluser();
        Iterator<User> iterator=userList.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next().getUserid());


        }*/


     /*  List<Integer> countList=adminService.getSqmyCountByUserAndAtatus();
        Iterator<Integer> iterator =countList.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }*/
    }
}
