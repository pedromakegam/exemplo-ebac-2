package br.com.pedro.ebac;

public class ExemploFabrica {
            public static void main(String[] args) {
                mostrar(new FabricaEletrica());
                mostrar(new FabricaCombustao());
            }
            private static void mostrar(FabricaCarros fabrica) {
                System.out.println(fabrica.criarCarro().modelo() + " - " + fabrica.criarMotor().energia());
            }
        }
