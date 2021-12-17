package fr.unice.polytech.si3.qgl.qualidad.type;

public enum ShapeType {
    CIRCLE {
        @Override
        public String toString() {
            return "circle";
        }
    },
    RECTANGLE {
        @Override
        public String toString() {
            return "rectangle";
        }
    },
    POLYGON {
        @Override
        public String toString() {
            return "polygon";
        }
    };

    public static ShapeType get(String string) {
        switch (string) {
            case "circle":
                return CIRCLE;
            case "rectangle":
                return RECTANGLE;
            case "polygon":
                return POLYGON;
            default:
                throw new IllegalArgumentException("Wrong shapeType");

        }
    }
}