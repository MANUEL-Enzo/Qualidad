package fr.unice.polytech.si3.qgl.qualidad.type;

public enum ActionType {
    MOVING {
        @Override
        public String toString() {
            return "MOVING";
        }
    },
    LIFT_SAIL {
        @Override
        public String toString() {
            return "LIFT_SAIL";
        }
    },
    LOWER_SAIL {
        @Override
        public String toString() {
            return "LOWER_SAIL";
        }
    },
    TURN {
        @Override
        public String toString() {
            return "TURN";
        }
    },
    OAR {
        @Override
        public String toString() {
            return "OAR";
        }
    },
    USE_WATCH {
        @Override
        public String toString() {
            return "USE_WATCH";
        }
    },
    AIM {
        @Override
        public String toString() {
            return "AIM";
        }
    },
    FIRE {
        @Override
        public String toString() {
            return "FIRE";
        }
    },
    RELOAD {
        @Override
        public String toString() {
            return "RELOAD";
        }
    };

    public static ActionType get(String string) {
        switch (string) {
            case "MOVING":
                return MOVING;
            case "LIFT_SAIL":
                return LIFT_SAIL;
            case "LOWER_SAIL":
                return LOWER_SAIL;
            case "TURN":
                return TURN;
            case "OAR":
                return OAR;
            case "USE_WATCH":
                return USE_WATCH;
            case "AIM":
                return AIM;
            case "FIRE":
                return FIRE;
            case "RELOAD":
                return RELOAD;
            default:
                throw new IllegalArgumentException("Wrong actionType");

        }
    }
}