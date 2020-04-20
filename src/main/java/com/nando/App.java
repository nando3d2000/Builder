package com.nando;

import java.util.Scanner;

enum Color{
  BLANCO,ROJO,NEGRO,AZUL,GRIS,PLATA
}

enum TipoMotor{
    GASOLINA,ELECTRICO
}

//Producto
class Carro{
  public int pasajeros;
  public String Matricula;
  public Boolean publico;
  public Color pintura;
  public TipoMotor motor;

  public Carro(){};

  public void Matricular(int pas,String matricula){
    this.pasajeros=pas;
    this.Matricula=matricula;
  }

  public void isServicioPublico(Boolean is){
    this.publico=is;
  }

  public void Pintar(Color color){
    this.pintura=color;
  }

  public void setMotor(TipoMotor tipo){
    this.motor=tipo;
  }
}
//COnstructor
abstract class constructorCarros{
  protected Carro vehiculo;

  public Carro getCarro(){
    return vehiculo;
  }
  public void crearCarroNuevo(){
    vehiculo=new Carro();
  }

  public void Matricular(){
    Scanner entradaTeclado=new Scanner(System.in);
    String matricula;
    int pasajeros;
    System.out.println("Ingrese el numero de la matricula: ");
    matricula=entradaTeclado.nextLine();
    System.out.println("Ingrese el numero de pasajeros: ");
    pasajeros=entradaTeclado.nextInt();
    vehiculo.Matricular(pasajeros, matricula);
    int opc=0;
    do{
      System.out.println("Escoja una opcion: ");
      System.out.println("1.Servicio publico.");
      System.out.println("2.Servicio privado.");
      opc=entradaTeclado.nextInt();
    }while(opc<1 || opc>2);
    if(opc==1){
      vehiculo.isServicioPublico(true);
    }
    else{
      vehiculo.isServicioPublico(false);
    }
    entradaTeclado.close();
  }

  public abstract void Pintura();
  public abstract void Motorizacion();
}

//Constructor concreto auto rojo ELECTRICO
class CarroRojoElectrico extends constructorCarros
{
  @Override
  public void Pintura(){
    Color rojo=Color.ROJO;
    vehiculo.Pintar(rojo);
  }

  @Override
  public void Motorizacion(){
    TipoMotor motor=TipoMotor.ELECTRICO;
    vehiculo.setMotor(motor);
  }
}
//Constructor concreto auto rojo GASOLINA
class CarroRojoGasolina extends constructorCarros
{
  @Override
  public void Pintura(){
    Color rojo=Color.ROJO;
    vehiculo.Pintar(rojo);
  }

  @Override
  public void Motorizacion(){
    TipoMotor motor=TipoMotor.GASOLINA;
    vehiculo.setMotor(motor);
  }
}

//Director
class Director{
  private constructorCarros construyecarros;

  public void setconstructor(constructorCarros fb){
    construyecarros=fb;
  }

  public Carro obtenerCarro(){
    return construyecarros.getCarro();
  }

  public void construirCarro(){
    construyecarros.crearCarroNuevo();
    construyecarros.Matricular();
    construyecarros.Pintura();
    construyecarros.Motorizacion();
  }

}


public class App
{
    public static void main( String[] args )
    {
        Director director=new Director();
        constructorCarros electricorojo=new CarroRojoElectrico();

        director.setconstructor(electricorojo);
        director.construirCarro();

        Carro vehiculo=director.obtenerCarro();
        System.out.println("Las caracteristicas del carro construido son:");
        System.out.println("Cantidad de pasajeros: "+ vehiculo.pasajeros);
        System.out.println("Matricula: "+ vehiculo.Matricula);
        System.out.println("Color: "+ vehiculo.pintura);
        System.out.println("Motor: "+ vehiculo.motor);
        if(vehiculo.publico){
          System.out.println("Servicio publico");
        }
        else{
          System.out.println("Servicio privado");
        }
    }
}
