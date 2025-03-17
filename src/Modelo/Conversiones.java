package Modelo;

/**
 *
 * @author Gabriel Torres & Emerson Vera
 */
public class Conversiones {

    public boolean validarNumero(String num) {
        String caracteresDecimales = "0123456789.";
        for (int i = 0; i < num.length(); i++) {
            char caracter = num.charAt(i);
            if (caracteresDecimales.indexOf(caracter) == -1) {
                return false;
            }
        }
        return true;
    }

    public String[] convertirAIEEE32(double num) {
        String[] IEEE = new String[3];
        String signo = (num < 0) ? "1" : "0";
        if (num < 0) {
            num = -num;
        }

        String[] exponente = conseguirExponente32(num);
        double fraccionaria = Double.parseDouble(exponente[1]);
        String mantisa = conseguirMantisa32(fraccionaria);

        IEEE[0] = signo;
        IEEE[1] = exponente[0];
        IEEE[2] = mantisa;
        return IEEE;
    }

    public String conseguirSigno(double num) {
        if (num < 0) {
            return "1";
        } else {
            return "0";
        }
    }

    public String[] conseguirExponente32(double num) {
        String[] exponente = new String[2];
        int exponenteReal = 0;

        while (num >= 2.0) {
            num /= 2.0;
            exponenteReal++;
        }
        while (num < 1.0) {
            num *= 2.0;
            exponenteReal--;
        }

        int exponenteSesgado = exponenteReal + 127;
        exponente[0] = String.format("%8s", Integer.toBinaryString(exponenteSesgado)).replace(' ', '0');
        exponente[1] = Double.toString(num - 1.0);
        return exponente;
    }

    public String conseguirMantisa32(double num) {
        StringBuilder mantisa = new StringBuilder();
        double fraccion = num;

        for (int i = 0; i < 23; i++) {
            fraccion *= 2;
            if (fraccion >= 1.0) {
                mantisa.append("1");
                fraccion -= 1.0;
            } else {
                mantisa.append("0");
            }
        }
        return mantisa.toString();
    }

    public String[] convertirAIEEE64(double num) {
        String[] IEE = new String[3];
        String signo = conseguirSigno(num);
        if (num < 0) {
            num = num * -1;
        }
        String[] exponente = conseguirExponente64(num);
        double tmp = Double.parseDouble(exponente[1]);
        String mantisa = conseguirMantisa64(tmp);
        IEE[0] = signo;
        IEE[1] = exponente[0];
        IEE[2] = mantisa;

        return IEE;
    }

    public static String[] conseguirExponente64(double num) {
        String[] exponente = new String[2];
        int exponenteReal = 0;

        while (num >= 2.0) {
            num /= 2.0;
            exponenteReal++;
        }
        while (num < 1.0) {
            num *= 2.0;
            exponenteReal--;
        }

        int exponenteSesgado = exponenteReal + 1023;
        exponente[0] = String.format("%11s", Integer.toBinaryString(exponenteSesgado)).replace(' ', '0');
        exponente[1] = Double.toString(num - 1.0);
        return exponente;
    }

    public String conseguirMantisa64(double num) {
        if (num >= 1) {
            num -= 1;
        }
        String bin = "";
        boolean check = true;
        while (check) {
            num *= 2;
            if (num > 1) {
                bin += "1";
                num -= 1;
            } else if (num == 1) {
                check = false;
            } else {
                bin += "0";
            }
        }
        bin = bin + "1";
        while (bin.length() < 52) {
            bin = bin + "0";
        }
        return bin;
    }

    public String convertirDeIEEE(String IEEE) {
        String resultado = "";
        if (IEEE.length() == 64) {
            resultado = convertirDeIEEE64(IEEE);
        } else {
            resultado = convertirDeIEEE32(IEEE);
        }

        return resultado;
    }

    public String convertirDeIEEE32(String IEEE) {

        char signoBit = IEEE.charAt(0);
        double signo = 0;
        if (signoBit == '0') {
            signo = 1;
        } else {
            signo = -1;
        }
        String exponenteBinario = IEEE.substring(1, 9);
        int exponenteSesgado = Integer.parseInt(exponenteBinario, 2);
        int exponenteReal = exponenteSesgado - 127;

        String mantisaBinario = IEEE.substring(9);
        double mantisa = 1.0;

        if (exponenteSesgado == 0) {
            mantisa = 0.0;
            exponenteReal = -126;
        }
        for (int i = 0; i < mantisaBinario.length(); i++) {
            if (mantisaBinario.charAt(i) == '1') {
                mantisa += Math.pow(2, -(i + 1));
            }
        }
        double valorDecimal = signo * mantisa * Math.pow(2, exponenteReal);

        return Double.toString(valorDecimal);
    }

    public String convertirDeIEEE64(String IEEE) {
        char signoBit = IEEE.charAt(0);
        double signo = 0;
        if (signoBit == '0') {
            signo = 1;
        } else {
            signo = -1;
        }
        String exponenteBinario = IEEE.substring(1, 12);
        int exponenteSesgado = Integer.parseInt(exponenteBinario, 2);
        int exponenteReal = exponenteSesgado - 1023;

        String mantisaBinario = IEEE.substring(12);
        double mantisa = 1.0;

        if (exponenteSesgado == 0) {
            mantisa = 0.0;
            exponenteReal = -1022;
        }

        for (int i = 0; i < mantisaBinario.length(); i++) {
            if (mantisaBinario.charAt(i) == '1') {
                mantisa += Math.pow(2, -(i + 1));
            }
        }

        double valorDecimal = signo * mantisa * Math.pow(2, exponenteReal);

        return Double.toString(valorDecimal);

    }
}
