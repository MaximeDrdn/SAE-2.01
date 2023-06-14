package com.spaceraceinc.logicielchevalblancdemerde.test;

import com.spaceraceinc.logicielchevalblancdemerde.MainMenu;
import com.spaceraceinc.logicielchevalblancdemerde.modules.CustomerPrestation;

public class AddingTest {

    public static void createFicConsummations() {

    }

    public static void createFicPrestations() {
        MainMenu.CUSTOMER_PRESTATIONS_LIST.add(new CustomerPrestation(293, "Pressing", 100.0));
        MainMenu.CUSTOMER_PRESTATIONS_LIST.add(new CustomerPrestation(100, "Pressing", 110.0));
        MainMenu.CUSTOMER_PRESTATIONS_LIST.add(new CustomerPrestation(203, "Navette", 150.0));
    }

    public static void createFicBreakfast() {

    }

    public static void loadTestGame() {
        AddingTest.createFicPrestations();
        AddingTest.createFicBreakfast();
        AddingTest.createFicConsummations();
    }

}
