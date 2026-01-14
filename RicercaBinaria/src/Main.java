/*
 * Autore: Makaoui Youness
 * Data: 14/01/2026
 * Luogo: xx
 * Descrizione:
 */

import java.util.Arrays;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        //
        Random random = new Random();
        // array da 10 elementi popolato randomicamente
        int[] array = new int[10];
        for (int i = 0; i < array.length; i++) {
            array[i] = i * random.nextInt(100);
        }

        // numero da trovare
        int num = random.nextInt(100);
        // per il primo caso inserisco il numero da trovare all'interno dell'array.
        array[random.nextInt(array.length)] = num;

        //ordinamento dell'array selection sort
        for(int i=0;i<array.length;i++){
            for(int j=i+1;j<array.length;j++){
                if(array[i]>array[j]){
                    int temp = array[i];
                    array[i]=array[j];
                    array[j]=temp;
                }
            }
        }

        System.out.println("Array: "+Arrays.toString(array));
        System.out.println("Numero: "+num);

        int min = 0;
        int max = array.length-1;
        int med = (min+max)/2;

        // caso 1 con numero sempre presente all'interno dell'array
        while(min<=max){
            if(array[med]==num){
                System.out.println("Trovato!");
                break;
            }else if(array[med]<num){
                min=med+1;
            }else{
                max=med-1;
            }
            med=(min+max)/2;
        }

        // caso 2
        for (int i = 0; i < array.length; i++) {
            array[i] = i * random.nextInt(100);
        }

        //ordinamento dell'array selection sort
        for(int i=0;i<array.length;i++){
            for(int j=i+1;j<array.length;j++){
                if(array[i]>array[j]){
                    int temp = array[i];
                    array[i]=array[j];
                    array[j]=temp;
                }
            }
        }

        System.out.println("Array: "+Arrays.toString(array));
        System.out.println("Numero: "+num);

        max = array.length-1;
        min = 0;
        med = (min+max)/2;

        // caso 2 con numero non sempre presente all'interno dell'array
        while(min <= max){
            if(array[med]==num){
                System.out.println("Trovato!");
                break;
            }else if(array[med]<num){
                min=med+1;
            }else{
                max=med-1;
            }
            med=(min+max)/2;
        }
        System.out.println("Numero non trovato!");
    }
}