package ru.newprotech.invaders;

import android.graphics.Canvas;
import android.graphics.RectF;

import java.util.Vector;

/**
 * Created by 6003 on 15.01.2015.
 */
public class CMenu {


    RectF rect;
    int maxItems;
    private Vector<CMenuItem> menuItems;
    private static CMenu ourInstance = new CMenu();

    public static CMenu getInstance() {
        return ourInstance;
    }

    public synchronized void draw(Canvas canvas){
        for (int i = 0; i < menuItems.size(); i++) {
            CMenuItem menuItem =  menuItems.elementAt(i);
            menuItem.draw(canvas);
        }
    }

    public synchronized int touch(float x, float y){
        for (int i = 0; i < menuItems.size(); i++) {
            CMenuItem menuItem =  menuItems.elementAt(i);
            if (menuItem.check(x, y))
                return menuItem.getNextState();
        }
        return 0;
    }

    private synchronized void add(CMenuItem menuItem){
        menuItems.add(menuItem);
    }

    private synchronized float height(){
        return rect.height()/maxItems;
    }

    private synchronized int numItems(){
        return menuItems.size();
    }

    public static CMenuItem createItem(int res, int what){
        RectF rect = new RectF(ourInstance.rect.left,0,
                ourInstance.rect.right,
                ourInstance.height());
        rect.offset(0,ourInstance.rect.top+ourInstance.numItems()*ourInstance.height());
        CMenuItem menuItem = new CMenuItem(rect,res,what);
        ourInstance.add(menuItem);
        return menuItem;
    }

    public static void init(RectF rect, int maxItems){
        ourInstance.rect = rect;
        ourInstance.maxItems = maxItems;
        ourInstance.menuItems = new Vector<>();
    }

    private CMenu() {
    }
}
