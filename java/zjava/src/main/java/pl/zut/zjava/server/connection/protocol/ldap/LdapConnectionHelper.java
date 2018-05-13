package pl.zut.zjava.server.connection.protocol.ldap;

import org.apache.directory.ldap.client.api.LdapConnection;
import org.apache.directory.ldap.client.api.LdapConnectionConfig;
import org.apache.directory.ldap.client.api.LdapNetworkConnection;
import pl.zut.zjava.commons.Config;

public class LdapConnectionHelper {


    public static LdapConnection createDefaultConnection() {

            LdapConnectionConfig
                config = new LdapConnectionConfig();
                config.setLdapHost(Config.getLdapHostAddress());
                config.setLdapPort(Config.getLdapServerPort());
                config.setTimeout(10);

        return new LdapNetworkConnection( config);
    }

}
