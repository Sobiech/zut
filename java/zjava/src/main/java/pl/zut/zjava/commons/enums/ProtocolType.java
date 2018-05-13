package pl.zut.zjava.commons.enums;

public enum ProtocolType {

    TCP_IP("t"),

    SOAP("s");

    private final String shortProtocol;


    ProtocolType(final String shortProtocol) {
        this.shortProtocol = shortProtocol;
    }


    public static ProtocolType getProtocolTypeByAbbrevation(String abbrevation) {

        for ( ProtocolType val : values() ){
            if ( val.shortProtocol.equalsIgnoreCase(abbrevation) ) {
                return val;
            }
        }

        throw new IllegalArgumentException("Podany skrot jest niepoprawny");
    }

}
