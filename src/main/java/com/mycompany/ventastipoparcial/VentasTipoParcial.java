//Una empresa de dulces necesita un sistema 
//para registrar y gestionar las ventas diarias. Este sistema debe permitir 
//la lectura de un archivo de texto con los detalles de las ventas y el manejo 
//de posibles excepciones que puedan ocurrir durante este proceso. 
//Además, debe permitir guardar un informe con las ventas procesadas.


package com.mycompany.ventastipoparcial;

import com.mycompany.ventastipoparcial.salesProcessing.AppendToFile;
import com.mycompany.ventastipoparcial.salesProcessing.ReadFile;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class VentasTipoParcial {

    public static void main(String[] args) throws IOException {
        File file = new File ("./sales.txt");
        
        try {
            if (file.createNewFile()) {
                System.out.println("Archivo creado");
            } else {
                System.out.println("El archivo ya existe");
            }
        } catch (IOException e) {
            System.out.println("error" + e.getMessage());
        }
        
        ReadFile readFile = new ReadFile(file); //ESTA ES MI CLASE, LA QUE CREÉ PARA LEER EL ARCHIVO
        List<String> sales = readFile.loadSales(); 
        
        /* for (String line : sales){
            String [] splited = new String[3];
            splited = line.split(";");
            System.out.println("VENTAS: "+ splited[0] + " " + splited[1] + " " + splited[2]);
            
        } */
        
        for (String line : sales) {
            String[] splited = line.split(";");
            if (splited.length == 3) { //producto, cantidad, precio
                try {
                    String name = splited[0];
                    int quantity = Integer.parseInt(splited[1]);
                    double price = Double.parseDouble(splited[2]);
                    double total = quantity * price;
                    System.out.println("VENTAS: " + name + " " + quantity + " " + price + " " + " Total: " + total);
                } catch (NumberFormatException e) {
                    System.out.println("Error al parsear la línea: " + line);
                }
            } else {
                System.out.println("Formato incorrecto: " + line);
            }
        }
        
        System.out.println("¿Desea agregar alguna actualización de venta al final?");

        AppendToFile appender = new AppendToFile(file);
        appender.addLineToEOF("bombombun;50;300");
        
        
   
    }
}
