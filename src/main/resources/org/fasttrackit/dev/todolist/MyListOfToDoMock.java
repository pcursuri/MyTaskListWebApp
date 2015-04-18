package org.fasttrackit.dev.todolist;



import java.sql.SQLException;
import java.util.*;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import org.fasttrackit.dev.todolist.ListCRUDOperations;

import javax.xml.bind.SchemaOutputResolver;

/**
 * Created by condor on April 04, 2015
 * FastTrackIT, 2015
 */
public class MyListOfToDoMock {

    private static MyListOfToDoMock m;

    public static MyListOfToDoMock getInstance() throws SQLException, ClassNotFoundException {
        System.out.println("get instance...");
        System.out.println("m=null "+ m==null);
       // if(m==null) {
            m=new MyListOfToDoMock();
            m.generateInitialList();
        //}
       return m;
    }


    private List<ToDoBean> toDoList = new ArrayList<ToDoBean>();
    private int id;

    private void generateInitialList() throws SQLException, ClassNotFoundException {
        System.out.println("Initializing...");
        ListCRUDOperations list = new ListCRUDOperations();
        String[] s = new String[20];
        s = list.demoRead();
        System.out.println("s is null = "+s==null);
        int i = 0;
        while (s[i]!=null){
            System.out.println("item to be loaded: "+s[i]);
            toDoList.add(new ToDoBean(i, s[i]));
            i++;
        }
        id=i;
    };

   public void addItem(String value) throws SQLException, ClassNotFoundException {
      id++;
       toDoList.add(new ToDoBean(id,value));
       ListCRUDOperations list = new ListCRUDOperations();
       list.demoCreate(value);
        }

    public void doneItem(int index) {
        for (ListIterator<ToDoBean> iter = toDoList.listIterator(); iter.hasNext(); ) {
            ToDoBean element = iter.next();
            if (element.getId()==index) {
                element.setDone(true);
                iter.set(element);
            }
        }
    }


    public void printList() {
        for (ListIterator<ToDoBean> iter = toDoList.listIterator(); iter.hasNext(); ) {
            ToDoBean element = iter.next();
            if (!element.isDone()) {
                System.out.print(element.getId() + ":");
                System.out.println(element.getWhatToDo());
            }

        }
    }

    public List getList() {
        return toDoList;
    }

}