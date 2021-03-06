package controleFrota.Impl;

import controleFrota.Acelerador;
import controleFrota.Autenticavel;

public class Carro implements Acelerador, Autenticavel {
    private String _letrasPlaca;    
    private int _numerosPlaca;    
    private int _chassi;
    private int _velocidadeAtual;
    private Motorista _motorista;

    public Carro(String placa, int chassi, Motorista motorista) throws IllegalArgumentException {
        this(chassi, motorista);
        
        validarPlaca(placa);
        _letrasPlaca = placa.substring(0, 3);
        _numerosPlaca = Integer.parseInt(placa.substring(3));
    }

    public Carro(int chassi, Motorista motorista) throws IllegalArgumentException {
        validarChassi(chassi);
        _chassi = chassi;
        _velocidadeAtual = 0;
        _motorista = motorista;
        _motorista.setVeiculo(this);
    }

    public String getPlaca() {
        return _letrasPlaca + _numerosPlaca;
    }

    public int getChassi() {
        return _chassi;
    }

    public int getVelocidadeAtual() {
        return _velocidadeAtual;
    }

    public Motorista getMotorista() {
        return _motorista;
    }

    @Override
    public void acelerar() {
        _velocidadeAtual++;
    }

    @Override
    public void acelerar(int limite) {  
        for (int i = _velocidadeAtual; i < limite; i++) {
            acelerar();
        }
    }

    public void frear() {
        _velocidadeAtual--;
    }

    @Override
    public String toString() {
        return "Carro [placa=" + getPlaca() + ", chassi=" + _chassi + ", velocidadeAtual=" + _velocidadeAtual + "]";
    }

    private void validarPlaca(String placa) throws IllegalArgumentException {
        if (placa == null || placa.length() != 7 || !placa.matches("[A-Z]{3}\\d{4}")) {
            throw new IllegalArgumentException("Placa inválida, a placa deve conter 3 letras e quatro dígitos!");
        }
    }

    private void validarChassi(int chassi) throws IllegalArgumentException {
        if (chassi < 1000000 || chassi > 9999999) {
            throw new IllegalArgumentException("Chassi inválido, o valor aceito é entre 1000000 e 9999999!");
        }
    }

    @Override
    public String obterCredenciais() {
        return getPlaca();
    }
}
