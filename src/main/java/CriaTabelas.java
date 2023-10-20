
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;


public class CriaTabelas {
	public static void main(String[] args) {

		EntityManagerFactory factory = Persistence.createEntityManagerFactory("LojaVirtualPU");
		EntityManager manager = factory.createEntityManager();

		EntityTransaction trx = manager.getTransaction();
		trx.begin();

		trx.commit();



	}

}
