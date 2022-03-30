package com.corso.esercizioDb2;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

public class LOG {


        public static final Logger L = Logger.getLogger(LOG.class);

        //creiamo un istanza(public static final) ed un costruttore private afifnche sia un Singleton
        public static LOG instance = null;

        //configurazione log4j
        {
            BasicConfigurator.configure();
        }


        private LOG() {

        }

        /**
         * @return instance
         */
        //in questo modo può essere richiamato dalle altre classi(con lo static)
        public static LOG getInstance() {
            if (instance == null)
                synchronized (LOG.class) {
                    instance = new LOG();
                }
            return instance;
        }

        public void info(String s) {
            //in questo modo stampiamo le info
            L.info(s);
        }

        private void debug(String s) {
            L.debug(s);
        }

        public void warn(String s) {
            L.warn(s);
        }

        public void error(String s) {
            //in questo modo stampiamo l'errore
            L.error(s);
        }

}
