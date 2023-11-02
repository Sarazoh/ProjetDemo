package com.example.demo;

import com.example.demo.model.Calcul;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CaluculTest {
    private Calcul calcul;

    @Test
    public void testAdditionner() {
        // GIVEN
        calcul = new Calcul(2.0f, 4.0f);

        // WHEN
        Float resultat = calcul.additionner(2.0f, 4.0f);

        // THEN
        assertEquals(resultat, 6.0F);

    }

    @Test
    public void testSoustration() {
        // GIVEN
        calcul = new Calcul(2.0f, 4.0f);

        // WHEN
        Float resultat = calcul.soustraire(2.0f, 4.0f);

        // THEN
        assertEquals(resultat, -2.0F);

    }

    @Test
    public void tesMutiplication() {
        // GIVEN
        calcul = new Calcul(2.0f, 4.0f);

        // WHEN
        Float resultat = calcul.multiplier(2.0f, 4.0f);

        // THEN
        assertEquals(resultat, 8.0F);

    }

    @Test
    public void testDiviser() throws Exception {

            // GIVEN
            calcul = new Calcul(4.0f, 2.0f);

            // WHEN
            Float resultat = calcul.deviser(4.0f, 2.0f);

            // THEN
            assertEquals(resultat, 2.0F);
    }

    @Test
    public void testDiviserAvecBEgaleZero() throws Exception {

        // GIVEN
        calcul = new Calcul(4.0f, 0);

        // WHEN
        Exception exception = assertThrows(Exception.class, () -> calcul.deviser(4.0f, 0));

        // THEN
    }

    @Test
    public void testCarre()  {
        // GIVEN
        calcul = new Calcul(2.0f, 2.0f);

        // WHEN
        Float resultat = calcul.carre(2.0f);

        // THEN
        assertEquals(resultat, 4.0F);
    }


    @Test
    public void testestIndentiteRemarquable()  {
        // GIVEN
        calcul = new Calcul(2.0f, 2.0f);

        // WHEN
        Float resultat = calcul.identiteRemarquable(2.0f, 2.0f);

        // THEN
        assertEquals(resultat, 16.0F);
    }









}

