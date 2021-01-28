package repository;

import model.Accident;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import service.Store;
import java.util.List;

/**
 * @author
 * @version 1
 * @since 27/01/21
 */

@Repository
public class JDBCStore implements Store {

    private final JdbcTemplate jdbcTemplate;

    public JDBCStore(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void addAccident(Accident accident) {
        if ((Integer) accident.getAccidentId() != null) {
            updateAccident(accident);
        }

        jdbcTemplate.update("INSERT INTO accident (accidentName, accidentText, accidentAddress)  VALUES(?,?,?)",
                accident.getAccidentName(), accident.getAccidentText(), accident.getAccidentAddress());
    }

    @Override
    public void updateAccident(Accident accident) {
        jdbcTemplate.update("UPDATE accident SET accidentName=?, accidentText=?, accidentAddress=? WHERE id=?",
                accident.getAccidentName(), accident.getAccidentText(), accident.getAccidentAddress());
    }

    @Override
    public List<Accident> getAllAccidents() {
       return jdbcTemplate.query("SELECT accident.accidentId, accident.accidentName, accident.accidentText, accident.accidentAddress FROM accident",
                         new BeanPropertyRowMapper<>(Accident.class));
    }

    @Override
    public Accident findAccidentById(int id) {
        return jdbcTemplate.query("SELECT accident.accidentId, accident.accidentName, accident.accidentText, accident.accidentAddress SELECT accident"
               + " WHERE accident.accidentId = ?", new Object[]{id}, new BeanPropertyRowMapper<>(Accident.class))
                .stream().findAny().orElse(null);
    }
}
