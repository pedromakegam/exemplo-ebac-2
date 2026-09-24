package br.com.pedro.ebac;

/** Operações aritméticas com double. Valores seguem a precisão de ponto flutuante do Java. */
public final class Calculadora {
    /**
     * Soma dois valores.
     * @param a primeira parcela
     * @param b segunda parcela
     * @return soma
     */
    public double adicionar(double a, double b) { return a + b; }
    /**
     * Subtrai b de a.
     * @param a minuendo
     * @param b subtraendo
     * @return diferença
     */
    public double subtrair(double a, double b) { return a - b; }
    /**
     * Multiplica dois valores.
     * @param a primeiro fator
     * @param b segundo fator
     * @return produto
     */
    public double multiplicar(double a, double b) { return a * b; }
    /**
     * Divide o dividendo pelo divisor.
     * @param dividendo valor a dividir
     * @param divisor valor diferente de zero
     * @return quociente
     * @throws ArithmeticException se divisor for zero, inclusive -0.0
     */
    public double dividir(double dividendo, double divisor) {
        if (divisor == 0.0) throw new ArithmeticException("Não é possível dividir por zero");
        return dividendo / divisor;
    }
}
