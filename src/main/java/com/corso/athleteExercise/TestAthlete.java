package com.corso.athleteExercise;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Scanner;

public class TestAthlete {

    static final LOG L = LOG.getInstance();

    public static void main(String[] args) throws IOException, ParseException, SQLException {
        Scanner s = new Scanner(System.in);
        Athlete a = new Athlete();

        AthleteOperations athleteOperations = new AthleteOperations();
        athleteOperations.connect();
        athleteOperations.createTable();

        System.out.println("\n");
        L.info("Decidi cosa vuoi fare : \n" +
                "1: Inserisci un nuovo atleta \n" +
                "2: Modifica il nome di un atleta specifico \n" +
                "3: Elimina un atleta specifico \n" +
                "4: Stampa tutti gli atleti con un altezza in particolare \n" +
                "5: Stampa l'atleta con un code specifico \n" +
                "6: Stampa tutti gli atleti");

        int scelta = s.nextInt();

        try {

            switch (scelta) {
                case 1: {
                    L.info("Inserisci i dati come segue:\n" +
                            "code, name, nation, birthday(yyyy-MM-dd), altezza");
                    a = new Athlete(s.nextInt(), s.next(), s.next(), s.next(), s.nextDouble());
                    athleteOperations.insertObject(a);
                    break;
                }
                case 2: {
                    L.info("Inserisci il nome da sostituire ad un code(int) sopra elencato");
                    a.setName(s.next());
                    a.setCode(s.nextInt());
                    athleteOperations.updateObject(a);
                    break;
                }
                case 3: {
                    L.info("Inserisci l'id da voler eliminare");
                    a.setCode(s.nextInt());
                    athleteOperations.deleteObject(a);
                    break;
                }
                case 4:
                    L.info("Inserisci un valore di altezza(double) con il quale comparare gli atleti");
                    double temp1=s.nextDouble();
                    athleteOperations.findByHeight(temp1);
                    break;
                case 5:
                    L.info("Inserisci l'id da voler ricercare");
                    int temp=s.nextInt();
                    athleteOperations.findById(temp);
                    break;
                case 6:
                    athleteOperations.printAll();
                    break;
                default:
                    L.error("Numero inserito errato");
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
