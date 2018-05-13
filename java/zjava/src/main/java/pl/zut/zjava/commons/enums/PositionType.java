package pl.zut.zjava.commons.enums;

public enum PositionType {

    PRACOWNIK(Values.PRACOWNIK),
    HANDLOWIEC(Values.HANDLOWIEC),
    DYREKTOR(Values.DYREKTOR);

    private final String value;

    PositionType(final String value) {
        this.value = value;
    }

    public static class Values {
        public static final String PRACOWNIK = "PRACOWNIK";
        public static final String HANDLOWIEC = "HANDLOWIEC";
        public static final String DYREKTOR = "DYREKTOR";
    }

}
