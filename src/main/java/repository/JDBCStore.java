package repository;

import model.Accident;
import model.AccidentType;
import model.Rule;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import service.Store;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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
    public void addAccident(Accident accident, List<Rule> rules, AccidentType accidentType) {
        if (accident.getAccidentId() != 0) {
            updateAccident(accident, rules, accidentType);
        } else {
            KeyHolder keyHolder = new GeneratedKeyHolder();
            jdbcTemplate.update(connection -> {
                PreparedStatement ps = connection.prepareStatement("INSERT INTO accident (accidentname, accidenttext, accidentaddress)  VALUES(?,?,?)",
                        new String[] {"accidentid"});
                ps.setString(1, accident.getAccidentName());
                ps.setString(2, accident.getAccidentText());
                ps.setString(3, accident.getAccidentAddress());
                return ps;
            }, keyHolder);
            batchInsertRule(rules, (Integer) keyHolder.getKey());
            jdbcTemplate.update("INSERT INTO accident_type (accident_id, type_id)  VALUES(?, ?)",
                    keyHolder.getKey(), accidentType.getTypeId());
        }
    }

    public int[] batchInsertRule(List<Rule> rules, int accidentId) {
        return this.jdbcTemplate.batchUpdate(
                "INSERT INTO accident_rule (rule_id, accident_id)  VALUES(?, ?)",
                new BatchPreparedStatementSetter() {
                    public void setValues(PreparedStatement ps, int i) throws SQLException {
                        ps.setInt(1, rules.get(i).getRuleId());
                        ps.setInt(2, accidentId);
                    }
                    public int getBatchSize() {
                        return rules.size();
                    }
                });
    }


    @Override
    public void updateAccident(Accident accident, List<Rule> rules, AccidentType accidentType) {
        jdbcTemplate.update("UPDATE accident SET accidentName=?, accidentText=?, accidentAddress=? WHERE accidentId=?",
                accident.getAccidentName(), accident.getAccidentText(), accident.getAccidentAddress(), accident.getAccidentId());
        jdbcTemplate.update("DELETE FROM accident_rule WHERE accident_id=?", accident.getAccidentId());
        batchInsertRule(rules, accident.getAccidentId());
        jdbcTemplate.update("UPDATE accident_type SET type_id =? WHERE accident_id=?",
                accidentType.getTypeId(), accident.getAccidentId());

    }

    @Override
    public List<Accident> getAllAccidents() {
        List<Accident> accidents = jdbcTemplate.query("SELECT accident.accidentId, accident.accidentName, accident.accidentText, accident.accidentAddress FROM accident",
                         new BeanPropertyRowMapper<>(Accident.class));
        for (Accident a:accidents) {
            a.setAccidentType(getAccidentTypeByAccidentId(a.getAccidentId()));
            a.setRules(getRulesByAccidentId(a.getAccidentId()));
        }
        return accidents;
    }

    @Override
    public Accident findAccidentById(String id) {
        int integerId = Integer.parseInt(id);
        return jdbcTemplate.query("SELECT * FROM accident"
               + " WHERE accident.accidentId = ?", new Object[]{integerId}, new BeanPropertyRowMapper<>(Accident.class))
                .stream().findAny().orElse(null);
    }

    @Override
    public List<Rule> getRulesByAccidentId(int id) {
        return jdbcTemplate.query("SELECT rules.ruleId, rules.ruleName FROM rules"
               + " LEFT JOIN accident_rule ON accident_rule.rule_id = rules.ruleId"
               + " WHERE accident_rule.accident_id = ?", new Object[]{id}, new BeanPropertyRowMapper<>(Rule.class));
    }

    @Override
    public AccidentType getAccidentTypeByAccidentId(int id) {
        return jdbcTemplate.query("SELECT types.typeId, types.typeName FROM types"
                + " LEFT JOIN accident_type ON accident_type.type_id = types.typeId"
                + " WHERE accident_type.accident_id = ?", new Object[]{id}, new BeanPropertyRowMapper<>(AccidentType.class))
                .stream().findAny().orElse(null);
    }

    @Override
    public List<Rule> getAllRules() {
        return  jdbcTemplate.query("SELECT rules.ruleId, rules.ruleName FROM rules",
                new BeanPropertyRowMapper<>(Rule.class));
    }

    @Override
    public List<AccidentType> getAllAccidentType() {
        return  jdbcTemplate.query("SELECT types.typeId, types.typeName FROM types",
                new BeanPropertyRowMapper<>(AccidentType.class));
    }

    @Override
    public List<Rule> getAllRulesByIds(String[] ids) {
        int[] numbers = Arrays.stream(ids).mapToInt(Integer::parseInt).toArray();
        List<Integer> list = Arrays.stream(numbers).boxed().collect(Collectors.toList());
        SqlParameterSource parameters = new MapSqlParameterSource("ids", list);
        NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(jdbcTemplate.getDataSource());
        List<Rule> rules = namedParameterJdbcTemplate.query(
                "SELECT * FROM rules WHERE rules.ruleId in (:ids)",
                parameters,
                (rs, rowNum) -> new Rule(rs.getInt("ruleid"), rs.getString("rulename"))
        );
        return rules;
    }

    @Override
    public AccidentType getAccidentTypeById(String id) {
        int integerId = Integer.parseInt(id);
        return jdbcTemplate.query("SELECT types.typeId, types.typeName FROM types WHERE types.typeId = ?",
        new Object[]{integerId}, new BeanPropertyRowMapper<>(AccidentType.class)).stream().findAny().orElse(null);
    }

}
