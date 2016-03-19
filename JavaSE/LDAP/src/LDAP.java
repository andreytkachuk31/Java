import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.*;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Andrii_Tkachuk
 * @since 7/23/2015
 */
public class LDAP {

    public static void main(String[] args) throws NamingException {
        Hashtable<String, Object> env = new Hashtable<String, Object>();
        env.put(Context.INITIAL_CONTEXT_FACTORY,  "com.sun.jndi.ldap.LdapCtxFactory");
        env.put(Context.PROVIDER_URL,  "ldap://localhost:389/dc=maxcrc,dc=com");

        DirContext ctx = new InitialDirContext(env);

        List<String> list = new LinkedList<String>();
        NamingEnumeration results = null;
        try {
            SearchControls controls = new SearchControls();
            controls.setSearchScope(SearchControls.SUBTREE_SCOPE);
            results = ctx.search("", "(objectclass=person)", controls);

            while (results.hasMore()) {
                SearchResult searchResult = (SearchResult) results.next();
                Attributes attributes =  searchResult.getAttributes();
                Attribute attr = attributes.get("cn");
                String cn = (String) attr.get();
                list.add(cn);
            }
        }  catch (NamingException e) {
            throw new RuntimeException(e);
        } finally {
            closeNamingEnumerationResult(results);
            closeContext(ctx);
        }
        System.out.println(list);
    }

    private static void closeContext(DirContext ctx) {
        if (ctx != null) {
            try {
                ctx.close();
            } catch (Exception e) {
                // Never mind this.
            }
        }
    }

    private static void closeNamingEnumerationResult(NamingEnumeration results) {
        if (results != null) {
            try {
                results.close();
            } catch (Exception e) {
                // Never mind this.
            }
        }
    }
}
