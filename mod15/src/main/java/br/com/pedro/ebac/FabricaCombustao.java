package br.com.pedro.ebac;

public class FabricaCombustao implements FabricaCarros {
            public Carro criarCarro() { return new SedanFlex(); }
            public Motor criarMotor() { return new MotorFlex(); }
            private record SedanFlex() implements Carro {
                public String modelo() { return "Sedã flex"; }
                public String familia() { return "combustão"; }
            }
            private record MotorFlex() implements Motor {
                public String energia() { return "etanol ou gasolina"; }
                public String familia() { return "combustão"; }
            }
        }
