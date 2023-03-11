package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class CarDaoImpl implements CarDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void add(Car car) {
        sessionFactory.getCurrentSession().save(car);
    }

    @Override
    public List<Car> listCars() {
        TypedQuery<Car> query=sessionFactory.getCurrentSession().createQuery("from Car");
        return query.getResultList();
    }

    public List<User> usersByModelAndSeries(String model, int series) {
        TypedQuery<User> query = sessionFactory.getCurrentSession()
                .createQuery("select c.user from Car c inner join c.user u where c.model = :model and c.series = :series");
        query.setParameter("model", model);
        query.setParameter("series", series);

        return query.getResultList();
    }
    /*
      * query = session.createQuery("select e.name, a.city from Employee e INNER JOIN e.address a");
		List<Object[]> list = query.list();
		for(Object[] arr : list){
			System.out.println(Arrays.toString(arr));
		}*/
}
