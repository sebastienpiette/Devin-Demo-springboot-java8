package hello;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class ApplicationSmokeTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    void contextLoadsAndSeedsCustomers() {
        Integer customerCount = jdbcTemplate.queryForObject(
                "SELECT COUNT(*) FROM customers", Integer.class);

        assertThat(customerCount).isEqualTo(4);
    }
}
