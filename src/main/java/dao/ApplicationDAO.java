package dao;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.sql.DataSource;

@Stateless
public class ApplicationDAO implements ApplicationDAOLocal {

   // Add business logic below.
    @Resource(lookup = "jdbc:/AMT")
    private DataSource dataSource;



}
