package fr.unice.polytech.si3.qgl.qualidad.type;

public enum SideType {
    PORTSIDE {
        @Override
        public String toString() {
            return "Portside";
        }
    },
    STARBOARD {
        @Override
        public String toString() {
            return "Starboard";
        }
    },
    NO_SIDE {
        @Override
        public String toString() {
            return "No side";
        }
    },
    NOT_ASSIGNED {
        @Override
        public String toString() {
            return "Not assigned";
        }
    }
}