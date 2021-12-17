package fr.unice.polytech.si3.qgl.qualidad.type;

public enum ModeType {
    REGATTA {
        @Override
        public String toString() {
            return "REGATTA";
        }
    },
    BATTLE {
        @Override
        public String toString() {
            return "BATTLE";
        }
    };

    public static ModeType get(String string) {
        switch (string) {
            case "REGATTA":
                return REGATTA;
            case "BATTLE":
                return BATTLE;
            default:
                throw new IllegalArgumentException("Wrong modeType");

        }
    }
}