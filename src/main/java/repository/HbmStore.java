package repository;

import model.Accident;
import model.AccidentType;
import model.Rule;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;
import service.Store;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.hibernate.SessionFactory;

/**
 * @author Andrey
 * @version 1
 * @since 31/01/21
 */


public class HbmStore implements Store {

    private final SessionFactory sf;

    public HbmStore(SessionFactory sf) {
        this.sf = sf;
    }

    public <T> T wrapperOne(final Function<Session, T> command) {
        final Session session = sf.openSession();
        final Transaction tx = session.beginTransaction();
        try {
            T rsl = command.apply(session);
            tx.commit();
            return rsl;
        } catch (final Exception e) {
            session.getTransaction().rollback();
            throw e;
        } finally {
            session.close();
        }
    }

    private <T> void wrapperTwo(BiConsumer<Session, T> function, T arg) {
        Session session = sf.openSession();
        session.beginTransaction();
        function.accept(session, arg);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void addAccident(Accident accident, List<Rule> rules, AccidentType accidentType) {
        accident.setRules(rules);
        accident.setAccidentType(accidentType);
        if (accident.getAccidentId() != 0) {
            this.wrapperTwo(Session::update, accident);
        }
        this.wrapperTwo(Session::save, accident);
    }

    @Override
    public void updateAccident(Accident accident, List<Rule> rules, AccidentType accidentType) {
        accident.setRules(rules);
        accident.setAccidentType(accidentType);
        this.wrapperTwo(Session::update, accident);
    }

    @Override
    public List<Accident> getAllAccidents() {
        return this.wrapperOne(
               session -> session.createQuery("select distinct ac from Accident ac "
                        + "join fetch ac.rules "
                        + "join fetch ac.accidentType "
               ).list());
    }

    @Override
    public Accident findAccidentById(String id) {
        int integerId = Integer.parseInt(id);
        return (Accident) this.wrapperOne(
                session -> session.createQuery("select distinct ac from Accident ac "
                        + "join fetch ac.rules "
                        + "join fetch ac.accidentType "
                        + " where ac.accidentId = :integerId ").setInteger("integerId", integerId).getSingleResult());
    }

    @Override
    public List<Rule> getRulesByAccidentId(int id) {
        return null;
    }

    @Override
    public AccidentType getAccidentTypeByAccidentId(int id) {
        return null;
    }

    @Override
    public List<Rule> getAllRules() {
        return this.wrapperOne(
                session -> session.createQuery("from Rule").list());
    }

    @Override
    public List<AccidentType> getAllAccidentType() {
        return this.wrapperOne(
                session -> session.createQuery("from AccidentType").list());
    }

    @Override
    public List<Rule> getAllRulesByIds(String[] ids) {
        int[] numbers = Arrays.stream(ids).mapToInt(Integer::parseInt).toArray();
        List<Integer> list = Arrays.stream(numbers).boxed().collect(Collectors.toList());
        return this.wrapperOne(
                session -> session.createQuery("FROM Rule r WHERE r.ruleId IN (:listId)")
                        .setParameterList("listId", list).list());
    }

    @Override
    public AccidentType getAccidentTypeById(String id) {
        int typeId = Integer.parseInt(id);
        return (AccidentType) this.wrapperOne(
                session -> session.createQuery("From AccidentType where typeId=:typeId")
                        .setInteger("typeId", typeId)
                        .uniqueResult()
        );
    }
}
