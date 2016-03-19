package dao.impl;

import dao.PersonDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ldap.core.AttributesMapper;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.stereotype.Component;

import javax.naming.NamingException;
import javax.naming.directory.Attributes;
import java.util.List;

/**
 * @author Andrii_Tkachuk
 * @since 8/5/2015
 */
@Component
public class PersonDaoImpl implements PersonDao {

    @Autowired
    private LdapTemplate ldapTemplate;

    @Override
    public List getAllPersonNames() {
        return ldapTemplate.search("", "(objectclass=person)",
                new AttributesMapper() {
                    public Object mapFromAttributes(Attributes attrs) throws NamingException {
                        return attrs.get("cn").get();
                    }
                });
    }
}
