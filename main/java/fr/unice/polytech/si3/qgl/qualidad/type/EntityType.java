package fr.unice.polytech.si3.qgl.qualidad.type;

public enum EntityType {
    OAR {
        @Override
        public String toString() {
            return "oar";
        }
    },
    ALTERNATIVE_OAR {
        @Override
        public String toString() {
            return "Alternative oar";
        }
    },
    SAIL {
        @Override
        public String toString() {
            return "sail";
        }
    },
    RUDDER {
        @Override
        public String toString() {
            return "rudder";
        }
    },
    WATCH {
        @Override
        public String toString() {
            return "watch";
        }
    },
    CANON {
        @Override
        public String toString() {
            return "canon";
        }
    };

    public static EntityType get(String string) {
        switch (string) {
            case "oar":
                return OAR;
            case "sail":
                return SAIL;
            case "rudder":
                return RUDDER;
            case "watch":
                return WATCH;
            case "canon":
                return CANON;
            default:
                throw new IllegalArgumentException("Wrong entityType");

        }
    }
}