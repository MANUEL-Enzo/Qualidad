package fr.unice.polytech.si3.qgl.qualidad.type;

public enum VisibleEntityType {
    SHIP {
        @Override
        public String toString() {
            return "ship";
        }
    },
    REEF {
        @Override
        public String toString() {
            return "reef";
        }
    },
    STREAM {
        @Override
        public String toString() {
            return "stream";
        }
    };

    public static VisibleEntityType get(String string) {
        switch (string) {
            case "ship":
                return SHIP;
            case "reef":
                return REEF;
            case "stream":
                return STREAM;
            default:
                throw new IllegalArgumentException("Wrong visibleEntityType");

        }
    }
}