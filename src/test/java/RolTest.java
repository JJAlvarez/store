import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import unis.stores.MainApplicationClass;
import unis.stores.entities.Rol;
import unis.stores.services.rol.RolService;

@SpringBootTest(classes = MainApplicationClass.class)
@RunWith(SpringRunner.class)
@PropertySource("classpath:application-test.properties")
public class RolTest {

    @Autowired
    RolService rolService;

    private Rol testRol = new Rol();

    private static final String NEW_ROL_NAME = "New Rol Name";

    public RolTest() {
        this.testRol.setName("Rol");
    }

    @Before
    public void createRol() {
        MockitoAnnotations.initMocks(this);
        this.testRol = rolService.createRol(this.testRol.getName());

        Assertions.assertNotEquals(null, this.testRol);
    }

    @After
    public void deleteRol() {
        Assertions.assertEquals(true, rolService.deleteRol(this.testRol.getId()));
    }

    @Test
    public void updateRol() {
        this.testRol.setName(NEW_ROL_NAME);
        this.testRol = rolService.updateRol(this.testRol.getId(), this.testRol.getName());

        Assertions.assertEquals(NEW_ROL_NAME, this.testRol.getName());
    }

    @Test
    public void getRol() {
        Assertions.assertNotEquals(null, rolService.getRolById(this.testRol.getId()));
    }

    @Test
    public void checkNoEmpty() {
        Rol emptyRol = rolService.createRol(null);

        Assertions.assertEquals(null, emptyRol);
    }
}
