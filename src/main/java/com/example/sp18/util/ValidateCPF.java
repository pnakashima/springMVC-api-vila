package com.example.sp18.util;

import ch.qos.logback.core.util.InvocationGate;

import java.util.InputMismatchException;

public class ValidateCPF {

    public static boolean isCPF(String CPF) {

        // elimina CPFs com caracteres iguais ou com menos de 11 dígitos
        if (CPF.equals("00000000000") || CPF.equals("11111111111") || CPF.equals("22222222222") ||
                CPF.equals("33333333333") || CPF.equals("44444444444") || CPF.equals("55555555555") ||
                CPF.equals("66666666666") || CPF.equals("77777777777") || CPF.equals("88888888888") ||
                CPF.equals("99999999999") || (CPF.length() != 11))
            return (false);

        char dig10, dig11;
        int sum, i, r, num, mult;

        try {
            // Calculo do 1o. Digito Verificador
            sum = 0;
            mult = 10;
            for (i = 0; i < 9; i++) {
                // converte o i-esimo caractere do CPF em um numero:
                // por exemplo, transforma o caractere '0' no inteiro 0
                // (48 eh a posicao de '0' na tabela ASCII)
                num = (int) (CPF.charAt(i) - 48);
                sum = sum + (num * mult);
                mult = mult - 1;
            }

            r = 11 - (sum % 11);
            if ((r == 10) || (r == 11))
                dig10 = '0';
            else
                dig10 = (char) (r + 48); // converte no respectivo caractere numerico

            // Calculo do 2o. Digito Verificador
            sum = 0;
            mult = 11;
            for (i = 0; i < 10; i++) {
                num = (int) (CPF.charAt(i) - 48);
                sum = sum + (num * mult);
                mult = mult - 1;
            }

            r = 11 - (sum % 11);
            if ((r == 10) || (r == 11))
                dig11 = '0';
            else
                dig11 = (char) (r + 48);


            // Verifica se os digitos calculados conferem com os digitos informados.
            if ((dig10 == CPF.charAt(9)) && (dig11 == CPF.charAt(10)))
                return (true);
            else
                return (false);
        } catch (InputMismatchException err) {
            return (false);
        }
    }
}