package repository;

import model.Accident;
import model.AccidentType;
import model.Rule;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import service.Store;

import java.util.List;

/**
 * @author
 * @version 1
 * @since
 */

@Repository
public class JDBCStore implements Store {

    private final JdbcTemplate jdbcTemplate;

    public JDBCStore(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void addAccident(Accident accident) {
        if ((Integer) accident.getId() != null) {
            updateAccident(accident);
        }
        jdbcTemplate.update("INSERT INTO accident (name, text, address, type_id, rule_id)  VALUES(?,?,?,?,?)", accident.getName(), accident.getText(), accident.getAddress(),
                accident.getAccidentType().getId(), accident.getRule().getId());
    }

    @Override
    public void updateAccident(Accident accident) {
        jdbcTemplate.update("UPDATE accident SET name=?, text=?, address=?, type_id=?, rule_id=? WHERE id=?",
                accident.getName(), accident.getText(), accident.getAddress(),
                accident.getAccidentType().getId(), accident.getRule().getId(), accident.getId());
    }

    @Override
    public List<Accident> getAllAccidents() {
       return jdbcTemplate.query("SELECT accident.id, accident.name, accident.text, accident.address, rules.name, types.name FROM accident"
                        + " LEFT JOIN rules ON accident.rule_id = rules.id"
                + " LEFT JOIN types ON accident.type_id = types.id", new BeanPropertyRowMapper<>(Accident.class));
    }

    @Override
    public List<AccidentType> getAllAccidentTypes() {
        return jdbcTemplate.query("SELECT* FROM types", new BeanPropertyRowMapper<>(AccidentType.class));
    }

    @Override
    public List<Rule> getAllRules() {
        return jdbcTemplate.query("SELECT* FROM rules", new BeanPropertyRowMapper<>(Rule.class));
    }

    @Override
    public Rule findRuleById(int id) {
        return jdbcTemplate.query("SELECT* FROM rules WHERE id=?", new Object[]{id}, new BeanPropertyRowMapper<>(Rule.class))
                .stream().findAny().orElse(null);

    }

    @Override
    public AccidentType findAccidentTypeById(int id) {
        return jdbcTemplate.query("SELECT* FROM rules WHERE id=?", new Object[]{id}, new BeanPropertyRowMapper<>(AccidentType.class))
                .stream().findAny().orElse(null);
    }

    @Override
    public Accident findAccidentById(int id) {
        return jdbcTemplate.query("select accident.id, accident.name, accident.text, accident.address, rules.name, types.name from accident"
               + " left join rules on accident.rule_id = rules.id"
               + " left join types on accident.type_id = types.id"
               + " where accident.id = ?", new Object[]{id}, new BeanPropertyRowMapper<>(Accident.class))
                .stream().findAny().orElse(null);
    }
}
