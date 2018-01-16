/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 *
 * @author dam
 */
public class UtilidadDeFechas {
    
    //Patrón utilizado para la conversión
    private static String FECHA_PATTERN = "dd/MM/yyyy";
    
    //Formateador de fecha
    public static final DateTimeFormatter FECHA_FORMATTER = DateTimeFormatter.ofPattern(FECHA_PATTERN);
    
    //Devuelve la fecha de entrada como un string formateado
    public static String formato(LocalDate fecha){
        
        if(fecha == null){
            return null;
        }
        
        return FECHA_FORMATTER.format(fecha);
        
    }
    
    public static LocalDate convertir(String fecha){
        
        try{
            return FECHA_FORMATTER.parse(fecha, LocalDate::from);
        }catch(DateTimeParseException e){
            return null;
        }
        
    }
    
    public static boolean fechaValida(String fecha){
        
        return UtilidadDeFechas.convertir(fecha) != null;
        
    }
    
}
