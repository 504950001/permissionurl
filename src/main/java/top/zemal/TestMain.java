package top.zemal;

import org.springframework.boot.SpringApplication;
import top.zemal.model.Menu;
import top.zemal.service.BaseService;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author zemal-tan
 * @description
 * @create 2017-07-27 11:49
 */
public class TestMain {
    public static void main(String[] args) {

        Menu menuA = initMenu();
        Set<String> result = new HashSet<>();
        BaseService baseService = new BaseService();
        result = baseService.getMenuUrls(menuA, result);
        for (String s: result){
            System.out.println(s);
        }
    }

    public static Menu initMenu(){
        Menu menuA = new Menu();
        Menu menuB = new Menu();
        Menu menuC = new Menu();
        Menu menuD = new Menu();
        Menu menuE = new Menu();
        Menu menuF = new Menu();
        menuD.setId((long)4);menuD.setUrl("D");
        menuE.setId((long)5);menuE.setUrl("E");
        menuF.setId((long)6);menuF.setUrl("F");
        menuC.setId((long)3);menuC.setUrl("C");
        List<Menu> clist = new ArrayList<>(); clist.add(menuD);clist.add(menuE);clist.add(menuF);
        menuC.setChildren(clist);
        menuB.setId((long)2);menuB.setUrl("B");
        List<Menu> alist = new ArrayList<>(); alist.add(menuB);alist.add(menuC);
        menuA.setId((long)1);menuA.setUrl("A");
        menuA.setChildren(alist);
        return menuA;
    }
}
