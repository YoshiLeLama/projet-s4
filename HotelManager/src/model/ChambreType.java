package model;

public enum ChambreType {
    CHAMBRE_SIMPLE {
        @Override
        public String toString() {
            return "Chambre Simple";
        }
    },
    CHAMBRE_DOUBLE {
        @Override
        public String toString() {
            return "Chambre double";
        }
    },
    SUITE_SIMPLE {
        @Override
        public String toString() {
            return "Suite simple";
        }
    },
    SUITE_PRESIDENTIELLE {
        @Override
        public String toString() {
            return "Suite présidentielle";
        }
    },


}
