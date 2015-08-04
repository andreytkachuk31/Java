import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.directory.*;
import java.util.Hashtable;

/**
 * @author Andrii_Tkachuk
 * @since 7/23/2015
 */
public class LDAP {

    public static void main(String[] args) {
        final String userName = "AWUser1";
        Hashtable env = initConfigLDAP(userName);

        DirContext ctx = null;
        NamingEnumeration results = null;
        try {
            ctx = new InitialDirContext(env);
            SearchControls controls = new SearchControls();
            controls.setSearchScope(SearchControls.SUBTREE_SCOPE);
            results = ctx.search("", "(objectclass=person)", controls);
            while (results.hasMore()) {
                SearchResult searchResult = (SearchResult) results.next();
                Attributes attributes = searchResult.getAttributes();
                System.out.println(" Person Common Name = " + attributes.get("cn"));
                System.out.println(" Person Display Name = " + attributes.get("displayName"));
                System.out.println(" Person logonhours = " + attributes.get("logonhours"));
                System.out.println(" Person MemberOf = " + attributes.get("memberOf"));
            }
        } catch (Throwable e) {
            e.printStackTrace();
        } finally {
            close(results);
            close(ctx);
        }
    }

    private static Hashtable initConfigLDAP(String userName) {
        Hashtable env = new Hashtable();
        env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
        env.put(Context.PROVIDER_URL, "ldap://192.168.1.102:389/dc=agileworks,dc=com");
        env.put(Context.SECURITY_AUTHENTICATION, "simple");
        env.put(Context.SECURITY_PRINCIPAL, new String("agileworks" + "\\" + userName));
        env.put(Context.SECURITY_CREDENTIALS, "$password1");
        return env;
    }

    private static void close(DirContext ctx) {
        if (ctx != null) {
            try {
                ctx.close();
            } catch (Exception e) {
            }
        }
    }

    private static void close(NamingEnumeration results) {
        if (results != null) {
            try {
                results.close();
            } catch (Exception e) {
            }
        }
    }
}
