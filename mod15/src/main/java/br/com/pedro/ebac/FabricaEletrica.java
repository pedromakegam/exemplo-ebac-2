package br.com.pedro.ebac;

public class FabricaEletrica implements FabricaCarros {
            public Carro criarCarro() { return new HatchEletrico(); }
            public Motor criarMotor() { return new MotorEletrico(); }
            private record HatchEletrico() implements Carro {
                public String modelo() { return "Hatch elétrico"; }
                public String familia() { return "elétrica"; }
            }
            private record MotorEletrico() implements Motor {
                public String energia() { return "bateria"; }
                public String familia() { return "elétrica"; }
            }
        }
