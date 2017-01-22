package hibernate;

import hibernate.Student;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class StudentDriver {

    public static void main(String[] args) throws Exception {

    }

    private static Iterator<Integer> f(){
        ArrayList<Integer> arrayList=new ArrayList<Integer>();
        arrayList.add(0);
        arrayList.add(1);
        arrayList.add(2);
        return arrayList.iterator();
    }
}
