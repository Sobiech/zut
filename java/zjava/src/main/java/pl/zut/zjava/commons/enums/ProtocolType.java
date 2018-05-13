package pl.zut.zjava.commons.enums;

import pl.zut.zjava.server.connection.strategy.ConnectionStrategy;
import pl.zut.zjava.server.connection.strategy.SoapConnectionStrategy;
import pl.zut.zjava.server.connection.strategy.TcpConnectionStrategy;

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

    public ConnectionStrategy getConnectionStrategy(String ws) {

        if ( this.equals(TCP_IP )) {
            return new TcpConnectionStrategy();
        }

        return new SoapConnectionStrategy(ws);
    }

}
