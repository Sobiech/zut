package pl.zut.pswa.service.ws.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.zut.pswa.entity.User;
import pl.zut.pswa.service.db.UserDbService;
import pl.zut.pswa.service.db.impl.UserDbServiceImpl;
import pl.zut.pswa.service.ws.UserWebService;

import javax.jws.WebService;
import java.util.List;
import java.util.Objects;

@WebService(endpointInterface = "pl.zut.pswa.service.ws.UserWebService")
public class UserWebServiceImpl
        extends AbstractWebServiceImpl implements UserWebService {

    private static final String PATH = "/user";

    private static final Logger logger = LoggerFactory.getLogger(UserWebServiceImpl.class);

    private UserDbService service;

    public UserWebServiceImpl() {
        this.service = new UserDbServiceImpl();
    }


    @Override
    public User getUserById(Long id) throws Exception {

        long start = System.currentTimeMillis();
        try {
            logger.info("getUserById(): by id:{}", String.valueOf(id));
            validateParameters(id);
            User user = service.findById(id);

            if (Objects.isNull(user) && id != null) {
                throw new Exception("User with given id " + id.toString() + " doesn't exists");
            }

            return user;
        } finally {
            logger.debug("getUserById(): done in {}[ms]", ( System.currentTimeMillis() - start ));
        }
    }


    @Override
    public List<User> findByFirstName(String firstName) throws Exception {

        long start = System.currentTimeMillis();
        try {
            logger.info("findByFirstName(): by firstName:{}", firstName);
            validateParameters(firstName);
            return service.findByFirstName(firstName);
        } finally {
            logger.debug("findByFirstName(): done in {}[ms]", ( System.currentTimeMillis() - start ));
        }
    }


    @Override
    public List<User> findByLastName(String lastName) throws Exception {

        long start = System.currentTimeMillis();
        try {
            logger.info("findByLastName(): by lastName:{}", lastName);
            validateParameters(lastName);
            return service.findByLastName(lastName);
        } finally {
            logger.debug("findByPhoneNumber(): done in {}[ms]", ( System.currentTimeMillis() - start ));
        }
    }


    @Override
    public User findByPhoneNumber(String phone) throws Exception {

        long start = System.currentTimeMillis();
        try {

            logger.info("findByPhoneNumber(): by phone:{}", phone);
            validateParameters(phone);
            User user = service.findByPhone(phone);
            if (Objects.isNull(user) && phone != null) {
                throw new Exception("User with given phone " + phone + " doesn't exists");
            }
            return user;
        } finally {
            logger.debug("findByPhoneNumber(): done in {}[ms]", ( System.currentTimeMillis() - start ));
        }
    }


    @Override
    protected String getWebServicePath() {
        return PATH;
    }

    @Override
    protected Logger getLogger() {
        return logger;
    }

}
