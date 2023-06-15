package com.spaceraceinc.logicielchevalblancdemerde.test;

import com.spaceraceinc.logicielchevalblancdemerde.MainMenu;
import com.spaceraceinc.logicielchevalblancdemerde.modules.Consummation;
import com.spaceraceinc.logicielchevalblancdemerde.modules.CustomerBreakfast;
import com.spaceraceinc.logicielchevalblancdemerde.modules.CustomerConsummation;
import com.spaceraceinc.logicielchevalblancdemerde.modules.CustomerPrestation;

import java.util.ArrayList;

public class AddingTest {

    public static void createFicConsummations() {
        Consummation c1 = new Consummation("Boisson gazeuse", "Soda", 2);
        Consummation c2 = new Consummation("Alcool", "Whisky", 3);
        Consummation c3 = new Consummation("Boisson vitale", "Eau", 1);
        ArrayList<Consummation> list = new ArrayList<>();
        ArrayList<Consummation> list2 = new ArrayList<>();
        list.add(c1);
        list.add(c2);
        list2.add(c3);
        MainMenu.CUSTOMER_CONSUMMATIONS_LIST.add(new CustomerConsummation(472, list));
        MainMenu.CUSTOMER_CONSUMMATIONS_LIST.add(new CustomerConsummation(472, list2));


    }

    public static void createFicPrestations() {
        CustomerPrestation pres1 = new CustomerPrestation(293, "Pressing", 100.0);
        pres1.setLabel("Test");
        MainMenu.CUSTOMER_PRESTATIONS_LIST.add(pres1);
        MainMenu.CUSTOMER_PRESTATIONS_LIST.add(new CustomerPrestation(203, "Navette", 150.0));
    }

    public static void createFicBreakfast() {
        MainMenu.CUSTOMER_BREAKFAST_LIST.add(new CustomerBreakfast(123, "Thé, pain et confiture", 3));
        MainMenu.CUSTOMER_BREAKFAST_LIST.add(new CustomerBreakfast(985, "Café, cracotte et beurre", 2));
        MainMenu.CUSTOMER_BREAKFAST_LIST.add(new CustomerBreakfast(842, "Jus d'orange, biscuit et fruits", 4));

    }

    public static void loadTestGame() {
        AddingTest.createFicPrestations();
        AddingTest.createFicBreakfast();
        AddingTest.createFicConsummations();
    }

}
